package com.example.recipe.services.impl;

import com.example.recipe.model.Recipe;
import com.example.recipe.services.FileService;
import com.example.recipe.services.IngredientAlreadyExistsException;
import com.example.recipe.services.RecipeAlreadyExistsException;
import com.example.recipe.services.RecipeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service

public class RecipeServiceImpl implements RecipeService {
    private final FileService fileService;
    private Map<Long, Recipe> recipes = new TreeMap<>();
    public static long id = 0;

    public RecipeServiceImpl(FileService fileService) {
        this.fileService = fileService;
    }


    @Override
    public Recipe addRecipe(Recipe recipe) {

        recipes.getOrDefault(id, recipe);
        recipes.put(id++, recipe);
        saveToFile();
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
            saveToFile();
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

    private void saveToFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(recipes);
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
            recipes = new ObjectMapper().readValue(json, new TypeReference<TreeMap<Long, Recipe>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}


