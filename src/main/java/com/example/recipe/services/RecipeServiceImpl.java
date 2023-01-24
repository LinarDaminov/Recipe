package com.example.recipe.services;

import com.example.recipe.model.Ingredient;
import com.example.recipe.model.Recipe;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service

public class RecipeServiceImpl implements RecipeService {
    private final Map<Long, Recipe> recipes = new HashMap<>();
    public static long id = 0;
    private final Path path;
    private final Path pathToTxtTemplate;
    private final ObjectMapper objectMapper;

    public RecipeServiceImpl(@Value("${application.file.recipes}") String path) {
        try {
            this.path = Paths.get(path);
            this.pathToTxtTemplate = Paths.get(
                    RecipeServiceImpl.class.getResource("recipesTemplate.txt").toURI());
            this.objectMapper = new ObjectMapper();
        } catch (InvalidPathException e) {
            e.printStackTrace();
            throw e;
        } catch (URISyntaxException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }


    @PostConstruct
    private void init() {
        readDataFromFile();
    }

    private void readDataFromFile() {
        try {
            byte[] file = Files.readAllBytes(path);
            Map<Long, Recipe> mapFromFile = objectMapper.readValue(file, new TypeReference<Map>() {
            });
            recipes.putAll(mapFromFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeDataToFile(Map<Long, Recipe> recipes) {
        try {
            byte[] bytes = new ObjectMapper().writeValueAsBytes(recipes);
            try {
                Files.write(path, bytes);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }


    @Override
    public Recipe addRecipe(Recipe recipe) {

        recipes.getOrDefault(id, recipe);
        recipes.put(id++, recipe);
        writeDataToFile(recipes);
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
            writeDataToFile(recipes);
            return recipe;

        }
        throw new IngredientAlreadyExistsException("Нет данного рецепта!");
    }

    @Override
    public Recipe remove(long id) {
        if (recipes.containsKey(id)) {
            writeDataToFile(recipes);
            return recipes.remove(id);
        }
        throw new RecipeAlreadyExistsException("Нет данного рецепта!");

    }

    @Override
    public List<Recipe> getAllIngredient() {
        return new ArrayList<>(this.recipes.values());
    }

    @Override
    public byte[] getAllInByte() {
        try {
            return Files.readAllBytes(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void importRecipes(MultipartFile recipes) {
        try {
            Map<Long, Recipe> mapFromRequest = objectMapper.readValue(recipes.getBytes(),
                    new TypeReference<Map>() {
                    });
            writeDataToFile(mapFromRequest);
        } catch (IOException e) {
            e.printStackTrace();

        }
    }
    @Override
    public byte[] exportTxt() {
        try {
            String template = Files.readString(pathToTxtTemplate, StandardCharsets.UTF_8);
            StringBuilder stringBuilder = new StringBuilder();
            for (Recipe recipe : recipes.values()) {
                StringBuilder ingredients = new StringBuilder();
                StringBuilder steps = new StringBuilder();
                for (Ingredient ingredient : recipe.getIngredientList()) {
                    ingredients.append("~").append(ingredient).append("\n");
                    int stepCounter = 1;
                    for (String step : recipe.getStepList()) {
                        steps.append(stepCounter++).append(". ").append(step).append("\n");
                    }
                }
                String recipeData = template.replace("%recipeName%", recipe.getRecipeName())
                        .replace("%time%", String.valueOf(recipe.getTime()))
                        .replace("%ingredients%", ingredients.toString())
                        .replace("%steps%", steps.toString());
                stringBuilder.append(recipeData).append("\n\n\n");
            }
            return stringBuilder.toString().getBytes(StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

