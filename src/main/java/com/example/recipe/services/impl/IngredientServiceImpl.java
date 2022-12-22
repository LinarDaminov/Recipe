package com.example.recipe.services.impl;

import com.example.recipe.model.Ingredient;
import com.example.recipe.services.IngredientAlreadyExistsException;
import com.example.recipe.services.IngredientService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;


import java.util.*;

@Service
@ResponseStatus(code = HttpStatus.BAD_REQUEST)

public class IngredientServiceImpl implements IngredientService {
    private final Map<Long, Ingredient> ingredients = new TreeMap<>();
    public static long id = 0;


    @Override
    public Ingredient addIngredient(Ingredient ingredient) {
        if (ingredients.containsKey(id)) {
            throw new IngredientAlreadyExistsException("Данный ингредиент уже существует!");
        }
        ingredients.getOrDefault(id, ingredient);
        ingredients.put(id++, ingredient);
        return ingredient;
    }


    @Override
    public Ingredient getIngredient(long id) {
        return ingredients.get(id);
    }
}

