package com.example.recipe.services;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class RecipeAlreadyExistsException extends RuntimeException {

    public RecipeAlreadyExistsException(String message) {

        super(message);
    }
}
