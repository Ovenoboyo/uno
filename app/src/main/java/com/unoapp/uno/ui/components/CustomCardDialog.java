package com.unoapp.uno.ui.components;

import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;

import com.unoapp.uno.models.Card;
import com.unoapp.uno.ui.CardDrawable;

public class CustomCardDialog extends JDialog {

    private JLabel label = new JLabel();
    private JPanel cardPanel = new JPanel();
    private JPanel buttonGroup = new JPanel();
    private JPanel global = new JPanel();

    private static final long serialVersionUID = 1L;

    public CustomCardDialog() throws IOException {
        super();
        init();
    }

    private void init() {
        setAlwaysOnTop(true);
        setUndecorated(true);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                return;
            }
        });

        global.setLayout(new BoxLayout(global, BoxLayout.Y_AXIS));
        global.add(label);
        global.add(cardPanel);
        global.add(buttonGroup);
        add(global);
    }

    public void addCards(Card... cards) throws IOException {
        for (Card c : cards) {
            cardPanel.add(new CardDrawable(c.getColor(), c.getNum(), false, null));
        }
    }

    public void clean() {
        cardPanel.removeAll();
        buttonGroup.removeAll();
        label.setText(null);
    }

    public void addButton(String title, ActionListener mListener, boolean enabled) {
        JButton button = new JButton(title);
        button.addActionListener(e -> {
            dispose();
            mListener.actionPerformed(e);
        });
        button.setEnabled(enabled);
        buttonGroup.add(button);
    }

    public void addLabel(String title) {
        label.setText(title);
    }

    public void showDialog() {
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}