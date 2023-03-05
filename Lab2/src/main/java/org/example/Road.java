package org.example;

import java.util.Objects;

public class Road
{
    public String name;
    public RoadType type;
    public double length;
    public double speedLimit;

    public String plecare;
    public String destinatie;

    /**
     * Constructor care creeaza un drum intre 2 locatii.
     * Verifica cu ajutorul metodei getDistanceTo ca lungimea sa nu depaseasca
     * distanta Euclidiana intre locatia a si locatia b.
     *
     * @author Teo
     * @param name numele drumului
     * @param type tipul drumului
     * @param length lungimea drumului
     * @param a obiect de tip Location semnificand un capat al drumului
     * @param b obiect de tip Location semnificand celalalt capat al drumului
     * @throws IllegalArgumentException in caz ca lungimea < distanta Euclidiana
     * @return un obiect de tip Road sau arunca o exceptie
     * @see RoadType,Location
     */
    public Road(String name, RoadType type, double length, Location a, Location b ) {
        this.name = name;
        this.type = type;
        this.plecare=a.name;
        this.destinatie=b.name;
        double distEuclid=a.getDistanceTo(b);
        if(length<distEuclid)
            throw new IllegalArgumentException("Lungimea " + length +" e mai mica decat distanta Euclidiana (" + distEuclid +") intre cele 2 locatii!");
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

    /**
     * Doua obiecte de tip Road sunt egale daca au acelasi nume
     *
     * @author Teo
     * @param o obiectul cu care se compara
     * @return true daca cele 2 obiecte au acelasi nume, false altfel
     */
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
