package entity;

import com.lust_gaming.engine.level.Level;
import util.MainGame;

public class LivingEntity extends Entity {

    protected float velocityX = 0, velocityY = 0;

    public LivingEntity(String image, int x, int y) {
        super(image, x, y);
    }

    public void init(Level level) {
        super.init(level);
    }

    public void move(float x, float y) {
        int xMove = MainGame.pixelToTile(x + this.x);
        int yMove = MainGame.pixelToTile(y + this.y);
        int currentX = MainGame.pixelToTile(this.x);
        int currentY = MainGame.pixelToTile(this.y);
        if (!collisionX(xMove, currentY)) this.x += x;
        if (!collisionY(currentX, yMove)) this.y += y;
    }

    public boolean collisionX(int xa, int ya) {
        if (level.getTile(xa, ya).solid()) return true;
        return false;
    }

    public boolean collisionY(int xa, int ya) {
        if (level.getTile(xa, ya).solid()) return true;
        return false;
    }

    public float getVelocityX() {
        return velocityX;
    }

    public float getVelocityY() {
        return velocityY;
    }

    public void setVelocityX(float velocityX) {
        this.velocityX = velocityX;
    }

    public void setVelocityY(float velocityY) {
        this.velocityY = velocityY;
    }
}
