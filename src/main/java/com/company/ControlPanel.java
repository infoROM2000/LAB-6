package com.company;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static com.company.DrawingPanel.*;
import static com.company.Main.usingRetainedMode;

public class ControlPanel extends JPanel {
    final MainFrame frame;

    JButton saveBtn = new JButton("Save");
    JButton loadBtn = new JButton("Load");
    JButton resetBtn = new JButton("Reset");
    JButton exitBtn = new JButton("Exit");
    JButton deleteBtn = new JButton("Delete");

    public ControlPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    private void init() {
        //change the default layout manager (just for fun)
        setLayout(new GridLayout(1, 5));
        if (usingRetainedMode)
            add(deleteBtn);
        add(saveBtn);
        add(loadBtn);
        add(resetBtn);
        add(exitBtn);
        //configure listeners for all buttons
        saveBtn.addActionListener(this::save);
        loadBtn.addActionListener(this::load);
        resetBtn.addActionListener(this::reset);
        exitBtn.addActionListener(this::exit);
        deleteBtn.addActionListener(this::delete);
    }

    private void delete(ActionEvent e) {
        shapeList.remove(shapeList.size() - 1);
    }

    private void save(ActionEvent e) {
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("C:/Users/Eu/Pictures"));
        chooser.setFileFilter(new FileNameExtensionFilter("Imagini JPG si PNG", "jpg", "png"));
        int returnVal = chooser.showSaveDialog(ControlPanel.this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            try {
                ImageIO.write(frame.canvas.image, "PNG", new File(chooser.getSelectedFile().getAbsolutePath()));
            } catch (IOException ex) {
                System.err.println(ex);
            }
        }
    }

    private void load(ActionEvent e) {
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("C:/Users/Eu/Pictures"));
        chooser.setFileFilter(new FileNameExtensionFilter("Imagini JPG si PNG", "jpg", "png"));
        int returnVal = chooser.showOpenDialog(ControlPanel.this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            try {
                BufferedImage image = ImageIO.read(new File(chooser.getSelectedFile().getAbsolutePath()));
                frame.canvas.image = image;
                frame.canvas.graphics = frame.canvas.image.createGraphics();
            } catch (IOException ex) {
                System.err.println(ex);
            }
        }
    }

    private void reset(ActionEvent e) {
        frame.canvas.createOffscreenImage();
        if (usingRetainedMode)
            shapeList.clear();
    }

    private void exit(ActionEvent e) {
        System.exit(0);
    }
}
