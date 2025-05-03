package controller.computingCRUD;

import model.Laptop;
import model.Manufacturer;
import model.Technology;
import utils.ScannerInput;
import utils.Utilities;

import static controller.ManufacturerAPI.getManufacturers;
import static controller.ManufacturerAPI.listAllManufacturers;
import static controller.TechnologyAPI.isValidId;
import static controller.TechnologyAPI.technologyList;
import static service.UserInterface.printlnRandomColor;

/**
 * 此类用于处理笔记本相关的 CRUD 操作。
 * This class is used to handle Laptop related CRUD operations.
 *
 * @author Fan Xinkang
 * @version 4.4
 * @since version 4.3
 */
public class LaptopCRUD {

    /**
     * 添加一个笔记本。
     * This method adds a new Laptop.
     *
     * @author Fan Xinkang
     * @since version 4.4
     */
    public static void addLaptop() {
        String modelName, id, processor;
        double price;
        int index, storage, screenSize;
        Manufacturer manufacturer;

        if (getManufacturers().isEmpty()) {
            printlnRandomColor("No manufacturers found. Please add a manufacturer first.");
            return;
        }

        do {
            modelName = ScannerInput.readNextLine("Please enter the name of the laptop (less than 20 chars): ");
        } while (!Utilities.isValidString(modelName, 20));

        do {
            price = ScannerInput.readNextDouble("Please enter the price of the laptop: ");
        } while (price <= 0);

        listAllManufacturers();
        do {
            index = ScannerInput.readNextInt("Please enter the index of the manufacturer: ");
        } while (!Utilities.isValidIndex(getManufacturers(), index - 1));
        manufacturer = getManufacturers().get(index - 1);

        do {
            id = ScannerInput.readNextLine("Please enter the id of the laptop: ");
        } while (!isValidId(id));

        do {
            processor = ScannerInput.readNextLine("Please enter the processor of the laptop (less than 20 chars): ");
        } while (!Utilities.validStringLength(processor, 20));

        do {
            storage = ScannerInput.readNextInt("Please enter the storage of the laptop (must be an integer multiple of 8): ");
        } while (storage % 8 != 0 || storage <= 0);

        do {
            screenSize = ScannerInput.readNextInt("Please enter the screen size of the laptop (larger than 10): ");
        } while (screenSize <= 10);

        Laptop laptop = new Laptop(modelName, price, manufacturer, id, processor, storage, screenSize);
        technologyList.add(laptop);
    }

    /**
     * 更新指定索引的笔记本。
     * This method updates the laptop at the specified index.
     *
     * @param laptopToUpdate 要更新的笔记本对象。
     *                       The laptop object to be updated.
     * @author Fan Xinkang
     * @since version 4.4
     */
    public static void updateLaptop(Laptop laptopToUpdate) {
        if (laptopToUpdate == null) {
            printlnRandomColor("Laptop not found.");
            return;
        }

        String originalModelName = laptopToUpdate.getModelName();
        double originalPrice = laptopToUpdate.getPrice();
        Manufacturer originalManufacturer = laptopToUpdate.getManufacturer();
        String originalId = laptopToUpdate.getId();
        String originalProcessor = laptopToUpdate.getProcessor();
        int originalStorage = laptopToUpdate.getStorage();
        int originalScreenSize = laptopToUpdate.getScreenSize();
        boolean modified = false;
        String modelName, id, processor;
        double price = originalPrice;
        int index = -1, storage = originalStorage, screenSize = originalScreenSize;
        Manufacturer manufacturer;

        modelName = ScannerInput.readNextLine(STR."Enter model name (press Enter to keep '\{originalModelName}'): ");
        if (!modelName.isEmpty()) {
            while (!Utilities.isValidString(modelName, 20)) {
                modelName = ScannerInput.readNextLine("Please enter a valid model name (less than 20 chars): ");
            }
            laptopToUpdate.setModelName(modelName);
            modified = true;
        }

        String priceInput = ScannerInput.readNextLine(STR."Enter price (press Enter to keep \{originalPrice}): ");
        if (!priceInput.isEmpty()) {
            try {
                price = Double.parseDouble(priceInput);
            } catch (Exception e) {
                printlnRandomColor("Invalid input.");
            }

            while (price <= 0) {
                try {
                    price = ScannerInput.readNextDouble("Please enter a valid price (> 0): ");
                } catch (Exception e) {
                    printlnRandomColor("Invalid input.");
                }
            }

            if (price != originalPrice) {
                laptopToUpdate.setPrice(price);
                modified = true;
            }
        }

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
                laptopToUpdate.setManufacturer(manufacturer);
                modified = true;
            }
        }

        id = ScannerInput.readNextLine(STR."Enter ID (press Enter to keep '\{originalId}'): ");
        if (!id.isEmpty()) {
            while (!isValidId(id)) {
                id = ScannerInput.readNextLine("ID already exists or invalid. Please enter a new ID: ");
            }
            laptopToUpdate.setId(id);
            modified = true;
        }

        processor = ScannerInput.readNextLine(STR."Enter processor (press Enter to keep '\{originalProcessor}'): ");
        if (!processor.isEmpty()) {
            while (!Utilities.validStringLength(processor, 20)) {
                processor = ScannerInput.readNextLine("Please enter a valid processor (less than 20 chars): ");
            }
            laptopToUpdate.setProcessor(processor);
            modified = true;
        }

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
                laptopToUpdate.setStorage(storage);
                modified = true;
            }
        }

        String screenSizeInput = ScannerInput.readNextLine(STR."Enter screen size (press Enter to keep \{originalScreenSize}): ");
        if (!screenSizeInput.isEmpty()) {
            try {
                screenSize = Integer.parseInt(screenSizeInput);
            } catch (NumberFormatException ignored) {
                printlnRandomColor("Invalid input.");
            }

            while (screenSize <= 0 || screenSize >= 10) {
                screenSize = ScannerInput.readNextInt("Please enter a valid screen size (> 0 and < 10): ");
            }

            if (screenSize != originalScreenSize) {
                laptopToUpdate.setScreenSize(screenSize);
                modified = true;
            }
        }

        if (modified &&
                originalModelName.equals(laptopToUpdate.getModelName()) &&
                originalPrice == laptopToUpdate.getPrice() &&
                originalManufacturer.equals(laptopToUpdate.getManufacturer()) &&
                originalId.equals(laptopToUpdate.getId()) &&
                originalProcessor.equals(laptopToUpdate.getProcessor()) &&
                originalStorage == laptopToUpdate.getStorage() &&
                originalScreenSize == laptopToUpdate.getScreenSize()) {
            printlnRandomColor("No changes.");
        } else if (modified) {
            printlnRandomColor("Laptop updated successfully.");
        } else {
            printlnRandomColor("No changes.");
        }

        printlnRandomColor(String.valueOf(laptopToUpdate));
    }

    /**
     * 列出所有笔记本。
     * Lists all laptops.
     *
     * @author Fan Xinkang
     * @since version 4.3
     */
    public static void listAllLaptops() {
        StringBuilder builder = new StringBuilder();
        for (Technology laptop : technologyList) {
            if (laptop instanceof Laptop) {
                builder.append(laptop).append("\n");
            }
        }

        if (builder.isEmpty()) {
            System.out.println("No laptops found.");
        } else {
            System.out.println(builder);
        }
    }
}
/*
 * End of LaptopCRUD Class.
 * Checked by Fan Xinkang on 2025/05/02.
 */