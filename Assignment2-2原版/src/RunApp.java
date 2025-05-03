import service.menu.MainMenu;

import static service.Display.beginDisplay;
import static service.Display.endDisplay;

/**
 * 运行程序入口。
 * The entry point to run the program.
 *
 * @param args 命令行参数。
 *             The command line arguments.
 * @author Fan Xinkang
 * @since version 4.3
 */
public static void main(String[] args) {
    beginDisplay();
    MainMenu.runMainMenu();
    endDisplay();
}
/*
 * End of RunApp Class.
 * Checked by Fan Xinkang on 2025/04/15.
 */