package entity;

import actionbar.ActionBar;
import inventory.Inventory;
import item.Item;
import item.tool.PickAxe;
import item.tool.Tool;
import menu.ItemInventoryMenu;
import menu.Menu;
import level.Level;
import util.GameAction;
import util.InputManager;
import util.MainGame;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.security.Key;

public class Player extends LivingEntity {

    InputManager inputManager;
    protected Inventory inventory;
    private ItemInventoryMenu itemMenu;
    private boolean menuOpen;
    private ActionBar actionBar;
    private Tool equipedTool;

    // Input
    GameAction left, right, up, down, interact, openMenu, action1, action2, action3, action4;

    public Player(String name, Image image, int x, int y, int w, int h, InputManager inputManager) {
        super(name, image, x, y, w, h);
        this.inputManager = inputManager;
        initInput();
        inventory = new Inventory();
        actionBar = new ActionBar(20, y * 2 - 92);
        actionBar.addTool(PickAxe.woodPickAxe);
        equipedTool = actionBar.selectedTool();
    }

    public void init(Level level) {
        super.init(level);
    }

    private void initInput() {
        left = new GameAction("left");
        right = new GameAction("right");
        up = new GameAction("up");
        down = new GameAction("down");
        interact = new GameAction("interact", GameAction.DETECT_INITIAL_PRESS_ONLY);
        openMenu = new GameAction("openMenu", GameAction.DETECT_INITIAL_PRESS_ONLY);
        action1 = new GameAction("action1", GameAction.DETECT_INITIAL_PRESS_ONLY);
        action2 = new GameAction("action2", GameAction.DETECT_INITIAL_PRESS_ONLY);
        action3 = new GameAction("action3", GameAction.DETECT_INITIAL_PRESS_ONLY);
        action4 = new GameAction("action4", GameAction.DETECT_INITIAL_PRESS_ONLY);

        inputManager.mapToKey(left, KeyEvent.VK_A);
        inputManager.mapToKey(right, KeyEvent.VK_D);
        inputManager.mapToKey(up, KeyEvent.VK_W);
        inputManager.mapToKey(down, KeyEvent.VK_S);
        inputManager.mapToKey(interact, KeyEvent.VK_SPACE);
        inputManager.mapToKey(openMenu, KeyEvent.VK_M);
        inputManager.mapToKey(action1, KeyEvent.VK_1);
        inputManager.mapToKey(action2, KeyEvent.VK_2);
        inputManager.mapToKey(action3, KeyEvent.VK_3);
        inputManager.mapToKey(action4, KeyEvent.VK_4);

    }


    public void update() {
        velocityX = velocityY = 0;

        updateInput();

        if (velocityX != 0) x += level.moveX(this, (int) velocityX, null);
        if (velocityY != 0) y += level.moveY(this, (int) velocityY, null);
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
        if (openMenu.isPressed()) {
            itemMenu = new ItemInventoryMenu(inventory, this, 10, 8);
            menuOpen = true;
        }
        if (action1.isPressed()) {
            actionBar.setCurrentSlot(0);
            equipedTool = actionBar.selectedTool();
        }
        if (action2.isPressed()) {
            actionBar.setCurrentSlot(1);
            equipedTool = actionBar.selectedTool();
        }
        if (action3.isPressed()) {
            actionBar.setCurrentSlot(2);
            equipedTool = actionBar.selectedTool();
        }
        if (action4.isPressed()) {
            actionBar.setCurrentSlot(3);
            equipedTool = actionBar.selectedTool();
        }
    }

    private void interact() {
        int interactX = (int) x;
        int interactY = (int) y;
        if (direction == 0) {
            interactX += 32;
            interactY -= 32;
        } else if (direction == 1) {
            interactX += 96;
            interactY += 32;
        } else if (direction == 2) {
            interactX += 32;
            interactY += 96;
        } else if (direction == 3) {
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

        if (menuOpen) {
            itemMenu.render(g, (int) (x - MainGame.getXOffset()), (int) (y - MainGame.getYOffset()));
        }
        actionBar.render(g);
    }
}
