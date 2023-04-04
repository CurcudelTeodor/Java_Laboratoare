package org.example;

public class Point {
    public int x,y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }


    public double distance(Point destination){
        return Math.sqrt((destination.x-this.x)*(destination.x-this.x) + (destination.y-this.y)*(destination.y-this.y));
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean hasSameCoordinates(Point b){
        if(this.x == b.x && this.y == b.y){
            return true;
        }
        return false;
    }
}
