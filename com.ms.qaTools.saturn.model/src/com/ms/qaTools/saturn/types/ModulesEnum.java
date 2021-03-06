package com.ms.qaTools.saturn.types;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc --> A representation of the literals of the enumeration '<em><b>Modules Enum</b></em>', and
 * utility methods for working with them. <!-- end-user-doc -->
 * @see com.ms.qaTools.saturn.types.TypesPackage#getModulesEnum()
 * @model extendedMetaData="name='ModulesEnum'"
 * @generated
 */
public enum ModulesEnum implements Enumerator
{
  /**
   * The '<em><b>SQL</b></em>' literal object.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @see #SQL_VALUE
   * @generated
   * @ordered
   */
  SQL(0, "SQL", "SQL"),

  /**
   * The '<em><b>Data Compare</b></em>' literal object.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @see #DATA_COMPARE_VALUE
   * @generated
   * @ordered
   */
  DATA_COMPARE(1, "DataCompare", "DataCompare"),

  /**
   * The '<em><b>Run Cmds</b></em>' literal object.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @see #RUN_CMDS_VALUE
   * @generated
   * @ordered
   */
  RUN_CMDS(2, "RunCmds", "RunCmds");

  /**
   * The '<em><b>SQL</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>SQL</b></em>' literal object isn't clear, there really should be more of a description
   * here...
   * </p>
   * <!-- end-user-doc -->
   * @see #SQL
   * @model
   * @generated
   * @ordered
   */
  public static final int               SQL_VALUE          = 0;

  /**
   * The '<em><b>Data Compare</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>Data Compare</b></em>' literal object isn't clear, there really should be more of a
   * description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #DATA_COMPARE
   * @model name="DataCompare"
   * @generated
   * @ordered
   */
  public static final int               DATA_COMPARE_VALUE = 1;

  /**
   * The '<em><b>Run Cmds</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>Run Cmds</b></em>' literal object isn't clear, there really should be more of a
   * description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #RUN_CMDS
   * @model name="RunCmds"
   * @generated
   * @ordered
   */
  public static final int               RUN_CMDS_VALUE     = 2;

  /**
   * An array of all the '<em><b>Modules Enum</b></em>' enumerators.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  private static final ModulesEnum[]    VALUES_ARRAY       = new ModulesEnum[]
    {
      SQL,
      DATA_COMPARE,
      RUN_CMDS,
    };

  /**
   * A public read-only list of all the '<em><b>Modules Enum</b></em>' enumerators.
   * <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * @generated
   */
  public static final List<ModulesEnum> VALUES             = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

  /**
   * Returns the '<em><b>Modules Enum</b></em>' literal with the specified literal value.
   * <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * @generated
   */
  public static ModulesEnum get(String literal)
  {
    for (int i = 0; i < VALUES_ARRAY.length; ++i)
    {
      ModulesEnum result = VALUES_ARRAY[i];
      if (result.toString().equals(literal))
      {
        return result;
      }
    }
    return null;
  }

  /**
   * Returns the '<em><b>Modules Enum</b></em>' literal with the specified name.
   * <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * @generated
   */
  public static ModulesEnum getByName(String name)
  {
    for (int i = 0; i < VALUES_ARRAY.length; ++i)
    {
      ModulesEnum result = VALUES_ARRAY[i];
      if (result.getName().equals(name))
      {
        return result;
      }
    }
    return null;
  }

  /**
   * Returns the '<em><b>Modules Enum</b></em>' literal with the specified integer value.
   * <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * @generated
   */
  public static ModulesEnum get(int value)
  {
    switch (value)
    {
      case SQL_VALUE: return SQL;
      case DATA_COMPARE_VALUE: return DATA_COMPARE;
      case RUN_CMDS_VALUE: return RUN_CMDS;
    }
    return null;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  private final int    value;

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  private final String name;

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  private final String literal;

  /**
   * Only this class can construct instances.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  private ModulesEnum(int value, String name, String literal)
  {
    this.value = value;
    this.name = name;
    this.literal = literal;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  public int getValue()
  {
    return value;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  public String getName()
  {
    return name;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  public String getLiteral()
  {
    return literal;
  }

  /**
   * Returns the literal value of the enumerator, which is its string representation.
   * <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * @generated
   */
  @Override
  public String toString()
  {
    return literal;
  }

} // ModulesEnum
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
