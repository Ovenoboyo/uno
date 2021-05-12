package com.unoapp.uno.ui.drawables;

import static java.awt.Color.WHITE;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.GeneralPath;
import java.awt.geom.RoundRectangle2D;

/**
 * This class has been automatically generated using
 * <a href="http://ebourg.github.io/flamingo-svg-transcoder/">Flamingo SVG
 * transcoder</a>.
 */
public class Wild {

    /**
     * Paints the transcoded SVG image on the specified graphics context.
     * 
     * @param g2d
     * @param color
     * @param width
     * @param height
     */
    public static void paint(Graphics2D g2d, Color color, double width, double height) {
        Shape shape = null;

        //

        // _0

        // _0_0
        Graphics2D g = (Graphics2D) g2d.create();
        double coef = Math.min(width / (double) 1120, height / (double) 1780);
        g.scale(coef, coef);
        shape = new RoundRectangle2D.Double(23.5, 23.5, 1073, 1733, 83, 83);
        g.setPaint(color);
        g.fill(shape);
        g.setPaint(WHITE);
        g.setStroke(new BasicStroke(47, 0, 0, 4));
        g.draw(shape);

        // _0_1
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(129.507, 1599.07);
        ((GeneralPath) shape).curveTo(151.785, 1614.71, 176.034, 1625.93, 201.867, 1632.97);
        ((GeneralPath) shape).lineTo(588.337, 821.5);
        ((GeneralPath) shape).lineTo(103.621, 821.5);
        ((GeneralPath) shape).curveTo(-40.3623, 1157.54, -36.6396, 1482.46, 129.507, 1599.07);
        ((GeneralPath) shape).closePath();

        g.setPaint(new Color(0xEFD341));
        g.fill(shape);

        // _0_2
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(918.133, 1132.36);
        ((GeneralPath) shape).curveTo(979.906, 1029.33, 1027.82, 923.759, 1061.43, 821.5);
        ((GeneralPath) shape).lineTo(588.337, 821.5);
        ((GeneralPath) shape).lineTo(201.867, 1632.97);
        ((GeneralPath) shape).curveTo(405.375, 1688.44, 707.159, 1484.27, 918.133, 1132.36);
        ((GeneralPath) shape).closePath();

        g.setPaint(new Color(0x6CB560));
        g.fill(shape);

        // _0_3
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(1061.43, 821.5);
        ((GeneralPath) shape).curveTo(1157.18, 530.176, 1136.9, 265.687, 990.493, 162.932);
        ((GeneralPath) shape).curveTo(968.215, 147.295, 943.966, 136.07, 918.133, 129.029);
        ((GeneralPath) shape).lineTo(588.337, 821.5);
        ((GeneralPath) shape).lineTo(1061.43, 821.5);
        ((GeneralPath) shape).closePath();

        g.setPaint(new Color(0x036BD2));
        g.fill(shape);

        // _0_4
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(201.867, 629.637);
        ((GeneralPath) shape).curveTo(163.828, 693.087, 131.045, 757.495, 103.621, 821.5);
        ((GeneralPath) shape).lineTo(588.337, 821.5);
        ((GeneralPath) shape).lineTo(918.133, 129.029);
        ((GeneralPath) shape).curveTo(714.625, 73.5596, 412.842, 277.728, 201.867, 629.637);
        ((GeneralPath) shape).closePath();

        g.setPaint(new Color(0xCE3C3D));
        g.fill(shape);

        // _0_5
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(143.007, 1579.83);
        ((GeneralPath) shape).curveTo(68.6491, 1527.64, 27.3639, 1426.25, 23.7568, 1291.94);
        ((GeneralPath) shape).curveTo(20.1673, 1158.28, 54.23, 996.442, 125.222, 830.755);
        ((GeneralPath) shape).curveTo(152.226, 767.731, 184.525, 704.267, 222.022, 641.721);
        ((GeneralPath) shape).curveTo(326.11, 468.101, 452.137, 331.758, 576.176, 245.888);
        ((GeneralPath) shape).curveTo(700.973, 159.493, 819.942, 126.623, 911.953, 151.702);
        ((GeneralPath) shape).curveTo(935.229, 158.046, 956.99, 168.128, 976.993, 182.167);
        ((GeneralPath) shape).curveTo(1042.49, 228.14, 1082.35, 312.195, 1093.38, 423.814);
        ((GeneralPath) shape).curveTo(1104.38, 535.127, 1086.25, 670.724, 1039.1, 814.162);
        ((GeneralPath) shape).curveTo(1006.05, 914.716, 958.88, 1018.69, 897.978, 1120.28);
        ((GeneralPath) shape).curveTo(793.89, 1293.9, 667.863, 1430.24, 543.824, 1516.11);
        ((GeneralPath) shape).curveTo(419.027, 1602.51, 300.058, 1635.38, 208.047, 1610.3);
        ((GeneralPath) shape).curveTo(184.771, 1603.95, 163.01, 1593.87, 143.007, 1579.83);
        ((GeneralPath) shape).closePath();

        g.setPaint(WHITE);
        g.draw(shape);

        g.dispose();

    }
}
