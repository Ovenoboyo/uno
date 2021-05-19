package com.unoapp.uno.ui.screens;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;

import com.unoapp.uno.Application;
import com.unoapp.uno.Application.ScreenObject;
import com.unoapp.uno.abstracts.MouseClickListener;
import com.unoapp.uno.models.Player;
import com.unoapp.uno.models.PlayerInfo;
import com.unoapp.uno.models.StatAnalytics;
import com.unoapp.uno.ui.components.BackButton;
import com.unoapp.uno.ui.components.ContinueLabel;
import com.unoapp.uno.ui.components.GenericMenuScreen;
import com.unoapp.uno.ui.components.ProgressBar;
import com.unoapp.uno.ui.components.ScaledBackground;
import com.unoapp.uno.ui.components.TransparentPanel;
import com.unoapp.uno.utils.Constants;
import com.unoapp.uno.utils.Constants.Screens;

public class Results extends GenericMenuScreen {
    private final GenericMenuScreen context = this;
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
        ScaledBackground background = new ScaledBackground(Constants.getAsset("mainBg.png"), xSize, ySize,
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
                Application.changeScreen(new ScreenObject(Screens.TITLE_SCREEN));
            }
        });

        TransparentPanel northPanel = new TransparentPanel(new FlowLayout(FlowLayout.LEFT));
        northPanel.add(Box.createRigidArea(new Dimension(30, 120)));
        BackButton backButton = new BackButton(0.8, Constants.getProximaInstance(36, true));
        backButton.addMouseListener(new MouseClickListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Application.changeScreen(new ScreenObject(context, Screens.TITLE_SCREEN));
            }
        });
        northPanel.add(backButton);

        southPanel.add(continueButton);
        southPanel.add(Box.createRigidArea(new Dimension(30, 100)));
        background.add(southPanel, BorderLayout.SOUTH);
        background.add(northPanel, BorderLayout.NORTH);

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

    private int getLevel(int xp) {
        return (int) (Math.floor(500 + Math.sqrt(2500 + 100 * xp)) / 100);
    }

    private int getUpperBoundXP(int level) {
        int L = level + 1;
        return 50 * L * L - 50 * L;
    }

    private Double getProgressPercentage(int current, int upperBoundXP) {
        return ((double) current / upperBoundXP) * 100;
    }

    private Integer calculateXP(String id, StatAnalytics analytics) {
        int xp = 100 + (analytics.getDraw2() * 2 + 2 * analytics.getSkip() + 2 * analytics.getReverse()
                + analytics.getWild() * 3 + analytics.getDraw4() * 4) * 3;
        if (id.equals(winner.getId())) {
            xp += 100;
        }
        return xp;
    }

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

    private TransparentPanel createPanel(Player player, PlayerInfo info, Constants.Color color) {
        TransparentPanel mainPanel = new TransparentPanel();

        Integer initialXP = info.getExperience();
        Integer level = getLevel(initialXP);
        Integer upperBoundXP = getUpperBoundXP(level);
        Integer newXP = calculateXP(player.getId(), player.getAnalytics());

        updateDatabase(info, player, newXP);

        mainPanel.add(new ProgressBar(MAX_COMPONENT_X - 200, 116, getProgressPercentage(initialXP, upperBoundXP),
                getProgressPercentage(newXP, upperBoundXP), color, info.getName(), newXP.toString()));
        return mainPanel;
    }
}
