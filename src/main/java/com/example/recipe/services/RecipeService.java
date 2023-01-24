package com.example.recipe.services;

import com.example.recipe.model.Recipe;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface RecipeService {
    Recipe addRecipe(Recipe recipe);

    Recipe getRecipe(long id);

    Recipe editRecipe(long id, Recipe recipe);

    Recipe remove(long id);

    List<Recipe> getAllIngredient();
    byte[] getAllInByte();

    byte[] exportTxt();

    void importRecipes(MultipartFile recipes);
}
