package service;

import java.util.concurrent.TimeUnit;

import static service.UserInterface.*;

public class Display {

    /*
     * 显示开始界面。
     * UserInterface.Display the start interface.
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

    /*
     * 显示结束界面。
     * UserInterface.Display the end interface.
     */
    public static void endDisplay() {
        shortSlash();
        printlnRandomColor("Exiting System...bye... ");
        printlnRandomColor("Thank you for using our Technology Store.");
        printlnRandomColor(STR."The program ends at: \{getLocalDateTime()}.");
        shortSlash();
    }
}
