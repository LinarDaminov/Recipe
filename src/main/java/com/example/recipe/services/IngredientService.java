package com.example.recipe.services;
import com.example.recipe.model.Ingredient;

public interface IngredientService {
    public long addIngredient(Ingredient ingredient);

    public Ingredient getIngredient(long key);

}
