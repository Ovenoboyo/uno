package com.unoapp.uno.ui;

import java.awt.Dimension;

import javax.swing.GroupLayout;
import javax.swing.JDesktopPane;

public class Screen extends javax.swing.JFrame {
    private static final long serialVersionUID = 4682933028079150112L;

    public Screen() {
        this.initComponents();
    }

    private void initComponents() {
        JDesktopPane desktopPane = new JDesktopPane();

        GroupLayout layout = new javax.swing.GroupLayout(getContentPane());

        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE));

        setMinimumSize(new Dimension(400, 400));
        setLocationRelativeTo(null);
        pack();
    }

}