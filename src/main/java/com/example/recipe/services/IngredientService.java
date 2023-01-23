package com.example.recipe.services;

import com.example.recipe.model.Ingredient;
import org.springframework.core.io.InputStreamResource;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.List;

public interface IngredientService {
    Ingredient addIngredient(Ingredient ingredient);


    Ingredient getIngredient(long id);


    Ingredient removeIngredient(long id);

    Ingredient editIngredient(long id, Ingredient newIngredient);


    List<Ingredient> getAllIngredient();

    void importIngredients(MultipartFile ingredients);
}
