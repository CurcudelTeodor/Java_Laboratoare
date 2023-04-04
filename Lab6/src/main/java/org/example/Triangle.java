package org.example;

import java.util.ArrayList;
import java.util.List;

public class Triangle {
    public Point varf1;
    public Point varf2;
    public Point varf3;

    public Triangle(Point varf1, Point varf2, Point varf3) {
        this.varf1 = varf1;
        this.varf2 = varf2;
        this.varf3 = varf3;
    }

    public List<Line> getEdges() {
        List<Line> edges = new ArrayList<>();
        edges.add(new Line(varf1, varf2));
        edges.add(new Line(varf2, varf3));
        edges.add(new Line(varf3, varf1));
        return edges;
    }
}
