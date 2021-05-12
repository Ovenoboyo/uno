package com.unoapp.uno.ui.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

import com.unoapp.uno.ui.drawables.Arrow;

public class ArrowLabel extends JPanel {
    private Double scale;
    private Color color;

    public ArrowLabel(Double scale, Color color) {
        this.scale = scale;
        this.color = color;
        setPreferredSize(new Dimension((int) (Arrow.getIconWidth() * scale), (int) (Arrow.getIconHeight() * scale)));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g.create();

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        g2d.scale(scale, scale);

        Arrow.paint(g2d, color);
        g2d.dispose();
    }
}
