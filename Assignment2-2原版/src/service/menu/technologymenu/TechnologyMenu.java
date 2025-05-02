package service.menu.technologymenu;

import utils.ScannerInput;

import static controller.TechnologyAPI.*;
import static service.UserInterface.printlnRandomColor;
import static service.UserInterface.randomSleep;
import static service.menu.MainMenu.runMainMenu;
import static service.menu.technologymenu.computingmenu.ComputingMenu.runComputingMenu;
import static service.menu.technologymenu.wearablemenu.WearableMenu.runWearableMenu;

public class TechnologyMenu {

    public static void runTechnologyMenu() {
        randomSleep();
        int option = technologyMenu();
        while (option != 0) {
            switch (option) {
                case 1 -> runComputingMenu();
                case 2 -> runWearableMenu();
                case 3 -> deleteTechnology();
                case 4 -> deleteAllTechnologies();
                case 5 -> updateTechnology();
                case 6 -> listAllTechnologies();
                default -> printlnRandomColor(STR."Invalid option entered\{option}");
            }
            ScannerInput.readNextLine("\n Press the enter key to continue");
            option = technologyMenu();
        }
        runMainMenu();
    }

    private static int technologyMenu() {
        return ScannerInput.readNextInt(""" 
               * * * * * * * * * * * * * * * * * * *
               *          Technology Menu          *
               * * * * * * * * * * * * * * * * * * *
               * 1) Add a Computing Device         *
               * 2) Add a Wearable Device          *
               * 3) Delete a Technology Device     *
               * 4) Delete all Technology Devices  *
               * 5) Update a Technology Device     *
               * 6) Find a Technology Device       *
               * * * * * * * * * * * * * * * * * * *
               * 0) Return to main menu            *
               * * * * * * * * * * * * * * * * * * *
               ==>""");
    }
}
