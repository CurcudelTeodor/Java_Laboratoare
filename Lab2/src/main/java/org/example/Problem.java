package org.example;

import java.util.ArrayList;
import java.util.Objects;

public class Problem
{
    public boolean isValid=true;
    public ArrayList<Location> locations;
    public ArrayList<Road> roads;

    public Problem() {
        this.locations = new ArrayList<Location>();
        this.roads = new ArrayList<Road>();
    }

    /**
     * Adauga o locatie in lista de locatii.
     * Daca o locatie deja exista in lista, arunca o exceptie
     * si setam campul isValid pe false indicand ca instanta problemei
     * nu este valida.
     *
     * @author Teo
     * @param loc obiect de tip Location ce se doreste a fi adaugat
     * @throws IllegalArgumentException daca o locatie deja exista in lista
     */
    public void addLocation(Location loc)
    {
        if(existsLocation(loc))
        {
            this.isValid=false;
            throw new IllegalArgumentException("Locatia "+loc.toString()+" deja exista!");
        }
        else
            locations.add(loc);
    }

    /**
     * Adauga un drum in lista de drumuri.
     * Daca drumul deja exista in lista, arunca o exceptie
     * si setam campul isValid pe false indicand ca instanta problemei
     * nu este valida. Cand drumul nu exista in lista, verifcam daca lista
     * de locatii contine capetele drumului, iar daca nu, aruncam o exceptie
     * si setam campul isValid pe false. Altfel, adaugam drumul in lista.
     *
     * @author Teo
     * @param drum obiect de tip Road ce se doreste a fi adaugat
     * @param a obiect de tip Location semnificand un capat al drumului
     * @param b obiect de tip Location semnificand celalalt capat al drumului
     * @throws IllegalArgumentException daca drumul deja exista in lista
     * @throws IllegalArgumentException daca lista de locatii nu contine unul dintre capetele drumului
     */
    public void addRoad(Road drum, Location a, Location b)
    {
        if(roads.contains(drum))
        {
            this.isValid=false;
            throw new IllegalArgumentException("Drumul "+drum.toString()+" deja exista!");
        }
        else
        {
            //verificam daca cele 2 locatii exista
            if(locations.contains(a) && locations.contains(b))
            {
                roads.add(drum);
            }
            else
            {
                this.isValid=false;
                throw new IllegalArgumentException("Una dintre cele doua locatii nu exista!");
            }
        }
    }

    /**
     * Verifica daca un obiect de tip Location exista deja in lista.
     * Pentru fiecare element de tip Location din lista locations
     * verifica daca obiectul de tip Location loc dat ca parametru
     * este egal cu elementul curent din lista. Daca da, returneaza true,
     * altfel daca dupa ce am parcurs toata lista nu am returnat, atunci
     * returnam false.
     *
     * @author Teo
     * @param loc obiectul de tip Location pentru care dorim sa vedem daca exista deja
     * @return true daca loc exista, false altfel
     */
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

    /**
     * Returneaza toate obiectele de tip Location stocate in lista de locatii
     *
     * @author Teo
     * @return toate obiecte de tip Location sub forma unui String
     */
    public String toStringLocations()
    {
        StringBuilder sb=new StringBuilder();
        for(Location element:locations)
        {
            sb.append("location=").append(element).append('\n');
        }
        return sb.toString();
    }

    /**
     * Returneaza toate obiectele de tip Road stocate in lista de drumuri
     *
     * @author Teo
     * @return toate obiecte de tip Road sub forma unui String
     */
    public String toStringRoads()
    {
        StringBuilder sb=new StringBuilder();
        for(Road element:roads)
        {
            sb.append("road=").append(element).append('\n');
        }
        return sb.toString();
    }


    public boolean isValidInstance()
    {
        //verificam coordonatele
        for(Location element:locations)
        {
            if(element.getX()<-800 || element.getX()>800 || element.getY()<-800 || element.getY()>800)
            {
                System.out.println("In afara limitelor!");
                return false;
                //throw new IllegalArgumentException("Out of bounds!");
            }
        }

        //verificam daca exista 2 locatii egale
        for(int i=0;i<locations.size()-1;i++)
            for(int j=i+1;j<locations.size(); j++)
                if(locations.get(i).getName().equals(locations.get(j).getName()))
                {
                    System.out.println("Doua locatii cu acelasi nume: "+locations.get(i).getName());
                    return false;
                }

        //verificam daca lungimea drumurilor > distanta Euclidiana
        for (Road element:roads)
        {
            //System.out.println(element.name);
            Location capatStanga= getLocationByName(element.plecare);
            Location capatDreapta= getLocationByName(element.destinatie);
            //dist=distanta Euclidiana
            double dist=capatStanga.getDistanceTo(capatDreapta);

            if(dist>element.length)
            {
                System.out.println("Distanta gresita!");
                return false;
            }
        }
        return true;

    }

    public Location getLocationByName(String name)
    {
        for(Location element:locations)
            if(element.getName().equals(name))
                return element;

        return null;
    }

    public void validCoord()
    {
        for(Location element:locations)
        {
            if(element.getX()<-800 || element.getX()>800 || element.getY()<-800 || element.getY()>800)
            {
                this.isValid=false;
                throw new IllegalArgumentException("Out of bounds!");
            }
        }
    }



}

