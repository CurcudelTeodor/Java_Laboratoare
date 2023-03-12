package org.example;

import java.util.HashMap;
import java.util.Map;

public class Company implements Node{
    private String name;

    public Map<Node,String> relationship;
    public Company(String name){
        this.name = name;
        this.relationship = new HashMap<>();
    }

    public void addRelationship(Person person, String type){
        this.relationship.put(person,type);
        person.relationship.put(this,type);
    }

    public Map<Node,String> getRelationship(){
        return this.relationship;
    }

    void printHashMap() {
        System.out.println("Relatiile companiei: " + this.name);
        for(Map.Entry<Node,String> entry : this.relationship.entrySet()){
            System.out.println(entry.getKey().getName()+ " : " + entry.getValue());
        }
    }

    @Override
    public int compareTo(Node otherCompany) {
        return this.name.compareTo(otherCompany.getName());
    }

    @Override
    public String getName() {
        return name;
    }
}
