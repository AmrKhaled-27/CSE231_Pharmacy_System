package edu.cse231.models.Items;

public class HealthDevice extends Item {

    private final String manufacturer;
    private final String model;

    public HealthDevice(String id, String name, double price,
            String manufacturer, String model) {
        super(id, name, price);
        this.manufacturer = manufacturer;
        this.model = model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getModel() {
        return model;
    }

    public String getDisplayDetails() {
        return "Device[name=" + getName()
                + ", manufacturer=" + manufacturer
                + ", model=" + model + "]";
    }

    @Override
    public String getDetails() {
        return getDisplayDetails();
    }

    @Override
    public Item copy() {
        return new HealthDevice(getId(), getName(), getPrice(), manufacturer, model);
    }
}
