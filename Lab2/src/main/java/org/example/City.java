package org.example;

public class City extends Location {
    private int population;

    public City(String name, int x, int y) {
        super(name, "ORAS", x, y);
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public int getPopulation() {
        return population;
    }
}
