package com.unoapp.uno.ui.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

/**
 * Generic implementation of anti-aliased text
 */
public class SmoothText extends JPanel {
    private String str;
    private Color color;
    private Font font;

    public SmoothText(String str, Color color, Font font) {
        this.str = str;
        this.color = color;
        this.font = font;

        setOpaque(false);
    }

    @Override
    public Dimension getPreferredSize() {
        Graphics g = getGraphics();
        g.setFont(font);
        FontMetrics met = g.getFontMetrics();
        return new Dimension(met.stringWidth(str), met.getHeight());
    }

    public void setText(String str) {
        this.str = str;
        repaint();
        revalidate();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setFont(font);

        Graphics2D g2d = (Graphics2D) g.create();

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        g2d.setColor(this.color);
        g2d.drawString(str, 0, font.getSize());
    }

}
