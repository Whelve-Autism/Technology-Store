package service.menu.reportmenu.techmenu;

import utils.ScannerInput;

import static controller.TechnologyAPI.*;
import static controller.computingCRUD.LaptopCRUD.listAllLaptops;
import static controller.computingCRUD.TabletCRUD.listAllTablets;
import static controller.wearableCRUD.SmartBandCRUD.listAllSmartBands;
import static controller.wearableCRUD.SmartWatchCRUD.listAllSmartWatches;
import static service.UserInterface.printlnRandomColor;
import static service.UserInterface.randomSleep;
import static service.menu.reportmenu.ReportMenu.runReportMenu;

/**
 * 此类用于管理科技设备报告菜单。
 * This class is used to manage the technology device report menu.
 *
 * @author Fan Xinkang
 * @version 4.3
 * @since version 4.3
 */
public class TechReportMenu {

    /**
     * 运行科技产品报告菜单。
     * Run the technology product report menu.
     *
     * @author Fan Xinkang
     * @since version 4.3
     */
    public static void runTechReportMenu() {
        randomSleep();
        int option = technologyReportMenu();
        while (option != 0) {
            switch (option) {
                case 1 -> listAllTechnologies();
                case 2 -> listAllLaptops();
                case 3 -> listAllTablets();
                case 4 -> listAllSmartBands();
                case 5 -> listAllSmartWatches();
                case 6 -> listTechnologiesAboveAPrice();
                case 7 -> listTechnologiesBelowAPrice();
                case 8 -> ListTopFiveExpansiveTechnologyDevices();
                default -> printlnRandomColor(STR."Invalid option entered\{option}");
            }
            ScannerInput.readNextLine("\n Press the enter key to continue");
            option = technologyReportMenu();
        }
        runReportMenu();
    }

    /**
     * 显示科技产品报告菜单。
     * Show the technology product report menu.
     *
     * @return int 选项。
     *             Option.
     * @author Fan Xinkang
     * @since version 4.3
     */
    private static int technologyReportMenu() {
        return ScannerInput.readNextInt("""
               * * * * * * * * * * * * * * * * * * * * * * * *
               *           Technology Report Menu            *
               * * * * * * * * * * * * * * * * * * * * * * * *
               * 1) List all technology                      *
               * 2) List all laptops                         *
               * 3) List all tablets                         *
               * 4) List all Smart bands                     *
               * 5) List all Smart watches                   *
               * 6) List all devices above a price           *
               * 7) List all devices below a price           *
               * 8) List top 5 expansive technology devices  *
               * * * * * * * * * * * * * * * * * * * * * * * *
               * 0) Return to Reports Menu                   *
               * * * * * * * * * * * * * * * * * * * * * * * *
               ==>""");
    }
}
/*
 * End of TechReportMenu Class.
 * Checked by Fan Xinkang on 2025/05/01.
 */