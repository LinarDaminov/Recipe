package com.example.recipe.controllers;


import com.example.recipe.model.Recipe;
import com.example.recipe.services.RecipeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/recipes")
@Tag(name = "Книга рецептов", description = "CRUD-операции, для работы с книгой рецептов")
public class RecipeController {
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }


    @PostMapping
    @Operation(
            summary = "Добавление рецепта"
    )
    public ResponseEntity<Recipe> addRecipe(@RequestBody Recipe recipe) {
        Recipe recipeId = recipeService.addRecipe(recipe);
        return ResponseEntity.ok().body(recipeId);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Получение рецепта по id"
    )
    public Recipe getRecipe(@PathVariable("id") int id) {
        Recipe getRecipe = recipeService.getRecipe(id);
        return ResponseEntity.ok().body(getRecipe).getBody();
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Изменение рецепта по id  "
    )
    public Recipe editRecipe(@PathVariable("id") long id, @RequestBody Recipe recipe) {
        return recipeService.editRecipe(id, recipe);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Удаление рецепта по id  "
    )

    public Recipe deleteRecipe(@PathVariable("id") long id) {
        return recipeService.remove(id);
    }

    @GetMapping
    @Operation(
            summary = "Получение всех  рецептов  "
    )
    public List<Recipe> getAll() {

        return recipeService.getAllIngredient();
    }

    @GetMapping(value = "/export")
    public ResponseEntity<byte[]> downloadRecipes() {
        byte[] bytes = recipeService.getAllInByte();
        if (bytes == null) {
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .contentLength(bytes.length)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename =\"recipes.json\"")
                .body(bytes);
    }

    @PostMapping(value = "/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void importRecipes(@RequestParam("recipes") MultipartFile recipes) {
        recipeService.importRecipes(recipes);
    }

    @GetMapping("/export")
    public ResponseEntity<byte[]> exportTxt() {
        byte[] bytes = recipeService.exportTxt();
        if (bytes == null) {
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok()
                .contentType(MediaType.TEXT_PLAIN)
                .contentLength(bytes.length)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename =\"info.txt\"")
                .body(bytes);
    }
}
