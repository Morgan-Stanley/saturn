package com.ms.qaTools.tree

import org.w3c.dom.Attr
import org.w3c.dom.Node
import org.w3c.dom.Text
import com.ms.qaTools.xml.getLocalName
import com.ms.qaTools.xml.nodeList2List
import com.ms.qaTools.xml.nodeMap2List
import com.ms.qaTools.xml.nodePath
import javax.xml.XMLConstants
import javax.xml.namespace.NamespaceContext
import org.w3c.dom.Comment
import scala.annotation.tailrec



class XmlNode(val node: Node)(implicit val nsContext: NamespaceContext) extends TreeNode[Node] {
  val namespaceURI = node.getNamespaceURI()
  val prefix = Option(namespaceURI).map(nsContext.getPrefix(_))
  val name: String = prefix.map(p => s"$p:").getOrElse("") + getLocalName(node)
  lazy val path: String = nodePath(node)
  private def notNs0(n: Node): Boolean = getLocalName(n) == XMLConstants.XMLNS_ATTRIBUTE || (Option(n.getNamespaceURI()).exists(_ == XMLConstants.XMLNS_ATTRIBUTE_NS_URI))
  lazy private val attributes: Iterable[Attr] = node.getAttributes().filterNot { a => notNs0(a) }.asInstanceOf[Iterable[Attr]]

  private lazy val (childrenTextNodes, childrenNonTextNodes) = node.getChildNodes().partition(_.isInstanceOf[Text])
  def children(filterOut: (Node) => Boolean = (n: Node) => n.isInstanceOf[Comment]) =
    attributes.toSeq.filterNot{filterOut}.map(XmlAttr(_)) ++ childrenNonTextNodes.filterNot{filterOut}.map(XmlNode(_))

  lazy val value = {
    val textValue = childrenTextNodes.map { _.getTextContent() }.mkString.trim
    if (textValue.isEmpty()) None else Some(textValue)
  }
  override def toString: String = path
  override def detach: TreeNode[Node] = {
    val pn = node.getParentNode()    
    if (pn != null) pn.synchronized {XmlNode(pn.removeChild(node))} else this
  }  
}

class XmlAttr(override val node: Attr)(implicit nsContext: NamespaceContext) extends XmlNode(node) {
  override lazy val value = {
    val textValue = node.getValue()
    if (textValue.isEmpty()) None else Some(textValue)
  }
}

class XmlText(override val node: Text)(implicit nsContext: NamespaceContext) extends XmlNode(node) {
  override lazy val value = {
    val textValue = node.getWholeText()
    if (textValue.isEmpty()) None else Some(textValue)
  }
}

object XmlNode {
  def apply(node: Node)(implicit nsContext: NamespaceContext): XmlNode = node match {
    case a: Attr => new XmlAttr(a)
    case t: Text => new XmlText(t)
    case n: Node => new XmlNode(n)    
  }
}

object XmlAttr {
  def apply(node: Attr)(implicit nsContext: NamespaceContext): XmlAttr = new XmlAttr(node)
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