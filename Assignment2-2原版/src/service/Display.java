package service;

import static service.UserInterface.*;

/**
 * 此类用于显示界面。
 * This class is used to display the interface.
 *
 * @author Fan Xinkang
 * @version 4.3
 * @since version 4.3
 */
public class Display {

    /**
     * 显示程序启动时的界面。
     * Show the interface when the program starts.
     *
     * @author Fan Xinkang
     * @since version 4.3
     */
    public static void beginDisplay() {
        middleSlash();
        printlnRandomColor("Technology Store V4.");
        printlnRandomColor("Developed by Fan Xinkang, Xu Shiyi and Lu Siyu.");
        printlnRandomColor(STR."The program starts at: \{getLocalDateTime()}.");
        middleSlash();
        System.out.println();
        printRandomColor("Please wait while the system loads");
        UserInterface.sleep();
        System.out.println();
        System.out.println();
        longSlash();
        printRandomColorForPattern("***************        ***************        ***************        ***************        ***************");
        printRandomColorForPattern("***************        ***************        ***************        ***************        ***************");
        printRandomColorForPattern("***                          ***              ***         ***        ***         ***              ***");
        printRandomColorForPattern("***                          ***              ***         ***        ***         ***              ***");
        printRandomColorForPattern("***                          ***              ***         ***        ***         ***              ***");
        printRandomColorForPattern("***************              ***              ***************        ***************              ***");
        printRandomColorForPattern("***************              ***              ***************        ***************              ***");
        printRandomColorForPattern("            ***              ***              ***         ***        *******                      ***");
        printRandomColorForPattern("            ***              ***              ***         ***        ***  ****                    ***");
        printRandomColorForPattern("            ***              ***              ***         ***        ***    ****                  ***");
        printRandomColorForPattern("***************              ***              ***         ***        ***      ****                ***");
        printRandomColorForPattern("***************              ***              ***         ***        ***        ****              ***");
        longSlash();
    }

    /**
     * 显示程序退出时的界面。
     * Show the interface when the program exits.
     *
     * @author Fan Xinkang
     * @since version 4.3
     */
    public static void endDisplay() {
        shortSlash();
        printlnRandomColor("Exiting System...bye... ");
        printlnRandomColor("Thank you for using our Technology Store.");
        printlnRandomColor(STR."The program ends at: \{getLocalDateTime()}.");
        shortSlash();
    }
}
/*
 * End of Display Class.
 * Checked by Fan Xinkang on 2025/05/03.
 */