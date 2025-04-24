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

public class TechnologyAPI {

    private static File file;
    public static ArrayList<Technology> technologyList;

    public TechnologyAPI(File file) {
        TechnologyAPI.file = file;
        technologyList = new ArrayList<>();
    }

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

    public static boolean isValidId(String id) {
        if (technologyList == null || technologyList.isEmpty()) {
            return true;
        }
        return technologyList.stream().noneMatch(technology -> technology.getId().equals(id));
    }

    public static void deleteAllTechnologies() {
        if (technologyList == null || technologyList.isEmpty()) {
            printlnRandomColor("There are no technologies in the list.");
            return;
        }

        technologyList.clear();
    }

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

    public static void updateTechnology() {
        ArrayList<Technology> technologyList = getTechnologyList();
        if (technologyList == null || technologyList.isEmpty()) {
            printlnRandomColor("No technologies found.");
            return;
        }
        listAllTechnologies();
        int index = ScannerInput.readNextInt("Please enter the index of the technology to update: ");
        if (index <= 0 || index > technologyList.size()) {
            printlnRandomColor("Invalid index.");
            return;
        }

        Technology technology = technologyList.get(index - 1);

        switch (technology) {
            case Laptop laptop -> updateLaptop();
            case Tablet tablet -> updateTablet();
            case SmartBand smartBand -> updateSmartBand();
            case SmartWatch smartWatch -> updateSmartWatch();
            case null, default -> printlnRandomColor("Unsupported technology type.");
        }
    }

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

    @SuppressWarnings("unchecked")
    public static void load() throws Exception {

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

    public static void save() throws Exception {

        /*
          使用 XStream 对象将数据写入 XML 文件。
          Write data to XML file using XStream object.
         */
        var xstream = new XStream(new DomDriver());
        ObjectOutputStream os = xstream.createObjectOutputStream(new FileWriter(file));
        os.writeObject(technologyList);
        os.close();
    }

    public String fileName() {
        return String.valueOf(file);
    }
}