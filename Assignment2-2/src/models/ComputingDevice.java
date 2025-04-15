package models;

import utils.Utilities;

/**
 * 此类用于表示计算机设备，包括平板电脑，提供了通用的属性和方法。
 * This class is used to represent computing devices, including tablets, providing common attributes and methods.
 *
 * @author Fan Xinkang
 * @version 2.0
 * @since version 1.0
 */
public abstract class ComputingDevice extends Technology {

    private String processor;
    private int storage;

    private boolean processorSet = false;
    private boolean storageSet = false;

    /**
     * 创建 ComputingDevice 对象。
     * Constructor for ComputingDevice.
     *
     * @param modelName 型号名称。
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
     * @author Fan Xinkang
     * @since version 1.0
     */
    public ComputingDevice(String modelName, double price, Manufacturer manufacturer, String id, String processor, int storage) {
        super(modelName, price, manufacturer, id);
        setProcessor(processor);
        setStorage(storage);
    }

    /*
      封装。
      Encapsulation.
     */
    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        if (!processorSet) {
            if (processor.length() <= 20) {
                this.processor = processor;
            } else {
                this.processor = processor.substring(0, 20);
            }
            processorSet = true;
        } else {
            if (processor.length() <= 20) {
                this.processor = processor;
            }
        }
    }

    public int getStorage() {
        return storage;
    }

    public void setStorage(int storage) {
        if (!storageSet) {
            if (storage >= 8 && storage <= 128 && storage % 8 == 0) {
                this.storage = storage;
            } else {
                this.storage = 8;
                System.out.println("Invalid storage value. Must be between 8 and 128 and divisible by 8.");
            }
            storageSet = true;
        } else {
            if (storage >= 8 && storage <= 128 && storage % 8 == 0) {
                this.storage = storage;
            } else {
                System.out.println("Invalid storage value. Must be between 8 and 128 and divisible by 8.");
            }
        }
    }

    /**
     * 抽象方法，用于连接到互联网。
     * Abstract method to connect to the internet.
     *
     * @return 连接结果。
     *         Connection result.
     * @author Fan Xinkang
     * @since version 1.0
     */
    public abstract String connectToInternet();

    /**
     * 抽象方法，用于获取保险保费。
     * Abstract method to get insurance premium.
     *
     * @return 保险保费。
     *         Insurance premium.
     * @author Fan Xinkang
     * @since version 1.0
     */
    public abstract double getInsurancePremium();

    /**
     * 重写 toString 方法，以便打印 ComputingDevice 对象。
     * Override the toString method to print ComputingDevice objects.
     *
     * @return 字符串表示。
     *         String representation.
     * @author Fan Xinkang
     * @since version 1.0
     */
    @Override
    public String toString() {
        return super.toString() + "\n" + STR."Storage: \{storage}GB, Processor: \{processor}";
    }
}
/*
 * End of models.ComputingDevice Class.
 * Checked by Fan Xinkang on 2025/04/15.
 */