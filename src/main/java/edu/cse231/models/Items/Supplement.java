package edu.cse231.models.Items;

public class Supplement extends Item {

    private final String ingredients;
    private final String recommendedDosage;

    public Supplement(String id, String name, double price,
            String ingredients, String recommendedDosage) {
        super(id, name, price);
        this.ingredients = ingredients;
        this.recommendedDosage = recommendedDosage;
    }

    public String getIngredients() {
        return ingredients;
    }

    public String getRecommendedDosage() {
        return recommendedDosage;
    }

    @Override
    public String getDetails() {
        return "Supplement[name=" + getName()
                + ", ingredients=" + ingredients
                + ", dosage=" + recommendedDosage + "]";
    }

    @Override
    public Item copy() {
        return new Supplement(getId(), getName(), getPrice(), ingredients, recommendedDosage);
    }
}
