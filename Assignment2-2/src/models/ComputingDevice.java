package models;

import utils.Utilities;

public abstract class ComputingDevice extends Technology {

    private String processor;
    private int storage;

    private boolean processorSet = false;
    private boolean storageSet = false;

    public ComputingDevice(String modelName, double price, Manufacturer manufacturer, String id, String processor, int storage) {
        super(modelName, price, manufacturer, id);
        setProcessor(processor);
        setStorage(storage);
    }

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

    public abstract String connectToInternet();

    public abstract double getInsurancePremium();

    @Override
    public String toString() {
        return super.toString() + "\n" + STR."Storage: \{storage}GB, Processor: \{processor}";
    }
}
