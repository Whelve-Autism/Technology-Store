package service.menu.reportmenu.manumenu;

import utils.ScannerInput;

import static controller.ManufacturerAPI.listAllManufacturers;
import static controller.ManufacturerAPI.listManufacturersByName;
import static service.UserInterface.printlnRandomColor;
import static service.UserInterface.randomSleep;
import static service.menu.reportmenu.ReportMenu.runReportMenu;

public class ManuReportMenu {

    public static void runManuReportMenu() {
        randomSleep();
        int option = manuReportMenu();
        while (option != 0) {
            switch (option) {
                case 1 -> listAllManufacturers();
                case 2 -> listManufacturersByName();
                default -> printlnRandomColor(STR."Invalid option entered\{option}");
            }
            ScannerInput.readNextLine("\n Press the enter key to continue");
            option =  manuReportMenu();
        }
        runReportMenu();
    }

    private static int manuReportMenu() {
        return ScannerInput.readNextInt("""
               * * * * * * * * * * * * * * * * * *
               *    Manufacturer Report Menu     *
               * * * * * * * * * * * * * * * * * *
               * 1) List Manufacturers           *
               * 2) List Manufacturers by name   *
               * * * * * * * * * * * * * * * * * *
               * 0) Return to Reports Menu       *
               * * * * * * * * * * * * * * * * * *
               ==>""");
    }
}
