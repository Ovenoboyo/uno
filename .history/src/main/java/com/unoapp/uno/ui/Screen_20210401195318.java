package com.unoapp.uno.ui;

import java.awt.Dimension;
import java.time.chrono.JapaneseDate;

import javax.swing.GroupLayout;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.springframework.stereotype.Component;

@Component
public class Screen{

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
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        JPanel panel = new JPanel();
    }

}