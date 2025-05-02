import service.menu.MainMenu;

import static service.Display.beginDisplay;
import static service.Display.endDisplay;

public static void main(String[] args) {
    beginDisplay();
    MainMenu.runMainMenu();
    endDisplay();
}
