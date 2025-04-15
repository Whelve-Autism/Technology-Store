package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 此类用于对 Technology 类进行测试。
 * This class is used to test the Technology class.
 *
 * @author Guoqing Lu
 * @since version 0.0
 */
class TechnologyTest {

    private Tablet validTablet, invalidTablet;

    /**
     * 在每个测试方法执行前进行初始化操作，确保测试数据的干净状态，避免测试之间的相互影响。
     * Initialize before each test method to ensure the cleanliness of the test data and avoid mutual influence between tests.
     *
     * @author Guoqing Lu
     * @since version 0.0
     */
    @org.junit.jupiter.api.BeforeEach
    public void setUp() {
        Manufacturer manufacturer = new Manufacturer("Samsung", 333);
        Manufacturer invalidManufacturer = new Manufacturer("ABCDEFGHIJKLMNOPQRSTU", 0);
        validTablet = new Tablet("Galaxy Tab S7", 799.99, manufacturer, "123456", "Snapdragon 865", 64, "Android");
        invalidTablet = new Tablet("Galaxy Tab S7 version 1 c.09462b", 19, invalidManufacturer, "12345678910", "Snapdragon 865", 64, "Android");
    }

    /**
     * 在每个测试方法执行完成后进行清理工作，确保测试环境的干净状态，避免测试之间的相互影响。
     * Clean up after the completion of each test method to ensure the cleanliness of the test environment and avoid mutual influence between tests.
     *
     * @author Guoqing Lu, Fan Xinkang
     * @since version 0.0
     */
    @org.junit.jupiter.api.AfterEach
    void tearDown() {
        validTablet = invalidTablet = null;
    }

    /**
     * 测试 getModelName() 方法。
     * Test the getModelName() method.
     *
     * @author Guoqing Lu
     * @since version 0.0
     */
    @Test
    public void testGetModelName() {
        assertEquals("Galaxy Tab S7", validTablet.getModelName());
        assertEquals("Galaxy Tab S7 version 1 c.0946", invalidTablet.getModelName());
    }

    /**
     * 测试 setModelName() 方法。
     * Test the setModelName() method.
     *
     * @author Guoqing Lu
     * @since version 0.0
     */
    @Test
    public void testSetModelName() {
        validTablet.setModelName("iPad Pro");
        assertEquals("iPad Pro", validTablet.getModelName());
        validTablet.setModelName("Galaxy Tab S7 version 1 c.09462b");
        assertEquals("iPad Pro", validTablet.getModelName());
    }

    /**
     * 测试 getPrice() 方法。
     * Test the getPrice() method.
     *
     * @author Guoqing Lu
     * @since version 0.0
     */
    @Test
    public void testGetPrice() {
        assertEquals(799.99, validTablet.getPrice(), 0.01);
        assertEquals(20, invalidTablet.getPrice(), 0.01);
    }

    /**
     * 测试 setPrice() 方法。
     * Test the setPrice() method.
     *
     * @author Guoqing Lu
     * @since version 0.0
     */
    @Test
    public void testSetPrice() {
        validTablet.setPrice(899.99);
        assertEquals(899.99, validTablet.getPrice(), 0.01);
        validTablet.setPrice(19);
        assertEquals(899.99, validTablet.getPrice(), 0.01);
    }

    /**
     * 测试 getManufacturer() 方法。
     * Test the getManufacturer() method.
     *
     * @author Guoqing Lu
     * @since version 0.0
     */
    @Test
    public void testGetManufacturer() {
        assertEquals("Samsung", validTablet.getManufacturer().getManufacturerName());
        assertEquals("ABCDEFGHIJKLMNOPQRST", invalidTablet.getManufacturer().getManufacturerName());
    }

    /**
     * 测试 setManufacturer() 方法。
     * Test the setManufacturer() method.
     *
     * @author Guoqing Lu
     * @since version 0.0
     */
    @Test
    public void testSetManufacturer() {
        Manufacturer newManufacturer = new Manufacturer("Apple", 1200);
        validTablet.setManufacturer(newManufacturer);
        assertEquals("Apple", validTablet.getManufacturer().getManufacturerName());
    }

    /**
     * 测试 getId() 方法。
     * Test the getId() method.
     *
     * @author Guoqing Lu
     * @since version 0.0
     */
    @Test
    public void testGetId() {
        assertEquals("123456", validTablet.getId());
        assertEquals("unknown", invalidTablet.getId());
    }

    /**
     * 测试 setId() 方法。
     * Test the setId() method.
     *
     * @author Guoqing Lu
     * @since version 0.0
     */
    @Test
    public void testSetId() {
        validTablet.setId("789012");
        assertEquals("789012", validTablet.getId());
        validTablet.setId("789012ABCDE");
        assertEquals("789012", validTablet.getId());
    }

    /**
     * 测试 toString() 方法。
     * Test the toString() method.
     *
     * @author Guoqing Lu
     * @since version 0.0
     */
    @Test
    public void testToString() {
        String expected = """
                Model Name: Galaxy Tab S7, Price: $799.99, Manufacturer{Name: Samsung, Num Employees: 333 employees}, ID: 123456
                Storage: 64GB, Processor: Snapdragon 865
                Operating System: Android
                Connects to the internet via Wi-Fi
                Insurance Premium: $7.9999
                """;
        assertTrue( validTablet.toString().contains(expected));
        expected = """
                Model Name: Galaxy Tab S7 version 1 c.0946, Price: $20.0, Manufacturer{Name: ABCDEFGHIJKLMNOPQRST, Num Employees: 1 employee}, ID: unknown
                Storage: 64GB, Processor: Snapdragon 865
                Operating System: Android
                Connects to the internet via Wi-Fi
                Insurance Premium: $0.2
                """;
        assertTrue( invalidTablet.toString().contains(expected));
    }
}
/*
 * End of test.models.TechnologyTest Class.
 * Checked by Fan Xinkang on 2025/04/11.
 */