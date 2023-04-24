package org.example;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int n = 5; //n mititel ca sa incapa output-ul
        LinkedList<Integer> tokens = new LinkedList<>();
        //0 = nevizitat in map -> incepem de la 1 aici
        for (int i = 1; i <= n * n * n; i++) {
            tokens.add(i);
        }
        ExplorationMap map = new ExplorationMap(n);
        ExplorationMap spawnMap = new ExplorationMap(n);
        Thread[] robots = new Thread[6];
        ArrayList<Robot> listaRoboti = new ArrayList<>();
        for (int i = 0; i < robots.length; i++) {
            Robot robot = new Robot("Robot " + i, map, spawnMap, tokens);
            robots[i] = new Thread(robot);
            //robots[i] = new Thread(new Robot("Robot " + i, map, spawnMap, tokens));
            listaRoboti.add(robot);
            robots[i].start();
        }

        TimeKeeper timekeeper = new TimeKeeper(60, listaRoboti);
        Thread timekeeperThread = new Thread(timekeeper);
        timekeeperThread.setDaemon(true);
        timekeeperThread.start();

        // Command loop
        boolean exploring = true;
        while (exploring) {
            System.out.println("Enter command:");
            String command = scanner.nextLine();

            switch (command) {
                case "pause all":
                    for (Robot robot : listaRoboti) {
                        robot.pauseRobot(3000);
                    }
                    break;
                case "start all":
                    for (Robot robot : listaRoboti) {
                        robot.startRobot();
                    }
                    break;
                case "pause":
                    System.out.println("Enter robot id:");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Enter pause time (0 for indefinite):");
                    long time = scanner.nextLong();
                    scanner.nextLine();
                    listaRoboti.get(id-1).pauseRobot(time);
                    break;
                case "start":
                    System.out.println("Enter robot id:");
                    int id2 = scanner.nextInt();
                    scanner.nextLine();
                    listaRoboti.get(id2-1).startRobot();
                    break;
                case "exit":
                    exploring = false;
                    break;
                default:
                    System.out.println("Invalid command.");
            }
        }


        //Opresc robotii si thread0ul timekeeper
        for (Robot robot : listaRoboti) {
            robot.stopRobot();
        }
        timekeeper.stopTimekeeper();


        for (int i = 0; i < robots.length; i++) {
            try {
                robots[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        int[] tokenCounts = new int[6];
        for (int i = 0; i < 6; i++) {
            tokenCounts[i] = listaRoboti.get(i).getTokensCollected();
        }
        //Afisez rezultatele
        System.out.println("Token counts:");
        for (int i = 0; i < 6; i++) {
            System.out.println("Robot " + (i) + ": " + tokenCounts[i]);
        }
        map.printMap();
    }
}
