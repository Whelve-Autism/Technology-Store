package controller.wearableCRUD;

import model.Manufacturer;
import model.SmartBand;
import model.Technology;
import utils.ScannerInput;
import utils.Utilities;

import static controller.ManufacturerAPI.getManufacturers;
import static controller.ManufacturerAPI.listAllManufacturers;
import static controller.TechnologyAPI.isValidId;
import static controller.TechnologyAPI.technologyList;
import static service.UserInterface.printlnRandomColor;

public class SmartBandCRUD {

    public static void addSmartBand() {
        String modelName, id, material, size;
        double price;
        int index;
        Manufacturer manufacturer;
        boolean heartRateMonitor;

        if (getManufacturers().isEmpty()) {
            printlnRandomColor("No manufacturers found. Please add a manufacturer first.");
            return;
        }

        do {
            modelName = ScannerInput.readNextLine("Please enter the name of the smart band.");
        } while (!Utilities.isValidString(modelName, 20));

        do {
            price = ScannerInput.readNextDouble("Please enter the price of the smart band.");
        } while (price <= 0);

        listAllManufacturers();
        do {
            index = ScannerInput.readNextInt("Please enter the index of the manufacturer: ");
        } while (!Utilities.isValidIndex(getManufacturers(), index));
        manufacturer = getManufacturers().get(index);

        do {
            id = ScannerInput.readNextLine("Please enter the id of the smart band.");
        } while (!isValidId(id));

        do {
            material = ScannerInput.readNextLine("Please enter the material of the smart band.");
        } while (!Utilities.isValidString(material, 20));

        do {
            size = ScannerInput.readNextLine("Please enter the size of the smart band.");
        } while (!Utilities.isValidString(size, 20));

        heartRateMonitor = ScannerInput.readNextBoolean("Does it include a heart rate monitor? (y/n): ");

        SmartBand smartBand = new SmartBand(modelName, price, manufacturer, id, material, size, heartRateMonitor);
        technologyList.add(smartBand);
    }

    public static void updateSmartBand() {
        // 假设我们有一个方法来获取要更新的SmartBand对象
        SmartBand smartBandToUpdate = getSmartBandToUpdate(); // 需要根据实际情况实现这个方法

        if (smartBandToUpdate == null) {
            printlnRandomColor("Smart band not found.");
            return;
        }

        String modelName, id, material, size;
        double price;
        int index;
        Manufacturer manufacturer;
        boolean heartRateMonitor;

        // Model Name
        modelName = ScannerInput.readNextLine("Please enter the name of the smart band (press Enter to keep current: " + smartBandToUpdate.getModelName() + "): ");
        if (!modelName.isEmpty()) {
            do {
                modelName = ScannerInput.readNextLine("Please enter the name of the smart band.");
            } while (!Utilities.isValidString(modelName, 20));
            smartBandToUpdate.setModelName(modelName);
        }

        // Price
        String priceInput = ScannerInput.readNextLine("Please enter the price of the smart band (press Enter to keep current: " + smartBandToUpdate.getPrice() + "): ");
        if (!priceInput.isEmpty()) {
            price = Double.parseDouble(priceInput);
            do {
                price = ScannerInput.readNextDouble("Please enter the price of the smart band.");
            } while (price <= 0);
            smartBandToUpdate.setPrice(price);
        }

        // Manufacturer
        listAllManufacturers();
        String indexInput = ScannerInput.readNextLine("Please enter the index of the manufacturer (press Enter to keep current: " + getManufacturers().indexOf(smartBandToUpdate.getManufacturer()) + "): ");
        if (!indexInput.isEmpty()) {
            index = Integer.parseInt(indexInput);
            do {
                index = ScannerInput.readNextInt("Please enter the index of the manufacturer: ");
            } while (!Utilities.isValidIndex(getManufacturers(), index));
            manufacturer = getManufacturers().get(index);
            smartBandToUpdate.setManufacturer(manufacturer);
        }

        // ID
        id = ScannerInput.readNextLine("Please enter the id of the smart band (press Enter to keep current: " + smartBandToUpdate.getId() + "): ");
        if (!id.isEmpty()) {
            do {
                id = ScannerInput.readNextLine("Please enter the id of the smart band.");
            } while (!isValidId(id));
            smartBandToUpdate.setId(id);
        }

        // Material
        material = ScannerInput.readNextLine("Please enter the material of the smart band (press Enter to keep current: " + smartBandToUpdate.getMaterial() + "): ");
        if (!material.isEmpty()) {
            do {
                material = ScannerInput.readNextLine("Please enter the material of the smart band.");
            } while (!Utilities.isValidString(material, 20));
            smartBandToUpdate.setMaterial(material);
        }

        // Size
        size = ScannerInput.readNextLine("Please enter the size of the smart band (press Enter to keep current: " + smartBandToUpdate.getSize() + "): ");
        if (!size.isEmpty()) {
            do {
                size = ScannerInput.readNextLine("Please enter the size of the smart band.");
            } while (!Utilities.isValidString(size, 20));
            smartBandToUpdate.setSize(size);
        }

        // Heart Rate Monitor
        String heartRateMonitorInput = ScannerInput.readNextLine("Does it include a heart rate monitor? (y/n) (press Enter to keep current: " + (smartBandToUpdate.isHeartRateMonitor() ? "y" : "n") + "): ");
        if (!heartRateMonitorInput.isEmpty()) {
            heartRateMonitor = ScannerInput.readNextBoolean("Does it include a heart rate monitor? (y/n): ");
            smartBandToUpdate.setHeartRateMonitor(heartRateMonitor);
        }

        // 假设我们有一个方法来保存更新后的SmartBand对象
        saveSmartBand(smartBandToUpdate); // 需要根据实际情况实现这个方法
    }

    // 需要根据实际情况实现的方法
    private static SmartBand getSmartBandToUpdate() {
        // 实现获取要更新的SmartBand对象的逻辑
        return null; // 示例返回null
    }

    // 需要根据实际情况实现的方法
    private static void saveSmartBand(SmartBand smartBand) {
        // 实现保存更新后的SmartBand对象的逻辑
    }

    public static void listAllSmartBands() {
        StringBuilder builder = new StringBuilder();
        for (Technology technology : technologyList) {
            if (technology instanceof SmartBand) {
                builder.append(technology).append("\n");
            }
        }

        if (builder.isEmpty()) {
            System.out.println("No smart bands found.");
        } else {
            System.out.println(builder);
        }
    }
}
