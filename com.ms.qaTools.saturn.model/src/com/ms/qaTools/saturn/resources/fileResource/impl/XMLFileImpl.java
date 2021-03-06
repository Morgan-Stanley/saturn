package com.ms.qaTools.saturn.resources.fileResource.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import com.ms.qaTools.saturn.resources.fileResource.FileResourcePackage;
import com.ms.qaTools.saturn.resources.fileResource.XMLFile;
import com.ms.qaTools.saturn.resources.fileResource.XMLNamespaceDefinitions;
import com.ms.qaTools.saturn.values.ComplexValue;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>XML File</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.ms.qaTools.saturn.resources.fileResource.impl.XMLFileImpl#getNamespaceDefinitions <em>Namespace Definitions</em>}</li>
 *   <li>{@link com.ms.qaTools.saturn.resources.fileResource.impl.XMLFileImpl#getXPath <em>XPath</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class XMLFileImpl extends DataSetFileImpl implements XMLFile
{
  /**
   * The cached value of the '{@link #getNamespaceDefinitions() <em>Namespace Definitions</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getNamespaceDefinitions()
   * @generated
   * @ordered
   */
  protected XMLNamespaceDefinitions namespaceDefinitions;
  /**
   * The cached value of the '{@link #getXPath() <em>XPath</em>}' containment reference.
   * <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * @see #getXPath()
   * @generated
   * @ordered
   */
  protected ComplexValue xPath;

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  protected XMLFileImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass()
  {
    return FileResourcePackage.Literals.XML_FILE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XMLNamespaceDefinitions getNamespaceDefinitions()
  {
    return namespaceDefinitions;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetNamespaceDefinitions(XMLNamespaceDefinitions newNamespaceDefinitions, NotificationChain msgs)
  {
    XMLNamespaceDefinitions oldNamespaceDefinitions = namespaceDefinitions;
    namespaceDefinitions = newNamespaceDefinitions;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FileResourcePackage.XML_FILE__NAMESPACE_DEFINITIONS, oldNamespaceDefinitions, newNamespaceDefinitions);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setNamespaceDefinitions(XMLNamespaceDefinitions newNamespaceDefinitions)
  {
    if (newNamespaceDefinitions != namespaceDefinitions)
    {
      NotificationChain msgs = null;
      if (namespaceDefinitions != null)
        msgs = ((InternalEObject)namespaceDefinitions).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FileResourcePackage.XML_FILE__NAMESPACE_DEFINITIONS, null, msgs);
      if (newNamespaceDefinitions != null)
        msgs = ((InternalEObject)newNamespaceDefinitions).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FileResourcePackage.XML_FILE__NAMESPACE_DEFINITIONS, null, msgs);
      msgs = basicSetNamespaceDefinitions(newNamespaceDefinitions, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FileResourcePackage.XML_FILE__NAMESPACE_DEFINITIONS, newNamespaceDefinitions, newNamespaceDefinitions));
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  public ComplexValue getXPath()
  {
    return xPath;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetXPath(ComplexValue newXPath, NotificationChain msgs)
  {
    ComplexValue oldXPath = xPath;
    xPath = newXPath;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FileResourcePackage.XML_FILE__XPATH, oldXPath, newXPath);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  public void setXPath(ComplexValue newXPath)
  {
    if (newXPath != xPath)
    {
      NotificationChain msgs = null;
      if (xPath != null)
        msgs = ((InternalEObject)xPath).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FileResourcePackage.XML_FILE__XPATH, null, msgs);
      if (newXPath != null)
        msgs = ((InternalEObject)newXPath).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FileResourcePackage.XML_FILE__XPATH, null, msgs);
      msgs = basicSetXPath(newXPath, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FileResourcePackage.XML_FILE__XPATH, newXPath, newXPath));
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case FileResourcePackage.XML_FILE__NAMESPACE_DEFINITIONS:
        return basicSetNamespaceDefinitions(null, msgs);
      case FileResourcePackage.XML_FILE__XPATH:
        return basicSetXPath(null, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType)
  {
    switch (featureID)
    {
      case FileResourcePackage.XML_FILE__NAMESPACE_DEFINITIONS:
        return getNamespaceDefinitions();
      case FileResourcePackage.XML_FILE__XPATH:
        return getXPath();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case FileResourcePackage.XML_FILE__NAMESPACE_DEFINITIONS:
        setNamespaceDefinitions((XMLNamespaceDefinitions)newValue);
        return;
      case FileResourcePackage.XML_FILE__XPATH:
        setXPath((ComplexValue)newValue);
        return;
    }
    super.eSet(featureID, newValue);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eUnset(int featureID)
  {
    switch (featureID)
    {
      case FileResourcePackage.XML_FILE__NAMESPACE_DEFINITIONS:
        setNamespaceDefinitions((XMLNamespaceDefinitions)null);
        return;
      case FileResourcePackage.XML_FILE__XPATH:
        setXPath((ComplexValue)null);
        return;
    }
    super.eUnset(featureID);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  @Override
  public boolean eIsSet(int featureID)
  {
    switch (featureID)
    {
      case FileResourcePackage.XML_FILE__NAMESPACE_DEFINITIONS:
        return namespaceDefinitions != null;
      case FileResourcePackage.XML_FILE__XPATH:
        return xPath != null;
    }
    return super.eIsSet(featureID);
  }

} // XMLFileImpl
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
