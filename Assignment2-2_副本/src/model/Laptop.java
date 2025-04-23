package model;

import utils.Utilities;

public class Laptop extends ComputingDevice {

    private int screenSize;

    private boolean screenSizeSet = false;

    public Laptop(String modelName, double price, Manufacturer manufacturer, String id, String processor, int storage, int screenSize) {
        super(modelName, price, manufacturer, id, processor, storage);
        this.screenSize = screenSize;
    }

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
