package service.menu.technologymenu.computingmenu;

import utils.ScannerInput;

import static controller.computingCRUD.LaptopCRUD.addLaptop;
import static controller.computingCRUD.TabletCRUD.addTablet;
import static service.UserInterface.printlnRandomColor;
import static service.UserInterface.randomSleep;
import static service.menu.technologymenu.TechnologyMenu.runTechnologyMenu;

/**
 * 此类用于管理计算机设备菜单。
 * This class is used to manage the computing devices' menu.
 *
 * @author Fan Xinkang
 * @version 4.3
 * @since version 4.3
 */
public class ComputingMenu {

    /**
     * 运行计算机设备菜单。
     * Run the computing menu.
     *
     * @author Fan Xinkang
     * @since version 4.3
     */
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

    /**
     * 展示计算机设备菜单。
     * Show the computing menu.
     *
     * @return 选项。
     *         Option.
     * @author Fan Xinkang
     * @since version 4.3
     */
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
/*
 * End of ComputingMenu Class.
 * Checked by Fan Xinkang on 2025/05/03.
 */