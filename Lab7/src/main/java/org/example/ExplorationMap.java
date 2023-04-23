package org.example;

class ExplorationMap {
    private int[][] map;
    private int n;

    public int getN() {
        return n;
    }

    public int[][] getMap() {
        return map;
    }

    private int nrVisited;

    public int getNrVisited() {
        return nrVisited;
    }

    public ExplorationMap(int n) {
        this.n = n;
        this.map = new int[n][n];
        this.nrVisited = 0;
    }

//    public synchronized boolean isFree(int i, int j){
//        // 0 -> nu a fost vizitat, deci e disponibil
//        if(map[i][j] == 0)
//            return true;
//        return false;
//    }
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
    public void printMap(){
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
    }
}
