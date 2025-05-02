package controller.computingCRUD;

import model.Manufacturer;
import model.Tablet;
import model.Technology;
import utils.ScannerInput;
import utils.Utilities;

import static controller.ManufacturerAPI.getManufacturers;
import static controller.ManufacturerAPI.listAllManufacturers;
import static controller.TechnologyAPI.isValidId;
import static controller.TechnologyAPI.technologyList;
import static model.Tablet.operatingSystems;
import static service.UserInterface.printlnRandomColor;

public class TabletCRUD {

    public static void addTablet() {
        String modelName, id, processor;
        double price;
        int index, storage, osIndex;
        Manufacturer manufacturer;

        if (getManufacturers().isEmpty()) {
            System.out.println("No manufacturers found. Please add a manufacturer first.");
            return;
        }

        do {
            modelName = ScannerInput.readNextLine("Please enter the name of the tablet.");
        } while (!Utilities.isValidString(modelName, 20));

        do {
            price = ScannerInput.readNextDouble("Please enter the price of the tablet.");
        } while (price <= 0);

        listAllManufacturers();
        do {
            index = ScannerInput.readNextInt("Please enter the index of the manufacturer: ");
        } while (!Utilities.isValidIndex(getManufacturers(), index - 1));
        manufacturer = getManufacturers().get(index - 1);

        do {
            id = ScannerInput.readNextLine("Please enter the id of the tablet.");
        } while (!isValidId(id));

        do {
            processor = ScannerInput.readNextLine("Please enter the processor of the tablet.");
        } while (!Utilities.isValidString(processor, 20));

        do {
            storage = ScannerInput.readNextInt("Please enter the storage of the tablet.");
        } while (storage % 8 != 0);

        Tablet.listAllOperatingSystems();
        do {
            osIndex = ScannerInput.readNextInt("Please enter the index of the operating system: ");
        } while (!Utilities.validRange(osIndex - 1, 0, operatingSystems.size() - 1));
        String operatingSystem = operatingSystems.get(osIndex - 1);

        Tablet tablet = new Tablet(modelName, price, manufacturer, id, processor, storage, operatingSystem);
        technologyList.add(tablet);
    }

    public static void updateTablet(Tablet tabletToUpdate) {
        if (tabletToUpdate == null) {
            printlnRandomColor("Tablet not found.");
            return;
        }

        // 保存原始数据用于比较是否更改
        String originalModelName = tabletToUpdate.getModelName();
        double originalPrice = tabletToUpdate.getPrice();
        Manufacturer originalManufacturer = tabletToUpdate.getManufacturer();
        String originalId = tabletToUpdate.getId();
        String originalProcessor = tabletToUpdate.getProcessor();
        int originalStorage = tabletToUpdate.getStorage();
        String originalOperatingSystem = tabletToUpdate.getOperatingSystem();

        boolean modified = false;

        String modelName, id, processor;
        double price = originalPrice;
        int index = -1, storage = originalStorage, osIndex = -1;
        Manufacturer manufacturer;
        String operatingSystem;

        // Model Name
        modelName = ScannerInput.readNextLine(STR."Enter model name (press Enter to keep '\{originalModelName}'): ");
        if (!modelName.isEmpty()) {
            while (!Utilities.isValidString(modelName, 20)) {
                modelName = ScannerInput.readNextLine("Please enter a valid model name (less than 20 chars): ");
            }
            tabletToUpdate.setModelName(modelName);
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
                tabletToUpdate.setPrice(price);
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
                tabletToUpdate.setManufacturer(manufacturer);
                modified = true;
            }
        }

        // ID
        id = ScannerInput.readNextLine(STR."Enter ID (press Enter to keep '\{originalId}'): ");
        if (!id.isEmpty()) {
            while (!isValidId(id)) {
                id = ScannerInput.readNextLine("ID already exists or invalid. Please enter a new ID: ");
            }
            tabletToUpdate.setId(id);
            modified = true;
        }

        // Processor
        processor = ScannerInput.readNextLine(STR."Enter processor (press Enter to keep '\{originalProcessor}'): ");
        if (!processor.isEmpty()) {
            while (!Utilities.validStringLength(processor, 20)) {
                processor = ScannerInput.readNextLine("Please enter a valid processor (less than 20 chars): ");
            }
            tabletToUpdate.setProcessor(processor);
            modified = true;
        }

        // Storage
        String storageInput = ScannerInput.readNextLine(STR."Enter storage (press Enter to keep \{originalStorage}): ");
        if (!storageInput.isEmpty()) {
            try {
                storage = Integer.parseInt(storageInput);
            } catch (Exception e) {
                printlnRandomColor("Invalid input.");
            }

            while (storage % 8 != 0 || storage <= 0) {
                storage = ScannerInput.readNextInt("Please enter a valid storage (multiple of 8 and > 0): ");
            }

            if (storage != originalStorage) {
                tabletToUpdate.setStorage(storage);
                modified = true;
            }
        }

        // Operating System
        Tablet.listAllOperatingSystems();
        String osIndexInput = ScannerInput.readNextLine(STR."Enter OS index (press Enter to keep \{operatingSystems.indexOf(originalOperatingSystem) + 1}): ");
        if (!osIndexInput.isEmpty()) {
            try {
                osIndex = Integer.parseInt(osIndexInput);
            } catch (Exception e) {
                printlnRandomColor("Invalid input.");
            }

            while (!Utilities.validRange(osIndex - 1, 0, operatingSystems.size() - 1)) {
                osIndex = ScannerInput.readNextInt("Please enter a valid OS index: ");
            }

            operatingSystem = operatingSystems.get(osIndex - 1);
            if (!operatingSystem.equals(originalOperatingSystem)) {
                tabletToUpdate.setOperatingSystem(operatingSystem);
                modified = true;
            }
        }

        // 判断是否有真实修改
        if (modified &&
                originalModelName.equals(tabletToUpdate.getModelName()) &&
                originalPrice == tabletToUpdate.getPrice() &&
                originalManufacturer.equals(tabletToUpdate.getManufacturer()) &&
                originalId.equals(tabletToUpdate.getId()) &&
                originalProcessor.equals(tabletToUpdate.getProcessor()) &&
                originalStorage == tabletToUpdate.getStorage() &&
                originalOperatingSystem.equals(tabletToUpdate.getOperatingSystem())) {
            printlnRandomColor("No changes.");
        } else if (modified) {
            printlnRandomColor("Tablet updated successfully.");
        } else {
            printlnRandomColor("No changes.");
        }

        printlnRandomColor(String.valueOf(tabletToUpdate));
    }

    public static void listAllTablets() {
        StringBuilder builder = new StringBuilder();
        for (Technology technology : technologyList) {
            if (technology instanceof Tablet) {
                builder.append(technology).append("\n");
            }
        }

        if (builder.isEmpty()) {
            System.out.println("No tablets found.");
        } else {
            System.out.println(builder);
        }
    }
}
