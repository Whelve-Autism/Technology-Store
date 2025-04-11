package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * 此类用于测试 Tablet 类。
 * This class is used to test the Tablet class.
 *
 * @author Guoqing Lu
 * @since version 0.0
 */
public class TabletTest {

    private Tablet validTablet;
    private Tablet invalidTablet;

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
        invalidTablet = new Tablet("Galaxy Tab S7 version 1 c.09462b", 19, invalidManufacturer, "12345678910", "Snapdragon 8655678920", 19, "Android v1");
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
     * 测试获取有效的操作系统名称。
     * Test getting the valid operating system name.
     *
     * @author Guoqing Lu
     * @since version 0.0
     */
    @Test
    public void testValidOperatingSystem() {
        assertEquals("Android", validTablet.getOperatingSystem());
    }

    /**
     * 测试获取无效的操作系统名称，并验证是否默认为 "Windows OS"。
     * Test getting the invalid operating system name and verify if it defaults to "Windows OS".
     *
     * @author Guoqing Lu
     * @since version 0.0
     */
    @Test
    public void testInvalidOperatingSystem() {
        assertEquals("Windows", invalidTablet.getOperatingSystem()); // Invalid OS should default to "Windows OS"
    }

    /**
     * 测试设置有效的操作系统名称。
     * Test setting the valid operating system name.
     *
     * @author Guoqing Lu
     * @since version 0.0
     */
    @Test
    public void testSetValidOperatingSystem() {
        assertEquals("Android", validTablet.getOperatingSystem());
        validTablet.setOperatingSystem("iPad");
        assertEquals("iPad", validTablet.getOperatingSystem());
        validTablet.setOperatingSystem("Android");
        assertEquals("Android", validTablet.getOperatingSystem());
        validTablet.setOperatingSystem("Chrome");
        assertEquals("Chrome", validTablet.getOperatingSystem());
        validTablet.setOperatingSystem("Windows");
        assertEquals("Windows", validTablet.getOperatingSystem());
        validTablet.setOperatingSystem("Amazon Fire");
        assertEquals("Amazon Fire", validTablet.getOperatingSystem());

    }

    /**
     * 测试设置无效的操作系统名称，并验证是否保持不变。
     * Test setting the invalid operating system name and verify if it remains unchanged.
     *
     * @author Guoqing Lu
     * @since version 0.0
     */
    @Test
    public void testSetInvalidOperatingSystem() {
        assertEquals("Android", validTablet.getOperatingSystem());
        validTablet.setOperatingSystem("iPad12 OS");
        assertEquals("Android", validTablet.getOperatingSystem());
        validTablet.setOperatingSystem("Android OS");
        assertEquals("Android", validTablet.getOperatingSystem());
        validTablet.setOperatingSystem("Chrome OS");
        assertEquals("Android", validTablet.getOperatingSystem());
        validTablet.setOperatingSystem("Windows OS");
        assertEquals("Android", validTablet.getOperatingSystem());
        validTablet.setOperatingSystem("Amazon Fire OS");
        assertEquals("Android", validTablet.getOperatingSystem());
    }

    /**
     * 测试获取有效的操作系统名称。
     * Test getting the valid operating system name.
     *
     * @author Guoqing Lu
     * @since version 0.0
     */
    @Test
    public void testToString() {
        String expected = "Operating System: Android, Insurance Premium: €7.99";
        assertTrue( validTablet.toString().contains(expected));
        expected = "Operating System: Windows, Insurance Premium: €0.2";
        assertTrue( invalidTablet.toString().contains(expected));
    }
}
/*
 * End of test.models.TabletTest Class.
 * Checked by Fan Xinkang on 2025/04/11.
 */