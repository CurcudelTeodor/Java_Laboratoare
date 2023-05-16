package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class ClientThread extends Thread {
    private Socket clientSocket;
    private GameServer gameServer;
    private Game game;
    public List<Game> activeGames = new ArrayList<>();

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
                    String gameID = command.substring(12);
                    System.out.println("Server received the command" + command);
                    output.println("Created game with the id = " + gameID);
                    output.flush();

                    this.game = new Game(gameID,3);
                    activeGames.add(game);
                }

                else if(command.startsWith("join game ")){
                    boolean existaJoc = false;
                    String gameID = command.substring(10);
                    System.out.println("Server received the command" + command);

                    for(Game joc : activeGames){
                        if(Objects.equals(joc.getId(), gameID)){
                            existaJoc = true;
                            output.println("Joined game with the id= " + gameID);
                            break;
                        }
                        else {
                            output.println("Game with the id " + gameID +" doesn't exist");

                        }

                    }


//                    boolean succes =  game.joinGame(gameID);
//                    if(succes){
//                        output.println("Joined game with the id= " + gameID);
//                        output.flush();
//                    }
//                    else{
//                        output.println("Failed to join game with the id= " + gameID);
//                        output.flush();
//                    }
                }


                else if(command.startsWith("place ")) {
                    String[] tokens = command.split(" ");
                    if(tokens.length == 3) {
                        int row = Integer.parseInt(tokens[1]);
                        int col = Integer.parseInt(tokens[2]);

                        //boolean success = board.makeMove(row, col);
                        output.println("Piece placed successfully at row=" + row + " and column="+col);
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
}
