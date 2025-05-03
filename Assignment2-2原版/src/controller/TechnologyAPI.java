package controller;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import model.*;

import utils.ScannerInput;

import java.io.*;
import java.util.*;

import static controller.computingCRUD.LaptopCRUD.updateLaptop;
import static controller.computingCRUD.TabletCRUD.updateTablet;
import static controller.wearableCRUD.SmartBandCRUD.updateSmartBand;
import static controller.wearableCRUD.SmartWatchCRUD.updateSmartWatch;
import static service.UserInterface.printlnRandomColor;

/**
 * 此类用于处理科技产品的数据。
 * This class is used to handle technology data.
 *
 * @author Fan Xinkang
 * @version 4.3
 * @since version 0.0
 */
public class TechnologyAPI {

    public static ArrayList<Technology> technologyList;
    private static File file = new File("technologyDevices.xml");

    static {
        technologyList = new ArrayList<>();
    }

    /**
     * 构造函数。
     * Constructor.
     *
     * @param file 文件。
     *             File.
     * @author Fan Xinkang
     * @since version 4.3
     */
    public TechnologyAPI(File file) {
        TechnologyAPI.file = file;
    }

    /*
     * 封装。
     * Encapsulation.
     */
    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        TechnologyAPI.file = file;
    }

    public static ArrayList<Technology> getTechnologyList() {
        return technologyList;
    }

    public void setTechnologyList(ArrayList<Technology> technologyList) {
        TechnologyAPI.technologyList = technologyList;
    }

    /**
     * 检查设备ID是否可用。
     * Checks if a device ID is available.
     *
     * @param id ID
     * @return 验证结果
     * @author Fan Xinkang
     * @since version 4.3
     */
    public static boolean isValidId(String id) {
        if (technologyList == null || technologyList.isEmpty()) {
            return true;
        }
        return technologyList.stream().noneMatch(technology -> technology.getId().equals(id)) && !id.isEmpty();
    }

    /**
     * 删除所有设备。
     * Deletes all devices.
     *
     * @author Fan Xinkang
     * @since version 4.3
     */
    public static void deleteAllTechnologies() {
        if (technologyList == null || technologyList.isEmpty()) {
            printlnRandomColor("There are no technologies in the list.");
            return;
        }
        technologyList.clear();
    }

    /**
     * 删除设备。
     * Deletes a device.
     *
     * @author Fan Xinkang
     * @since version 4.3
     */
    public static void deleteTechnology() {
        if (technologyList == null || technologyList.isEmpty()) {
            printlnRandomColor("There are no technologies in the list.");
            return;
        }

        listAllTechnologies();
        int index = ScannerInput.readNextInt("Please enter the index of the technology to delete: ");
        if (index != -1) {
            technologyList.remove(index - 1);
        } else {
            printlnRandomColor("Technology not found.");
        }
    }

    /**
     * 更新设备。
     * Updates a device.
     *
     * @author Fan Xinkang
     * @since version 4.3
     */
    public static void updateTechnology() {
        ArrayList<Technology> technologyList = getTechnologyList();
        if (technologyList == null || technologyList.isEmpty()) {
            printlnRandomColor("No technologies found.");
            return;
        }

        listAllTechnologies();
        int index = -1;
        boolean validInput = false;
        do {
            String indexInput = ScannerInput.readNextLine("Enter the index to update (or press Enter to cancel): ");
            if (indexInput.trim().isEmpty()) {
                printlnRandomColor("Update canceled.");
                return;
            }

            try {
                index = Integer.parseInt(indexInput);
                if (index >= 1 && index <= technologyList.size()) {
                    validInput = true;
                } else {
                    printlnRandomColor("Index out of range. Please enter a valid index.");
                }
            } catch (NumberFormatException e) {
                printlnRandomColor("Invalid input. Please enter a number.");
            }
        } while (!validInput);

        Technology technology = technologyList.get(index - 1);
        switch (technology) {
            case Laptop laptop -> updateLaptop(laptop);
            case Tablet tablet -> updateTablet(tablet);
            case SmartBand smartBand -> updateSmartBand(smartBand);
            case SmartWatch smartWatch -> updateSmartWatch(smartWatch);
            case null, default -> printlnRandomColor("Unsupported technology type.");
        }
    }

    /**
     * 列出所有设备。
     * Lists all devices.
     *
     * @author Fan Xinkang
     * @since version 4.3
     */
    public static void listAllTechnologies() {
        if (technologyList == null || technologyList.isEmpty()) {
            printlnRandomColor("There are no technologies in the list.");
            return;
        }

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < technologyList.size(); i++) {
            Technology technology = technologyList.get(i);
            builder.append(i + 1).append(". ").append(technology).append("\n");
        }
        printlnRandomColor(builder.toString());
    }

    /**
     * 列出价格高于指定价格的设备。
     * Lists devices priced above a specified price.
     *
     * @author Fan Xinkang
     * @since version 4.3
     */
    public static void listTechnologiesAboveAPrice() {
        if (technologyList == null || technologyList.isEmpty()) {
            printlnRandomColor("There are no technologies in the list.");
            return;
        }
        double price = ScannerInput.readNextDouble("Please enter the price: ");
        StringBuilder builder = new StringBuilder();
        for (Technology technology : technologyList) {
            if (technology.getPrice() > price) {
                builder.append(technology).append("\n");
            }
        }
        printlnRandomColor(builder.toString());
    }

    /**
     * 列出价格低于指定价格的设备。
     * Lists devices priced below a specified price.
     *
     * @author Fan Xinkang
     * @since version 4.3
     */
    public static void listTechnologiesBelowAPrice() {
        if (technologyList == null || technologyList.isEmpty()) {
            printlnRandomColor("There are no technologies in the list.");
            return;
        }
        double price = ScannerInput.readNextDouble("Please enter the price: ");
        StringBuilder builder = new StringBuilder();
        for (Technology technology : technologyList) {
            if (technology.getPrice() < price) {
                builder.append(technology).append("\n");
            }
        }
        printlnRandomColor(builder.toString());
    }

    /**
     * 列出最贵的前5个设备。
     * Lists the top 5 most expensive devices.
     *
     * @author Fan Xinkang
     * @since version 4.3
     */
    public static void ListTopFiveExpansiveTechnologyDevices() {
        if (technologyList == null || technologyList.isEmpty()) {
            printlnRandomColor("There are no technologies in the list.");
            return;
        }

        technologyList.sort(Comparator.comparingDouble(Technology::getPrice).reversed());
        int limit = Math.min(5, technologyList.size());
        technologyList.subList(0, limit).forEach(technology -> {
            printlnRandomColor(technology.toString());
        });
    }

    /**
     * 获取文件名。
     * Gets the file name.
     *
     * @return 文件名
     * @author Fan Xinkang
     * @since version 4.3
     */
    public String fileName() {
        return String.valueOf(file);
    }

    /**
     * 加载制造商。
     * Loads manufacturers.
     *
     * @author Fan Xinkang
     * @since version 4.3
     */
    public static void load() {
        try {
            Class<?>[] classes = new Class[]{
                    Technology.class,
                    Laptop.class,
                    Tablet.class,
                    SmartWatch.class,
                    SmartBand.class,
                    Manufacturer.class
            };

            XStream xstream = new XStream(new DomDriver());
            XStream.setupDefaultSecurity(xstream);
            xstream.allowTypes(classes);

            xstream.registerConverter(new converter.TabletConverter());
            xstream.registerConverter(new converter.SmartWatchConverter());
            xstream.setMode(XStream.NO_REFERENCES);

            try (ObjectInputStream in = xstream.createObjectInputStream(new FileReader(file))) {
                technologyList = (ArrayList<Technology>) in.readObject();
            }

            printlnRandomColor("Technologies loaded successfully.");
        } catch (Exception e) {
            printlnRandomColor(STR."Error loading technologies: \{e.getMessage()}");
        }
    }

    /**
     * 保存制造商。
     * Saves manufacturers.
     *
     * @author Fan Xinkang
     * @since version 4.3
     */
    public static void save() {
        try {
            var xstream = new XStream(new DomDriver());
            ObjectOutputStream os = xstream.createObjectOutputStream(new FileWriter(file));
            os.writeObject(technologyList);
            os.close();

            printlnRandomColor("Technologies saved successfully.");
        } catch (Exception e) {
            printlnRandomColor(STR."Error saving technologies: \{e.getMessage()}");
        }
    }
}
/*
 * End of TechnologyAPI Class.
 * Checked by Fan Xinkang on 2025/05/02.
 */