package com.example.recipe.services;

import com.example.recipe.model.Ingredient;
import com.example.recipe.services.IngredientAlreadyExistsException;
import com.example.recipe.services.IngredientService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service
public class IngredientServiceImpl implements IngredientService {

    private final Map<Long, Ingredient> ingredients = new HashMap<>();
    public static long id = 0;

    private final Path path;
    private final ObjectMapper objectMapper;


    public IngredientServiceImpl(@Value("${application.file.ingredients}") String path) {
        try {
            this.path = Paths.get(path);
            this.objectMapper = new ObjectMapper();
        } catch (InvalidPathException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @PostConstruct
    private void init() {
        readDataFromFile();
    }

    private void readDataFromFile() {
        try {
            byte[] file = Files.readAllBytes(path);
            Map<Long, Ingredient> mapFromFile = objectMapper.readValue(file, new TypeReference<>() {
            });
            ingredients.putAll(mapFromFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeDataToFile(Map<Long, Ingredient> ingredients) {
        try {
            byte[] bytes = new ObjectMapper().writeValueAsBytes(ingredients);
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
    public Ingredient addIngredient(Ingredient ingredient) {

        ingredients.getOrDefault(id, ingredient);
        ingredients.put(id++, ingredient);
        writeDataToFile(ingredients);
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
            Ingredient ingredient = ingredients.remove(id);
            writeDataToFile(ingredients);
            return ingredient;
        }
        throw new IngredientAlreadyExistsException("Нет данного ингредиента!");
    }


    @Override
    public Ingredient editIngredient(long id, Ingredient ingredient) {
        if (ingredients.containsKey(id)) {
            ingredients.put(id, ingredient);
            writeDataToFile(ingredients);
            return ingredient;
        }
        throw new IngredientAlreadyExistsException("Нет данного ингредиента!");

    }

    @Override
    public List<Ingredient> getAllIngredient() {
        return new ArrayList<>(this.ingredients.values());
    }

    @Override
    public void importIngredients(MultipartFile ingredients) {
        try {
            Map<Long, Ingredient> mapFromRequest = objectMapper.readValue(ingredients.getBytes(),
                    new TypeReference<Map>() {
                    });
            writeDataToFile(mapFromRequest);
        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}





