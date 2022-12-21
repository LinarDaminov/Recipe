package com.example.recipe.controllers;

import com.example.recipe.model.Ingredient;
import com.example.recipe.services.impl.IngredientServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/ingredient")
public class IngredientController {

    private final IngredientServiceImpl ingredientServiceImpl;

    public IngredientController(IngredientServiceImpl ingredientServiceImpl) {
        this.ingredientServiceImpl = ingredientServiceImpl;
    }

    @PostMapping
    public ResponseEntity<Long> addIngredient(@RequestBody Ingredient ingredient) {
        long id = ingredientServiceImpl.addIngredient(ingredient);
        return ResponseEntity.ok().body(id);
    }

    @GetMapping("/{id}")
    public Ingredient getIngredient(@PathVariable("id") int id) {
        Ingredient getIngredient = ingredientServiceImpl.getIngredient(id);
        return ResponseEntity.ok().body(getIngredient).getBody();
    }


}
