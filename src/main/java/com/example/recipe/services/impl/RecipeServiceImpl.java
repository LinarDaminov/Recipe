package com.example.recipe.services.impl;

import com.example.recipe.model.Recipe;
import com.example.recipe.services.IngredientAlreadyExistsException;
import com.example.recipe.services.RecipeAlreadyExistsException;
import com.example.recipe.services.RecipeService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service

public class RecipeServiceImpl implements RecipeService {

    private Map<Long, Recipe> recipes = new TreeMap<>();
    public static long id = 0;

    @Override
    public Recipe addRecipe(Recipe recipe) {

        recipes.getOrDefault(id, recipe);
        recipes.put(id++, recipe);

        return recipe;

    }


    @Override
    public Recipe getRecipe(long id) {
        if (recipes.containsKey(id)) {
            return recipes.get(id);
        }
        throw new RecipeAlreadyExistsException("Нет такого рецепта!");

    }

    @Override
    public Recipe editRecipe(long id, Recipe recipe) {
        if (recipes.containsKey(id)) {
            recipes.put(id, recipe);
            return recipe;
        }
        throw new IngredientAlreadyExistsException("Нет данного рецепта!");
    }

    @Override
    public Recipe remove(long id) {
        if (recipes.containsKey(id)) {
            return recipes.remove(id);
        }
        throw new RecipeAlreadyExistsException("Нет данного рецепта!");

    }

    @Override
    public List<Recipe> getAllIngredient() {
        return new ArrayList<>(this.recipes.values());
    }

}


