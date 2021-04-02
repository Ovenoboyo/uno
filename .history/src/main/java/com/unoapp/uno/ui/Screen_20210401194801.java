package com.unoapp.uno.ui;

import java.awt.Dimension;

import javax.swing.GroupLayout;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;

import org.springframework.stereotype.Component;

@Component
public class Screen extends javax.swing.JFrame {

    // /**
    //  * Generated SerialUID
    //  */
    // private static final long serialVersionUID = 1L;

    // public Screen() {
    //     this.initComponents();
    // }

    // private void initComponents() {
    //     JDesktopPane desktopPane = new JDesktopPane();

    //     GroupLayout layout = new javax.swing.GroupLayout(getContentPane());

    //     layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
    //             .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE));
    //     layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
    //             .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE));

    //     setMinimumSize(new Dimension(400, 400));
    //     setLocationRelativeTo(null);
    //     pack();
    // }
    

    public static void main(String[] args){

        JFrame frame = new JFrame();
        frame.setSize(100, 100);

    }

}