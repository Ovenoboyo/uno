package com.unoapp.uno.ui.drawables;

import static java.awt.Color.BLACK;
import static java.awt.Color.WHITE;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.GeneralPath;

/**
 * This class has been automatically generated using
 * <a href="http://ebourg.github.io/flamingo-svg-transcoder/">Flamingo SVG transcoder</a>.
 */
public class FancyArrow {

    /** The width of this icon. */
    private static int width = 84;

    /** The height of this icon. */
    private static int height = 93;

    public static int getIconHeight() {
        return height;
    }

    public static int getIconWidth() {
        return width;
    }

    /**
     * Paints the transcoded SVG image on the specified graphics context.
     * 
     * @param g Graphics context.
     */
    public static void paint(Graphics2D g) {
        Shape shape = null;

        // 

        // _0

        // _0_0
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(14.1342, 38.9193);
        ((GeneralPath) shape).curveTo(11.4577, 40.448, 9.80006, 43.2886, 9.78572, 46.3708);
        ((GeneralPath) shape).curveTo(9.77138, 49.4531, 11.4025, 52.3089, 14.0647, 53.8625);
        ((GeneralPath) shape).lineTo(63.232, 82.5551);
        ((GeneralPath) shape).curveTo(65.8942, 84.1086, 69.183, 84.1239, 71.8595, 82.5952);
        ((GeneralPath) shape).curveTo(74.536, 81.0665, 76.1936, 78.226, 76.208, 75.1437);
        ((GeneralPath) shape).lineTo(76.4728, 18.2172);
        ((GeneralPath) shape).curveTo(76.4871, 15.1349, 74.856, 12.2791, 72.1938, 10.7256);
        ((GeneralPath) shape).curveTo(69.5316, 9.17201, 66.2429, 9.15671, 63.5664, 10.6854);
        ((GeneralPath) shape).lineTo(14.1342, 38.9193);
        ((GeneralPath) shape).closePath();

        g.setPaint(WHITE);
        g.fill(shape);
        g.setPaint(new Color(0xEFD341));
        g.setStroke(new BasicStroke(17.2551f, 0, 1, 4));
        g.draw(shape);

        // _0_1
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(16.7765, 40.6275);
        ((GeneralPath) shape).curveTo(15.4456, 41.2465, 14.546, 42.5285, 14.4167, 43.9906);
        ((GeneralPath) shape).curveTo(14.2873, 45.4526, 14.9477, 46.8727, 16.1492, 47.7157);
        ((GeneralPath) shape).lineTo(62.7486, 80.4142);
        ((GeneralPath) shape).curveTo(63.9501, 81.2573, 65.5101, 81.3954, 66.8409, 80.7764);
        ((GeneralPath) shape).curveTo(68.1718, 80.1574, 69.0714, 78.8754, 69.2007, 77.4134);
        ((GeneralPath) shape).lineTo(74.2188, 20.7079);
        ((GeneralPath) shape).curveTo(74.3482, 19.2458, 73.6877, 17.8258, 72.4862, 16.9827);
        ((GeneralPath) shape).curveTo(71.2847, 16.1396, 69.7247, 16.0016, 68.3939, 16.6206);
        ((GeneralPath) shape).lineTo(16.7765, 40.6275);
        ((GeneralPath) shape).closePath();

        g.setPaint(WHITE);
        g.fill(shape);
        g.setPaint(BLACK);
        g.setStroke(new BasicStroke(8.21671f, 0, 1, 4));
        g.draw(shape);

    }
}
