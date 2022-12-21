package com.example.recipe.services;
import com.example.recipe.model.Ingredient;

public interface IngredientService {
    public Ingredient addIngredient(Ingredient ingredient);


    public Ingredient getIngredient(long key);

}
