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

public class LaptopCRUD {

    public static void addLaptop() {
        String modelName, id, processor;
        double price;
        int index, storage, screenSize;
        Manufacturer manufacturer;

        if (getManufacturers().isEmpty()) {
            System.out.println("No manufacturers found. Please add a manufacturer first.");
            return;
        }

        do {
            modelName = ScannerInput.readNextLine("Please enter the name of the laptop.");
        } while (!Utilities.isValidString(modelName, 20));

        do {
            price = ScannerInput.readNextDouble("Please enter the price of the laptop.");
        } while (price <= 0);

        listAllManufacturers();
        do {
            index = ScannerInput.readNextInt("Please enter the index of the manufacturer: ");
        } while (!Utilities.isValidIndex(getManufacturers(), index));
        manufacturer = getManufacturers().get(index);

        do {
            id = ScannerInput.readNextLine("Please enter the id of the laptop.");
        } while (!isValidId(id));

        do {
            processor = ScannerInput.readNextLine("Please enter the processor of the laptop.");
        } while (!Utilities.validStringLength(processor, 20));

        do {
            storage = ScannerInput.readNextInt("Please enter the storage of the laptop.");
        } while (storage % 8 != 0);

        do {
            screenSize = ScannerInput.readNextInt("Please enter the screen size of the laptop.");
        } while (screenSize <= 10);

        Laptop laptop = new Laptop(modelName, price, manufacturer, id, processor, storage, screenSize);
        technologyList.add(laptop);
    }

    public static void updateLaptop() {
        Laptop laptopToUpdate = getLaptopToUpdate(); // 获取要更新的 Laptop 对象

        if (laptopToUpdate == null) {
            System.out.println("Laptop not found."); // 修正错误信息
            return;
        }

        String modelName, id, processor;
        double price;
        int index, storage, screenSize;
        Manufacturer manufacturer;

        // Model Name
        modelName = ScannerInput.readNextLine("Please enter the name of the laptop (press Enter to keep current: " + laptopToUpdate.getModelName() + "): ");
        if (!modelName.isEmpty()) {
            do {
                modelName = ScannerInput.readNextLine("Please enter the name of the laptop.");
            } while (!Utilities.isValidString(modelName, 20));
            laptopToUpdate.setModelName(modelName);
        }

        // Price
        String priceInput = ScannerInput.readNextLine("Please enter the price of the laptop (press Enter to keep current: " + laptopToUpdate.getPrice() + "): ");
        if (!priceInput.isEmpty()) {
            price = Double.parseDouble(priceInput);
            do {
                price = ScannerInput.readNextDouble("Please enter the price of the laptop.");
            } while (price <= 0);
            laptopToUpdate.setPrice(price);
        }

        // Manufacturer
        listAllManufacturers();
        String indexInput = ScannerInput.readNextLine("Please enter the index of the manufacturer (press Enter to keep current: " + getManufacturers().indexOf(laptopToUpdate.getManufacturer()) + "): ");
        if (!indexInput.isEmpty()) {
            index = Integer.parseInt(indexInput);
            do {
                index = ScannerInput.readNextInt("Please enter the index of the manufacturer: ");
            } while (!Utilities.isValidIndex(getManufacturers(), index));
            manufacturer = getManufacturers().get(index);
            laptopToUpdate.setManufacturer(manufacturer);
        }

        // ID
        id = ScannerInput.readNextLine("Please enter the id of the laptop (press Enter to keep current: " + laptopToUpdate.getId() + "): ");
        if (!id.isEmpty()) {
            do {
                id = ScannerInput.readNextLine("Please enter the id of the laptop.");
            } while (!isValidId(id));
            laptopToUpdate.setId(id);
        }

        // Processor
        processor = ScannerInput.readNextLine("Please enter the processor of the laptop (press Enter to keep current: " + laptopToUpdate.getProcessor() + "): ");
        if (!processor.isEmpty()) {
            do {
                processor = ScannerInput.readNextLine("Please enter the processor of the laptop.");
            } while (!Utilities.validStringLength(processor, 20));
            laptopToUpdate.setProcessor(processor);
        }

        // Storage
        String storageInput = ScannerInput.readNextLine("Please enter the storage of the laptop (press Enter to keep current: " + laptopToUpdate.getStorage() + "): ");
        if (!storageInput.isEmpty()) {
            storage = Integer.parseInt(storageInput);
            do {
                storage = ScannerInput.readNextInt("Please enter the storage of the laptop.");
            } while (storage % 8 != 0);
            laptopToUpdate.setStorage(storage);
        }

        // Screen Size
        String screenSizeInput = ScannerInput.readNextLine("Please enter the screen size of the laptop (press Enter to keep current: " + laptopToUpdate.getScreenSize() + "): ");
        if (!screenSizeInput.isEmpty()) {
            screenSize = Integer.parseInt(screenSizeInput);
            do {
                screenSize = ScannerInput.readNextInt("Please enter the screen size of the laptop.");
            } while (screenSize <= 10);
            laptopToUpdate.setScreenSize(screenSize);
        }

        // 保存更新后的 Laptop 对象
        saveLaptop(laptopToUpdate);
    }

    public static void saveLaptop(Laptop laptop) {
    }

    public static Laptop getLaptopToUpdate() {
        return null;
    }

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