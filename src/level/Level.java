package level;

import entity.Entity;
import entity.StringEntity;
import tile.DefaultTile;
import tile.Tile;
import util.Collision;
import util.GameLoop;
import util.ImageManager;
import util.InputManager;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Level {

    protected InputManager inputManager;
    protected Collision collision;

    protected int[] map;
    protected int[] tileDurability;
    protected List<Tile> tiles = new ArrayList<Tile>();
    protected List<StringEntity> stringEntities = new ArrayList<StringEntity>();
    protected List<Entity> entities = new ArrayList<Entity>();

    protected int imageWidth, imageHeight;
    protected final int width, height;
    protected int tileSize;

    /**
     * Constructor for new Level.
     *
     * @param imageFile    Name of the image file used to lay out the map.
     * @param width        Width of the screen.
     * @param height       Height of the screen.
     * @param inputManager Reference to the InputManager.
     */
    public Level(String imageFile, int width, int height, InputManager inputManager) {
        this.width = width;
        this.height = height;
        Image image = ImageManager.getImage(imageFile);
        imageWidth = image.getWidth(null);
        imageHeight = image.getHeight(null);
        map = MapGenerator.generateMap(ImageManager.getBufferedImage(imageFile), imageWidth, imageHeight);
        tileDurability = new int[map.length];
        tiles = MapGenerator.getTiles(this, ImageManager.getBufferedImage(imageFile), imageWidth, imageHeight);
        this.inputManager = inputManager;
        tileSize = GameLoop.getTileSize();
        collision = new Collision(this);
        init();
        initInput();
    }

    public Level(int width, int height, InputManager inputManager) {
        this.width = width;
        this.height = height;
        this.inputManager = inputManager;
        tileSize = GameLoop.getTileSize();
        init();
        initInput();
    }

    /**
     * Main Initialize method for (images, animation, ect...).
     */
    public abstract void init();

    /**
     * Initializes the input for the Level. If no input is specified there will be no input for that Level.
     */
    public abstract void initInput();

    public abstract void update();

    public abstract void render(Graphics2D g);

    /**
     * Gets the Tile at xa + ya * {@code imageWidth} in the {@code map} array.
     *
     * @param xa            The x position used to find the Tile in the {@code map} array.
     * @param ya            The x position used to find the Tile in the {@code map} array.
     * @param tilePrecision If the xa and ya variables passed in are in pixel precision or tile precision.
     * @return The Tile at the xa + ya * maps image width.
     */
    public Tile getTile(float xa, float ya, boolean tilePrecision) {
        int x, y;
        if (tilePrecision) {
            x = (int) xa;
            y = (int) ya;
        } else {
            x = GameLoop.pixelToTile(xa);
            y = GameLoop.pixelToTile(ya);
        }

        if (x > imageWidth - 1 || x < 0 || y > imageHeight - 1 || y < 0) return DefaultTile.defaultTile;
        for (int i = 0; i < tiles.size(); i++) {
            Tile tile = tiles.get(i);
            if (tile.getId() == map[x + y * imageWidth]) return tile;
        }
        return DefaultTile.defaultTile;
    }

    public void setTile(int x, int y, Tile tile) {
        map[x + y * imageWidth] = tile.getId();
    }

    public void setTileDurability(int position, int durability) {
        tileDurability[position] = durability;
    }

    public int getTileDurability(int x, int y) {
        return tileDurability[x + y * imageWidth];
    }

    public void decreaseTileDurability(int x, int y, int amount) {
        tileDurability[x + y * imageWidth] -= amount;
    }

    /**
     * Renders the map starting at the top left corner of the screen and the Tile at xp + yp * {@code imageWidth} in the {@code map} array.
     *
     * @param g  Reference to Graphics2D.
     * @param xp The x position used to find the Tile in the {@code map} array.
     * @param yp The y position used to find the TIle int the {@code map} array.
     */
    public void renderMap(Graphics2D g, int xp, int yp) {
        int startX = GameLoop.pixelToTile(xp) - 1;
        int endX = GameLoop.pixelToTile(xp) + GameLoop.pixelToTile(width) + 2;
        int startY = GameLoop.pixelToTile(yp) - 1;
        int endY = GameLoop.pixelToTile(yp) + GameLoop.pixelToTile(height) + 2;

        for (int y = startY; y < endY; y++) {
            for (int x = startX; x < endX; x++) {
                g.drawImage(getTile(x, y, true).getImage(), x * tileSize - xp, y * tileSize - yp, null);
            }
        }
    }

    public boolean interactWithEntity(Entity entity, int x, int y) {
        return collision.interactWithEntity(entity, x, y);
    }

    /**
     * Adds a StringEntity to the {@code stringEntities}
     *
     * @param entity The StringEntity to be added
     */
    public void addStringEntity(StringEntity entity) {
        stringEntities.add(entity);
    }

    /**
     * Updates all the StringEntity's in the {@code stringEntities} ArrayList.
     * Also removes any StringEntity that {@code isRemoved}
     */
    public void updateStringEntities() {
        for (int i = 0; i < stringEntities.size(); i++) {
            StringEntity stringEntity = stringEntities.get(i);
            stringEntity.update();
            if (stringEntity.isRemoved()) stringEntities.remove(stringEntity);
        }
    }

    /**
     * Renders all the StringEntity's in the {@code stringEntities} ArrayList.
     */
    public void renderStringEntities(Graphics2D g) {
        for (int i = 0; i < stringEntities.size(); i++) {
            stringEntities.get(i).render(g);
        }
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
        collision.addEntity(entity);
    }

    public void removeEntity(Entity entity) {
        entities.remove(entity);
        collision.removeEntity(entity);
    }

    public void updateEntities() {
        for (int i = 0; i < entities.size(); i++) {
            Entity entity = entities.get(i);
            entity.update();
            if (entity.isRemoved()) {
                entities.remove(i);
                collision.removeEntity(entity);
            }
        }
    }

    public void renderEntities(Graphics2D g) {
        for (int i = 0; i < entities.size(); i++) {
            entities.get(i).render(g);
        }
    }

    public int moveX(Entity entity, int x, Entity excludeEntity) {
        return collision.getMaxMoveX(entity, x, excludeEntity);
    }

    public int moveY(Entity entity, int y, Entity excludeEntity) {
        return collision.getMaxMoveY(entity, y, excludeEntity);
    }

}
