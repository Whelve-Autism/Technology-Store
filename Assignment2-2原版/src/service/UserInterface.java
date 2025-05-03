package service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 此类用于控制输出的样式，优化用户的体验。
 * This class is used to control the output style, optimizing the user experience.
 *
 * @author Fan Xinkang
 * @version 4.3
 * @since version 4.3
 */
public class UserInterface {

    private static final String[] COLOURS = {
            "\u001B[91m", // 红色 (Bright Red)
            "\u001B[92m", // 绿色 (Bright Green)
            "\u001B[93m", // 黄色 (Bright Yellow)
            "\u001B[94m", // 蓝色 (Bright Blue)
            "\u001B[95m", // 紫色 (Bright Purple)
            "\u001B[96m", // 青色 (Bright Cyan)
            "\u001B[97m"  // 白色 (Bright White)
    };

    private static final Random random = new Random();

    /**
     * 输出一个随机颜色的字符串（不换行）。
     * Output a random color string (without newline).
     *
     * @param word 输出的字符串。
     *             The string to be output.
     * @author Fan Xinkang
     * @since version 4.3
     */
    public static void printRandomColor(String word) {
        String randomColor = COLOURS[random.nextInt(COLOURS.length)];
        System.out.print(STR."\{randomColor}\{word}\u001B[0m");
    }

    /**
     * 输出一个随机颜色的字符串（换行）。
     * Output a random color string (with newline).
     *
     * @param wordln 输出的字符串。
     *               The string to be output.
     * @author Fan Xinkang
     * @since version 4.3
     */
    public static void printlnRandomColor(String wordln) {
        String randomColor = COLOURS[random.nextInt(COLOURS.length)];
        System.out.println(STR."\{randomColor}\{wordln}\u001B[0m");
    }

    /**
     * 输出一个随机颜色的字符串（根据指定的图案）。
     * Output a random color string (based on the specified pattern).
     *
     * @param pattern 指定的图案。
     *                The specified pattern.
     * @author Fan Xinkang
     * @since version 4.3
     */
    public static void printRandomColorForPattern(String pattern) {
        StringBuilder result = new StringBuilder();
        for (char signal : pattern.toCharArray()) {
            String randomColor = COLOURS[random.nextInt(COLOURS.length)];
            result.append(randomColor).append(signal).append("\u001B[0m");
        }
        System.out.println(result.toString());
    }

    /**
     * 输出短长度的斜杠。
     * Output short length slashes.
     *
     * @author Fan Xinkang
     * @since version 4.3
     */
    public static void shortSlash() {
        for (int i = 0; i < 41; i++){
            printRandomColor("/");
        }
        printlnRandomColor("/");
    }

    /**
     * 输出中长度的斜杠。
     * Output middle length slashes.
     *
     * @author Fan Xinkang
     * @since version 4.3
     */
    public static void middleSlash() {
        for (int i = 0; i < 47; i++){
            printRandomColor("/");
        }
        printlnRandomColor("/");
    }

    /**
     * 输出长长度的斜杠。
     * Output long length slashes.
     *
     * @author Fan Xinkang
     * @since version 4.3
     */
    public static void longSlash() {
        for (int i = 0; i < 107; i++){
            printRandomColor("/");
        }
        printlnRandomColor("/");
    }

    /**
     * 获取当前时间。
     * Get current time.
     *
     * @return 当前时间。
     *         Current time.
     * @author Fan Xinkang
     * @since version 4.3
     */
    public static String getLocalDateTime() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    /**
     * 随机休眠。
     * Random sleep.
     *
     * @author Fan Xinkang
     * @since version 4.3
     */
    public static void randomSleep() {
        Random random = new Random();
        if (random.nextDouble() < 0.3) {
            printRandomColor("Wait a moment. The system is a little stuck");
            sleep();
        }
    }

    /**
     * 休眠。
     * Sleep.
     *
     * @author Fan Xinkang
     * @since version 4.3
     */
    public static void sleep() {
        try {
            for (int i = 0; i < 10; i++) {
                printRandomColor(".");
                TimeUnit.MILLISECONDS.sleep(300);
            }
            System.out.println();
        } catch (Exception e) {
            System.err.println(STR."Thread was interrupted: \{e.getMessage()}");
            Thread.currentThread().interrupt();
        }
    }
}