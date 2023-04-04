package org.example;

class ExplorationMap {
    private int[][] map;
    private int n;

    public int getN() {
        return n;
    }

    private int nrVisited;
    public ExplorationMap(int n) {
        this.n = n;
        this.map = new int[n][n];
        this.nrVisited = 0;
    }
    public synchronized boolean isVisited(int i, int j) {
        if(map[i][j] == 0)
            return false;
        return true;

    }
    public synchronized void visitCell(int i, int j, int token) {
        map[i][j] = token;
        nrVisited++;
    }
    public synchronized boolean totalExplorata() {
        return nrVisited == n * n;
    }
}