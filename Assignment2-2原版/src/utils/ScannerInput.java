package utils;

import java.util.Scanner;

import static service.UserInterface.printRandomColor;
import static service.UserInterface.printlnRandomColor;

/**
 * 此类用于读取用户输入。
 * This class is used to read user input.
 *
 * @author Guoqing Lu
 * @version 1.0
 * @since version 0.0
 */
public class ScannerInput {

    /**
     * 读取一个整数。
     * Read an integer.
     *
     * @param prompt 提示信息。
     *               prompt The information printed to the console for the user to read.
     * @return 用户输入的整数。
     *         The integer value read from the user.
     * @author Guoqing Lu
     * @since version 0.0
     */
    public static int readNextInt(String prompt) {
        do {
            var scanner = new Scanner(System.in);
            try {
                printRandomColor(prompt);
                return Integer.parseInt(scanner.next());
            } catch (NumberFormatException e) {
                printlnRandomColor("\tEnter a number please.");
            }
        }  while (true);
    }

    /**
     * 读取一个双精度浮点值。
     * Read a double value.
     *
     * @param prompt 提示信息。
     *               prompt The information printed to the console for the user to read.
     * @return 用户输入的双精度浮点值。
     *         The double value read from the user.
     * @author Guoqing Lu
     * @since version 0.0
     */
    public static double readNextDouble(String prompt) {
        do {
            var scanner = new Scanner(System.in);
            try {
                printRandomColor(prompt);
                return Double.parseDouble(scanner.next());
            } catch (NumberFormatException e) {
                printlnRandomColor("\tEnter a number please.");
            }
        } while (true);
    }

    /**
     * 读取一个单精度浮点值。
     * Read a float value.
     *
     * @param prompt 提示信息。
     *               prompt The information printed to the console for the user to read.
     * @return 用户输入的单精度浮点值。
     *         The float value read from the user.
     * @author Guoqing Lu
     * @since version 0.0
     */
    public static float readNextFloat(String prompt) {
        do {
            var scanner = new Scanner(System.in);
            try {
                printRandomColor(prompt);
                return Float.parseFloat(scanner.next());
            } catch (NumberFormatException e) {
                printlnRandomColor("\tEnter a number please.");
            }
        }  while (true);
    }

    /**
     * 读取一个字符串。
     * Read a string.
     *
     * @param prompt 提示信息。
     *               prompt The information.
     * @return 用户输入的字符串。
     *         The string read from the user.
     * @author Guoqing Lu
     * @since version 0.0
     */
    public static String readNextLine(String prompt) {
        Scanner input = new Scanner(System.in);
        printRandomColor(prompt);
        return input.nextLine();
    }

    /**
     * 读取一个字符。
     * Read a character.
     *
     * @param prompt 提示信息。
     *               prompt The information printed to the console for the user to read.
     * @return 用户输入的字符。
     *         The character read from the user.
     * @author Guoqing Lu, Fan Xinkang
     * @since version 1.0
     */
    public static char readNextChar(String prompt) {
        Scanner input = new Scanner(System.in);
        while (true) {
            printRandomColor(prompt);
            String userInput = input.next();
            if (userInput.length() == 1) {
                return userInput.charAt(0);
            } else {
                printlnRandomColor("\tPlease enter a single character.");
            }
        }
    }

    /**
     * 读取一个布尔值。
     * Read a boolean value.
     *
     * @param prompt 提示信息。
     *               prompt The information printed to the console for the user to read.
     * @return 用户输入的布尔值。
     *         The boolean value read from the user.
     * @author Guoqing Lu, Fan Xinkang
     * @since version 1.0
     */
    public static boolean readNextBoolean(String prompt) {
        while (true) {
            char input = Character.toLowerCase(readNextChar(prompt));
            if (input == 'y') {
                return true;
            } else if (input == 'n') {
                return false;
            } else {
                printlnRandomColor("Invalid input, please enter y or n.");
            }
        }
    }
}
/*
 * End of utils.ScannerInput Class.
 * Checked by Fan Xinkang on 2025/04/17.
 */