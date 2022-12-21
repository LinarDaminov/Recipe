package com.example.recipe.controllers;

import com.example.recipe.model.Recipe;
import com.example.recipe.services.impl.RecipeServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/recipe")
public class RecipeController {
    private final RecipeServiceImpl recipeServiceImpl;

    public RecipeController(RecipeServiceImpl recipeServiceImpl) {
        this.recipeServiceImpl = recipeServiceImpl;
    }


    @PostMapping

    public ResponseEntity<Long> addRecipe(@RequestBody Recipe recipe) {
        long recipeId = recipeServiceImpl.addRecipe(recipe);
        return ResponseEntity.ok().body(recipeId);
    }

    @GetMapping("/{id}")
    public Recipe getRecipe(@PathVariable("id") int id) {
        Recipe getRecipe = recipeServiceImpl.getRecipe(id);
        return ResponseEntity.ok().body(getRecipe).getBody();
    }

}
