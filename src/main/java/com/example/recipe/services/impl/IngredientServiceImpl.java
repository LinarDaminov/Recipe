package com.example.recipe.services.impl;

import com.example.recipe.model.Ingredient;
import com.example.recipe.services.IngredientAlreadyExistsException;
import com.example.recipe.services.IngredientService;
import org.springframework.stereotype.Service;


import java.util.*;

@Service
public class IngredientServiceImpl implements IngredientService {
    private final Map<Long, Ingredient> ingredients = new TreeMap<>();
    public static long id = 0;


    @Override
    public Ingredient addIngredient(Ingredient ingredient) {

        ingredients.getOrDefault(id, ingredient);
        ingredients.put(id++, ingredient);
        return ingredient;

    }

    @Override
    public Ingredient getIngredient(long id) {
        if (ingredients.containsKey(id)) {
            return ingredients.get(id);
        }
        throw new IngredientAlreadyExistsException("Нет данного ингредиента!");
    }

    @Override
    public Ingredient removeIngredient(long id) {
        if (ingredients.containsKey(id)) {
            return ingredients.remove(id);
        }
        throw new IngredientAlreadyExistsException("Нет данного ингредиента!");
    }


    @Override
    public Ingredient editIngredient(long id, Ingredient ingredient) {
        if (ingredients.containsKey(id)) {
            ingredients.put(id, ingredient);
            return ingredient;
        }
        throw new IngredientAlreadyExistsException("Нет данного ингредиента!");

    }

    @Override
    public List<Ingredient> getAllIngredient() {
        return new ArrayList<>(this.ingredients.values());
    }
}

