package com.example.recipe.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data

public class Recipe {
    private String recipeName;
    private int time;
    private List<Ingredient> ingredientList = new ArrayList<>();
    private List<String> stepList = new ArrayList<>();


    public Recipe(String recipeName, int time, List<Ingredient> ingredientList, List<String> stepList) {
        this.recipeName = recipeName;
        this.time = time;
        this.ingredientList = ingredientList;
        this.stepList = stepList;
    }
}
