package tile;

public class DefaultTile extends Tile {

    public static Tile defaultTile = new DefaultTile("grass.png", -1, 1);

    public DefaultTile(String imageFile, int id, int durability) {
        super(imageFile, id, durability);
    }

    public boolean solid() {
        return true;
    }
}
