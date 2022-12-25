package com.example.recipe.controllers;


import com.example.recipe.model.Recipe;
import com.example.recipe.services.RecipeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recipes")
public class RecipeController {
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }


    @PostMapping
    public ResponseEntity<Recipe> addRecipe(@RequestBody Recipe recipe) {
        Recipe recipeId = recipeService.addRecipe(recipe);
        return ResponseEntity.ok().body(recipeId);
    }

    @GetMapping("/{id}")
    public Recipe getRecipe(@PathVariable("id") int id) {
        Recipe getRecipe = recipeService.getRecipe(id);
        return ResponseEntity.ok().body(getRecipe).getBody();
    }

    @PutMapping("/{id}")
    public Recipe editRecipe(@PathVariable("id") long id, @RequestBody Recipe recipe) {
        return recipeService.editRecipe(id, recipe);
    }

    @DeleteMapping("/{id}")
    public Recipe deleteRecipe(@PathVariable("id") long id) {
        return recipeService.remove(id);
    }

    @GetMapping
    public List<Recipe> getAll() {

        return recipeService.getAllIngredient();
    }

}
