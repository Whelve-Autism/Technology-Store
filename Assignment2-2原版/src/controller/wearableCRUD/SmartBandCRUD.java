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
        } while (!Utilities.isValidIndex(getManufacturers(), index - 1));
        manufacturer = getManufacturers().get(index - 1);

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

    public static void updateSmartBand(SmartBand smartBandToUpdate) {
        if (smartBandToUpdate == null) {
            printlnRandomColor("Smart band not found.");
            return;
        }

        String originalModelName = smartBandToUpdate.getModelName();
        double originalPrice = smartBandToUpdate.getPrice();
        Manufacturer originalManufacturer = smartBandToUpdate.getManufacturer();
        String originalId = smartBandToUpdate.getId();
        String originalMaterial = smartBandToUpdate.getMaterial();
        String originalSize = smartBandToUpdate.getSize();
        boolean originalHeartRateMonitor = smartBandToUpdate.isHeartRateMonitor();
        boolean modified = false;
        String modelName, id, material, size;
        double price = originalPrice;
        int index = -1;
        Manufacturer manufacturer;
        boolean heartRateMonitor;

        // Model Name
        modelName = ScannerInput.readNextLine(STR."Enter model name (press Enter to keep '\{originalModelName}'): ");
        if (!modelName.isEmpty()) {
            while (!Utilities.isValidString(modelName, 20)) {
                modelName = ScannerInput.readNextLine("Please enter a valid model name (less than 20 chars): ");
            }
            smartBandToUpdate.setModelName(modelName);
            modified = true;
        }

        // Price
        String priceInput = ScannerInput.readNextLine(STR."Enter price (press Enter to keep \{originalPrice}): ");
        if (!priceInput.isEmpty()) {
            try {
                price = Double.parseDouble(priceInput);
            } catch (Exception e) {
                printlnRandomColor("Invalid input.");
            }

            while (price <= 0) {
                price = ScannerInput.readNextDouble("Please enter a valid price (> 0): ");
            }

            if (price != originalPrice) {
                smartBandToUpdate.setPrice(price);
                modified = true;
            }
        }

        // Manufacturer
        listAllManufacturers();
        String indexInput = ScannerInput.readNextLine(STR."Enter manufacturer index (press Enter to keep \{getManufacturers().indexOf(originalManufacturer) + 1}): ");
        if (!indexInput.isEmpty()) {
            try {
                index = Integer.parseInt(indexInput);
            } catch (Exception e) {
                printlnRandomColor("Invalid input.");
            }

            while (!Utilities.isValidIndex(getManufacturers(), index - 1)) {
                index = ScannerInput.readNextInt("Please enter a valid manufacturer index: ");
            }

            manufacturer = getManufacturers().get(index - 1);
            if (!manufacturer.equals(originalManufacturer)) {
                smartBandToUpdate.setManufacturer(manufacturer);
                modified = true;
            }
        }

        // ID
        id = ScannerInput.readNextLine(STR."Enter ID (press Enter to keep '\{originalId}'): ");
        if (!id.isEmpty()) {
            while (!isValidId(id)) {
                id = ScannerInput.readNextLine("ID already exists or invalid. Please enter a new ID: ");
            }
            smartBandToUpdate.setId(id);
            modified = true;
        }

        // Material
        material = ScannerInput.readNextLine(STR."Enter material (press Enter to keep '\{originalMaterial}'): ");
        if (!material.isEmpty()) {
            while (!Utilities.isValidString(material, 20)) {
                material = ScannerInput.readNextLine("Please enter a valid material (less than 20 chars): ");
            }
            smartBandToUpdate.setMaterial(material);
            modified = true;
        }

        // Size
        size = ScannerInput.readNextLine(STR."Enter size (press Enter to keep '\{originalSize}'): ");
        if (!size.isEmpty()) {
            while (!Utilities.isValidString(size, 20)) {
                size = ScannerInput.readNextLine("Please enter a valid size (less than 20 chars): ");
            }
            smartBandToUpdate.setSize(size);
            modified = true;
        }

        // Heart Rate Monitor
        String heartRateMonitorInput = ScannerInput.readNextLine(STR."Does it include a heart rate monitor? (y/n) (press Enter to keep \{(originalHeartRateMonitor ? "y" : "n")}): ");
        if (!heartRateMonitorInput.isEmpty()) {
            heartRateMonitor = ScannerInput.readNextBoolean("Does it include a heart rate monitor? (y/n): ");
            if (heartRateMonitor != originalHeartRateMonitor) {
                smartBandToUpdate.setHeartRateMonitor(heartRateMonitor);
                modified = true;
            }
        }

        // 判断是否有真实修改
        if (modified &&
                originalModelName.equals(smartBandToUpdate.getModelName()) &&
                originalPrice == smartBandToUpdate.getPrice() &&
                originalManufacturer.equals(smartBandToUpdate.getManufacturer()) &&
                originalId.equals(smartBandToUpdate.getId()) &&
                originalMaterial.equals(smartBandToUpdate.getMaterial()) &&
                originalSize.equals(smartBandToUpdate.getSize()) &&
                originalHeartRateMonitor == smartBandToUpdate.isHeartRateMonitor()) {
            printlnRandomColor("No changes.");
        } else if (modified) {
            printlnRandomColor("Smart band updated successfully.");
        } else {
            printlnRandomColor("No changes.");
        }

        printlnRandomColor(String.valueOf(smartBandToUpdate));
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
