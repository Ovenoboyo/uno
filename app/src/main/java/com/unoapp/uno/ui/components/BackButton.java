package com.unoapp.uno.ui.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import com.unoapp.uno.ui.drawables.FancyArrow;

/**
 * Panel to hold back icon and text
 */
public class BackButton extends TransparentPanel {
    private Double scale;
    private Font font;

    public BackButton(Double scale, Font font) {
        this.scale = scale;
        this.font = font;
    }

    /**
     * returns preferred size of panel
     * Size should usually be ((Size of string) + (Size of fancy arrow) + padding offset)) * scale
     */
    @Override
    public Dimension getPreferredSize() {
        Graphics g = getGraphics();
        g.setFont(font);
        FontMetrics met = g.getFontMetrics();
        return new Dimension((int) ((met.stringWidth("Back") + FancyArrow.getIconWidth() + 30) * scale),
                (int) (Math.max(met.getHeight(), FancyArrow.getIconHeight()) * scale));
    }

    /**
     * Paint the fancy arrow and back label
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setFont(font);

        Graphics2D g2d = (Graphics2D) g.create();

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        g2d.scale(scale, scale);

        FancyArrow.paint(g2d);

        g2d.setColor(Color.WHITE);
        g2d.drawString("Back", FancyArrow.getIconWidth() + 15,
                (font.getSize() / 2 + FancyArrow.getIconHeight() / 2) - 5);

    }
}
