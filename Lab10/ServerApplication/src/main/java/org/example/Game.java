package org.example;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private  String id;
    private  List<Player> players;
    private  Board board;
    private int currentPlayerIndex;

    public Game() {
    }

    public Game (String id, int boardSize) {
        this.id = id;
        this.board = new Board(boardSize,boardSize);
        this.currentPlayerIndex = 0;
    }

    public String getId() {
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

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void start() {
    }

}
