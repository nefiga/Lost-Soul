package entity;

import classes.PlayerClass;
import item.tool.Tool;
import level.Level;
import util.MainGame;

import java.awt.*;

public class LivingEntity extends Entity {

    protected PlayerClass playerClass;

    protected float velocityX = 0, velocityY = 0;

    public LivingEntity(String name, Image image, PlayerClass playerClass, int x, int y, int w, int h) {
        super(name, image, x, y, w, h);
    }

    public void update() {
        rect.setLocation((int) velocityX, (int) velocityY);
    }

    public void render(Graphics2D g) {

    }

    public void moveX(float move) {
        int moveX;
        moveX = level.moveX(this, (int) move, null);
        x += moveX;
        MainGame.changeMouseOffsetX(moveX);
}

    public void moveY(float move) {
        int moveY;
        moveY = level.moveY(this, (int) move, null);
        y += moveY;
        MainGame.changeMouseOffsetY(moveY);
    }

    public void changeDirection(int direction) {
        this.direction = direction;
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

    public boolean canCollect() {
        return false;
    }

    public void setVelocityX(float velocityX) {
        this.velocityX = velocityX;
    }

    public void setVelocityY(float velocityY) {
        this.velocityY = velocityY;
    }

    public int getStat(int stat) {return playerClass.getStat(stat);}

    public int getSecondaryStat(int stat) { return playerClass.getSecondaryStat(stat);}
}
