package org.example;

import java.util.Objects;

public class Road
{
    public String name;
    public RoadType type;
    public double length;
    public double speedLimit;

    public Road(String name, RoadType type, double length, Location a, Location b ) {
        this.name = name;
        this.type = type;
        if(length<a.getDistanceTo(b))
            throw new IllegalArgumentException("Lungimea e mai mica decat distanta Euclidiana intre cele 2 locatii!");
        this.length = length;
        this.speedLimit = setSpeedLimit(type);
    }

    public int setSpeedLimit(RoadType type)
    {
       switch (type)
       {
           case AUTOSTRADA:
               return 130;
           case EXPRES:
               return 120;
           case NATIONAL:
               return 100;
           default:
               return 90;
       }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Road road)) return false;
        return Objects.equals(name, road.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Road{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", length=" + length +
                ", speedLimit=" + speedLimit +
                '}';
    }
}
