package org.example;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static java.lang.Thread.sleep;

class Robot implements Runnable {
    private String name;
    private ExplorationMap map;
    private LinkedList<Integer> tokens;
    private int tokensCollected;
    public final int[] position;
    private int[] nextPosition;
    public boolean running;
    public boolean paused;

    public Robot(String name, ExplorationMap map, ExplorationMap spawnMap, LinkedList<Integer> tokens) {
        this.name = name;
        this.map = map;
        this.tokens = tokens;
        this.running = true;
        this.paused = false;
        this.position = new int[2];
        this.nextPosition = new int[2];

        //de unde incepe explorarea (unde e robotul initial)
        do {
            this.position[0] = (int) (Math.random() * map.getN());
            this.position[1] = (int) (Math.random() * map.getN());
        } while (spawnMap.isVisited(position[0], position[1]));

        spawnMap.getMap()[position[0]][position[1]] = 99;

        this.tokensCollected = 0;
        System.out.println("Robot cu numele " + this.name + " coordonatele: i=" + position[0] + " j=" + position[1]);
    }

    public void run() {

        while (running && map.nrVisited < map.getN() * map.getN()) {
            int[] nextPosition = getNextPosition();

            if (nextPosition != null){
                synchronized(map) {
                    int timeout = 100; //milisecunde
                    long startTime = System.currentTimeMillis();
                    while (map.isVisited(position[0], position[1])) {
                        try {
                            sleep(3000);
                            map.wait(timeout);
                            //System.out.println(System.currentTimeMillis() - startTime);
                            if (System.currentTimeMillis() - startTime > timeout) {
                                break;
                            }
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }

                    position[0] = nextPosition[0];
                    position[1] = nextPosition[1];

                    if (!map.isVisited(position[0], position[1])) {
                        if (!tokens.isEmpty()) {
                            int token = tokens.removeFirst();
                            map.visitCell(position[0], position[1], token);
                            System.out.println("NRVISITED: " + map.nrVisited + "--" + name + " celula vizitata (" + position[0] + ", " + position[1] + ") si am extras token-ul: " + token);
                            this.tokensCollected++;
                        } else {
                            map.visitCell(position[0], position[1], 0);
                            System.out.println(name + " celula vizitata (" + position[0] + ", " + position[1] + ")");
                        }
                        map.notifyAll();

                    }
                }

            } else {
                running = false;
            }
        }

        System.out.println(name+" a terminat explorarea");
        System.out.println(name + " a colectat " + tokensCollected + " token-uri.");
    }

    private int[] getNextPosition() {
        //Coada care stocheaza pozitiile urmatoare de explorat (cele 4 directii - in cel mai bun caz, adica daca celulele nu au fost deja vizitate)
        Queue<int[]> queue = new LinkedList<>();

        //Adaugam positia curenta in coada
        queue.offer(position);

        //visited = matrice pentru a tine cont cine e vizitat si cine nu
        boolean[][] visited = new boolean[map.getN()][map.getN()];
        visited[position[0]][position[1]] = true;

        //BFS
        while (!queue.isEmpty()) {
            //Aflam in cate directii ne putem duce
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] currPos = queue.poll();
                //Exploram toate celule invecinate
                for (int[] neighbor : getNeighbors(currPos)) {
                    if (!visited[neighbor[0]][neighbor[1]]) {
                        //Daca celula invecinata nu a fost vizitata, o adaugam la coada
                        queue.offer(neighbor);
                        visited[neighbor[0]][neighbor[1]] = true;
                        //Daca celula nu a fost explorata in map, returnam pozitia respectiva (acolo vom merge)
                        if (map.getMap()[neighbor[0]][neighbor[1]] == 0) {
                            return neighbor;
                        }
                    }
                }
            }
        }

        //Daca toate celulele au fost explorate, atunci returnam null
        return null;
    }

    private List<int[]> getNeighbors(int[] position) {
        List<int[]> neighbors = new ArrayList<>();
        int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}}; //sus, dreapta, jos, stanga in ordine
        for (int[] direction : directions) {
            int[] neighbor = new int[2];
            neighbor[0] = position[0] + direction[0];
            neighbor[1] = position[1] + direction[1];
            if (isValidPosition(neighbor)) {
                neighbors.add(neighbor);
            }
        }
        return neighbors;
    }



    public synchronized void pauseRobot(long time) {
        paused = true;
        if (time > 0) {
            try {
                wait(time);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public synchronized void startRobot() {
        paused = false;
        notifyAll();
    }

    public synchronized void stopRobot(){
        running = false;
    }

    private boolean isValidPosition(int[] position) {
        int numRows = map.getN();
        int numCols = map.getN();

        return position[0] >= 0 && position[0] < numRows &&
                position[1] >= 0 && position[1] < numCols;
    }

    public int getTokensCollected() {
        return tokensCollected;
    }


}
