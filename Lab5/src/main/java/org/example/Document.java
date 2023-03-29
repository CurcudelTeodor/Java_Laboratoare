package org.example;

import java.io.File;
import java.io.FileNotFoundException;

public class Document {
    private String id;
    public String title;
    private String location;

    public Document() {
    }

    public Document(String id, String title, String location) throws FileNotFoundException {

        this.id = id;
        this.title = title;
        if(!new File(location).exists())
            throw new FileNotFoundException("Fisierul nu exista : " + location);
        this.location = location;
    }

    public String getId() {
        return id;
    }


    public String getLocation() {
        return location;
    }

    public String getTitle() {
        return title;
    }

    public void setId(String id) {
        this.id = id;
    }
    public void setLocation(String location) {
        this.location = location;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", location='" + location + '\'' +
                '\n';
    }
}
