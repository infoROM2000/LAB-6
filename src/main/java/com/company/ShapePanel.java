package com.company;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ShapePanel extends JPanel {
    final MainFrame frame;
    String selectedShape;

    public ShapePanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    private void init() {
        setLayout(new GridLayout(6, 1));
        selectedShape = "circle";
        JButton polygon = new JButton();
        try {
            Image img = ImageIO.read(getClass().getResource("/polygon.png"));
            polygon.setIcon(new ImageIcon(img));
        } catch (Exception ex) {
            System.out.println(ex);
        }
        JButton circle = new JButton();
        try {
            Image img = ImageIO.read(getClass().getResource("/circle.png"));
            circle.setIcon(new ImageIcon(img));
        } catch (Exception ex) {
            System.out.println(ex);
        }
        add(polygon);
        add(circle);
        polygon.addActionListener(this::polygon);
        circle.addActionListener(this::circle);
    }

    private void polygon(ActionEvent e) {
        selectedShape = "polygon";
        updateConfigPanel();
    }

    private void circle(ActionEvent e) {
        selectedShape = "circle";
        updateConfigPanel();
    }

    private void updateConfigPanel() {
        frame.configPanel.removeAll(); //stergem elementele pe care le avea ConfigPanel
        frame.configPanel.repaint(); //desenem peste elemente (ramane gol) altfel ramane ce era inainte unde nu se deseneaza
        frame.configPanel.revalidate();
        frame.configPanel.init(); //initializam iarasi ConfigPanel-ul
    }

    public String getShape() {
        return selectedShape;
    }
}
