package com.example.recipe.controllers;

import com.example.recipe.model.Ingredient;
import com.example.recipe.services.IngredientService;
import com.example.recipe.services.impl.IngredientServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/ingredients")
public class IngredientController {

    private final IngredientService ingredientService;

    public IngredientController(IngredientServiceImpl ingredientServiceImpl) {
        this.ingredientService = ingredientServiceImpl;
    }


    @PostMapping
    public ResponseEntity<?> addIngredient(@RequestBody Ingredient ingredient) {
        if (StringUtils.isBlank(ingredient.getName())) {
            return ResponseEntity.badRequest().body("Название ингредиента пустое");
        }
        return ResponseEntity.ok(ingredientService.addIngredient(ingredient));
    }

    @GetMapping("/{id}")
    public Ingredient getIngredient(@PathVariable("id") int id) {
        return ingredientService.getIngredient(id);

    }

    @PutMapping("/{id}")
    public Ingredient editIngredient(@PathVariable("id") long id, @RequestBody Ingredient ingredient) {
        return ingredientService.editIngredient(id, ingredient);
    }

    @DeleteMapping("/{id}")
    public Ingredient removeIngredient(@PathVariable("id") long id) {

        return ingredientService.removeIngredient(id);
    }

    @GetMapping("/get")
    public List<Ingredient> getAll() {

        return ingredientService.getAllIngredient();
    }

}
