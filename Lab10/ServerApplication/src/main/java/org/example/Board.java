package org.example;
public class Board {
    private char[][] board;
    private int numRows;
    private int numCols;
public Board(){};

    public char[][] getBoard() {
        return board;
    }

    public Board(int numRows, int numCols) {
        this.numRows = numRows;
        this.numCols = numCols;
        this.board = new char[numRows][numCols];
    }
    public char[][] initialize(){
        for(int i = 0; i < numRows; i++)
            for(int j = 0; j < numCols; j++){
                board[i][j]='~';
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
        // Check rows
        for (int row = 0; row < numRows; row++) {
            int count = 0;
            for (int col = 0; col < numCols; col++) {
                if (board[row][col] == symbol) {
                    count++;
                    if (count == 5) {
                        return true;
                    }
                } else {
                    count = 0;
                }
            }
        }

        // Check columns
        for (int col = 0; col < numCols; col++) {
            int count = 0;
            for (int row = 0; row < numRows; row++) {
                if (board[row][col] == symbol) {
                    count++;
                    if (count == 5) {
                        return true;
                    }
                } else {
                    count = 0;
                }
            }
        }

        // Check diagonals
        for (int row = 0; row < numRows - 4; row++) {
            for (int col = 0; col < numCols - 4; col++) {
                if (board[row][col] == symbol &&
                        board[row+1][col+1] == symbol &&
                        board[row+2][col+2] == symbol &&
                        board[row+3][col+3] == symbol &&
                        board[row+4][col+4] == symbol) {
                    return true;
                }
            }
        }

        // Check anti-diagonals
        for (int row = 0; row < numRows - 4; row++) {
            for (int col = numCols - 1; col >= 4; col--) {
                if (board[row][col] == symbol &&
                        board[row+1][col-1] == symbol &&
                        board[row+2][col-2] == symbol &&
                        board[row+3][col-3] == symbol &&
                        board[row+4][col-4] == symbol) {
                    return true;
                }
            }
        }

        return false;
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
