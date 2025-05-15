package edu.cse231.models.Recipies;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.cse231.interfaces.Searchable;

public class RecipesInventory implements Searchable<Recipe> {

    private final List<Recipe> recipes;

    public RecipesInventory() {
        this.recipes = new ArrayList<>();
    }

    public void addRecipe(Recipe recipe) {
        this.recipes.add(recipe);
    }

    @Override
    public Recipe searchById(String recipeId) {
        for (Recipe recipe : recipes) {
            if (recipe.getId().equals(recipeId)) {
                return new Recipe(
                        recipe.getId(),
                        recipe.getPatientName(),
                        recipe.getDoctorName(),
                        recipe.getDateIssued(),
                        recipe.getExpirationDate()
                );
            }
        }
        return null;
    }

    @Override
    public Recipe searchByName(String patientOrDoctorNameQuery) {
        if (patientOrDoctorNameQuery == null || patientOrDoctorNameQuery.trim().isEmpty()) {
            return null;
        }
        String lowerCaseQuery = patientOrDoctorNameQuery.toLowerCase();
        for (Recipe recipe : recipes) {
            if ((recipe.getPatientName() != null && recipe.getPatientName().toLowerCase().contains(lowerCaseQuery))
                    || (recipe.getDoctorName() != null && recipe.getDoctorName().toLowerCase().contains(lowerCaseQuery))) {
                return new Recipe(
                        recipe.getId(),
                        recipe.getPatientName(),
                        recipe.getDoctorName(),
                        recipe.getDateIssued(),
                        recipe.getExpirationDate()
                );
            }
        }
        return null;
    }

    public List<Recipe> getAllRecipes() {
        List<Recipe> copies = new ArrayList<>();
        for (Recipe recipe : recipes) {
            copies.add(new Recipe(
                    recipe.getId(),
                    recipe.getPatientName(),
                    recipe.getDoctorName(),
                    recipe.getDateIssued(),
                    recipe.getExpirationDate()
            ));
        }
        return Collections.unmodifiableList(copies);
    }

    public boolean isValidRecipe(String recipeId) {
        Recipe recipe = searchById(recipeId);
        if (recipe == null) {
            return false;
        }
        return recipe.isValid() && recipe.isActive();
    }

    public void markRecipeFulfilled(String recipeId) {
        Recipe recipe = searchById(recipeId);
        if (recipe != null) {
            recipe.markAsFulfilled();
        }
    }
}
