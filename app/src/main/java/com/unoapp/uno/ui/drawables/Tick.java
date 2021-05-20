package com.unoapp.uno.ui.drawables;

import static java.awt.Color.WHITE;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.GeneralPath;
import java.awt.image.BufferedImage;

/**
 * This class has been automatically generated using
 * <a href="http://ebourg.github.io/flamingo-svg-transcoder/">Flamingo SVG transcoder</a>.
 */
public class Tick implements javax.swing.Icon {

    /** The width of this icon. */
    private int width;

    /** The height of this icon. */
    private int height;

    /** The rendered image. */
    private BufferedImage image;

    /**
     * Creates a new transcoded SVG image.
     */
    public Tick() {
        this(40, 40);
    }

    /**
     * Creates a new transcoded SVG image.
     */
    public Tick(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public int getIconHeight() {
        return height;
    }

    @Override
    public int getIconWidth() {
        return width;
    }

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        if (image == null) {
            image = new BufferedImage(getIconWidth(), getIconHeight(), BufferedImage.TYPE_INT_ARGB);
            double coef = Math.min((double) width / (double) 61, (double) height / (double) 61);

            Graphics2D g2d = image.createGraphics();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.scale(coef, coef);
            paint(g2d);
            g2d.dispose();
        }

        g.drawImage(image, x, y, null);
    }

    /**
     * Paints the transcoded SVG image on the specified graphics context.
     * 
     * @param g Graphics context.
     */
    private static void paint(Graphics2D g) {
        Shape shape = null;

        // 

        // _0

        // _0_0
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(0.0, 30.25);
        ((GeneralPath) shape).curveTo(0.0, 22.2272, 3.18704, 14.533, 8.86002, 8.86002);
        ((GeneralPath) shape).curveTo(14.533, 3.18704, 22.2272, 0.0, 30.25, 0.0);
        ((GeneralPath) shape).curveTo(38.2728, 0.0, 45.967, 3.18704, 51.64, 8.86002);
        ((GeneralPath) shape).curveTo(57.313, 14.533, 60.5, 22.2272, 60.5, 30.25);
        ((GeneralPath) shape).curveTo(60.5, 38.2728, 57.313, 45.967, 51.64, 51.64);
        ((GeneralPath) shape).curveTo(45.967, 57.313, 38.2728, 60.5, 30.25, 60.5);
        ((GeneralPath) shape).curveTo(22.2272, 60.5, 14.533, 57.313, 8.86002, 51.64);
        ((GeneralPath) shape).curveTo(3.18704, 45.967, 0.0, 38.2728, 0.0, 30.25);
        ((GeneralPath) shape).closePath();
        ((GeneralPath) shape).moveTo(44.4917, 24.8292);
        ((GeneralPath) shape).curveTo(44.773, 24.5479, 44.9961, 24.2141, 45.1483, 23.8466);
        ((GeneralPath) shape).curveTo(45.3005, 23.4791, 45.3788, 23.0853, 45.3788, 22.6875);
        ((GeneralPath) shape).curveTo(45.3788, 22.2898, 45.3005, 21.8959, 45.1483, 21.5284);
        ((GeneralPath) shape).curveTo(44.9961, 21.1609, 44.773, 20.8271, 44.4917, 20.5458);
        ((GeneralPath) shape).curveTo(44.2104, 20.2645, 43.8766, 20.0414, 43.5091, 19.8892);
        ((GeneralPath) shape).curveTo(43.1416, 19.737, 42.7478, 19.6587, 42.35, 19.6587);
        ((GeneralPath) shape).curveTo(41.9523, 19.6587, 41.5584, 19.737, 41.1909, 19.8892);
        ((GeneralPath) shape).curveTo(40.8234, 20.0414, 40.4896, 20.2645, 40.2083, 20.5458);
        ((GeneralPath) shape).lineTo(27.225, 33.5352);
        ((GeneralPath) shape).lineTo(20.2917, 26.5958);
        ((GeneralPath) shape).curveTo(20.0104, 26.3145, 19.6766, 26.0914, 19.3091, 25.9392);
        ((GeneralPath) shape).curveTo(18.9416, 25.787, 18.5478, 25.7087, 18.15, 25.7087);
        ((GeneralPath) shape).curveTo(17.7523, 25.7087, 17.3584, 25.787, 16.9909, 25.9392);
        ((GeneralPath) shape).curveTo(16.6234, 26.0914, 16.2896, 26.3145, 16.0083, 26.5958);
        ((GeneralPath) shape).curveTo(15.727, 26.8771, 15.5039, 27.2109, 15.3517, 27.5784);
        ((GeneralPath) shape).curveTo(15.1995, 27.9459, 15.1212, 28.3398, 15.1212, 28.7375);
        ((GeneralPath) shape).curveTo(15.1212, 29.1353, 15.1995, 29.5291, 15.3517, 29.8966);
        ((GeneralPath) shape).curveTo(15.5039, 30.2641, 15.727, 30.5979, 16.0083, 30.8792);
        ((GeneralPath) shape).lineTo(25.0833, 39.9542);
        ((GeneralPath) shape).curveTo(25.3643, 40.2359, 25.6981, 40.4594, 26.0656, 40.6119);
        ((GeneralPath) shape).curveTo(26.4331, 40.7644, 26.8271, 40.8429, 27.225, 40.8429);
        ((GeneralPath) shape).curveTo(27.6229, 40.8429, 28.0169, 40.7644, 28.3844, 40.6119);
        ((GeneralPath) shape).curveTo(28.7519, 40.4594, 29.0857, 40.2359, 29.3667, 39.9542);
        ((GeneralPath) shape).lineTo(44.4917, 24.8292);
        ((GeneralPath) shape).closePath();

        g.setPaint(WHITE);
        g.fill(shape);

    }
}
