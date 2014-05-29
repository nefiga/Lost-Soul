package image;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImageManager {

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
}
