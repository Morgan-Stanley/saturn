package com.ms.qaTools.saturn.resources.teradataResource;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import com.ms.qaTools.saturn.types.TypesPackage;

/**
 * <!-- begin-user-doc --> The <b>Package</b> for the model. It contains accessors for the meta objects to represent
 * <ul>
 * <li>each class,</li>
 * <li>each feature of each class,</li>
 * <li>each enum,</li>
 * <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see com.ms.qaTools.saturn.resources.teradataResource.TeradataResourceFactory
 * @model kind="package"
 *        annotation="http://www.ms.com/2011/tdo-qa-tools/rcs author='$Author$' change='$Change$' file='$File$' id='$Id$' revision='$Revision$' timestamp='$DateTime$'"
 * @generated
 */
public interface TeradataResourcePackage extends EPackage
{
  /**
   * The package name.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  String                  eNAME                                   = "teradataResource";

  /**
   * The package namespace URI.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  String                  eNS_URI                                 = "http://www.ms.com/2009/Saturn/Resources/Teradata";

  /**
   * The package namespace name.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  String                  eNS_PREFIX                              = "teradataResource";

  /**
   * The singleton instance of the package.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  TeradataResourcePackage eINSTANCE                               = com.ms.qaTools.saturn.resources.teradataResource.impl.TeradataResourcePackageImpl.init();

  /**
   * The meta object id for the '{@link com.ms.qaTools.saturn.resources.teradataResource.impl.TeradataResourceImpl <em>Teradata Resource</em>}' class.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @see com.ms.qaTools.saturn.resources.teradataResource.impl.TeradataResourceImpl
   * @see com.ms.qaTools.saturn.resources.teradataResource.impl.TeradataResourcePackageImpl#getTeradataResource()
   * @generated
   */
  int                     TERADATA_RESOURCE                       = 0;

  /**
   * The feature id for the '<em><b>Enabled</b></em>' attribute.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int                     TERADATA_RESOURCE__ENABLED              = TypesPackage.DATABASE_RESOURCE_DEFINITION__ENABLED;

  /**
   * The feature id for the '<em><b>State</b></em>' attribute.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int                     TERADATA_RESOURCE__STATE                = TypesPackage.DATABASE_RESOURCE_DEFINITION__STATE;

  /**
   * The feature id for the '<em><b>Connector Preference</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
   * -->
   * 
   * @generated
   * @ordered
   */
  int                     TERADATA_RESOURCE__CONNECTOR_PREFERENCE = TypesPackage.DATABASE_RESOURCE_DEFINITION__CONNECTOR_PREFERENCE;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int                     TERADATA_RESOURCE__NAME                 = TypesPackage.DATABASE_RESOURCE_DEFINITION__NAME;

  /**
   * The feature id for the '<em><b>Persistent</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TERADATA_RESOURCE__PERSISTENT = TypesPackage.DATABASE_RESOURCE_DEFINITION__PERSISTENT;

  /**
   * The feature id for the '<em><b>Login</b></em>' containment reference.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int                     TERADATA_RESOURCE__LOGIN                = TypesPackage.DATABASE_RESOURCE_DEFINITION_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Password</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
   * -->
   * 
   * @generated
   * @ordered
   */
  int                     TERADATA_RESOURCE__PASSWORD             = TypesPackage.DATABASE_RESOURCE_DEFINITION_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Server</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
   * -->
   * 
   * @generated
   * @ordered
   */
  int                     TERADATA_RESOURCE__SERVER               = TypesPackage.DATABASE_RESOURCE_DEFINITION_FEATURE_COUNT + 2;

  /**
   * The feature id for the '<em><b>Database</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
   * -->
   * 
   * @generated
   * @ordered
   */
  int                     TERADATA_RESOURCE__DATABASE             = TypesPackage.DATABASE_RESOURCE_DEFINITION_FEATURE_COUNT + 3;

  /**
   * The feature id for the '<em><b>Authentification</b></em>' attribute.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int                     TERADATA_RESOURCE__AUTHENTIFICATION     = TypesPackage.DATABASE_RESOURCE_DEFINITION_FEATURE_COUNT + 4;

  /**
   * The number of structural features of the '<em>Teradata Resource</em>' class.
   * <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * @generated
   * @ordered
   */
  int                     TERADATA_RESOURCE_FEATURE_COUNT         = TypesPackage.DATABASE_RESOURCE_DEFINITION_FEATURE_COUNT + 5;

  /**
   * The meta object id for the '{@link com.ms.qaTools.saturn.resources.teradataResource.AuthentificationEnums <em>Authentification Enums</em>}' enum.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @see com.ms.qaTools.saturn.resources.teradataResource.AuthentificationEnums
   * @see com.ms.qaTools.saturn.resources.teradataResource.impl.TeradataResourcePackageImpl#getAuthentificationEnums()
   * @generated
   */
  int                     AUTHENTIFICATION_ENUMS                  = 1;

  /**
   * The meta object id for the '<em>Authentification Enums Object</em>' data type.
   * <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * @see com.ms.qaTools.saturn.resources.teradataResource.AuthentificationEnums
   * @see com.ms.qaTools.saturn.resources.teradataResource.impl.TeradataResourcePackageImpl#getAuthentificationEnumsObject()
   * @generated
   */
  int                     AUTHENTIFICATION_ENUMS_OBJECT           = 2;

  /**
   * Returns the meta object for class '{@link com.ms.qaTools.saturn.resources.teradataResource.TeradataResource <em>Teradata Resource</em>}'.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @return the meta object for class '<em>Teradata Resource</em>'.
   * @see com.ms.qaTools.saturn.resources.teradataResource.TeradataResource
   * @generated
   */
  EClass getTeradataResource();

  /**
   * Returns the meta object for the containment reference '
   * {@link com.ms.qaTools.saturn.resources.teradataResource.TeradataResource#getLogin <em>Login</em>}'. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for the containment reference '<em>Login</em>'.
   * @see com.ms.qaTools.saturn.resources.teradataResource.TeradataResource#getLogin()
   * @see #getTeradataResource()
   * @generated
   */
  EReference getTeradataResource_Login();

  /**
   * Returns the meta object for the containment reference '
   * {@link com.ms.qaTools.saturn.resources.teradataResource.TeradataResource#getPassword <em>Password</em>}'. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for the containment reference '<em>Password</em>'.
   * @see com.ms.qaTools.saturn.resources.teradataResource.TeradataResource#getPassword()
   * @see #getTeradataResource()
   * @generated
   */
  EReference getTeradataResource_Password();

  /**
   * Returns the meta object for the containment reference '
   * {@link com.ms.qaTools.saturn.resources.teradataResource.TeradataResource#getServer <em>Server</em>}'. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for the containment reference '<em>Server</em>'.
   * @see com.ms.qaTools.saturn.resources.teradataResource.TeradataResource#getServer()
   * @see #getTeradataResource()
   * @generated
   */
  EReference getTeradataResource_Server();

  /**
   * Returns the meta object for the containment reference '
   * {@link com.ms.qaTools.saturn.resources.teradataResource.TeradataResource#getDatabase <em>Database</em>}'. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for the containment reference '<em>Database</em>'.
   * @see com.ms.qaTools.saturn.resources.teradataResource.TeradataResource#getDatabase()
   * @see #getTeradataResource()
   * @generated
   */
  EReference getTeradataResource_Database();

  /**
   * Returns the meta object for the attribute '{@link com.ms.qaTools.saturn.resources.teradataResource.TeradataResource#getAuthentification <em>Authentification</em>}'.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Authentification</em>'.
   * @see com.ms.qaTools.saturn.resources.teradataResource.TeradataResource#getAuthentification()
   * @see #getTeradataResource()
   * @generated
   */
  EAttribute getTeradataResource_Authentification();

  /**
   * Returns the meta object for enum '{@link com.ms.qaTools.saturn.resources.teradataResource.AuthentificationEnums <em>Authentification Enums</em>}'.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @return the meta object for enum '<em>Authentification Enums</em>'.
   * @see com.ms.qaTools.saturn.resources.teradataResource.AuthentificationEnums
   * @generated
   */
  EEnum getAuthentificationEnums();

  /**
   * Returns the meta object for data type '{@link com.ms.qaTools.saturn.resources.teradataResource.AuthentificationEnums <em>Authentification Enums Object</em>}'.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @return the meta object for data type '<em>Authentification Enums Object</em>'.
   * @see com.ms.qaTools.saturn.resources.teradataResource.AuthentificationEnums
   * @model instanceClass="com.ms.qaTools.saturn.resources.teradataResource.AuthentificationEnums"
   *        extendedMetaData="name='AuthentificationEnums:Object' baseType='AuthentificationEnums'"
   * @generated
   */
  EDataType getAuthentificationEnumsObject();

  /**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
  TeradataResourceFactory getTeradataResourceFactory();

  /**
   * <!-- begin-user-doc --> Defines literals for the meta objects that represent
   * <ul>
   * <li>each class,</li>
   * <li>each feature of each class,</li>
   * <li>each enum,</li>
   * <li>and each data type</li>
   * </ul>
   * <!-- end-user-doc -->
   * @generated
   */
  interface Literals
  {
    /**
     * The meta object literal for the '{@link com.ms.qaTools.saturn.resources.teradataResource.impl.TeradataResourceImpl <em>Teradata Resource</em>}' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see com.ms.qaTools.saturn.resources.teradataResource.impl.TeradataResourceImpl
     * @see com.ms.qaTools.saturn.resources.teradataResource.impl.TeradataResourcePackageImpl#getTeradataResource()
     * @generated
     */
    EClass     TERADATA_RESOURCE                   = eINSTANCE.getTeradataResource();

    /**
     * The meta object literal for the '<em><b>Login</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference TERADATA_RESOURCE__LOGIN            = eINSTANCE.getTeradataResource_Login();

    /**
     * The meta object literal for the '<em><b>Password</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference TERADATA_RESOURCE__PASSWORD         = eINSTANCE.getTeradataResource_Password();

    /**
     * The meta object literal for the '<em><b>Server</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference TERADATA_RESOURCE__SERVER           = eINSTANCE.getTeradataResource_Server();

    /**
     * The meta object literal for the '<em><b>Database</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference TERADATA_RESOURCE__DATABASE         = eINSTANCE.getTeradataResource_Database();

    /**
     * The meta object literal for the '<em><b>Authentification</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute TERADATA_RESOURCE__AUTHENTIFICATION = eINSTANCE.getTeradataResource_Authentification();

    /**
     * The meta object literal for the '{@link com.ms.qaTools.saturn.resources.teradataResource.AuthentificationEnums <em>Authentification Enums</em>}' enum.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see com.ms.qaTools.saturn.resources.teradataResource.AuthentificationEnums
     * @see com.ms.qaTools.saturn.resources.teradataResource.impl.TeradataResourcePackageImpl#getAuthentificationEnums()
     * @generated
     */
    EEnum      AUTHENTIFICATION_ENUMS              = eINSTANCE.getAuthentificationEnums();

    /**
     * The meta object literal for the '<em>Authentification Enums Object</em>' data type.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see com.ms.qaTools.saturn.resources.teradataResource.AuthentificationEnums
     * @see com.ms.qaTools.saturn.resources.teradataResource.impl.TeradataResourcePackageImpl#getAuthentificationEnumsObject()
     * @generated
     */
    EDataType  AUTHENTIFICATION_ENUMS_OBJECT       = eINSTANCE.getAuthentificationEnumsObject();

  }

} // TeradataResourcePackage
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
