package com.example.recipe.services;

public interface FileService {
    boolean saveToFile(String json);

    String readFromFile();

}
