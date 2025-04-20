package main;

import controllers.ManufacturerAPI;
import controllers.TechnologyDeviceAPI;

import models.*;
import utils.ScannerInput;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 此类用于启动应用程序，并初始化应用程序所需的资源。
 * This class is used to start the application and initialize the required resources.
 *
 * @author Guoqing Lu, Fan Xinkang
 * @version 4.3
 * @since version 0.0
 */
public class Driver {

    private TechnologyDeviceAPI techAPI;
    private ManufacturerAPI manufacturerAPI;

    /**
     * 主函数，用于启动应用程序。
     * The main function is used to start the application and initialize the required resources.
     *
     * @param args 传递给应用程序的命令行参数。
     *             The command line arguments passed to the application.
     * @throws Exception 抛出异常。
     *                   The exception thrown.
     * @author Guoqing Lu
     * @since version 0.0
     */
    public static void main(String[] args) throws Exception {
        new Driver().start();
    }

    //----------------------
    //      Main Menu
    //----------------------

    /**
     * 启动应用程序，初始化应用程序所需的资源。
     * This method is used to start the application and initialize the required resources.
     *
     * @author Guoqing Lu, Fan Xinkang
     * @since version 0.0
     */
    public void start() {
        manufacturerAPI = new ManufacturerAPI(new File("manufacturers.xml"));
        File techFile = new File("technologyDevices.xml");

        if (!techFile.exists()) {
            System.err.println("Error: technologyDevices.xml not found in the current directory.");
            return;
        }

        techAPI = new TechnologyDeviceAPI(techFile);

        try {
            techAPI.load();
        } catch (FileNotFoundException e) {
            System.err.println("technologyDevices.xml not found. Please ensure the file exists in the current directory.");
        } catch (IOException e) {
            System.err.println(STR."Error in loading technologyDevices.xml: \{e.getMessage()}");
        } catch (Exception e) {
            System.err.println(STR."Unexpected error: \{e.getMessage()}");
            throw new RuntimeException(e);
        }

        runMainMenu();
    }

    /**
     * 主菜单，显示应用程序的主菜单选项。
     * The main menu, which displays the options for the main menu.
     *
     * @return 选择的选项。
     * @author Fan Xinkang
     * @since version 0.0
     */
    private int mainMenu() {
        System.out.println("""
                         -------Technology Store--------
                        |  1) Manufacturer CRUD MENU     |
                        |  2) Technology CRUD MENU       |
                        |  3) Reports MENU               |
                        |--------------------------------|
                        |  4) Search Manufacturers       |
                        |  5) Search Technology Devices  |
                        |  6) Sort Technology Devices    |
                        |--------------------------------|
                        |  10) Save all                  |
                        |  11) Load all                  |
                        |--------------------------------|
                        |  0) Exit                       |
                         --------------------------------""");
        return ScannerInput.readNextInt("==>> ");
    }

    /**
     * 运行主菜单，显示应用程序的主菜单选项，并根据用户选择执行相应的操作。
     * This method runs the main menu, which displays the options for the main menu, and executes the corresponding operations based on the user's selection.
     *
     * @author Guoqing Lu, Fan Xinkang
     * @since version 0.0
     */
    private void runMainMenu() {
        int option = mainMenu();
        while (option != 0) {
            switch (option) {
                case 1->  runManufacturerMenu();
                case 2->  runTechMenu();
                case 3->  runReportsMenu();
                case 4-> {
                    String manufacturerName = ScannerInput.readNextLine("Please enter the manufacturer name: ");
                    System.out.println(manufacturerAPI.listAllByManufacturerName(manufacturerName));
                }
                case 5-> {
                    String id = ScannerInput.readNextLine("Please enter the technology ID: ");
                    System.out.println(techAPI.getTechnologyDeviceById(id));
                }
                case 6-> {
                    System.out.println("""
                            1) Sort by price ascending.
                            2) Sort by price descending.
                    """);
                    int sortOption = ScannerInput.readNextInt("==>> ");
                    switch (sortOption) {
                        case 1-> techAPI.sortByPriceAscending();
                        case 2-> techAPI.sortByPriceDescending();
                        default-> System.out.println("Invalid option entered");
                    }
                }
                case 10-> {
                    try {
                        techAPI.save();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
                case 11-> {
                    try {
                        techAPI.load();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
                default ->  System.out.println(STR."Invalid option entered\{option}");
            }
            ScannerInput.readNextLine("\n Press the enter key to continue");
            option = mainMenu();
        }
        exitApp();
    }

    /**
     * 退出应用程序，保存应用程序所需的资源。
     * This method is used to exit the application and save the required resources.
     *
     * @author Guoqing Lu, Fan Xinkang
     * @since version 0.0
     */
    private void exitApp() {

        try {
            techAPI.save();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println("Exiting....");
        System.exit(0);
    }

    //----------------------
    //  Manufacturer Menu
    //----------------------

    /**
     * 运行制造商菜单，显示制造商菜单选项，并根据用户选择执行相应的操作。
     * This method runs the manufacturer menu, which displays the options for the manufacturer menu, and executes the corresponding operations based on the user's selection.
     *
     * @author Guoqing Lu, Fan Xinkang
     * @since version 0.0
     */
    private void runManufacturerMenu() {
        int option = manufacturerMenu();
        while (option != 0) {
            switch (option) {
                case 1 -> addManufacturer();
                case 2 -> deleteManufacturer();
                case 3 -> updateManufacturer();
                case 4 -> System.out.println(manufacturerAPI.listManufacturers());
                case 5-> findManufacturer();
                case 6-> listByManufacturerName();
                default->  System.out.println(STR."Invalid option entered\{option}");
            }
            ScannerInput.readNextLine("\n Press the enter key to continue");
            option = manufacturerMenu();
        }
        runMainMenu();
    }

    /**
     * 显示制造商菜单选项。
     * This method displays the options for the manufacturer menu.
     *
     * @return 返回用户选择的选项。
     *         The selected option.
     * @author Guoqing Lu
     * @since version 0.0
     */
    private int manufacturerMenu() {
        System.out.println("""
                --------Manufacturer Menu---------
               |  1) Add a manufacturer           |
               |  2) Delete a manufacturer        |
               |  3) Update manufacturer details  |
               |  4) List all manufacturers       |
               |  5) Find a manufacturer          |
               |  0) Return to main menu          |
                ----------------------------------""");
        return ScannerInput.readNextInt("==>>");
    }

    /**
     * 添加制造商。
     * This method adds a manufacturer.
     *
     * @author Fan Xinkang
     * @since version 4.0
     */
    private void addManufacturer() {
        String manufacturerName = ScannerInput.readNextLine("Please enter the manufacturer name: ");
        int manufacturerNumEmployees = ScannerInput.readNextInt("Please enter the number of employees: ");

        if (manufacturerAPI.addManufacturer(new Manufacturer(manufacturerName, manufacturerNumEmployees))){
            System.out.println("Add successful");
        } else {
            System.out.println("Add not successful");
        }
    }

    /**
     * 删除制造商。
     * This method deletes a manufacturer.
     *
     * @author Fan Xinkang
     * @since version 4.0
     */
    private void deleteManufacturer() {
        String manufacturerName = ScannerInput.readNextLine("Please enter the manufacturer name: ");
        if (manufacturerAPI.removeManufacturerByName(manufacturerName) != null) {
            System.out.println("Delete successful");
        } else {
            System.out.println("Delete not successful");
        }
    }

    /**
     * 更新制造商。
     * This method updates a manufacturer.
     *
     * @author Fan Xinkang
     * @since version 4.0
     */
    private void updateManufacturer(){
        Manufacturer manufacturer = getManufacturerByName();
        if (manufacturer != null){
            int numEmployees= ScannerInput.readNextInt("Please enter number of Employees: ");
            if (manufacturerAPI.updateManufacturer(manufacturer.getManufacturerName(), numEmployees)) {
                System.out.println("Number of Employees Updated");
            } else {
                System.out.println("Number of Employees not Updated");
            }
        } else {
            System.out.println("Manufacturer name is invalid");
        }
    }

    /**
     * 通过名称获取制造商。
     * This method retrieves a manufacturer by its name.
     *
     * @author Fan Xinkang
     * @since version 4.0
     */
    private void findManufacturer(){
        Manufacturer developer = getManufacturerByName();
        if (developer == null){
            System.out.println("No such manufacturer exists");
        }
        else{
            System.out.println(developer);
        }
    }

    /**
     * 通过名称获取制造商。
     * This method retrieves a manufacturer by its name.
     *
     * @author Fan Xinkang
     * @since version 4.0
     */
    private void listByManufacturerName(){
        String manufacturer = ScannerInput.readNextLine("Enter the manufacturer's name: ");

        System.out.println(manufacturerAPI.listAllByManufacturerName(manufacturer));
    }


    //---------------------
    //  Tech Store Menu
    //---------------------

    /**
     * 运行技术设备菜单，显示技术设备菜单选项，并根据用户选择执行相应的操作。
     * This method runs the technology device menu, which displays the options for the technology device menu, and executes the corresponding operations based on the user's selection.
     *
     * @author Guoqing Lu, Fan Xinkang
     * @since version 0.0
     */
    private void runTechMenu() {
        int option = techAPIMenu();
        while (option != 0) {
            switch (option) {
                case 1 -> addTechnologyDevice();
                case 2 -> deleteTechnologyDevice();
                case 3 -> System.out.println(techAPI.listAllTechnologyDevices());
                case 4 -> updateTechnologyDevice();
                default->  System.out.println(STR."Invalid option entered\{option}");
            }
            ScannerInput.readNextLine("\n Press the enter key to continue");
            option = techAPIMenu();
        }
        runMainMenu();
    }

    /**
     * 显示技术设备菜单选项。
     * This method displays the options for the technology device menu.
     *
     * @return 返回用户选择的选项。
     *         The selected option.
     * @author Guoqing Lu
     * @since version 0.0
     */
    private int techAPIMenu() {
        System.out.println(""" 
                -----Technology Store Menu-----
               | 1) Add a Tech Device           |
               | 2) Delete a Tech Device        |
               | 3) List all Tech Devices       |
               | 4) Update Tech Device          |
               | 0) Return to main menu         |
                ----------------------------""");
        return ScannerInput.readNextInt("==>>");
    }

    /**
     * 添加技术设备。
     * This method adds a technology device.
     *
     * @author Fan Xinkang
     * @since version 4.0
     */
    private void addTechnologyDevice() {
        System.out.println("Select the type of technology device to add:");
        System.out.println("1) Computer Device");
        System.out.println("2) Wearable Device");
        System.out.println("0) Back to the previous level of directory.");
        int deviceTypeChoice = ScannerInput.readNextInt("==>> ");

        while (deviceTypeChoice != 0) {
            switch (deviceTypeChoice) {
                case 1:
                    addComputingDevice();
                    break;
                case 2:
                    addWearableDevice();
                    break;
                default:
                    System.out.println("Invalid option entered.");
                    break;
            }
            ScannerInput.readNextLine("\nPress the enter key to continue");
            deviceTypeChoice = ScannerInput.readNextInt("Select the type of technology device to add:");
        }
        runTechMenu();
    }

    /**
     * 添加计算设备。
     * This method adds a computing device.
     *
     * @author Fan Xinkang
     * @since version 4.0
     */
    private void addComputingDevice() {
        System.out.println("Select the type of computing device:");
        System.out.println("1) Tablet");
        System.out.println("0) Back to the previous level of directory.");
        int computingChoice = ScannerInput.readNextInt("==>> ");

        Technology technology = null;

        while (computingChoice != 0) {
            switch (computingChoice) {
                case 1:
                    technology = addTablet();
                    break;
                default:
                    System.out.println("Invalid option entered.");
                    break;
            }
            if (technology != null && techAPI.addTechnologyDevice(technology)) {
                System.out.println("Add successful");
            } else {
                System.out.println("Add not successful");
            }
            ScannerInput.readNextLine("\nPress the enter key to continue");
            System.out.println("Select the type of computing device:");
            System.out.println("1) Tablet");
            System.out.println("0) Back to the previous level of directory.");
            computingChoice = ScannerInput.readNextInt("Select the type of computing device:");
        }
        addTechnologyDevice();
    }

    /**
     * 添加穿戴设备。
     * This method adds a wearable device.
     *
     * @author Fan Xinkang
     * @since version 4.0
     */
    private void addWearableDevice() {
        System.out.println("Select the type of wearable device:");
        System.out.println("1) SmartBand");
        System.out.println("2) SmartWatch");
        System.out.println("0) Back to the previous level of directory.");
        int wearableChoice = ScannerInput.readNextInt("==>> ");

        Technology technology = null;
        while (wearableChoice != 0) {
            switch (wearableChoice) {
                case 1:
                    technology = addSmartBand();
                    break;
                case 2:
                    technology = addSmartWatch();
                    break;
                default:
                    System.out.println("Invalid option entered.");
                    break;
            }
            if (technology != null && techAPI.addTechnologyDevice(technology)) {
                System.out.println("Add successful");
            } else {
                System.out.println("Add not successful");
            }
            ScannerInput.readNextLine("\nPress the enter key to continue");
            System.out.println("Select the type of wearable device:");
            System.out.println("1) SmartBand");
            System.out.println("2) SmartWatch");
            System.out.println("0) Back to the previous level of directory.");
            wearableChoice = ScannerInput.readNextInt("Select the type of wearable device:");
        }
        addTechnologyDevice();
    }

    /**
     * 添加平板电脑。
     * This method adds a tablet.
     *
     * @author Fan Xinkang
     * @since version 4.0
     */
    private Tablet addTablet() {
        TechnologyInput input = getTechnologyInputInformation();
        if (input == null) {
            return null;
        }

        String processor = ScannerInput.readNextLine("Please enter the processor: ");
        int storage = ScannerInput.readNextInt("Please enter the storage (GB): ");
        String operatingSystem = ScannerInput.readNextLine("Please enter the operating system: ");
        return new Tablet(input.modelName, input.price, input.manufacturer, input.id, processor, storage, operatingSystem);
    }

    /**
     * 添加智能手环。
     * This method adds a smart band.
     *
     * @author Fan Xinkang
     * @since version 4.0
     */
    private SmartBand addSmartBand() {
        TechnologyInput input = getTechnologyInputInformation();
        if (input == null) {
            return null;
        }

        String material = ScannerInput.readNextLine("Please enter the material: ");
        String size = ScannerInput.readNextLine("Please enter the size: ");
        boolean heartRateMonitor = ScannerInput.readNextBoolean("Does it include a heart rate monitor? (y/n): ");
        return new SmartBand(input.modelName, input.price, input.manufacturer, input.id, material, size, heartRateMonitor);
    }

    /**
     * 添加智能手表。
     * This method adds a smart watch.
     *
     * @author Fan Xinkang
     * @since version 4.0
     */
    private SmartWatch addSmartWatch() {
        TechnologyInput input = getTechnologyInputInformation();
        if (input == null) {
            return null;
        }

        String material = ScannerInput.readNextLine("Please enter the material: ");
        String size = ScannerInput.readNextLine("Please enter the size: ");
        String displayType = ScannerInput.readNextLine("Please enter the display type: ");
        return new SmartWatch(input.modelName, input.price, input.manufacturer, input.id, material, size, displayType);
    }

    /**
     * 获取技术设备的输入信息。
     * This method gets the input information for a technology device.
     *
     * @author Fan Xinkang
     * @since version 4.0
     */
    private TechnologyInput getTechnologyInputInformation() {
        String modelName = ScannerInput.readNextLine("Please enter the model name: ");
        double price = ScannerInput.readNextDouble("Please enter the price: ");
        String manufacturerName = ScannerInput.readNextLine("Please enter the manufacturer name: ");
        String id = ScannerInput.readNextLine("Please enter the id: ");
        Manufacturer manufacturer = manufacturerAPI.getManufacturerByName(manufacturerName);

        if (manufacturer == null) {
            System.out.println("Invalid manufacturer name.");
            return null;
        }

        if (!isValidId(id)) {
            System.out.println("Invalid id.");
            return null;
        }

        return new TechnologyInput(modelName, price, manufacturer, id);
    }

    /**
     * 封装技术设备的输入信息。
     * This class wraps the input information for a technology device.
     *
     * @author Fan Xinkang
     * @since version 4.0
     */
    private static class TechnologyInput {
        String modelName;
        double price;
        Manufacturer manufacturer;
        String id;

        TechnologyInput(String modelName, double price, Manufacturer manufacturer, String id) {
            this.modelName = modelName;
            this.price = price;
            this.manufacturer = manufacturer;
            this.id = id;
        }
    }

    /**
     * 删除技术设备。
     * This method deletes a technology device.
     *
     * @author Fan Xinkang
     * @since version 4.0
     */
    private void deleteTechnologyDevice() {
        System.out.println(techAPI.getTechnologyList());
        int index = ScannerInput.readNextInt("Please enter the index of the technology device to delete: ");
        if (techAPI.deleteTechnologyByIndex(index) != null) {
            System.out.println("Delete successful");
        } else {
            System.out.println("Delete not successful");
        }
    }

    /**
     * 更新技术设备。
     * This method updates a technology device.
     *
     * @author Fan Xinkang
     * @since version 4.2
     */
    private void updateTechnologyDevice() {
        System.out.println(techAPI.getTechnologyList());
        int index = ScannerInput.readNextInt("Please enter the index of the technology device to update: ");
        Technology deviceToUpdate = techAPI.getTechnologyByIndex(index);

        if (deviceToUpdate == null) {
            System.out.println("Invalid index. Update not successful.");
            return;
        }

        TechnologyInput newInput = getTechnologyInputInformation();
        if (newInput == null) {
            System.out.println("Update not successful.");
            return;
        }

        String newId = newInput.id.toLowerCase();

        if (!newId.equals(deviceToUpdate.getId().toLowerCase()) && techAPI.getTechnologyDeviceById(newId) != null) {
            System.out.println("ID already exists. Please enter a unique ID.");
            return;
        }

        boolean updateSuccessful = false;
        switch (deviceToUpdate) {
            case Tablet tablet -> updateSuccessful = techAPI.updateTablet(deviceToUpdate.getId(), new Tablet(
                    newInput.modelName, newInput.price, newInput.manufacturer, newId,
                    ScannerInput.readNextLine("Please enter the processor: "),
                    ScannerInput.readNextInt("Please enter the storage (GB): "),
                    ScannerInput.readNextLine("Please enter the operating system: ")
            ));
            case SmartWatch smartWatch ->
                    updateSuccessful = techAPI.updateSmartWatch(deviceToUpdate.getId(), new SmartWatch(
                            newInput.modelName, newInput.price, newInput.manufacturer, newId,
                            ScannerInput.readNextLine("Please enter the material: "),
                            ScannerInput.readNextLine("Please enter the size: "),
                            ScannerInput.readNextLine("Please enter the display type: ")
                    ));
            case SmartBand smartBand ->
                    updateSuccessful = techAPI.updateSmartBand(deviceToUpdate.getId(), new SmartBand(
                            newInput.modelName, newInput.price, newInput.manufacturer, newId,
                            ScannerInput.readNextLine("Please enter the material: "),
                            ScannerInput.readNextLine("Please enter the size: "),
                            ScannerInput.readNextBoolean("Does it include a heart rate monitor? (y/n): ")
                    ));
            default -> {
                System.out.println("Invalid technology device type.");
            }
        }

        if (updateSuccessful) {
            System.out.println("Update successful");
        } else {
            System.out.println("Update not successful");
        }
    }

    //---------------------
    //  Reports Menu
    //---------------------

    /**
     * 运行报告菜单。
     * This method runs the reports menu.
     *
     * @author Guoqing Lu, Fan Xinkang
     * @since version 0.0
     */
    public void runReportsMenu(){
        int option = reportsMenu();
        while (option != 0) {
            switch (option) {
                case 1-> runManufacturerReports();
                case 2-> runTechnologyReports();
                default->  System.out.println(STR."Invalid option entered\{option}");
            }
            ScannerInput.readNextLine("\n Press the enter key to continue");
            option = reportsMenu();
        }
        runMainMenu();
    }

    /**
     * 运行报告菜单。
     * This method runs the reports menu.
     *
     * @author Guoqing Lu
     * @since version 0.0
     */
    private int reportsMenu() {
        System.out.println(""" 
                --------Reports Menu ---------
               | 1) Manufacturers Overview    |
               | 2) Technology Overview       |
               | 0) Return to main menu       |
                 -----------------------------""");
        return ScannerInput.readNextInt("==>>");
    }

    /**
     * 运行制造商报告菜单。
     * This method runs the manufacturer reports menu.
     *
     * @author Fan Xinkang
     * @since version 4.0
     */
    private int manufacturerReportsMenu() {
        System.out.println(""" 
                ---------- Manufacturers Reports Menu  -------------
               | 1) List Manufacturers                              |
               | 2) List Manufacturers from a given manufacturer    |
               | 3) List Manufacturers by a given name              |
               | 0) Return to Reports Menu                          |
                 ---------------------------------------------------""");
        return ScannerInput.readNextInt("==>>");
    }

    /**
     * 运行制造商报告菜单。
     * This method runs the manufacturer reports menu.
     *
     * @author Fan Xinkang
     * @since version 4.0
     */
    private void runManufacturerReports() {
        int option = manufacturerReportsMenu();
        while (option != 0) {
            switch (option) {
                case 1-> System.out.println(manufacturerAPI.listManufacturers());
                case 2 -> {
                    String manufacturerName = ScannerInput.readNextLine("Enter the manufacturer's name: ");
                    Manufacturer manufacturer = manufacturerAPI.getManufacturerByName(manufacturerName);
                    if (manufacturer != null) {
                        System.out.println(techAPI.listAllTechDevicesByChosenManufacturer(manufacturer));
                    } else {
                        System.out.println("No such manufacturer exists.");
                    }
                }
                case 3 -> {
                    String manufacturerName = ScannerInput.readNextLine("Enter the manufacturer's name: ");
                    System.out.println(manufacturerAPI.listAllByManufacturerName(manufacturerName));
                }
                default->  System.out.println(STR."Invalid option entered\{option}");
            }
            ScannerInput.readNextLine("\n Press the enter key to continue");
            option =  manufacturerReportsMenu();
        }
        runReportsMenu();
    }

    /**
     * 运行技术报告菜单。
     * This method runs the technology reports menu.
     *
     * @author Fan Xinkang
     * @since version 4.0
     */
    private int technologyReportsMenu() {
        System.out.println("""
        ------------- Technology Reports Menu  --------------
        | 1) List all technology                            |
        | 2) List all SmartBands                            |
        | 3) List all Smart Watch                           |
        | 4) List all Tablets                               |
        | 5) List all devices above a price                 |
        | 6) List all devices below a price                 |
        | 7) List all tablets by operating system           |
        | 0) Return to Reports Menu                         |
        -----------------------------------------------------
        """);
        return ScannerInput.readNextInt("==>>");
    }

    /**
     * 运行技术报告菜单。
     * This method runs the technology reports menu.
     *
     * @author Fan Xinkang
     * @since version 4.0
     */
    private void runTechnologyReports() {
        int option = technologyReportsMenu();
        while (option != 0) {
            switch (option) {
                case 1-> System.out.println(techAPI.listAllTechnologyDevices());
                case 2-> System.out.println(techAPI.listAllSmartBands());
                case 3-> System.out.println(techAPI.listAllSmartWatches());
                case 4-> System.out.println(techAPI.listAllTablets());
                case 5-> {
                    double price = ScannerInput.readNextDouble("Please enter the price: ");
                    System.out.println(techAPI.listAllTechnologyAbovePrice(price));
                }
                case 6-> {
                    double price = ScannerInput.readNextDouble("Please enter the price: ");
                    System.out.println(techAPI.listAllTechnologyBelowPrice(price));
                }
                case 7-> {
                    String os = ScannerInput.readNextLine("Please enter the operating system: ");
                    System.out.println(techAPI.listAllTabletsByOperatingSystem(os));
                }
            }
            ScannerInput.readNextLine("\n Press the enter key to continue");
            option = technologyReportsMenu();
        }
        runReportsMenu();
    }

    /**
     * 获取制造商名称。
     * This method gets the manufacturer name.
     *
     * @author Guoqing Lu
     * @since version 0.0
     */
    private Manufacturer getManufacturerByName() {
        String manufacturerName = ScannerInput.readNextLine("Please enter the manufacturer's name: ");
        if (manufacturerAPI.isValidManufacturer(manufacturerName)) {
            return manufacturerAPI.getManufacturerByName(manufacturerName);
        } else {
            return null;
        }
    }

    /**
     * 验证ID是否有效。
     * This method validates the ID.
     *
     * @author Fan Xinkang
     * @since version 4.3
     */
    private boolean isValidId(String idToValidate) {
        for (Technology techDev : techAPI.getTechnologyList()) {
            if (techDev.getId().equalsIgnoreCase(idToValidate)) {
                return false;
            }
        }
        return true;
    }
}
/*
 * End of main.Driver Class.
 * Checked by Fan Xinkang on 2025/04/20.
 */