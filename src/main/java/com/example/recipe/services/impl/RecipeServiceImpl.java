package com.example.recipe.services.impl;

import com.example.recipe.model.Recipe;
import com.example.recipe.services.RecipeService;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.TreeMap;

@Service
public class RecipeServiceImpl implements RecipeService {
    private Map<Integer, Recipe> recipes = new TreeMap<>();
    public static int recipeId = 0;



    @Override
    public void addRecipe(Recipe recipe) {
        recipes.put(recipeId++, recipe);
    }

    @Override
    public Recipe getRecipe(int key) {
        return recipes.get(key) ;
    }
}
