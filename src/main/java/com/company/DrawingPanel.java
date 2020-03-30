package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.ArrayList;

import static com.company.Main.usingRetainedMode;

public class DrawingPanel extends JPanel {
    final MainFrame frame;
    final static int W = 800, H = 600;
    static List<Shape> shapeList = new ArrayList<>();
    BufferedImage image; //the offscreen image
    Graphics2D graphics; //the "tools" needed to draw in the image

    public DrawingPanel(MainFrame frame) {
        this.frame = frame;
        createOffscreenImage();
        init();
    }

    public void createOffscreenImage() {
        image = new BufferedImage(W, H, BufferedImage.TYPE_INT_ARGB);
        graphics = image.createGraphics();
        graphics.setColor(Color.WHITE); //fill the image with white
        graphics.fillRect(0, 0, W, H);
    }

    private void init() {
        setPreferredSize(new Dimension(W, H)); //don’t use setSize. Why?
        setBorder(BorderFactory.createEtchedBorder()); //for fun
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                drawShape(e.getX(), e.getY());
                if (usingRetainedMode) {
                    frame.canvas.createOffscreenImage();
                    for (Shape shape : shapeList) {
                        graphics.setColor(shape.getColor());
                        graphics.fill((java.awt.Shape) shape);
                    }
                }

                repaint();
            } //Can’t use lambdas, JavaFX does a better job in these cases
        });
    }

    private void drawShape(int x, int y) {
        int radius = (int) (Math.random() * 100); //generate a random number
        boolean culoareRandom = frame.configPanel.getColor();
        float r = 0f, g = 0f, b = 0f;
        if (culoareRandom) {
            r = (float) Math.random();
            g = (float) Math.random();
            b = (float) Math.random();
        }
        float a = frame.configPanel.getTransparency(); // a e transparenta de la 0(invizibil) la 1(opac)
        Color color = new Color(r, g, b, a); //create a transparent random Color.
        graphics.setColor(color);
        String shape = frame.shapePanel.getShape();
        switch (shape) {
            case "polygon":
                int sides = frame.configPanel.getSidesField(); //get the value from UI (in ConfigPanel)
                if (!usingRetainedMode)
                    graphics.fill(new RegularPolygon(x, y, radius, sides, false));
                else
                    shapeList.add(new RegularPolygon(x, y, radius, sides, true, color));
                break;
            case "circle":
                int xRadius = frame.configPanel.getXRadius();
                int yRadius = frame.configPanel.getYRadius();
                if (!usingRetainedMode)
                    graphics.fill(new Circle(x, y, xRadius, yRadius, false));
                else
                    shapeList.add(new Circle(x, y, xRadius, yRadius, true, color));
                break;
            default:
        }
    }

    @Override
    public void update(Graphics g) {
    } //Why did I do that?

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, this);
    }
}
