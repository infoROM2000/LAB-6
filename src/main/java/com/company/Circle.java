package com.company;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Circle extends Ellipse2D.Double implements Shape {
    Color color;

    public Circle(int x, int y, int xRadius, int yRadius, boolean cu_culori, Color... color) {
        super(x - xRadius / 2, y - yRadius / 2, xRadius, yRadius); // folosim x/y-radius/2 pentru ca locul unde apasam sa fie centrul cercului
        if (cu_culori)
            this.color = color[0];
    }

    public Color getColor() {
        return color;
    }

}
