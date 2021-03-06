package com.ms.qaTools.io.rowWriter
import com.ms.qaTools.compare.CompareColDef
import com.ms.qaTools.io.ColDefAwareWriter
import com.ms.qaTools.io.rowSource.ColumnDefinition
import com.ms.qaTools.io.rowSource.ColumnDefinitions
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.OutputStream
import org.apache.poi.hssf.usermodel.HSSFWorkbook
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.xssf.streaming.SXSSFWorkbook
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import scala.Option.option2Iterable
import scala.collection.JavaConversions.iterableAsScalaIterable

class ExcelRowWriter(wb: Workbook, ws: Sheet, output: OutputStream, append: Boolean,
                     writeColNames: Boolean, nullMarker: String)
    extends SimpleExcelRowWriter(ws, append, writeColNames, nullMarker) {
  override def close = {
    wb.write(output)
    if (wb.isInstanceOf[SXSSFWorkbook]) wb.asInstanceOf[SXSSFWorkbook].dispose
    output.close
  }
}

class SimpleExcelRowWriter(ws: Sheet, append: Boolean, writeColNames: Boolean, nullMarker: String)
    extends ColDefAwareWriter[Iterator[Seq[String]]] { // TODO refactor as Iterator[Seq[Cell]]
  if(!append)
    for(row <- ws) ws.removeRow(row)

  private def createNewRow = {
    if (ws.getRow(currRowNum) != null) currRowNum += 1
    ws.createRow(currRowNum)
  }

  private def writeRow(values: Seq[String]) = {
    val row = createNewRow
    values.zipWithIndex foreach {
      case (s, index) => row.createCell(index).setCellValue(Option(s).orElse(Option(nullMarker)).orNull)
    }
  }

  def write(source: Iterator[Seq[String]], colDefs: Option[Seq[ColumnDefinition]]) = {
    (colDefs, source, writeColNames, currRowNum) match {
      case (Some(colDefs), _, true, 0) =>
        writeRow(colDefs.map(_.name))
      case (None, c: ColumnDefinitions, true, 0) =>
        writeRow(c.colDefs.map(_.name))
      case _ => ()
    }

    source.foldLeft(0)((count, values) => {writeRow(values); count + 1})
  }

  def close() = ()

  protected var currRowNum = ws.getWorkbook match {
    case wb: SXSSFWorkbook => Option(wb.getXSSFWorkbook) map { xssfWb =>
      val s = xssfWb.getSheetAt(wb.getSheetIndex(ws))
      val n = s.getLastRowNum
      if (s.getRow(n) == null) n else n + 1
    } getOrElse 0
    case _ => ws.getLastRowNum
  }
}

object ExcelRowWriter {
  def apply(ws: Sheet): SimpleExcelRowWriter = apply(ws, writeColNames = true, nullMarker = null)
  def apply(ws: Sheet, writeColNames: Boolean, nullMarker: String) =
    new SimpleExcelRowWriter(ws, false, writeColNames, nullMarker)

  /*
   * Worbook types
   * SXSSF (XLSX): Can only create new sheet and write to it, Forward only, Cannot read sheet content
   * XSSF (XLSX): Can read/write sheet, can append to sheet (got to last row)
   * HSSF (XLS):
   * Overwrite: The file will be overwritten (all sheets will be lost)
   * Append: Append new rows to the sheet
   */
  def apply(fileName: String,
            wsName: String = "Sheet1",
            isXlsx: Boolean = true,
            overwrite: Boolean = false,
            append: Boolean = false,
            writeColNames: Boolean = true,
            nullMarker: String = null): ExcelRowWriter = {
    val outFileExists = new File(fileName).exists
    val wb = if (isXlsx) {
      if(overwrite) new SXSSFWorkbook()
      else if (append || outFileExists) new SXSSFWorkbook(new XSSFWorkbook(new FileInputStream(fileName)))
      else new SXSSFWorkbook()
    }
    else if (overwrite || !outFileExists) new HSSFWorkbook
    else new HSSFWorkbook(new FileInputStream(fileName))

    val ws = Option(wb.getSheet(wsName)).getOrElse(wb.createSheet(wsName))
    new ExcelRowWriter(wb, ws, new FileOutputStream(fileName), append, writeColNames, nullMarker)
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
