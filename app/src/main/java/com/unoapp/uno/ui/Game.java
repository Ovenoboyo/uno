package com.unoapp.uno.ui;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Container;
import java.awt.FlowLayout;
import java.util.ArrayList;

import com.unoapp.uno.engine.GameController;
import com.unoapp.uno.models.Card;

/**
 * Game Screen
 */
public class Game {
    JFrame frame = new JFrame();
    JPanel activePlayerCardPanel;
    JPanel lastCardPanel;
    GameController controller;

    public Game() {
        init();
    }

    private void init() {
        generateUIComponents();

        // Initialize game controller after UI has been generated
        initializeController();
        controller.startGameLoop();

    }

    private void generateUIComponents() {
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        Container pane = frame.getContentPane();
        frame.setLayout(new BoxLayout(pane, BoxLayout.PAGE_AXIS));

        activePlayerCardPanel = new JPanel();
        activePlayerCardPanel.setLayout(new FlowLayout());

        lastCardPanel = new JPanel();
        lastCardPanel.setLayout(new FlowLayout());

        pane.add(lastCardPanel);
        pane.add(activePlayerCardPanel);
        frame.pack();
    }

    private void initializeController() {
        this.controller = new GameController(() -> refreshUI());
    }

    private void refreshUI() {
        lastCardPanel.removeAll();
        generateLastCard();
        lastCardPanel.revalidate();

        activePlayerCardPanel.removeAll();
        generatePlayerCards();
        activePlayerCardPanel.revalidate();

        frame.revalidate();
        frame.repaint();
        frame.pack();
    }

    private void generatePlayerCards() {
        ArrayList<Card> cards = controller.getCurrentPlayer().getHand();

        for (Card c : cards)
            activePlayerCardPanel.add(populateCard(c));
    }

    private void generateLastCard() {
        JButton button = new JButton(controller.getLastPlayedCard().toString());
        lastCardPanel.add(button);

    }

    private JButton populateCard(Card card) {
        JButton button = new JButton(card.toString());
        button.addActionListener(arg0 -> controller.playCard(card));
        return button;
    }

    public void setVisible(Boolean value) {
        this.frame.setVisible(value);
    }
}
