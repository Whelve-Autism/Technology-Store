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

public class SmartWatchCRUD {

    public void addSmartWatch() {
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
        } while (!Utilities.isValidIndex(getManufacturers(), index));
        manufacturer = getManufacturers().get(index);

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

    public static void updateSmartWatch() {
        // 假设我们有一个方法来获取要更新的SmartWatch对象
        SmartWatch smartWatchToUpdate = getSmartWatchToUpdate(); // 需要根据实际情况实现这个方法

        if (smartWatchToUpdate == null) {
            printlnRandomColor("Smart watch not found.");
            return;
        }

        String modelName, id, material, size;
        double price;
        int index, diIndex;
        Manufacturer manufacturer;

        // Model Name
        modelName = ScannerInput.readNextLine("Please enter the name of the smart watch (press Enter to keep current: " + smartWatchToUpdate.getModelName() + "): ");
        if (!modelName.isEmpty()) {
            do {
                modelName = ScannerInput.readNextLine("Please enter the name of the smart watch.");
            } while (!Utilities.isValidString(modelName, 20));
            smartWatchToUpdate.setModelName(modelName);
        }

        // Price
        String priceInput = ScannerInput.readNextLine("Please enter the price of the smart watch (press Enter to keep current: " + smartWatchToUpdate.getPrice() + "): ");
        if (!priceInput.isEmpty()) {
            price = Double.parseDouble(priceInput);
            do {
                price = ScannerInput.readNextDouble("Please enter the price of the smart watch.");
            } while (price <= 0);
            smartWatchToUpdate.setPrice(price);
        }

        // Manufacturer
        listAllManufacturers();
        String indexInput = ScannerInput.readNextLine("Please enter the index of the manufacturer (press Enter to keep current: " + getManufacturers().indexOf(smartWatchToUpdate.getManufacturer()) + "): ");
        if (!indexInput.isEmpty()) {
            index = Integer.parseInt(indexInput);
            do {
                index = ScannerInput.readNextInt("Please enter the index of the manufacturer: ");
            } while (!Utilities.isValidIndex(getManufacturers(), index));
            manufacturer = getManufacturers().get(index);
            smartWatchToUpdate.setManufacturer(manufacturer);
        }

        // ID
        id = ScannerInput.readNextLine("Please enter the id of the smart watch (press Enter to keep current: " + smartWatchToUpdate.getId() + "): ");
        if (!id.isEmpty()) {
            do {
                id = ScannerInput.readNextLine("Please enter the id of the smart watch.");
            } while (!isValidId(id));
            smartWatchToUpdate.setId(id);
        }

        // Material
        material = ScannerInput.readNextLine("Please enter the material of the smart watch (press Enter to keep current: " + smartWatchToUpdate.getMaterial() + "): ");
        if (!material.isEmpty()) {
            do {
                material = ScannerInput.readNextLine("Please enter the material of the smart watch.");
            } while (!Utilities.isValidString(material, 20));
            smartWatchToUpdate.setMaterial(material);
        }

        // Size
        size = ScannerInput.readNextLine("Please enter the size of the smart watch (press Enter to keep current: " + smartWatchToUpdate.getSize() + "): ");
        if (!size.isEmpty()) {
            do {
                size = ScannerInput.readNextLine("Please enter the size of the smart watch.");
            } while (!Utilities.isValidString(size, 20));
            smartWatchToUpdate.setSize(size);
        }

        // Display Type
        SmartWatch.listAllDisplayTypes();
        String diIndexInput = ScannerInput.readNextLine("Please enter the index of the display type (press Enter to keep current: " + SmartWatch.displayTypes.indexOf(smartWatchToUpdate.getDisplayType()) + "): ");
        if (!diIndexInput.isEmpty()) {
            do {
                diIndex = ScannerInput.readNextInt("Please enter the index of the display type: ");
            } while (!Utilities.isValidIndex(SmartWatch.displayTypes, diIndex));
            String displayType = SmartWatch.displayTypes.get(diIndex);
            smartWatchToUpdate.setDisplayType(displayType);
        }

        // 假设我们有一个方法来保存更新后的SmartWatch对象
        saveSmartWatch(smartWatchToUpdate); // 需要根据实际情况实现这个方法
    }

    // 需要根据实际情况实现的方法
    private static SmartWatch getSmartWatchToUpdate() {
        // 实现获取要更新的SmartWatch对象的逻辑
        return null; // 示例返回null
    }

    // 需要根据实际情况实现的方法
    private static void saveSmartWatch(SmartWatch smartWatch) {
        // 实现保存更新后的SmartWatch对象的逻辑
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
