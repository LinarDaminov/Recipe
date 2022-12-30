package com.example.recipe.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class Recipe {
    private String recipeName;
    private int time;
    private List<Ingredient> ingredientList = new ArrayList<>();
    private List<String> stepList = new ArrayList<>();



}
