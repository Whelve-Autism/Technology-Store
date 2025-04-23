//package controller.detail;
//
//import model.*;
//
//import java.io.*;
//import java.util.*;
//
//import static controller.detail.ManufacturerDetail.hasManufacturers;
//
///**
// * 此类用于处理技术设备的数据。
// * This class is used to handle technology device data.
// *
// * @author Fan Xinkang, Xu Shiyi, Lu Siyu
// * @version 3.0
// * @since version 2.0
// */
//public class TechnologyDetail {
//
//    private File file;
//    public static ArrayList<Technology> technologyList;
//
//    /**
//     * 创建 TechnologyDeviceAPI 对象。
//     * Constructor for TechnologyDeviceAPI.
//     *
//     * @param file 文件。
//     *             The file.
//     * @author Fan Xinkang
//     * @since version 3.0
//     */
//    public TechnologyDetail(File file) {
//        this.file = file;
//        technologyList = new ArrayList<>();
//    }
//
//    /*
//      封装。
//      Encapsulation.
//     */
//    public File getFile() {
//        return file;
//    }
//
//    public void setFile(File file) {
//        this.file = file;
//    }
//
//    public static ArrayList<Technology> getTechnologyList() {
//        return technologyList;
//    }
//
//    public void setTechnologyList(ArrayList<Technology> technologyList) {
//        TechnologyDetail.technologyList = technologyList;
//    }
//
//    public static boolean hasTechnologyDevices() {
//        return technologyList != null && !technologyList.isEmpty();
//    }
//
//    public static boolean isValidId(String id) {
//        if (id == null || id.isEmpty() || id.trim().isEmpty()) {
//            return false;
//        }
//
//        for (Technology technology : technologyList) {
//            if (technology.getId().equals(id)) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    public static int getTechnologyIndexById(String id) {
//        for (int i = 0; i < technologyList.size(); i++) {
//            if (technologyList.get(i).getId().equals(id)) {
//                return i;
//            }
//        }
//        return -1;
//    }
//
//    public static int getTechnologyIndexByName(String modelName) {
//        for (int i = 0; i < technologyList.size(); i++) {
//            if (technologyList.get(i).getModelName().equals(modelName)) {
//                return i;
//            }
//        }
//        return -1;
//    }
//
//    public static int getTechnologyIndexByManufacturer(Manufacturer manufacturer) {
//        for (int i = 0; i < technologyList.size(); i++) {
//            if (technologyList.get(i).getManufacturer().equals(manufacturer)) {
//                return i;
//            }
//        }
//        return -1;
//    }
//
//    public static boolean addLaptop(Laptop laptop) {
//        if (!hasManufacturers()) {
//            System.out.println("No manufacturers exist.");
//            return false;
//        }
//
//        if (isExistTechnologyId(laptop.getId())) {
//            System.out.println("Technology ID already exists.");
//            return false;
//        }
//        return technologyList.add(laptop);
//    }
//
//    public static boolean updateLaptop(Laptop laptop) {
//        if (!hasTechnologyDevices()) {
//            System.out.println("No technology devices exist.");
//            return false;
//        }
//
//        if (!isExistTechnologyId(laptop.getId())) {
//            System.out.println("Technology ID does not exist.");
//            return false;
//        }
//        int index = getTechnologyIndexById(laptop.getId());
//        Laptop currentLaptop = (Laptop) technologyList.get(index);
//        String newId = laptop.getId();
//        if (!currentLaptop.getId().equalsIgnoreCase(newId)) {
//            if (isExistTechnologyId(newId)) {
//                System.out.println("Technology ID already exists.");
//                return false;
//            }
//        }
//        technologyList.set(index, laptop);
//        return true;
//    }
//
//    public static void listAllLaptop() {
//        if (!hasTechnologyDevices()) {
//            System.out.println("No technology devices exist.");
//        }
//        StringBuilder builder = new StringBuilder();
//        for (Technology technology : technologyList) {
//            if (technology instanceof Laptop) {
//                builder.append(technology).append("\n");
//            }
//        }
//
//        if (builder.isEmpty()) {
//            System.out.println("No laptops exist.");
//        }
//        System.out.println(builder);
//    }
//
//    public static boolean addTablet(Tablet tablet) {
//        if (!hasManufacturers()) {
//            System.out.println("No manufacturers exist.");
//            return false;
//        }
//
//        if (isExistTechnologyId(tablet.getId())) {
//            System.out.println("Technology ID already exists.");
//            return false;
//        }
//        return technologyList.add(tablet);
//    }
//
//    public static boolean updateTablet(Tablet tablet) {
//        if (!hasTechnologyDevices()) {
//            System.out.println("No technology devices exist.");
//            return false;
//        }
//
//        if (!isExistTechnologyId(tablet.getId())) {
//            System.out.println("Technology ID does not exist.");
//            return false;
//        }
//        int index = getTechnologyIndexById(tablet.getId());
//        Tablet currentTablet = (Tablet) technologyList.get(index);
//        String newId = tablet.getId();
//        if (!currentTablet.getId().equalsIgnoreCase(newId)) { // 修复：添加括号并确保方法名正确
//            if (isExistTechnologyId(newId)) {
//                System.out.println("Technology ID already exists.");
//                return false;
//            }
//        }
//        technologyList.set(index, tablet);
//        return true;
//    }
//
//    public static void listAllTablet() {
//        if (!hasTechnologyDevices()) {
//            System.out.println("No technology devices exist.");
//        }
//        StringBuilder builder = new StringBuilder();
//        for (Technology technology : technologyList) {
//            if (technology instanceof Tablet) {
//                builder.append(technology).append("\n");
//            }
//        }
//
//        if (builder.isEmpty()) {
//            System.out.println("No tablets exist.");
//        }
//        System.out.println(builder);
//    }
//
//    public static boolean addSmartBand(SmartBand smartBand) {
//        if (!hasManufacturers()) {
//            System.out.println("No manufacturers exist.");
//            return false;
//        }
//
//        if (isExistTechnologyId(smartBand.getId())) {
//            System.out.println("Technology ID already exists.");
//            return false;
//        }
//        return technologyList.add(smartBand);
//    }
//
//    public static boolean updateSmartBand(SmartBand smartBand) {
//        if (!hasTechnologyDevices()) {
//            System.out.println("No technology devices exist.");
//            return false;
//        }
//
//        if (!isExistTechnologyId(smartBand.getId())) {
//            System.out.println("Technology ID does not exist.");
//            return false;
//        }
//        int index = getTechnologyIndexById(smartBand.getId());
//        SmartBand currentSmartBand = (SmartBand) technologyList.get(index);
//        String newId = smartBand.getId();
//        if (!currentSmartBand.getId().equalsIgnoreCase(newId)) {
//            if (isExistTechnologyId(newId)) {
//                System.out.println("Technology ID already exists.");
//                return false;
//            }
//        }
//        technologyList.set(index, smartBand);
//        return true;
//    }
//
//    public static void listAllSmartBand() {
//        if (!hasTechnologyDevices()) {
//            System.out.println("No technology devices exist.");
//        }
//        StringBuilder builder = new StringBuilder();
//        for (Technology technology : technologyList) {
//            if (technology instanceof SmartBand) {
//                builder.append(technology).append("\n");
//            }
//        }
//
//        if (builder.isEmpty()) {
//            System.out.println("No smart bands exist.");
//        }
//        System.out.println(builder);
//    }
//
//    public static boolean addSmartWatch(SmartWatch smartWatch) {
//        if (!hasManufacturers()) {
//            System.out.println("No manufacturers exist.");
//            return false;
//        }
//
//        if (isExistTechnologyId(smartWatch.getId())) {
//            System.out.println("Technology ID already exists.");
//            return false;
//        }
//        return technologyList.add(smartWatch);
//    }
//
//    public static boolean updateSmartWatch(SmartWatch smartWatch) {
//        if (!hasTechnologyDevices()) {
//            System.out.println("No technology devices exist.");
//            return false;
//        }
//
//        if (!isExistTechnologyId(smartWatch.getId())) {
//            System.out.println("Technology ID does not exist.");
//            return false;
//        }
//        int index = getTechnologyIndexById(smartWatch.getId());
//        SmartWatch currentSmartWatch = (SmartWatch) technologyList.get(index);
//        String newId = smartWatch.getId();
//        if (!currentSmartWatch.getId().equalsIgnoreCase(newId)) {
//            if (isExistTechnologyId(newId)) {
//                System.out.println("Technology ID already exists.");
//                return false;
//            }
//        }
//        technologyList.set(index, smartWatch);
//        return true;
//    }
//
//    public static void listAllSmartWatch() {
//        if (!hasTechnologyDevices()) {
//            System.out.println("No technology devices exist.");
//        }
//        StringBuilder builder = new StringBuilder();
//        for (Technology technology : technologyList) {
//            if (technology instanceof SmartWatch) {
//                builder.append(technology).append("\n");
//            }
//        }
//
//        if (builder.isEmpty()) {
//            System.out.println("No smart watches exist.");
//        }
//        System.out.println(builder);
//    }
//
//    public static boolean deleteTechnologyById(String id) {
//        if (!hasTechnologyDevices()) {
//            System.out.println("No technology devices exist.");
//            return false;
//        }
//
//        if (!isExistTechnologyId(id)) {
//            System.out.println("Technology ID does not exist.");
//            return false;
//        }
//        technologyList.remove(getTechnologyIndexById(id));
//        return true;
//    }
//
//    public static String listAllTechnologyDevices() {
//        if (!hasTechnologyDevices()) {
//            return "No technology devices exist.";
//        }
//        StringBuilder builder = new StringBuilder();
//        for (Technology technology : technologyList) {
//            builder.append(technology).append("\n");
//        }
//        return builder.toString();
//    }
//
//    public static String listAllTechnologyDevicesByManufacturer(Manufacturer manufacturer) {
//        if (!hasTechnologyDevices()) {
//            return STR."No technology devices exist.";
//        } else {
//            StringBuilder builder = new StringBuilder();
//            for (Technology technology : technologyList) {
//                if (technology.getManufacturer().equals(manufacturer)) {
//                    builder.append(technology).append("\n");
//                }
//            }
//
//            if (builder.isEmpty()) {
//                return STR."No technology devices by \{manufacturer}.";
//            } else {
//                return builder.toString();
//            }
//        }
//    }
//
//    public static String listAllTechnologyDevicesAboveAPrice(double price) {
//        if (!hasTechnologyDevices()) {
//            return STR."No technology devices exist.";
//        } else {
//            StringBuilder builder = new StringBuilder();
//            for (Technology technology : technologyList) {
//                if (technology.getPrice() > price) {
//                    builder.append(technology).append("\n");
//                }
//            }
//
//            if (builder.isEmpty()) {
//                return STR."No technology devices more expensive than \{price}.";
//            } else {
//                return builder.toString();
//            }
//        }
//    }
//
//    public static String listAllTechnologyDevicesBelowAPrice(double price) {
//        if (!hasTechnologyDevices()) {
//            return STR."No technology devices exist.";
//        } else {
//            StringBuilder builder = new StringBuilder();
//            for (Technology technology : technologyList) {
//                if (technology.getPrice() < price) {
//                    builder.append(technology).append("\n");
//                }
//            }
//
//            if (builder.isEmpty()) {
//                return STR."No technology less expensive than \{price}.";
//            } else {
//                return builder.toString();
//            }
//        }
//    }
//
//    public static void listTopFiveExpansiveTechnologyDevices () {
//        if (!hasTechnologyDevices()) {
//            System.out.println("No technology devices exist.");
//        } else {
//            List<Technology> technologyList = new ArrayList<>(TechnologyDetail.getTechnologyList());
//            technologyList.sort(Comparator.comparingDouble(Technology::getPrice));
//            List<Technology> topFiveTechnologyDevices = technologyList.subList(0, Math.min(5, technologyList.size()));
//            System.out.println("Top 5 technology devices:");
//            for (Technology technology : topFiveTechnologyDevices) {
//               System.out.println(technology);
//            }
//        }
//    }
//
//    public static void listTopFiveCheapTechnologyDevices () {
//        if (!hasTechnologyDevices()) {
//            System.out.println("No technology devices exist.");
//        } else {
//            List<Technology> technologyList = new ArrayList<>(TechnologyDetail.getTechnologyList());
//            technologyList.sort(Comparator.comparingDouble(Technology::getPrice));
//            List<Technology> topFiveTechnologyDevices = technologyList.subList(0, Math.min(5, technologyList.size()));
//            System.out.println("Top 5 technology devices:");
//            for (Technology technology : topFiveTechnologyDevices) {
//                System.out.println(technology);
//            }
//        }
//    }
//}