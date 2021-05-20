package com.unoapp.uno.ui.components;

import java.awt.Dimension;

import javax.swing.JLabel;

import com.unoapp.uno.ui.drawables.Tick;

public class TickLabel extends JLabel {
    public TickLabel() {
        Tick start = new Tick();
        setIcon(start);
        setPreferredSize(new Dimension(start.getIconWidth(), start.getIconHeight()));
    }
}
