package service.menu.technologymenu.computingmenu;

import utils.ScannerInput;

import static controller.computingCRUD.LaptopCRUD.addLaptop;
import static controller.computingCRUD.TabletCRUD.addTablet;
import static service.UserInterface.printlnRandomColor;
import static service.UserInterface.randomSleep;
import static service.menu.technologymenu.TechnologyMenu.runTechnologyMenu;

public class ComputingMenu {

    public static void runComputingMenu() {
        randomSleep();
        int option = computingMenu();
        while (option != 0) {
            switch (option) {
                case 1 -> addLaptop();
                case 2 -> addTablet();
                default -> printlnRandomColor(STR."Invalid option entered\{option}");
            }
            ScannerInput.readNextLine("\n Press the enter key to continue");
            option = computingMenu();
        }
        runTechnologyMenu();
    }

    public static int computingMenu() {
        return ScannerInput.readNextInt("""
               * * * * * * * * * * * * * * * * * *
               *         Computing Menu          *
               * * * * * * * * * * * * * * * * * *
               * 1) Add a new laptop             *
               * 2) Add a new tablet             *
               * * * * * * * * * * * * * * * * * *
               * 0) Return to Technology Menu    *
               * * * * * * * * * * * * * * * * * *
               ==>""");
    }
}
