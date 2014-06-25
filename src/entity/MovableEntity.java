package entity;

import level.Level;
import util.MainGame;

import java.awt.*;

public class MovableEntity extends Entity {


    public MovableEntity(String name, String image, int x, int y, int w, int h) {
        super(name, image, x, y, w, h);
        movable = true;
    }

    public void render(Graphics2D g) {
        g.drawImage(image, (int) x - (int) MainGame.getXOffset(), (int) y - (int) MainGame.getYOffset(), null);
    }

    public void interact(Level level, Entity entity) {
        level.addStringEntity(new StringEntity("Don't Touch Me!!!", (int) (x - MainGame.getXOffset()), (int) (y - MainGame.getYOffset()), 20, StringEntity.Movement.NONE, 3, Color.BLACK));
    }

    public int pushY(String pushingEntity, int moveY) {
        int moveDistance = level.moveY(name, moveY, pushingEntity);
        y += moveDistance;
        return moveDistance;
    }

    public int pushX(String pushingEntity, int moveX) {
        int moveDistance = level.moveX(name, moveX, pushingEntity);
        x += moveDistance;
        return moveDistance;
    }
}
