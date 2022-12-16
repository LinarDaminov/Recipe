package com.example.recipe.services;

import com.example.recipe.model.Ingredient;

public interface IngredientService {
    void addIngredient(Ingredient ingredient);

    Ingredient getIngredient(int key);

}
