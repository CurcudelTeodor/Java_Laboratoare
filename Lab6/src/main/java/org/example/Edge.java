package org.example;

public class Edge {
    private final Point vertex1;
    private final Point vertex2;
    private boolean colored;

    public Edge(Point vertex1, Point vertex2) {
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
        colored = false;
    }

    public Point getVertex1() {
        return vertex1;
    }

    public Point getVertex2() {
        return vertex2;
    }

    public boolean isColored() {
        return colored;
    }

    public void setColored(boolean colored) {
        this.colored = colored;
    }
}