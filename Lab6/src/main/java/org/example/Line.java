package org.example;

import java.awt.*;

public class Line {
    private final Point start;
    private final Point end;
    private Color color;

    public Line(Point start, Point end, Color gray) {
        this.start = start;
        this.end = end;
        this.color = Color.GRAY;
    }

    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    public Point getStart() {
        return start;
    }

    public Point getEnd() {
        return end;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean existsConnection(Line line){
        if (this.getStart().hasSameCoordinates(line.getStart()))
            return true;
        if(this.getStart().hasSameCoordinates(line.getEnd()))
            return true;
        if (this.getEnd().hasSameCoordinates(line.getStart()))
            return true;
        if(this.getEnd().hasSameCoordinates(line.getEnd()))
            return true;
        return false;
    }



    public boolean contains(java.awt.Point point) {
        // Verific daca punctul e la o dist mica fata de linie
        double distance = Math.abs((end.getY() - start.getY()) * point.getX()
                - (end.getX() - start.getX()) * point.getY()
                + end.getX() * start.getY() - end.getY() * start.getX())
                / Math.sqrt(Math.pow(end.getY() - start.getY(), 2) + Math.pow(end.getX() - start.getX(), 2));
        return distance < 10;
    }


}
