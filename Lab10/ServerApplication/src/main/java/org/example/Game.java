package org.example;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private int id;
    private  List<Player> players = new ArrayList<>();
    private  Board board;
    private int currentPlayerIndex;

    public Game() {
    }

    public Game (int id, int boardSize) {
        this.id = id;
        this.board = new Board(boardSize,boardSize);
        this.currentPlayerIndex = 0;
    }

    public int getId() {
        return id;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Board getBoard() {
        return board;
    }

    public int getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }

    public Player getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }

    public boolean addPlayer(long name) {
        if (players.size() < 2) {
            players.add(new Player(name));
            System.out.println("NUMAR JUCATORI=" +players.size() + " IN JOCUL " + id);
            System.out.println("-----------------");
            for (Player jucator : players){
                System.out.println(jucator.getName());
            }
            return true;
        }

        return false; // Maximum number of players reached
    }


}
