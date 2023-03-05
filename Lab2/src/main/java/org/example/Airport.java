package org.example;

public class Airport extends Location
{
    private int numberTerminals;

    public Airport(String name, int x, int y) {
        super(name, "AEROPORT", x, y);
    }

    public void setNumberTerminals(int numberTerminals) {
        this.numberTerminals = numberTerminals;
    }

    public int getNumberTerminals() {
        return numberTerminals;
    }

}
