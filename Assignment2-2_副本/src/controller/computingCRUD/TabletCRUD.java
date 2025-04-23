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
        } while (!Utilities.isValidIndex(getManufacturers(), index));
        manufacturer = getManufacturers().get(index);

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
        } while (!Utilities.validRange(osIndex, 0, operatingSystems.size() - 1));
        String operatingSystem = operatingSystems.get(osIndex);

        Tablet tablet = new Tablet(modelName, price, manufacturer, id, processor, storage, operatingSystem);
        technologyList.add(tablet);
    }

    public static void updateTablet() {
        // 假设我们有一个方法来获取要更新的Tablet对象
        Tablet tabletToUpdate = getTabletToUpdate(); // 需要根据实际情况实现这个方法

        if (tabletToUpdate == null) {
            System.out.println("Tablet not found.");
            return;
        }

        String modelName, id, processor;
        double price;
        int index, storage, osIndex;
        Manufacturer manufacturer;

        // Model Name
        modelName = ScannerInput.readNextLine("Please enter the name of the tablet (press Enter to keep current: " + tabletToUpdate.getModelName() + "): ");
        if (!modelName.isEmpty()) {
            do {
                modelName = ScannerInput.readNextLine("Please enter the name of the tablet.");
            } while (!Utilities.isValidString(modelName, 20));
            tabletToUpdate.setModelName(modelName);
        }

        // Price
        String priceInput = ScannerInput.readNextLine("Please enter the price of the tablet (press Enter to keep current: " + tabletToUpdate.getPrice() + "): ");
        if (!priceInput.isEmpty()) {
            price = Double.parseDouble(priceInput);
            do {
                price = ScannerInput.readNextDouble("Please enter the price of the tablet.");
            } while (price <= 0);
            tabletToUpdate.setPrice(price);
        }

        // Manufacturer
        listAllManufacturers();
        String indexInput = ScannerInput.readNextLine("Please enter the index of the manufacturer (press Enter to keep current: " + getManufacturers().indexOf(tabletToUpdate.getManufacturer()) + "): ");
        if (!indexInput.isEmpty()) {
            index = Integer.parseInt(indexInput);
            do {
                index = ScannerInput.readNextInt("Please enter the index of the manufacturer: ");
            } while (!Utilities.isValidIndex(getManufacturers(), index));
            manufacturer = getManufacturers().get(index);
            tabletToUpdate.setManufacturer(manufacturer);
        }

        // ID
        id = ScannerInput.readNextLine("Please enter the id of the tablet (press Enter to keep current: " + tabletToUpdate.getId() + "): ");
        if (!id.isEmpty()) {
            do {
                id = ScannerInput.readNextLine("Please enter the id of the tablet.");
            } while (!isValidId(id));
            tabletToUpdate.setId(id);
        }

        // Processor
        processor = ScannerInput.readNextLine("Please enter the processor of the tablet (press Enter to keep current: " + tabletToUpdate.getProcessor() + "): ");
        if (!processor.isEmpty()) {
            do {
                processor = ScannerInput.readNextLine("Please enter the processor of the tablet.");
            } while (!Utilities.isValidString(processor, 20));
            tabletToUpdate.setProcessor(processor);
        }

        // Storage
        String storageInput = ScannerInput.readNextLine("Please enter the storage of the tablet (press Enter to keep current: " + tabletToUpdate.getStorage() + "): ");
        if (!storageInput.isEmpty()) {
            storage = Integer.parseInt(storageInput);
            do {
                storage = ScannerInput.readNextInt("Please enter the storage of the tablet.");
            } while (storage % 8 != 0);
            tabletToUpdate.setStorage(storage);
        }

        // Operating System
        Tablet.listAllOperatingSystems();
        String osIndexInput = ScannerInput.readNextLine("Please enter the index of the operating system (press Enter to keep current: " + operatingSystems.indexOf(tabletToUpdate.getOperatingSystem()) + "): ");
        if (!osIndexInput.isEmpty()) {
            osIndex = Integer.parseInt(osIndexInput);
            do {
                osIndex = ScannerInput.readNextInt("Please enter the index of the operating system: ");
            } while (!Utilities.validRange(osIndex, 0, operatingSystems.size() - 1));
            String operatingSystem = operatingSystems.get(osIndex);
            tabletToUpdate.setOperatingSystem(operatingSystem);
        }

        // 假设我们有一个方法来保存更新后的Tablet对象
        saveTablet(tabletToUpdate); // 需要根据实际情况实现这个方法
    }

    // 需要根据实际情况实现的方法
    private static Tablet getTabletToUpdate() {
        // 实现获取要更新的Tablet对象的逻辑
        return null; // 示例返回null
    }

    // 需要根据实际情况实现的方法
    private static void saveTablet(Tablet tablet) {
        // 实现保存更新后的Tablet对象的逻辑
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
