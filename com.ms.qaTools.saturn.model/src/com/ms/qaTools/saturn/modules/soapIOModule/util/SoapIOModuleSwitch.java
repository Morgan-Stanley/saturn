package com.ms.qaTools.saturn.modules.soapIOModule.util;

import com.ms.qaTools.saturn.modules.soapIOModule.*;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.Switch;

import com.ms.qaTools.saturn.modules.soapIOModule.AbstractSoapIOOperation;
import com.ms.qaTools.saturn.modules.soapIOModule.AbstractTerminationCondition;
import com.ms.qaTools.saturn.modules.soapIOModule.DocumentRoot;
import com.ms.qaTools.saturn.modules.soapIOModule.PerlTerminationCondition;
import com.ms.qaTools.saturn.modules.soapIOModule.SoapIOConfiguration;
import com.ms.qaTools.saturn.modules.soapIOModule.SoapIOGetOperation;
import com.ms.qaTools.saturn.modules.soapIOModule.SoapIOModulePackage;
import com.ms.qaTools.saturn.modules.soapIOModule.SoapIOPutAndGetOperation;
import com.ms.qaTools.saturn.modules.soapIOModule.SoapIOPutOperation;

/**
 * <!-- begin-user-doc --> The <b>Switch</b> for the model's inheritance hierarchy. It supports the call
 * {@link #doSwitch(EObject) doSwitch(object)} to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object and proceeding up the inheritance hierarchy until a non-null result is
 * returned, which is the result of the switch. <!-- end-user-doc -->
 * @see com.ms.qaTools.saturn.modules.soapIOModule.SoapIOModulePackage
 * @generated
 */
public class SoapIOModuleSwitch<T> extends Switch<T>
{
  /**
   * The cached model package
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  protected static SoapIOModulePackage modelPackage;

  /**
   * Creates an instance of the switch.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  public SoapIOModuleSwitch()
  {
    if (modelPackage == null)
    {
      modelPackage = SoapIOModulePackage.eINSTANCE;
    }
  }

  /**
   * Checks whether this is a switch for the given package.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @parameter ePackage the package in question.
   * @return whether this is a switch for the given package.
   * @generated
   */
  @Override
  protected boolean isSwitchFor(EPackage ePackage)
  {
    return ePackage == modelPackage;
  }

  /**
   * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @return the first non-null result returned by a <code>caseXXX</code> call.
   * @generated
   */
  @Override
  protected T doSwitch(int classifierID, EObject theEObject)
  {
    switch (classifierID)
    {
      case SoapIOModulePackage.ABSTRACT_SOAP_IO_OPERATION:
      {
        AbstractSoapIOOperation abstractSoapIOOperation = (AbstractSoapIOOperation)theEObject;
        T result = caseAbstractSoapIOOperation(abstractSoapIOOperation);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SoapIOModulePackage.ABSTRACT_TERMINATION_CONDITION:
      {
        AbstractTerminationCondition abstractTerminationCondition = (AbstractTerminationCondition)theEObject;
        T result = caseAbstractTerminationCondition(abstractTerminationCondition);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SoapIOModulePackage.DOCUMENT_ROOT:
      {
        DocumentRoot documentRoot = (DocumentRoot)theEObject;
        T result = caseDocumentRoot(documentRoot);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SoapIOModulePackage.PERL_TERMINATION_CONDITION:
      {
        PerlTerminationCondition perlTerminationCondition = (PerlTerminationCondition)theEObject;
        T result = casePerlTerminationCondition(perlTerminationCondition);
        if (result == null) result = caseAbstractTerminationCondition(perlTerminationCondition);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SoapIOModulePackage.SOAP_IO_CONFIGURATION:
      {
        SoapIOConfiguration soapIOConfiguration = (SoapIOConfiguration)theEObject;
        T result = caseSoapIOConfiguration(soapIOConfiguration);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SoapIOModulePackage.SOAP_IO_GET_OPERATION:
      {
        SoapIOGetOperation soapIOGetOperation = (SoapIOGetOperation)theEObject;
        T result = caseSoapIOGetOperation(soapIOGetOperation);
        if (result == null) result = caseAbstractSoapIOOperation(soapIOGetOperation);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SoapIOModulePackage.SOAP_IO_PUT_AND_GET_OPERATION:
      {
        SoapIOPutAndGetOperation soapIOPutAndGetOperation = (SoapIOPutAndGetOperation)theEObject;
        T result = caseSoapIOPutAndGetOperation(soapIOPutAndGetOperation);
        if (result == null) result = caseAbstractSoapIOOperation(soapIOPutAndGetOperation);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SoapIOModulePackage.SOAP_IO_PUT_OPERATION:
      {
        SoapIOPutOperation soapIOPutOperation = (SoapIOPutOperation)theEObject;
        T result = caseSoapIOPutOperation(soapIOPutOperation);
        if (result == null) result = caseAbstractSoapIOOperation(soapIOPutOperation);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      default: return defaultCase(theEObject);
    }
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Abstract Soap IO Operation</em>'. <!--
   * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
   * end-user-doc -->
   * 
   * @param object
   *          the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Abstract Soap IO Operation</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAbstractSoapIOOperation(AbstractSoapIOOperation object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Abstract Termination Condition</em>'. <!--
   * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
   * end-user-doc -->
   * 
   * @param object
   *          the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Abstract Termination Condition</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAbstractTerminationCondition(AbstractTerminationCondition object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Document Root</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Document Root</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseDocumentRoot(DocumentRoot object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Perl Termination Condition</em>'. <!--
   * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
   * end-user-doc -->
   * 
   * @param object
   *          the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Perl Termination Condition</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casePerlTerminationCondition(PerlTerminationCondition object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Soap IO Configuration</em>'. <!--
   * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
   * end-user-doc -->
   * 
   * @param object
   *          the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Soap IO Configuration</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseSoapIOConfiguration(SoapIOConfiguration object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Soap IO Get Operation</em>'. <!--
   * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
   * end-user-doc -->
   * 
   * @param object
   *          the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Soap IO Get Operation</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseSoapIOGetOperation(SoapIOGetOperation object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Soap IO Put And Get Operation</em>'. <!--
   * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
   * end-user-doc -->
   * 
   * @param object
   *          the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Soap IO Put And Get Operation</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseSoapIOPutAndGetOperation(SoapIOPutAndGetOperation object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Soap IO Put Operation</em>'. <!--
   * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
   * end-user-doc -->
   * 
   * @param object
   *          the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Soap IO Put Operation</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseSoapIOPutOperation(SoapIOPutOperation object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
   * <!-- begin-user-doc --> This
   * implementation returns null; returning a non-null result will terminate the switch, but this is the last case
   * anyway. <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject)
   * @generated
   */
  @Override
  public T defaultCase(EObject object)
  {
    return null;
  }

} // SoapIOModuleSwitch
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
