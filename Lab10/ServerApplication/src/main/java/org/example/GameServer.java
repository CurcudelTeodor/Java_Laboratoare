package org.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;



public class GameServer {
    private final int port;
    private volatile boolean isRunning;
    private ServerSocket serverSocket;

    public GameServer(int port) {
        this.port = port;
        this.isRunning = false;
    }

    public void start() {
        try {
            serverSocket = new ServerSocket(port);
            isRunning = true;
            System.out.printf("Server started on port %d%n", port);

            while (isRunning) {
                Socket clientSocket = serverSocket.accept();
                System.out.printf("Client connected from %s%n", clientSocket.getInetAddress().getHostName());
                new ClientThread(clientSocket,this).start();
            }
        } catch (IOException e) {
            System.err.printf("Error starting server: %s%n", e.getMessage());
        } finally {
            stop();
        }
    }

    public void stop() {
        isRunning = false;
        try {
            if (serverSocket != null) {
                serverSocket.close();
            }
            System.out.println("Server stopped");
        } catch (IOException e) {
            System.err.printf("Error stopping server: %s%n", e.getMessage());
        }
    }
}
