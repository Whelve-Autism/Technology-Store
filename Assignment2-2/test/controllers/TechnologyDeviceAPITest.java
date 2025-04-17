package controllers;

import models.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 此类用于测试 TechnologyDeviceAPI 类。
 * This class is used to test the TechnologyDeviceAPI class.
 *
 * @author Fan Xinkang
 * @version 3.1
 */
class TechnologyDeviceAPITest {
    private Manufacturer apple = new Manufacturer("Apple", 1020);
    private Manufacturer samsung = new Manufacturer("Samsung", 1200);
    private Manufacturer hitachi = new Manufacturer("Hitachi", 1325);
    private Manufacturer tesla = new Manufacturer("Tesla", 3245);

    private TechnologyDeviceAPI populatedDevices = new TechnologyDeviceAPI(new File("technologyDevicesTest.xml"));
    private TechnologyDeviceAPI emptyDevices = new TechnologyDeviceAPI(new File("technologyDevicesemptyTest.xml"));

    /**
     * 在每个测试方法执行前进行初始化操作，确保测试数据的干净状态，避免测试之间的相互影响。
     * Initialize before each test method to ensure the cleanliness of the test data and avoid mutual influence between tests.
     *
     * @author Fan Xinkang
     * @since version 3.1
     */
    @BeforeEach
    void setUp() {
        try {
            populatedDevices.load();
            emptyDevices.load();
        } catch (Exception e){
            System.out.println(e);
        }
    }

    /**
     * 测试 CRUD 方法并验证它们的正确性。
     * Test CRUD methods and verify their correctness.
     *
     * @author Fan Xinkang
     * @since version 3.1
     */
    @Nested
    class CRUDMethods {

        /**
         * 测试添加新设备到空的列表，并验证列表中的设备数量是否正确。
         * Test adding a new device to an empty list and verify the correctness of the number of devices in the list.
         *
         * @author Fan Xinkang
         * @since version 3.1
         */
        @Test
        void addNewTechnologyDevicetoEmpty() {
            assertEquals(0, emptyDevices.numberOfTechnologyDevices());
            Tablet newTab = new Tablet("Galaxy Tab S7", 799.99, tesla, "123456", "Snapdragon 865", 64, "Android");
            emptyDevices.addTechnologyDevice(newTab);
            assertEquals(1, emptyDevices.numberOfTechnologyDevices());
            Tablet newTab2 = new Tablet("Galaxy Tab S8", 799.99, samsung, "123457", "Snapdragon 865", 64, "Android");
            emptyDevices.addTechnologyDevice(newTab2);
            assertEquals(2, emptyDevices.numberOfTechnologyDevices());
        }

        /**
         * 测试添加新设备到 populatedDevices 列表，并验证列表中的设备数量是否正确。
         * Test adding a new device to the populatedDevices list and verify the correctness of the number of devices in the list.
         *
         * @author Fan Xinkang
         * @since version 3.1
         */
        @Test
        void addNewTechnologySameId() {
            assertEquals(0, emptyDevices.numberOfTechnologyDevices());
            Tablet newTab = new Tablet("Galaxy Tab S7", 799.99, tesla, "123456", "Snapdragon 865", 64, "Android");
            emptyDevices.addTechnologyDevice(newTab);
            assertEquals(1, emptyDevices.numberOfTechnologyDevices());
            Tablet newTab2 = new Tablet("Galaxy Tab S8", 799.99, samsung, "123456", "Snapdragon 865", 64, "Android");
            emptyDevices.addTechnologyDevice(newTab2);
            assertEquals(1, emptyDevices.numberOfTechnologyDevices());
        }

        /**
         * 测试通过索引获取设备，并验证获取的设备是否正确。
         * Test getting a device by index and verify that the retrieved device is correct.
         *
         * @author Fan Xinkang
         * @since version 3.1
         */
        @Test
        void getTechnologyByIndex() {
            Technology tech = populatedDevices.getTechnologyByIndex(0);
            assertNotNull(tech);
            assertEquals("A123", tech.getId());
        }

        /**
         * 测试通过 ID 获取设备，并验证获取的设备是否正确。
         * Test getting a device by ID and verify that the retrieved device is correct.
         *
         * @author Fan Xinkang
         * @since version 3.1
         */
        @Test
        void getTechnologyDeviceById() {
            Technology tech = populatedDevices.getTechnologyDeviceById("A123");
            assertNotNull(tech);
            assertEquals("A123", tech.getId());
        }

        /**
         * 测试更新平板电脑，并验证更新后的平板电脑是否正确。
         * Test updating a tablet and verify that the updated tablet is correct.
         *
         * @author Fan Xinkang
         * @since version 3.1
         */
        @Test
        void updateTablet() {
            Technology originalDevice = populatedDevices.getTechnologyDeviceById("T1223");
            assertNotNull(originalDevice, "Original device with ID T1223 should exist");
            System.out.println(STR."Original device: \{originalDevice.getClass().getName()}");

            assertInstanceOf(Tablet.class, originalDevice, "Original device should be a Tablet");

            Tablet updatedTablet = new Tablet("iPad Pro",899.99, tesla,"T1223","Snapdragon 888", 128, "Android");

            assertTrue(populatedDevices.updateTablet("T1223", updatedTablet));

            Tablet retrievedTablet = (Tablet) populatedDevices.getTechnologyDeviceById("T1223");
            assertNotNull(retrievedTablet, "Updated Tablet with ID T1223 should exist");
            assertEquals("iPad Pro", retrievedTablet.getModelName(), "Model name should be updated");
            assertEquals(899.99, retrievedTablet.getPrice(), "Price should be updated");
            assertEquals(128, retrievedTablet.getStorage(), "Storage should be updated");
            assertEquals("Android", retrievedTablet.getOperatingSystem(), "Operating system should be updated");
            assertEquals("T1223", retrievedTablet.getId(), "ID should remain the same");
        }

        /**
         * 测试更新智能手环，并验证更新后的智能手环是否正确。
         * Test updating a smart band and verify that the updated smart band is correct.
         *
         * @author Fan Xinkang
         * @since version 3.1
         */
        @Test
        void updateSmartBand() {
            SmartBand newSmartBand = new SmartBand("Smart Band", 99.99, tesla, "A123", "Aluminium", "42mm", true);
            assertTrue(populatedDevices.updateSmartBand("A123", newSmartBand));
            SmartBand updatedSmartBand = (SmartBand) populatedDevices.getTechnologyDeviceById("A123");
            assertNotNull(updatedSmartBand);
            assertEquals("Smart Band", updatedSmartBand.getModelName());
            assertEquals(99.99, updatedSmartBand.getPrice());
            assertEquals("Aluminium", updatedSmartBand.getMaterial());
            assertEquals("42mm", updatedSmartBand.getSize());
            assertTrue(updatedSmartBand.isHeartRateMonitor());
        }

        /**
         * 测试更新智能手表，并验证更新后的智能手表是否正确。
         * Test updating a smart watch and verify that the updated smart watch is correct.
         *
         * @author Fan Xinkang
         * @since version 3.1
         */
        @Test
        void updateSmartWatch() {
            SmartWatch newSmartWatch = new SmartWatch("Smart Watch", 199.99, tesla, "W1234", "Aluminium", "42mm", "LCD");
            assertTrue(populatedDevices.updateSmartWatch("W1234", newSmartWatch));
            SmartWatch updatedSmartWatch = (SmartWatch) populatedDevices.getTechnologyDeviceById("W1234");
            assertNotNull(updatedSmartWatch);
            assertEquals("Smart Watch", updatedSmartWatch.getModelName());
            assertEquals(199.99, updatedSmartWatch.getPrice());
            assertEquals("Aluminium", updatedSmartWatch.getMaterial());
            assertEquals("42mm", updatedSmartWatch.getSize());
            assertEquals("LCD", updatedSmartWatch.getDisplayType());
        }

        /**
         * 测试通过索引删除设备，并验证删除后的设备数量是否正确。
         * Test deleting a device by index and verify that the number of devices after deletion is correct.
         *
         * @author Fan Xinkang
         * @since version 3.1
         */
        @Test
        void deleteTechnologyByIndex() {
            Technology technology = populatedDevices.deleteTechnologyByIndex(0);
            assertNotNull(technology);
            assertEquals("A123", technology.getId());
            assertNull(populatedDevices.getTechnologyDeviceById("A123"));

            Technology newFirstTech = populatedDevices.getTechnologyByIndex(0);
            assertNotNull(newFirstTech);
            assertEquals("W1234", newFirstTech.getId());
        }

        /**
         * 测试通过 ID 删除设备，并验证删除后的设备数量是否正确。
         * Test deleting a device by ID and verify that the number of devices after deletion is correct.
         *
         * @author Fan Xinkang
         * @since version 3.1
         */
        @Test
        void deleteTechnologyById() {
            Technology tech = populatedDevices.deleteTechnologyById("W1234");
            assertNotNull(tech);
            assertEquals("W1234", tech.getId());
            assertNull(populatedDevices.getTechnologyDeviceById("W1234"));
        }
    }

    /**
     * 测试列表方法并验证它们的正确性。
     * Test listing methods and verify their correctness.
     *
     * @author Fan Xinkang
     * @since version 3.1
     */
    @Nested
    class ListingMethods {

        /**
         * 测试当 ArrayList 为空时，listAllTechnologyDevices 方法是否返回正确的信息。
         * Test whether listAllTechnologyDevices method returns correct information when ArrayList is empty.
         *
         * @author Fan Xinkang
         * @since version 3.1
         */
        @Test
        void listAllReturnsNoTechnologyStoredWhenArrayListIsEmpty() {
            assertEquals(0, emptyDevices.numberOfTechnologyDevices());
            assertTrue(emptyDevices.listAllTechnologyDevices().toLowerCase().contains("no technology devices"));
        }

        /**
         * 测试当 ArrayList 不为空时，listAllTechnologyDevices 方法是否返回正确的信息。
         * Test whether listAllTechnologyDevices method returns correct information when ArrayList is not empty.
         *
         * @author Fan Xinkang
         * @since version 3.1
         */
        @Test
        void listAllReturnsTechnologyDevicesStoredWhenArrayListHasTechnologyDevicesStored() {
            assertEquals(4, populatedDevices.numberOfTechnologyDevices());
            String populatedDeviceStr = populatedDevices.listAllTechnologyDevices();

            assertTrue(populatedDeviceStr.contains("ID: A123"));
            assertTrue(populatedDeviceStr.contains("ID: W1234"));
            assertTrue(populatedDeviceStr.contains("ID: T1223"));
            assertTrue(populatedDeviceStr.contains("ID: W3535"));
        }

        /**
         * 测试 listAllTablets 方法是否返回正确的信息。
         * Test whether listAllTablets method returns correct information.
         *
         * @author Fan Xinkang
         * @since version 3.1
         */
        @Test
        void listAllTablets() {
            String tabletsStr = populatedDevices.listAllTablets();
            assertTrue(tabletsStr.contains("ID: T1223"));
            assertFalse(tabletsStr.contains("ID: W1234"));
            assertFalse(tabletsStr.contains("ID: A123"));
            assertFalse(tabletsStr.contains("ID: W3535"));
        }

        /**
         * 测试 listAllTablets 方法是否返回正确的信息。
         * Test whether listAllTablets method returns correct information.
         *
         * @author Fan Xinkang
         * @since version 3.1
         */
        @Test
        void listAllSmartBands() {
            String smartBandsStr = populatedDevices.listAllSmartBands();
            assertTrue(smartBandsStr.contains("ID: A123"));
            assertFalse(smartBandsStr.contains("ID: W3535"));
            assertFalse(smartBandsStr.contains("ID: W1234"));
            assertFalse(smartBandsStr.contains("ID: T1223"));
        }

        /**
         * 测试 listAllSmartWatches 方法是否返回正确的信息。
         * Test whether listAllSmartWatches method returns correct information.
         *
         * @author Fan Xinkang
         * @since version 3.1
         */
        @Test
        void listAllSmartWatches() {
            String smartWatchesStr = populatedDevices.listAllSmartWatches();
            assertTrue(smartWatchesStr.contains("ID: W1234"));
            assertFalse(smartWatchesStr.contains("ID: A123"));
        }

        /**
         * 测试 listAllTechDevicesByChosenManufacturer 方法是否返回正确的信息。
         * Test whether listAllTechDevicesByChosenManufacturer method returns correct information.
         *
         * @author Fan Xinkang
         * @since version 3.1
         */
        @Test
        void listAllTechDevicesByChosenManufacturer() {
            Manufacturer samsung = new Manufacturer("Samsung", 1200);
            String devicesStr = populatedDevices.listAllTechDevicesByChosenManufacturer(samsung);
            assertTrue(devicesStr.contains("ID: A123"));
            assertTrue(devicesStr.contains("ID: W1234"));
            assertTrue(devicesStr.contains("W3535"));
        }

        /**
         * 测试 listAllTabletsByOperatingSystem 方法是否返回正确的信息。
         * Test whether listAllTabletsByOperatingSystem method returns correct information.
         *
         * @author Fan Xinkang
         * @since version 3.1
         */
        @Test
        void listAllTabletsByOperatingSystem() {
            String tabletsStr = populatedDevices.listAllTabletsByOperatingSystem("Android");
            assertTrue(tabletsStr.contains("ID: T1223"));
            assertFalse(tabletsStr.contains("ID: W1234"));
            assertFalse(tabletsStr.contains("ID: A123"));
            assertFalse(tabletsStr.contains("ID: W3535"));
        }

        /**
         * 测试 listAllTechnologyAbovePrice 方法是否返回正确的信息。
         * Test whether listAllTechnologyAbovePrice method returns correct information.
         *
         * @author Fan Xinkang
         * @since version 3.1
         */
        @Test
        void listAllTechnologyAbovePrice() {
            String devicesStr = populatedDevices.listAllTechnologyAbovePrice(100);
            assertTrue(devicesStr.contains("ID: A123"));
            assertTrue(devicesStr.contains("ID: W1234"));
            assertTrue(devicesStr.contains("ID: T1223"));
            assertFalse(devicesStr.contains("ID: W3535"));
            assertFalse(devicesStr.contains("No technology more expensive than 100.0"));
        }

        /**
         * 测试 listAllTechnologyBelowPrice 方法是否返回正确的信息。
         * Test whether listAllTechnologyBelowPrice method returns correct information.
         *
         * @author Fan Xinkang
         * @since version 3.1
         */
        @Test
        void listAllTechnologyBelowPrice() {
            String devicesStr = populatedDevices.listAllTechnologyBelowPrice(100);
            assertFalse(devicesStr.contains("ID: A123"));
            assertFalse(devicesStr.contains("ID: W1234"));
            assertFalse(devicesStr.contains("ID: T1223"));
            assertTrue(devicesStr.contains("ID: W3535"));
            assertFalse(devicesStr.contains("No technology less expensive than 100.0"));
        }
    }

    /**
     * 测试报告方法并验证它们的正确性。
     * Test reporting methods and verify their correctness.
     *
     * @author Fan Xinkang
     * @since version 3.1
     */
    @Nested
    class ReportingMethods {

        /**
         * 测试 numberOfTechnologyByChosenManufacturer 方法是否返回正确的信息。
         * Test whether numberOfTechnologyByChosenManufacturer method returns correct information.
         *
         * @author Fan Xinkang
         * @since version 3.1
         */
        @Test
        void numberOfTechnologyByChosenManufacturer() {
            int count = populatedDevices.numberOfTechnologyByChosenManufacturer(tesla);
            assertEquals(0, count);
        }

        /**
         * 测试 numberOfTablets 方法是否返回正确的信息。
         * Test whether numberOfTablets method returns correct information.
         *
         * @author Fan Xinkang
         * @since version 3.1
         */
        @Test
        void numberOfTablets() {
            int count = populatedDevices.numberOfTablets();
            assertEquals(1, count);
        }

        /**
         * 测试 numberOfSmartBands 方法是否返回正确的信息。
         * Test whether numberOfSmartBands method returns correct information.
         *
         * @author Fan Xinkang
         * @since version 3.1
         */
        @Test
        void numberOfSmartBands() {
            int count = populatedDevices.numberOfSmartBands();
            assertEquals(1, count);
        }

        /**
         * 测试 numberOfSmartWatch 方法是否返回正确的信息。
         * Test whether numberOfSmartWatch method returns correct information.
         *
         * @author Fan Xinkang
         * @since version 3.1
         */
        @Test
        void numberOfSmartWatch() {
            int count = populatedDevices.numberOfSmartWatch();
            assertEquals(2, count);
        }

        /**
         * 测试 topFiveMostExpensiveTechnology 方法是否返回正确的信息。
         * Test whether topFiveMostExpensiveTechnology method returns correct information.
         *
         * @author Fan Xinkang
         * @since version 3.1
         */
        @Test
        void topFiveMostExpensiveTechnology() {
            List<Technology> topFive = populatedDevices.topFiveMostExpensiveTechnology();
            assertEquals(4, topFive.size());
            for (int i = 0; i < topFive.size(); i++) {
                Technology tech = topFive.get(i);
                if (i == 0) {
                    assertEquals("T1223", tech.getId());
                } else if (i == 1) {
                    assertEquals("W1234", tech.getId());
                } else if (i == 2) {
                    assertEquals("A123", tech.getId());
                } else if (i == 3) {
                    assertEquals("W3535", tech.getId());
                }
            }
        }

        /**
         * 测试 topFiveMostExpensiveTablet 方法是否返回正确的信息。
         * Test whether topFiveMostExpensiveTablet method returns correct information.
         *
         * @author Fan Xinkang
         * @since version 3.1
         */
        @Test
        void topFiveMostExpensiveTablet() {
            List<Technology> topFive = populatedDevices.topFiveMostExpensiveTablet();
            assertEquals(1, topFive.size());
            for (int i = 0; i < topFive.size(); i++) {
                Technology tech = topFive.get(i);
                if (i == 0) {
                    assertEquals("T1223", tech.getId());
                }
            }
        }

        /**
         * 测试 topFiveMostExpensiveSmartBand 方法是否返回正确的信息。
         * Test whether topFiveMostExpensiveSmartBand method returns correct information.
         *
         * @author Fan Xinkang
         * @since version 3.1
         */
        @Test
        void topFiveMostExpensiveSmartBand() {
            List<Technology> topFive = populatedDevices.topFiveMostExpensiveSmartBand();
            assertEquals(1, topFive.size());
            for (int i = 0; i < topFive.size(); i++) {
                Technology tech = topFive.get(i);
                if (i == 0) {
                    assertEquals("A123", tech.getId());
                }
            }
        }

        /**
         * 测试 topFiveMostExpensiveSmartWatch 方法是否返回正确的信息。
         * Test whether topFiveMostExpensiveSmartWatch method returns correct information.
         *
         * @author Fan Xinkang
         * @since version 3.1
         */
        @Test
        void topFiveMostExpensiveSmartWatch() {
            List<Technology> topFive = populatedDevices.topFiveMostExpensiveSmartWatch();
            assertEquals(2, topFive.size());
            for (int i = 0; i < topFive.size(); i++) {
                Technology tech = topFive.get(i);
                if (i == 0) {
                    assertEquals("W1234", tech.getId());
                } else if (i == 1) {
                    assertEquals("W3535", tech.getId());
                }
            }
        }

        /**
         * 测试 swapTechnology 方法是否返回正确的信息。
         * Test whether swapTechnology method returns correct information.
         *
         * @author Fan Xinkang
         * @since version 3.1
         */
        @Test
        void swapTechnology() {
            Technology tech1 = populatedDevices.getTechnologyByIndex(0);
            Technology tech2 = populatedDevices.getTechnologyByIndex(1);

            populatedDevices.swapTechnology(populatedDevices.getTechnologyList(), 0, 1);
            assertEquals(tech1.getId(), populatedDevices.getTechnologyByIndex(1).getId());
            assertEquals(tech2.getId(), populatedDevices.getTechnologyByIndex(0).getId());
            assertEquals(tech1.getPrice(), populatedDevices.getTechnologyByIndex(1).getPrice());
            assertEquals(tech2.getPrice(), populatedDevices.getTechnologyByIndex(0).getPrice());
            assertEquals(tech1.getManufacturer(), populatedDevices.getTechnologyByIndex(1).getManufacturer());
            assertEquals(tech2.getManufacturer(), populatedDevices.getTechnologyByIndex(0).getManufacturer());
            assertEquals(tech1.getModelName(), populatedDevices.getTechnologyByIndex(1).getModelName());
            assertEquals(tech2.getModelName(), populatedDevices.getTechnologyByIndex(0).getModelName());
        }
    }

    /**
     * 测试搜索方法并验证它们的正确性。
     * Test searching methods and verify their correctness.
     *
     * @author Fan Xinkang
     * @since version 3.1
     */
    @Nested
    class SearchingMethods {

        /**
         * 测试 getTechnologyDeviceById 方法是否返回正确的信息。
         * Test whether getTechnologyDeviceById method returns correct information.
         *
         * @author Fan Xinkang
         * @since version 3.1
         */
        @Test
        void getTechnologyDeviceByIdReturnsNullWhenIdDoesNotExist() {
            assertNull(populatedDevices.getTechnologyDeviceById("123"));
        }

        /**
         * 测试 getTechnologyDeviceById 方法是否返回正确的信息。
         * Test whether getTechnologyDeviceById method returns correct information.
         *
         * @author Fan Xinkang
         * @since version 3.1
         */
        @Test
        void getTechnologyDeviceByIdReturnsTechnologyDeviceWhenIdExists() {
            Technology tech = populatedDevices.getTechnologyDeviceById("W1234");
            assertNotNull(tech);
            assertEquals("W1234", tech.getId());
        }

        /**
         * 测试 getTechnologyByIndex 方法是否返回正确的信息。
         * Test whether getTechnologyByIndex method returns correct information.
         *
         * @author Fan Xinkang
         * @since version 3.1
         */
        @Test
        void getTechnologyByIndexReturnsNullWhenIndexIsOutOfBounds() {
            assertNull(populatedDevices.getTechnologyByIndex(4));
        }

        /**
         * 测试 getTechnologyByIndex 方法是否返回正确的信息。
         * Test whether getTechnologyByIndex method returns correct information.
         *
         * @author Fan Xinkang
         * @since version 3.1
         */
        @Test
        void getTechnologyByIndexReturnsTechnologyDeviceWhenIndexExists() {
            Technology tech = populatedDevices.getTechnologyByIndex(1);
            assertNotNull(tech);
            assertEquals("W1234", tech.getId());
        }
    }

    /**
     * 测试排序方法并验证它们的正确性。
     * Test sorting methods and verify their correctness.
     *
     * @author Fan Xinkang
     * @since version 3.1
     */
    @Nested
    class SortingMethods {

        /**
         * 测试 sortByPriceAscending 方法是否重新排序列表。
         * Test whether sortByPriceAscending method reorders the list.
         *
         * @author Fan Xinkang
         * @since version 3.1
         */
        @Test
        void sortByPriceAscendingReOrdersList() {
            assertEquals(4, populatedDevices.numberOfTechnologyDevices());
            assertEquals("smart watch1", populatedDevices.getTechnologyByIndex(0).getModelName());
            assertEquals("Smart Watch 12", populatedDevices.getTechnologyByIndex(1).getModelName());
            assertEquals("IPad 123", populatedDevices.getTechnologyByIndex(2).getModelName());
            assertEquals("HiTech Watch", populatedDevices.getTechnologyByIndex(3).getModelName());

            populatedDevices.sortByPriceAscending();
            assertEquals("HiTech Watch", populatedDevices.getTechnologyByIndex(0).getModelName());
            assertEquals("smart watch1", populatedDevices.getTechnologyByIndex(1).getModelName());
            assertEquals("Smart Watch 12", populatedDevices.getTechnologyByIndex(2).getModelName());
            assertEquals("IPad 123", populatedDevices.getTechnologyByIndex(3).getModelName());
        }

        /**
         * 测试 sortByPriceAscending 方法是否正确排序。
         * Test whether sortByPriceAscending method sorts correctly.
         *
         * @author Fan Xinkang
         * @since version 3.1
         */
        @Test
        void sortByCostDescendingReOrdersList() {
            assertEquals(4, populatedDevices.numberOfTechnologyDevices());
            assertEquals("smart watch1", populatedDevices.getTechnologyByIndex(0).getModelName());
            assertEquals("Smart Watch 12", populatedDevices.getTechnologyByIndex(1).getModelName());
            assertEquals("IPad 123", populatedDevices.getTechnologyByIndex(2).getModelName());
            assertEquals("HiTech Watch", populatedDevices.getTechnologyByIndex(3).getModelName());

            populatedDevices.sortByPriceDescending();
            assertEquals("IPad 123", populatedDevices.getTechnologyByIndex(0).getModelName());
            assertEquals("Smart Watch 12", populatedDevices.getTechnologyByIndex(1).getModelName());
            assertEquals("smart watch1", populatedDevices.getTechnologyByIndex(2).getModelName());
            assertEquals("HiTech Watch", populatedDevices.getTechnologyByIndex(3).getModelName());
        }

        /**
         * 测试 sortByPriceAscending 方法在空列表时是否不会崩溃。
         * Test whether sortByPriceAscending method does not crash when list is empty.
         *
         * @author Fan Xinkang
         * @since version 3.1
         */
        @Test
        void sortByPriceAscendingDoesntCrashWhenListIsEmpty() {
            assertEquals(0, emptyDevices.numberOfTechnologyDevices());
            emptyDevices.sortByPriceAscending();
        }

        /**
         * 测试 sortByPriceDescending 方法在空列表时是否不会崩溃。
         * Test whether sortByPriceDescending method does not crash when list is empty.
         *
         * @author Fan Xinkang
         * @since version 3.1
         */
        @Test
        void sortByPriceDescendingDoesntCrashWhenListIsEmpty() {
            assertEquals(0, emptyDevices.numberOfTechnologyDevices());
            emptyDevices.sortByPriceDescending();
        }
    }
}
/*
 * End of test.controllers.TechnologyDeviceAPITest Class.
 * Checked by Fan Xinkang on 2025/04/16.
 */