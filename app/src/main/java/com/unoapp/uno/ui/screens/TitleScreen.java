package com.unoapp.uno.ui.screens;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;

import com.unoapp.uno.ui.components.RoundedRectangle;
import com.unoapp.uno.ui.components.ScaledBackground;
import com.unoapp.uno.ui.components.SmoothText;
import com.unoapp.uno.ui.components.TransparentPanel;

public class TitleScreen extends JFrame {

    public TitleScreen() {
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

        buttonPanel.add(createButton("Play", "assets/playIcon.png", 178, 124));
        buttonPanel.add(Box.createHorizontalStrut(80));
        buttonPanel.add(createButton("Settings", "assets/gear.png", 123, 123));
        buttonPanel.add(Box.createHorizontalStrut(80));
        buttonPanel.add(createButton("Quit", "assets/power.png", 144, 144));
        // buttonPanel.add(playButton);

        mainPanel.add(Box.createVerticalGlue());
        mainPanel.add(Box.createVerticalGlue());
        mainPanel.add(Box.createVerticalGlue());

        mainPanel.add(buttonPanel);
        mainPanel.add(Box.createVerticalGlue());

        background.add(mainPanel);

        pack();

    }

    private TransparentPanel createButton(String labelText, String iconSrc, int iconWidth, int iconHeight) {
        TransparentPanel mainPanel = new TransparentPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));

        TransparentPanel textPanel = new TransparentPanel();
        SmoothText text = new SmoothText(labelText, Color.WHITE);
        textPanel.add(text);

        RoundedRectangle playButton = new RoundedRectangle(200, 200, 80);
        playButton.setLayout(new BoxLayout(playButton, BoxLayout.PAGE_AXIS));
        TransparentPanel label = new TransparentPanel();
        label.add(new ScaledBackground(iconSrc, iconWidth, iconHeight, new FlowLayout()));
        playButton.add(Box.createVerticalGlue());
        playButton.add(Box.createHorizontalStrut(iconHeight / 2));
        playButton.add(label);
        playButton.add(Box.createVerticalGlue());

        mainPanel.add(textPanel);
        mainPanel.add(Box.createVerticalStrut(15));
        mainPanel.add(playButton);

        return mainPanel;
    }
}