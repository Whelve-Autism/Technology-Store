package models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 此类用于测试 SmartBand 类。
 * This class is used to test the SmartBand class.
 *
 * @author Fan Xinkang
 * @since version 1.1
 */
class SmartBandTest {

    private SmartBand validSmartBand, edgeCaseSmartBand, invalidSmartBand;

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

        validSmartBand = new SmartBand("01234567890123456789012345678", 21, validManufacturer, "012345678", "0123456789012345678", "012345678", true);
        edgeCaseSmartBand = new SmartBand("012345678901234567890123456789", 20, edgeCaseManufacturer, "0123456789", "01234567890123456789", "0123456789", true);
        invalidSmartBand = new SmartBand("0123456789012345678901234567890", 19, invalidManufacturer, "01234567890", "012345678901234567890", "01234567890", false);
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
        validSmartBand = invalidSmartBand = edgeCaseSmartBand = null;
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
         * 测试 getHeartRateMonitor 方法并验证其返回值是否符合预期。
         * Test the getHeartRateMonitor method and verify that its return value matches the expected value.
         *
         * @author Fan Xinkang
         * @since version 1.1
         */
        @Test
        void testGetHeartRateMonitor() {
            assertEquals("Includes Heart Rate Monitor.", validSmartBand.getHeartRateMonitor());
            assertEquals("Includes Heart Rate Monitor.", edgeCaseSmartBand.getHeartRateMonitor());
            assertEquals("No Heart Rate Monitor included.", invalidSmartBand.getHeartRateMonitor());
        }

        /**
         * 测试 getInsurancePremium 方法并验证其返回值是否符合预期。
         * Test the getInsurancePremium method and verify that its return value matches the expected value.
         *
         * @author Fan Xinkang
         * @since version 1.1
         */
        @Test
        void testGetInsurancePremium() {
            assertEquals(21 * 0.07, validSmartBand.getInsurancePremium(), 0.01);
            assertEquals(20 * 0.07, edgeCaseSmartBand.getInsurancePremium(), 0.01);
            assertEquals(20 * 0.07, invalidSmartBand.getInsurancePremium(), 0.01);
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
         * 测试 setHeartRateMonitor 方法并验证其行为是否符合预期。
         * Test the setHeartRateMonitor method and verify that its behavior matches the expected behavior.
         *
         * @author Fan Xinkang
         * @since version 1.1
         */
        @Test
        void testSetHeartRateMonitor() {
            assertTrue(validSmartBand.isHeartRateMonitor());
            validSmartBand.setHeartRateMonitor(false);
            assertFalse(validSmartBand.isHeartRateMonitor());
            validSmartBand.setHeartRateMonitor(true);
            assertTrue(validSmartBand.isHeartRateMonitor());

            assertTrue(edgeCaseSmartBand.isHeartRateMonitor());
            edgeCaseSmartBand.setHeartRateMonitor(false);
            assertFalse(edgeCaseSmartBand.isHeartRateMonitor());
            edgeCaseSmartBand.setHeartRateMonitor(true);
            assertTrue(edgeCaseSmartBand.isHeartRateMonitor());

            assertFalse(invalidSmartBand.isHeartRateMonitor());
            invalidSmartBand.setHeartRateMonitor(true);
            assertTrue(invalidSmartBand.isHeartRateMonitor());
            invalidSmartBand.setHeartRateMonitor(false);
            assertFalse(invalidSmartBand.isHeartRateMonitor());
        }
    }

    /**
     * 测试 heartRateMonitor 属性并验证其行为是否符合预期。
     * Test the heartRateMonitor attribute and verify that its behavior matches the expected behavior.
     *
     * @author Fan Xinkang
     * @since version 1.1
     */
    @Test
    void testHeartRateMonitor() {
        assertTrue(validSmartBand.isHeartRateMonitor());
        assertTrue(edgeCaseSmartBand.isHeartRateMonitor());
        assertFalse(invalidSmartBand.isHeartRateMonitor());
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
        assertEquals("Connects to the internet via Companion App.", validSmartBand.connectToInternet());
        assertEquals("Connects to the internet via Companion App.", edgeCaseSmartBand.connectToInternet());
        assertEquals("Connects to the internet via Companion App.", invalidSmartBand.connectToInternet());
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
            Includes Heart Rate Monitor.
            Connects to the internet via Companion App.
            Insurance Premium: $1.47
            """;
        assertEquals(expected, validSmartBand.toString());

        expected = """
            Model Name: 012345678901234567890123456789, Price: $20.0, Manufacturer{Name: 01234567890123456789, Num Employees: 1 employee}, ID: 0123456789
            Material: 01234567890123456789, Size: 0123456789
            Includes Heart Rate Monitor.
            Connects to the internet via Companion App.
            Insurance Premium: $1.4
            """;
        assertEquals(expected, edgeCaseSmartBand.toString());

        expected = """
            Model Name: 012345678901234567890123456789, Price: $20.0, Manufacturer{Name: 01234567890123456789, Num Employees: 1 employee}, ID: unknown
            Material: 01234567890123456789, Size: 0123456789
            No Heart Rate Monitor included.
            Connects to the internet via Companion App.
            Insurance Premium: $1.4
            """;
        assertEquals(expected, invalidSmartBand.toString());
    }
}
/*
 * End of test.models.SmartBandTest Class.
 * Checked by Fan Xinkang on 2025/04/11.
 */