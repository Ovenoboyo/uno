package com.unoapp.uno.abstracts;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Custom implementation of MouseListener where you dont have to 
 * override unnecessary methods .-.
 */
public abstract class MouseClickListener implements MouseListener {
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
