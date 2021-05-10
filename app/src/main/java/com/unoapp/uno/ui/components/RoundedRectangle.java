package com.unoapp.uno.ui.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.LayoutManager;

import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class RoundedRectangle extends TransparentPanel {

    private int width, height;

    public RoundedRectangle(int width, int height) {
        super();
        this.width = width;
        this.height = height;
        setPreferredSize(new Dimension(width, height));
    }

    public RoundedRectangle(int width, int height, LayoutManager mgr) {
        this(width, height);
        setLayout(mgr);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g.create();

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        g2d.setColor(Color.WHITE);

        g2d.fillRoundRect(0, 0, width, height, 80, 80);
    }
}
