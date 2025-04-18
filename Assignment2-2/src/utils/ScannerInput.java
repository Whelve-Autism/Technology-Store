package utils;

import java.util.Scanner;

/**
 * This class provides methods for the robust handling of I/O using Scanner.
 * It creates a new Scanner object for each read from the user, thereby
 * eliminating the Scanner bug (where the buffers don't flush correctly after an int read).
 *
 * The methods also parse the numeric data entered to ensure it is correct. If it isn't correct,
 * the user is prompted to enter it again.
 *
 * @author Siobhan Drohan, Mairead Meagher
 * @version 1.0
 *
 */

public class ScannerInput {

    /**
     * Read an int from the user.  If the entered data isn't actually an int,
     * the user is prompted again to enter the int.
     *
     * @param prompt  The information printed to the console for the user to read
     * @return The number read from the user and verified as an int.
     */
    public static int readNextInt(String prompt) {
        do {
            var scanner = new Scanner(System.in);
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.next());
            }
            catch (NumberFormatException e) {
                System.err.println("\tEnter a number please.");
            }
        }  while (true);
    }

    /**
     * Read a double from the user.  If the entered data isn't actually a double,
     * the user is prompted again to enter the double.
     *
     * @param prompt  The information printed to the console for the user to read
     * @return The number read from the user and verified as a double.
     */
    public static double readNextDouble(String prompt) {
        do {
            var scanner = new Scanner(System.in);
            try{
                System.out.print(prompt);
                return Double.parseDouble(scanner.next());
            }
            catch (NumberFormatException e) {
                System.err.println("\tEnter a number please.");
            }
        }  while (true);
    }
    /**
     * Read a float from the user.  If the entered data isn't actually a float,
     * the user is prompted again to enter the float.
     *
     * @param prompt  The information printed to the console for the user to read
     * @return The number read from the user and verified as a double.
     */
    public static float readNextFloat(String prompt) {
        do {
            var scanner = new Scanner(System.in);
            try{
                System.out.print(prompt);
                return Float.parseFloat(scanner.next());
            }
            catch (NumberFormatException e) {
                System.err.println("\tEnter a number please.");
            }
        }  while (true);
    }

    /**
     * Read a line of text from the user.  There is no validation done on the entered data.
     *
     * @param prompt  The information printed to the console for the user to read
     * @return The String read from the user.
     */
    public static String readNextLine(String prompt) {
        Scanner input = new Scanner(System.in);
        System.out.print(prompt);
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
            System.out.print(prompt);
            String userInput = input.next();
            if (userInput.length() == 1) {
                return userInput.charAt(0);
            } else {
                System.err.println("\tPlease enter a single character.");
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
            char input = readNextChar(String.valueOf(prompt));
            if (input == 'y' || input == 'n') {
                return input == 'y';
            } else {
                System.out.println("Invalid input, please enter y or n.");
            }
        }
    }
}
/*
 * End of utils.ScannerInput Class.
 * Checked by Fan Xinkang on 2025/04/17.
 */