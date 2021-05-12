package com.unoapp.uno.ui.drawables;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.GeneralPath;

/**
 * This class has been automatically generated using
 * <a href="http://ebourg.github.io/flamingo-svg-transcoder/">Flamingo SVG transcoder</a>.
 */
public class Arrow {

    public static int getIconHeight() {
        return 43;
    }

    public static int getIconWidth() {
        return 47;
    }

    /**
     * Paints the transcoded SVG image on the specified graphics context.
     * 
     * @param g Graphics context.
     * @param color color of arrow
     */
    public static void paint(Graphics2D g, Color color) {
        Shape shape = null;

        // 

        // _0

        // _0_0
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(47.0, 21.5);
        ((GeneralPath) shape).lineTo(21.6923, 0.0);
        ((GeneralPath) shape).lineTo(21.6923, 14.5);
        ((GeneralPath) shape).lineTo(0.0, 14.5);
        ((GeneralPath) shape).lineTo(0.0, 28.0);
        ((GeneralPath) shape).lineTo(21.6923, 28.0);
        ((GeneralPath) shape).lineTo(21.6923, 43.0);
        ((GeneralPath) shape).lineTo(47.0, 21.5);
        ((GeneralPath) shape).closePath();

        g.setPaint(color);
        g.fill(shape);

    }
}
