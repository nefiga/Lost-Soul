package tile;

import entity.LivingEntity;
import entity.Player;
import level.Level;
import util.ImageManager;

import java.awt.*;
import java.util.*;

public class Tile {

    protected final int none = -1, top = 0, right = 1, bottom = 2, left = 3;
    protected Image image;
    protected int durability;
    private int id;

    private static java.util.List<Tile> allTiles = new ArrayList<Tile>();

    /**
     * Constructor for a new tile.
     *
     * @param imageFile The location of the imageFile.
     * @param id        value of the color used in the map imageFile to reference this tile.
     */
    public Tile(String imageFile, int id, int durability) {
        this.image = ImageManager.getImage(imageFile);
        this.id = id;
        this.durability = durability;
    }

    /**
     * Normal entities should not be able to pass through solid tiles
     *
     * @return True if the tile is solid.
     */
    public boolean solid() {
        return false;
    }

    /**
     * Controls if tiles can be broken.
     *
     * @return True if this tile can be broken.
     */
    public boolean breakable() {
        return false;
    }

    /**
     * Interacts with the Tile. There is interaction by default.
     */
    public void interact(Level level, Player entity, int x, int y) {

    }

    public int touchingTiles(Level level, int x, int y) {
        return 0;
    }

    /**
     * Adds the tile to the {@link #allTiles} ArrayList. Tiles must be added to this list for the game to know they exist.
     * @param tile A static reference of the tile to be added to the {@link #allTiles} ArrayList.
     */
    public static void addTile(Tile tile) {
        if (!allTiles.contains(tile)) allTiles.add(tile);
    }

    /**
     *
     * @return ArrayList of all the tiles that have been added to the game.
     */
    public static java.util.List<Tile> getTileList() {
        return allTiles;
    }

    /**
     *
     * @return Image of this tile.
     */
    public Image getImage(Level level, int x, int y) {
        return image;
    }

    /**
     * The value of the color used in the map imageFile to reference this tile.
     *
     * @return id
     */
    public int getId() {
        return id;
    }

    public int getDurability() {
        return durability;
    }
}

