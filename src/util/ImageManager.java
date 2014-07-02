package util;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class  ImageManager {

    static String image;

    public static Image getImage(String file) {
        return new ImageIcon("res/" + file).getImage();
    }

    public static BufferedImage getBufferedImage(String imageFile) {
        image = "/" + imageFile;
        try {
            BufferedImage image1 = ImageIO.read(ImageManager.class.getResource(image));
            return image1;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Image flipHorizontal(BufferedImage image){
        BufferedImage flipped;
        AffineTransform flip = AffineTransform.getScaleInstance(-1, 1);
        flip.translate(-image.getHeight(null), 0);
        AffineTransformOp transform = new AffineTransformOp(flip, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        flipped = transform.filter(image, null);
        return new ImageIcon(flipped).getImage();
}

    public static Image flipVertical(BufferedImage image) {
        BufferedImage flipped;
        AffineTransform flip = AffineTransform.getScaleInstance(1, -1);
        flip.translate(0, -image.getWidth(null));
        AffineTransformOp transform = new AffineTransformOp(flip, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        flipped = transform.filter(image, null);
        return new ImageIcon(flipped).getImage();
    }

    public static Image flipVerticalAndHorizontal(BufferedImage image) {
        BufferedImage flipped;
        AffineTransform flip = AffineTransform.getScaleInstance(-1, -1);
        flip.translate(-image.getHeight(null), -image.getWidth(null));
        AffineTransformOp transform = new AffineTransformOp(flip, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        flipped = transform.filter(image, null);
        return new ImageIcon(flipped).getImage();
    }
}
