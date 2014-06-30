package entity;

import item.Item;
import util.MainGame;

import java.awt.*;

public class ItemEntity extends Entity{

    private Item item;

    public ItemEntity(String name, Item item, int x, int y, int w, int h) {
        super(name, item.getImage(), x, y, w, h);
        this.item = item;
    }

    public void update() {

    }

    public void render(Graphics2D g) {
        int rx = (int) (x - MainGame.getXOffset());
        int ry = (int) (y - MainGame.getYOffset());
        g.drawImage(image, rx, ry, null);
    }

    public Item getItem() {
        return item;
    }

    @Override
    public boolean collectable()  {
        return true;
    }
}
