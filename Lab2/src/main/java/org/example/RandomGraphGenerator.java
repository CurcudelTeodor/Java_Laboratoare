package org.example;

import java.util.*;

public class RandomGraphGenerator {
    public ArrayList<Location> locations;
    public ArrayList<Road> roads;

    public Graph graphBaza;
    public RandomGraphGenerator(int numLocations, int numRoads) {
        this.locations = generateLocations(numLocations);
        this.roads = generateRoads(locations, numRoads);


        //facem un graf cu locatiile si drumurile generate
       graphBaza = new Graph(locations, roads);

        //afisam lista de adiacenta pentru a ne convinge ca s-a construit un graf
        graphBaza.printAdjList(graphBaza.adjList);
    }

    //generam locatii random
    public static ArrayList<Location> generateLocations(int numLocations) {
        ArrayList<Location> locations = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < numLocations; i++) {
            String name = "Location " + (i + 1);
            int x = random.nextInt()  / 1000000;
            int y = random.nextInt() / 1000000;
            Location location = new City(name, x, y);
            locations.add(location);
        }

        return locations;
    }

    //generam drumuri random conectand locatiile
    public static ArrayList<Road> generateRoads(ArrayList<Location> locations, int numRoads) {
        ArrayList<Road> roads = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < numRoads; i++) {
            Location source = locations.get(random.nextInt(locations.size()));
            Location destination = locations.get(random.nextInt(locations.size()));
            double distance = random.nextDouble() * 100000000;
            Road road = new Road(source.getName(),RoadType.NATIONAL ,distance,source,destination);
            roads.add(road);
        }

        return roads;
    }

    public List<Location> getNeighbors(Location location) {
        List<Location> neighbors = new ArrayList<>();
        ArrayList<String> adjacentLocations = graphBaza.adjList.get(location.getName());

        for (String adjacentLocationName : adjacentLocations) {
            Location adjacentLocation = getLocationByName(adjacentLocationName);
            if (adjacentLocation != null) {
                neighbors.add(adjacentLocation);
            }
        }

        return neighbors;
    }

    //metoda ajutatoare pentru a lua o locatie dupa nume
    public Location getLocationByName(String name) {
        for (Location location : locations) {
            if (location.getName().equals(name)) {
                return location;
            }
        }
        return null;
    }

}

