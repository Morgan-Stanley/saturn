package com.ms.qaTools.saturn.codeGen

import java.io.File
import java.util.{List => JList}

import scala.annotation.tailrec
import scala.collection.JavaConversions._
import scala.language.implicitConversions
import scala.reflect.internal.util.BatchSourceFile
import scala.tools.nsc.Global
import scala.tools.nsc.Settings
import scala.tools.nsc.reporters.StoreReporter
import scala.util.{Failure, Success, Try}

import org.apache.commons.io.FileUtils
import org.apache.commons.io.FilenameUtils
import org.eclipse.emf.ecore.EObject

import com.ms.qaTools.CompilerClassLoader
import com.ms.qaTools.ScalaParseException
import com.ms.qaTools.TryUtil
import com.ms.qaTools.ecore.utils.ECoreStringUtils
import com.ms.qaTools.io.DirIO
import com.ms.qaTools.io.FileIO
import com.ms.qaTools.saturn.{AbstractLink => MAbstractLink}
import com.ms.qaTools.saturn.{AbstractRunGroup => MAbstractRunGroup}
import com.ms.qaTools.saturn.{AbstractStep => MStep}
import com.ms.qaTools.saturn.DocumentRoot
import com.ms.qaTools.saturn.{IncludeFile => MIncludeFile}
import com.ms.qaTools.saturn.ResourceParameter
import com.ms.qaTools.saturn.{RunGroup => MRunGroup}
import com.ms.qaTools.saturn.{Saturn => MSaturn}
import com.ms.qaTools.saturn.annotations.saturnVerbosity.SaturnVerbosityConfiguration
import com.ms.qaTools.saturn.annotations.saturnWeb.{SaturnWebConfiguration => MSaturnWebConfiguration}
import com.ms.qaTools.saturn.modules.procedureCallModule.ResourceArgument
import com.ms.qaTools.saturn.modules.xmlGenModule.XmlGenConfiguration
import com.ms.qaTools.saturn.runtime.SaturnResultContext
import com.ms.qaTools.saturn.types.{AbstractAnnotation => MAbstractAnnotation}
import com.ms.qaTools.saturn.types.AbstractRepetitionHandler
import com.ms.qaTools.saturn.types.NamedResourceDefinition
import com.ms.qaTools.saturn.types.ResourceDefinition
import com.ms.qaTools.saturn.utils.SaturnEObjectUtils.SaturnEObjectHelper

object SaturnCodeGenUtil {
  import com.ms.qaTools.saturn.dsl.{SaturnDeserializer => P}

  def createFromFileName(file: String): Try[SaturnCodeGenUtil] = for {
    s <- Try(P.deserialize(file.toUri)).rethrow(s"An error occurred while parsing saturn file: '$file'.")
    f = new java.io.File(file).getAbsolutePath
    m <- P.genIncludeFileMap(P.extractAllIncludeFiles(s), P.extractPath(f))
  } yield SaturnCodeGenUtil(s, m, saturnFileName = Option(f))

  def setUpClassPath(s: Settings, cl: ClassLoader = Thread.currentThread.getContextClassLoader): Unit = cl match {
    case cl: org.apache.tools.ant.AntClassLoader => s.bootclasspath.append(cl.getClasspath)
    case _                                       =>
      s.usejavacp.value = true
      s.embeddedDefaults(new CompilerClassLoader(cl))
  }
}

case class SaturnCodeGenUtil(saturn:MSaturn,
                             includeFileMap:Map[MIncludeFile, (MSaturn, String)] = Map.empty,
                             isImported: Boolean = false,
                             saturnFileName: Option[String] = None,
                             saturnAsJarResource: Boolean = true) {
  val includeFiles:Set[MSaturn] = includeFileMap.values.map(_._1).toSet
  val saturnEObjects = includeFiles + saturn
  lazy val allLinks:Seq[MAbstractLink] = saturnEObjects.flatMap{_.getLinks}.toSeq

  lazy val wrappedRunGroups: Map[MAbstractRunGroup, WrappedAbstractRunGroup] = saturnEObjects.iterator.flatMap { saturn =>
    val canRunExpressionGenerator = CanRunExpressionGenerator(saturn)
    (Iterator.single(saturn) ++ saturn.eAllContents).collect {
      case group: MRunGroup => (group, WrappedRunGroup(group, canRunExpressionGenerator.getCanRunExpression(group), allLinks, includeFileMap))
      case step: MStep      => (step, WrappedStep(step, canRunExpressionGenerator.getCanRunExpression(step), allLinks, includeFileMap))
    }
  }.toMap

  def getIncludeFileFullFileName(includeFile: MSaturn) = {
    includeFileMap.find{case (i,s) => s._1 == includeFile} match {
      case Some(x) => x._2._2
      case None => throw new Exception("Include file not found: " + includeFile)
    }
  }

  def copySaturnFiles(outDir: DirIO) = {
    saturnFileName.foreach { f =>
      FileUtils.copyFile(new File(f), new File((outDir + FileIO(FilenameUtils.getName(f))).fileName))
      val diag = new File(FilenameUtils.removeExtension(f) + ".saturnDiagram")
      if (diag.exists) FileUtils.copyFile(diag, new File((outDir + FileIO(diag.getName)).fileName))
    }
    includeFileMap.foreach{case (_, (saturn, fileName)) =>
      FileUtils.copyFile(new File(fileName), new File((outDir + FileIO(FilenameUtils.getName(fileName))).fileName))
    }
  }

  def getIncludeFiles:Set[MSaturn] = includeFiles
  def getIncludeFileSaturn(includeFile:MIncludeFile):MSaturn = includeFileMap(includeFile)._1
//  def getLinks(g:MAbstractRunGroup) = allLinks.filter{_.getTarget() == g}

  def getIncludeFileCodeGenUtil(includeFile:MIncludeFile):SaturnCodeGenUtil = {
    val (saturn, path) = includeFileMap(includeFile)
    SaturnCodeGenUtil(saturn, includeFileMap, true, saturnFileName = Some(path))
  }

  def getIncludeFileCodeGenUtil(includeFileSaturn:MSaturn):SaturnCodeGenUtil = {
    val path = includeFileMap.collectFirst {
      case (_, (saturn, path)) if saturn == includeFileSaturn => path
    }.getOrElse {
      throw new NoSuchElementException(s"Cannot find $includeFileSaturn in include files")
    }
    SaturnCodeGenUtil(includeFileSaturn, includeFileMap, true, saturnFileName = Some(path))
  }

  implicit def getWrapped(runGroup:MAbstractRunGroup):WrappedAbstractRunGroup= wrappedRunGroups(runGroup)

  def getRunGroupByName(fullName:String):MAbstractRunGroup = {
    val fullNameParts = fullName.split("\\.")
    fullNameParts.foldLeft[MAbstractRunGroup](saturn){(o,n) =>
        o match {
          case rg:MRunGroup => {
            val runGroups:Seq[MAbstractRunGroup] = rg.getRunGroups()
            val runGroup:MAbstractRunGroup = runGroups.find(_.getName() == n) match {
              case Some(f) => f
              case _       => throw new Exception("Could not find runGroup: '" + fullName + "'")
            }
            runGroup
          }
          case _           => throw new Exception("Could not find runGroup: '" + fullName + "'")
        }
      }
  }

  def getLinkSatisfied(l:MAbstractLink) = l.getSource().getName() + "2" + l.getTarget().getName() + "Satisfied"
  def getLinkName(l:MAbstractLink) = l.getSource().getName()
  def getLinkResultName(l:MAbstractLink) = getLinkName(l) + "Result"

  def getAnnotations():JList[MAbstractAnnotation] = (for(annotation @(a:MAbstractAnnotation) <- (saturn :: saturn.eAllContents.toList)) yield annotation).toList
  def getWebAnnotations():JList[MSaturnWebConfiguration] = (for(annotation @(a:MSaturnWebConfiguration) <- (saturn :: saturn.eAllContents.toList)) yield annotation).toList
  def getRunGroupsWithJUnitAnnotations(saturn: MSaturn):Seq[MAbstractRunGroup] = (Iterator(saturn) ++ saturn.eAllContents()).collect{case rg: MAbstractRunGroup => rg}.filter{rg => !rg.getJUnitAnnotations.isEmpty}.toSeq

  def sortRunGroups(runGroups:Seq[MAbstractRunGroup]):Try[List[MAbstractRunGroup]] = Try {
    val dependencies = allLinks.filter{link => runGroups.contains(link.getSource()) && runGroups.contains(link.getTarget())}.map{link => (link.getSource, link.getTarget)}.toSet

    @tailrec
    def _sortRunGroups(rr:List[MAbstractRunGroup], dd:Set[(MAbstractRunGroup, MAbstractRunGroup)], soFar:List[MAbstractRunGroup]=Nil):List[MAbstractRunGroup] = {
      if(rr.isEmpty) soFar
      else {
        val(in,out) = rr.partition{r => !dd.exists{d => d._1 == r} }
        if(in.isEmpty) throw new Exception("RunGroups contain circular dependency(ies).")
        _sortRunGroups(out, dd.filter{d => !in.contains(d._2)}, in:::soFar)
      }
    }
    _sortRunGroups(runGroups.toList, dependencies)
  }

  def getXmlGenInputResource(cfg:XmlGenConfiguration):ResourceDefinition = {
    val dataSets = cfg.getDataSets()
    if(!dataSets.isEmpty()) dataSets.get(0).getFile()
    else null
  }

  def getParentResultContextName(eObject: EObject): String = eObject.eContainer() match {
    case null => throw new Exception("Could not get parent context for:" + eObject)
    case r:NamedResourceDefinition => r.getResultContextName
    case r:MAbstractRunGroup        => {
      val wrapped = getWrapped(r)
      val preIterationObjects = wrapped.getPreIterationObjects
      //To review for included files
      //all pre-iteration object (attributes + resources) that are not include files
      /*
       * PRE? RH? INCL? OR/ON
       * T    T   T     OR
       * T    T   F     OR
       * T    F   T     OR
       * T    F   F     ON **
       * F    T   T     OR
       * F    T   F     ON **
       * F    F   T     OR
       * F    F   F     ON **
       */
      if(includeFiles.toSet[MAbstractRunGroup](r)) wrapped.getClassName
      else if(preIterationObjects.contains(eObject) &&  r.getRepetitionHandler() != null) wrapped.getClassName
      else wrapped.getIterationClassName
    }
    case rph: AbstractRepetitionHandler => {
      rph.eContainer() match {
        case r: MAbstractRunGroup => getWrapped(r).getClassName
        case _ => throw new Exception("Parent of a repetition handler is not an abstractRunGroup??")
      }
    }
    case resParm: ResourceParameter => {
      //Not ideal, context name should be the one from the caller
      getParentResultContextName(resParm.eContainer)
    }
    case resParm: ResourceArgument => {
      //Not ideal, context name should be the one from the caller
      getParentResultContextName(resParm.eContainer)
    }
    case documentRoot: DocumentRoot => {
      // for resources defined in a forEach/forEachXPath root level
      getWrapped(documentRoot.getSaturn).getClassName
    }

    case e: EObject => e.eContainer match {
      case s: MSaturn => getParentResultContextName(e)
      // correct rc name for a resource defined inlined e.g. config file for xml2csv
      case r: NamedResourceDefinition => getParentResultContextName(r)
      case r         => getParentResultContextName(e)
    }
  }

  def getHexHashCode(o:Any) = Integer.toHexString(o.hashCode)
  def generateResourceAlias(rg: MAbstractRunGroup): String = generateResourceAlias(wrappedRunGroups(rg))
  val resourceRoot = "resources/saturn"
  def resource = FilenameUtils.getBaseName(saturnFileName.getOrElse(sys.error("undefined saturnFileName in SaturnCodeGenUtil")))
  def generateResourceAlias(rg: WrappedAbstractRunGroup) = s"/$resourceRoot/${resource}.saturn"
  def diagramResource(saturn: MSaturn) = s"/$resourceRoot/${resource}.saturnDiagram"
  def getConsoleOutputAnnotation(rg: MAbstractRunGroup): String = {
    val verbosity = for {
      a <- rg.getAnnotations()
      if a.eClass.getName == "SaturnVerbosityConfiguration"
    } yield {
      val b = a.asInstanceOf[SaturnVerbosityConfiguration]
      b.getVerbosity.getName
    }
    if(verbosity.isEmpty) "" else verbosity.head
  }

  def getDefaultIterationNo(runGroup: MAbstractRunGroup) = if(runGroup.getRepetitionHandler == null) "0" else "iterationNo"

  protected[codeGen] lazy val compiler: Global = {
    val settings = new Settings
    SaturnCodeGenUtil.setUpClassPath(settings)
    Global(settings, new StoreReporter)
  }

  def parseScala(code: String): (compiler.Tree, compiler.analyzer.Context) = compiler.synchronized {
    import compiler._
    val wrappedCode = s"""${SaturnImports() match {case Success(v) => v case Failure(e) => throw new Exception("Failed to generate imports", e)}}\nobject wrapper {\n$code\n}"""
    val unit = new CompilationUnit(new BatchSourceFile("<SaturnCodeGenUtil-parseScala>", wrappedCode))
    val run = new Run
    phase = run.parserPhase
    val parsedTree = new syntaxAnalyzer.UnitParser(unit).parse()
    phase = run.namerPhase
    val context = analyzer.newNamer(analyzer.rootContext(unit)).enterSym(parsedTree)
    phase = run.typerPhase
    analyzer.resetTyper()
    undoLog.clear()
    val typedTree = analyzer.newTyper(context).typed(parsedTree)
    if (reporter.hasErrors) {
      val e = ScalaParseException.fromReporter(reporter, code)
      reporter.reset()
      throw e
    }
    val PackageDef(_, imports :+ ModuleDef(_, _, Template(_, _, _ :: parsed))) = typedTree
    val tree = parsed match {
      case expr :: Nil   => expr
      case stats :+ expr => Block(stats, expr)
    }
    //(tree, (context /: imports)((c, i) => c.makeNewImport(i.asInstanceOf[Import])))
    (tree, (context /: imports)((c, i) => c.make(i.asInstanceOf[Import])))
  }
}

class SaturnResultContextWrapper(val rc: SaturnResultContext)
object SaturnResultContextWrapper {
  def apply(rc: SaturnResultContext) = new SaturnResultContextWrapper(rc)
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
