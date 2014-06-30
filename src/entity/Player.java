package entity;

import inventory.Inventory;
import item.Item;
import item.resource.ResourceItem;
import item.tool.PickAxe;
import item.tool.Tool;
import level.Level;
import util.GameAction;
import util.InputManager;
import util.MainGame;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Player extends LivingEntity {

    InputManager inputManager;
    protected Inventory inventory;

    // Input
    GameAction left, right, up, down, interact;

    public Player(String name, Image image, int x, int y, int w, int h, InputManager inputManager) {
        super(name, image, x, y, w, h);
        this.inputManager = inputManager;
        initInput();
        inventory = new Inventory();
    }

    public void init(Level level) {
        super.init(level);
    }

    private void initInput() {
        left = new GameAction("left");
        right = new GameAction("right");
        up = new GameAction("up");
        down = new GameAction("down");
        interact = new  GameAction("interact", GameAction.DETECT_INITIAL_PRESS_ONLY);
        inputManager.mapToKey(left, KeyEvent.VK_LEFT);
        inputManager.mapToKey(right, KeyEvent.VK_RIGHT);
        inputManager.mapToKey(up, KeyEvent.VK_UP);
        inputManager.mapToKey(down, KeyEvent.VK_DOWN);
        inputManager.mapToKey(interact, KeyEvent.VK_SPACE);
    }


    public void update() {
        velocityX = velocityY = 0;

        updateInput();

        if (velocityX != 0) x += level.moveX(this, (int) velocityX, null);
        if (velocityY != 0)  y += level.moveY(this, (int) velocityY, null);
    }

    private void updateInput() {
        if (left.isPressed()) {
            velocityX = -5;
            direction = 3;
        }
        if (right.isPressed()) {
            velocityX = 5;
            direction = 1;
        }
        if (up.isPressed()) {
            velocityY = -5;
            direction = 0;
        }
        if (down.isPressed()) {
            velocityY = 5;
            direction = 2;
        }
        if (interact.isPressed()) interact();
    }

    private void interact() {
        int interactX = (int) x;
        int interactY = (int) y;
        if (direction == 0) {
            interactX += 32;
            interactY -= 32;
        }
        else if (direction == 1) {
            interactX += 96;
            interactY += 32;
        }
        else if (direction == 2) {
            interactX += 32;
            interactY += 96;
        }
        else if (direction == 3) {
            interactX -= 32;
            interactY += 32;
        }
        if (level.interactWithEntity(this, interactX, interactY)) return;

        level.getTile(interactX, interactY, false).interact(level, this, interactX, interactY);
    }

    public void collectItem(Item item) {
        inventory.addItem(item, 1);
    }

    public boolean canCollect() {
        return true;
    }

    public void render(Graphics2D g) {
        g.drawImage(image, (int) x - (int) MainGame.getXOffset(), (int) y - (int) MainGame.getYOffset(), null);
        g.drawImage(PickAxe.woodPickAxe.getImage(), (int) x + 74, (int) y, null);
    }
}
