package tile;

import entity.ItemEntity;
import entity.LivingEntity;
import entity.StringEntity;
import item.resource.ResourceItem;
import item.tool.PickAxe;
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

    int random = 0;
    public void interact(Level level, LivingEntity entity, int x, int y) {
        int tileX = MainGame.pixelToTile(x);
        int tileY = MainGame.pixelToTile(y);
        int damage = 3;
        if (entity.getEquippedTool() instanceof PickAxe) damage += entity.getEquippedTool().getDamage();
        if (random == 0){
            int startX =  x - (int) MainGame.getXOffset() - 10;
            int startY = y - (int) MainGame.getYOffset();
            level.addStringEntity(new StringEntity(Integer.toString(damage), startX, startY, 50, StringEntity.Movement.ARCH_LEFT, 3, Color.BLACK));
            random++;
        }
        else {
            int startX =  x - (int) MainGame.getXOffset() + 10;
            int startY = y - (int) MainGame.getYOffset();
            level.addStringEntity(new StringEntity(Integer.toString(damage), startX, startY, 50, StringEntity.Movement.ARCH_RIGHT, 3, Color.BLACK));
            random = 0;
        }
        level.decreaseTileDurability(tileX, tileY, damage);
        if (level.getTileDurability(tileX,  tileY) <= 0) {
            level.setTile(tileX, tileY, GrassTile.grassTile);
            level.addEntity(new ItemEntity(ResourceItem.wood.getName(), ResourceItem.wood, x, y, 32, 32));
        }
    }

}
