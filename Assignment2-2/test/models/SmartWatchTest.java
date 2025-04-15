package models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 此类用于测试 SmartWatch 类。
 * This class is used to test the SmartWatch class.
 *
 * @author Fan Xinkang
 * @since version 1.1
 */
class SmartWatchTest {

    private SmartWatch validSmartWatch, edgeCaseSmartWatch, invalidSmartWatch;

    /**
     * 在每个测试方法执行前进行初始化操作，确保测试数据的干净状态，避免测试之间的相互影响。
     * Initialize before each test method to ensure the cleanliness of the test data and avoid mutual influence between tests.
     *
     * @author Fan Xinkang
     * @since version 1.1
     */
    @BeforeEach
    void setUp() {

        Manufacturer validManufacturer = new Manufacturer("0123456789012345678", 1);
        Manufacturer edgeCaseManufacturer = new Manufacturer("01234567890123456789", 1);
        Manufacturer invalidManufacturer = new Manufacturer("012345678901234567890", 0);

        validSmartWatch = new SmartWatch("01234567890123456789012345678", 21, validManufacturer, "012345678", "0123456789012345678", "012345678", "AMOLED");
        edgeCaseSmartWatch = new SmartWatch("012345678901234567890123456789", 20, edgeCaseManufacturer, "0123456789", "01234567890123456789", "0123456789", "AMOLED");
        invalidSmartWatch = new SmartWatch("0123456789012345678901234567890", 19, invalidManufacturer, "01234567890", "012345678901234567890", "01234567890", "LCD");
    }

    /**
     * 在每个测试方法执行完成后进行清理工作，确保测试环境的干净状态，避免测试之间的相互影响。
     * Clean up after the completion of each test method to ensure the cleanliness of the test environment and avoid mutual influence between tests.
     *
     * @author Fan Xinkang
     * @since version 1.1
     */
    @AfterEach
    void tearDown() {
        validSmartWatch = edgeCaseSmartWatch = invalidSmartWatch = null;
    }

    /**
     * 测试 getters 方法并验证它们的返回值是否符合预期。
     * Test the getters methods and verify that their return values match the expected values.
     *
     * @author Fan Xinkang
     * @since version 1.1
     */
    @Nested
    class Getters {

        /**
         * 测试 getDisplayType 方法并验证它的返回值是否符合预期。
         * Test the getDisplayType methods and verify that their return values match the expected values.
         *
         * @author Fan Xinkang
         * @since version 1.1
         */
        @Test
        void testGetDisplayType() {
            assertEquals("AMOLED", validSmartWatch.getDisplayType());
            assertEquals("AMOLED", edgeCaseSmartWatch.getDisplayType());
            assertEquals("LCD", invalidSmartWatch.getDisplayType());
        }

        /**
         * 测试 getInsurancePremium 方法并验证它的返回值是否符合预期。
         * Test the getInsurancePremium methods and verify that their return values match the expected values.
         *
         * @author Fan Xinkang
         * @since version 1.1
         */
        @Test
        void testGetInsurancePremium() {
            assertEquals(21 * 0.06, validSmartWatch.getInsurancePremium(), 0.01);
            assertEquals(20 * 0.06, edgeCaseSmartWatch.getInsurancePremium(), 0.01);
            assertEquals(20 * 0.06, invalidSmartWatch.getInsurancePremium(), 0.01);
        }
    }

    /**
     * 测试 setters 方法并验证它们的行为是否符合预期。
     * Test the setters methods and verify that their behavior matches the expected behavior.
     *
     * @author Fan Xinkang
     * @since version 1.1
     */
    @Nested
    class Setters {

        /**
         * 测试 setDisplayType 方法并验证它的行为是否符合预期。
         * Test the setDisplayType methods and verify that their behavior matches the expected behavior.
         *
         * @author Fan Xinkang
         * @since version 1.1
         */
        @Test
        void testSetDisplayType() {
            assertEquals("AMOLED", validSmartWatch.getDisplayType());
            validSmartWatch.setDisplayType("TFT");
            assertEquals("TFT", validSmartWatch.getDisplayType());
            validSmartWatch.setDisplayType("AMOLED");
            assertEquals("AMOLED", validSmartWatch.getDisplayType());

            assertEquals("AMOLED", edgeCaseSmartWatch.getDisplayType());
            edgeCaseSmartWatch.setDisplayType("LED");
            assertEquals("LED", edgeCaseSmartWatch.getDisplayType());
            edgeCaseSmartWatch.setDisplayType("AMOLED");
            assertEquals("AMOLED", edgeCaseSmartWatch.getDisplayType());

            assertEquals("LCD", invalidSmartWatch.getDisplayType());
            invalidSmartWatch.setDisplayType("OLED");
            assertEquals("LCD", invalidSmartWatch.getDisplayType());
            invalidSmartWatch.setDisplayType("LED");
            assertEquals("LED", invalidSmartWatch.getDisplayType());
        }
    }

    /**
     * 测试 connectToInternet 方法并验证它的返回值是否符合预期。
     * Test the connectToInternet methods and verify that their return values match the expected values.
     *
     * @author Fan Xinkang
     * @since version 1.1
     */
    @Test
    void testConnectToInternet() {
        assertEquals("Connects to the internet via bluetooth.", validSmartWatch.connectToInternet());
        assertEquals("Connects to the internet via bluetooth.", edgeCaseSmartWatch.connectToInternet());
        assertEquals("Connects to the internet via bluetooth.", invalidSmartWatch.connectToInternet());
    }

    /**
     * 测试 toString 方法并验证它的返回值是否符合预期。
     * Test the toString methods and verify that their return values match the expected values.
     *
     * @author Fan Xinkang
     * @since version 1.1
     */
    @Test
    void testToString() {

        String expected = """
            Model Name: 01234567890123456789012345678, Price: $21.0, Manufacturer{Name: 0123456789012345678, Num Employees: 1 employee}, ID: 012345678
            Material: 0123456789012345678, Size: 012345678
            Display Type: AMOLED
            Connects to the internet via bluetooth.
            Insurance Premium: $1.26
            """;
        assertEquals(expected, validSmartWatch.toString());

        expected = """
            Model Name: 012345678901234567890123456789, Price: $20.0, Manufacturer{Name: 01234567890123456789, Num Employees: 1 employee}, ID: 0123456789
            Material: 01234567890123456789, Size: 0123456789
            Display Type: AMOLED
            Connects to the internet via bluetooth.
            Insurance Premium: $1.2
            """;
        assertEquals(expected, edgeCaseSmartWatch.toString());

        expected = """
            Model Name: 012345678901234567890123456789, Price: $20.0, Manufacturer{Name: 01234567890123456789, Num Employees: 1 employee}, ID: unknown
            Material: 01234567890123456789, Size: 0123456789
            Display Type: LCD
            Connects to the internet via bluetooth.
            Insurance Premium: $1.2
            """;
        assertEquals(expected, invalidSmartWatch.toString());
    }

}
/*
 * End of test.models.SmartWatchTest Class.
 * Checked by Fan Xinkang on 2025/04/11.
 */