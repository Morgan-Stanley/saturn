package com.ms.qaTools.io.rowSource.jdbc
import com.ms.qaTools.io.rowSource.DatabaseConnection
import com.ms.qaTools.io.rowSource.MaximumRetryIterator
import com.ms.qaTools.Logger
import java.sql.ResultSet
import java.util.TimeZone
import org.joda.time.format.DateTimeFormat
import scala.collection.immutable.Stream.consWrapper
import scala.concurrent.ExecutionContext

case class IntervalTimestampQuery(intervalQueryStr: String, timestampField: String, timezone: String, timeBound: Long)
/*
 * Helper to create a SQL query string based on a timestamp field, a timezone and a timeboud
 */

object IntervalTimeStampQueryUtil {
  // Convert local time to time zone time
  def getTimeZoneTime(time: Long, timezone: String): Long = getTimeZoneTime(time, TimeZone.getTimeZone(timezone))

  def getTimeZoneTime(time: Long, timezone: TimeZone): Long = {
    val localTz = TimeZone.getDefault()
    val offset = timezone.getOffset(time) - localTz.getOffset(time)
    time + offset
  }

  // Convert time from time zone to the local time zone
  def getLocalTime(time: Long, timezone: String): Long = getLocalTime(time, TimeZone.getTimeZone(timezone))

  def getLocalTime(time: Long, timezone: TimeZone): Long = {
    val localTz = TimeZone.getDefault()
    val offset = timezone.getOffset(time) - localTz.getOffset(time)
    time - offset
  }

  def apply(query: String, timestampField: String, timezone: String, timeBound: Long, epochToTimestampStringFunc: Long => String) = {
    val localDbTime = getTimeZoneTime(timeBound, timezone)
    val textBound = epochToTimestampStringFunc(localDbTime)

    val intervalQuery = if (query.toUpperCase().matches("^SELECT\\s.+\\sWHERE\\s.+$")) {
      query + " AND %s >= %s".format(timestampField, textBound)
    }
    else if (query.toUpperCase().matches("^SELECT\\s.+$")) query + " WHERE %s >= %s".format(timestampField, textBound)
    else query
    intervalQuery
  }
}

trait IntervalTimestampQueryCreator {
  val epochToTimestampStringFunc: Long => String

  def createIntervalQuery(
    intervalQuery: String,
    timestampField: String,
    timezone: String,
    timeBound: Long) = IntervalTimestampQuery(intervalQuery, timestampField, timezone, timeBound)

  def createInitialQuery(query: String, timestampField: String, timezone: String, timeBound: Long, keepPreviousTime: Boolean = false): IntervalTimestampQuery = {
    val intervalQueryStr = IntervalTimeStampQueryUtil(query, timestampField, timezone, timeBound, epochToTimestampStringFunc)
    createIntervalQuery(intervalQueryStr, timestampField, timezone, if (keepPreviousTime) timeBound else System.currentTimeMillis)
  }

  def createFollowingQueries(query: String, previousIntervalQuery: IntervalTimestampQuery, keepPreviousTime: Boolean = false): Stream[IntervalTimestampQuery] = {
    val timestampField = previousIntervalQuery.timestampField
    val timezone = previousIntervalQuery.timezone
    val timeBound = previousIntervalQuery.timeBound

    val intervalQueryStr = IntervalTimeStampQueryUtil(query, timestampField, timezone, timeBound, epochToTimestampStringFunc)
    val time = if (keepPreviousTime) timeBound else System.currentTimeMillis
    val followingQuery = IntervalTimestampQuery(intervalQueryStr, timestampField, timezone, time)
    followingQuery #:: createFollowingQueries(query, followingQuery, keepPreviousTime)
  }
}

case object SybaseIntervalTimestampQueryCreator extends IntervalTimestampQueryCreator {
  val epochToTimestampStringFunc = (epoch: Long) => s"CAST('${DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss").print(epoch)}' AS DATETIME)"
}

case object DB2IntervalTimestampQueryCreator extends IntervalTimestampQueryCreator {
  val epochToTimestampStringFunc = (epoch: Long) => s"TIMESTAMP('${DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss").print(epoch)}')"
}

case object H2IntervalTimestampQueryCreator extends IntervalTimestampQueryCreator {
  val epochToTimestampStringFunc = (epoch: Long) => DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss").print(epoch)
}

case object PostgresIntervalTimestampQueryCreator extends IntervalTimestampQueryCreator {
  val epochToTimestampStringFunc = (epoch: Long) => s"TIMESTAMP '${DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss").print(epoch)}'"
}

/*
 * Creates a RetryIterator SQLSelectRowSource that will
 * keep retrying until there is data available.
 * If KeepPreviousTime is true than the time stamp filter value
 * will be always the same.
 */
object IntervalSQLSelectRetryRowSource {
  val logger = Logger(this.getClass)

  def apply(
    connection: DatabaseConnection with FetchSupport,
    queryStr: String,
    intervalQueryCreator: IntervalTimestampQueryCreator,
    timestampField: String,
    maxRetry: Int = 10,
    infiniteRetry: Boolean = false,
    timezone: String = "UTC",
    retryPeriod: Long = 1000,
    startTime: Option[Long] = None,
    keepPreviousTime: Boolean = false,
    resultSetType: Int = ResultSet.TYPE_FORWARD_ONLY,
    resultSetConcurrency: Int = ResultSet.CONCUR_READ_ONLY)(implicit ec: ExecutionContext) = {
    val firstQuery = intervalQueryCreator.createInitialQuery(queryStr, timestampField, timezone, startTime.getOrElse(System.currentTimeMillis), keepPreviousTime)
    val queries = Stream.cons(firstQuery, intervalQueryCreator.createFollowingQueries(queryStr, firstQuery, keepPreviousTime)).toIterator
    val config = FetchSupport.Config(resultSetType = resultSetType, resultSetConcurrency = resultSetConcurrency)

    val sqlSelectCreator: () => ResultSetRowSource = () => {
      val queryStr = queries.next.intervalQueryStr
      connection.fetch(queryStr, config = config)
    }
    MaximumRetryIterator(sqlSelectCreator, maxRetry, infiniteRetry, retryPeriod).asFuture
  }
}
/*
Copyright 2017 Morgan Stanley

Licensed under the GNU Lesser General Public License Version 3 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

https://www.gnu.org/licenses/lgpl-3.0.en.html

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

IN ADDITION, THE FOLLOWING DISCLAIMER APPLIES IN CONNECTION WITH THIS PROGRAM:

THIS SOFTWARE IS LICENSED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE AND ANY
WARRANTY OF NON-INFRINGEMENT, ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT
OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING
IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY
OF SUCH DAMAGE. THIS SOFTWARE MAY BE REDISTRIBUTED TO OTHERS ONLY BY EFFECTIVELY
USING THIS OR ANOTHER EQUIVALENT DISCLAIMER IN ADDITION TO ANY OTHER REQUIRED
LICENSE TERMS.
*/
