package org.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class GameServer {
    private final int port;
    private volatile boolean isRunning;
    private ServerSocket serverSocket;
    private List<Game> games;
    private int gameIdCounter;
    private List<Game> activeGames;
    private Game gameToPlay;
    private Board board = new Board();

    public List<Game> getActiveGames() {
        return activeGames;
    }

    public GameServer(int port) {
        this.port = port;
        this.isRunning = false;
        this.games = new ArrayList<>();
        this.gameIdCounter = 1;
        this.activeGames = new ArrayList<>();
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

    public int createGame() {
        int gameId = gameIdCounter++;
        Game game = new Game(gameId,3);
        games.add(game);
        return gameId;
    }

    public boolean joinGame(int gameId, List<Game> activeGames, long name) {
        System.out.println("NUMAR JUCATORI: " );
        for (Game game : activeGames){
            if(game.getId() == gameId && game.addPlayer(name) == true){

                this.gameToPlay = game;
                this.board = game.getBoard();
                this.board.initialize();
                //game.addPlayer(name);
                return true;
            }
        }
        return false;
    }

    public Game findGame(int gameId) {
        for (Game game : activeGames) {
            if (game.getId() == gameId) {
                return game;
            }
        }
        return null; //Nu am gasit jocul
    }

    public boolean submitMove(int row, int col, char symbol, Timer timer, TimerTask timeoutTask){
        System.out.println("row, col and symbol = " +row +" "+ col +" "+symbol);
        if(this.board.getBoard()[row][col]!='x' && this.board.getBoard()[row][col]!='o'){

            //dam cancel la timer
            if (timer != null) {
                timer.cancel();
                timer = null;
            }
            if (timeoutTask != null) {
                timeoutTask.cancel();
                timeoutTask = null;
            }

            this.board.getBoard()[row][col] = symbol;
            board.printBoard();

            if (board.isWinner('x')){
                System.out.println("Jucatorul cu x a castigat!!!");
                return true;
            }
            else if (board.isWinner('o')){
                System.out.println("Jucatorul cu x a castigat!!!");
                return true;
            }
            return true;
        }
        return false;
    }

    public void handleTimeout(long playerId) {

        System.out.println("S-a scurs timpul pentru jucatorul cu numele " + playerId);

    }
}
