package service.menu.reportmenu;

import utils.ScannerInput;

import static service.UserInterface.printlnRandomColor;
import static service.UserInterface.randomSleep;
import static service.menu.MainMenu.runMainMenu;
import static service.menu.reportmenu.manumenu.ManuReportMenu.runManuReportMenu;
import static service.menu.reportmenu.techmenu.TechReportMenu.runTechReportMenu;

public class ReportMenu {

    public static void runReportMenu() {
        randomSleep();
        int option = reportMenu();
        while (option != 0) {
            switch (option) {
                case 1 -> runManuReportMenu();
                case 2 -> runTechReportMenu();
                default -> printlnRandomColor(STR."Invalid option entered\{option}");
            }
            ScannerInput.readNextLine("\n Press the enter key to continue");
            option = reportMenu();
        }
        runMainMenu();
    }

    private static int reportMenu() {
        return ScannerInput.readNextInt(""" 
               * * * * * * * * * * * * * * * * * *
               *           Report Menu           *
               * * * * * * * * * * * * * * * * * *
               * 1) Manufacturers overview       *
               * 2) Technology devices overview  *
               * * * * * * * * * * * * * * * * * *
               * 0) Return to main menu          *
               * * * * * * * * * * * * * * * * * *
               ==>""");
    }
}
