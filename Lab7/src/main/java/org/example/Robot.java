package org.example;

import java.util.LinkedList;

import static java.lang.Thread.sleep;

class Robot implements Runnable {
    private String name;
    private ExplorationMap map;
    private LinkedList<Integer> tokens;
    private int tokensCollected;
    private int i, j;
    private boolean running;
    private boolean paused;

    public Robot(String name, ExplorationMap map, ExplorationMap spawnMap, LinkedList<Integer> tokens) {
        this.name = name;
        this.map = map;
        this.tokens = tokens;

        //de unde incepe explorarea
        do{
            this.i = (int) (Math.random() * map.getN());
            this.j = (int) (Math.random() * map.getN());
        }while (spawnMap.isVisited(i,j));
        spawnMap.getMap()[i][j]=99;

        this.tokensCollected = 0;
        System.out.println("Robot cu numele " + this.name + "coordonatele: i=" +this.i +" j=" + this.j);
    }

    public void run() {
        while (!map.totalExplorata()) {
            synchronized (map) {
                while (map.isVisited(i, j)) {
                    try {
                        //sleep(2000);
                        map.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //generam random o pozitie pana gasim o celula nevizitata
                    do{
                        i = (int) (Math.random() * map.getN());
                        j = (int) (Math.random() * map.getN());
                    }while (map.isVisited(i,j)==true);

                }
                int token = tokens.removeFirst();
                map.visitCell(i, j, token);
                this.tokensCollected++;
                System.out.println(name + " celula vizitata (" + i + ", " + j + ") si am extras token-ul: " + token);
                map.notifyAll();
            }
        }
        System.out.println(name+" a terminat explorarea");
        System.out.println(name + " a colectat " + tokensCollected + " token-uri.");

        map.printMap();
        System.out.println(map.getNrVisited());
        System.exit(0);
    }

    public int getTokensCollected() {
        return tokensCollected;
    }
}
