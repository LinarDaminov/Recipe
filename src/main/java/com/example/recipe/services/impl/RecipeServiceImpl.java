package com.example.recipe.services.impl;

import com.example.recipe.model.Recipe;
import com.example.recipe.services.RecipeAlreadyExistsException;
import com.example.recipe.services.RecipeService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Map;
import java.util.TreeMap;

@Service
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class RecipeServiceImpl  implements RecipeService {

    private Map<Long, Recipe> recipes = new TreeMap<>();
    public static long id = 0;

    @Override
    public Recipe addRecipe(Recipe recipe) {

        if ((recipes.containsKey(id))) {
            throw new RecipeAlreadyExistsException("Данный рецепт уже есть");
        }
            recipes.getOrDefault(id, recipe);
            recipes.put(id++, recipe);

        return recipe;
    }

    @Override
    public Recipe getRecipe(long id) {
        return recipes.get(id);

    }
}

