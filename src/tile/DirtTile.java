package tile;

import entity.Entity;
import entity.StringEntity;
import level.Level;
import util.MainGame;

import java.awt.*;

public class DirtTile extends Tile{

    public static Tile dirtTile = new DirtTile("tiles/dirt.png", 0xff6a4305, 10);

    public DirtTile(String imageFile, int id, int durability) {
        super(imageFile, id, durability);
    }

    public boolean solid() {
        return true;
    }

    public void interact(Level level, Entity entity, int x, int y) {
        int tileX = MainGame.pixelToTile(x);
        int tileY = MainGame.pixelToTile(y);
        level.addStringEntity(new StringEntity("1", x - (int) MainGame.getXOffset(), y - (int) MainGame.getYOffset(), 50, StringEntity.Movement.UP, 3, Color.BLACK));
        level.decreaseTileDurability(tileX, tileY, 1);
        if (level.getTileDurability(tileX,  tileY) <= 0) level.setTile(tileX, tileY, GrassTile.grassTile);
    }

}
