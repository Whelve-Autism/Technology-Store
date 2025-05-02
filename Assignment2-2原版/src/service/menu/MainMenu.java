package service.menu;

import controller.ManufacturerAPI;
import controller.TechnologyAPI;
import utils.ScannerInput;

import static service.UserInterface.printlnRandomColor;
import static service.UserInterface.randomSleep;
import static service.menu.manufacturermenu.ManufacturerMenu.runManufacturerMenu;
import static service.menu.reportmenu.ReportMenu.runReportMenu;
import static service.menu.technologymenu.TechnologyMenu.runTechnologyMenu;

public class MainMenu {

    public static void runMainMenu() {
        randomSleep();
        int option = mainMenu();
        while (option != 0) {
            switch (option) {
                case 1 -> runManufacturerMenu();
                case 2 -> runTechnologyMenu();
                case 3 -> runReportMenu();
                case 4 -> {
                    try {
                        ManufacturerAPI.save();
                        TechnologyAPI.save();
                    } catch (Exception e) {
                        printlnRandomColor(STR."Error saving Manufacturers and Technologies: \{e.getMessage()}");
                    }
                }
                case 5 -> {
                    try {
                        ManufacturerAPI.load();
                        TechnologyAPI.load();
                    } catch (Exception e) {
                        printlnRandomColor(STR."Error loading Manufacturers and Technologies: \{e.getMessage()}");
                    }
                }
                default -> printlnRandomColor(STR."Invalid option entered\{option}");
            }
            ScannerInput.readNextLine("\n Press the enter key to continue");
            option = mainMenu();
        }
    }

    private static int mainMenu() {
        return ScannerInput.readNextInt("""
               * * * * * * * * * * * * * * * * * *
               *        Technology Store         *
               * * * * * * * * * * * * * * * * * *
               * 1) Manufacturer CRUD MENU       *
               * 2) Technology CRUD MENU         *
               * 3) Reports MENU                 *
               * * * * * * * * * * * * * * * * * *
               * 4) Save all                     *
               * 5) Load all                     *
               * * * * * * * * * * * * * * * * * *
               * 0) Exit                         *
               * * * * * * * * * * * * * * * * * *
               ==>""");
    }
}