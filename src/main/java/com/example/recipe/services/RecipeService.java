package com.example.recipe.services;

import com.example.recipe.model.Recipe;

public interface RecipeService {
     public long addRecipe(Recipe recipe);

    public Recipe getRecipe(long id);

}
