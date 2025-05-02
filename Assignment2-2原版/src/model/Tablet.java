package model;

import utils.Utilities;

import java.util.ArrayList;
import java.util.List;

/**
 * 此类用于创建 Tablet 对象。
 * This class is used to create Tablet objects.
 *
 * @author Fan Xinkang
 * @version 4.2
 * @since version 1.0
 */
public class Tablet extends ComputingDevice {

    private static String operatingSystem = "Windows";

    private boolean operatingSystemSet = false;

    public static final ArrayList<String> operatingSystems = new ArrayList<>() {{
        add("iPad");
        add("Android");
        add("Chrome");
        add("Windows");
        add("Amazon Fire");
    }};

    /**
     * 创建 Tablet 对象。
     * Constructor for Tablet.
     *
     * @param modelName 型号。
     *                  Model name.
     * @param price 价格。
     *              Price.
     * @param manufacturer 生产商。
     *                     Manufacturer.
     * @param id ID.
     * @param processor 处理器。
     *                  Processor.
     * @param storage 存储空间。
     *                Storage.
     * @param operatingSystem 操作系统。
     *                        Operating System.
     * @author Fan Xinkang
     * @since version 1.0
     */
    public Tablet(String modelName, double price, Manufacturer manufacturer, String id, String processor, int storage, String operatingSystem) {
        super(modelName, price, manufacturer, id, processor, storage);
        setOperatingSystem(operatingSystem);
    }

    /*
      封装。
      Encapsulation.
     */

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(String operatingSystem) {
        Tablet.operatingSystem = operatingSystem;
    }

    public static void listAllOperatingSystems() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < operatingSystems.size(); i++) {
            builder.append(STR."\{i + 1})\{operatingSystems.get(i)} ");
        }
        System.out.println(builder);
    }

    /**
     * 连接到互联网。
     * Connect to the internet.
     *
     * @return 连接成功。
     *         Connected successfully.
     * @author Fan Xinkang
     * @since version 1.0
     */
    @Override
    public String connectToInternet() {
        return "Connects to the internet via Wi-Fi";
    }

    /**
     * 获取保险保费。
     * Get insurance premium.
     *
     * @return 保险保费。
     *         Insurance Premium.
     * @author Fan Xinkang
     * @since version 1.0
     */
    @Override
    public double getInsurancePremium() {
        return Utilities.toTwoDecimalPlaces(getPrice() * 0.01);
    }

    /**
     * 重写 toString 方法，以便打印 Tablet 对象。
     * Override the toString method to print Tablet objects.
     *
     * @return 字符串表示。
     *         String representation.
     * @author Fan Xinkang
     * @since version 1.0
     */
    @Override
    public String toString() {
        return STR."""
                \{super.toString()}
                Operating System: \{getOperatingSystem()}
                \{connectToInternet()}
                Insurance Premium: $\{getInsurancePremium()}
                """;
    }
}
/*
 * End of models.Tablet Class.
 * Checked by Fan Xinkang on 2025/04/15.
 */