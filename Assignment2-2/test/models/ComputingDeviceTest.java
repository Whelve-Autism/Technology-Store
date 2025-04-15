package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * 此类用于测试 ComputingDevice 类。
 * This class is used to test the ComputingDevice class.
 *
 * @author Guoqing Lu, Fan Xinkang
 * @version 1.1
 * @since version 0.0
 */
public class ComputingDeviceTest {

    private Tablet validTablet, invalidTablet;

    /**
     * 在每个测试方法执行前进行初始化操作，确保测试数据的干净状态，避免测试之间的相互影响。
     * Initialize before each test method to ensure the cleanliness of the test data and avoid mutual influence between tests.
     *
     * @author Guoqing Lu
     * @since version 0.0
     */
    @Before
    public void setUp() {
        Manufacturer manufacturer = new Manufacturer("Samsung", 333);
        Manufacturer invalidManufacturer = new Manufacturer("ABCDEFGHIJKLMNOPQRSTU", 0);
        validTablet = new Tablet("Galaxy Tab S7", 799.99, manufacturer, "123456", "Snapdragon 865", 64, "Android");
        invalidTablet = new Tablet("Galaxy Tab S7 version 1 c.09462b", 19, invalidManufacturer, "12345678910", "Snapdragon 8655678920", 19, "Android");
    }

    /**
     * 在每个测试方法执行完成后进行清理工作，确保测试环境的干净状态，避免测试之间的相互影响。
     * Clean up after the completion of each test method to ensure the cleanliness of the test environment and avoid mutual influence between tests.
     *
     * @author Guoqing Lu, Fan Xinkang
     * @since version 0.0
     */
    @After
    public void tearDown() {
        validTablet = invalidTablet = null;
    }

    /**
     * 测试 getProcessor() 方法，验证获取处理器型号是否正确。
     * Test the getProcessor() method to verify that the processor model is obtained correctly.
     *
     * @author Guoqing Lu
     * @since version 0.0
     */
    @Test
    public void testGetProcessor() {
        assertEquals("Snapdragon 865", validTablet.getProcessor());
        assertEquals("Snapdragon 865567892", invalidTablet.getProcessor());
    }

    /**
     * 测试 setProcessor() 方法，验证设置处理器型号是否正确。
     * Test the setProcessor() method to verify that the processor model is set correctly.
     *
     * @author Guoqing Lu
     * @since version 0.0
     */
    @Test
    public void testSetProcessor() {
        validTablet.setProcessor("Snapdragon 888");
        assertEquals("Snapdragon 888", validTablet.getProcessor());
        validTablet.setProcessor("Snapdragon 8655678920");
        assertEquals("Snapdragon 888", validTablet.getProcessor());
    }

    /**
     * 测试 getStorage() 方法，验证获取存储容量是否正确。
     * Test the getStorage() method to verify that the storage capacity is obtained correctly.
     *
     * @author Guoqing Lu
     * @since version 0.0
     */
    @Test
    public void testGetStorage() {
        assertEquals(64, validTablet.getStorage());
        assertEquals(8, invalidTablet.getStorage());
    }

    /**
     * 测试 setStorage() 方法，验证设置存储容量是否正确。
     * Test the setStorage() method to verify that the storage capacity is set correctly.
     *
     * @author Guoqing Lu
     * @since version 0.0
     */
    @Test
    public void testSetStorage() {
        validTablet.setStorage(128);
        assertEquals(128, validTablet.getStorage());
        validTablet.setStorage(127);
        assertEquals(128, validTablet.getStorage());
        validTablet.setStorage(256);
        assertEquals(128, validTablet.getStorage());
    }

    /**
     * 测试 toString() 方法，验证输出的字符串是否包含处理器型号和存储容量。
     * Test the toString() method to verify that the output string contains the processor model and storage capacity.
     *
     * @author Guoqing Lu
     * @since version 0.0
     */
    @Test
    public void testToString() {
        String expected = "Storage: 64GB, Processor: Snapdragon 865";
        assertTrue( validTablet.toString().contains(expected));
        expected = "Storage: 8GB, Processor: Snapdragon 865567892";
        assertTrue( invalidTablet.toString().contains(expected));
    }
}
/*
 * End of test.models.ComputingDeviceTest Class.
 * Checked by Fan Xinkang on 2025/04/15.
 */