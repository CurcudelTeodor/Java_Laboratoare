package org.example;

public class Programmer extends Person{
    private String limbaj;
    public Programmer(String name, String birthDate, String limbajProgramare){
        super(name,birthDate);
        this.limbaj=limbajProgramare;
    }

    public String getLimbaj() {
        return limbaj;
    }
}
