package models;

/**
 * 此类用于创建 SmartBand 对象。
 * This class is used to create SmartBand objects.
 *
 * @author Fan Xinkang
 * @version 1.0
 * @since version 1.0
 */
public class SmartBand extends WearableDevice {

    private boolean heartRateMonitor;

    /**
     * 创建 SmartBand 对象。
     * Constructor for SmartBand.
     *
     * @param modelName 型号。
     *                  Model name.
     * @param price 价格。
     *              Price.
     * @param manufacturer 生产商。
     *                     Manufacturer.
     * @param id ID.
     * @param material 材质。
     *                 Material.
     * @param size 大小。
     *             Size.
     * @param heartRateMonitor 心率计。
     *                         Heart Rate Monitor.
     * @author Fan Xinkang
     * @since version 1.0
     */
    public SmartBand(String modelName, double price, Manufacturer manufacturer, String id, String material, String size, boolean heartRateMonitor) {
        super(modelName, price, manufacturer, id, material, size);
        this.heartRateMonitor = heartRateMonitor;
    }

    /**
     * 获取心率计。
     * Get heart rate monitor.
     *
     * @return 心率计。
     *         Heart Rate Monitor.
     * @author Fan Xinkang
     * @since version 1.0
     */
    public boolean isHeartRateMonitor() {
        return heartRateMonitor;
    }

    /*
      封装。
      Encapsulation.
     */
    public String getHeartRateMonitor() {
        if (heartRateMonitor) {
            return "Includes Heart Rate Monitor.";
        } else {
            return "No Heart Rate Monitor included.";
        }
    }

    public void setHeartRateMonitor(boolean heartRateMonitor) {
        this.heartRateMonitor = heartRateMonitor;
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
        return "Connects to the internet via Companion App.";
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
        return getPrice() * 0.07;
    }

    /**
     * 重写 toString 方法，以便打印 SmartBand 对象。
     * Override the toString method to print SmartBand objects.
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
                \{getHeartRateMonitor()}
                \{connectToInternet()}
                Insurance Premium: $\{getInsurancePremium()}
                """;
    }
}
/*
 * End of models.SmartBand Class.
 * Checked by Fan Xinkang on 2025/04/15.
 */