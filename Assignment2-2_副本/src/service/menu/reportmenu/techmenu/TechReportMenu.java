package service.menu.reportmenu.techmenu;

import utils.ScannerInput;

import static controller.TechnologyAPI.*;
import static controller.computingCRUD.LaptopCRUD.listAllLaptops;
import static controller.computingCRUD.TabletCRUD.listAllTablets;
import static controller.wearableCRUD.SmartBandCRUD.listAllSmartBands;
import static controller.wearableCRUD.SmartWatchCRUD.listAllSmartWatches;
import static service.UserInterface.printlnRandomColor;
import static service.UserInterface.randomSleep;
import static service.menu.reportmenu.ReportMenu.runReportMenu;

public class TechReportMenu {

    public static void runTechReportMenu() {
        randomSleep();
        int option = technologyReportMenu();
        while (option != 0) {
            switch (option) {
                case 1 -> listAllTechnologies();
                case 2 -> listAllLaptops();
                case 3 -> listAllTablets();
                case 4 -> listAllSmartBands();
                case 5 -> listAllSmartWatches();
                case 6 -> listTechnologiesAboveAPrice();
                case 7 -> listTechnologiesBelowAPrice();
                default -> printlnRandomColor(STR."Invalid option entered\{option}");
            }
            ScannerInput.readNextLine("\n Press the enter key to continue");
            option = technologyReportMenu();
        }
        runReportMenu();
    }

    private static int technologyReportMenu() {
        return ScannerInput.readNextInt("""
               * * * * * * * * * * * * * * * * * * * *
               *       Technology Report Menu        *
               * * * * * * * * * * * * * * * * * * * *
               * 1) List all technology              *
               * 2) List all laptops                 *
               * 3) List all tablets                 *
               * 4) List all Smart bands             *
               * 5) List all Smart watches           *
               * 6) List all devices above a price   *
               * 7) List all devices below a price   *
               * * * * * * * * * * * * * * * * * * * *
               * 0) Return to Reports Menu           *
               * * * * * * * * * * * * * * * * * * * *
               """);
    }
}
