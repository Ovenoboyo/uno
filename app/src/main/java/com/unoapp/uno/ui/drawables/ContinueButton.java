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

/**
 * This class has been automatically generated using
 * <a href="http://ebourg.github.io/flamingo-svg-transcoder/">Flamingo SVG transcoder</a>.
 */
public class ContinueButton implements javax.swing.Icon {

    /** The width of this icon. */
    private int width;

    /** The height of this icon. */
    private int height;

    /** The rendered image. */
    private BufferedImage image;

    /**
     * Creates a new transcoded SVG image.
     */
    public ContinueButton() {
        this(257, 109);
    }

    /**
     * Creates a new transcoded SVG image.
     */
    public ContinueButton(int width, int height) {
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
        ((GeneralPath) shape).moveTo(55.6068, 69.1836);
        ((GeneralPath) shape).curveTo(60.8957, 69.0029, 63.8183, 65.9853, 65.4433, 62.94);
        ((GeneralPath) shape).lineTo(60.9813, 60.9673);
        ((GeneralPath) shape).curveTo(60.0415, 62.9805, 57.9344, 64.5654, 55.4518, 64.6503);
        ((GeneralPath) shape).curveTo(51.0984, 64.7991, 47.8179, 61.5612, 47.6629, 57.0279);
        ((GeneralPath) shape).curveTo(47.508, 52.4945, 50.5598, 49.0403, 54.9133, 48.8915);
        ((GeneralPath) shape).curveTo(57.3958, 48.8066, 59.6074, 50.28, 60.6812, 52.1884);
        ((GeneralPath) shape).lineTo(64.9969, 49.8796);
        ((GeneralPath) shape).curveTo(63.2038, 46.9512, 60.0472, 44.1774, 54.7583, 44.3581);
        ((GeneralPath) shape).curveTo(47.5625, 44.6041, 42.1579, 49.8317, 42.41, 57.2074);
        ((GeneralPath) shape).curveTo(42.6621, 64.5831, 48.411, 69.4296, 55.6068, 69.1836);
        ((GeneralPath) shape).closePath();
        ((GeneralPath) shape).moveTo(76.6373, 68.4649);
        ((GeneralPath) shape).curveTo(82.394, 68.2681, 85.7053, 63.9765, 85.5356, 59.0114);
        ((GeneralPath) shape).curveTo(85.3672, 54.0823, 81.7705, 50.0268, 76.0139, 50.2235);
        ((GeneralPath) shape).curveTo(70.2932, 50.419, 66.9819, 54.7106, 67.1504, 59.6398);
        ((GeneralPath) shape).curveTo(67.3201, 64.6049, 70.9167, 68.6604, 76.6373, 68.4649);
        ((GeneralPath) shape).closePath();
        ((GeneralPath) shape).moveTo(76.4984, 64.3992);
        ((GeneralPath) shape).curveTo(73.6561, 64.4964, 71.993, 62.2119, 71.8996, 59.4775);
        ((GeneralPath) shape).curveTo(71.8074, 56.779, 73.3105, 54.3863, 76.1529, 54.2891);
        ((GeneralPath) shape).curveTo(78.9952, 54.192, 80.6942, 56.4753, 80.7864, 59.1737);
        ((GeneralPath) shape).curveTo(80.8799, 61.9081, 79.3407, 64.3021, 76.4984, 64.3992);
        ((GeneralPath) shape).closePath();
        ((GeneralPath) shape).moveTo(105.522, 67.0454);
        ((GeneralPath) shape).lineTo(105.102, 54.7766);
        ((GeneralPath) shape).curveTo(104.987, 51.3946, 103.078, 49.2985, 99.2642, 49.4289);
        ((GeneralPath) shape).curveTo(96.4219, 49.526, 94.3458, 50.9658, 93.276, 52.3351);
        ((GeneralPath) shape).lineTo(93.1985, 50.0685);
        ((GeneralPath) shape).lineTo(88.6292, 50.2246);
        ((GeneralPath) shape).lineTo(89.2231, 67.6025);
        ((GeneralPath) shape).lineTo(93.7924, 67.4463);
        ((GeneralPath) shape).lineTo(93.3928, 55.7531);
        ((GeneralPath) shape).curveTo(94.1127, 54.6839, 95.4814, 53.6285, 97.2804, 53.5671);
        ((GeneralPath) shape).curveTo(99.2233, 53.5007, 100.511, 54.2851, 100.593, 56.6957);
        ((GeneralPath) shape).lineTo(100.952, 67.2016);
        ((GeneralPath) shape).lineTo(105.522, 67.0454);
        ((GeneralPath) shape).closePath();
        ((GeneralPath) shape).moveTo(115.902, 67.1229);
        ((GeneralPath) shape).curveTo(117.809, 67.0577, 119.015, 66.5122, 119.678, 65.8772);
        ((GeneralPath) shape).lineTo(118.588, 62.4564);
        ((GeneralPath) shape).curveTo(118.345, 62.7169, 117.706, 62.9909, 117.022, 63.0143);
        ((GeneralPath) shape).curveTo(116.015, 63.0487, 115.411, 62.2408, 115.374, 61.1615);
        ((GeneralPath) shape).lineTo(115.106, 53.3181);
        ((GeneralPath) shape).lineTo(118.632, 53.1975);
        ((GeneralPath) shape).lineTo(118.495, 49.2039);
        ((GeneralPath) shape).lineTo(114.969, 49.3244);
        ((GeneralPath) shape).lineTo(114.807, 44.5752);
        ((GeneralPath) shape).lineTo(110.202, 44.7326);
        ((GeneralPath) shape).lineTo(110.364, 49.4818);
        ((GeneralPath) shape).lineTo(107.486, 49.5802);
        ((GeneralPath) shape).lineTo(107.622, 53.5738);
        ((GeneralPath) shape).lineTo(110.501, 53.4754);
        ((GeneralPath) shape).lineTo(110.811, 62.5422);
        ((GeneralPath) shape).curveTo(110.916, 65.6363, 112.7, 67.2324, 115.902, 67.1229);
        ((GeneralPath) shape).closePath();
        ((GeneralPath) shape).moveTo(123.824, 47.1487);
        ((GeneralPath) shape).curveTo(125.335, 47.097, 126.517, 45.8319, 126.465, 44.3208);
        ((GeneralPath) shape).curveTo(126.414, 42.8097, 125.15, 41.6642, 123.639, 41.7158);
        ((GeneralPath) shape).curveTo(122.164, 41.7662, 120.945, 42.9966, 120.997, 44.5077);
        ((GeneralPath) shape).curveTo(121.048, 46.0188, 122.349, 47.1991, 123.824, 47.1487);
        ((GeneralPath) shape).closePath();
        ((GeneralPath) shape).moveTo(126.785, 66.3187);
        ((GeneralPath) shape).lineTo(126.191, 48.9409);
        ((GeneralPath) shape).lineTo(121.622, 49.097);
        ((GeneralPath) shape).lineTo(122.216, 66.4749);
        ((GeneralPath) shape).lineTo(126.785, 66.3187);
        ((GeneralPath) shape).closePath();
        ((GeneralPath) shape).moveTo(147.614, 65.6068);
        ((GeneralPath) shape).lineTo(147.195, 53.338);
        ((GeneralPath) shape).curveTo(147.079, 49.956, 145.171, 47.8599, 141.357, 47.9903);
        ((GeneralPath) shape).curveTo(138.514, 48.0874, 136.438, 49.5272, 135.369, 50.8965);
        ((GeneralPath) shape).lineTo(135.291, 48.6298);
        ((GeneralPath) shape).lineTo(130.722, 48.786);
        ((GeneralPath) shape).lineTo(131.316, 66.1639);
        ((GeneralPath) shape).lineTo(135.885, 66.0077);
        ((GeneralPath) shape).lineTo(135.485, 54.3145);
        ((GeneralPath) shape).curveTo(136.205, 53.2453, 137.574, 52.1899, 139.373, 52.1285);
        ((GeneralPath) shape).curveTo(141.316, 52.0621, 142.603, 52.8465, 142.686, 55.2571);
        ((GeneralPath) shape).lineTo(143.045, 65.763);
        ((GeneralPath) shape).lineTo(147.614, 65.6068);
        ((GeneralPath) shape).closePath();
        ((GeneralPath) shape).moveTo(168.45, 64.8947);
        ((GeneralPath) shape).lineTo(167.856, 47.5169);
        ((GeneralPath) shape).lineTo(163.286, 47.673);
        ((GeneralPath) shape).lineTo(163.689, 59.4382);
        ((GeneralPath) shape).curveTo(162.931, 60.4726, 161.561, 61.492, 159.798, 61.5523);
        ((GeneralPath) shape).curveTo(157.856, 61.6187, 156.569, 60.8702, 156.487, 58.4596);
        ((GeneralPath) shape).lineTo(156.127, 47.9177);
        ((GeneralPath) shape).lineTo(151.557, 48.0739);
        ((GeneralPath) shape).lineTo(151.979, 60.4147);
        ((GeneralPath) shape).curveTo(152.095, 63.7967, 153.965, 65.822, 157.779, 65.6917);
        ((GeneralPath) shape).curveTo(160.621, 65.5945, 162.664, 64.228, 163.805, 62.8562);
        ((GeneralPath) shape).lineTo(163.88, 65.0509);
        ((GeneralPath) shape).lineTo(168.45, 64.8947);
        ((GeneralPath) shape).closePath();
        ((GeneralPath) shape).moveTo(181.169, 64.8923);
        ((GeneralPath) shape).curveTo(183.867, 64.8001, 186.573, 63.8791, 188.246, 62.237);
        ((GeneralPath) shape).lineTo(186.13, 59.3556);
        ((GeneralPath) shape).curveTo(185.052, 60.4731, 183.023, 61.1908, 181.548, 61.2412);
        ((GeneralPath) shape).curveTo(178.598, 61.342, 176.772, 59.5673, 176.409, 57.3825);
        ((GeneralPath) shape).lineTo(189.326, 56.941);
        ((GeneralPath) shape).lineTo(189.291, 55.9336);
        ((GeneralPath) shape).curveTo(189.097, 50.2489, 185.438, 46.4837, 180.257, 46.6608);
        ((GeneralPath) shape).curveTo(174.969, 46.8415, 171.402, 51.0338, 171.574, 56.0709);
        ((GeneralPath) shape).curveTo(171.764, 61.6476, 175.88, 65.073, 181.169, 64.8923);
        ((GeneralPath) shape).closePath();
        ((GeneralPath) shape).moveTo(184.795, 53.9981);
        ((GeneralPath) shape).lineTo(176.232, 54.2907);
        ((GeneralPath) shape).curveTo(176.389, 52.5564, 177.539, 50.3918, 180.382, 50.2946);
        ((GeneralPath) shape).curveTo(183.404, 50.1914, 184.63, 52.3467, 184.795, 53.9981);
        ((GeneralPath) shape).closePath();

        g.fill(shape);

    }
}
