package com.unoapp.uno.ui.screens;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
import com.unoapp.uno.ui.components.ScaledBackground;
import com.unoapp.uno.ui.components.SmoothText;
import com.unoapp.uno.ui.components.TransparentPanel;
import com.unoapp.uno.ui.drawables.Deck;
import com.unoapp.uno.utils.Constants;

/**
 * Game Screen
 */
public class Game extends JFrame {
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
        // WSL Workaround
        Toolkit tk = Toolkit.getDefaultToolkit();
        int xSize = ((int) tk.getScreenSize().getWidth());
        int ySize = ((int) tk.getScreenSize().getHeight());
        setPreferredSize(new Dimension(xSize, ySize));

        final int MAX_COMPONENT_X = xSize - 120;
        final int MAX_COMPONENT_Y = ySize - 60;

        GraphicsEnvironment graphics = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice device = graphics.getDefaultScreenDevice();

        // Should work on normal
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setUndecorated(true);
        setResizable(false);

        device.setFullScreenWindow(this);

        activePlayerCardPanel = new TransparentPanel();

        JScrollPane scrollCards = new JScrollPane(activePlayerCardPanel);
        scrollCards.setPreferredSize(new Dimension(MAX_COMPONENT_X, 267 + 50));
        scrollCards.setBorder(null);

        activePlayerDetails = new TransparentPanel();
        activePlayerDetails.setLayout(new FlowLayout(FlowLayout.LEFT));

        TransparentPanel southPanel = new TransparentPanel();
        southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.PAGE_AXIS));

        southPanel.add(activePlayerDetails);
        southPanel.add(scrollCards);

        tablePanel = new TransparentPanel();
        tablePanel.setLayout(new FlowLayout());
        tablePanel.setPreferredSize(new Dimension(MAX_COMPONENT_X, 267 + 100));

        TransparentPanel componentHolder = new TransparentPanel(new BorderLayout());
        componentHolder.setPreferredSize(new Dimension(MAX_COMPONENT_X, MAX_COMPONENT_Y));
        componentHolder.add(southPanel, BorderLayout.SOUTH);
        componentHolder.add(tablePanel, BorderLayout.NORTH);

        ScaledBackground background = new ScaledBackground("assets/bg.png", xSize, ySize, new FlowLayout());

        background.add(componentHolder);

        getContentPane().add(background);
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
        generateDeckButton();
        generateLastCard();
        tablePanel.revalidate();

        activePlayerCardPanel.removeAll();
        handlePreviousDraw2();
        handlePreviousDraw4();
        generatePlayerCards();
        activePlayerCardPanel.revalidate();

        activePlayerDetails.removeAll();
        generatePlayerDetails();
        activePlayerDetails.revalidate();

        revalidate();
        repaint();
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
        Deck deckIcon = new Deck(200, 280);
        JLabel label = new JLabel(deckIcon);
        label.setPreferredSize(new Dimension(200, 280));
        label.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                controller.drawCard(controller.getCurrentPlayer());
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                label.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                label.setBorder(null);
            }
        });

        tablePanel.add(label);
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
}
