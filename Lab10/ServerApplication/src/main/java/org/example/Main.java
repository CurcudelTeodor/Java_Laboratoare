package org.example;


public class Main {
    public static void main(String[] args) {
        GameServer server1 = new GameServer(5001);
        server1.start();
    }
}