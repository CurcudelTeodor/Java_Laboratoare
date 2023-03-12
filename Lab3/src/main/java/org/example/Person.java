package org.example;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
public class Person implements Node{
    private String name;
    private String birthDate;

    public Map<Node,String> relationship;

    public Map<Node, String> getRelationship() {
        return this.relationship;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", relationship=" + relationship +
                '}';
    }

    void printHashMap() {
        System.out.println("Relatiile lui: " + this.name);
        for(Map.Entry<Node,String> entry : this.relationship.entrySet()){
            System.out.println(entry.getKey().getName()+ " : " + entry.getValue());
        }
    }

    public void addRelationshipPerson(Person person, String type){
        this.relationship.put(person,type);
        person.relationship.put(this,type);
    }

    public void addRelationshipCompany(Company company, String type){
        this.relationship.put(company,type);
        company.relationship.put(this,type);
    }

    public Person(String name,String birthDate) {
        this.name = name;
        this.birthDate=birthDate;
        this.relationship=new HashMap<>();
    }

    @Override
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name=name;
    }

    //@Override
    public int compareTo(Node otherPerson) {
        return this.name.compareTo(otherPerson.getName());
    }
}
