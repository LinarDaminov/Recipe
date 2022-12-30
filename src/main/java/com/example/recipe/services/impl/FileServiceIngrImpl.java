package com.example.recipe.services.impl;

import com.example.recipe.services.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class FileServiceIngrImpl implements FileService {
    @Value("${path.to.data.file}")
    private String dataFilePath;
    @Value("${name1.of.data.file}")
    private String name1;


    @Override
    public boolean saveToFile(String json) {
        try {
            cleanDataFile();
            Files.writeString(Path.of(dataFilePath, name1), json);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public String readFromFile() {
        try {
            return Files.readString(Path.of(dataFilePath, name1));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private boolean cleanDataFile() {
        try {
            Path path = Path.of(dataFilePath, name1);
            Files.deleteIfExists(path);
            Files.createFile(path);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

}
