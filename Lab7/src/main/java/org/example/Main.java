package org.example;

import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        int n = 3; //n mititel ca sa incapa output-ul
        LinkedList<Integer> tokens = new LinkedList<>();
        //0 = nevizitat in map -> incepem de la 1 aici
        for (int i = 1; i <= n * n * n; i++) {
            tokens.add(i);
        }
        ExplorationMap map = new ExplorationMap(n);
        Thread[] robots = new Thread[6];
        for (int i = 0; i < robots.length; i++) {
            robots[i] = new Thread(new Robot("Robot " + i, map, tokens));
            robots[i].start();
        }
        for (int i = 0; i < robots.length; i++) {
            try {
                robots[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
