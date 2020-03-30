package com.company;

import javax.swing.*;

public class ConfigPanel extends JPanel {
    final MainFrame frame;
    JLabel sidesLabel; // we’re drawing regular polygons
    JSpinner sidesField; // number of sides
    JComboBox colorCombo; // the color of the shape
    JSpinner xRadius;
    JSpinner yRadius;
    JSpinner transparency;

    public ConfigPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    public void init() {
        //create the label and the spinner
        String selectedShape = frame.shapePanel.getShape();
        String[] colors = {"black", "random"};
        colorCombo = new JComboBox(colors);
        switch (selectedShape) {
            case "polygon":
                sidesLabel = new JLabel("Number of sides:");
                sidesField = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
                sidesField.setValue(6); //default number of sides
                add(sidesLabel); //JPanel uses FlowLayout by default
                add(sidesField);
                break;
            case "circle":
                JLabel xR = new JLabel("x Radius:");
                JLabel yR = new JLabel("y Radius:");
                xRadius = new JSpinner(new SpinnerNumberModel(25, 5, 100, 5));
                yRadius = new JSpinner(new SpinnerNumberModel(25, 5, 100, 5));
                add(xR);
                add(xRadius);
                add(yR);
                add(yRadius);
                break;
            default:
        }
        transparency = new JSpinner(new SpinnerNumberModel(50, 0, 100, 1));
        add(colorCombo);
        add(transparency);
    }

    public int getSidesField() {
        return (int) sidesField.getValue();
    }

    public boolean getColor() {
        if (colorCombo.getSelectedItem().toString().equals("black"))
            return false;
        return true;
    }

    public int getXRadius() {
        return (int) xRadius.getValue();
    }

    public int getYRadius() {
        return (int) yRadius.getValue();
    }

    public float getTransparency() {
        int a = (int) transparency.getValue();
        return (float) a / 100f;
    }
}
