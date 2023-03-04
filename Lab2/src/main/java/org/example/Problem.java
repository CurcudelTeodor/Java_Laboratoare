package org.example;

import java.util.ArrayList;
import java.util.Objects;

public class Problem
{
    private ArrayList<Location> locations;
    private ArrayList<Road> roads;

    public Problem() {
        this.locations = new ArrayList<Location>();
        this.roads = new ArrayList<Road>();
    }

    public void addLocation(Location loc)
    {
        if(existsLocation(loc))
            throw new IllegalArgumentException("Locatia "+loc.toString()+" deja exista!");
        else
            locations.add(loc);
    }

    public void addRoad(Road drum, Location a, Location b)
    {
        if(roads.contains(drum))
            throw new IllegalArgumentException("Drumul "+drum.toString()+" deja exista!");
        else
        {
            //verificam daca cele 2 locatii exista
            if(locations.contains(a) && locations.contains(b))
            {
                roads.add(drum);
            }
            else
                throw new IllegalArgumentException("Una dintre cele doua locatii nu exista!");
        }

    }

    public boolean existsLocation(Location loc)
    {
        for(Location element:locations)
        {
            if(loc.equals(element))
                return true;
        }
        return false;
    }

    public Location getLocations(int index) {
        return locations.get(index);
    }

    public String toStringLocations()
    {
        StringBuilder sb=new StringBuilder();
        for(Location element:locations)
        {
            sb.append("location=").append(element).append('\n');
        }
        return sb.toString();
    }

    public String toStringRoads()
    {
        StringBuilder sb=new StringBuilder();
        for(Road element:roads)
        {
            sb.append("road=").append(element).append('\n');
        }
        return sb.toString();
    }

}

