package com.unoapp.uno.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.font.TextLayout;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import java.awt.image.BufferedImage;
import javax.swing.border.Border;

import com.unoapp.uno.ui.components.BlankCard;
import com.unoapp.uno.ui.components.DrawFour;
import com.unoapp.uno.ui.components.DrawTwo;
import com.unoapp.uno.ui.components.Reverse;
import com.unoapp.uno.ui.components.Skip;
import com.unoapp.uno.ui.components.Wild;
import com.unoapp.uno.utils.Constants;

public class CardLabel extends JLabel {

    /**
     * Auto Generated
     */
    private static final long serialVersionUID = 1L;

    private Constants.Color color;
    private Integer num;
    private BufferedImage image;
    private Border grayBorder;
    private Boolean disabled;

    private final int width = 168;
    private final int height = 267;

    private final Font small = new Font("Gilmer Heavy", Font.PLAIN, 36);
    private final Font reverse = new Font("Gilmer Heavy", Font.PLAIN, -36);
    private final Font big = new Font("Gilmer Heavy", Font.PLAIN, 82);

    public CardLabel(Constants.Color color, Integer num, Boolean disabled, onClickListener mClickListener)
            throws IOException {
        this.color = color;
        this.num = num;
        this.disabled = disabled;

        this.grayBorder = BorderFactory.createLineBorder(Color.DARK_GRAY);
        this.setMouseListener(mClickListener);
    }

    private void setMouseListener(onClickListener mClickListener) {
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                mClickListener.onClicked();
            }

            @Override
            public void mouseEntered(MouseEvent arg0) {
                setBorder(grayBorder);
                revalidate();
            }

            @Override
            public void mouseExited(MouseEvent arg0) {
                setBorder(null);
                revalidate();
            }

            @Override
            public void mousePressed(MouseEvent arg0) {

            }

            @Override
            public void mouseReleased(MouseEvent arg0) {
            }
        });
    }

    private Color getColor() {
        if (disabled) {
            return Color.GRAY;
        }

        switch (this.color) {
        case RED:
            return new Color(206, 60, 61);
        case BLUE:
            return new Color(3, 107, 210);
        case GREEN:
            return new Color(108, 181, 96);
        case YELLOW:
            return new Color(239, 211, 65);
        case BLACK:
            return new Color(36, 39, 41);
        }
        return null;
    }

    private boolean isSixNine() {
        return num == 6 || num == 9;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(width, height);
    }

    private void drawPlus2(Graphics2D g2d) {
        new TextLayout("+2", small, g2d.getFontRenderContext()).draw(g2d, 14, 44);
        new TextLayout("+2", reverse, g2d.getFontRenderContext()).draw(g2d, (width - 14), (height - 44));
        DrawTwo.paint(g2d, getColor(), (width / 2) - (DrawTwo.getOrigWidth() * 0.2 * 0.5),
                (height / 2) - (DrawTwo.getOrigHeight() * 0.2 * 0.5));
    }

    private void drawReverse(Graphics2D g2d) {
        Reverse.paint(g2d, Color.WHITE, 0.05f, 16 - (Reverse.getOrigX() * 0.05),
                34 - (Reverse.getOrigY() * 0.05) - (Reverse.getOrigHeight() * 0.05 * 0.5));

        Reverse.paint(g2d, Color.WHITE, 0.05f,
                (width - 16) - (Reverse.getOrigX() * 0.05) - (Reverse.getOrigWidth() * 0.05),
                (height - 34) - (Reverse.getOrigY() * 0.05) - (Reverse.getOrigHeight() * 0.05 * 0.5));

        Reverse.paint(g2d, getColor(), 0.15f,
                (width / 2) - (Reverse.getOrigWidth() * 0.15 * 0.5) - Reverse.getOrigX() * 0.15,
                (height / 2) - (Reverse.getOrigHeight() * 0.15 * 0.5) - Reverse.getOrigY() * 0.15);

    }

    private void drawSkip(Graphics2D g2d) {
        Skip.paint(g2d, Color.WHITE, 0.05f, 16, 32 - (Skip.getOrigHeight() * 0.05 * 0.5));

        Skip.paint(g2d, Color.WHITE, 0.05f, (width - 16) - (Skip.getOrigWidth() * 0.05),
                (height - 32) - (Skip.getOrigHeight() * 0.05 * 0.5));
        Skip.paint(g2d, getColor(), 0.15f, (width / 2) - (Skip.getOrigWidth() * 0.15 * 0.5),
                (height / 2) - (Skip.getOrigHeight() * 0.15 * 0.5));
    }

    private void drawDraw4(Graphics2D g2d) {
        new TextLayout("+4", small, g2d.getFontRenderContext()).draw(g2d, 14, 44);
        new TextLayout("+4", reverse, g2d.getFontRenderContext()).draw(g2d, (width - 14), (height - 44));
        DrawFour.paint(g2d, getColor(), (width / 2) - (706 * 0.15) / 2, height / 2 - (521 * 0.15) / 2);
    }

    private void drawWild(Graphics2D g2d) {
        Wild.paint(g2d, getColor(), width, height);
    }

    private void drawNormal(Graphics2D g2d) {
        g2d.setColor(Color.WHITE);
        // Draw numbers
        TextLayout layout = new TextLayout(num.toString(), small, g2d.getFontRenderContext());
        Rectangle r = layout.getPixelBounds(null, 0, 0);
        layout.draw(g2d, 16, 44);
        if (isSixNine())
            g2d.fillRect(15, 46, (int) r.getWidth() + 2, 2);

        TextLayout layout3 = new TextLayout(num.toString(), reverse, g2d.getFontRenderContext());
        Rectangle r3 = layout3.getPixelBounds(null, 0, 0);

        layout3.draw(g2d, (width - 16), (height - 44));
        if (isSixNine())
            g2d.fillRect((int) (width - 16 - 1 - r3.getWidth()), (height - 44 - 4), (int) r3.getWidth() + 2, 2);

        g2d.setColor(getColor());
        TextLayout layout2 = new TextLayout(num.toString(), big, g2d.getFontRenderContext());
        Rectangle r2 = layout2.getPixelBounds(null, 0, 0);
        float width2 = ((width / 2) - (float) (r2.getWidth() / 2));
        float height2 = (float) ((height / 2) + (r2.getHeight() / 2));
        layout2.draw(g2d, width2, height2);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        if (num == Constants.WILD) {
            drawWild(g2d);
            g2d.dispose();
            return;
        }

        Graphics2D ng2d = (Graphics2D) g2d.create();
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        ng2d.drawImage(image, 0, 0, width, height, null);
        double coef = Math.min((double) width / (double) 1120, (double) height / (double) 1780);
        ng2d.scale(coef, coef);
        BlankCard.paint(getColor(), ng2d);
        ng2d.dispose();

        g2d.setColor(Color.WHITE);

        switch (num) {
        case Constants.DRAW2:
            drawPlus2(g2d);
            break;
        case Constants.REVERSE:
            drawReverse(g2d);
            break;
        case Constants.SKIP:
            drawSkip(g2d);
            break;
        case Constants.DRAWFOUR:
            drawDraw4(g2d);
            break;
        case Constants.WILD:
            drawWild(g2d);
            break;
        default:
            drawNormal(g2d);
            break;
        }

        g2d.dispose();
    }

    public interface onClickListener {
        void onClicked();
    }
}