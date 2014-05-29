package tile;

import image.ImageManager;

import java.awt.*;

public class Tile {

    Image image;
    int id;

    /**
     * Constructor for a new Tile.
     * @param fileName Location of the image example "grassTile.png"
     * @param id Value of the color used in the map image to reference this tile.
     */
    public Tile(String fileName, int id) {
        image = ImageManager.getImage(fileName);
        this.id = id;
    }

    /**
     * Determines if entities can pass through this tile.
     * @return False if entities can pass though this tile.
     */
    public boolean isSolid() {
        return false;
    }

    /**
     * Determines if this tile can be broken.
     * @return True if this tile can be broken.
     */
    public boolean isBrakeable() {
        return false;
    }

    public Image getImage() {
        return image;
    }

    public int getId() {
        return id;
    }
}
