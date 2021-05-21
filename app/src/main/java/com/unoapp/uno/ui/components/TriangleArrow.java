package com.unoapp.uno.ui.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class TriangleArrow extends TransparentPanel {
    private Boolean left;
    private Color color;

    /**
     * Create a fully filled triangle pointing sideways
     * 
     * @param left true if arrow is pointing left
     * @param color color of arrow
     */
    public TriangleArrow(Boolean left, Color color) {
        super();
        this.left = left;
        this.color = color;
        setPreferredSize(new Dimension(14, 22 + 4));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g.create();

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        g2d.setColor(color);

        int[] xPoints, yPoints;
        if (left) {
            xPoints = new int[] { 14, 14, 0 };
            yPoints = new int[] { 0 + 4, 22 + 4, 11 + 4 };
        } else {

            xPoints = new int[] { 0, 14, 0 };
            yPoints = new int[] { 0 + 4, 11 + 4, 22 + 4 };
        }
        g2d.fillPolygon(xPoints, yPoints, 3);
    }

}
