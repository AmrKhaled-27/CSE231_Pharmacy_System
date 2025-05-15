package edu.cse231.models.Items;

public class Medication extends Item {

    private final String strength;
    private final String dosageForm;
    private final String activeIngredient;

    public Medication(String id, String name, double price,
            String strength, String dosageForm, String activeIngredient) {
        super(id, name, price);
        this.strength = strength;
        this.dosageForm = dosageForm;
        this.activeIngredient = activeIngredient;
    }

    public String getStrength() {
        return strength;
    }

    public String getDosageForm() {
        return dosageForm;
    }

    public String getActiveIngredient() {
        return activeIngredient;
    }

    @Override
    public String getDetails() {
        return "Medication[name=" + getName()
                + ", strength=" + strength
                + ", form=" + dosageForm
                + ", ingredient=" + activeIngredient + "]";
    }

    @Override
    public Item copy() {
        return new Medication(getId(), getName(), getPrice(), strength, dosageForm, activeIngredient);
    }
}
