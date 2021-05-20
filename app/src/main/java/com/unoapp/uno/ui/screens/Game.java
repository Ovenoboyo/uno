package com.unoapp.uno.ui.screens;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.unoapp.uno.UNO;
import com.unoapp.uno.UNO.ScreenObject;
import com.unoapp.uno.abstracts.MouseClickListener;
import com.unoapp.uno.engine.GameController;
import com.unoapp.uno.engine.GameController.IGameController;
import com.unoapp.uno.engine.GameController.IGameController.continueDraw;
import com.unoapp.uno.models.Card;
import com.unoapp.uno.models.Player;
import com.unoapp.uno.models.PlayerInfo;
import com.unoapp.uno.ui.components.CardPanel;
import com.unoapp.uno.ui.components.ColorSelectionDialog;
import com.unoapp.uno.ui.components.CustomCardDialog;
import com.unoapp.uno.ui.components.GenericMenuScreen;
import com.unoapp.uno.ui.components.LastPlayedComponent;
import com.unoapp.uno.ui.components.PlayerOrder;
import com.unoapp.uno.ui.components.ScaledBackground;
import com.unoapp.uno.ui.components.TransparentPanel;
import com.unoapp.uno.ui.drawables.Deck;
import com.unoapp.uno.utils.Assets;
import com.unoapp.uno.utils.Constants;
import com.unoapp.uno.utils.Constants.Screens;

/**
 * Game Screen
 */
public class Game extends GenericMenuScreen {
    private JPanel activePlayerCardPanel;
    private JPanel activePlayerDetails;
    private JPanel tablePanel;
    private TransparentPanel unoPanel;
    private GameController controller;

    private CustomCardDialog customDialog;

    private boolean nextPersonDrawsTwo = false;
    private boolean nextPersonDrawsFour = false;
    private boolean isDrawing = false;
    private boolean disabledExceptDraw2 = false;
    private boolean disabledExceptDraw4 = false;

    private GenericMenuScreen context = this;

    /**
     * Default constructor
     * 
     */
    public Game(ArrayList<PlayerInfo> players) {
        customDialog = new CustomCardDialog(this);
        init(players);
    }

    private ArrayList<Player> populatePlayers(ArrayList<PlayerInfo> playerInfos) {
        ArrayList<Player> players = new ArrayList<>();
        for (PlayerInfo p : playerInfos) {
            players.add(Player.parseFromPlayerInfo(p));
        }
        return players;
    }

    /**
     * Initialize game screen
     */
    private void init(ArrayList<PlayerInfo> players) {
        initializeController(populatePlayers(players));

        generateUIComponents();

        // Initialize game controller after UI has been generated
        controller.startGameLoop();
    }

    /**
     * Handles draw 2
     */
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

    /**
     * Handles draw 4
     */
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

    /**
     * Creates and show dialog to draw cards
     * @param card card that has been drawn
     * @param isPlayable true if card drawn is playable otherwise false
     */
    private void populateDrawDialog(Card card, boolean isPlayable) {
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
        ScaledBackground background = new ScaledBackground(Assets.getAsset("bg.png"), xSize, ySize);

        activePlayerCardPanel = new TransparentPanel();

        JScrollPane scrollCards = new JScrollPane(activePlayerCardPanel);
        scrollCards.setPreferredSize(new Dimension(MAX_COMPONENT_X, 267 + 50));
        scrollCards.setBorder(null);
        scrollCards.setOpaque(false);

        activePlayerDetails = new TransparentPanel();
        activePlayerDetails.setLayout(new FlowLayout(FlowLayout.LEFT));

        TransparentPanel southPanel = new TransparentPanel();
        southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.PAGE_AXIS));

        JButton simulateWinner = new JButton("Simulate winner");
        simulateWinner.addMouseListener(new MouseClickListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                controller.simulateWinner();
            }
        });

        southPanel.add(activePlayerDetails);
        southPanel.add(scrollCards);

        unoPanel = new TransparentPanel();
        var unoIcon = new ScaledBackground(Assets.getAsset("playIcon.png"), 178, 124);
        unoIcon.addMouseListener(new MouseClickListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                controller.getCurrentPlayer().setUno(true);
                unoIcon.setVisible(false);
            }
        });
        unoPanel.add(unoIcon);
        unoPanel.setVisible(false);

        tablePanel = new TransparentPanel();
        tablePanel.add(generateDeckButton());
        tablePanel.add(unoPanel);
        // tablePanel.add(simulateWinner);

        tablePanel.setPreferredSize(new Dimension(MAX_COMPONENT_X, 267 + 100));

        TransparentPanel componentHolder = new TransparentPanel(new BorderLayout());
        componentHolder.setPreferredSize(new Dimension(MAX_COMPONENT_X, MAX_COMPONENT_Y));
        componentHolder.add(southPanel, BorderLayout.SOUTH);
        componentHolder.add(tablePanel, BorderLayout.NORTH);

        background.add(componentHolder);

        getContentPane().add(background);
    }

    /**
     * Creates dialog for actions on drawn cards
     * Allows player to play drawn card, 
     * Draw another card (if current card is unplayable), 
     * Keep drawn card if it is playable
     * 
     * @param cards
     * @param cDraw
     * @throws IOException
     */
    private void populateDrawnCardsDialog(Card[] cards, continueDraw cDraw) throws IOException {
        if (cDraw != null)
            this.isDrawing = true;

        customDialog.clean();
        customDialog.addCards(cards);
        customDialog.addButton("Continue", arg0 -> {
            this.isDrawing = false;
            if (cDraw != null)
                cDraw.continueTurn();
        }, true);

        customDialog.showDialog();
    }

    /**
     * Shows color change wheel if player has played a valid special card
     * 
     * @param card card that has been played
     */
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
    private void initializeController(ArrayList<Player> players) {
        this.controller = new GameController(players, new IGameController() {
            @Override
            public void turnEndCallback() {
                refreshUI();
            }

            @Override
            public void drawCardCallback(Card card, boolean isPlayable) {
                populateDrawDialog(card, isPlayable);
            }

            @Override
            public void gotWinnerCallback(Player player, ArrayList<Player> winner) {
                UNO.changeScreen(
                        new ScreenObject(context, Screens.RESULTS, new ScreenObject.ResultsData(winner, player)));
            }

            @Override
            public void drawTwoCallback() {
                nextPersonDrawsTwo = true;
            }

            @Override
            public void drawFourCallback() {
                nextPersonDrawsFour = true;
            }

            @Override
            public void drawingCallback(Card[] cards, continueDraw cDraw) {
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
    private void refreshUI() {
        if (tablePanel.getComponentCount() == 3)
            tablePanel.remove(1);
        generateLastCard();

        activePlayerCardPanel.removeAll();
        handlePreviousDraw2();
        handlePreviousDraw4();
        generatePlayerCards();

        activePlayerDetails.removeAll();
        generatePlayerOrder();

        if (controller.getCurrentPlayer().getHand().size() == 1 && controller.playerCanPlay()) {
            unoPanel.setVisible(true);
        } else {
            unoPanel.setVisible(false);
        }

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

    /**
     * Generates JLabel to show all players and order of turns
     */
    private void generatePlayerOrder() {
        PlayerOrder label = new PlayerOrder(controller.getPlayers(), controller.getCurrentPlayer(),
                controller.getLastPlayedCard().get(0).getColor(), controller.isReversed());
        activePlayerDetails.add(label);
    }

    /**
     * Generate component to show last played card
     * @throws IOException
     */
    private void generateLastCard() {
        tablePanel.add(new LastPlayedComponent(controller.getLastPlayedCard()), 1);
    }

    /**
     * Generates Button to draw card
     * 
     * @return JLabel with deck icon
     */
    private JLabel generateDeckButton() {
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

        return label;
    }

    /**
     * Generates a component with details of card
     * 
     * @param card card of which button is to be generated
     * @return Generated compoenent (Currently JButton)
     */
    private CardPanel populateCard(Card card, boolean isStatic, boolean isDisabled) {
        return new CardPanel(card, isDisabled, (isStatic || isDisabled) ? null : () -> onCardClick(card));
    }

    /**
     * Handles Card clicks
     * @param card card that has been played
     */
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

    @Override
    public void dispose() {
        super.dispose();
        this.controller = null;
        this.customDialog = null;
    }
}
