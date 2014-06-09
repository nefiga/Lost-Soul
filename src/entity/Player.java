package entity;

import com.lust_gaming.engine.input.GameAction;
import com.lust_gaming.engine.input.InputManager;
import com.lust_gaming.engine.level.Level;
import util.MainGame;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Player extends LivingEntity{

    InputManager inputManager;

    // Input
    GameAction left, right, up, down;

    public Player(String image, int x, int y, InputManager inputManager) {
        super(image, x, y);
        this.inputManager = inputManager;
        initInput();
    }

    public void init(Level level) {
        super.init(level);
    }

    private void initInput() {
        left = new GameAction("left");
        right = new GameAction("right");
        up = new GameAction("up");
        down = new GameAction("down");
        inputManager.mapToKey(left, KeyEvent.VK_LEFT);
        inputManager.mapToKey(right, KeyEvent.VK_RIGHT);
        inputManager.mapToKey(up, KeyEvent.VK_UP);
        inputManager.mapToKey(down, KeyEvent.VK_DOWN);
    }


    public void update() {
        velocityX = velocityY = 0;
        if (left.isPressed()) velocityX = - 2;
        if (right.isPressed()) velocityX = 2;
        if (up.isPressed()) velocityY = - 2;
        if (down.isPressed()) velocityY = 2;
        if (velocityX != 0 || velocityY != 0) move(velocityX, velocityY);
    }

    public void render(Graphics2D g) {
        g.drawImage(image, (int) x - (int) MainGame.getXOffset(), (int) y - (int) MainGame.getYOffset(), null);
    }
}
