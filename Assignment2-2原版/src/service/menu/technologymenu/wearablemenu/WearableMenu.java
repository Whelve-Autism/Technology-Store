package service.menu.technologymenu.wearablemenu;

import utils.ScannerInput;

import static controller.wearableCRUD.SmartBandCRUD.addSmartBand;
import static controller.wearableCRUD.SmartWatchCRUD.addSmartWatch;
import static service.UserInterface.printlnRandomColor;
import static service.UserInterface.randomSleep;
import static service.menu.technologymenu.TechnologyMenu.runTechnologyMenu;

/**
 * 此类用于管理穿戴设备的菜单。
 * This class is used to manage wearable devices' menu.
 *
 * @author Fan Xinkang
 * @version 4.3
 * @since version 4.3
 */
public class WearableMenu {

    /**
     * 运行穿戴设备菜单。
     * Run wearable devices' menu.
     *
     * @author Fan Xinkang
     * @since version 4.3
     */
    public static void runWearableMenu() {
        randomSleep();
        int option = wearableMenu();
        while (option != 0) {
            switch (option) {
                case 1 -> addSmartBand();
                case 2 -> addSmartWatch();
                default -> printlnRandomColor(STR."Invalid option entered\{option}");
            }
            ScannerInput.readNextLine("\n Press the enter key to continue");
            option = wearableMenu();
        }
        runTechnologyMenu();
    }

    /**
     * 展示穿戴设备菜单。
     * Show wearable devices' menu.
     *
     * @return int 选项。
     *             Option.
     * @author Fan Xinkang
     * @since version 4.3
     */
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
/*
 * End of WearableMenu Class.
 * Checked by Fan Xinkang on 2025/05/03.
 */