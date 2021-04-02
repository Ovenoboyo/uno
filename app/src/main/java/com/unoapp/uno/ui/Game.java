package com.unoapp.uno.ui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.FlowLayout;
import java.util.ArrayList;

import com.unoapp.uno.engine.GameController;
import com.unoapp.uno.models.Card;

/**
 * Game Screen
 */
public class Game {
    JFrame frame = new JFrame();
    JPanel activeCardPanel;
    GameController controller = new GameController();

    public Game() {
        init();
    }

    private void init() {
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new FlowLayout());

        activeCardPanel = new JPanel();
        activeCardPanel.setLayout(new FlowLayout());

        generatePlayerCards();
        frame.add(activeCardPanel);
        frame.pack();
    }

    public void generatePlayerCards() {
        ArrayList<Card> cards = controller.getCurrentPlayer().getHand();

        for (Card c : cards)
            activeCardPanel.add(populateCard(c.toString()));
    }

    public JButton populateCard(String cardTitle) {
        return new JButton(cardTitle);
    }

    public void setVisible(Boolean value) {
        this.frame.setVisible(value);
    }
}
