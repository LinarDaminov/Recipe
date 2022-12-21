package com.example.recipe.services.impl;

import com.example.recipe.model.Recipe;
import com.example.recipe.services.RecipeService;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.TreeMap;

@Service
public class RecipeServiceImpl implements RecipeService {

    private Map<Integer, Recipe> recipes = new TreeMap<>();
    public static int id = 0;

    @Override
    public long addRecipe(Recipe recipe) {

        if ((recipes.containsKey(id))) {

            throw new RuntimeException("Данный рецепт уже существует");
        } else {
            recipes.getOrDefault(id, recipe);
            recipes.put(id, recipe);
        }
        return id++;
    }

    @Override
    public Recipe getRecipe(long id) {
        return recipes.get(id);

    }
}
