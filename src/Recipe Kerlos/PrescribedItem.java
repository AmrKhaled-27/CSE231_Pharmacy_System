package pharmacysystem;

public class PrescribedItem {
    //private Item item;
    private int prescribedQuantity;
    private String dosage;
    private String instructions;

    public PrescribedItem(int prescribedQuantity, String dosage, String instructions) {
        //this.item = item;
        this.prescribedQuantity = prescribedQuantity;
        this.dosage = dosage;
        this.instructions = instructions;
    }

    /*public Item getItem() {
        return item;
    }*/

    public int getPrescribedQuantity() {
        return prescribedQuantity;
    }

    public String getDosage() {
        return dosage;
    }

    public String getInstructions() {
        return instructions;
    }
}