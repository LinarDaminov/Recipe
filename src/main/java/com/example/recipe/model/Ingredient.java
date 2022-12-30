package com.example.recipe.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Ingredient {
    private String name;
    private int quantityOfIngredient;
    private String measurement;


}
