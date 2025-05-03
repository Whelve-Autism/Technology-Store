package service.menu.technologymenu;

import utils.ScannerInput;

import static controller.TechnologyAPI.*;
import static service.UserInterface.printlnRandomColor;
import static service.UserInterface.randomSleep;
import static service.menu.MainMenu.runMainMenu;
import static service.menu.technologymenu.computingmenu.ComputingMenu.runComputingMenu;
import static service.menu.technologymenu.wearablemenu.WearableMenu.runWearableMenu;

/**
 * 此类用于管理科技设备菜单。
 * This class is used to manage the technology menu.
 *
 * @author Fan Xinkang
 * @version 4.3
 * @since version 4.3
 */
public class TechnologyMenu {

    /**
     * 运行科技设备菜单。
     * Run the technology menu.
     *
     * @author Fan Xinkang
     * @since version 4.3
     */
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

    /**
     * 展示科技设备菜单。
     * Show the technology menu.
     *
     * @return int 选项。
     *             Option.
     * @author Fan Xinkang
     * @since version 4.3
     */
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
/*
 * End of TechnologyMenu Class.
 * Checked by Fan Xinkang on 2025/05/03.
 */