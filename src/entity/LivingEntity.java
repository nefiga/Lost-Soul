package entity;


import level.Level;

import java.awt.*;

public class LivingEntity extends Entity {

    protected float velocityX = 0, velocityY = 0;

    public LivingEntity(String name, String image, int x, int y, int w, int h) {
        super(name, image, x, y, w, h);
    }

    public void update() {
        rect.setLocation((int) velocityX, (int) velocityY);
    }

    public void render(Graphics2D g) {

    }

    public void init(Level level) {
        super.init(level);
    }

    public float getVelocityX() {
        return velocityX;
    }

    public float getVelocityY() {
        return velocityY;
    }

    public void setVelocityX(float velocityX) {
        this.velocityX = velocityX;
    }

    public void setVelocityY(float velocityY) {
        this.velocityY = velocityY;
    }
}
