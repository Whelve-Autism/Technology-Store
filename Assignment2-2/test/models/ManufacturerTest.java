package models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 此类用于测试 Manufacturer 类。
 * This class contains unit tests for the Manufacturer class.
 *
 * @author Guoqing Lu, Fan Xinkang
 * @version 1.1
 * @since version 0.0
 */
class ManufacturerTest {

    Manufacturer manValid, manInvalid, manBorder, manBelowBorder, manEmpty;

    /**
     * 在每个测试方法执行前进行初始化操作，确保测试数据的干净状态，避免测试之间的相互影响。
     * Initialize before each test method to ensure the cleanliness of the test data and avoid mutual influence between tests.
     *
     * @author Guoqing Lu
     * @since version 0.0
     */
    @BeforeEach
    public void setup(){
        manValid = new Manufacturer("Samsung", 1000);
        manInvalid = new Manufacturer("Samsungs901234567890X", -1);
        manBorder = new Manufacturer("Samsungs901234567890",  1);
        manBelowBorder = new Manufacturer("Samsungs90123456789" , 0);
        manEmpty = new Manufacturer("", -10);
    }

    /**
     * 测试 Manufacturer 类的构造函数，包括 manufacturerName 和 numEmployees 的验证。
     * This method tests the constructor of the Manufacturer class, including the validation of manufacturerName and numEmployees.
     *
     * @author Guoqing Lu
     * @since version 0.0
     */
    @Test
    void constructorTests() {
        //testing manufacturerName - at <20, 20, 21 chars
        assertEquals("Samsung", manValid.getManufacturerName());  //value accepted - under 20 length limit
        assertEquals("Samsungs901234567890", manInvalid.getManufacturerName());  //value truncated to  20 length limit
        assertEquals("Samsungs901234567890", manBorder.getManufacturerName());  //value accepted - at 20 length limit
        assertEquals("Samsungs90123456789", manBelowBorder.getManufacturerName());//value accepted - at 10 length limit
        assertEquals("", manEmpty.getManufacturerName()); // value accepted - empty string
        //testing numEmployees (>=1)  - at valid and invalid,
        assertEquals(1000, manValid.getNumEmployees());  //valid value accepted correctly
        assertEquals(1, manInvalid.getNumEmployees());   // check that default is set when invalid input given
        assertEquals(1, manBorder.getNumEmployees());    // check that 1 is accepted as valid input
        assertEquals(1, manBelowBorder.getNumEmployees());   // check that default set when 0 is input (invalid)
        assertEquals(1, manEmpty.getNumEmployees());      // check that default is set when negative value is input.
    }

    /**
     * 测试 Manufacturer 类的 getter 和 setter 方法，包括 manufacturerName 和 numEmployees 的验证。
     * This method tests the getter and setter methods of the Manufacturer class, including the validation of manufacturerName and numEmployees.
     *
     * @author Guoqing Lu
     * @since version 0.0
     */
    @Test
    void manufacturerNameGetAndSetWorkingCorrectly() {
        assertEquals("Samsung", manValid.getManufacturerName());
        manValid.setManufacturerName("Apple");  //valid change
        assertEquals("Apple", manValid.getManufacturerName());
        manValid.setManufacturerName("Samsungs901234567890");  //valid change
        assertEquals("Samsungs901234567890", manValid.getManufacturerName());
        manValid.setManufacturerName("Samsungs90123456789");  //valid change
        assertEquals("Samsungs90123456789", manValid.getManufacturerName());
        manValid.setManufacturerName("XXXXXXXX901234567890XXX");  //invalid - no change
        assertEquals("Samsungs90123456789", manValid.getManufacturerName());
        manValid.setManufacturerName("");  //valid - no change
        assertEquals("", manValid.getManufacturerName());
    }

    /**
     * 测试 Manufacturer 类的 getter 和 setter 方法，包括 manufacturerName 和 numEmployees 的验证。
     * This method tests the getter and setter methods of the Manufacturer class, including the validation of manufacturerName and numEmployees.
     *
     * @author Guoqing Lu
     * @since version 0.0
     */
    @Test
    void numEmployeesGetAndSetWorkingCorrectly() {
        assertEquals(1000, manValid.getNumEmployees());
        manValid.setNumEmployees(999); //valid change
        assertEquals(999, manValid.getNumEmployees());
        manValid.setNumEmployees(1); //valid change
        assertEquals(1, manValid.getNumEmployees());
        manValid.setNumEmployees(10);
        assertEquals(10, manValid.getNumEmployees());
        manValid.setNumEmployees(0); //invalid change
        assertEquals(10, manValid.getNumEmployees());
        manValid.setNumEmployees(-1); //invalid change
        assertEquals(10, manValid.getNumEmployees());

    }

    /**
     * 测试 Manufacturer 类的 equals 方法，包括 Manufacturer 对象的比较。
     * This method tests the equals method of the Manufacturer class, including the comparison of Manufacturer objects.
     *
     * @author Guoqing Lu
     * @since version 0.0
     */
    @Test
    void validatingTheEqualsMethod() {
        //checking that equals works when the objects are at the same location
        Manufacturer copyManInvalid = manValid;
        assertEquals(manValid, copyManInvalid);
        //now checking that true is returned when the values in separate objects are the same
        assertEquals(new Manufacturer("Samsung", 1000), manValid);
        //checking that false is returned  when one or both fields are different
        assertNotEquals(new Manufacturer("Tesla", 1000), manValid);
        assertNotEquals(new Manufacturer("Samsung", 1999), manValid);
        assertNotEquals(new Manufacturer("Tesla", 1999), manValid);
    }

    /**
     * 测试 Manufacturer 类的 toString 方法。
     * This method tests the toString method of the Manufacturer class.
     *
     * @author Guoqing Lu
     * @since version 0.0
     */
    @Nested
    class ToString {

        /**
         * 测试 Manufacturer 类的 toString 方法，验证字符串表示是否包含制造商名称和员工数量。
         * This method tests the toString method of the Manufacturer class to ensure that the string representation includes the manufacturer name and the number of employees.
         *
         * @author Guoqing Lu
         * @since version 0.0
         */
        @Test
        void toStringContainsAllFieldsInObject() {
            //checking a Manufacturer contains manufacturer name and number of employees
            String manuStringpluralEmployees = manValid.toString();
            assertTrue(manuStringpluralEmployees.contains("Samsung"));
            assertTrue(manuStringpluralEmployees.contains("1000"));

            //checking a Manufacturer contains manufacturer name and number of employees
            String manuStringSingleEmployee = manBorder.toString();
            assertTrue(manuStringSingleEmployee.contains("Samsungs901234567890"));
            assertTrue(manuStringSingleEmployee.contains("1"));
        }

        /**
         * 测试 Manufacturer 类的 toString 方法，验证当员工数量为单数或复数时，字符串表示是否正确包含 "employee" 或 "employees"。
         * This method tests the toString method of the Manufacturer class to ensure that the string representation correctly includes "employee" or "employees" based on the number of employees.
         *
         * @author Guoqing Lu
         * @since version 0.0
         */
        @Test
        void toStringAddsEmployeesToTheString() {
            //checking a Manufacturer contains "employees" when number of employees is plural, 1 otherwise.
            //checking for plural
            String manuStringpluralEmployees = manValid.toString();
            assertTrue(manuStringpluralEmployees.contains("1000 employees"));
            //checking for singular
            String manuStringSingleEmployee = manBorder.toString();
            assertTrue(manuStringSingleEmployee.contains("1 employee"));
        }
    }
}
/*
 * End of test.models.ManufacturerTest Class.
 * Checked by Fan Xinkang on 2025/04/11.
 */