package org.example;

import java.util.Objects;

public class Location
{
    public String name;
    public  LocationType type;
    private int x;
    private int y;

    public Location(String name, LocationType type, int x, int y)
    {

        this.name = name;
        this.type = type;
        this.x = x;
        this.y = y;
    }

    public int getX()
    {
        return x;
    }
    public int getY()
    {
        return y;
    }

    public double getDistanceTo(Location b)
    {
        return ( Math.floor(Math.sqrt((b.getX()-this.getX())*(b.getX()-this.getX()) + (b.getY()-this.getY())*(b.getY()-this.getY()))*1000
        )/1000 );
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
