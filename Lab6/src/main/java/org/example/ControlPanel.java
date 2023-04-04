package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.iharder.Base64;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.*;


public class ControlPanel extends JPanel {
    final MainFrame frame;

    JButton exitBtn = new JButton("Exit");
    JButton loadBtn = new JButton("PrintEncoded");
    JButton saveBtn = new JButton("Save");
    JButton resetBtn = new JButton("Reset");

    //create all buttons (Load, Exit, etc.)

    public ControlPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }
    private void init() {
        //change the default layout manager (just for fun)
        setLayout(new GridLayout(1, 4));
        add(loadBtn);
        add(saveBtn);
        add(resetBtn);
        add(exitBtn);

        //add all buttons ...TODO
        //configure listeners for all buttons
        exitBtn.addActionListener(this::exitGame);
        saveBtn.addActionListener(this::saveBtn);
        resetBtn.addActionListener(this::resetBtn);
        loadBtn.addActionListener(this::loadBtn);
    }

    private void loadBtn(ActionEvent actionEvent){
//        try{
//            ObjectMapper objectMapper = new ObjectMapper();
//            DrawingPanel drawingPanel = frame.canvas;
//            objectMapper.writeValue(new File("e:\\Facultate\\Anul 2 Sem 2\\Java\\Lab6\\ceva.json"), drawingPanel );
//        }
//        catch (IOException ex){
//            throw new InvalidParameterException("aaa");
//
//        }



//
//        try{
//            FileOutputStream fos = new FileOutputStream("test.dat");
//            DataOutputStream out = new DataOutputStream(fos);
//            out.writeInt(12345);
//        }
//
//
//         catch (IOException ex){
//            throw new InvalidParameterException("aaa");
//        }
        try{
            BufferedImage image = ImageIO.read(new File("canvas.png"));
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            ImageIO.write(image, "png", bytes);
            String resultantimage = Base64.encodeBytes(bytes.toByteArray());
            System.out.println(resultantimage);
        } catch (Exception e){
            e.printStackTrace();
        }


    }


    private void exitGame(ActionEvent e) {
        frame.dispose();
    }

    private void saveBtn(ActionEvent actionEvent) {
        frame.pack();
        DrawingPanel drawingPanel = frame.canvas;
        BufferedImage image=new BufferedImage(drawingPanel.getWidth(), drawingPanel.getHeight(),BufferedImage.TYPE_INT_RGB);
        Graphics2D g2=(Graphics2D)image.getGraphics();
        drawingPanel.paint(g2);
        try {
            ImageIO.write(image, "png", new File("canvas.png"));
        } catch (Exception e) {
        }


    }

    private void resetBtn(ActionEvent actionEvent) {
        DrawingPanel drawingPanel = frame.canvas;
        Graphics g = frame.canvas.getGraphics();
        drawingPanel.creeazaPuncteSiLinii(g);
    }

}

