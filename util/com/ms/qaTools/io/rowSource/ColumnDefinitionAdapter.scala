package com.ms.qaTools.io.rowSource

trait ColumnDefinitionAdapter {
  def colDefs: Seq[ColumnDefinition]
  def extractColDefs[X <% Seq[String]](rowIterator: Iterator[X]): Seq[ColumnDefinition]
}

trait AdapterColumnDefinitions extends ColumnDefinitions {
  def columnDefinitionAdapter: ColumnDefinitionAdapter
  def colDefs: Seq[ColumnDefinition] = columnDefinitionAdapter.colDefs

  def extractColDefs[X <% Seq[String]](rowIterator: Iterator[X]): Seq[ColumnDefinition] = {
    columnDefinitionAdapter.extractColDefs(rowIterator)
    return colDefs
  }
}

object ColumnDefinitionAdapter {
  def apply(firstRow: Int, separatorChar: Char, multiPartColNameSep: String, colNameRows: Int, transformColDefs: (Seq[ColumnDefinition]) => Seq[ColumnDefinition] = identity[Seq[ColumnDefinition]]) =
    new StreamingColumnDefinitionAdapter(colNameRows = colNameRows, skipRows = firstRow, multiPartColNameSep = multiPartColNameSep, transformColDefs = transformColDefs)
  def apply(colNames: Seq[String], skipRows: Int) =
    new SimpleColumnDefinitionAdapter(colNames.map {ColumnDefinition(_)}, skipRows)
  def apply(colCount: Int, skipRows: Int) =
    new SimpleColumnDefinitionAdapter((0 until colCount).map {i => ColumnDefinition(name = i.toString, index = i)}, skipRows)
}
/*
COPYRIGHT NOTICE AND DISCLAIMER
Copyright (c) 2009-2016, Contributor

This program is free software; you can redistribute it and/or modify it under the terms of the GNU Lesser General Public License, version 3, as published by the Free Software Foundation.

This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License, version 3 for more details.

You should have received a copy of the GNU Lesser General Public License, version 3, along with this program; if not, see http://www.gnu.org/licenses/ or write to the Free Software Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
THE FOLLOWING DISCLAIMER APPLIES TO ALL SOFTWARE CODE AND OTHER MATERIALS CONTRIBUTED IN CONNECTION WITH THIS PROGRAM:
THIS SOFTWARE IS LICENSED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE AND ANY WARRANTY OF NON-INFRINGEMENT, ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE. THIS SOFTWARE MAY BE REDISTRIBUTED TO OTHERS ONLY BY EFFECTIVELY USING THIS OR ANOTHER EQUIVALENT DISCLAIMER AS WELL AS ANY OTHER LICENSE TERMS THAT MAY APPLY.
*/