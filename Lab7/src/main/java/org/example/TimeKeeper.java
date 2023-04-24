package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class TimeKeeper implements Runnable {
    private int timeLimit;
    private ArrayList<Robot> robots;
    private boolean running;
    private long startTime;
    private Scanner scanner;


    public TimeKeeper(int timeLimit, ArrayList<Robot> robots) {
        this.timeLimit = timeLimit;
        this.robots = robots;
        this.running = true;
        this.startTime = System.currentTimeMillis();
        this.scanner = new Scanner(System.in);
    }

    public void stopTimekeeper() {
        this.running = false;
    }

    @Override
    public void run() {
        int timeElapsed = 0;
        while (timeElapsed < timeLimit && running) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            timeElapsed++;

            // Verificam daca vreun robot a fost oprit
            for (Robot robot : robots) {
                if (robot.running == false) {
                    stopTimekeeper();
                    return;
                }
            }
//            //Afisam timpul secunda cu secunda
//            long elapsedTime = System.currentTimeMillis() - startTime;
//            System.out.println("Elapsed time: " + elapsedTime / 1000 + " seconds");

            if (scanner.hasNextLine()) {
                String input = scanner.nextLine();
                if (input.equals("exit")) {
                    stopTimekeeper();
                    break;
                }
            }
        }

        //Am depasit timpul (timeLimit)
        stopRobots();
        long elapsedTime = System.currentTimeMillis() - startTime;
        System.out.println("Total exploration time: " + elapsedTime/1000 + " seconds");
    }

    private void stopRobots() {
        for (Robot robot : robots) {
            robot.stopRobot();
        }
    }
}
