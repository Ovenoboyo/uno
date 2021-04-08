package com.unoapp.uno.ui;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Dialog;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.ArrayList;

import com.unoapp.uno.engine.GameController;
import com.unoapp.uno.engine.GameController.IGameController;
import com.unoapp.uno.engine.GameController.IGameController.continueDraw;
import com.unoapp.uno.models.Card;
import com.unoapp.uno.models.Player;
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

    private boolean nextPersonDraws = false;
    private boolean isDrawing = false;
    private boolean disabledExceptDraw2 = false;

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

    private void handlePreviousDraw2() {
        if (nextPersonDraws) {
            if (controller.getCurrentPlayer().getHand().stream().filter(card -> card.getNum() == Constants.DRAW2)
                    .findAny().orElse(null) != null) {
                disabledExceptDraw2 = true;

            } else {
                nextPersonDraws = false;
                controller.drawTwo(controller.getCurrentPlayer());
            }
        }
    }

    private void populateDialog(Card card, boolean isPlayable) {
        JDialog dialog = new JDialog();
        dialog.setUndecorated(true);

        JLabel label = new JLabel(controller.getCurrentPlayer().getName() + " You have drawn a");

        Container pane = dialog.getContentPane();
        dialog.setLayout(new BoxLayout(pane, BoxLayout.PAGE_AXIS));

        JPanel panel = new JPanel();
        panel.add(populateCard(card, true, false));

        JPanel buttonGroup = new JPanel();
        JButton play = new JButton("Play");
        JButton drawNext = new JButton("Draw");
        JButton keep = new JButton("Keep");

        play.addActionListener(arg0 -> {
            isDrawing = false;
            controller.playCard(card);
            dialog.dispose();
        });

        drawNext.addActionListener(arg0 -> {
            controller.drawCard(controller.getCurrentPlayer());
            dialog.dispose();
        });

        keep.addActionListener(arg0 -> {
            isDrawing = false;
            controller.pass(controller.getCurrentPlayer());
            dialog.dispose();
        });

        if (!isPlayable) {
            play.setEnabled(false);
            keep.setEnabled(false);
        } else {
            drawNext.setEnabled(false);
        }

        buttonGroup.add(keep);
        buttonGroup.add(play);
        buttonGroup.add(drawNext);

        dialog.add(label);
        dialog.add(panel);
        dialog.add(buttonGroup);
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
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

    private void populateDrawn2CardsDialog(ArrayList<Card> cards, continueDraw cDraw) {
        JDialog dialog = new JDialog();
        dialog.setUndecorated(true);

        JLabel label = new JLabel(
                controller.getCurrentPlayer().getName() + " You have drawn. Your turn will be skipped.");

        Container pane = dialog.getContentPane();
        dialog.setLayout(new BoxLayout(pane, BoxLayout.PAGE_AXIS));

        JPanel panel = new JPanel();
        for (Card c : cards)
            panel.add(populateCard(c, true, false));

        JPanel buttonGroup = new JPanel();
        JButton cont = new JButton("Continue");

        cont.addActionListener(arg0 -> {
            cDraw.continueTurn();
            dialog.dispose();
        });

        buttonGroup.add(cont);

        dialog.add(label);
        dialog.add(panel);
        dialog.add(buttonGroup);
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
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
                isDrawing = true;
                populateDialog(card, isPlayable);
                refreshUI();
            }

            @Override
            public void gotWinnerCallback(Player player) {
                System.out.println("Got winner");

            }

            @Override
            public void drawTwoCallback() {
                nextPersonDraws = true;
                System.out.println(nextPersonDraws);
            }

            @Override
            public void drawingTwoCallback(ArrayList<Card> cards, continueDraw cDraw) {
                populateDrawn2CardsDialog(cards, cDraw);
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
                    .add(populateCard(c, false, (isDrawing || (disabledExceptDraw2 && c.getNum() != Constants.DRAW2))));
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
    private CardDrawable populateCard(Card card, boolean isStatic, boolean isDisabled) {
        CardDrawable drawable;
        try {
            drawable = new CardDrawable(card.getColor(), card.getNum(), isDisabled,
                    (isStatic || isDisabled) ? null : () -> {
                        controller.playCard(card);
                        if (disabledExceptDraw2)
                            disabledExceptDraw2 = false;
                    });
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return drawable;
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
