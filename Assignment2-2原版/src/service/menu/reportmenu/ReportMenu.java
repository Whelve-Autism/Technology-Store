package service.menu.reportmenu;

import utils.ScannerInput;

import static service.UserInterface.printlnRandomColor;
import static service.UserInterface.randomSleep;
import static service.menu.MainMenu.runMainMenu;
import static service.menu.reportmenu.manumenu.ManuReportMenu.runManuReportMenu;
import static service.menu.reportmenu.techmenu.TechReportMenu.runTechReportMenu;

/**
 * 此类用于管理报告菜单。
 * This class is used to manage the report menu.
 *
 * @author Fan Xinkang
 * @version 4.3
 * @since version 4.3
 */
public class ReportMenu {

    /**
     * 运行报告菜单。
     * Run the report menu.
     *
     * @author Fan Xinkang
     * @since version 4.3
     */
    public static void runReportMenu() {
        randomSleep();
        int option = reportMenu();
        while (option != 0) {
            switch (option) {
                case 1 -> runManuReportMenu();
                case 2 -> runTechReportMenu();
                default -> printlnRandomColor(STR."Invalid option entered\{option}");
            }
            ScannerInput.readNextLine("\n Press the enter key to continue");
            option = reportMenu();
        }
        runMainMenu();
    }

    /**
     * 展示运行报告菜单。
     * Show the report menu.
     *
     * @return int 选项。
     *             Option.
     * @author Fan Xinkang
     * @since version 4.3
     */
    private static int reportMenu() {
        return ScannerInput.readNextInt(""" 
               * * * * * * * * * * * * * * * * * *
               *           Report Menu           *
               * * * * * * * * * * * * * * * * * *
               * 1) Manufacturers overview       *
               * 2) Technology devices overview  *
               * * * * * * * * * * * * * * * * * *
               * 0) Return to main menu          *
               * * * * * * * * * * * * * * * * * *
               ==>""");
    }
}
/*
 * End of ReportMenu Class.
 * Checked by Fan Xinkang on 2025/05/03.
 */