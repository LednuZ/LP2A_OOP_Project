package pouilleux.userinterface;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class ImageUtil {
    public static ImageIcon rotateImageIcon(ImageIcon icon, double angleDegrees) {
        BufferedImage buffered = toBufferedImage(icon.getImage());
        BufferedImage rotated = rotateBufferedImage(buffered, angleDegrees);
        return new ImageIcon(rotated);
    }

    private static BufferedImage toBufferedImage(Image img) {
        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();
        return bimage;
    }

    private static BufferedImage rotateBufferedImage(BufferedImage image, double angleDegrees) {
        double radians = Math.toRadians(angleDegrees);

        int w = image.getWidth();
        int h = image.getHeight();

        // Compute Dimensions after rotation
        double sin = Math.abs(Math.sin(radians));
        double cos = Math.abs(Math.cos(radians));
        int newW = (int) Math.floor(w * cos + h * sin);
        int newH = (int) Math.floor(h * cos + w * sin);

        BufferedImage rotated = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = rotated.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        // Translate to center and rotate
        AffineTransform at = new AffineTransform();
        at.translate((newW - w) / 2.0, (newH - h) / 2.0);
        at.rotate(radians, w / 2.0, h / 2.0);

        g2d.drawImage(image, at, null);
        g2d.dispose();

        return rotated;
    }
}
