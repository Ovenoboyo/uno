package com.unoapp.uno.ui.components;

import java.awt.Dimension;

import javax.swing.JLabel;

import com.unoapp.uno.ui.drawables.Person;

/**
 * Label to hold person icon
 */
public class PersonIcon extends JLabel {
    public PersonIcon(java.awt.Color color) {
        this(150, 150, color);
    }

    public PersonIcon(int width, int height, java.awt.Color color) {
        setIcon(new Person(width, height, color));
        setPreferredSize(new Dimension(width, height));
    }
}
