package org.example;

import java.util.*;

public class RouteFinder {
    private RandomGraphGenerator graph;
    public double totalDistance = 0;

    public RouteFinder(RandomGraphGenerator graph) {
        this.graph = graph;
    }

    public List<Location> findShortestRoute(Location start, Location end) {
        //facem o harta pentru a stoca distanta de la locatia start la fiecare locatie
        Map<Location, Double> distances = new HashMap<>();
        distances.put(start, 0.0);

        //facem o harta pentru a stoca locatia precedenta in drumul cel mai scurt
        Map<Location, Location> previousLocations = new HashMap<>();

        //multime pentru locatiile vizitate
        Set<Location> visited = new HashSet<>();

        //coada pentru procesarea locatiilor
        Queue<Location> queue = new LinkedList<>();
        queue.add(start);

        while (!queue.isEmpty()) {
            Location current = queue.poll();

            //verificam daca am ajuns la sosire
            if (current.equals(end)) {
                break;
            }

            visited.add(current);

            //exploram vecinii locatiei curente
            List<Location> neighbors = graph.getNeighbors(current);
            for (Location neighbor : neighbors) {
                //calculam distanta de la start la vecinul curent
                double distance = distances.get(current) + current.getDistanceTo(neighbor);

                //actualziam distanta si locatia precedenta daca gasim un drum mai scurt
                if (!distances.containsKey(neighbor) || distance < distances.get(neighbor)) {
                    distances.put(neighbor, distance);
                    previousLocations.put(neighbor, current);
                }

                //aduagam vecinul in coada daca nu a fost vizitat
                if (!visited.contains(neighbor)) {
                    queue.add(neighbor);
                }
            }
        }

        //construim cel mai scurt drum pana la sfarsit folosind previousLocations
        List<Location> shortestPath = new ArrayList<>();
        Location current = end;
        while (current != null) {
            shortestPath.add(0, current);
            current = previousLocations.get(current);
        }

        for(Location loc: shortestPath){
            System.out.print(loc.getName()+" -> ");
        }
        System.out.println();
        return shortestPath;
    }


}
