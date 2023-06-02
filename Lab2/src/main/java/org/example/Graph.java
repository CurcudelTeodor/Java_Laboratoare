package org.example;

import java.util.*;

public class Graph {
    public Map<String, ArrayList<String>> adjList;
    public ArrayList<Location> locations;
    public ArrayList<Road> roads;
    /**
     * Construieste un graf cu locatii si drumuri sub forma de HashMap (cheie, valoare).
     * Cheile sunt locatiile, iar valorile sunt locatiile incidente cu cheia, adica
     * exista drum de la cheie.
     * <p>
     * Pentru  fiecare locatie loc din lista location data ca parametru, initializam o lista
     * de String-uri desemnand locul unde se vor pune locatiile adiacente cu loc.
     * <p>
     * Pentru fiecare element de tip Road din lista data ca parametru roads,
     * stabilim cele 2 capete ale drumului. Localizam unde trebuie sa adaugam
     * capatul destinatie cu adjList.get(plecare) (de fapt, gasim cheia plecare)
     * si apoi adaugam destinatie la lista lui plecare. Similar pentru celalalt
     * capat.
     *
     * @param locations lista de obiecte de tip Location pentru care se va construi graful
     *                  (reprezentand nodurile)
     * @param roads     lista de obiecte de tip Road pentru care se va construi graful
     *                  (reprezentand muchii: avem drum de la A la B -> vom avea o muchie in graf
     *                  de la A la B)
     * @author Teo
     */
    public Graph(ArrayList<Location> locations, ArrayList<Road> roads) {
        this.locations = locations;
        this.roads = roads;
        //initializam lista de adicenta
        adjList = new HashMap<String, ArrayList<String>>();
        for (Location loc : locations) {
            adjList.put(loc.getName(), new ArrayList<String>());
        }

        //construim efectiv lista
        for (Road element : roads) {
            String plecare = element.plecare;
            String destinatie = element.destinatie;

            ArrayList<String> adjList1 = adjList.get(plecare);
            adjList1.add(destinatie);

            ArrayList<String> adjList2 = adjList.get(destinatie);
            adjList2.add(plecare);
        }
    }

    /**
     * Afiseaza lista de adiacenta.
     * Pentru fiecare cheie (nume de locatie), parcurgem lista ei
     * si afisam locatiile cu care e conectata.
     *
     * @param adjList lista de adiacenta prin care e reprezentat graful
     * @return afiseaza pe ecran lista de adiacenta
     * @author Teo
     */
    public void printAdjList(Map<String, ArrayList<String>> adjList) {
        for (String loc : adjList.keySet()) {
            System.out.print(loc + " e conectata cu: ");
            ArrayList<String> adjLocs = adjList.get(loc);
            for (String loc2 : adjLocs) {
                System.out.print(loc2 + ", ");
            }
            System.out.println();
        }
    }

    //verificam daca numele unei locatii se gaseste in lista de locatii
    public boolean existsLocationByName(ArrayList<Location> locations, String numeLocatie) {
        for (Location element : locations) {
            if (element.getName().equals(numeLocatie))
                return true;
        }
        return false;
    }

    /**
     * Decide daca putem ajunge dintr-o locatie in alta cu drumurile date.
     * <p>
     * Mai intai verificam daca ambele nume de locatie exista. Daca macar un
     * nume nu exista, aruncam o exceptie.
     * <p>
     * Declaram un HashSet de noduri vizitate si adaugam primul nod, plecare ca
     * fiind vizitat. Declaram o coada pentru a face parcurgerea pe latime (BFS)
     * si adaugam ca nod initial nodul plecare.
     * <p>
     * Cat timp coada nu e vida, adica cat timp mai avem noduri, verificam daca
     * locatia din capul cozii este sosire (daca am ajuns la locatia dorita).
     * Daca da, returnam true. Daca nu am returnat true, parcurgem lista de
     * vecini a nodului curent, iar daca vreun vecin nu a fost vizitat, il vizitam
     * (il adaugam in HashSet-ul de noduri vizitate) si il adaugam in coada.
     * <p>
     * Daca coada a ramas vida si nu am returat true, inseamna ca nu am putut ajunge
     * la locatia dorita, deci returnam false.
     *
     * @param locations lista de obiecte de tip Location pentru care a fost construit graful
     * @param roads     lista de obiecte de tip Road pentru care a fost contruit graful
     * @param plecare   numele obiectului de tip Location de unde plecam
     * @param sosire    numele obiectului de tip Location unde vrem sa ajungem
     * @return true daca putem ajunge din obiectul Location cu numele plecare in obiectul Location cu numele sosire, false altfel
     * @throws IllegalArgumentException daca macar un nume nu exista
     * @author Teo
     */
    //true -> Putem ajunge din locatia cu numele plecare in sosire; false -> altfel
    public boolean canReach(ArrayList<Location> locations, ArrayList<Road> roads, String plecare, String sosire) {
        //verificari
        if (!existsLocationByName(locations, plecare) || !existsLocationByName(locations, sosire)) {
            throw new IllegalArgumentException("Una dintre locatii nu exista!");
        }

        Set<String> vizitati = new HashSet<>();
        vizitati.add(plecare);

        //coada pentru BFS implementata ca o list inlantuita
        LinkedList<String> coada = new LinkedList<>();
        coada.offer(plecare);

        while (coada.isEmpty() == false) {
            //luam numele locatiei din capul cozii
            String locCurenta = coada.poll();
            if (locCurenta.equals(sosire))
                return true;

                //parcurgem lista de vecini a locatiei curente
            else for (String vecin : adjList.get(locCurenta)) {
                //vecinul nu a fost vizitat
                if (vizitati.contains(vecin) == false) {
                    vizitati.add(vecin);
                    coada.offer(vecin);
                }
            }
        }
        return false;

    }

    public List<Location> getNeighbors(Location location) {
        List<Location> neighbors = new ArrayList<>();
        ArrayList<String> adjacentLocations = adjList.get(location.getName());

        for (String adjacentLocationName : adjacentLocations) {
            Location adjacentLocation = getLocationByName(adjacentLocationName);
            if (adjacentLocation != null) {
                neighbors.add(adjacentLocation);
            }
        }

        return neighbors;
    }

    // Helper method to get a location by its name
    private Location getLocationByName(String name) {
        for (Location location : locations) {
            if (location.getName().equals(name)) {
                return location;
            }
        }
        return null;
    }

}
