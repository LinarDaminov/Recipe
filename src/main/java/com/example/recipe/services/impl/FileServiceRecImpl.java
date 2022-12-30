package com.example.recipe.services.impl;

import com.example.recipe.services.FileService;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileServiceRecImpl implements FileService {
    @Value("${path.to.data.file}")
    private String dataFilePath;
    @Value("${name2.of.data.file}")
    private String name2;

    @Override
    public boolean saveToFile(String json) {
        try {
            cleanDataFile();
            Files.writeString(Path.of(dataFilePath, name2), json);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public String readFromFile() {
        try {
            return Files.readString(Path.of(dataFilePath, name2));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private boolean cleanDataFile() {
        try {
            Path path = Path.of(dataFilePath, name2);
            Files.deleteIfExists(path);
            Files.createFile(path);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

}
