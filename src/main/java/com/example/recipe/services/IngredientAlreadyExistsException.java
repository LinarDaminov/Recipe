package com.example.recipe.services;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class IngredientAlreadyExistsException extends RuntimeException {
    public IngredientAlreadyExistsException(String message) {
        super(message);
    }
}
