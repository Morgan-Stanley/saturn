package com.ms.qaTools.conversions
import com.ms.qaTools.tree.TreeNode
import com.ms.qaTools.tree.XmlNode
import com.ms.qaTools.xml._
import com.ms.qaTools.xml.NamespaceContextImpl
import javax.xml.namespace.NamespaceContext
import org.w3c.dom.Document
import org.w3c.dom.Node
import scala.language.implicitConversions

object XmlToTreeNodeConversions {
  implicit def xmlDocsToTreeNodes(docs: Iterator[Document])(implicit nsContext: NamespaceContext): Iterator[Option[XmlNode]] =
    docs.map { d =>
      val nodeNsContext = nsContext match {
        case n: NamespaceContextImpl => NamespaceContextImpl(d) + n
        case _ => NamespaceContextImpl(d)
      }
      Option(XmlNode(d.getDocumentElement)(nodeNsContext))
    }

  implicit def xmlNodeToNode(treeNode: XmlNode): Node = treeNode.node

  implicit def xmlNodeToDocument(xmlNode: TreeNode[Node]): Document = {
    val doc = DocumentBuilderTL.get().newDocument()
    doc.adoptNode(xmlNode.node)
    doc.appendChild(xmlNode.node)
    doc
  }

  implicit def nodeToDocument(node: Node): Document =
    if (node.getNodeType() == Node.DOCUMENT_NODE)
      node.asInstanceOf[Document]
    else {
      val doc = DocumentBuilderTL.get().newDocument()
      doc.adoptNode(node)
      doc.appendChild(node)
      doc
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
