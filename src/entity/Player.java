package entity;

import actionbar.ActionBar;
import input.*;
import inventory.Inventory;
import item.Item;
import item.gear.weapon.Sword;
import item.tool.PickAxe;
import level.Level;
import util.MainGame;

import java.awt.*;

public class Player extends LivingEntity {

    InventoryInput inventoryInput;
    private Inventory inventory;
    private boolean inventoryOpen;
    private ActionBar actionbar;


    public Player(String name, Image image, int x, int y, int w, int h) {
        super(name, image, x, y, w, h);
        inventory = new Inventory(this, (int) (x - MainGame.getXOffset()), (int) (y - MainGame.getYOffset()));
        inventoryInput = new InventoryInput(inventory, MainGame.getGameInput());
        InputUpdater.addInput(InventoryInput.getName(), inventoryInput);
        actionbar = new ActionBar(20, y * 2 - 92);
        actionbar.addTool(PickAxe.woodPickAxe);
        equip(actionbar.selectedTool());
        for (int i = 0; i < 5; i++) {
            inventory.canAddItem(PickAxe.woodPickAxe, 1);
        }
        inventory.canAddItem(Sword.jaggedSword, 1);
    }

    public void init(Level level) {
        super.init(level);
    }

    public void update() {

    }

    public void interact(int x, int y) {

        // Check to make sure click is with in rage of player and also
        // click is in front of the player
        if (direction == 0) {
        } else if (direction == 1) {
        } else if (direction == 2) {
        } else if (direction == 3) {
        }

        if (level.interactWithEntity(this, x, y)) return;

        level.getTile(x, y, false).interact(level, this, x, y);
    }

    public void openInventory() {
        inventoryOpen = true;
        InputUpdater.setCurrentInput(InventoryInput.getName());
    }

    public void closeInventory() {
        inventoryOpen = false;
        InputUpdater.setCurrentInput(PlayerInput.getName());
    }

    public void setActionbarSlot(int slot) {
        actionbar.setCurrentSlot(slot);
        equip(actionbar.selectedTool());
    }

    public Level getLevel() {
        return level;
    }

    public ActionBar getActionbar() {
        return actionbar;
    }

    public void collectItem(Item item) {
        inventory.canAddItem(item, 1);
    }

    public boolean canCollect() {
        return true;
    }

    public void render(Graphics2D g) {
        g.drawImage(image, MainGame.centerX, MainGame.centerY, null);

        if (inventoryOpen) {
            inventory.render(g);
        }
        actionbar.render(g);
    }
}
