package com.unoapp.uno.ui.drawables;

import static java.awt.Color.BLACK;
import static java.awt.Color.WHITE;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.GeneralPath;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

/**
 * This class has been automatically generated using
 * <a href="http://ebourg.github.io/flamingo-svg-transcoder/">Flamingo SVG transcoder</a>.
 */
public class StartButton extends ImageIcon {

    /** The width of this icon. */
    private int width = 257;

    /** The height of this icon. */
    private int height = 109;

    /** The rendered image. */
    private BufferedImage image;

    /**
     * Creates a new transcoded SVG image.
     */
    public StartButton() {
        this(257, 109);
    }

    /**
     * Creates a new transcoded SVG image.
     */
    public StartButton(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public int getIconHeight() {
        return height;
    }

    @Override
    public int getIconWidth() {
        return width;
    }

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        if (image == null) {
            image = new BufferedImage(getIconWidth(), getIconHeight(), BufferedImage.TYPE_INT_ARGB);
            double coef = Math.min((double) width / (double) 257, (double) height / (double) 109);

            Graphics2D g2d = image.createGraphics();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.scale(coef, coef);
            paint(g2d);
            g2d.dispose();
        }

        g.drawImage(image, x, y, null);
    }

    /**
     * Paints the transcoded SVG image on the specified graphics context.
     * 
     * @param g Graphics context.
     */
    private static void paint(Graphics2D g) {
        Shape shape = null;

        // 

        // _0

        // _0_0
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(16.8501, 13.1884);
        ((GeneralPath) shape).curveTo(12.4326, 13.2712, 8.91864, 16.9194, 9.0014, 21.3369);
        ((GeneralPath) shape).lineTo(10.3689, 94.3241);
        ((GeneralPath) shape).curveTo(10.4517, 98.7416, 14.0999, 102.256, 18.5174, 102.173);
        ((GeneralPath) shape).lineTo(197.486, 98.8196);
        ((GeneralPath) shape).curveTo(199.383, 98.784, 201.207, 98.075, 202.63, 96.8192);
        ((GeneralPath) shape).lineTo(246.422, 58.1728);
        ((GeneralPath) shape).curveTo(248.219, 56.5867, 249.21, 54.2784, 249.123, 51.8829);
        ((GeneralPath) shape).curveTo(249.036, 49.4875, 247.879, 47.2575, 245.971, 45.8066);
        ((GeneralPath) shape).lineTo(200.811, 11.4658);
        ((GeneralPath) shape).curveTo(199.378, 10.376, 197.619, 9.80146, 195.819, 9.83518);
        ((GeneralPath) shape).lineTo(16.8501, 13.1884);
        ((GeneralPath) shape).closePath();

        g.setPaint(WHITE);
        g.fill(shape);
        g.setPaint(new Color(0xEFD341));
        g.setStroke(new BasicStroke(16, 0, 1, 4));
        g.draw(shape);

        // _0_1
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(17.6644, 15.2002);
        ((GeneralPath) shape).curveTo(15.4553, 15.2002, 13.6644, 16.9911, 13.6644, 19.2002);
        ((GeneralPath) shape).lineTo(13.6644, 92.2002);
        ((GeneralPath) shape).curveTo(13.6644, 94.4093, 15.4553, 96.2002, 17.6644, 96.2002);
        ((GeneralPath) shape).lineTo(196.664, 96.2002);
        ((GeneralPath) shape).curveTo(197.641, 96.2002, 198.584, 95.8428, 199.315, 95.1955);
        ((GeneralPath) shape).lineTo(242.815, 56.6955);
        ((GeneralPath) shape).curveTo(243.702, 55.9113, 244.195, 54.7751, 244.163, 53.5923);
        ((GeneralPath) shape).curveTo(244.131, 52.4094, 243.577, 51.3015, 242.65, 50.5662);
        ((GeneralPath) shape).lineTo(199.15, 16.0662);
        ((GeneralPath) shape).curveTo(198.443, 15.5054, 197.567, 15.2002, 196.664, 15.2002);
        ((GeneralPath) shape).lineTo(17.6644, 15.2002);
        ((GeneralPath) shape).closePath();

        g.setPaint(WHITE);
        g.fill(shape);
        g.setPaint(BLACK);
        g.setStroke(new BasicStroke(8, 0, 1, 4));
        g.draw(shape);

        // _0_2
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(83.4672, 68.2315);
        ((GeneralPath) shape).curveTo(89.9434, 68.0101, 92.9604, 64.5931, 92.8104, 60.2037);
        ((GeneralPath) shape).curveTo(92.5042, 51.2449, 78.8116, 54.2704, 78.6812, 50.4566);
        ((GeneralPath) shape).curveTo(78.632, 49.0174, 79.8221, 48.0042, 82.0168, 47.9292);
        ((GeneralPath) shape).curveTo(84.4634, 47.8456, 87.0822, 48.5845, 89.0865, 50.3171);
        ((GeneralPath) shape).lineTo(91.801, 46.4781);
        ((GeneralPath) shape).curveTo(89.3514, 44.3646, 86.1459, 43.3214, 82.3321, 43.4518);
        ((GeneralPath) shape).curveTo(76.6834, 43.6448, 73.3425, 47.073, 73.479, 51.0666);
        ((GeneralPath) shape).curveTo(73.7877, 60.0974, 87.504, 56.7109, 87.6491, 60.9564);
        ((GeneralPath) shape).curveTo(87.6958, 62.3236, 86.3729, 63.6656, 83.5665, 63.7615);
        ((GeneralPath) shape).curveTo(80.2565, 63.8746, 77.5796, 62.4892, 75.8283, 60.7841);
        ((GeneralPath) shape).lineTo(73.1907, 64.7645);
        ((GeneralPath) shape).curveTo(75.4989, 66.9549, 78.8618, 68.3889, 83.4672, 68.2315);
        ((GeneralPath) shape).closePath();
        ((GeneralPath) shape).moveTo(101.742, 67.6069);
        ((GeneralPath) shape).curveTo(103.649, 67.5417, 104.855, 66.9962, 105.518, 66.3612);
        ((GeneralPath) shape).lineTo(104.428, 62.9404);
        ((GeneralPath) shape).curveTo(104.185, 63.2009, 103.546, 63.4749, 102.863, 63.4982);
        ((GeneralPath) shape).curveTo(101.855, 63.5327, 101.251, 62.7248, 101.214, 61.6454);
        ((GeneralPath) shape).lineTo(100.946, 53.802);
        ((GeneralPath) shape).lineTo(104.472, 53.6815);
        ((GeneralPath) shape).lineTo(104.336, 49.6879);
        ((GeneralPath) shape).lineTo(100.81, 49.8084);
        ((GeneralPath) shape).lineTo(100.647, 45.0591);
        ((GeneralPath) shape).lineTo(96.0421, 45.2165);
        ((GeneralPath) shape).lineTo(96.2044, 49.9658);
        ((GeneralPath) shape).lineTo(93.3261, 50.0641);
        ((GeneralPath) shape).lineTo(93.4626, 54.0578);
        ((GeneralPath) shape).lineTo(96.3409, 53.9594);
        ((GeneralPath) shape).lineTo(96.6508, 63.0261);
        ((GeneralPath) shape).curveTo(96.7566, 66.1203, 98.5401, 67.7163, 101.742, 67.6069);
        ((GeneralPath) shape).closePath();
        ((GeneralPath) shape).moveTo(123.059, 66.4461);
        ((GeneralPath) shape).lineTo(122.675, 55.2206);
        ((GeneralPath) shape).curveTo(122.505, 50.2196, 118.817, 48.7607, 114.859, 48.8959);
        ((GeneralPath) shape).curveTo(112.124, 48.9894, 109.42, 49.9464, 107.361, 51.8898);
        ((GeneralPath) shape).lineTo(109.192, 54.889);
        ((GeneralPath) shape).curveTo(110.621, 53.4714, 112.324, 52.7288, 114.195, 52.6648);
        ((GeneralPath) shape).curveTo(116.498, 52.5861, 118.048, 53.6858, 118.109, 55.4488);
        ((GeneralPath) shape).lineTo(118.188, 57.7874);
        ((GeneralPath) shape).curveTo(116.99, 56.4595, 114.914, 55.7741, 112.611, 55.8528);
        ((GeneralPath) shape).curveTo(109.841, 55.9475, 106.62, 57.6065, 106.759, 61.6721);
        ((GeneralPath) shape).curveTo(106.891, 65.5578, 110.229, 67.3168, 113.0, 67.2221);
        ((GeneralPath) shape).curveTo(115.266, 67.1447, 117.289, 66.2471, 118.428, 64.8033);
        ((GeneralPath) shape).lineTo(118.49, 66.6023);
        ((GeneralPath) shape).lineTo(123.059, 66.4461);
        ((GeneralPath) shape).closePath();
        ((GeneralPath) shape).moveTo(114.729, 64.0652);
        ((GeneralPath) shape).curveTo(112.93, 64.1267, 111.423, 63.2417, 111.368, 61.6226);
        ((GeneralPath) shape).curveTo(111.31, 59.9316, 112.753, 58.9457, 114.552, 58.8843);
        ((GeneralPath) shape).curveTo(116.027, 58.8338, 117.483, 59.2884, 118.273, 60.27);
        ((GeneralPath) shape).lineTo(118.347, 62.4287);
        ((GeneralPath) shape).curveTo(117.626, 63.4619, 116.204, 64.0148, 114.729, 64.0652);
        ((GeneralPath) shape).closePath();
        ((GeneralPath) shape).moveTo(132.161, 66.135);
        ((GeneralPath) shape).lineTo(131.768, 54.6577);
        ((GeneralPath) shape).curveTo(132.486, 53.5166, 134.471, 52.5842, 135.982, 52.5326);
        ((GeneralPath) shape).curveTo(136.486, 52.5153, 136.919, 52.5366, 137.245, 52.5974);
        ((GeneralPath) shape).lineTo(137.093, 48.1361);
        ((GeneralPath) shape).curveTo(134.934, 48.2098, 132.818, 49.5429, 131.648, 51.1318);
        ((GeneralPath) shape).lineTo(131.567, 48.7572);
        ((GeneralPath) shape).lineTo(126.997, 48.9133);
        ((GeneralPath) shape).lineTo(127.591, 66.2912);
        ((GeneralPath) shape).lineTo(132.161, 66.135);
        ((GeneralPath) shape).closePath();
        ((GeneralPath) shape).moveTo(146.4, 66.0806);
        ((GeneralPath) shape).curveTo(148.307, 66.0155, 149.513, 65.4699, 150.175, 64.8349);
        ((GeneralPath) shape).lineTo(149.086, 61.4141);
        ((GeneralPath) shape).curveTo(148.843, 61.6746, 148.204, 61.9486, 147.52, 61.972);
        ((GeneralPath) shape).curveTo(146.513, 62.0064, 145.909, 61.1985, 145.872, 60.1192);
        ((GeneralPath) shape).lineTo(145.604, 52.2758);
        ((GeneralPath) shape).lineTo(149.13, 52.1553);
        ((GeneralPath) shape).lineTo(148.993, 48.1616);
        ((GeneralPath) shape).lineTo(145.467, 48.2821);
        ((GeneralPath) shape).lineTo(145.305, 43.5329);
        ((GeneralPath) shape).lineTo(140.7, 43.6903);
        ((GeneralPath) shape).lineTo(140.862, 48.4395);
        ((GeneralPath) shape).lineTo(137.984, 48.5379);
        ((GeneralPath) shape).lineTo(138.12, 52.5315);
        ((GeneralPath) shape).lineTo(140.998, 52.4332);
        ((GeneralPath) shape).lineTo(141.308, 61.4999);
        ((GeneralPath) shape).curveTo(141.414, 64.5941, 143.198, 66.1901, 146.4, 66.0806);
        ((GeneralPath) shape).closePath();
        ((GeneralPath) shape).moveTo(156.8, 57.4764);
        ((GeneralPath) shape).lineTo(156.966, 41.2612);
        ((GeneralPath) shape).lineTo(151.461, 41.4494);
        ((GeneralPath) shape).lineTo(152.77, 57.6141);
        ((GeneralPath) shape).lineTo(156.8, 57.4764);
        ((GeneralPath) shape).closePath();
        ((GeneralPath) shape).moveTo(155.064, 65.7125);
        ((GeneralPath) shape).curveTo(156.647, 65.6583, 157.933, 64.2816, 157.88, 62.7345);
        ((GeneralPath) shape).curveTo(157.826, 61.1515, 156.449, 59.8657, 154.866, 59.9198);
        ((GeneralPath) shape).curveTo(153.283, 59.9739, 152.034, 61.3494, 152.088, 62.9325);
        ((GeneralPath) shape).curveTo(152.14, 64.4796, 153.481, 65.7666, 155.064, 65.7125);
        ((GeneralPath) shape).closePath();

        g.fill(shape);

    }
}
