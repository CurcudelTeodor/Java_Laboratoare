package org.example;

import java.util.LinkedList;

import static java.lang.Thread.sleep;

class Robot implements Runnable {
    private String name;
    private ExplorationMap map;
    private LinkedList<Integer> tokens;
    private int i, j;

    public Robot(String name, ExplorationMap map, LinkedList<Integer> tokens) {
        this.name = name;
        this.map = map;
        this.tokens = tokens;
        i = (int) (Math.random() * map.getN());
        j = (int) (Math.random() * map.getN());
    }

    public void run() {
        while (!map.totalExplorata()) {
            synchronized (map) {
                while (map.isVisited(i, j)) {
                    try {
                        sleep(2000);
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
                System.out.println(name + " celula vizitata (" + i + ", " + j + ") si am extras token-ul: " + token);
                map.notifyAll();
            }
        }
        System.out.println("Toata harta a fost explorata de robotei!");
        System.exit(0);
    }
}
