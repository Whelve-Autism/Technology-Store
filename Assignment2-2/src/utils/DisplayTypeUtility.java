package utils;

import java.util.ArrayList;

/**
 * 此类用于判断显示类型是否合法。
 * This class is used to check whether the display type is valid.
 *
 * @author Guoqing Lu
 * @version 1.0
 */
public class DisplayTypeUtility {

    private static ArrayList<String> displayTypes = new ArrayList<>(){{
        add("AMOLED");
        add("LCD");
        add("LED");
        add("TFT");
    }};

    /**
     * 判断显示类型是否合法。
     * Check whether the display type is valid.
     *
     * @param type 显示类型。
     *             Display type.
     * @return 判断的结果。
     *         The validation result.
     * @author Guoqing Lu
     * @since version 0.0
     */
    public static boolean isValidDisplayType(String type) {
        //must not be case sensitive
        for (String disType:displayTypes){
            if (disType.equalsIgnoreCase(type)) {
                return true;
            }
        }
        return false;
    }
}
/*
 * End of utils.DisplayTypeUtility Class.
 * Checked by Fan Xinkang on 2025/04/18.
 */