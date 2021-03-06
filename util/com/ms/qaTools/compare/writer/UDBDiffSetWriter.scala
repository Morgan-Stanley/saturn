package com.ms.qaTools.compare.writer
import com.ms.qaTools.compare.CompareColDefs
import com.ms.qaTools.io.rowSource.DatabaseConnection
import com.ms.qaTools.io.rowSource.jdbc.ExecuteSupport
import com.ms.qaTools.io.rowSource.jdbc.FetchSupport
import com.ms.qaTools.io.rowSource.jdbc.ResultSetRowSource
import com.ms.qaTools.io.rowSource.Utils._

class UDBDiffSetWriter(dbConnection: DatabaseConnection with ExecuteSupport with FetchSupport, colDefs: CompareColDefs, tblPrefix: String, dbQualifier: String)
extends DataBaseDiffSetWriter(dbConnection, colDefs, tblPrefix) {
  setupDataBaseEnvironment

  private def createTables() = {
    create("table", "LEFTTABLE (rowId integer not null primary key, " + sortedLeftColumns.map{_._1.name + " varchar(256) "}.toList.mkString(",") + ")")
    create("table", "RIGHTTABLE (rowId integer not null primary key, " + sortedRightColumns.map{_._1.name + " varchar(256) "}.toList.mkString(",") + ")")
    create("table", "DIFFS (diffId integer not null primary key, leftId integer, rightId integer, statusId integer, reason varchar(256))")
    create("index", "diffs_leftId on " + tblPrefix + "diffs (leftId)")
    create("index", "diffs_rightId on " + tblPrefix + "diffs (rightId)")
    create("table", "CELLDIFFS (cellDiffId integer not null primary key, diffId integer, leftIdx integer, rightIdx integer, statusId integer, reason varchar(256))")
    create("index", "cellDiffs_diffId on " + tblPrefix + "cellDiffs (diffId)")
    create("table", "XPATHDIFFS (xPathDiffId integer not null primary key, diffId integer, leftXPath varchar(256), rightXPath varchar(256), aliasXPath varchar(256), statusId integer)")
    create("index", "xPathDiffs_diffId on " + tblPrefix + "xPathDiffs (diffId)")
    create("table", "STATUS (statusId integer not null primary key, status varchar(256))")
  }

  private def create(typ: String, statement: String) =
    dbConnection.execute(s"create $typ " + dbQualifier + "." + tblPrefix + statement)

  private def cleanTables() = {
    dbConnection.fetch("SELECT TABNAME from SYSCAT.TABLES WHERE TABNAME in( 'DIFFS', 'CELLDIFFS', 'XPATHDIFFS', 'STATUS', 'LEFTTABLE', 'RIGHTTABLE') and TABSCHEMA = '" + dbQualifier + "'").foreach(rs =>
      dbConnection.execute("DROP TABLE " + dbQualifier + "." + rs.toSeqString.mkString))
    dbConnection.fetch("SELECT VIEWNAME from SYSCAT.VIEWS WHERE VIEWNAME in( 'summary', 'different', 'inLeftOnly', 'inRightOnly', 'explained', 'identical') and VIEWSCHEMA = '" + dbQualifier + "'").foreach(rs =>
      dbConnection.execute("DROP VIEW " + dbQualifier + "." + rs.toSeqString.mkString))
  }

  override def createViews() = {
    // Create view summary
    dbConnection.execute("CREATE VIEW " + dbQualifier + "." + tblPrefix + "summary AS SELECT " +
      "(SELECT COUNT(0) AS leftDsNumRows FROM " + dbQualifier + "." + tblPrefix + "leftTable)," +
      "(SELECT COUNT(0) AS rightDsNumRows FROM " + dbQualifier + "." + tblPrefix + "rightTable)," +
      "(SELECT COUNT(statusId) AS different FROM " + dbQualifier + "." + tblPrefix + "diffs WHERE statusId = 1)," +
      "(SELECT COUNT(statusId) AS inLeftOnly FROM " + dbQualifier + "." + tblPrefix + "diffs WHERE statusId = 2)," +
      "(SELECT COUNT(statusId) AS inRightOnly FROM " + dbQualifier + "." + tblPrefix + "diffs WHERE statusId = 3)," +
      "(SELECT COUNT(statusId) AS explained FROM " + dbQualifier + "." + tblPrefix + "diffs WHERE statusId = 4)," +
      "(SELECT COUNT(statusId) AS identical FROM " + dbQualifier + "." + tblPrefix + "diffs WHERE statusId = 0)," +
      "(SELECT COUNT(statusId) AS validatePass FROM " + dbQualifier + "." + tblPrefix + "diffs WHERE statusId = 5)," +
      "(SELECT COUNT(statusId) AS validateFail FROM " + dbQualifier + "." + tblPrefix + "diffs WHERE statusId = 6)" +
      " from sysibm.sysdummy1")
    // Create view inLeftOnly
    dbConnection.execute("CREATE VIEW " + dbQualifier + "." + tblPrefix + "inLeftOnly AS SELECT " + dbQualifier + "." +
      tblPrefix + "leftTable.* " + "from " + dbQualifier + "." + tblPrefix + "diffs AS diffs JOIN " + dbQualifier + "." +
      tblPrefix + "leftTable ON " + dbQualifier + "." + tblPrefix + "leftTable.rowId = diffs.leftId " + "AND diffs.statusId = 2")
    // Create view inRightOnly
    dbConnection.execute("CREATE VIEW " + dbQualifier + "." + tblPrefix + "inRightOnly AS SELECT " + dbQualifier + "." +
      tblPrefix + "leftTable.* " + "from " + dbQualifier + "." + tblPrefix + "diffs AS diffs JOIN " + dbQualifier + "." +
      tblPrefix + "leftTable ON " + dbQualifier + "." + tblPrefix + "leftTable.rowId = diffs.leftId " + "AND diffs.statusId = 3")
    // Create view identical
    val genericColumns = (sortedLeftColumns.map(_._1.name).zipWithIndex.map(C => {
      val (column, index) = C
      val columnCreation = dbQualifier + "." + tblPrefix + "leftTable." + column + " AS " + column + "_leftVal," +
         dbQualifier + "." + tblPrefix + "rightTable." + column + " AS " + column + "_rightVal," +
        "CASE WHEN (" + dbQualifier + "." + tblPrefix + "leftTable." + column + " IS NULL) THEN 3 WHEN (" + dbQualifier + "." + tblPrefix + "rightTable." + column + " IS NULL) THEN 2" +
        " ELSE COALESCE((SELECT statusId FROM " + dbQualifier + "." + tblPrefix + "cellDiffs WHERE diffId = diffs.diffId and leftIdx = " + (index + 1) + "), 0) END AS " + column + "_diffStatus"
      columnCreation
    })).toList.mkString(",")
    dbConnection.execute("CREATE VIEW " + dbQualifier + "." + tblPrefix + "identical AS SELECT " + dbQualifier + "." + tblPrefix + "leftTable.rowId AS leftRowId,"
      + dbQualifier + "." + tblPrefix + "rightTable.rowId AS rightRowId,diffs.statusId AS statusId," +
      genericColumns +
      " FROM " + dbQualifier + "." + tblPrefix + "diffs AS diffs " +
      "JOIN " + dbQualifier + "." + tblPrefix + "leftTable ON " + dbQualifier + "." + tblPrefix + "leftTable.rowId = diffs.leftId " +
      " JOIN " + tblPrefix + "rightTable ON " + dbQualifier + "." + tblPrefix + "rightTable.rowId = diffs.rightId AND diffs.statusId = 0")
    // Create view different
    dbConnection.execute("CREATE VIEW " + dbQualifier + "." + tblPrefix + "different AS SELECT " + dbQualifier + "." + tblPrefix + "leftTable.rowId AS leftRowId,"
      + dbQualifier + "." + tblPrefix + "rightTable.rowId AS rightRowId,diffs.statusId AS statusId," +
      genericColumns +
      " FROM " + dbQualifier + "." + tblPrefix + "diffs AS diffs " +
      "JOIN " + dbQualifier + "." + tblPrefix + "leftTable ON " + dbQualifier + "." + tblPrefix + "leftTable.rowId = diffs.leftId " +
      " JOIN " + tblPrefix + "rightTable ON " + dbQualifier + "." + tblPrefix + "rightTable.rowId = diffs.rightId AND diffs.statusId = 1")
    // Create view explained
    dbConnection.execute("CREATE VIEW " + dbQualifier + "." + tblPrefix + "explained AS SELECT " + dbQualifier + "." + tblPrefix + "leftTable.rowId AS leftRowId,"
      + dbQualifier + "." + tblPrefix + "rightTable.rowId AS rightRowId,diffs.statusId AS statusId," +
      genericColumns +
      " FROM " + dbQualifier + "." + tblPrefix + "diffs AS diffs " +
      "JOIN " + dbQualifier + "." + tblPrefix + "leftTable ON " + dbQualifier + "." + tblPrefix + "leftTable.rowId = diffs.leftId " +
      " JOIN " + tblPrefix + "rightTable ON " + dbQualifier + "." + tblPrefix + "rightTable.rowId = diffs.rightId AND diffs.statusId = 4")
  }

  def setupDataBaseEnvironment() = {cleanTables; createTables; createValidStatus}
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
