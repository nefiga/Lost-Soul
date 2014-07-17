package entity;

import actionbar.ActionBar;
import classes.PlayerClass;
import input.*;
import inventory.Inventory;
import item.Item;
import item.ItemStack;
import item.gear.Gear;
import item.gear.weapon.Sword;
import item.tool.PickAxe;
import item.tool.Tool;
import level.Level;
import util.MainGame;

import java.awt.*;

public class Player extends LivingEntity {

    InventoryInput inventoryInput;
    private Inventory inventory;
    private boolean inventoryOpen;
    private ActionBar actionbar;


    public Player(String name, Image image, PlayerClass playerClass, int x, int y, int w, int h) {
        super(name, image, playerClass, x, y, w, h);
        inventory = new Inventory(this, (int) (x - MainGame.getXOffset()), (int) (y - MainGame.getYOffset()));
        inventoryInput = new InventoryInput(inventory, MainGame.getGameInput());
        InputUpdater.addInput(InventoryInput.getName(), inventoryInput);
        actionbar = new ActionBar(20, y * 2 - 92);
        this.playerClass = playerClass;
        for (int i = 0; i < 5; i++) {
            inventory.canAddItem(new ItemStack(PickAxe.woodPickAxe, 1));
        }
        inventory.canAddItem(new ItemStack(Sword.jaggedSword, 1));
    }

    public void init(Level level) {
        super.init(level);
    }

    public void update() {

    }

    public void interact(int x, int y) {
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

    public void equipGear(Gear gear) {
        int[] stats = gear.getStats();
        for (int i = 0; i < stats.length; i++) {
            playerClass.changeStat(i, stats[i]);
        }
    }

    public void unequipGear(Gear gear) {
        int[] stats = gear.getStats();
        for (int i = 0; i < stats.length; i++) {
            playerClass.changeStat(i, -stats[i]);
        }
    }

    public Tool getEquippedTool() {
        return actionbar.getCurrentTool();
    }

    public void setActionbarSlot(int slot) {
        actionbar.setCurrentSlot(slot);
    }

    public ItemStack[] getTools() {
        return actionbar.getTools();
    }

    public Level getLevel() {
        return level;
    }

    public ActionBar getActionbar() {
        return actionbar;
    }

    public void collectItem(Item item) {
        inventory.canAddItem(new ItemStack(item, 1));
    }

    public boolean canCollect() {
        return true;
    }

    public void render(Graphics2D g) {

        if (inventoryOpen) {
            inventory.render(g);
        }
        else {
            g.drawImage(image, MainGame.centerX, MainGame.centerY, null);
            actionbar.render(g);
        }
    }
}
