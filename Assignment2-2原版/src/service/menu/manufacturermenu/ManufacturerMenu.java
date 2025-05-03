package service.menu.manufacturermenu;

import utils.ScannerInput;

import static controller.ManufacturerAPI.*;
import static service.UserInterface.printlnRandomColor;
import static service.UserInterface.randomSleep;
import static service.menu.MainMenu.runMainMenu;

/**
 * 此类用于管理制造商菜单。
 * This class is used to manage the manufacturer menu.
 *
 * @author Fan Xinkang
 * @version 4.3
 * @since version 4.3
 */
public class ManufacturerMenu {

    /**
     * 运行制造商菜单。
     * Run the manufacturer menu.
     *
     * @author Fan Xinkang
     * @since version 4.3
     */
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

    /**
     * 展示制造商菜单。
     * Show the manufacturer menu.
     *
     * @return int 选项。
     *             Option.
     * @author Fan Xinkang
     * @since version 4.3
     */
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
/*
 * End of ManufacturerMenu Class.
 * Checked by Fan Xinkang on 2025/05/01.
 */