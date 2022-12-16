package com.example.recipe.services;

import com.example.recipe.model.Recipe;

public interface RecipeService {
     void addRecipe(Recipe recipe);

    Recipe getRecipe(int key);

}
