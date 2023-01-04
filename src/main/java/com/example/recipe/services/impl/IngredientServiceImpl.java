package com.example.recipe.services.impl;

import com.example.recipe.model.Ingredient;
import com.example.recipe.services.FileService;
import com.example.recipe.services.IngredientAlreadyExistsException;
import com.example.recipe.services.IngredientService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;


import javax.annotation.PostConstruct;
import java.util.*;

@Service
public class IngredientServiceImpl implements IngredientService {
    private final FileService fileService;
    private Map<Long, Ingredient> ingredients = new TreeMap<>();
    public static long id = 0;

    public IngredientServiceImpl(FileService fileService) {
        this.fileService = fileService;
    }


    @Override
    public Ingredient addIngredient(Ingredient ingredient) {

        ingredients.getOrDefault(id, ingredient);
        ingredients.put(id++, ingredient);
        saveToFile();
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
            saveToFile();
            return ingredient;
        }
        throw new IngredientAlreadyExistsException("Нет данного ингредиента!");

    }

    @Override
    public List<Ingredient> getAllIngredient() {
        return new ArrayList<>(this.ingredients.values());
    }


    private void saveToFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(ingredients);
            fileService.saveToFile(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
    @PostConstruct
    private void init() {
         readFromFile();
    }

    private void readFromFile() {
        String json = fileService.readFromFile();
        try {
            ingredients = new ObjectMapper().readValue(json, new TypeReference<TreeMap<Long, Ingredient>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}

