package controller;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import model.Manufacturer;
import utils.ScannerInput;
import utils.Utilities;

import static service.UserInterface.printlnRandomColor;
import static utils.Utilities.isValidIndex;

/**
 * 此类用于处理制造商的数据。
 * This class is used to handle manufacturer data.
 *
 * @author Guoqing Lu, Fan Xinkang
 * @version 4.3
 * @since version 0.0
 */
public class ManufacturerAPI {

    private static List<Manufacturer> manufacturers = new ArrayList<>();
    private static File file = new File("manufacturers.xml");

    /**
     * 构造函数，初始化文件路径。
     * Constructor, initializes the file path.
     *
     * @param file 文件路径。
     *             file path.
     * @author Fan Xinkang
     * @since version 4.3
     */
    public ManufacturerAPI(File file) {
        ManufacturerAPI.file = file;
    }

    /*
      封装。
      Encapsulation.
     */
    public static List<Manufacturer> getManufacturers() {
        return manufacturers;
    }

    public static void setManufacturers(List<Manufacturer> manufacturers) {
        ManufacturerAPI.manufacturers = manufacturers;
    }

    /**
     * 检查制造商名称是否有效。
     * Checks if the manufacturer name is valid.
     *
     * @param manufacturerName 制造商名称。
     *                         Manufacturer name.
     * @return 验证结果。
     *         Verification result.
     * @author Fan Xinkang
     * @since version 4.3
     */
    public static boolean isValidManufacturersName(String manufacturerName) {
        return manufacturers.stream().noneMatch(manufacturer -> manufacturer.getManufacturerName().equalsIgnoreCase(manufacturerName)) && Utilities.isValidString(manufacturerName, 20);
    }

    /**
     * 添加制造商。
     * Adds a manufacturer.
     *
     * @author Fan Xinkang
     * @since version 4.3
     */
    public static void addManufacturer() {
        String manufacturerName;
        int numEmployees;

        do {
            manufacturerName = ScannerInput.readNextLine("Please enter the manufacturer name: ");
        } while (!isValidManufacturersName(manufacturerName));

        do {
            numEmployees = ScannerInput.readNextInt("Please enter the number of employees: ");
        } while (numEmployees <= 0);

        Manufacturer manufacturer = new Manufacturer(manufacturerName, numEmployees);
        manufacturers.add(manufacturer);
        printlnRandomColor("Manufacturer added successfully.");
    }

    /**
     * 更新制造商。
     * Updates a manufacturer.
     *
     * @author Fan Xinkang
     * @since version 4.3
     */
    public static void updateManufacturer() {
        if (manufacturers.isEmpty()) {
            printlnRandomColor("No manufacturers found.");
            return;
        }

        listAllManufacturers();
        String indexInput = ScannerInput.readNextLine("Enter the index to update (or press Enter to cancel): ");
        if (indexInput.trim().isEmpty()) {
            printlnRandomColor("Operation canceled.");
            return;
        }

        int index;
        try {
            index = Integer.parseInt(indexInput);
        } catch (NumberFormatException e) {
            printlnRandomColor("Invalid input. Please enter a valid number.");
            return;
        }

        if (!isValidIndex(manufacturers, index - 1)) {
            printlnRandomColor("Index out of range.");
            return;
        }
        Manufacturer manufacturer = manufacturers.get(index - 1);

        String manufacturerName = ScannerInput.readNextLine(STR."New name (Enter to keep current: \{manufacturer.getManufacturerName()}): ");
        if (!manufacturerName.trim().isEmpty()) {
            do {
                manufacturerName = ScannerInput.readNextLine("Please enter the new manufacturer name: ");
            } while (!isValidManufacturersName(manufacturerName));
            manufacturer.setManufacturerName(manufacturerName);
        }

        String numEmployeesInput = ScannerInput.readNextLine(STR."New number of employees (Enter to keep current: \{manufacturer.getNumEmployees()}): ");
        if (!numEmployeesInput.isEmpty()) {
            int numEmployees;
            do {
                numEmployees = ScannerInput.readNextInt("Please enter the new number of employees: ");
            } while (numEmployees <= 0);
            manufacturer.setNumEmployees(numEmployees);
        }

        printlnRandomColor("Manufacturer updated successfully.");
    }

    /**
     * 删除制造商。
     * Deletes a manufacturer.
     *
     * @author Fan Xinkang
     * @since version 4.3
     */
    public static void deleteManufacturer() {
        if (manufacturers.isEmpty()) {
            printlnRandomColor("No manufacturers found.");
            return;
        }

        listAllManufacturers();
        String indexInput = ScannerInput.readNextLine("Enter the index to delete (or press Enter to cancel): ");
        if (indexInput.trim().isEmpty()) {
            printlnRandomColor("Deletion canceled.");
            return;
        }

        int index;
        try {
            index = Integer.parseInt(indexInput);
        } catch (NumberFormatException e) {
            printlnRandomColor("Invalid input. Please enter a valid number.");
            return;
        }

        if (!isValidIndex(manufacturers, index)) {
            printlnRandomColor("Index out of range.");
            return;
        }

        Manufacturer manufacturer = manufacturers.get(index - 1);
        manufacturers.remove(manufacturer);
        printlnRandomColor("Manufacturer deleted successfully.");
    }

    /**
     * 列出所有制造商。
     * Lists all manufacturers.
     *
     * @author Fan Xinkang
     * @since version 4.3
     */
    public static void listAllManufacturers() {
        if (manufacturers.isEmpty()) {
            printlnRandomColor("No manufacturers found.");
            return;
        }

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < manufacturers.size(); i++) {
            Manufacturer manufacturer = manufacturers.get(i);
            builder.append(i + 1).append(". ").append(manufacturer).append("\n");
        }
        printlnRandomColor(builder.toString());
    }

    /**
     * 按名称列出所有制造商。
     * Lists all manufacturers by name.
     *
     * @author Fan Xinkang
     * @since version 4.3
     */
    public static void listManufacturersByName() {
        if (manufacturers.isEmpty()) {
            printlnRandomColor("No manufacturers found.");
            return;
        }
        String manufacturerName = ScannerInput.readNextLine("Please enter the manufacturer name: ");
        for (Manufacturer manufacturer : manufacturers) {
            if (manufacturer.getManufacturerName().equals(manufacturerName)) {
                printlnRandomColor(manufacturer.toString());
            }
        }
    }

    /**
     * 获取文件名。
     * Gets the file name.
     *
     * @return 文件名。
     *         File name.
     * @author Fan Xinkang
     * @since version 4.3
     */
    public String fileName() {
        return String.valueOf(file);
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
            os.writeObject(manufacturers);
            os.close();

            printlnRandomColor("Manufacturers saved successfully.");
        } catch (Exception e) {
            printlnRandomColor(STR."Error saving manufacturers: \{e.getMessage()}");
        }
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
            Class<?>[] classes = new Class[] { Manufacturer.class };

            XStream xstream = new XStream(new DomDriver());
            XStream.setupDefaultSecurity(xstream);
            xstream.allowTypes(classes);

            ObjectInputStream in = xstream.createObjectInputStream(new FileReader(file));
            manufacturers = (List<Manufacturer>) in.readObject();
            in.close();

            printlnRandomColor("Manufacturers loaded successfully.");
        } catch (Exception e) {
            printlnRandomColor(STR."Error loading manufacturers: \{e.getMessage()}");
        }
    }
}
/*
 * End of ManufacturerAPI Class.
 * Checked by Fan Xinkang on 2025/05/02.
 */