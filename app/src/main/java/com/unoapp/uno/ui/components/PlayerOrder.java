package com.unoapp.uno.ui.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;

import com.unoapp.uno.models.Player;
import com.unoapp.uno.ui.drawables.Arrow;
import com.unoapp.uno.utils.Colors;
import com.unoapp.uno.utils.Constants;
import com.unoapp.uno.utils.Fonts;

/**
 * Panel to hold player names with 
 * arrow on top of current player
 */
public class PlayerOrder extends TransparentPanel {
    private ArrayList<Player> players;
    private Player currentPlayer;
    private Constants.Color activeColor;
    private String str;
    private int charCount = 0;
    private boolean isReversed;

    public PlayerOrder(ArrayList<Player> players, Player currentPlayer, Constants.Color activeColor,
            boolean isReversed) {
        this.players = players;
        this.currentPlayer = currentPlayer;
        this.activeColor = activeColor;
        this.setData();
        this.isReversed = isReversed;
    }

    private void setData() {
        String str = "";
        for (Player p : players) {
            if (p.getName().equals(currentPlayer.getName())) {
                charCount = str.length() + p.getName().length() / 2;
                str += "," + p.getName() + ",    ";
            } else {
                str += p.getName() + "    ";
            }
        }
        this.str = str;
        setFont(Fonts.getGilmerInstance(36f));
    }

    @Override
    public Dimension getPreferredSize() {
        Graphics g = getGraphics();
        FontMetrics met = g.getFontMetrics();
        return new Dimension(met.stringWidth(str), met.getHeight() + Arrow.getIconHeight());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        FontMetrics met = g.getFontMetrics();

        String[] split = str.split(",");
        int nextStringPos = 0;
        for (String s : split) {
            g2d.setColor(Colors.getColor(activeColor, !s.equals(currentPlayer.getName())));
            g2d.drawString(s, nextStringPos, getFont().getSize() + Arrow.getIconHeight());
            nextStringPos += met.stringWidth(s);
        }

        g2d.setColor(new Color(185, 185, 185));

        g2d.translate(met.stringWidth(this.str.substring(0, charCount)) - Arrow.getIconWidth() / 2, 0);
        if (this.isReversed) {
            g2d.rotate(Math.PI);
            g2d.translate(-Arrow.getIconWidth(), -Arrow.getIconHeight());
        }
        Arrow.paint(g2d, Colors.getColor(activeColor, false));

        g2d.dispose();
    }

}
