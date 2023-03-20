package org.example;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Catalog implements Serializable {
    private String name;
    private List<Document> docs = new ArrayList<>();

    public Catalog(String name) {
        this.name = name;
    }

    public Document findById(String id) {
        for (var doc : docs) {
            if (doc.getId().equals(id)) {
                return doc;
            }
        }
        return null;
    }

    public void add(Document doc) {
        docs.add(doc);
    }

    @Override
    public String toString() {
        return "Catalog{" +
                "name='" + name + '\'' +
                ", docs=" + docs +
                '}';
    }
}
