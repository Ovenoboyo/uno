package com.unoapp.uno.ui.components;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

import com.unoapp.uno.models.Card;

/**
 * Panel to hold last played cards
 */
public class LastPlayedComponent extends TransparentPanel {

    private ArrayList<Card> lastPlayed = new ArrayList<>();

    public LastPlayedComponent(ArrayList<Card> lastPlayed) {
        this.lastPlayed.addAll(lastPlayed);
        this.init();
    }

    private void init() {
        setLayout(null);
        setPreferredSize(new Dimension(168 + 50, 267 + 50));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g.create();
        g2d.translate(25, 25);

        Card card;
        CardPanel label;
        for (int i = lastPlayed.size() - 1; i >= 0; i--) {
            card = lastPlayed.get(i);
            label = new CardPanel(card, false, null);
            label.paintManually(g2d);
            g2d.rotate(8 * Math.PI / 180, label.getWidth() / 2, label.getHeight() / 2);
        }
        g2d.dispose();
    }

}
