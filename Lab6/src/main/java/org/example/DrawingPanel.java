package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static java.lang.Thread.sleep;

public class DrawingPanel extends JPanel implements Serializable {
    public Graphics panelGraphics;

    private final int W = 800;
    private final int H = 600;
    private final MainFrame frame;

    boolean isRedPlayerTurn = true;

    private List<Point> vertices = new LinkedList<>();
    private List<Line> lines = new LinkedList<>();
    private List<Line> selectedLinesRed = new ArrayList<>();
    private List<Line> selectedLinesBlue = new ArrayList<>();

    private List<Triangle> triangles = new ArrayList<>();


    public DrawingPanel(MainFrame frame) {
        this.frame = frame;
        setPreferredSize(new Dimension(W, H));
        setBorder(BorderFactory.createEtchedBorder());

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                for (Line line : lines) {
                    if (line.contains(e.getPoint())) {
                        if (selectedLinesRed.contains(line) && isRedPlayerTurn) {
                            selectedLinesRed.remove(line);
                        } else if (!selectedLinesRed.contains(line) && !selectedLinesBlue.contains(line) && isRedPlayerTurn){
                            selectedLinesRed.add(line);
                            System.out.println("Linia cu coordonatele punctului 1: " + line.getStart().x +" "+ line.getStart().y + " si coordonatele punctului 2: " + line.getEnd().x + " "+ line.getEnd().y);


                        }
                        else if(selectedLinesBlue.contains(line) && !isRedPlayerTurn){
                            selectedLinesBlue.remove(line);
                        }
                        else if(!selectedLinesBlue.contains(line) &&!selectedLinesRed.contains(line) && !isRedPlayerTurn){
                            selectedLinesBlue.add(line);
                        }
                        isRedPlayerTurn = !isRedPlayerTurn;
                        repaint();
                        break;
                    }
                }
            }
        });
    }

    public void creeazaPuncteSiLinii(Graphics g) {
        vertices.clear();
        lines.clear();
        selectedLinesRed.clear();
        selectedLinesBlue.clear();
        triangles.clear();

        int numVertices = (int) frame.configPanel.dotsSpinner.getValue();
        double edgeProbability = (double) frame.configPanel.linesComboBox.getSelectedItem();

        //Graphics g = super.getGraphics();
        g.clearRect(0,0,W,H);


        // Create vertices
        double angleIncrement = 2 * Math.PI / numVertices;
        double radius = H / 2 - 10;
        //vertices = new ArrayList<>();
        int centerX = W / 2;
        int centerY = H / 2;
        for (int i = 0; i < numVertices; i++) {
            double angle = i * angleIncrement;
            int x = (int) Math.round(centerX + radius * Math.cos(angle));
            int y = (int) Math.round(centerY + radius * Math.sin(angle));
            vertices.add(new Point(x, y));
        }

        // Create lines
        //lines = new ArrayList<>();
        for (int i = 0; i < numVertices - 1; i++) {
            for (int j = i + 1; j < numVertices; j++) {
                if (Math.random() < edgeProbability) {
                    Point p1 = vertices.get(i);
                    Point p2 = vertices.get(j);
                    lines.add(new Line(p1, p2, Color.GRAY));
                }
            }
        }

        //genereazaTriunghiurile();
        repaint();
    }



    protected void paintComponent(Graphics g) {
        this.panelGraphics = g;
        super.paintComponent(g);
        g.clearRect(0, 0, W, H);

        // Draw lines
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(5));
        for (Line line : lines) {
            g2d.setColor(line.getColor());
            g2d.drawLine(line.getStart().x, line.getStart().y, line.getEnd().x, line.getEnd().y);
        }

        // Draw selected lines
        for (Line line : selectedLinesRed) {
            g2d.setColor(Color.RED);
            g2d.drawLine(line.getStart().x, line.getStart().y, line.getEnd().x, line.getEnd().y);
        }

        for (Line line : selectedLinesBlue) {
            g2d.setColor(Color.BLUE);
            g2d.drawLine(line.getStart().x, line.getStart().y, line.getEnd().x, line.getEnd().y);
        }
       // verificaCastigator();
        checkRedWin();
        checkBlueWin();



    }

    private void checkRedWin(){
        boolean isWinner = false;
        for(Line line1 : selectedLinesRed){
           for(Line line2 : selectedLinesRed){
               for(Line line3 : selectedLinesRed){
                   if(line1.existsConnection(line2) && line2.existsConnection(line3) && line3.existsConnection(line1) && line1!=line2 && line2!=line3 && line3!=line1 )
                   {
                       System.out.println("Linia cu coordonatele liniei 1: " + line1.getStart().x +" "+ line1.getStart().y + " si coordonatele punctului 2: " + line1.getEnd().x + " "+ line1.getEnd().y);
                       System.out.println("Linia cu coordonatele liniei 2: " + line2.getStart().x +" "+ line2.getStart().y + " si coordonatele punctului 2: " + line2.getEnd().x + " "+ line2.getEnd().y);
                       System.out.println("Linia cu coordonatele liniei 3: " + line3.getStart().x +" "+ line3.getStart().y + " si coordonatele punctului 2: " + line3.getEnd().x + " "+ line3.getEnd().y);

                       Graphics2D g2d = (Graphics2D) panelGraphics;
                       g2d.setStroke(new BasicStroke(5));
                       g2d.setColor(Color.RED);
                       g2d.drawLine(line3.getStart().x, line3.getStart().y, line1.getEnd().x, line1.getEnd().y);
                       isWinner = true;
                       break;
                   }

               }
           }
        }
        if(isWinner == true){
            JOptionPane.showMessageDialog(this, "Jucatorul "+ (isRedPlayerTurn ? "Albastru" : "Rosu" ) + " a castigat!");
            JOptionPane.getRootFrame().dispose(); // close the message dialog box

        }
    }

    private void checkBlueWin(){
        boolean isWinner = false;
        for(Line line1 : selectedLinesBlue){
            for(Line line2 : selectedLinesBlue){
                for(Line line3 : selectedLinesBlue){
                    if(line1.existsConnection(line2) && line2.existsConnection(line3) && line3.existsConnection(line1) && line1!=line2 && line2!=line3 && line3!=line1 )
                    {
                        System.out.println("Linia cu coordonatele liniei 1: " + line1.getStart().x +" "+ line1.getStart().y + " si coordonatele punctului 2: " + line1.getEnd().x + " "+ line1.getEnd().y);
                        System.out.println("Linia cu coordonatele liniei 2: " + line2.getStart().x +" "+ line2.getStart().y + " si coordonatele punctului 2: " + line2.getEnd().x + " "+ line2.getEnd().y);
                        System.out.println("Linia cu coordonatele liniei 3: " + line3.getStart().x +" "+ line3.getStart().y + " si coordonatele punctului 2: " + line3.getEnd().x + " "+ line3.getEnd().y);

                        Graphics2D g2d = (Graphics2D) panelGraphics;
                        g2d.setStroke(new BasicStroke(5));
                        g2d.setColor(Color.BLUE);
                        g2d.drawLine(line3.getStart().x, line3.getStart().y, line1.getEnd().x, line1.getEnd().y);
                        isWinner = true;
                        break;
                    }

                }
            }
        }
        if(isWinner == true){
            JOptionPane.showMessageDialog(this, "Jucatorul "+ (isRedPlayerTurn ? "Albastru" : "Rosu" ) + " a castigat!");
            JOptionPane.getRootFrame().dispose();

        }

    }


    private void genereazaTriunghiurile(){
        triangles.clear();
        for(int i = 0; i < vertices.size() - 2; i++){
            for(int j = i + 1; j < vertices.size() - 1; j++){
                for(int k = j + 1; k < vertices.size(); k++){
                    triangles.add(new Triangle(vertices.get(i),vertices.get(j), vertices.get(k)));
                }
            }
        }
    }


    private void verificaCastigator(){
        for(Triangle triunghi : triangles){
            System.out.println("Triunghiul: "+ triunghi.varf1.x + "-" +triunghi.varf1.y + "  "+ triunghi.varf2.x +"-"+ triunghi.varf2.y+  "  "  + triunghi.varf3.x + "-"+triunghi.varf3.y);
            Color color = getColor(triunghi.getEdges().get(0));
            System.out.println("CULOARE " + color);
            boolean sameColor = true;
            for(Line linie : triunghi.getEdges()){
                System.out.println("\tLinia: "+ linie.getStart().x + "  " + linie.getEnd().y);
                System.out.println("Culoare linie : " + getColor(linie));
                System.out.println(!getColor(linie).equals(color));
                System.out.println(Color.GRAY);
                if(getColor(linie).equals(color) && color==Color.GRAY && getColor(linie)==Color.GRAY){
                    sameColor = false;
                    //System.out.println("Inainte de break;");
                    break;
                }

            }
            if(sameColor == true){
                System.out.println("YESSSSSSSSSS");
                JOptionPane.showMessageDialog(this, "Jucatorul "+ (isRedPlayerTurn ? "Rosu" : "Albastru" ) + " a castigat!");
                break;
            }
        }

    }


    private Color getColor(Line line) {
        if (selectedLinesRed.contains(line)) {
            return Color.RED;
        } else if (selectedLinesBlue.contains(line)) {
            return Color.BLUE;
        } else {
            return Color.GRAY;
        }
    }
}