package com.unoapp.uno.ui.screens;

import java.awt.BorderLayout;
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

import com.unoapp.uno.Application;
import com.unoapp.uno.Application.ScreenObject;
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
import com.unoapp.uno.utils.Constants;
import com.unoapp.uno.utils.Constants.Screens;

/**
 * Screen where players will be choosen
 */
public class PlayerSelection extends GenericMenuScreen {
    private final GenericMenuScreen context = this;

    private final class CreateUserDialog {
        private JPanel panel;
        private JTextField nameField;
        private Component parent;

        CreateUserDialog(Component parent) {
            this.parent = parent;
            init();
        }

        private void init() {
            nameField = new JTextField();
            panel = new JPanel(new GridLayout(0, 1));
            panel.add(new JLabel("Player name:"));
            panel.add(nameField);
        }

        private void showDialog() {
            int result = JOptionPane.showOptionDialog(parent, panel, "Create new player", JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE, null, new String[] { "Create", "Cancel" }, "Create");
            if (result == JOptionPane.OK_OPTION) {
                try {
                    Constants.dbConnection.createPlayer(nameField.getText());
                    getPlayers();
                } catch (SQLException e) {
                    e.printStackTrace();
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

    private void getPlayers() {
        players = Constants.dbConnection.getAllPlayers();
    }

    private ArrayList<PlayerInfo> getActivePlayers() {
        ArrayList<PlayerInfo> players = new ArrayList<>();
        for (Integer i : frameIndices) {
            if (i > -1)
                players.add(this.players.get(i));
        }
        return players;
    }

    private void validatePlayers() throws InvalidPlayersException {

        Set<Integer> lump = new HashSet<Integer>();
        for (Integer i : frameIndices) {
            if (lump.contains(i) || i == -1)
                throw new InvalidPlayersException();
            lump.add(i);
        }
    }

    private void init() {
        dialog = new CreateUserDialog(this);

        ScaledBackground background = new ScaledBackground(Constants.getAsset("mainBg.png"), xSize, ySize,
                new BorderLayout());
        getContentPane().add(background);

        TransparentPanel mainPanel = new TransparentPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        mainPanel.setPreferredSize(new Dimension(MAX_COMPONENT_X, MAX_COMPONENT_Y));

        TransparentPanel buttonPanel = new TransparentPanel();

        buttonPanel.add(createPanel(Constants.getColor(Constants.Color.BLUE, false), 0));
        buttonPanel.add(Box.createHorizontalStrut(30));
        buttonPanel.add(createPanel(Constants.getColor(Constants.Color.GREEN, false), 1));
        buttonPanel.add(Box.createHorizontalStrut(30));
        buttonPanel.add(createPanel(Constants.getColor(Constants.Color.YELLOW, false), 2));
        buttonPanel.add(Box.createHorizontalStrut(30));
        buttonPanel.add(createPanel(Constants.getColor(Constants.Color.RED, false), 3));

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
                    Application.changeScreen(new ScreenObject(context, Screens.GAME, getActivePlayers()));
                } catch (InvalidPlayersException e1) {
                    JOptionPane.showMessageDialog(context, e1.getMessage(), "Error!", JOptionPane.OK_OPTION);
                }
            }
        });

        TransparentPanel northPanel = new TransparentPanel(new FlowLayout(FlowLayout.LEFT));
        northPanel.add(Box.createRigidArea(new Dimension(30, 120)));
        BackButton backButton = new BackButton(0.8, Constants.getProximaInstance(36));
        backButton.addMouseListener(new MouseClickListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Application.changeScreen(new ScreenObject(context, Screens.TITLE_SCREEN));
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

        TransparentPanel mainPanel = new TransparentPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));

        TransparentPanel textPanel = new TransparentPanel();
        SmoothText arrowL = new SmoothText("<");
        SmoothText arrowR = new SmoothText(">");
        SmoothText text = new SmoothText(players.get(frameIndices[index]).getName(), Constants.getProximaInstance(28));

        arrowR.addMouseListener(new IArrowMouseListener(text, index, true));
        arrowL.addMouseListener(new IArrowMouseListener(text, index, false));
        text.addMouseListener(new ITextMouseClickListener(index));

        textPanel.add(arrowL);
        textPanel.add(text);
        textPanel.add(arrowR);

        RoundedRectangle playButton = new RoundedRectangle(375, 375, 80);
        playButton.setLayout(new BoxLayout(playButton, BoxLayout.PAGE_AXIS));

        TransparentPanel label = new TransparentPanel();
        label.add(new PersonIcon(color));

        playButton.add(Box.createVerticalGlue());
        playButton.add(Box.createHorizontalStrut(135 / 2));
        playButton.add(label);
        playButton.add(textPanel);
        playButton.add(Box.createVerticalGlue());

        mainPanel.add(Box.createVerticalStrut(15));
        mainPanel.add(playButton);

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
                    dialog.showDialog();
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
