package org.example;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;

public class DrawingPanel extends JPanel {

    final MainFrame frame;
    final static int W = 800, H = 600;

    public DrawingPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    private void init() {
        setPreferredSize(new Dimension(W, H));
        setBorder(BorderFactory.createEtchedBorder());
    }

    protected void paintComponent(int numVertices, double edgeProbability) {
        Graphics g = getGraphics();
        g.clearRect(0,0,W,H);

        //int numVertices = (int) frame.configPanel.dotsSpinner.getValue();
        //double edgeProbability = (double) frame.configPanel.linesComboBox.getSelectedItem();

        List<Point> vertices = new LinkedList<>();
        double angleIncrement = 2 * Math.PI / numVertices;
        double radius = H / 2 - 10;

        int centerX = W / 2;
        int centerY = H / 2;
        for (int i = 0; i < numVertices; i++) {
            double angle = i * angleIncrement;
            int x = (int) Math.round(centerX + radius * Math.cos(angle));
            int y = (int) Math.round(centerY + radius * Math.sin(angle));
            vertices.add(new Point(x, y));
        }


        //desenam liniile inainte pentru a acoperi mai apoi capetele cu punctisoare
        g.setColor(Color.GRAY);
        for (int i = 0; i < numVertices - 1; i++) {
            for (int j = i + 1; j < numVertices; j++) {
                if (Math.random() < edgeProbability) {
                    Point p1 = vertices.get(i);
                    Point p2 = vertices.get(j);
                    g.drawLine(p1.x, p1.y, p2.x, p2.y);
                }
            }
        }


        // desenam punctele
        g.setColor(Color.BLACK);
        int dotSize = 10;
        for (Point vertex : vertices) {
            g.fillOval(vertex.x - dotSize / 2, vertex.y - dotSize / 2, dotSize, dotSize);
        }


    }
}
