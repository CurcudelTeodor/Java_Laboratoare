package org.example;
public class Board {
    private char[][] board;
    private int numRows;
    private int numCols;
public Board(){};
    public Board(int numRows, int numCols) {
        this.numRows = numRows;
        this.numCols = numCols;
        this.board = new char[numRows][numCols];
    }
    public char[][] initialize(){
        for(int i = 0; i < numRows; i++)
            for(int j = 0; j < numCols; j++){
                board[i][j]='*';
            }
        return board;
    }

    public boolean isValidMove(int row, int col) {
        return row >= 0 && row < numRows && col >= 0 && col < numCols && board[row][col] == 0;
    }

    public void makeMove(int row, int col, char symbol) {
        board[row][col] = symbol;
    }

    public boolean isFull() {
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                if (board[row][col] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isWinner(char symbol) {
        // check rows
        for (int row = 0; row < numRows; row++) {
            boolean won = true;
            for (int col = 0; col < numCols; col++) {
                if (board[row][col] != symbol) {
                    won = false;
                    break;
                }
            }
            if (won) {
                return true;
            }
        }

        // check columns
        for (int col = 0; col < numCols; col++) {
            boolean won = true;
            for (int row = 0; row < numRows; row++) {
                if (board[row][col] != symbol) {
                    won = false;
                    break;
                }
            }
            if (won) {
                return true;
            }
        }

        // check diagonal
        boolean won = true;
        for (int i = 0; i < numRows; i++) {
            if (board[i][i] != symbol) {
                won = false;
                break;
            }
        }
        if (won) {
            return true;
        }

        // check anti-diagonal
        won = true;
        for (int i = 0; i < numRows; i++) {
            if (board[i][numCols - 1 - i] != symbol) {
                won = false;
                break;
            }
        }
        return won;
    }

    public void printBoard() {
        System.out.println();
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                if (board[row][col] == 0) {
                    System.out.print("-");
                } else {
                    System.out.print(board[row][col]);
                }
                System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
