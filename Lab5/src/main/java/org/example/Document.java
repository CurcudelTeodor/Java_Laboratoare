package org.example;

import java.util.HashMap;
import java.util.Map;

public class Document {
    private String id;

    public String getId() {
        return id;
    }

    private String title;
    private String location;

    private Map<String, Object> tags = new HashMap<>();

    public Document(String id, String title){
        this.id = id;
        this.title = title;
    }

    public void addTag(String key, Object obj) {
        tags.put(key, obj);
    }
}
