package com.unoapp.uno.ui.drawables;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

/**
 * This class has been automatically generated using
 * <a href="http://ebourg.github.io/flamingo-svg-transcoder/">Flamingo SVG
 * transcoder</a>.
 */
public class Skip {

    /**
     * Paints the transcoded SVG image on the specified graphics context. You can
     * install a custom transformation on the graphics context to scale the image.
     * 
     * @param g2d
     * @param color
     * @param scale
     * @param x
     * @param y
     */
    public static void paint(Graphics2D g2d, Color color, float scale, double x, double y) {
        Shape shape = null;

        java.util.LinkedList<AffineTransform> transformations = new java.util.LinkedList<AffineTransform>();

        //

        // _0

        // _0_0
        Graphics2D g = (Graphics2D) g2d.create();
        g.translate(x, y);
        g.scale(scale, scale);
        shape = new Ellipse2D.Double(39, 39, 414, 414);
        g.setPaint(color);
        g.setStroke(new BasicStroke(78, 0, 0, 4));
        g.draw(shape);
        transformations.push(g.getTransform());
        g.transform(new AffineTransform(-0.70710677f, 0.70710677f, -0.70710677f, -0.70710677f, 801.23956f, -60.89229f));

        // _0_1
        shape = new Rectangle2D.Double(413.2309875488281, 135.49600219726562, 412.57501220703125, 84.1404037475586);
        g.fill(shape);

        g.setTransform(transformations.pop()); // _0_1

        g.dispose();

    }

    /**
     * Returns the X of the bounding box of the original SVG image.
     * 
     * @return The X of the bounding box of the original SVG image.
     */
    public static int getOrigX() {
        return 0;
    }

    /**
     * Returns the Y of the bounding box of the original SVG image.
     * 
     * @return The Y of the bounding box of the original SVG image.
     */
    public static int getOrigY() {
        return 0;
    }

    /**
     * Returns the width of the bounding box of the original SVG image.
     * 
     * @return The width of the bounding box of the original SVG image.
     */
    public static int getOrigWidth() {
        return 492;
    }

    /**
     * Returns the height of the bounding box of the original SVG image.
     * 
     * @return The height of the bounding box of the original SVG image.
     */
    public static int getOrigHeight() {
        return 492;
    }
}
