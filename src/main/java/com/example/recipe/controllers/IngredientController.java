package com.example.recipe.controllers;

import com.example.recipe.model.Ingredient;
import com.example.recipe.services.IngredientService;
import com.example.recipe.services.impl.IngredientServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/ingredients")
public class IngredientController {

    private final IngredientService ingredientService;

    public IngredientController(IngredientServiceImpl ingredientServiceImpl) {
        this.ingredientService = ingredientServiceImpl;
    }


    @PostMapping
    public ResponseEntity<Ingredient> addIngredient(@RequestBody Ingredient ingredient) {
        Ingredient ingredient1 = ingredientService.addIngredient(ingredient);
        return ResponseEntity.ok().body(ingredient1);
    }

    @GetMapping("/{id}")
    public Ingredient getIngredient(@PathVariable("id") int id) {
        Ingredient getIngredient = ingredientService.getIngredient(id);
        return ResponseEntity.ok().body(getIngredient).getBody();
    }


}
