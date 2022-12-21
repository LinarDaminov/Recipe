package com.example.recipe.model;

import lombok.Data;

@Data
public class Ingredient {
    private String name;
    private int quantityOfIngredient;
    private String measurement;


    public Ingredient(String name, int quantityOfIngredient, String measurement) {
        this.name = name;
        this.quantityOfIngredient = quantityOfIngredient;
        this.measurement = measurement;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantityOfIngredient() {
        return quantityOfIngredient;
    }

    public void setQuantityOfIngredient(int quantityOfIngredient) {
        this.quantityOfIngredient = quantityOfIngredient;
    }

    public String getMeasurement() {
        return measurement;
    }

    public void setMeasurement(String measurement) {
        this.measurement = measurement;
    }
}
