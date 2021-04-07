package com.unoapp.uno.ui;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;

import com.unoapp.uno.engine.GameController;
import com.unoapp.uno.engine.GameController.IGameController;
import com.unoapp.uno.models.Card;
import com.unoapp.uno.models.Player;

/**
 * Game Screen
 */
public class Game {
    JFrame frame;
    JPanel activePlayerCardPanel;
    JPanel activePlayerDetails;
    JPanel tablePanel;
    GameController controller;

    /**
     * Default constructor
     */
    public Game() {
        init();
    }

    /**
     * Initialize game screen
     */
    private void init() {
        generateUIComponents();

        // Initialize game controller after UI has been generated
        initializeController();
        controller.startGameLoop();

    }

    /**
     * Generate all the UI required for game screen
     */
    private void generateUIComponents() {
        frame = new JFrame();
        frame.setPreferredSize(new Dimension(640, 480));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        Container pane = frame.getContentPane();
        frame.setLayout(new BoxLayout(pane, BoxLayout.PAGE_AXIS));

        activePlayerCardPanel = new JPanel();
        activePlayerCardPanel.setLayout(new FlowLayout());

        activePlayerDetails = new JPanel();
        activePlayerDetails.setLayout(new FlowLayout());

        tablePanel = new JPanel();
        tablePanel.setLayout(new FlowLayout());

        pane.add(tablePanel);
        pane.add(activePlayerCardPanel);
        pane.add(activePlayerDetails);
        frame.pack();
    }

    /**
     * Initialize controller to handle game loop
     */
    private void initializeController() {
        this.controller = new GameController(new IGameController() {
            @Override
            public void turnEndCallback() {
                refreshUI();
            }

            @Override
            public void drawCardCallback() {
                refreshUI();
            }

            @Override
            public void gotWinnerCallback(Player player) {
                System.out.println("Got winner");

            }
        });
    }

    /**
     * Update all the UI components based off changed values. Called after every
     * turn
     */
    private void refreshUI() {
        tablePanel.removeAll();
        generateLastCard();
        generateDeckButton();
        tablePanel.revalidate();

        activePlayerCardPanel.removeAll();
        generatePlayerCards();
        activePlayerCardPanel.revalidate();

        activePlayerDetails.removeAll();
        generatePlayerDetails();
        activePlayerDetails.revalidate();

        frame.revalidate();
        frame.repaint();
    }

    /**
     * Generate buttons based off players allocated cards
     */
    private void generatePlayerCards() {
        ArrayList<Card> cards = controller.getCurrentPlayer().getHand();

        for (Card c : cards)
            activePlayerCardPanel.add(populateCard(c));
    }

    private void generatePlayerDetails() {
        activePlayerDetails.add(new JLabel(controller.getCurrentPlayer().getName()));
    }

    /**
     * Generate component to show last played card
     */
    private void generateLastCard() {
        // TODO: Replace button with something more non-clickable
        JButton button = new JButton(controller.getLastPlayedCard().toString());
        tablePanel.add(button);
    }

    private void generateDeckButton() {
        JButton button = new JButton("Draw card");
        button.addActionListener(arg0 -> controller.drawCard(controller.getCurrentPlayer()));
        tablePanel.add(button);
    }

    /**
     * Generates a component with details of card
     * 
     * @param card card of which button is to be generated
     * @return Generated compoenent (Currently JButton)
     */
    private JButton populateCard(Card card) {
        JButton button = new JButton(card.toString());
        button.addActionListener(arg0 -> controller.playCard(card));
        return button;
    }

    /**
     * Set visibility of game screen
     * 
     * @param value
     */
    public void setVisible(Boolean value) {
        this.frame.setVisible(value);
    }
}
