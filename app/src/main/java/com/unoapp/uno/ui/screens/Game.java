package com.unoapp.uno.ui.screens;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.ArrayList;

import com.unoapp.uno.engine.GameController;
import com.unoapp.uno.engine.GameController.IGameController;
import com.unoapp.uno.engine.GameController.IGameController.continueDraw;
import com.unoapp.uno.models.Card;
import com.unoapp.uno.models.Player;
import com.unoapp.uno.ui.components.CardLabel;
import com.unoapp.uno.ui.components.ColorSelectionDialog;
import com.unoapp.uno.ui.components.CustomCardDialog;
import com.unoapp.uno.ui.components.LastPlayedComponent;
import com.unoapp.uno.ui.components.SmoothText;
import com.unoapp.uno.utils.Constants;

/**
 * Game Screen
 */
public class Game {
    private JFrame frame;
    private JPanel activePlayerCardPanel;
    private JPanel activePlayerDetails;
    private JPanel tablePanel;
    private GameController controller;

    private CustomCardDialog customDialog;

    private boolean nextPersonDrawsTwo = false;
    private boolean nextPersonDrawsFour = false;
    private boolean isDrawing = false;
    private boolean disabledExceptDraw2 = false;
    private boolean disabledExceptDraw4 = false;

    /**
     * Default constructor
     * 
     * @throws IOException
     */
    public Game() throws IOException {
        customDialog = new CustomCardDialog();

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

    private void handlePreviousDraw2() {
        if (nextPersonDrawsTwo) {
            if (controller.getCurrentPlayer().getHand().stream().filter(card -> card.getNum() == Constants.DRAW2)
                    .findAny().orElse(null) != null) {
                disabledExceptDraw2 = true;

            } else {
                nextPersonDrawsTwo = false;
                controller.drawTwo(controller.getCurrentPlayer());
            }
        }
    }

    private void handlePreviousDraw4() {
        if (nextPersonDrawsFour) {
            if (controller.getCurrentPlayer().getHand().stream().filter(card -> card.getNum() == Constants.DRAWFOUR)
                    .findAny().orElse(null) != null) {
                disabledExceptDraw4 = true;

            } else {
                nextPersonDrawsFour = false;
                controller.drawFour(controller.getCurrentPlayer());
            }
        }
    }

    private void populateDrawDialog(Card card, boolean isPlayable) throws IOException {
        isDrawing = true;
        refreshUI();

        customDialog.clean();

        customDialog.addCards(card);
        customDialog.addButton("Play", arg0 -> {
            isDrawing = false;
            onCardClick(card);
        }, isPlayable);

        customDialog.addButton("Draw", arg0 -> controller.drawCard(controller.getCurrentPlayer()), !isPlayable);
        customDialog.addButton("Keep", arg0 -> {
            isDrawing = false;
            controller.pass(controller.getCurrentPlayer());
        }, isPlayable);

        customDialog.showDialog();
    }

    /**
     * Generate all the UI required for game screen
     */
    private void generateUIComponents() {
        frame = new JFrame();

        // WSL Workaround
        Toolkit tk = Toolkit.getDefaultToolkit();
        int xSize = ((int) tk.getScreenSize().getWidth());
        int ySize = ((int) tk.getScreenSize().getHeight());
        frame.setPreferredSize(new Dimension(xSize, ySize));

        GraphicsEnvironment graphics = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice device = graphics.getDefaultScreenDevice();

        // Should work on normal

        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel nnPanel = new JPanel();

        activePlayerCardPanel = new JPanel();
        // activePlayerCardPanel.setPreferredSize(new Dimension(500, 267 + 50));
        activePlayerCardPanel.setLayout(new FlowLayout());

        JScrollPane scrollCards = new JScrollPane(activePlayerCardPanel);
        scrollCards.setPreferredSize(new Dimension(xSize - 120, 267 + 50));
        scrollCards.setBorder(null);

        activePlayerDetails = new JPanel();
        activePlayerDetails.setLayout(new FlowLayout(FlowLayout.LEFT));

        JPanel nPanel = new JPanel();
        nPanel.setLayout(new BoxLayout(nPanel, BoxLayout.PAGE_AXIS));

        nPanel.add(activePlayerDetails);
        nPanel.add(scrollCards);
        nPanel.add(Box.createVerticalStrut(60));

        nnPanel.add(nPanel);

        JPanel nnnPanel = new JPanel();
        nnnPanel.setLayout(new BoxLayout(nnnPanel, BoxLayout.PAGE_AXIS));

        tablePanel = new JPanel();
        tablePanel.setLayout(new FlowLayout());
        tablePanel.setPreferredSize(new Dimension(xSize, 267 + 100));

        nnnPanel.add(Box.createVerticalStrut(60));
        nnnPanel.add(tablePanel);

        panel.add(nnnPanel, BorderLayout.NORTH);
        panel.add(nnPanel, BorderLayout.SOUTH);
        // panel.add(Box.createVerticalBox());

        Container pane = frame.getContentPane();
        JScrollPane globalScroll = new JScrollPane(panel);
        // frame.setLayout(new BoxLayout(pane, BoxLayout.PAGE_AXIS));
        pane.add(globalScroll);

        // frame.pack();
        frame.setUndecorated(true);
        frame.setResizable(false);

        device.setFullScreenWindow(frame);
    }

    private void populateDrawnCardsDialog(Card[] cards, continueDraw cDraw) throws IOException {
        this.isDrawing = true;

        customDialog.clean();
        customDialog.addCards(cards);
        customDialog.addButton("Continue", arg0 -> {
            this.isDrawing = false;
            cDraw.continueTurn();
        }, true);

        customDialog.showDialog();
    }

    private void populateColorChangeUI(Card card) {
        isDrawing = true;
        try {
            refreshUI();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ColorSelectionDialog dialog = new ColorSelectionDialog(color -> {
            isDrawing = false;
            card.setChangedColor(color);
            controller.playCard(card);
        });
        dialog.showDialog();
    }

    /**
     * Initialize controller to handle game loop
     */
    private void initializeController() {
        this.controller = new GameController(new IGameController() {
            @Override
            public void turnEndCallback() {
                try {
                    refreshUI();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void drawCardCallback(Card card, boolean isPlayable) {
                try {
                    populateDrawDialog(card, isPlayable);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void gotWinnerCallback(Player player) {
                System.out.println("Got winner");
            }

            @Override
            public void drawTwoCallback() {
                nextPersonDrawsTwo = true;
            }

            @Override
            public void drawingTwoCallback(Card[] cards, continueDraw cDraw) {
                try {
                    populateDrawnCardsDialog(cards, cDraw);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void drawFourCallback() {
                nextPersonDrawsFour = true;
            }

            @Override
            public void drawingFourCallback(Card[] cards, continueDraw cDraw) {
                try {
                    populateDrawnCardsDialog(cards, cDraw);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    /**
     * Update all the UI components based off changed values. Called after every
     * turn
     * @throws IOException
     */
    private void refreshUI() throws IOException {
        tablePanel.removeAll();
        generateLastCard();
        generateDeckButton();
        tablePanel.revalidate();

        activePlayerCardPanel.removeAll();
        handlePreviousDraw2();
        handlePreviousDraw4();
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
            activePlayerCardPanel
                    .add(populateCard(c, false, isDrawing || (disabledExceptDraw2 && c.getNum() != Constants.DRAW2)
                            || (disabledExceptDraw4 && c.getNum() != Constants.DRAWFOUR)));
    }

    private void generatePlayerDetails() {
        SmoothText label = new SmoothText(controller.getPlayers(), controller.getCurrentPlayer(),
                controller.getLastPlayedCard().get(0).getColor());
        activePlayerDetails.add(label);
    }

    /**
     * Generate component to show last played card
     * @throws IOException
     */
    private void generateLastCard() throws IOException {
        // tablePanel.add(populateCard(controller.getLastPlayedCard(), true, false));
        tablePanel.add(new LastPlayedComponent(controller.getLastPlayedCard()));
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
    private CardLabel populateCard(Card card, boolean isStatic, boolean isDisabled) {
        CardLabel drawable;
        try {
            drawable = new CardLabel(card, isDisabled, (isStatic || isDisabled) ? null : () -> onCardClick(card));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return drawable;
    }

    private void onCardClick(Card card) {
        if (card.isSpecial()) {
            populateColorChangeUI(card);
        } else {
            controller.playCard(card);
        }
        if (disabledExceptDraw2)
            disabledExceptDraw2 = false;
        if (disabledExceptDraw4)
            disabledExceptDraw4 = false;
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
