package item;

import util.ImageManager;

import java.awt.*;

public class Item {

    protected final int id;
    protected String name;
    protected Image image;

    public Item(String name, String image, int id) {
        this.name = name;
        this.image = ImageManager.getImage(image);
        this.id = id;
    }

    public boolean canStack() {return false;}

    public int getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Image getImage() {
        return image;
    }
}
