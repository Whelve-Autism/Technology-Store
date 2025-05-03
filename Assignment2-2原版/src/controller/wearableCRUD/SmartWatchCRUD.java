package controller.wearableCRUD;

import model.Manufacturer;
import model.SmartWatch;
import model.Technology;
import utils.ScannerInput;
import utils.Utilities;

import static controller.ManufacturerAPI.getManufacturers;
import static controller.ManufacturerAPI.listAllManufacturers;
import static controller.TechnologyAPI.isValidId;
import static controller.TechnologyAPI.technologyList;
import static service.UserInterface.printlnRandomColor;

/**
 * 此类用于处理智能手表相关的 CRUD 操作。
 * This class is used to handle SmartWatch related CRUD operations.
 *
 * @author Fan Xinkang
 * @version 4.4
 * @since version 4.3
 */
public class SmartWatchCRUD {

    /**
     * 添加一个智能手表。
     * This method adds a smart watch.
     *
     * @author Fan Xinkang
     * @since version 4.4
     */
    public static void addSmartWatch() {
        String modelName, id, material, size;
        double price;
        int index, diIndex;
        Manufacturer manufacturer;

        if (getManufacturers().isEmpty()) {
            printlnRandomColor("No manufacturers found. Please add a manufacturer first.");
            return;
        }

        do {
            modelName = ScannerInput.readNextLine("Please enter the name of the smart watch.");
        } while (!Utilities.isValidString(modelName, 20));

        do {
            price = ScannerInput.readNextDouble("Please enter the price of the smart watch.");
        } while (price <= 0);

        listAllManufacturers();
        do {
            index = ScannerInput.readNextInt("Please enter the index of the manufacturer: ");
        } while (!Utilities.isValidIndex(getManufacturers(), index - 1));
        manufacturer = getManufacturers().get(index - 1);

        do {
            id = ScannerInput.readNextLine("Please enter the id of the smart watch.");
        } while (!isValidId(id));

        do {
            material = ScannerInput.readNextLine("Please enter the material of the smart watch.");
        } while (!Utilities.isValidString(material, 20));

        do {
            size = ScannerInput.readNextLine("Please enter the size of the smart watch.");
        } while (!Utilities.isValidString(size, 20));

        SmartWatch.listAllDisplayTypes();
        do {
            diIndex = ScannerInput.readNextInt("Please enter the index of the display type: ");
        } while (!Utilities.isValidIndex(SmartWatch.displayTypes, diIndex));
        String displayType = SmartWatch.displayTypes.get(diIndex);

        SmartWatch smartWatch = new SmartWatch(modelName, price, manufacturer, id, material, size, displayType);
        technologyList.add(smartWatch);
    }

    public static void updateSmartWatch(SmartWatch smartWatchToUpdate) {
        if (smartWatchToUpdate == null) {
            printlnRandomColor("Smart watch not found.");
            return;
        }

        String originalModelName = smartWatchToUpdate.getModelName();
        double originalPrice = smartWatchToUpdate.getPrice();
        Manufacturer originalManufacturer = smartWatchToUpdate.getManufacturer();
        String originalId = smartWatchToUpdate.getId();
        String originalMaterial = smartWatchToUpdate.getMaterial();
        String originalSize = smartWatchToUpdate.getSize();
        String originalDisplayType = smartWatchToUpdate.getDisplayType();
        boolean modified = false;
        String modelName, id, material, size;
        double price = originalPrice;
        int index = -1, diIndex = -1;
        Manufacturer manufacturer;
        String displayType;

        // Model Name
        modelName = ScannerInput.readNextLine(STR."Enter model name (press Enter to keep '\{originalModelName}'): ");
        if (!modelName.isEmpty()) {
            while (!Utilities.isValidString(modelName, 20)) {
                modelName = ScannerInput.readNextLine("Please enter a valid model name (less than 20 chars): ");
            }
            smartWatchToUpdate.setModelName(modelName);
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
                smartWatchToUpdate.setPrice(price);
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
                smartWatchToUpdate.setManufacturer(manufacturer);
                modified = true;
            }
        }

        // ID
        id = ScannerInput.readNextLine(STR."Enter ID (press Enter to keep '\{originalId}'): ");
        if (!id.isEmpty()) {
            while (!isValidId(id)) {
                id = ScannerInput.readNextLine("ID already exists or invalid. Please enter a new ID: ");
            }
            smartWatchToUpdate.setId(id);
            modified = true;
        }

        // Material
        material = ScannerInput.readNextLine(STR."Enter material (press Enter to keep '\{originalMaterial}'): ");
        if (!material.isEmpty()) {
            while (!Utilities.isValidString(material, 20)) {
                material = ScannerInput.readNextLine("Please enter a valid material (less than 20 chars): ");
            }
            smartWatchToUpdate.setMaterial(material);
            modified = true;
        }

        // Size
        size = ScannerInput.readNextLine(STR."Enter size (press Enter to keep '\{originalSize}'): ");
        if (!size.isEmpty()) {
            while (!Utilities.isValidString(size, 20)) {
                size = ScannerInput.readNextLine("Please enter a valid size (less than 20 chars): ");
            }
            smartWatchToUpdate.setSize(size);
            modified = true;
        }

        // Display Type
        SmartWatch.listAllDisplayTypes();
        String diIndexInput = ScannerInput.readNextLine(STR."Enter display type index (press Enter to keep \{SmartWatch.displayTypes.indexOf(originalDisplayType)}): ");
        if (!diIndexInput.isEmpty()) {
            try {
                diIndex = Integer.parseInt(diIndexInput);
            } catch (Exception e) {
                printlnRandomColor("Invalid input.");
            }

            while (!Utilities.isValidIndex(SmartWatch.displayTypes, diIndex)) {
                diIndex = ScannerInput.readNextInt("Please enter a valid display type index: ");
            }

            displayType = SmartWatch.displayTypes.get(diIndex);
            if (!displayType.equals(originalDisplayType)) {
                smartWatchToUpdate.setDisplayType(displayType);
                modified = true;
            }
        }

        // 判断是否有真实修改
        if (modified &&
                originalModelName.equals(smartWatchToUpdate.getModelName()) &&
                originalPrice == smartWatchToUpdate.getPrice() &&
                originalManufacturer.equals(smartWatchToUpdate.getManufacturer()) &&
                originalId.equals(smartWatchToUpdate.getId()) &&
                originalMaterial.equals(smartWatchToUpdate.getMaterial()) &&
                originalSize.equals(smartWatchToUpdate.getSize()) &&
                originalDisplayType.equals(smartWatchToUpdate.getDisplayType())) {
            printlnRandomColor("No changes.");
        } else if (modified) {
            printlnRandomColor("Smart watch updated successfully.");
        } else {
            printlnRandomColor("No changes.");
        }

        printlnRandomColor(String.valueOf(smartWatchToUpdate));
    }

    public static void listAllSmartWatches() {
        StringBuilder builder = new StringBuilder();
        for (Technology technology : technologyList) {
            if (technology instanceof SmartWatch) {
                builder.append(technology).append("\n");
            }
        }

        if (builder.isEmpty()) {
            System.out.println("No smart watches found.");
        } else {
            System.out.println(builder);
        }
    }
}
/*
 * End of SmartWatchCRUD.java.
 * Checked by Fan Xinkang on 2025/05/02.
 */