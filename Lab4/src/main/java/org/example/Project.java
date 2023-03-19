package org.example;

public class Project implements Node{

    public String name;

    public Project(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Project{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public String getName() {
        return this.name;
    }
}
