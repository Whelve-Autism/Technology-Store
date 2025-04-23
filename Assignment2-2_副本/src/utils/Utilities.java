package utils;

import java.util.List;

/**
 * 此类用于提供一些通用的 utility 方法。
 * This class is used to provide some common utility methods.
 *
 * @author Guoqing Lu, Fan Xinkang
 * @version 1.0
 * @since version 0.0
 */
public class Utilities {

    /**
     * 将数字转换为两位小数。
     * Converts the number to two decimal places.
     *
     * @param number 要转换的数字。
     *               The number to convert.
     * @return 被转换的数字。
     *         The number converted.
     * @author Guoqing Lu
     * @since version 0.0
     */
    public static double toTwoDecimalPlaces(double number) {
        return (int) (number * 100) / 100.0;
    }

    /**
     * 判断一个布尔值，如果为 true，则返回 Y，如果为 false，则返回 N。
     * Converts a boolean value to Y or N.
     *
     * @param booleanToConvert 要转换的布尔值。
     *                         The boolean value to convert.
     * @return 被转换的布尔值。
     *         The boolean value converted.
     * @author Guoqing Lu
     * @since version 0.0
     */
    public static char booleanToYN(boolean booleanToConvert) {
        return booleanToConvert ? 'Y' : 'N';
    }

    /**
     * 判断一个字符，如果为 Y 或 y，则返回 true，否则返回 false。
     * Converts a character to a boolean value.
     *
     * @param charToConvert 要转换的字符。
     *                      The character to convert.
     * @return 被转换的字符。
     *         The character converted.
     * @author Guoqing Lu
     * @since version 0.0
     */
    public static boolean YNtoBoolean(char charToConvert) {
        return ((charToConvert == 'y') || (charToConvert == 'Y'));
    }

    /**
     * 判断一个数字是否在给定的范围内。
     * Checks if a number is within a given range.
     *
     * @param numberToCheck 要判断的数字。
     *                      The number to check.
     * @param min 范围下限。
     *            The minimum value of the range.
     * @param max 范围上限。
     *            The maximum value of the range.
     * @return 判断的结果
     *         The validation result.
     * @author Guoqing Lu
     * @since version 0.0
     */
    public static boolean validRange(int numberToCheck, int min, int max) {
        return ((numberToCheck >= min) && (numberToCheck <= max));
    }

    /**
     * 判断一个数字是否在给定的范围内。
     * Checks if a number is within a given range.
     *
     * @param numberToCheck 要判断的数字。
     *                      The number to check.
     * @param min 范围下限。
     *            The minimum value of the range.
     * @param max 范围上限。
     *            The maximum value of the range.
     * @return 判断的结果
     *         The validation result.
     * @author Guoqing Lu
     * @since version 0.0
     */
    public static boolean validRange(double numberToCheck, double min, double max) {
        return ((numberToCheck >= min) && (numberToCheck <= max));
    }

    /**
     * 判断一个数字是否在给定的范围内。
     * Checks if a number is within a given range.
     *
     * @param numbertoCheck 要判断的数字。
     *                      The number to check.
     * @param min 范围下限。
     *            The minimum value of the range.
     * @param max 范围上限。
     *            The maximum value of the range.
     * @param delta 允许的误差范围。
     *              The allowed range of error.
     * @return 判断的结果
     *         The validation result.
     * @author Guoqing Lu
     * @since version 0.0
     */
    public static boolean validRange(float numbertoCheck, float min, float max, float delta) {
        return ((numbertoCheck >= (min-delta)) && (numbertoCheck <= (max+delta)));
    }

    /**
     * 判断一个字符串的长度是否在给定的范围内。
     * Checks if a string's length is within a given range.
     *
     * @param stringToTruncate 要判断的字符串。
     *                         The string to check.
     * @param length 最大长度。
     *               The maximum length.
     * @return 判断的结果
     *         The validation result.
     * @author Guoqing Lu
     * @since version 0.0
     */
    public static String truncateString(String stringToTruncate, int length) {
        if (stringToTruncate != null) {
            if (stringToTruncate.length() <= length) {
                return stringToTruncate;
            } else {
                return stringToTruncate.substring(0, length);
            }
        } else {
            return null;
        }
    }

    /**
     * 判断一个字符串的长度是否在给定的范围内。
     * Checks if a string's length is within a given range.
     *
     * @param strToCheck 要判断的字符串。
     *                   The string to check.
     * @param maxLength 最大长度。
     *                  The maximum length.
     * @return 判断的结果
     *         The validation result.
     * @author Guoqing Lu
     * @since version 0.0
     */
    public static boolean validStringLength(String strToCheck, int maxLength){
        if (strToCheck != null ){
            return strToCheck.length() <= maxLength;
        }
        return false;
    }

    /**
     * 判断一个索引是否在给定的范围内。
     * Checks if an index is within a given range.
     *
     * @param list 要判断的列表。
     *             The list to check.
     * @param indexToCheck 要判断的索引。
     *                     The index to check.
     * @return 判断的结果
     *         The validation result.
     * @author Guoqing Lu
     * @since version 0.0
     */
    public static boolean isValidIndex(List list, int indexToCheck){
        return ((indexToCheck >= 0) && (indexToCheck < list.size()));
    }

    public static boolean isValidString(String strToCheck, int maxLength) {
        return (strToCheck != null) && (!strToCheck.trim().isEmpty()) && (strToCheck.length() <= maxLength);
    }

    public static boolean isValidInt(int intToCheck, int maxLength) {
        return (intToCheck >= 0) && (intToCheck <= maxLength);
    }

    public static boolean isValidDouble(double doubleToCheck, double maxLength) {
        return (doubleToCheck >= 0) && (doubleToCheck <= maxLength);
    }


}
/*
 * End of utils.Utilities Class.
 * Checked by Fan Xinkang on 2025/04/17.
 */