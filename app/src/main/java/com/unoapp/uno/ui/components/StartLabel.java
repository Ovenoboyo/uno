package com.unoapp.uno.ui.components;

import java.awt.Dimension;

import javax.swing.JLabel;

import com.unoapp.uno.ui.drawables.StartButton;

public class StartLabel extends JLabel {
    public StartLabel() {
        StartButton start = new StartButton(177, 75);
        setIcon(start);
        setPreferredSize(new Dimension(start.getIconWidth(), start.getIconHeight()));
    }
}
