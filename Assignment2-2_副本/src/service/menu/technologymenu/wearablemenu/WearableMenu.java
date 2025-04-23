package service.menu.technologymenu.wearablemenu;

import utils.ScannerInput;

import static controller.wearableCRUD.SmartBandCRUD.addSmartBand;
import static controller.wearableCRUD.SmartWatchCRUD.updateSmartWatch;
import static service.UserInterface.printlnRandomColor;
import static service.UserInterface.randomSleep;
import static service.menu.technologymenu.TechnologyMenu.runTechnologyMenu;

public class WearableMenu {

    public static void runWearableMenu() {
        randomSleep();
        int option = wearableMenu();
        while (option != 0) {
            switch (option) {
                case 1 -> addSmartBand();
                case 2 -> updateSmartWatch();
                default -> printlnRandomColor(STR."Invalid option entered\{option}");
            }
            ScannerInput.readNextLine("\n Press the enter key to continue");
            option = wearableMenu();
        }
        runTechnologyMenu();
    }

    public static int wearableMenu() {
        return ScannerInput.readNextInt("""
               * * * * * * * * * * * * * * * * * *
               *          Wearable Menu          *
               * * * * * * * * * * * * * * * * * *
               * 1) Add a new smart band         *
               * 2) Add a new smart watch        *
               * * * * * * * * * * * * * * * * * *
               * 0) Return to Technology Menu    *
               * * * * * * * * * * * * * * * * * *
               ==>""");
    }
}