package com.ms.qaTools.saturn.lint.validators

import scala.Option.option2Iterable
import scala.annotation.tailrec
import scala.collection.JavaConversions._

import org.eclipse.emf.ecore.EObject

import com.ms.qaTools.saturn.{AbstractRunGroup => MAbstractRunGroup}
import com.ms.qaTools.saturn.{AttributeParameter => MAttributeParameter}
import com.ms.qaTools.saturn.{ProcedureCallStep => MProcedureCallStep}
import com.ms.qaTools.saturn.{ResourceParameter => MResourceParameter}
import com.ms.qaTools.saturn.lint.ResultError
import com.ms.qaTools.saturn.lint.ResultOK
import com.ms.qaTools.saturn.lint.SaturnLintRuleResult
import com.ms.qaTools.saturn.modules.procedureCallModule.{AttributeArgument => MAttributeArgument}
import com.ms.qaTools.saturn.modules.procedureCallModule.{ResourceArgument => MResourceArgument}
import com.ms.qaTools.saturn.resources.referenceResource.{ReferenceResource => MReferenceResource}
import com.ms.qaTools.saturn.types.{ResourceDefinition => MResourceDefinition}
import com.ms.qaTools.saturn.utils.SaturnEObjectUtils.SaturnEObjectHelper
import com.ms.qaTools.saturn.values.{TextComplexValue => MTextComplexValue}

object ProcedureCallValidator extends LintValidator {
  def validate(eObject: EObject): Seq[SaturnLintRuleResult] = 
    Seq(ProcedureCallNullConfigValidator,
        ProcedureCallReferentNameValidator,
        ProcedureCallReferentExistsValidator,
        ProcedureCallReferentParametersValidator,
        ProcedureOptionalParameterValidator) flatMap (_.validate(eObject))
}

object ProcedureCallNullConfigValidator extends LintValidator {

  val rule: String = "SAT-PROC-CALLS-001"

  def validate(eObject: EObject): Seq[SaturnLintRuleResult] = {
    val failures = for {
      procCall <- eObject.eAllContentsByType[MProcedureCallStep]().toSeq
      result   <- if (!Option(procCall.getProcedureCallConfiguration()).isDefined)
                    Seq(ResultError(rule, s"Procedure call configuration for '${procCall.getName}' is not defined.", Seq(procCall)))
                  else
                    Seq()
    } yield result
    if(failures.isEmpty)
      Seq(ResultOK(rule, "Procedure calls must have non null configurations."))
    else
      failures
  }

}

object ProcedureCallReferentNameValidator extends LintValidator {
  val rule: String = "SAT-PROC-CALLS-002"

  def validate(eObject: EObject): Seq[SaturnLintRuleResult] = {
    val procCalls = eObject.eAllContentsByType[MProcedureCallStep]().toSeq
    val failures = invalidProcNames(procCalls) ++ complexValueNames(procCalls)
    if(failures.isEmpty)
      Seq(ResultOK(rule, "Procedure calls must have valid text names.'^[A-Za-z0-9_]*\\.?[A-Za-z0-9_]+$'"))
    else
      failures
  }

  def invalidProcNames(procs: Seq[MProcedureCallStep]): Seq[SaturnLintRuleResult] =
    for {
      procCall  <- procs
      config    <- Option(procCall.getProcedureCallConfiguration())
      refNameCv <- Option(config.getRunGroup())
      name      <- if (refNameCv.getMixed().forall(_.getValue().isInstanceOf[MTextComplexValue]))
                     Option(refNameCv.getText().map(_.getText()).mkString)
                   else
                     None
      result    <- if (!name.matches("""^[A-Za-z0-9_]*\.?[A-Za-z0-9_]+$"""))
                     Option(ResultError(rule, s"Procedure call '${procCall.getName}' referent name does not match '^[A-Za-z0-9_]*\\.?[A-Za-z0-9_]+$$'.", Seq(procCall)))
                   else
                     None
    } yield result

  def complexValueNames(procs: Seq[MProcedureCallStep]): Seq[SaturnLintRuleResult] =
    for {
      procCall     <- procs
      config       <- Option(procCall.getProcedureCallConfiguration())
      referentName <- Option(config.getRunGroup())
      result       <- if (!referentName.getMixed().forall(_.getValue().isInstanceOf[MTextComplexValue]))
                        Option(ResultError(rule, s"Procedure call '${procCall.getName}' referent name is not text.", Seq(procCall)))
                      else
                        None
    } yield result
  
}

object ProcedureCallReferentExistsValidator extends LintValidator {

  val rule = "SAT-PROC-CALLS-003"

  def validate(eObject: EObject): Seq[SaturnLintRuleResult] = {
    val failures = for{
      procCall  <- eObject.eAllContentsByType[MProcedureCallStep]().toSeq
      config    <- Option(procCall.getProcedureCallConfiguration())
      refNameCv <- Option(config.getRunGroup())
      refName   <- if (refNameCv.getMixed().forall(_.getValue().isInstanceOf[MTextComplexValue]))
                     Option(refNameCv.getText().map(_.getText()).mkString)
                   else
                     None
      result    <- {
        val proc = eObject.eAllContentsByType[MAbstractRunGroup].find(p => p.isProcedure() && p.getName == refName)
        if (refName.matches("""[A-Za-z0-9_]+$"""))
          if(!proc.isDefined)
            Option(ResultError(rule,s"Procedure '${refName}' not found in proc call '${procCall.getName}'.",Seq(procCall)))
          else
            None
        //TODO check include files to see if procs exist
        else if(refName.matches("""^[A-Za-z0-9_]+\.[A-Za-z0-9_]+$"""))
          Option(ResultOK(rule,s"Procedure '${refName}' found in proc call '${procCall.getName}'."))
        else
          None
      }
    } yield result
    if (failures.isEmpty)
      Seq(ResultOK(rule, "Procedure call referents must exist."))
    else
      failures
  }

}

object ProcedureCallReferentParametersValidator extends LintValidator {

  val rule = "SAT-PROC-CALLS-004"

  def validate(eObject: EObject): Seq[SaturnLintRuleResult] = {
    val failures = for {
      procCall  <- eObject.eAllContentsByType[MProcedureCallStep]().toSeq
      config    <- Option(procCall.getProcedureCallConfiguration()).toSeq
      refNameCv <- Option(config.getRunGroup()).toSeq
      refName   <- if (refNameCv.getMixed().forall(_.getValue().isInstanceOf[MTextComplexValue]))
                     Option(refNameCv.getText().map(_.getText()).mkString).toSeq
                   else
                     None.toSeq
      //TODO check include files for the proc also
      proc      <- eObject.eAllContentsByType[MAbstractRunGroup].find(p => p.isProcedure && p.getName == refName).toSeq
      result    <- {
        val procArgs = proc.getParameters()
        val procCallArgs = config.getArguments()

        val missingParams = procArgs.filter(_.isIsMandatory()) map (a => (a.getName(), procCallArgs.exists(_.getName() == a.getName()))) filter (!_._2)
        val extraParams = procCallArgs map (a => (a.getName(), procArgs.exists(_.getName() == a.getName()))) filter (!_._2)
        val wrongTypes = procCallArgs filter (a => procArgs.exists(_.getName() == a.getName)) map { a =>
          (a.getName, procArgs find (_.getName() == a.getName()) exists { p =>
            p match {
              case aParam: MAttributeParameter => a.isInstanceOf[MAttributeArgument]
              case rParam: MResourceParameter  => a match {
                case rArg: MResourceArgument =>
                  val assignable = for {
                    p <- resourceClass(rParam.getDefaultResource)
                    a <- resourceClass(rArg.getResource)
                  } yield p.isAssignableFrom(a)
                  assignable.getOrElse(true)
                case _                       => false
              }
            }
          }
          )
        } filter (!_._2)
        (missingParams map (p => ResultError(rule, s"Procedure call '${procCall.getName}' does not have mandatory parameter '${p._1}'.", Seq(procCall)))) ++
        (extraParams map (p => ResultError(rule, s"Procedure call '${procCall.getName}' has extra parameter '${p._1}'.", Seq(procCall)))) ++
        (wrongTypes map (p => ResultError(rule, s"Procedure call '${procCall.getName}' parameter '${p._1}' is the wrong type.", Seq(procCall))))
      }
    } yield result
    if(failures.isEmpty)
      Seq(ResultOK(rule, "Procedures must have valid parameters."))
    else
      failures
//    Seq(ResultOK(rule, "Procedures must have valid parameters."))
  }

  def resourceClass(x: MResourceDefinition): Option[Class[_]] = x match {
    case null                    => None
    case ref: MReferenceResource => referencedType(ref)
    case _                       => Some(x.getClass)
  }

  protected def referencedType(ref: MReferenceResource): Option[Class[_]] =
    if (Option(ref.getIncludeFile).map(_.nonEmpty).getOrElse(false)) None
    else ref.containingRunGroup.flatMap(referencedType(ref.getResource, _))

  @tailrec protected def referencedType(name: String, rg: MAbstractRunGroup): Option[Class[_]] = {
    import scala.language.postfixOps
    val rDef = rg.getResources collectFirst {case r if r.getName == name => resourceClass(r)} flatten
    lazy val rParam = if (rg.isProcedure)
      rg.getParameters collectFirst {
        case r: MResourceParameter if r.getName == name => resourceClass(r.getDefaultResource)
      } flatten
    else
      None
    rDef orElse rParam match {
      case None => rg.containingRunGroup match {
        case Some(parent) => referencedType(name, parent)
        case None         => None
      }
      case t => t
    }
  }
}

object ProcedureOptionalParameterValidator extends LintValidator{

  val rule: String = "SAT-PROC-CALLS-005"

  def validate(eObject: EObject): Seq[SaturnLintRuleResult] = {
    val failures = for {
      proc      <- eObject.eAllContentsByType[MAbstractRunGroup].toSeq.filter(_.isProcedure())
      parameter <- proc.getParameters().filter(!_.isIsMandatory())
      result    <- {
        parameter match {
          case a: MAttributeParameter => if (!Option(a.getDefaultValue()).isDefined)
                                           Option(ResultError(rule, s"Procedure '${proc.getName}' optional parameter '${parameter.getName}' default value not specified.", Seq(proc)))
                                         else
                                           None
          case r: MResourceParameter  => if (!Option(r.getDefaultResource()).isDefined)
                                           Option(ResultError(rule, s"Procedure '${proc.getName}' optional parameter '${parameter.getName}' default value not specified.", Seq(proc)))
                                         else
                                           None
          case _ => throw new Exception(s"Parameter type ${parameter.getClass} is invalid.")
        }
      }
    } yield result
    if(failures.isEmpty)
      Seq(ResultOK(rule, "Procedures must have default values for optional parameters."))
    else
      failures
  }

}