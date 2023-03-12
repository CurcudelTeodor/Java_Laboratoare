package org.example;

import java.util.Objects;

public abstract class Location {
    protected String name;
    protected String type;
    protected int x;
    protected int y;

    protected Location(String name, String type, int x, int y) {
        this.name = name;
        this.type = type;
        this.x = x;
        this.y = y;
    }

    public String getName() {
        return name;
    }

    protected int getX() {
        return x;
    }

    protected int getY() {
        return y;
    }

    /**
     * Returneaza distanta dintre locatia curenta (this) si locatia b.
     * Folosita pentru a valida lungimea unui drum
     * Argumentul b trebuie sa fie de tipul Location.
     * Distanta este cea Euclidiana, considerand doar primele 3 cifre dupa virgula.
     *
     * @param b locatia la care dorim sa ajungem, un capat al drumului
     * @return distanta dintre cele 2 locatii (this si b)
     * @author Teo
     */
    public double getDistanceTo(Location b) {
        return (Math.floor(Math.sqrt((b.getX() - this.getX()) * (b.getX() - this.getX()) + (b.getY() - this.getY()) * (b.getY() - this.getY())) * 1000) / 1000);
    }


    @Override
    public String toString() {
        return "Location{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", x=" + x +
                ", y=" + y +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Location location)) return false;
        return this.getX() == location.getX() && this.getY() == location.getY() && Objects.equals(name, location.name) && this.type == location.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, type, getX(), getY());
    }
}
