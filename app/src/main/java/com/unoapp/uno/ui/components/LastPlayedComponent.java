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

    private ArrayList<Card> lastPlayed;
    private int RLowerBoundary = -16;
    private int RUpperBoundary = 16;
    private int RotationOffset = 8;
    private Random random = new Random();

    public LastPlayedComponent(ArrayList<Card> lastPlayed) {
        this.lastPlayed = lastPlayed;
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

                /**
                 * Check if the generated angle is greater or lesser than previous angle by [RotationOffset]
                 * 
                 * Say the RotationOffset is [8] and the previous angle is 3, RLowerBoundary is [-16], RUpperBoundary is [16]
                 * then the angle generated should lie in range 
                 * 
                 * [RLowerBoundary, PreviousAngle - RotationOffset] U [PreviousAngle + RotationOffset, RUpperBoundary]
                 * or
                 * [-16, -5] U [8, 16]. 
                 * 
                 * Since it should be union of both ranges, if it lies in either of the ranges the condition should be satisfied.
                 * 
                 * The only exception being if the previous angle is 0. Then we can use any value
                 */
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

    /**
     * Generates random integer between RUpperBoundary and RLowerBoundary (Inclusive).
     * @return Integer between RUpperBoundary and RLowerBoundary (Inclusive)
     */
    private Integer getRandomAngle() {
        return random.nextInt(RUpperBoundary - RLowerBoundary) + RLowerBoundary;
    }

    /**
     * Check if provided value is between specified low and high bounds of range
     * 
     * @param low lower bound of range
     * @param high upper bound of range
     * @param x value to check
     * @return true if value is in range otherwise false
     */
    private Boolean inRange(int low, int high, int x) {
        return ((x - high) * (x - low) <= 0);
    }

}
