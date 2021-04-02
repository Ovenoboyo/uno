package com.unoapp.uno.ui;

import javax.swing.JButton;
import javax.swing.JFrame;

import com.unoapp.uno.engine.GameController;

public class Game {
    JFrame frame = new JFrame();
    GameController controller = new GameController();

    public Game() {
        init();
    }

    private void init() {
        JButton test = new JButton();
        frame.add(test);

        System.out.println(frame);
    }

    public void setVisible(Boolean value) {
        this.frame.setVisible(value);
    }
}
