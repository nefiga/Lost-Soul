package tile;

import util.ImageManager;

import java.awt.*;

public class DirtWallTile extends Tile{

    public static Tile dirtWall = new DirtWallTile("tiles/dirt_wall.png", 0xff594100, 1);

    private Image leftImage = ImageManager.getImage("dirt_wall_left.png");
    private Image rightImage = ImageManager.getImage("dirt_wall_right.png");
    private Image topImage = ImageManager.getImage("dirt_top.png");

    public DirtWallTile(String imageFile, int id, int durability) {
        super(imageFile, id, durability);
    }

    public boolean solid() {
        return true;
    }
}
