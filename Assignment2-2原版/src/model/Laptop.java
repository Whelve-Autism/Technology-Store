package model;

import utils.Utilities;

/**
 * 此类用于创建 Laptop 对象。
 * This class is used to create Laptop objects.
 *
 * @author Fan Xinkang
 * @since version 1.0
 */
public class Laptop extends ComputingDevice {

    private int screenSize;

    private boolean screenSizeSet = false;

    /**
     * 创建 Laptop 对象。
     * Create Laptop object.
     *
     * @param modelName 型号。
     *                  Model.
     * @param price 价格。
     *              Price.
     * @param manufacturer 生产商。
     *                     Manufacturer.
     * @param id ID
     * @param processor 处理器。
     *                  Processor.
     * @param storage 存储空间。
     *                Storage.
     * @param screenSize 屏幕尺寸。
     *                   Screen Size.
     * @author Fan Xinkang
     * @since version 1.0
     */
    public Laptop(String modelName, double price, Manufacturer manufacturer, String id, String processor, int storage, int screenSize) {
        super(modelName, price, manufacturer, id, processor, storage);
        this.screenSize = screenSize;
    }

    /*
      封装。
      Encapsulation.
     */
    public int getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(int screenSize) {
        if (!screenSizeSet) {
            if (Utilities.validRange(screenSize, 10, 50)) {
                this.screenSize = screenSize;
            } else {
                this.screenSize = 10;
            }
        } else {
            if (Utilities.validRange(screenSize, 10, 50)) {
                this.screenSize = screenSize;
            }
        }
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
        return "Connects to the internet via wires";
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
        return Utilities.toTwoDecimalPlaces(getPrice() * 0.02);
    }
}
/*
 * End of Laptop Class.
 * Checked by Fan Xinkang on 2025/05/03.
 */