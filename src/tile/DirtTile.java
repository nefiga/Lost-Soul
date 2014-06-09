package tile;

import com.lust_gaming.engine.tile.Tile;

public class DirtTile extends Tile{

    public static Tile dirtTile = new DirtTile("tiles/dirt.png", 0xff6a4305);

    public DirtTile(String imageFile, int id) {
        super(imageFile, id);
    }

    public boolean solid() {
        return true;
    }
}
