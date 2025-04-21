package com.api.utils;
import com.jayway.jsonpath.JsonPath;

import java.io.InputStream;
public class JsonFileManager {
    private final Object jsonDocument;
    public JsonFileManager (String filePath){
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(filePath);
            if (inputStream == null) {
                throw new IllegalArgumentException("File Not Found" + filePath);
            }
            this.jsonDocument = JsonPath.parse(inputStream).json();
        } catch (Exception e){
            throw new RuntimeException("Error loading json file" + filePath, e);
        }
    }

    public String getString(String jsonPath){
          return JsonPath.read(jsonDocument, jsonPath);
    }
    public Integer getInt(String jsonPath){
        return JsonPath.read(jsonDocument, jsonPath);
    }
}
