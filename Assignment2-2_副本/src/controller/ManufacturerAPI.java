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
 * @version 3.1
 * @since version 0.0
 */
public class ManufacturerAPI {

    private static List<Manufacturer> manufacturers = new ArrayList<>();
    private static File file;

    public ManufacturerAPI(File file) {
        ManufacturerAPI.file = file;
    }

    public static List<Manufacturer> getManufacturers() {
        return manufacturers;
    }

    public static void setManufacturers(List<Manufacturer> manufacturers) {
        ManufacturerAPI.manufacturers = manufacturers;
    }

    public static boolean isValidManufacturersName(String manufacturerName) {
        return manufacturers.stream().noneMatch(manufacturer -> manufacturer.getManufacturerName().equalsIgnoreCase(manufacturerName)) && Utilities.isValidString(manufacturerName, 20);
    }

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

    public static void updateManufacturer() {
        if (manufacturers.isEmpty()) {
            printlnRandomColor("No manufacturers found.");
            return;
        }

        listAllManufacturers();
        int index = ScannerInput.readNextInt("Please enter the index of the manufacturer to update: ");
        if (!isValidIndex(manufacturers, index)) {
            printlnRandomColor("Invalid index.");
            return;
        }

        Manufacturer manufacturer = manufacturers.get(index - 1);

        String manufacturerName = ScannerInput.readNextLine("Please enter the new manufacturer name (press Enter to keep current: " + manufacturer.getManufacturerName() + "): ");
        if (!manufacturerName.trim().isEmpty()) {
            do {
                manufacturerName = ScannerInput.readNextLine("Please enter the new manufacturer name: ");
            } while (!isValidManufacturersName(manufacturerName));
            manufacturer.setManufacturerName(manufacturerName);
        }

        String numEmployeesInput = ScannerInput.readNextLine("Please enter the new number of employees (press Enter to keep current: " + manufacturer.getNumEmployees() + "): ");
        if (!numEmployeesInput.isEmpty()) {
            int numEmployees;
            do {
                numEmployees = ScannerInput.readNextInt("Please enter the new number of employees: ");
            } while (numEmployees <= 0);
            manufacturer.setNumEmployees(numEmployees);
        }
        printlnRandomColor("Manufacturer updated successfully.");
    }

    public static void deleteManufacturer() {
        if (manufacturers.isEmpty()) {
            printlnRandomColor("No manufacturers found.");
            return;
        }
        listAllManufacturers();
        int index;
        String indexInput = ScannerInput.readNextLine("Please enter the index of the manufacturer to delete: ");
        if (!indexInput.isEmpty()) {
            do {
                index = ScannerInput.readNextInt("Please enter the index of the manufacturer to delete: ");
                if (!isValidIndex(manufacturers, index)) {
                    printlnRandomColor("Invalid index. Please try again.");
                }
            } while (!isValidIndex(manufacturers, index));
            Manufacturer manufacturer = manufacturers.get(index - 1);
            manufacturers.remove(manufacturer);
            printlnRandomColor("Manufacturer deleted successfully.");
        }
        printlnRandomColor("Manufacturer not deleted.");
    }

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

    public String fileName() {
        return String.valueOf(file);
    }

    public static void save() throws Exception {
        var xstream = new XStream(new DomDriver());
        ObjectOutputStream os = xstream.createObjectOutputStream(new FileWriter(file));
        os.writeObject(manufacturers);
        os.close();
    }

    public static void load() throws Exception {

        Class<?>[] classes = new Class[] {Manufacturer.class};

        XStream xstream = new XStream(new DomDriver());
        XStream.setupDefaultSecurity(xstream);
        xstream.allowTypes(classes);

        ObjectInputStream in = xstream.createObjectInputStream(new FileReader(file));
        manufacturers = (List<Manufacturer>) in.readObject();
        in.close();
    }
}