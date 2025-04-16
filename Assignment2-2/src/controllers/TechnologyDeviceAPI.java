package controllers;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import models.*;

import utils.ISerializer;

import utils.Utilities;

import java.io.*;
import java.util.*;

//TODO - ensure that this class implements iSerializer

/**
 * 此类用于处理技术设备的数据。
 * This class is used to handle technology device data.
 *
 * @author Fan Xinkang, Xu Shiyi, Lu Siyu
 * @version 3.0
 * @since version 2.0
 */
public class TechnologyDeviceAPI implements ISerializer {

    //TODO - create 2 fields
    private File file;
    private ArrayList<Technology> technologyList;

    //TODO - create constructor
    public TechnologyDeviceAPI(File file) {
        this.file = file;
        this.technologyList = new ArrayList<>();
    }

    //TODO - CRUD Methods

    // 添加技术设备
    public boolean addTechnologyDevice(Technology technology) {
        if (technology == null || !isValid(technology.getId()) || !isValidId(technology.getId())) {
            return false;
        }
        technologyList.add(technology);
        return true;
    }


    // 根据索引获取技术产品
    public Technology getTechnologyByIndex(int index) {
        if (Utilities.isValidIndex(technologyList, index)) {
            return technologyList.get(index);
        } else {
            return null;
        }
    }

    // 根据ID获取技术设备
    public Technology getTechnologyDeviceById(String id) {
        for (Technology device : technologyList) {
            if (device.getId().equals(id)) {
                return device;
            }
        }
        return null;
    }

    // 更新平板电脑
    public boolean updateTablet(String id, Tablet tablet) {
        if (tablet == null || !isValid(id) || !isValidId(id)) {
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

    // 更新智能手表
    public boolean updateSmartWatch(String id, SmartWatch smartWatch) {
        if (smartWatch == null || !isValid(id) || !isValidId(id)) {
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

    // 更新智能手环
    public boolean updateSmartBand(String id, SmartBand smartBand) {
        if (smartBand == null || !isValid(id) || !isValidId(id)) {
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

    // 根据索引删除技术产品
    public Technology deleteTechnologyByIndex(int index) {
        if (Utilities.isValidIndex(technologyList, index)) {
            return technologyList.remove(index);
        } else {
            return null;
        }
    }

    // 根据ID删除技术产品
    public Technology deleteTechnologyById(String id) {
        for (Technology device : technologyList) {
            if (device.getId().equals(id)) {
                technologyList.remove(device);
                return device;
            }
        }
        return null;
    }

    //TODO - Number methods

    // 获取由选定制造商生产的技术产品数量
    public int numberOfTechnologyByChosenManufacturer(Manufacturer manufacturer) {
        int number = 0;
        for (Technology technology : technologyList) {
            if (technology.getManufacturer().equals(manufacturer)) {
                number++;
            }
        }
        return number;
    }

    // 获取技术设备的数量
    public int numberOfTechnologyDevices() {
        return technologyList.size();
    }

    // 获取平板电脑的数量
    public int numberOfTablets() {
        int number = 0;
        for (Technology technology : technologyList) {
            if (technology instanceof Tablet) {
                number++;
            }
        }
        return number;
    }

    // 获取智能手表的数量
    public int numberOfSmartWatch() {
        int number = 0;
        for (Technology technology : technologyList) {
            if (technology instanceof SmartWatch) {
                number++;
            }
        }
        return number;
    }

    // 获取智能手环的数量
    public int numberOfSmartBands() {
        int number = 0;
        for (Technology technology : technologyList) {
            if (technology instanceof SmartBand) {
                number++;
            }
        }
        return number;
    }

    // TODO Read/list methods

    // 列出所有技术设备
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

    // 列出所有平板电脑
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

    // 列出所有智能手表
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

    // 列出所有智能手环
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

    // 按选定制造商列出所有技术设备
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

    // 按操作系统列出所有平板电脑
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


    // 列出价格高于指定值的所有技术产品
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

    // 列出价格低于指定值的所有技术产品
    public String listAllTechnologyBelowPrice(double price) {
        if (technologyList.isEmpty()) {
            return "There are no technology devices in the list.";
        } else {
            StringBuilder builder = new StringBuilder();
            for (Technology technology : technologyList) {
                if (technology.getPrice() < price) {
                    builder.append(technology).append("\n");
                }
            }
            return builder.toString();
        }
    }


    //TODO get Technology methods

    //TODO - delete methods

    //TODO - sort methods

    // 按价格升序排序
    public void sortByPriceAscending() {
        technologyList.sort(Comparator.comparingDouble(Technology::getPrice));
    }

    // 按价格降序排序
    public void sortByPriceDescending() {
        technologyList.sort(Comparator.comparingDouble(Technology::getPrice).reversed());
    }

    // 交换技术产品列表中的两个元素
    public void swapTechnology(List<Technology> list, int i, int j) {
        if (i >= 0 && i < technologyList.size() && j >= 0 && j < technologyList.size()) {
            Technology temp = technologyList.get(i);
            technologyList.set(i, technologyList.get(j));
            technologyList.set(j, temp);
        }
    }

    //TODO Top 5 methods

    // 获取最昂贵的前五款技术产品
    public List<Technology> topFiveMostExpensiveTechnology() {
        sortByPriceDescending();
        int limit = Math.min(5, technologyList.size());
        return new ArrayList<>(technologyList.subList(0, limit));
    }

    // 获取最昂贵的前五款平板电脑
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


    // 获取最昂贵的前五款智能手表
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

    // TODO Persistence methods

    // 加载数据
    @SuppressWarnings("unchecked")
    public void load() throws Exception {
        //list of classes that you wish to include in the serialisation, separated by a comma
        Class<?>[] classes = new Class[]{
                Technology.class,
                Tablet.class,
                SmartWatch.class,
                SmartBand.class,
                Manufacturer.class
        };

        //setting up the xstream object with default security and the above classes
        XStream xstream = new XStream(new DomDriver());
        XStream.setupDefaultSecurity(xstream);
        xstream.allowTypes(classes);

        //doing the actual serialisation to an XML file
        ObjectInputStream in = xstream.createObjectInputStream(new FileReader(file));
        technologyList = (ArrayList<Technology>) in.readObject();
        in.close();
    }

    // 保存数据
    public void save() throws Exception {
        var xstream = new XStream(new DomDriver());
        ObjectOutputStream os = xstream.createObjectOutputStream(new FileWriter(file));
        os.writeObject(technologyList);
        os.close();
    }

    // 获取文件名
    public String fileName() {
        return String.valueOf(file);
    }

    // 验证字符串是否有效
    public boolean isValid(String str) {
        return str != null && !str.isEmpty();
    }

    // 以下是isValidId可以更新以适应您的代码
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