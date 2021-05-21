package com.unoapp.uno.ui.components;

import java.awt.Dimension;

import javax.swing.JLabel;

import com.unoapp.uno.ui.drawables.Person;

/**
 * Label to hold person icon
 */
public class PersonIcon extends JLabel {

    /**
     * Label with person icon of default size [150 x 150] and custom color
     * 
     * @param color color of icon
     */
    public PersonIcon(java.awt.Color color) {
        this(150, 150, color);
    }

    /**
     * Label with person icon of custom size and color
     * The aspect ratio is never lost. THe icon is just scaled to minimum of either height or width
     * 
     * @param width width of icon
     * @param height height of icon
     * @param color color of icon
     */
    public PersonIcon(int width, int height, java.awt.Color color) {
        setIcon(new Person(width, height, color));
        setPreferredSize(new Dimension(width, height));
    }
}
