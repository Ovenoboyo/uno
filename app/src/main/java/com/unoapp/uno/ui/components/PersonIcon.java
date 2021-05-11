package com.unoapp.uno.ui.components;

import java.awt.Dimension;

import javax.swing.JLabel;

import com.unoapp.uno.ui.drawables.Person;

public class PersonIcon extends JLabel {
    public PersonIcon(java.awt.Color color) {
        setIcon(new Person(150, 150, color));
        setPreferredSize(new Dimension(150, 150));
    }
}
