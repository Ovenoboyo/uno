package com.unoapp.uno.ui.components;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

import com.unoapp.uno.models.Card;

/**
 * Panel to hold last played cards
 */
public class LastPlayedComponent extends TransparentPanel {

    private ArrayList<Card> lastPlayed = new ArrayList<>();
    private int RLowerBoundary = -16;
    private int RUpperBoundary = 16;
    private int RotationOffset = 8;
    private Random random = new Random();

    public LastPlayedComponent(ArrayList<Card> lastPlayed) {
        this.lastPlayed.addAll(lastPlayed);
        this.init();
    }

    private void init() {
        setLayout(null);
        setPreferredSize(new Dimension(168 + 100, 267 + 50));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g.create();
        g2d.translate(25, 25);

        Card card;
        CardPanel label;
        Integer prevAngle = 0;
        Integer rand = 0;
        for (int i = lastPlayed.size() - 1; i >= 0; i--) {
            Graphics2D tg2d = (Graphics2D) g2d.create();
            card = lastPlayed.get(i);
            label = new CardPanel(card, false, null);

            if (card.getAngle() == 0) {
                rand = getRandomAngle();
                while (!(inRange(RLowerBoundary, prevAngle - RotationOffset, rand)
                        || inRange(prevAngle + RotationOffset, RUpperBoundary, rand)) && prevAngle != 0) {
                    rand = getRandomAngle();
                }
                card.setAngle(rand);
            }

            tg2d.rotate(card.getAngle() * Math.PI / 180, label.getWidth() / 2, label.getHeight() / 2);
            label.paintManually(tg2d);
            tg2d.dispose();

            prevAngle = card.getAngle();
        }
        g2d.dispose();
    }

    private Integer getRandomAngle() {
        return random.nextInt(RUpperBoundary - RLowerBoundary) + RLowerBoundary;
    }

    private Boolean inRange(int low, int high, int x) {
        return ((x - high) * (x - low) <= 0);
    }

}
