package com.ms.qaTools.saturn.resources.soapIOResource;

import com.ms.qaTools.saturn.types.DeviceResourceDefinition;
import com.ms.qaTools.saturn.types.NamedResourceDefinition;
import com.ms.qaTools.saturn.values.ComplexValue;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Cps Resource</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.ms.qaTools.saturn.resources.soapIOResource.CpsResource#getTimeOut <em>Time Out</em>}</li>
 *   <li>{@link com.ms.qaTools.saturn.resources.soapIOResource.CpsResource#getTransport <em>Transport</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.ms.qaTools.saturn.resources.soapIOResource.SoapIOResourcePackage#getCpsResource()
 * @model extendedMetaData="name='CpsResource' kind='elementOnly'"
 * @generated
 */
public interface CpsResource extends DeviceResourceDefinition
{
  /**
   * Returns the value of the '<em><b>Time Out</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Time Out</em>' containment reference isn't clear, there really should be more of a
   * description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Time Out</em>' containment reference.
   * @see #setTimeOut(ComplexValue)
   * @see com.ms.qaTools.saturn.resources.soapIOResource.SoapIOResourcePackage#getCpsResource_TimeOut()
   * @model containment="true"
   *        extendedMetaData="kind='element' name='TimeOut' namespace='##targetNamespace'"
   * @generated
   */
  ComplexValue getTimeOut();

  /**
   * Sets the value of the '{@link com.ms.qaTools.saturn.resources.soapIOResource.CpsResource#getTimeOut <em>Time Out</em>}' containment reference.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @param value the new value of the '<em>Time Out</em>' containment reference.
   * @see #getTimeOut()
   * @generated
   */
  void setTimeOut(ComplexValue value);

  /**
   * Returns the value of the '<em><b>Transport</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Transport</em>' containment reference isn't clear, there really should be more of a
   * description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Transport</em>' containment reference.
   * @see #setTransport(CPSTransport)
   * @see com.ms.qaTools.saturn.resources.soapIOResource.SoapIOResourcePackage#getCpsResource_Transport()
   * @model containment="true" required="true"
   *        extendedMetaData="kind='element' name='Transport' namespace='##targetNamespace'"
   * @generated
   */
  CPSTransport getTransport();

  /**
   * Sets the value of the '{@link com.ms.qaTools.saturn.resources.soapIOResource.CpsResource#getTransport <em>Transport</em>}' containment reference.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @param value the new value of the '<em>Transport</em>' containment reference.
   * @see #getTransport()
   * @generated
   */
  void setTransport(CPSTransport value);

} // CpsResource
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
