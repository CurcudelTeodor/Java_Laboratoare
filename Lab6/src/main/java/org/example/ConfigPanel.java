package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Vector;

public class ConfigPanel extends JPanel {
    final MainFrame frame;
    JLabel dotsLabel, linesLabel;
    JSpinner dotsSpinner;
    JComboBox linesComboBox;
    JButton createNewGame = new JButton("Create new game");
    public ConfigPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }
    private void init() {

        //create the label and the spinner
        dotsLabel = new JLabel("Number of dots:");
        dotsSpinner = new JSpinner(new SpinnerNumberModel(6, 3, 100, 1));

        linesLabel = new JLabel("Line probability:");
        Vector<Double> options = new Vector<>(Arrays.asList(0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1.0));
        //Vector vec1 = new Vector (10);
        //vec1.add(0.1);
        //vec1.add(0.2);
        linesComboBox = new JComboBox(options);

        //create the rest of the components
        //this.setLayout (new GridLayout(3,2));
        add(dotsLabel); //JPanel uses FlowLayout by default
        add(dotsSpinner);
        add(linesLabel);
        add(linesComboBox);
        add(createNewGame);

        createNewGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // get the number of vertices and edge probability from the config panel
                int numVertices = (int) dotsSpinner.getValue();
                double edgeProbability = (double) linesComboBox.getSelectedItem();

                DrawingPanel drawingPanel = frame.canvas;
                Graphics g = frame.canvas.getGraphics();
                drawingPanel.creeazaPuncteSiLinii(g);

//
//                g.setColor(Color.ORANGE);
//                g.fillRect(0,0,800,600);
            }
        });

    }
}
