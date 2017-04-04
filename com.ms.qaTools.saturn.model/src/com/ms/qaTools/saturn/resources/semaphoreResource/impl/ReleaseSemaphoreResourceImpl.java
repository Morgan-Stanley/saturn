/**
 * COPYRIGHT NOTICE AND DISCLAIMER
 *  
 * Copyright © 2009, Contributor
 * This program is free software; you can redistribute it and/or modify it under the terms of the GNU Lesser General Public License, version 3, as published by the Free Software Foundation.
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License, version 3 for more details.
 * You should have received a copy of the GNU Lesser General Public License, version 3, along with this program; if not, see http://www.gnu.org/licenses/ or write to the Free Software Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 * THE FOLLOWING DISCLAIMER APPLIES TO ALL SOFTWARE CODE AND OTHER MATERIALS CONTRIBUTED IN CONNECTION WITH THIS PROGRAM:
 * THIS SOFTWARE IS LICENSED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE AND ANY WARRANTY OF NON-INFRINGEMENT, ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE. THIS SOFTWARE MAY BE REDISTRIBUTED TO OTHERS ONLY BY EFFECTIVELY USING THIS OR ANOTHER EQUIVALENT DISCLAIMER AS WELL AS ANY OTHER LICENSE TERMS THAT MAY APPLY.
 */
package com.ms.qaTools.saturn.resources.semaphoreResource.impl;

import java.math.BigInteger;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import com.ms.qaTools.saturn.resources.semaphoreResource.ReleaseSemaphoreResource;
import com.ms.qaTools.saturn.resources.semaphoreResource.SemaphoreResourcePackage;
import com.ms.qaTools.saturn.types.NamedResourceDefinition;
import com.ms.qaTools.saturn.types.impl.NamedResourceDefinitionImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Release Semaphore Resource</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.ms.qaTools.saturn.resources.semaphoreResource.impl.ReleaseSemaphoreResourceImpl#getSemaphoreResource <em>Semaphore Resource</em>}</li>
 *   <li>{@link com.ms.qaTools.saturn.resources.semaphoreResource.impl.ReleaseSemaphoreResourceImpl#getSemaphoreName <em>Semaphore Name</em>}</li>
 *   <li>{@link com.ms.qaTools.saturn.resources.semaphoreResource.impl.ReleaseSemaphoreResourceImpl#getTimeout <em>Timeout</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ReleaseSemaphoreResourceImpl extends NamedResourceDefinitionImpl implements ReleaseSemaphoreResource
{
  /**
   * The cached value of the '{@link #getSemaphoreResource() <em>Semaphore Resource</em>}' containment reference. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see #getSemaphoreResource()
   * @generated
   * @ordered
   */
  protected NamedResourceDefinition semaphoreResource;

  /**
   * The default value of the '{@link #getSemaphoreName() <em>Semaphore Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSemaphoreName()
   * @generated
   * @ordered
   */
  protected static final String     SEMAPHORE_NAME_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getSemaphoreName() <em>Semaphore Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSemaphoreName()
   * @generated
   * @ordered
   */
  protected String                  semaphoreName           = SEMAPHORE_NAME_EDEFAULT;

  /**
   * The default value of the '{@link #getTimeout() <em>Timeout</em>}' attribute.
   * <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * @see #getTimeout()
   * @generated
   * @ordered
   */
  protected static final BigInteger TIMEOUT_EDEFAULT        = null;

  /**
   * The cached value of the '{@link #getTimeout() <em>Timeout</em>}' attribute.
   * <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * @see #getTimeout()
   * @generated
   * @ordered
   */
  protected BigInteger              timeout                 = TIMEOUT_EDEFAULT;

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  protected ReleaseSemaphoreResourceImpl()
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
    return SemaphoreResourcePackage.Literals.RELEASE_SEMAPHORE_RESOURCE;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  public NamedResourceDefinition getSemaphoreResource()
  {
    return semaphoreResource;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetSemaphoreResource(NamedResourceDefinition newSemaphoreResource,
      NotificationChain msgs)
  {
    NamedResourceDefinition oldSemaphoreResource = semaphoreResource;
    semaphoreResource = newSemaphoreResource;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SemaphoreResourcePackage.RELEASE_SEMAPHORE_RESOURCE__SEMAPHORE_RESOURCE, oldSemaphoreResource, newSemaphoreResource);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  public void setSemaphoreResource(NamedResourceDefinition newSemaphoreResource)
  {
    if (newSemaphoreResource != semaphoreResource)
    {
      NotificationChain msgs = null;
      if (semaphoreResource != null)
        msgs = ((InternalEObject)semaphoreResource).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SemaphoreResourcePackage.RELEASE_SEMAPHORE_RESOURCE__SEMAPHORE_RESOURCE, null, msgs);
      if (newSemaphoreResource != null)
        msgs = ((InternalEObject)newSemaphoreResource).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SemaphoreResourcePackage.RELEASE_SEMAPHORE_RESOURCE__SEMAPHORE_RESOURCE, null, msgs);
      msgs = basicSetSemaphoreResource(newSemaphoreResource, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SemaphoreResourcePackage.RELEASE_SEMAPHORE_RESOURCE__SEMAPHORE_RESOURCE, newSemaphoreResource, newSemaphoreResource));
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  public String getSemaphoreName()
  {
    return semaphoreName;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  public void setSemaphoreName(String newSemaphoreName)
  {
    String oldSemaphoreName = semaphoreName;
    semaphoreName = newSemaphoreName;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SemaphoreResourcePackage.RELEASE_SEMAPHORE_RESOURCE__SEMAPHORE_NAME, oldSemaphoreName, semaphoreName));
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  public BigInteger getTimeout()
  {
    return timeout;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  public void setTimeout(BigInteger newTimeout)
  {
    BigInteger oldTimeout = timeout;
    timeout = newTimeout;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SemaphoreResourcePackage.RELEASE_SEMAPHORE_RESOURCE__TIMEOUT, oldTimeout, timeout));
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
      case SemaphoreResourcePackage.RELEASE_SEMAPHORE_RESOURCE__SEMAPHORE_RESOURCE:
        return basicSetSemaphoreResource(null, msgs);
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
      case SemaphoreResourcePackage.RELEASE_SEMAPHORE_RESOURCE__SEMAPHORE_RESOURCE:
        return getSemaphoreResource();
      case SemaphoreResourcePackage.RELEASE_SEMAPHORE_RESOURCE__SEMAPHORE_NAME:
        return getSemaphoreName();
      case SemaphoreResourcePackage.RELEASE_SEMAPHORE_RESOURCE__TIMEOUT:
        return getTimeout();
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
      case SemaphoreResourcePackage.RELEASE_SEMAPHORE_RESOURCE__SEMAPHORE_RESOURCE:
        setSemaphoreResource((NamedResourceDefinition)newValue);
        return;
      case SemaphoreResourcePackage.RELEASE_SEMAPHORE_RESOURCE__SEMAPHORE_NAME:
        setSemaphoreName((String)newValue);
        return;
      case SemaphoreResourcePackage.RELEASE_SEMAPHORE_RESOURCE__TIMEOUT:
        setTimeout((BigInteger)newValue);
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
      case SemaphoreResourcePackage.RELEASE_SEMAPHORE_RESOURCE__SEMAPHORE_RESOURCE:
        setSemaphoreResource((NamedResourceDefinition)null);
        return;
      case SemaphoreResourcePackage.RELEASE_SEMAPHORE_RESOURCE__SEMAPHORE_NAME:
        setSemaphoreName(SEMAPHORE_NAME_EDEFAULT);
        return;
      case SemaphoreResourcePackage.RELEASE_SEMAPHORE_RESOURCE__TIMEOUT:
        setTimeout(TIMEOUT_EDEFAULT);
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
      case SemaphoreResourcePackage.RELEASE_SEMAPHORE_RESOURCE__SEMAPHORE_RESOURCE:
        return semaphoreResource != null;
      case SemaphoreResourcePackage.RELEASE_SEMAPHORE_RESOURCE__SEMAPHORE_NAME:
        return SEMAPHORE_NAME_EDEFAULT == null ? semaphoreName != null : !SEMAPHORE_NAME_EDEFAULT.equals(semaphoreName);
      case SemaphoreResourcePackage.RELEASE_SEMAPHORE_RESOURCE__TIMEOUT:
        return TIMEOUT_EDEFAULT == null ? timeout != null : !TIMEOUT_EDEFAULT.equals(timeout);
    }
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String toString()
  {
    if (eIsProxy()) return super.toString();

    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (semaphoreName: ");
    result.append(semaphoreName);
    result.append(", timeout: ");
    result.append(timeout);
    result.append(')');
    return result.toString();
  }

} // ReleaseSemaphoreResourceImpl