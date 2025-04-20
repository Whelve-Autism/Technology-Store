package models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 此类用于测试 WearableDevice 类。
 * This class is used to test the WearableDevice class.
 *
 * @author Fan Xinkang
 * @since version 1.1
 */
public class WearableDeviceTest {

    private SmartBand validSmartBand, edgeCaseSmartBand, invalidSmartBand;

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

        validSmartBand = new SmartBand("01234567890123456789012345678", 21, validManufacturer, "012345678", "0123456789012345678", "012345678", true);
        edgeCaseSmartBand = new SmartBand("012345678901234567890123456789", 20, edgeCaseManufacturer, "0123456789", "01234567890123456789", "0123456789", true);
        invalidSmartBand = new SmartBand("0123456789012345678901234567890", 19, invalidManufacturer, "01234567890", "012345678901234567890", "01234567890", false);

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
        validSmartBand = edgeCaseSmartBand = invalidSmartBand = null;
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
         * 测试 getMaterial 方法并验证它们的返回值是否符合预期。
         * Test the getMaterial methods and verify that their return values match the expected values.
         *
         * @author Fan Xinkang
         * @since version 1.1
         */
        @Test
        void testGetMaterial() {

            /*
              智能手环的材料。
              The material of the smart band.
             */
            assertEquals("0123456789012345678", validSmartBand.getMaterial());
            assertEquals("01234567890123456789", edgeCaseSmartBand.getMaterial());
            assertEquals("01234567890123456789", invalidSmartBand.getMaterial());

            /*
              智能手表的材料。
              The material of the smart watch.
             */
            assertEquals("0123456789012345678", validSmartWatch.getMaterial());
            assertEquals("01234567890123456789", edgeCaseSmartWatch.getMaterial());
            assertEquals("01234567890123456789", invalidSmartWatch.getMaterial());
        }

        /**
         * 测试 getSize 方法并验证它们的返回值是否符合预期。
         * Test the getSize methods and verify that their return values match the expected values.
         *
         * @author Fan Xinkang
         * @since version 1.1
         */
        @Test
        void testGetSize() {

            assertEquals("012345678", validSmartBand.getSize());
            assertEquals("0123456789", edgeCaseSmartBand.getSize());
            assertEquals("0123456789", invalidSmartBand.getSize());

            assertEquals("012345678", validSmartWatch.getSize());
            assertEquals("0123456789", edgeCaseSmartWatch.getSize());
            assertEquals("0123456789", invalidSmartWatch.getSize());
        }

        /**
         * 测试 getInsurancePremium 方法并验证它们的返回值是否符合预期。
         * Test the getInsurancePremium methods and verify that their return values match the expected values.
         *
         * @author Fan Xinkang
         * @since version 1.1
         */
        @Test
        void testGetInsurancePremium() {

            assertEquals(21 * 0.07, validSmartBand.getInsurancePremium(), 0.01);
            assertEquals(20 * 0.07, edgeCaseSmartBand.getInsurancePremium(), 0.01);
            assertEquals(20 * 0.07, invalidSmartBand.getInsurancePremium(), 0.01);

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
         * 测试 setMaterial 方法并验证它们的行为是否符合预期。
         * Test the setMaterial methods and verify that their behavior matches the expected behavior.
         *
         * @author Fan Xinkang
         * @since version 1.1
         */
        @Test
        void testSetMaterial() {

            validSmartBand.setMaterial("Steel");
            assertEquals("Steel", validSmartBand.getMaterial());

            validSmartWatch.setMaterial("Steel");
            assertEquals("Steel", validSmartWatch.getMaterial());

            /*
              材料的长度小于20个字符。
              The length of the material is less than 20 characters.
             */
            validSmartBand.setMaterial("0123456789012345678");
            assertEquals("0123456789012345678", validSmartBand.getMaterial());

            validSmartWatch.setMaterial("0123456789012345678");
            assertEquals("0123456789012345678", validSmartWatch.getMaterial());

            /*
              材料的长度等于20个字符。
              The length of the material is equal to 20 characters.
             */
            validSmartBand.setMaterial("01234567890123456789");
            assertEquals("01234567890123456789", validSmartBand.getMaterial());

            validSmartWatch.setMaterial("01234567890123456789");
            assertEquals("01234567890123456789", validSmartWatch.getMaterial());

            /*
              材料的长度大于20个字符。
              The length of the size is greater than 20 characters.
             */
            validSmartBand.setMaterial("123456789012345678901");
            assertEquals("01234567890123456789", validSmartBand.getMaterial());

            validSmartWatch.setMaterial("123456789012345678901");
            assertEquals("01234567890123456789", validSmartWatch.getMaterial());
        }

        /**
         * 测试 setSize 方法并验证它们的行为是否符合预期。
         * Test the setSize methods and verify that their behavior matches the expected behavior.
         *
         * @author Fan Xinkang
         * @since version 1.1
         */
        @Test
        void testSetSize() {

            validSmartBand.setSize("42mm");
            assertEquals("42mm", validSmartBand.getSize());

            validSmartWatch.setSize("44mm");
            assertEquals("44mm", validSmartWatch.getSize());

            /*
              尺寸的长度小于10个字符。
              The length of the size is less than 10 characters.
             */
            validSmartBand.setSize("012345678");
            assertEquals("012345678", validSmartBand.getSize());

            validSmartWatch.setSize("012345678");
            assertEquals("012345678", validSmartWatch.getSize());

            /*
              尺寸的长度等于10个字符。
              The length of the size is equal to 10 characters.
             */
            validSmartBand.setSize("0123456789");
            assertEquals("0123456789", validSmartBand.getSize());
            validSmartWatch.setSize("0123456789");
            assertEquals("0123456789", validSmartWatch.getSize());

            /*
              尺寸的长度大于10个字符。
              The length of the size is greater than 10 characters.
             */
            validSmartBand.setSize("12345678901");
            assertEquals("0123456789", validSmartBand.getSize());

            validSmartWatch.setSize("12345678901");
            assertEquals("0123456789", validSmartWatch.getSize());
        }
    }

    /**
     * 测试 connectToInternet 方法并验证它们的返回值是否符合预期。
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

        assertEquals("Connects to the internet via bluetooth.", validSmartWatch.connectToInternet());
        assertEquals("Connects to the internet via bluetooth.", edgeCaseSmartWatch.connectToInternet());
        assertEquals("Connects to the internet via bluetooth.", invalidSmartWatch.connectToInternet());
    }

    /**
     * 测试 toString() 方法。
     * Test toString() method.
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

        expected = """
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
 * End of test.models.WearableDeviceTest Class.
 * Checked by Fan Xinkang on 2025/04/15.
 */