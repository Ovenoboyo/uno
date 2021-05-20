package com.unoapp.uno.ui.screens;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;

import com.unoapp.uno.UNO;
import com.unoapp.uno.UNO.ScreenObject;
import com.unoapp.uno.abstracts.MouseClickListener;
import com.unoapp.uno.models.Achievement;
import com.unoapp.uno.models.PlayerInfo;
import com.unoapp.uno.ui.components.BackButton;
import com.unoapp.uno.ui.components.GenericMenuScreen;
import com.unoapp.uno.ui.components.RoundedRectangle;
import com.unoapp.uno.ui.components.ScaledBackground;
import com.unoapp.uno.ui.components.SmoothText;
import com.unoapp.uno.ui.components.TickLabel;
import com.unoapp.uno.ui.components.TransparentPanel;
import com.unoapp.uno.ui.components.TriangleArrow;
import com.unoapp.uno.utils.Assets;
import com.unoapp.uno.utils.Constants;
import com.unoapp.uno.utils.Constants.AchievementTypes;
import com.unoapp.uno.utils.Constants.Screens;
import com.unoapp.uno.utils.Experience;
import com.unoapp.uno.utils.Fonts;

/**
 * Achievement screen
 */
public class AchievementScreen extends GenericMenuScreen {
    private ArrayList<Achievement> achievements;
    private ArrayList<PlayerInfo> players;
    private Integer playerIndex = 0;

    private Box box;

    private final GenericMenuScreen context = this;

    /**
     * Default constructor
     */
    public AchievementScreen() {
        super();
        getAllPlayers();
        getAllAchievements();
        init();
    }

    /**
     * Initialize screen components
     */
    private void init() {
        ScaledBackground background = new ScaledBackground(Assets.getAsset("mainBg.png"), xSize, ySize,
                new BorderLayout());
        getContentPane().add(background);

        TransparentPanel northPanel = new TransparentPanel();
        northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.PAGE_AXIS));

        TransparentPanel backPanel = new TransparentPanel(new FlowLayout(FlowLayout.LEFT));

        northPanel.add(Box.createVerticalStrut(30));
        backPanel.add(Box.createHorizontalStrut(30));

        BackButton backButton = new BackButton(0.8, Fonts.getProximaInstance(36, true));
        backButton.addMouseListener(new MouseClickListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                UNO.changeScreen(new ScreenObject(context, Screens.TITLE_SCREEN));
            }
        });

        backPanel.add(backButton);
        northPanel.add(backPanel);
        background.add(northPanel, BorderLayout.NORTH);

        if (players != null && players.size() > 0) {

            TransparentPanel dropdownPanel = new TransparentPanel();
            dropdownPanel.setMaximumSize(new Dimension(MAX_COMPONENT_X - 500, 188));
            dropdownPanel.setLayout(new BoxLayout(dropdownPanel, BoxLayout.Y_AXIS));

            TransparentPanel dropdownContainer = new TransparentPanel(new FlowLayout(FlowLayout.LEFT));

            dropdownContainer.add(createPlayerSelection());

            TransparentPanel labelContainer = new TransparentPanel(new FlowLayout(FlowLayout.LEFT));
            SmoothText label = new SmoothText("Choose Player", Color.WHITE);
            labelContainer.add(label);

            dropdownPanel.add(labelContainer);
            dropdownPanel.add(dropdownContainer);
            dropdownPanel.add(Box.createVerticalStrut(30));

            northPanel.add(Box.createVerticalStrut(30));
            northPanel.add(dropdownPanel);

            box = Box.createVerticalBox();
            box.setOpaque(false);

            renderAchievements();

            JScrollPane pane = new JScrollPane(box, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                    JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            pane.setOpaque(false);
            pane.getViewport().setOpaque(false);
            pane.setBorder(null);

            background.add(pane, BorderLayout.CENTER);

        }
        background.add(Box.createVerticalStrut(30), BorderLayout.SOUTH);

    }

    /**
     * Create player circular selection panel
     * @return Transparent panel with player selection
     */
    private TransparentPanel createPlayerSelection() {
        RoundedRectangle rectangle = new RoundedRectangle(280, 50, 50);
        TransparentPanel textHolder = new TransparentPanel();
        TransparentPanel textPanel = new TransparentPanel(new BorderLayout(), new Dimension(200, 29));
        TriangleArrow arrowL = new TriangleArrow(true, Color.BLUE);
        TriangleArrow arrowR = new TriangleArrow(false, Color.BLUE);
        SmoothText text = new SmoothText(players.get(playerIndex).getName(), Color.BLACK,
                Fonts.getProximaInstance(28, true));

        TransparentPanel playerNameHolder = new TransparentPanel();
        playerNameHolder.add(text);

        arrowR.addMouseListener(new IArrowMouseListener(text, true));
        arrowL.addMouseListener(new IArrowMouseListener(text, false));

        textPanel.add(arrowL, BorderLayout.WEST);
        textPanel.add(playerNameHolder, BorderLayout.CENTER);
        textPanel.add(arrowR, BorderLayout.EAST);

        textHolder.add(textPanel);

        rectangle.setLayout(new BoxLayout(rectangle, BoxLayout.PAGE_AXIS));

        rectangle.add(Box.createHorizontalGlue());
        rectangle.add(Box.createHorizontalStrut(150 / 2));

        rectangle.add(textHolder);
        rectangle.add(Box.createVerticalGlue());

        return rectangle;
    }

    /**
     * Get all players from database
     */
    private void getAllPlayers() {
        this.players = Constants.dbConnection.getAllPlayers();
    }

    /**
     * Get all achievements from database
     */
    private void getAllAchievements() {
        this.achievements = Constants.dbConnection.getAchievements();
    }

    /**
     * Get progress number of current player respective to achievement type
     * 
     * @param p PlayerInfo object fetched from database
     * @param type Type of achievement
     * @return Progress of given achievement as int
     */
    private int getPlayerProgress(PlayerInfo p, AchievementTypes type) {
        if (p != null) {
            switch (type) {
                case PLAYED:
                    return p.getGamesWon() + p.getGamesLost();
                case DRAW2:
                    return p.getDraw2();
                case DRAW4:
                    return p.getDraw4();
                case EXP:
                    return Experience.getLevel(p.getExperience());
                case REVERSE:
                    return p.getReverse();
                case SKIP:
                    return p.getSkip();
                case WILD:
                    return p.getWild();
            }
        }
        return 0;
    }

    /**
     * Increment index 
     */
    private void incrementIndex() {
        playerIndex++;
        if (playerIndex >= players.size()) {
            playerIndex = 0;
        }
    }

    private void renderAchievements() {

        box.add(Box.createVerticalGlue());
        box.add(Box.createVerticalGlue());

        int xp = players.get(playerIndex).getExperience();
        Double nearest = Math.max(5, 5 * (Math.ceil(Math.abs(Experience.getLevel(xp) / (double) 5))));

        box.add(createPanel(
                new Achievement(AchievementTypes.EXP, "Reach level " + nearest.intValue(), nearest.floatValue()),
                players.get(playerIndex)));

        for (var a : achievements) {
            box.add(createPanel(a, players.get(playerIndex)));
        }

        box.add(Box.createVerticalGlue());
        box.add(Box.createVerticalGlue());
        box.add(Box.createVerticalGlue());
    }

    /**
     * Decrements index in circular manner
     * @param index index of frame whose index is to be decremented
     */
    private void decrementIndex() {
        playerIndex--;
        if (playerIndex < 0) {
            playerIndex = players.size() - 1;
        }
    }

    /**
     * Create achievement rectangle panel
     * 
     * @param achievement achievement to render 
     * @param player player whose progress to show
     * @return Transparent panel of rendered rectangle
     */
    private TransparentPanel createPanel(Achievement achievement, PlayerInfo player) {
        Integer progress = getPlayerProgress(player, achievement.getType());
        Float total = achievement.getTotal();

        return createAchievementRect(progress >= total, achievement.getTitle(), progress, total.intValue());
    }

    /**
     * Create rectangle for achievement
     * 
     * @param completed true if achievment is completed otherwise false
     * @param title title of achievement
     * @param progress current of progress of player towards achievement
     * @param total total amount required to complete achievement
     * @return panel with rounded rectangle
     */
    private TransparentPanel createAchievementRect(Boolean completed, String title, Integer progress, Integer total) {
        var mainPanel = new TransparentPanel();

        RoundedRectangle rect = new RoundedRectangle(MAX_COMPONENT_X - 500, 120, 60, new FlowLayout(),
                (completed) ? new Color(97, 215, 67) : Color.WHITE);

        var textBox = new TransparentPanel();
        textBox.setLayout(new BoxLayout(textBox, BoxLayout.Y_AXIS));
        textBox.setPreferredSize(new Dimension(MAX_COMPONENT_X - 500, 120 - 5));

        var borderPanel = new TransparentPanel(new BorderLayout());
        borderPanel.setMaximumSize(new Dimension(MAX_COMPONENT_X - 580, 29));

        SmoothText text = new SmoothText(title);
        borderPanel.add(text, BorderLayout.WEST);

        if (completed) {
            var tickPanel = new TransparentPanel();
            var tickLabel = new TickLabel();

            tickPanel.add(tickLabel);
            borderPanel.add(tickPanel, BorderLayout.EAST);
        } else {
            SmoothText text2 = new SmoothText(progress + " / " + total);
            borderPanel.add(text2, BorderLayout.EAST);
        }

        textBox.add(Box.createVerticalGlue());
        textBox.add(borderPanel);
        textBox.add(Box.createVerticalGlue());

        rect.add(textBox);
        mainPanel.add(rect);
        return mainPanel;

    }

    /**
     * Mouse click listener for arrow
     */
    private final class IArrowMouseListener extends MouseClickListener {
        private final SmoothText text;
        private final Boolean increment;

        private IArrowMouseListener(SmoothText text, boolean increment) {
            this.text = text;
            this.increment = increment;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if (increment)
                incrementIndex();
            else
                decrementIndex();

            text.setText(players.get(playerIndex).getName());

            box.removeAll();
            renderAchievements();
            box.revalidate();

        }
    }
}
