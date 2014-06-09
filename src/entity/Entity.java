package entity;

import com.lust_gaming.engine.image.ImageManager;
import com.lust_gaming.engine.level.Level;

import java.awt.*;

public class Entity {

    protected Image image;
    protected Level level;

    protected float x, y;

    public Entity(String image, int x, int y) {
        this.x = x;
        this.y = y;
        this.image = ImageManager.getImage(image);
    }

    public void init(Level level) {
        this.level = level;
    }

    public void update() {

    }

    public void render(Graphics2D g) {

    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public Image getImage() {
        return image;
    }
}
