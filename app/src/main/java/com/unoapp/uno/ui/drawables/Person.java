package com.unoapp.uno.ui.drawables;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.GeneralPath;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

/**
 * This class has been automatically generated using
 * <a href="http://ebourg.github.io/flamingo-svg-transcoder/">Flamingo SVG transcoder</a>.
 */
public class Person extends ImageIcon {

    /** The width of this icon. */
    private int width;

    /** The height of this icon. */
    private int height;

    private final Color color;

    /** The rendered image. */
    private BufferedImage image;

    /**
     * Creates a new transcoded SVG image.
     */
    public Person() {
        this(135, 135, Color.BLACK);
    }

    /**
     * Creates a new transcoded SVG image.
     */
    public Person(int width, int height, Color color) {
        this.width = width;
        this.height = height;
        this.color = color;
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
            double coef = Math.min((double) width / (double) 135, (double) height / (double) 135);

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
    private void paint(Graphics2D g) {
        Shape shape = null;

        // 

        // _0

        // _0_0
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(11.25, 135.0);
        ((GeneralPath) shape).curveTo(11.25, 135.0, 0.0, 135.0, 0.0, 123.75);
        ((GeneralPath) shape).curveTo(0.0, 112.5, 11.25, 78.75, 67.5, 78.75);
        ((GeneralPath) shape).curveTo(123.75, 78.75, 135.0, 112.5, 135.0, 123.75);
        ((GeneralPath) shape).curveTo(135.0, 135.0, 123.75, 135.0, 123.75, 135.0);
        ((GeneralPath) shape).lineTo(11.25, 135.0);
        ((GeneralPath) shape).closePath();
        ((GeneralPath) shape).moveTo(67.5, 67.5);
        ((GeneralPath) shape).curveTo(76.4511, 67.5, 85.0355, 63.9442, 91.3649, 57.6149);
        ((GeneralPath) shape).curveTo(97.6942, 51.2855, 101.25, 42.7011, 101.25, 33.75);
        ((GeneralPath) shape).curveTo(101.25, 24.7989, 97.6942, 16.2145, 91.3649, 9.88515);
        ((GeneralPath) shape).curveTo(85.0355, 3.55579, 76.4511, 0.0, 67.5, 0.0);
        ((GeneralPath) shape).curveTo(58.5489, 0.0, 49.9645, 3.55579, 43.6351, 9.88515);
        ((GeneralPath) shape).curveTo(37.3058, 16.2145, 33.75, 24.7989, 33.75, 33.75);
        ((GeneralPath) shape).curveTo(33.75, 42.7011, 37.3058, 51.2855, 43.6351, 57.6149);
        ((GeneralPath) shape).curveTo(49.9645, 63.9442, 58.5489, 67.5, 67.5, 67.5);
        ((GeneralPath) shape).closePath();

        g.setPaint(this.color);
        g.fill(shape);

    }
}
