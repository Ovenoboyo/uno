package com.unoapp.uno.ui.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

import javax.swing.Box;
import javax.swing.BoxLayout;

import com.unoapp.uno.utils.Constants;

public class ProgressBar extends TransparentPanel {
    private int width, height;
    private Double initialProgress, newProgress;
    private Constants.Color color;

    public ProgressBar(int width, int height, Double initialProgress, Double newProgress, Constants.Color color,
            String name, String xpAdded) {
        super(new BorderLayout());
        this.width = width;
        this.height = height;
        this.initialProgress = initialProgress;
        this.newProgress = newProgress;
        this.color = color;

        add(createNamePanel(name), BorderLayout.WEST);
        add(createTextPanel("+ " + xpAdded + "xp"), BorderLayout.EAST);

        setPreferredSize(new Dimension(width, height));
    }

    private TransparentPanel createNamePanel(String str) {
        var namePanel = createTextPanel(str);
        namePanel.add(new PersonIcon(59, 59, Constants.getColor(color, false)), 0);
        namePanel.add(Box.createHorizontalStrut(60), 0);

        return namePanel;
    }

    private TransparentPanel createTextPanel(String str) {
        TransparentPanel extraPanel = new TransparentPanel();
        extraPanel.setLayout(new BoxLayout(extraPanel, BoxLayout.X_AXIS));
        extraPanel.add(Box.createHorizontalStrut(30));
        extraPanel.add(new SmoothText(str, Constants.getProximaInstance(29, false)), BorderLayout.WEST);
        extraPanel.add(Box.createHorizontalStrut(30));

        return extraPanel;
    }

    private Color getColors() {
        switch (color) {
            default:
            case RED:
                return new Color(255, 186, 187);
            case GREEN:
                return new Color(185, 255, 174);
            case YELLOW:
                return new Color(255, 243, 180);
            case BLUE:
                return new Color(196, 216, 255);
        }
    }

    private Color getLightColors() {
        switch (color) {
            default:
            case RED:
                return new Color(255, 211, 211);
            case GREEN:
                return new Color(221, 255, 215);
            case YELLOW:
                return new Color(255, 248, 214);
            case BLUE:
                return new Color(232, 240, 255);
        }
    }

    private Integer percentageToWidth(double perc) {
        return (int) ((Math.min(perc, 100) / 100) * width);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g.create();

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        g2d.setPaint(Color.WHITE);

        g2d.fillRoundRect(0, 0, width, height, height, height);

        int initialWidth = percentageToWidth(initialProgress);

        if (initialWidth != 0) {
            g2d.setPaint(getColors());
            g2d.fill(new RoundRectangle2D.Double(0, 0, initialWidth, height, height, height));
            g2d.fill(new Rectangle2D.Double(initialWidth / 2, 0, initialWidth / 2 + 1, height));
        }
        g2d.setPaint(getLightColors());

        var newProgressWidth = percentageToWidth(newProgress);

        if (initialWidth == 0) {
            g2d.fill(new RoundRectangle2D.Double(0, 0, newProgressWidth, height, height, height));
            initialWidth = newProgressWidth / 2;
            newProgressWidth = newProgressWidth / 2;
        }

        if (initialWidth + newProgressWidth >= width - 15) {
            newProgressWidth = width - initialWidth;
            g2d.fill(new RoundRectangle2D.Double(initialWidth, 0, newProgressWidth + 1, height, height, height));
            g2d.fill(new Rectangle2D.Double(initialWidth, 0, newProgressWidth / 2, height));
        } else {
            g2d.fill(new Rectangle2D.Double(initialWidth, 0, newProgressWidth + 1, height));
        }
    }
}
