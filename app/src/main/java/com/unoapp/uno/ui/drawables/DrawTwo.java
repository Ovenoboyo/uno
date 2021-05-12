package com.unoapp.uno.ui.drawables;

import static java.awt.Color.WHITE;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;

/**
 * This class has been automatically generated using
 * <a href="http://ebourg.github.io/flamingo-svg-transcoder/">Flamingo SVG
 * transcoder</a>.
 */
public class DrawTwo {

    /**
     * Paints the transcoded SVG image on the specified graphics context. You can
     * install a custom transformation on the graphics context to scale the image.
     * 
     * @param g2d
     * @param color
     * @param x
     * @param y
     */
    public static void paint(Graphics2D g2d, Color color, double x, double y) {
        Shape shape = null;

        //

        // _0

        // _0_0
        Graphics2D g = (Graphics2D) g2d.create();
        g.translate(x, y);
        g.scale(0.2, 0.2);
        shape = new RoundRectangle2D.Double(144, 18, 256, 399, 90, 90);
        g.setPaint(color);
        g.fill(shape);
        g.setPaint(WHITE);
        g.setStroke(new BasicStroke(35, 0, 0, 4));
        g.draw(shape);

        // _0_1
        shape = new RoundRectangle2D.Double(18, 139, 256, 399, 90, 90);
        g.setPaint(color);
        g.fill(shape);
        g.setPaint(WHITE);
        g.draw(shape);

        g.dispose();

    }

    /**
     * Returns the width of the bounding box of the original SVG image.
     * 
     * @return The width of the bounding box of the original SVG image.
     */
    public static int getOrigWidth() {
        return 417;
    }

    /**
     * Returns the height of the bounding box of the original SVG image.
     * 
     * @return The height of the bounding box of the original SVG image.
     */
    public static int getOrigHeight() {
        return 555;
    }
}
