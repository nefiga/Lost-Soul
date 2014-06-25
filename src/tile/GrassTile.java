package tile;


public class GrassTile extends Tile{

    public static Tile grassTile = new GrassTile("tiles/grass.png", 0xff006607, 1);

    public GrassTile(String imageFile, int id, int durability) {
        super(imageFile, id, durability);
    }
}
