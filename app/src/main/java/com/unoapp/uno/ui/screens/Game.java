package com.unoapp.uno.ui.screens;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
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

        // Should work on normal
        // frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
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
        refreshUI();

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
                refreshUI();
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
     */
    private void refreshUI() {
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
        activePlayerDetails.add(new JLabel(controller.getCurrentPlayer().getName()));
    }

    /**
     * Generate component to show last played card
     */
    private void generateLastCard() {
        tablePanel.add(populateCard(controller.getLastPlayedCard(), true, false));
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
            drawable = new CardLabel(card.getColor(), card.getNum(), isDisabled,
                    (isStatic || isDisabled) ? null : () -> onCardClick(card));
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
