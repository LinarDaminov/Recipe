package com.example.recipe.controllers;

import com.example.recipe.model.Ingredient;
import com.example.recipe.services.IngredientAlreadyExistsException;
import com.example.recipe.services.IngredientService;
import com.example.recipe.services.IngredientServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
@RequestMapping("/ingredients")
@Tag(name = "Книга ингредиентов", description = "CRUD-операции, для работы с книгой ингредиентов")


public class IngredientController {

    private final IngredientService ingredientService;

    public IngredientController(IngredientServiceImpl ingredientServiceImpl) {
        this.ingredientService = ingredientServiceImpl;
    }


    @PostMapping
    @Operation(
            summary = "Добавление ингредиента  "
    )
    public ResponseEntity<?> addIngredient(@RequestBody Ingredient ingredient) {
        if (StringUtils.isBlank(ingredient.getName())) {
            throw new IngredientAlreadyExistsException("Название ингредиента пустое!");
        }
        return ResponseEntity.ok(ingredientService.addIngredient(ingredient));
    }


    @GetMapping("/{id}")
    @Operation(
            summary = "Получение ингредиента по id "
    )
    public Ingredient getIngredient(@PathVariable("id") int id) {

        return ingredientService.getIngredient(id);

    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Изменение  ингредиента по id "
    )
    public Ingredient editIngredient(@PathVariable("id") long id, @RequestBody Ingredient ingredient) {
        return ingredientService.editIngredient(id, ingredient);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Удаление ингредиента по id "
    )
    public Ingredient removeIngredient(@PathVariable("id") long id) {

        return ingredientService.removeIngredient(id);
    }

    @GetMapping("/get")
    @Operation(
            summary = "Получение всех ингредиентов "
    )
    public List<Ingredient> getAll() {

        return ingredientService.getAllIngredient();
    }

    @PostMapping(value = "/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void importIngredients(@RequestParam("ingredients") MultipartFile ingredients) {
        ingredientService.importIngredients(ingredients);
    }


}