package controllers;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import models.*;

import utils.ISerializer;

import utils.Utilities;

import java.io.*;
import java.util.*;

/**
 * 此类用于处理技术设备的数据。
 * This class is used to handle technology device data.
 *
 * @author Fan Xinkang, Xu Shiyi, Lu Siyu
 * @version 3.0
 * @since version 2.0
 */
public class TechnologyDeviceAPI implements ISerializer {

    private File file;
    private ArrayList<Technology> technologyList;

    /**
     * 创建 TechnologyDeviceAPI 对象。
     * Constructor for TechnologyDeviceAPI.
     *
     * @param file 文件。
     *             The file.
     * @author Fan Xinkang
     * @since version 3.0
     */
    public TechnologyDeviceAPI(File file) {
        this.file = file;
        this.technologyList = new ArrayList<>();
    }

    /*
      封装。
      Encapsulation.
     */
    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public ArrayList<Technology> getTechnologyList() {
        return technologyList;
    }

    public void setTechnologyList(ArrayList<Technology> technologyList) {
        this.technologyList = technologyList;
    }

    /**
     * 添加技术产品。
     * Add a technology device.
     *
     * @param technology 科技产品。
     *                   The technology device.
     * @return 添加的结果。
     *         The result of adding.
     * @author Fan Xinkang
     * @since version 3.0
     */
    public boolean addTechnologyDevice(Technology technology) {
        if (technology == null || !isValid(technology.getId()) || !isValidId(technology.getId())) {
            return false;
        }
        technologyList.add(technology);
        return true;
    }

    /**
     * 根据索引获取技术产品。
     * Obtain technical products according to the index.
     *
     * @param index 索引。
     *              The index.
     * @return 科技产品。
     *         The technology device.
     * @author Fan Xinkang
     * @since version 3.0
     */
    public Technology getTechnologyByIndex(int index) {
        if (Utilities.isValidIndex(technologyList, index)) {
            return technologyList.get(index);
        } else {
            return null;
        }
    }

    /**
     * 根据 ID 获取技术设备。
     * Obtain a technology device by ID.
     *
     * @param id ID.
     * @return 科技产品。
     *         The technology device.
     * @author Fan Xinkang
     * @since version 3.0
     */
    public Technology getTechnologyDeviceById(String id) {
        for (Technology device : technologyList) {
            if (device.getId().equals(id)) {
                return device;
            }
        }
        return null;
    }

    /**
     * 根据 ID 更新平板电脑。
     * Update a tablet by ID.
     *
     * @param id ID.
     * @param tablet 平板电脑。
     *               The tablet.
     * @return 更新结果。
     *         The result of updating.
     * @author Fan Xinkang
     * @since version 3.0
     */
    public boolean updateTablet(String id, Tablet tablet) {
        if (tablet == null || !isValid(id)) {
            return false;
        }

        for (int i = 0; i < technologyList.size(); i++) {
            Technology technology = technologyList.get(i);
            if (technology instanceof Tablet && technology.getId().equals(id)) {
                technologyList.set(i, tablet);
                return true;
            }
        }
        return false;
    }

    /**
     * 根据 ID 更新平板电脑。
     * Update a tablet by ID.
     *
     * @param id ID.
     * @param smartWatch 智能手表。
     *                   The smart watch.
     * @return 更新结果。
     *         The result of updating.
     * @author Fan Xinkang
     * @since version 3.0
     */
    public boolean updateSmartWatch(String id, SmartWatch smartWatch) {
        if (smartWatch == null || !isValid(id)) {
            return false;
        }

        for (int i = 0; i < technologyList.size(); i++) {
            Technology technology = technologyList.get(i);
            if (technology instanceof SmartWatch && technology.getId().equals(id)) {
                technologyList.set(i, smartWatch);
                return true;
            }
        }
        return false;
    }

    /**
     * 根据 ID 更新智能手环。
     * Update a smart band by ID.
     *
     * @param id ID.
     * @param smartBand 智能手环。
     *                  The smart band.
     * @return 更新结果。
     *         The result of updating.
     * @author Fan Xinkang
     * @since version 3.0
     */
    public boolean updateSmartBand(String id, SmartBand smartBand) {
        if (smartBand == null || !isValid(id)) {
            return false;
        }

        for (int i = 0; i < technologyList.size(); i++) {
            Technology technology = technologyList.get(i);
            if (technology instanceof SmartBand && technology.getId().equals(id)) {
                technologyList.set(i, smartBand);
                return true;
            }
        }
        return false;
    }

    /**
     * 根据索引删除技术产品。
     * Delete a technology device by index.
     *
     * @param index 索引。
     *              The index.
     * @return 删除结果。
     *         The result of deleting.
     * @author Fan Xinkang
     * @since version 3.0
     */
    public Technology deleteTechnologyByIndex(int index) {
        if (Utilities.isValidIndex(technologyList, index)) {
            return technologyList.remove(index);
        } else {
            return null;
        }
    }

    /**
     * 根据 ID 删除技术产品。
     * Delete a technology device by ID.
     *
     * @param id ID.
     * @return 删除结果。
     *         The result of deleting.
     * @author Fan Xinkang
     * @since version 3.0
     */
    public Technology deleteTechnologyById(String id) {
        for (Technology device : technologyList) {
            if (device.getId().equals(id)) {
                technologyList.remove(device);
                return device;
            }
        }
        return null;
    }

    /**
     * 根据制造商获取技术设备的数量。
     * Get the number of technology devices by manufacturer.
     *
     * @param manufacturer 制造商。
     *                     The manufacturer.
     * @return 数量。
     *         The number.
     * @author Fan Xinkang
     * @since version 3.0
     */
    public int numberOfTechnologyByChosenManufacturer(Manufacturer manufacturer) {
        int number = 0;
        for (Technology technology : technologyList) {
            if (technology.getManufacturer().equals(manufacturer)) {
                number++;
            }
        }
        return number;
    }

    /**
     * 获取技术设备的数量。
     * Get the number of technology devices.
     *
     * @return 数量。
     *         The number.
     * @author Fan Xinkang
     * @since version 3.0
     */
    public int numberOfTechnologyDevices() {
        return technologyList.size();
    }

    /**
     * 获取平板电脑的数量。
     * Get the number of tablets.
     *
     * @return 数量。
     *         The number.
     * @author Fan Xinkang
     * @since version 3.0
     */
    public int numberOfTablets() {
        int number = 0;
        for (Technology technology : technologyList) {
            if (technology instanceof Tablet) {
                number++;
            }
        }
        return number;
    }

    /**
     * 获取智能手环的数量。
     * Get the number of smart bands.
     *
     * @return 数量。
     *         The number.
     * @author Fan Xinkang
     * @since version 3.0
     */
    public int numberOfSmartBands() {
        int number = 0;
        for (Technology technology : technologyList) {
            if (technology instanceof SmartBand) {
                number++;
            }
        }
        return number;
    }

    /**
     * 获取智能手表的数量。
     * Get the number of smart watches.
     *
     * @return 数量。
     *         The number.
     * @author Fan Xinkang
     * @since version 3.0
     */
    public int numberOfSmartWatch() {
        int number = 0;
        for (Technology technology : technologyList) {
            if (technology instanceof SmartWatch) {
                number++;
            }
        }
        return number;
    }

    /**
     * 列出所有技术产品。
     * List all technology devices.
     *
     * @return 技术产品列表。
     *         The list of technology devices.
     * @author Fan Xinkang
     * @since version 3.0
     */
    public String listAllTechnologyDevices() {
        if (technologyList.isEmpty()) {
            return "There are no technology devices in the list.";
        } else {
            StringBuilder builder = new StringBuilder();
            for (Technology technology : technologyList) {
                builder.append(technology).append("\n");
            }
            return builder.toString();
        }
    }

    /**
     * 列出所有平板电脑。
     * List all tablets.
     *
     * @return 平板电脑列表。
     *         The list of tablets.
     * @author Fan Xinkang
     * @since version 3.0
     */
    public String listAllTablets() {
        if (technologyList.isEmpty()) {
            return "There are no tablets in the list.";
        } else {
            StringBuilder builder = new StringBuilder();
            for (Technology technology : technologyList) {
                if (technology instanceof Tablet) {
                    builder.append(technology).append("\n");
                }
            }
            return builder.toString();
        }
    }

    /**
     * 列出所有智能手环。
     * List all smart bands.
     *
     * @return 智能手环列表。
     *         The list of smart bands.
     * @author Fan Xinkang
     * @since version 3.0
     */
    public String listAllSmartBands() {
        if (technologyList.isEmpty()) {
            return "There are no smart bands in the list.";
        } else {
            StringBuilder builder = new StringBuilder();
            for (Technology technology : technologyList) {
                if (technology instanceof SmartBand) {
                    builder.append(technology).append("\n");
                }
            }
            return builder.toString();
        }
    }

    /**
     * 列出所有智能手表。
     * List all smart watches.
     *
     * @return 智能手表列表。
     *         The list of smart watches.
     * @author Fan Xinkang
     * @since version 3.0
     */
    public String listAllSmartWatches() {
        if (technologyList.isEmpty()) {
            return "There are no smart watches in the list.";
        } else {
            StringBuilder builder = new StringBuilder();
            for (Technology technology : technologyList) {
                if (technology instanceof SmartWatch) {
                    builder.append(technology).append("\n");
                }
            }
            return builder.toString();
        }
    }

    /**
     * 根据制造商列出技术产品。
     * List all technology devices by manufacturer.
     *
     * @param manufacturer 制造商。
     *                     The manufacturer.
     * @return 技术产品列表。
     *         The list of technology devices.
     * @author Fan Xinkang
     * @since version 3.0
     */
    public String listAllTechDevicesByChosenManufacturer(Manufacturer manufacturer) {
        if (technologyList.isEmpty()) {
            return STR."No technology manufactured \{manufacturer.getManufacturerName()}.";
        } else {
            StringBuilder builder = new StringBuilder();
            for (Technology technology : technologyList) {
                if (technology.getManufacturer().equals(manufacturer)) {
                    builder.append(technology).append("\n");
                }
            }
            return builder.toString();
        }
    }

    /**
     * 根据操作系统列出平板电脑。
     * List all tablets by operating system.
     *
     * @param os 操作系统。
     *           The operating system.
     * @return 平板电脑列表。
     *         The list of tablets.
     * @author Fan Xinkang
     * @since version 3.0
     */
    public String listAllTabletsByOperatingSystem(String os) {
        if (technologyList.isEmpty()) {
            return "There are no tablets in the list.";
        } else if (!isValid(os)) {
            return "Invalid operating system.";
        } else {
            StringBuilder builder = new StringBuilder();
            boolean found = false;
            for (Technology technology : technologyList) {
                if (technology instanceof Tablet tablet) {
                    if (tablet.getOperatingSystem().equals(os)) {
                        builder.append(tablet).append("\n");
                        found = true;
                    }
                }
            }

            if (found) {
                return builder.toString();
            } else {
                return STR."There are no tablets with operating system \{os}.";
            }
        }
    }

    /**
     * 根据价格列出技术产品。
     * List all technology devices above a specified price.
     *
     * @param price 价格。
     *              The price.
     * @return 技术产品列表。
     *         The list of technology devices.
     * @author Fan Xinkang
     * @since version 3.0
     */
    public String listAllTechnologyAbovePrice(double price) {
        if (technologyList.isEmpty()) {
            return "There are no technology devices in the list.";
        } else {
            StringBuilder builder = new StringBuilder();
            boolean found = false;
            for (Technology technology : technologyList) {
                if (technology.getPrice() >= price) {
                    builder.append(technology).append("\n");
                    found = true;
                }
            }
            if (found) {
                return builder.toString();
            } else {
                return STR."No technology more expensive than \{price}.";
            }
        }
    }

    /**
     * 根据价格列出技术产品。
     * List all technology devices below a specified price.
     *
     * @param price 价格。
     *              The price.
     * @return 技术产品列表。
     *         The list of technology devices.
     * @author Fan Xinkang
     * @since version 3.0
     */
    public String listAllTechnologyBelowPrice(double price) {
        if (technologyList.isEmpty()) {
            return "There are no technology devices in the list.";
        } else {
            StringBuilder builder = new StringBuilder();
            boolean found = false;
            for (Technology technology : technologyList) {
                if (technology.getPrice() < price) {
                    builder.append(technology).append("\n");
                    found = true;
                }
            }
            if (found) {
                return builder.toString();
            } else {
                return STR."No technology less expensive than \{price}.";
            }
        }
    }

    /**
     * 按价格升序排序。
     * Sort by price ascending.
     *
     * @author Fan Xinkang
     * @since version 3.0
     */
    public void sortByPriceAscending() {
        technologyList.sort(Comparator.comparingDouble(Technology::getPrice));
    }

    /**
     * 按价格降序排序。
     * Sort by price descending.
     *
     * @author Fan Xinkang
     * @since version 3.0
     */
    public void sortByPriceDescending() {
        technologyList.sort(Comparator.comparingDouble(Technology::getPrice).reversed());
    }

    /**
     * 交换两个技术产品。
     * Swap two technology devices.
     *
     * @param i 第一个索引。
     *          The first index.
     * @param j 第二个索引。
     *          The second index.
     * @author Fan Xinkang
     * @since version 3.0
     */
    public void swapTechnology(List<Technology> list, int i, int j) {
        if (i >= 0 && i < technologyList.size() && j >= 0 && j < technologyList.size()) {
            Technology temp = technologyList.get(i);
            technologyList.set(i, technologyList.get(j));
            technologyList.set(j, temp);
        }
    }

    /**
     * 获取最昂贵的前五款技术产品。
     * Get the five most expensive technology devices.
     *
     * @return 最昂贵的前五款技术产品。
     *         The five most expensive technology devices.
     * @author Fan Xinkang
     * @since version 3.0
     */
    public List<Technology> topFiveMostExpensiveTechnology() {
        sortByPriceDescending();
        int limit = Math.min(5, technologyList.size());
        return new ArrayList<>(technologyList.subList(0, limit));
    }

    /**
     * 获取最昂贵的前五款平板电脑。
     * Get the five most expensive tablets.
     *
     * @return 最昂贵的前五款平板电脑。
     *         The five most expensive tablets.
     * @author Fan Xinkang
     * @since version 3.0
     */
    public List<Technology> topFiveMostExpensiveTablet() {
        List<Technology> tablets = new ArrayList<>();
        for (Technology technology : technologyList) {
            if (technology instanceof Tablet) {
                tablets.add(technology);
            }
        }
        tablets.sort(Comparator.comparingDouble(Technology::getPrice).reversed());
        int limit = Math.min(5, tablets.size());
        return new ArrayList<>(tablets.subList(0, limit));
    }

    /**
     * 获取最昂贵的前五款智能手环。
     * Get the five most expensive smart bands.
     *
     * @return 最昂贵的前五款智能手环。
     *         The five most expensive smart bands.
     * @author Fan Xinkang
     * @since version 3.0
     */
    public List<Technology> topFiveMostExpensiveSmartBand() {
        List<Technology> smartBands = new ArrayList<>();
        for (Technology technology : technologyList) {
            if (technology instanceof SmartBand) {
                smartBands.add(technology);
            }
        }
        smartBands.sort(Comparator.comparingDouble(Technology::getPrice).reversed());
        int limit = Math.min(5, smartBands.size());
        return new ArrayList<>(smartBands.subList(0, limit));
    }

    /**
     * 获取最昂贵的前五款智能手表。
     * Get the five most expensive smart watches.
     *
     * @return 最昂贵的前五款智能手表。
     *         The five most expensive smart watches.
     * @author Fan Xinkang
     * @since version 3.0
     */
    public List<Technology> topFiveMostExpensiveSmartWatch() {
        List<Technology> smartWatches = new ArrayList<>();
        for (Technology technology : technologyList) {
            if (technology instanceof SmartWatch) {
                smartWatches.add(technology);
            }
        }
        smartWatches.sort(Comparator.comparingDouble(Technology::getPrice).reversed());
        int limit = Math.min(5, smartWatches.size());
        return new ArrayList<>(smartWatches.subList(0, limit));
    }

    /**
     * 加载数据。
     * Load data.
     *
     * @throws Exception 如果加载数据时发生异常。
     *                   If an exception occurs while loading data.
     * @author Fan Xinkang
     * @since version 3.0
     */
    @SuppressWarnings("unchecked")
    public void load() throws Exception {

        /*
          允许的类。
          Allowed classes.
         */
        Class<?>[] classes = new Class[]{
                Technology.class,
                Tablet.class,
                SmartWatch.class,
                SmartBand.class,
                Manufacturer.class
        };

        /*
          设置 XStream 对象以允许指定的类。
          Set up XStream object to allow specified classes.
         */
        XStream xstream = new XStream(new DomDriver());
        XStream.setupDefaultSecurity(xstream);
        xstream.allowTypes(classes);

        /*
          从 XML 文件中读取数据。
          Read data from XML file.
         */
        ObjectInputStream in = xstream.createObjectInputStream(new FileReader(file));
        technologyList = (ArrayList<Technology>) in.readObject();
        in.close();
    }

    /**
     * 保存数据。
     * Save data.
     *
     * @throws Exception 如果保存数据时发生异常。
     *                   If an exception occurs while saving data.
     * @author Fan Xinkang
     * @since version 3.0
     */
    public void save() throws Exception {

        /*
          使用 XStream 对象将数据写入 XML 文件。
          Write data to XML file using XStream object.
         */
        var xstream = new XStream(new DomDriver());
        ObjectOutputStream os = xstream.createObjectOutputStream(new FileWriter(file));
        os.writeObject(technologyList);
        os.close();
    }

    /**
     * 获取文件名。
     * Get file name.
     *
     * @return 文件名。
     *         File name.
     * @author Fan Xinkang
     * @since version 3.0
     */
    public String fileName() {
        return String.valueOf(file);
    }

    /**
     * 检查字符串是否有效。
     * Check if string is valid.
     *
     * @param str 字符串。
     *            String.
     * @return 检查的结果。
     *         The result of checking.
     * @author Fan Xinkang
     * @since version 3.0
     */
    public boolean isValid(String str) {
        return str != null && !str.isEmpty();
    }

    /**
     * 检查 ID 是否有效。
     * Check if ID is valid.
     *
     * @param id ID.
     * @return 检查的结果。
     *         The result of checking.
     * @author Guoqing Lu
     * @since version 0.0
     */
    public boolean isValidId(String id) {
        for (Technology techDev : technologyList) {
            if (techDev.getId().equals(id)) {
                return false;
            }
        }
        return true;
    }
}
/*
 * End of controllers.TechnologyDeviceAPI Class.
 * Checked by Fan Xinkang on 2025/04/16.
 */