package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.*;

class ClientThread extends Thread {
    private Socket clientSocket;
    private GameServer gameServer;
    private Game game;
   // public List<Game> activeGames = new ArrayList<>();
    private Timer timer;
    private TimerTask timeoutTask;

    private int BOARDSIZE = 10;

    public ClientThread(Socket clientSocket, GameServer gameServer) {
        this.clientSocket = clientSocket;
        this.gameServer = gameServer;
        //this.game = new Game();
    }

    public void run() {
        try (BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true)) {

            String command;
            while ((command = input.readLine()) !=null){
                if(command.equals("stop")){
                    clientSocket.close();
                    gameServer.stop();
                    output.println("Server stopped");
                    break;
                }

                else if(command.equals("exit")){
                    System.out.println("Client disconnected");
                    clientSocket.close();
                    break;
                }

                else if(command.startsWith("create game ")){
                    System.out.println("AAAAAAAAAA" + this.getId());
                    int gameId = gameServer.createGame();
                    gameServer.getActiveGames().add(new Game(gameId,BOARDSIZE));
                    output.println("Game created with ID: " + gameId);
                    output.flush();
                }

                else if(command.startsWith("join game ")){
                    String[] tokens = command.split(" ");
                    if (tokens.length == 3) {
                        int gameId = Integer.parseInt(tokens[2]);
                        boolean joined = gameServer.joinGame(gameId,gameServer.getActiveGames(),this.getId());
                        if (joined) {
                            startTimer();
                            output.println("Joined game with ID: " + gameId);
                        } else {
                            output.println("Failed to join game with ID: " + gameId);
                        }
                        output.flush();
                    } else {
                        output.println("Invalid command format");
                        output.flush();
                    }
                }


                else if(command.startsWith("place ")) {
                    String[] tokens = command.split(" ");
                    if(tokens.length == 4) {
                        int row = Integer.parseInt(tokens[1]);
                        int col = Integer.parseInt(tokens[2]);
                        char symbol = tokens[3].toCharArray()[0];

                        //trimitem si id0ul thread-ului pentru a putea adauga in bd miscarile si cine le-a facut
                        boolean moveSubmitted = gameServer.submitMove(row, col,symbol,timer,timeoutTask, this.getId());
                        System.out.println("row, col and symbol = " +row +" "+ col +" "+symbol);
                        if(moveSubmitted){
                            output.println("Piece ->" + symbol + " placed successfully at row=" + row + " and column="+col);
                        output.flush();
                        }
                        else{
                            output.println("Failed to submit move row=" + row +" and column= "+ col);
                            output.flush();
                        }
                        output.flush();
//
//                      output.println("Failed to place piece");
//                      output.flush();

                    } else {
                        output.println("Invalid command format");
                        output.flush();
                    }
                }

                else{
                    System.out.println("Server received the command " + command);
                    output.println("Server received the req " + command);
                    output.flush();
                }
            }







        } catch (IOException e) {
            System.err.println("Error handling client request: " + e.getMessage());
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                System.err.println("Error closing client socket: " + e.getMessage());
            }
        }
    }

    public void startTimer() {
        timer = new Timer();
        timeoutTask = new TimerTask() {
            @Override
            public void run() {

                gameServer.handleTimeout(getId());
            }
        };
        timer.schedule(timeoutTask, 1000000000);
    }


}
