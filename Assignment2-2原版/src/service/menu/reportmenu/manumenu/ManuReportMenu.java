package service.menu.reportmenu.manumenu;

import utils.ScannerInput;

import static controller.ManufacturerAPI.listAllManufacturers;
import static controller.ManufacturerAPI.listManufacturersByName;
import static service.UserInterface.printlnRandomColor;
import static service.UserInterface.randomSleep;
import static service.menu.reportmenu.ReportMenu.runReportMenu;

/**
 * 此类用于管理制造商报告菜单。
 * This class is used to manage the manufacturer report menu.
 *
 * @author Fan Xinkang
 * @version 4.3
 * @since version 4.3
 */
public class ManuReportMenu {

    /**
     * 运行制造商报告菜单。
     * Run the manufacturer report menu.
     *
     * @author Fan Xinkang
     * @since version 4.3
     */
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

    /**
     * 展示制造商报告菜单。
     * Show the manufacturer report menu.
     *
     * @return int 选项。
     *             Option.
     * @author Fan Xinkang
     * @since version 4.3
     */
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
/*
 * End of ManuReportMenu Class.
 * Checked by Fan Xinkang on 2025/05/01.
 */