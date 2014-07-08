package tile;

import level.Level;
import util.ImageManager;

import java.awt.*;

public class DirtWallTile extends Tile{

    public static Tile dirtWall = new DirtWallTile("tiles/dirt_wall.png", 0xff594100, 1);

    private Image left = ImageManager.getImage("dirt_wall_left.png");
    private Image right = ImageManager.getImage("dirt_wall_right.png");
    private Image top = ImageManager.getImage("dirt_top.png");

    public DirtWallTile(String imageFile, int id, int durability) {
        super(imageFile, id, durability);
    }

    public boolean solid() {
        return true;
    }

    public Image getImage(Level level, int x, int y) {
        if (level.getTile(x, y -1, true).equals(this)) {

        }
        else if (level.getTile(x, y + 1, true).equals(this)) {

        }
            return image;
    }
}
