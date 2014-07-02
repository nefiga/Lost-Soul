package menu;

import item.Item;
import item.tool.Tool;
import util.ImageManager;

import java.awt.*;

public abstract class Menu {

    private Image center = ImageManager.getImage("menu_c.png");
    private Image left = ImageManager.getImage("menu_l.png");
    private Image right = ImageManager.flipHorizontal(ImageManager.getBufferedImage("menu_l.png"));
    private Image top = ImageManager.getImage("menu_t.png");
    private Image bottom = ImageManager.flipVertical(ImageManager.getBufferedImage("menu_t.png"));
    private Image topLeft = ImageManager.getImage("menu_tl.png");
    private Image topRight = ImageManager.flipHorizontal(ImageManager.getBufferedImage("menu_tl.png"));
    private Image bottomLeft = ImageManager.flipVertical(ImageManager.getBufferedImage("menu_tl.png"));
    private Image bottomRight = ImageManager.flipVerticalAndHorizontal(ImageManager.getBufferedImage("menu_tl.png"));

    protected int width, height;

    public  Menu(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public abstract void update();

    public void render(Graphics2D g, int startX, int startY) {
        // Center
        for (int i = 1; i < width -1; i++) {
            for (int j = 1; j < height -1;  j++) {
                g.drawImage(center, i * 16 + startX, j * 16 + startY, null);
            }
        }

        // Sides
        for (int i = 1; i < height -1; i++) {
            g.drawImage(left, startX, i * 16 + startY, null);
            g.drawImage(right, (width - 1) * 16 + startX, i * 16 + startY, null);
        }

        // Top & Bottom
        for (int i = 1; i < width - 1; i++) {
            g.drawImage(top, i * 16 + startX, startY, null);
            g.drawImage(bottom, i * 16 + startX, (height -1) * 16 + startY, null);
        }

        // Corners
        g.drawImage(topLeft, startX, startY, null);
        g.drawImage(topRight, 16 * (width -1) + startX, startY, null);
        g.drawImage(bottomLeft, startX, 16 * (height - 1) + startY, null);
        g.drawImage(bottomRight, 16 * (width - 1) + startX, 16 * (height - 1) + startY, null);
    }
}
