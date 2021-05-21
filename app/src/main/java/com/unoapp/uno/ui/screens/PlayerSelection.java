package com.unoapp.uno.ui.screens;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.unoapp.uno.UNO;
import com.unoapp.uno.UNO.ScreenObject;
import com.unoapp.uno.UNO.ScreenObject.GameData;
import com.unoapp.uno.abstracts.MouseClickListener;
import com.unoapp.uno.models.PlayerInfo;
import com.unoapp.uno.ui.components.BackButton;
import com.unoapp.uno.ui.components.GenericMenuScreen;
import com.unoapp.uno.ui.components.PersonIcon;
import com.unoapp.uno.ui.components.RoundedRectangle;
import com.unoapp.uno.ui.components.ScaledBackground;
import com.unoapp.uno.ui.components.SmoothText;
import com.unoapp.uno.ui.components.StartLabel;
import com.unoapp.uno.ui.components.TransparentPanel;
import com.unoapp.uno.ui.components.TriangleArrow;
import com.unoapp.uno.utils.Assets;
import com.unoapp.uno.utils.Colors;
import com.unoapp.uno.utils.Constants;
import com.unoapp.uno.utils.Constants.Screens;
import com.unoapp.uno.utils.Fonts;

/**
 * Screen where players will be choosen
 */
public class PlayerSelection extends GenericMenuScreen {
    private final GenericMenuScreen context = this;

    private final class CreateUserDialog {
        private JPanel panel;
        JLabel label;
        private JTextField nameField;
        private Component parent;

        CreateUserDialog(Component parent) {
            this.parent = parent;
            init();
        }

        private void init() {
            label = new JLabel();
            label.setForeground(Color.RED);
            nameField = new JTextField();
            panel = new JPanel(new GridLayout(0, 1));
            panel.add(new JLabel("Player name:"));
            panel.add(nameField);
            panel.add(label);
        }

        private Boolean validate() {
            return (nameField.getText().length() <= 15 && nameField.getText().length() >= 4);
        }

        private void showDialog(String errorString) {
            if (errorString != null) {
                label.setText(errorString);
            }

            int result = JOptionPane.showOptionDialog(parent, panel, "Create new player", JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE, null, new String[] { "Create", "Cancel" }, "Create");
            if (result == JOptionPane.OK_OPTION) {
                label.setText("");
                if (validate()) {
                    try {
                        Constants.dbConnection.createPlayer(nameField.getText());
                        getPlayers();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } else {
                    showDialog("Character length should be less than 15 and and greater than 4!");
                }
            }
        }
    }

    private final Integer CREATE_NEW_BUTTON = -1;

    private CreateUserDialog dialog;

    private ArrayList<PlayerInfo> players = null;
    private Integer[] frameIndices = new Integer[] { -1, 0, 1, 2 };

    public PlayerSelection() {
        super();
        getPlayers();
        init();
    }

    /**
     * Get all players from database
     */
    private void getPlayers() {
        players = Constants.dbConnection.getAllPlayers();
    }

    /**
     * Get players selected by user
     * @return ArrayList of players selected by users
     * @throws InvalidPlayersException when less than 2 players are selected
     */
    private ArrayList<PlayerInfo> getActivePlayers() throws InvalidPlayersException {
        ArrayList<PlayerInfo> players = new ArrayList<>();
        for (Integer i : frameIndices) {
            if (i > -1)
                players.add(this.players.get(i));
        }
        if (players.size() < 2) {
            throw new InvalidPlayersException();
        }
        return players;
    }

    /**
     * Check if any of the selected players are duplicates
     * 
     * @throws InvalidPlayersException when one or more players are duplicate
     */
    private void validatePlayers() throws InvalidPlayersException {
        Set<Integer> lump = new HashSet<Integer>();
        for (Integer i : frameIndices) {
            if (i != -1 && lump.contains(i))
                throw new InvalidPlayersException();
            lump.add(i);
        }
    }

    private void init() {
        dialog = new CreateUserDialog(this);

        ScaledBackground background = new ScaledBackground(Assets.getAsset("mainBg.png"), xSize, ySize,
                new BorderLayout());
        getContentPane().add(background);

        TransparentPanel mainPanel = new TransparentPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        mainPanel.setPreferredSize(new Dimension(MAX_COMPONENT_X, MAX_COMPONENT_Y));

        TransparentPanel buttonPanel = new TransparentPanel();

        buttonPanel.add(createPanel(Colors.getColor(Constants.Color.BLUE, false), 0));
        buttonPanel.add(Box.createHorizontalStrut(60));
        buttonPanel.add(createPanel(Colors.getColor(Constants.Color.GREEN, false), 1));
        buttonPanel.add(Box.createHorizontalStrut(60));
        buttonPanel.add(createPanel(Colors.getColor(Constants.Color.YELLOW, false), 2));
        buttonPanel.add(Box.createHorizontalStrut(60));
        buttonPanel.add(createPanel(Colors.getColor(Constants.Color.RED, false), 3));

        mainPanel.add(Box.createVerticalGlue());
        mainPanel.add(Box.createVerticalGlue());
        mainPanel.add(Box.createVerticalGlue());

        mainPanel.add(buttonPanel);
        mainPanel.add(Box.createVerticalGlue());

        background.add(mainPanel, BorderLayout.CENTER);

        TransparentPanel southPanel = new TransparentPanel(new FlowLayout(FlowLayout.RIGHT));
        TransparentPanel startButton = new TransparentPanel(new FlowLayout(FlowLayout.LEFT));
        startButton.add(Box.createRigidArea(new Dimension(0, 120)));
        startButton.add(new StartLabel());

        startButton.addMouseListener(new MouseClickListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    validatePlayers();
                    UNO.changeScreen(new ScreenObject(context, Screens.GAME, new GameData(getActivePlayers())));
                } catch (InvalidPlayersException e1) {
                    JOptionPane.showMessageDialog(context, e1.getMessage(), "Error!", JOptionPane.OK_OPTION);
                }
            }
        });

        TransparentPanel northPanel = new TransparentPanel(new FlowLayout(FlowLayout.LEFT));
        northPanel.add(Box.createRigidArea(new Dimension(30, 120)));
        BackButton backButton = new BackButton(0.8, Fonts.getProximaInstance(36, true));
        backButton.addMouseListener(new MouseClickListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                UNO.changeScreen(new ScreenObject(context, Screens.TITLE_SCREEN));
            }
        });
        northPanel.add(backButton);

        southPanel.add(startButton);
        southPanel.add(Box.createRigidArea(new Dimension(30, 100)));
        background.add(southPanel, BorderLayout.SOUTH);
        background.add(northPanel, BorderLayout.NORTH);

        pack();
    }

    /**
     * Increments index in circular manner
     * @param index index of frame whose index is to be incremented
     */
    private void incrementIndex(Integer index) {
        frameIndices[index]++;
        if (frameIndices[index] >= players.size()) {
            frameIndices[index] = CREATE_NEW_BUTTON;
        }
    }

    /**
     * Decrements index in circular manner
     * @param index index of frame whose index is to be decremented
     */
    private void decrementIndex(Integer index) {
        frameIndices[index]--;
        if (frameIndices[index] < CREATE_NEW_BUTTON) {
            frameIndices[index] = players.size() - 1;
        }
    }

    /**
     * Create panel with name of player
     * @param color
     * @param index index of panel (usually out of 4)
     * @return
     */
    private TransparentPanel createPanel(java.awt.Color color, Integer index) {
        incrementIndex(index);

        int rectSize = (MAX_COMPONENT_X / 4) - (60);

        TransparentPanel mainPanel = new TransparentPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));

        TransparentPanel textHolder = new TransparentPanel();
        TransparentPanel textPanel = new TransparentPanel(new BorderLayout(), new Dimension(rectSize - 100, 32));
        TriangleArrow arrowL = new TriangleArrow(true, color);
        TriangleArrow arrowR = new TriangleArrow(false, color);
        SmoothText text = new SmoothText(
                frameIndices[index] >= 0 ? players.get(frameIndices[index]).getName() : "Create New",
                Fonts.getProximaInstance(28, true));

        TransparentPanel playerNameHolder = new TransparentPanel();
        playerNameHolder.add(text);

        arrowR.addMouseListener(new IArrowMouseListener(text, index, true));
        arrowL.addMouseListener(new IArrowMouseListener(text, index, false));
        text.addMouseListener(new ITextMouseClickListener(index));

        textPanel.add(arrowL, BorderLayout.WEST);
        textPanel.add(playerNameHolder, BorderLayout.CENTER);
        textPanel.add(arrowR, BorderLayout.EAST);

        textHolder.add(textPanel);

        RoundedRectangle playerHolder = new RoundedRectangle(rectSize, rectSize, 80);
        playerHolder.setLayout(new BoxLayout(playerHolder, BoxLayout.PAGE_AXIS));

        TransparentPanel label = new TransparentPanel();
        label.add(new PersonIcon(color));

        playerHolder.add(Box.createVerticalGlue());
        playerHolder.add(Box.createHorizontalStrut(150 / 2));
        playerHolder.add(label);
        playerHolder.add(textHolder);
        playerHolder.add(Box.createVerticalGlue());

        mainPanel.add(Box.createVerticalStrut(15));
        mainPanel.add(playerHolder);

        return mainPanel;
    }

    /**
     * Mouse click listener for player name text
     */
    private final class ITextMouseClickListener extends MouseClickListener {
        private final Integer index;

        private ITextMouseClickListener(Integer index) {
            this.index = index;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if (frameIndices[index] == CREATE_NEW_BUTTON) {
                if (dialog != null)
                    dialog.showDialog(null);
            }
        }
    }

    /**
     * Mouse click listener for arrow
     */
    private final class IArrowMouseListener extends MouseClickListener {
        private final SmoothText text;
        private final Integer index;
        private final Boolean increment;

        private IArrowMouseListener(SmoothText text, Integer index, boolean increment) {
            this.text = text;
            this.index = index;
            this.increment = increment;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if (increment)
                incrementIndex(index);
            else
                decrementIndex(index);

            if (frameIndices[index] == -1) {
                text.setText("Create New");
            } else {
                text.setText(players.get(frameIndices[index]).getName());
            }
        }
    }

    public class InvalidPlayersException extends Exception {
        InvalidPlayersException() {
            super("Players selected are invalid");
        }
    }
}
