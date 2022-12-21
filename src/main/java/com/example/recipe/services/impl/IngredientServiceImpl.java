package com.example.recipe.services.impl;

import com.example.recipe.model.Ingredient;
import com.example.recipe.services.IngredientService;
import org.springframework.stereotype.Service;


import java.util.*;

@Service
public class IngredientServiceImpl implements IngredientService {
    private final Map<Long, Ingredient> ingredients = new TreeMap<>();
    public static long id = 0;


    @Override
    public long addIngredient(Ingredient ingredient) {
        if (ingredients.containsKey(id)) {
            throw new RuntimeException("Данный ингредиент уже существует!");
        } else {
            ingredients.getOrDefault(id, ingredient);
            ingredients.put(id, ingredient);
        }
        return id++;
    }

    @Override
    public Ingredient getIngredient(long id) {
        return ingredients.get(id);

    }
}

