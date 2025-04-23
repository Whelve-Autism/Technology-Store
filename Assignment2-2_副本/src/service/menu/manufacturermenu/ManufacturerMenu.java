package service.menu.manufacturermenu;

import utils.ScannerInput;

import static controller.ManufacturerAPI.*;
import static service.UserInterface.printlnRandomColor;
import static service.UserInterface.randomSleep;
import static service.menu.MainMenu.runMainMenu;

public class ManufacturerMenu {

    public static void runManufacturerMenu() {
        randomSleep();
        int option = manufacturerMenu();
        while (option != 0) {
            switch (option) {
                case 1 -> addManufacturer();
                case 2 -> deleteManufacturer();
                case 3 -> updateManufacturer();
                case 4 -> listManufacturersByName();
                default -> printlnRandomColor(STR."Invalid option entered\{option}");
            }
            ScannerInput.readNextLine("\n Press the enter key to continue");
            option = manufacturerMenu();
        }
        runMainMenu();
    }

    private static int manufacturerMenu() {
        return ScannerInput.readNextInt("""
               * * * * * * * * * * * * * * * * * *
               *        Manufacturer Menu        *
               * * * * * * * * * * * * * * * * * *
               * 1) Add a manufacturer           *
               * 2) Delete a manufacturer        *
               * 3) Update a manufacturer        *
               * 4) Find a manufacturer          *
               * * * * * * * * * * * * * * * * * *
               * 0) Return to main menu          *
               * * * * * * * * * * * * * * * * * *
               ==>""");
    }
}