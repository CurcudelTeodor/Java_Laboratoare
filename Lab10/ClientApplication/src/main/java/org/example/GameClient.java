package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class GameClient {
    public GameClient(){

        int port = 5001;
        String host = "localhost";

        try (Socket socket = new Socket(host, port);
             PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             Scanner scanner = new Scanner(System.in)) {

            while (true) {

                String command = scanner.nextLine();

                if (command.equals("stop")) {
                    output.println("stop");
                    String response = input.readLine();
                    System.out.println(response);
                    break;
                }
                else if (command.equals("exit")) {
                    output.println("exit");
                    socket.close();
                    break;
                }
                else {
                    output.println(command);
                    String response = input.readLine();
                    System.out.println(response);
                }
            }

        } catch (IOException e) {
            System.err.println("Error connecting to server: " + e.getMessage());
        }
    }
}
