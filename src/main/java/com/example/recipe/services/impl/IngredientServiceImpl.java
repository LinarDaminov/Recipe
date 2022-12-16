package com.example.recipe.services.impl;
import com.example.recipe.model.Ingredient;
import com.example.recipe.services.IngredientService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class IngredientServiceImpl implements IngredientService {

    public static int id=0;

    private static Map<Integer, Ingredient> ingredients = new TreeMap<>();


    @Override
    public void addIngredient(Ingredient ingredient) {
        ingredients.getOrDefault(id++, ingredient);

    }

    @Override
    public Ingredient getIngredient(int key) {
        return ingredients.get(key);

    }
}

