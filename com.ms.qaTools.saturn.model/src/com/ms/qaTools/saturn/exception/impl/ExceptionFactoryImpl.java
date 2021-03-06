package com.ms.qaTools.saturn.exception.impl;

import com.ms.qaTools.saturn.exception.*;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;

import com.ms.qaTools.saturn.exception.CompareException;
import com.ms.qaTools.saturn.exception.CompareInitException;
import com.ms.qaTools.saturn.exception.DocumentRoot;
import com.ms.qaTools.saturn.exception.ExceptionFactory;
import com.ms.qaTools.saturn.exception.ExceptionPackage;
import com.ms.qaTools.saturn.exception.FileNotFoundException;
import com.ms.qaTools.saturn.exception.FileSystemException;
import com.ms.qaTools.saturn.exception.PermissionDeniedException;
import com.ms.qaTools.saturn.exception.QATUnknownCode;
import com.ms.qaTools.saturn.exception.RawException;
import com.ms.qaTools.saturn.exception.SATException;
import com.ms.qaTools.saturn.exception.StackTraceException;
import com.ms.qaTools.saturn.exception.TraceableException;
import com.ms.qaTools.saturn.exception.exception;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Factory</b>. <!-- end-user-doc -->
 * @generated
 */
public class ExceptionFactoryImpl extends EFactoryImpl implements ExceptionFactory
{
  /**
   * Creates the default factory implementation.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  public static ExceptionFactory init()
  {
    try
    {
      ExceptionFactory theExceptionFactory = (ExceptionFactory)EPackage.Registry.INSTANCE.getEFactory(ExceptionPackage.eNS_URI);
      if (theExceptionFactory != null)
      {
        return theExceptionFactory;
      }
    }
    catch (Exception exception)
    {
      EcorePlugin.INSTANCE.log(exception);
    }
    return new ExceptionFactoryImpl();
  }

  /**
   * Creates an instance of the factory.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  public ExceptionFactoryImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EObject create(EClass eClass)
  {
    switch (eClass.getClassifierID())
    {
      case ExceptionPackage.COMPARE_EXCEPTION: return createCompareException();
      case ExceptionPackage.COMPARE_INIT_EXCEPTION: return createCompareInitException();
      case ExceptionPackage.DOCUMENT_ROOT: return createDocumentRoot();
      case ExceptionPackage.EXCEPTION: return createexception();
      case ExceptionPackage.FILE_NOT_FOUND_EXCEPTION: return createFileNotFoundException();
      case ExceptionPackage.FILE_SYSTEM_EXCEPTION: return createFileSystemException();
      case ExceptionPackage.PERMISSION_DENIED_EXCEPTION: return createPermissionDeniedException();
      case ExceptionPackage.QAT_UNKNOWN_CODE: return createQATUnknownCode();
      case ExceptionPackage.RAW_EXCEPTION: return createRawException();
      case ExceptionPackage.SAT_EXCEPTION: return createSATException();
      case ExceptionPackage.STACK_TRACE_EXCEPTION: return createStackTraceException();
      case ExceptionPackage.TRACEABLE_EXCEPTION: return createTraceableException();
      default:
        throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  public CompareException createCompareException()
  {
    CompareExceptionImpl compareException = new CompareExceptionImpl();
    return compareException;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  public CompareInitException createCompareInitException()
  {
    CompareInitExceptionImpl compareInitException = new CompareInitExceptionImpl();
    return compareInitException;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  public DocumentRoot createDocumentRoot()
  {
    DocumentRootImpl documentRoot = new DocumentRootImpl();
    return documentRoot;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  public exception createexception()
  {
    exceptionImpl exception = new exceptionImpl();
    return exception;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  public FileNotFoundException createFileNotFoundException()
  {
    FileNotFoundExceptionImpl fileNotFoundException = new FileNotFoundExceptionImpl();
    return fileNotFoundException;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  public FileSystemException createFileSystemException()
  {
    FileSystemExceptionImpl fileSystemException = new FileSystemExceptionImpl();
    return fileSystemException;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  public PermissionDeniedException createPermissionDeniedException()
  {
    PermissionDeniedExceptionImpl permissionDeniedException = new PermissionDeniedExceptionImpl();
    return permissionDeniedException;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  public QATUnknownCode createQATUnknownCode()
  {
    QATUnknownCodeImpl qatUnknownCode = new QATUnknownCodeImpl();
    return qatUnknownCode;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  public RawException createRawException()
  {
    RawExceptionImpl rawException = new RawExceptionImpl();
    return rawException;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  public SATException createSATException()
  {
    SATExceptionImpl satException = new SATExceptionImpl();
    return satException;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  public StackTraceException createStackTraceException()
  {
    StackTraceExceptionImpl stackTraceException = new StackTraceExceptionImpl();
    return stackTraceException;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  public TraceableException createTraceableException()
  {
    TraceableExceptionImpl traceableException = new TraceableExceptionImpl();
    return traceableException;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  public ExceptionPackage getExceptionPackage()
  {
    return (ExceptionPackage)getEPackage();
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @deprecated
   * @generated
   */
  @Deprecated
  public static ExceptionPackage getPackage()
  {
    return ExceptionPackage.eINSTANCE;
  }

} // ExceptionFactoryImpl
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
