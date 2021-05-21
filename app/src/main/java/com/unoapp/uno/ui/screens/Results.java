package com.unoapp.uno.ui.screens;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;

import com.unoapp.uno.UNO;
import com.unoapp.uno.UNO.ScreenObject;
import com.unoapp.uno.abstracts.MouseClickListener;
import com.unoapp.uno.models.Player;
import com.unoapp.uno.models.PlayerInfo;
import com.unoapp.uno.ui.components.ContinueLabel;
import com.unoapp.uno.ui.components.GenericMenuScreen;
import com.unoapp.uno.ui.components.ProgressBar;
import com.unoapp.uno.ui.components.ScaledBackground;
import com.unoapp.uno.ui.components.TransparentPanel;
import com.unoapp.uno.utils.Assets;
import com.unoapp.uno.utils.Constants;
import com.unoapp.uno.utils.Constants.Screens;
import com.unoapp.uno.utils.Experience;

/**
 * End-game screen shown after a winner is chosen
 * Shows experience gained by every player and a button to return to title screen
 */
public class Results extends GenericMenuScreen {
    private final ArrayList<Player> players;
    private final Player winner;
    private TransparentPanel buttonPanel;

    public Results(ArrayList<Player> players, Player winner) {
        super();
        this.players = players;
        this.winner = winner;
        init();
    }

    private void init() {
        ScaledBackground background = new ScaledBackground(Assets.getAsset("mainBg.png"), xSize, ySize,
                new BorderLayout());
        getContentPane().add(background);

        TransparentPanel mainPanel = new TransparentPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        mainPanel.setPreferredSize(new Dimension(MAX_COMPONENT_X, MAX_COMPONENT_Y));

        buttonPanel = new TransparentPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.PAGE_AXIS));

        mainPanel.add(buttonPanel);
        addPlayers();
        background.add(mainPanel, BorderLayout.CENTER);

        TransparentPanel southPanel = new TransparentPanel(new FlowLayout(FlowLayout.RIGHT));
        TransparentPanel continueButton = new TransparentPanel(new FlowLayout(FlowLayout.LEFT));
        continueButton.add(Box.createRigidArea(new Dimension(0, 120)));
        continueButton.add(new ContinueLabel());

        continueButton.addMouseListener(new MouseClickListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                UNO.changeScreen(new ScreenObject(Screens.TITLE_SCREEN));
            }
        });

        southPanel.add(continueButton);
        southPanel.add(Box.createRigidArea(new Dimension(30, 100)));
        background.add(southPanel, BorderLayout.SOUTH);

        pack();
    }

    private void addPlayers() {
        buttonPanel.add(Box.createVerticalGlue());
        buttonPanel.add(Box.createVerticalGlue());
        if (this.players != null) {
            for (int i = 0; i < players.size(); i++) {
                var info = Constants.dbConnection.getSinglePlayer(players.get(i).getId());
                buttonPanel.add(createPanel(players.get(i), info, Constants.Color.values()[i]));
            }
        }

        buttonPanel.add(Box.createVerticalGlue());
    }

    private Double getProgressPercentage(int current, int upperBoundXP) {
        return ((double) current / upperBoundXP) * 100;
    }

    /**
     * Update database with new player stats
     * 
     * @param info PlayerInfo with old player stats
     * @param player Player with updated stats
     * @param experience Experience recieved by player
     */
    private void updateDatabase(PlayerInfo info, Player player, int experience) {
        info.setDraw2(info.getDraw2() + player.getAnalytics().getDraw2());
        info.setDraw4(info.getDraw4() + player.getAnalytics().getDraw4());
        info.setWild(info.getWild() + player.getAnalytics().getWild());
        info.setSkip(info.getSkip() + player.getAnalytics().getSkip());
        info.setReverse(info.getReverse() + player.getAnalytics().getReverse());
        info.setExperience(info.getExperience() + experience);

        if (info.getId().equals(winner.getId())) {
            info.setGamesWon(info.getGamesWon() + 1);
        } else {
            info.setGamesLost(info.getGamesLost() + 1);
        }

        try {
            Constants.dbConnection.updatePlayer(info);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates panel with progressbar
     * 
     * @param player Player whose experience is to be generated
     * @param info  PlayerInfo of player whose experience is to be generated
     * @param color Color of progress bar
     * @return Transparant panel with ProgressBar
     */
    private TransparentPanel createPanel(Player player, PlayerInfo info, Constants.Color color) {
        TransparentPanel mainPanel = new TransparentPanel();

        Integer initialXP = info.getExperience();
        Integer level = Experience.getLevel(initialXP);
        Integer upperBoundXP = Experience.getUpperBoundXP(level);
        Integer newXP = Experience.calculateXP(player.getAnalytics(), player.getId().equals(winner.getId()));

        updateDatabase(info, player, newXP);

        mainPanel.add(new ProgressBar(MAX_COMPONENT_X - 200, 116, getProgressPercentage(initialXP, upperBoundXP),
                getProgressPercentage(newXP, upperBoundXP), color, info.getName(), newXP.toString()));
        return mainPanel;
    }
}
