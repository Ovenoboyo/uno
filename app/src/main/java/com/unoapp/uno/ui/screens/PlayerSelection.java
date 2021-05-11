package com.unoapp.uno.ui.screens;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;

import com.unoapp.uno.ui.components.PersonIcon;
import com.unoapp.uno.ui.components.RoundedRectangle;
import com.unoapp.uno.ui.components.ScaledBackground;
import com.unoapp.uno.ui.components.SmoothText;
import com.unoapp.uno.ui.components.TransparentPanel;
import com.unoapp.uno.utils.Constants;

import java.awt.FlowLayout;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import java.awt.Toolkit;

public class PlayerSelection extends JFrame {

    public PlayerSelection() {
        init("assets/mainBg.png");
    }

    private void init(String bgSrc) {
        Toolkit tk = Toolkit.getDefaultToolkit();
        int xSize = ((int) tk.getScreenSize().getWidth());
        int ySize = ((int) tk.getScreenSize().getHeight());
        setPreferredSize(new Dimension(xSize, ySize));

        GraphicsEnvironment graphics = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice device = graphics.getDefaultScreenDevice();

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setUndecorated(true);
        setResizable(false);

        device.setFullScreenWindow(this);

        final int MAX_COMPONENT_X = xSize - 120;
        final int MAX_COMPONENT_Y = ySize - 60;

        ScaledBackground background = new ScaledBackground(bgSrc, xSize + 120, ySize + 120, new FlowLayout());
        background.setBounds(0, 0, xSize, ySize);
        getContentPane().add(background);

        TransparentPanel mainPanel = new TransparentPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        mainPanel.setPreferredSize(new Dimension(MAX_COMPONENT_X, MAX_COMPONENT_Y));

        TransparentPanel buttonPanel = new TransparentPanel();

        buttonPanel.add(createButton(Constants.getColor(Constants.Color.BLUE, false)));
        buttonPanel.add(Box.createHorizontalStrut(30));
        buttonPanel.add(createButton(Constants.getColor(Constants.Color.GREEN, false)));
        buttonPanel.add(Box.createHorizontalStrut(30));
        buttonPanel.add(createButton(Constants.getColor(Constants.Color.YELLOW, false)));
        buttonPanel.add(Box.createHorizontalStrut(30));
        buttonPanel.add(createButton(Constants.getColor(Constants.Color.RED, false)));
        // buttonPanel.add(playButton);

        mainPanel.add(Box.createVerticalGlue());
        mainPanel.add(Box.createVerticalGlue());
        mainPanel.add(Box.createVerticalGlue());

        mainPanel.add(buttonPanel);
        mainPanel.add(Box.createVerticalGlue());

        background.add(mainPanel);

        pack();

    }

    private TransparentPanel createButton(java.awt.Color color) {
        TransparentPanel mainPanel = new TransparentPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));

        TransparentPanel textPanel = new TransparentPanel();
        SmoothText arrowL = new SmoothText("<", Color.BLACK);
        SmoothText arrowR = new SmoothText(">", Color.BLACK);
        SmoothText text = new SmoothText("Player Name", Color.BLACK);
        textPanel.add(arrowL);
        textPanel.add(text);
        textPanel.add(arrowR);

        RoundedRectangle playButton = new RoundedRectangle(375, 375);
        playButton.setLayout(new BoxLayout(playButton, BoxLayout.PAGE_AXIS));
        TransparentPanel label = new TransparentPanel();
        label.add(new PersonIcon(color));
        playButton.add(Box.createVerticalGlue());
        playButton.add(Box.createHorizontalStrut(135 / 2));
        playButton.add(label);
        playButton.add(textPanel);
        playButton.add(Box.createVerticalGlue());

        // mainPanel.add(textPanel);
        mainPanel.add(Box.createVerticalStrut(15));
        mainPanel.add(playButton);

        return mainPanel;
    }
}