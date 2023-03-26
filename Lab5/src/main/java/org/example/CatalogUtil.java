package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public abstract class CatalogUtil {
    public CatalogUtil() {
    }

    public static void save(Catalog catalog, String path) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File(path), catalog);
    }

    public static Catalog load(String path) throws InvalidCatalogException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Catalog catalog = objectMapper.readValue(new File(path), Catalog.class);
            return catalog;
        }catch (IOException ex){
            throw new InvalidCatalogException(ex);
        }

    }

    public static void view(Document doc) {
        System.out.println(doc);
    }

}
