package tile;

import com.lust_gaming.engine.tile.Tile;

public class GrassTile extends Tile{

    public static Tile grassTile = new GrassTile("tiles/grass.png", 0xff006607);

    public GrassTile(String imageFile, int id) {
        super(imageFile, id);
    }
}
