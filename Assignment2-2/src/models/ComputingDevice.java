package models;

public abstract class ComputingDevice extends Technology {

    private int storage;
    private String processor;

    public ComputingDevice(String modelName, double price, Manufacturer manufacturer, String id, String processor, int storage) {
        super(modelName, price, manufacturer, id);
        this.processor = processor;
        this.storage = storage;
    }

    public int getStorage() {
        return storage;
    }

    public void setStorage(int storage) {
        if (storage >= 8 && storage <= 128 && storage % 8 == 0) {
            this.storage = storage;
        } else {
            System.out.println("Invalid storage value. Must be between 8 and 128 and divisible by 8.");
        }
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        if (processor.length() <= 20) {
            this.processor = processor;
        } else {
            this.processor = processor.substring(0, 20);
        }
    }

    public abstract String connectToInternet();

    public abstract double getInsurancePremium();

    @Override
    public String toString() {
        return super.toString() + "\n" + STR."ComputingDevice{storage='\{storage}', processor='\{processor}'}";
    }
}
