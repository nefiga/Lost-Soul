package entity;

import item.Item;
import level.Level;
import util.ImageManager;

import java.awt.*;

public class Entity {

    protected Image image;
    protected Level level;

    protected float x, y;
    protected int direction = 0;
    protected Rectangle rect;
    protected String name;
    protected boolean movable, removed;

    public Entity(String name, Image image, int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.image = image;
        this.name = name;
        rect = new Rectangle(x, y, w, h);
    }

    public Entity(String name, int x, int y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }

    public void init(Level level) {
        this.level = level;
    }

    public void update() {
        if (direction < 0) direction = 3;
        if (direction > 3) direction = 0;
    }

    public void render(Graphics2D g) {

    }

    public void interact(Level level, Entity entity) {
    }

    public void collectItem(Item item) {

    }

    public Item getItem() {
        return null;
    }

    public int pushX(Entity pushingEntity, int moveX) {
        return 0;
    }

    public int pushY(Entity pushingEntity, int moveY) {
        return 0;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public String getName() {
        return name;
    }

    public Rectangle getRect() {
        return rect;
    }

    public Image getImage() {
        return image;
    }

    public boolean isMovable() {
        return movable;
    }

    public boolean isRemoved() {
        return removed;
    }

    public boolean canCollect() {
        return false;
    }

    public boolean collectable() {
        return false;
    }

    public void remove() {
        removed = true;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
        rect.setLocation(x, y);
    }

}