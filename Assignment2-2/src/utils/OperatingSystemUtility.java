package utils;

import java.util.*;

/**
 * 此类用于验证操作系统名称。
 * This class is used to validate operating system names.
 *
 * @author Guoqing Lu
 * @version 0.0
 * @since version 0.0
 */
public class OperatingSystemUtility {

    private static ArrayList<String> operatingSystems = new ArrayList<>(){{
        add("iPad");
        add("Android");
        add("Chrome");
        add("Windows");
        add("Amazon Fire");
    }};

    public static ArrayList<String> getOperatingSystems() {
        return operatingSystems;
    }

    /**
     * 检查操作系统名称是否合法。
     * This method is used to check if the operating system name is valid.
     *
     * @param os 操作系统名称
     * @return 如果操作系统名称合法，则返回true，否则返回false
     * @since version 0.0
     */
    public static boolean isValidOperatingSystem(String os) {
        for (String osName:operatingSystems){
            if (osName.equalsIgnoreCase(os)) {
                return true;
            }
        }
        return false;
    }
}
/*
 * End of utils.OperatingSystemUtility Class.
 * Checked by Fan Xinkang on 2025/04/18.
 */