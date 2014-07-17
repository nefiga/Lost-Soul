package inventory;

import classes.Stats;
import entity.Player;
import input.InputManager;
import item.Item;
import item.ItemStack;
import item.gear.Gear;
import item.gear.armor.*;
import item.gear.weapon.Weapon;
import item.tool.Tool;
import util.ImageManager;

import java.awt.*;

public class Inventory {

    private ItemStack[][] itemStacks = new ItemStack[5][4];
    private ItemStack[][] gear = new ItemStack[2][3];

    private final int inventoryXOffset, inventoryYOffset;
    private final int centerX, centerY;

    private int statsX;
    private int toolsY;
    private int[] statsY = new int[8];
    private int[] gearX = new int[2];
    private int[] gearY = new int[3];
    private int[] toolsX = new int[4];
    private int[] inventoryX = new int[5];
    private int[] inventoryY = new int[4];

    Player player;
    Image image;
    ItemStack holding = null;

    public Inventory(Player player, int centerX, int centerY) {
        this.player = player;
        this.centerX = centerX;
        this.centerY = centerY;
        image = ImageManager.getImage("inventory.png");
        inventoryXOffset = image.getWidth(null) / 2;
        inventoryYOffset = image.getHeight(null) / 2;

        // Setting inventoryX and inventoryY positions
        for (int x = 0; x < inventoryX.length; x++) {
            inventoryX[x] = 4 + x * 68 - inventoryXOffset;
        }
        for (int y = 0; y < inventoryY.length; y++) {
            inventoryY[y] = 208 + y * 68 - inventoryYOffset;
        }
        // Setting gearX and gearY positions
        for (int x = 0; x < gearX.length; x++) {
            gearX[x] = 4 + x * 68 - inventoryXOffset;
        }
        for (int y = 0; y < gearY.length; y++) {
            gearY[y] = 4 + y * 68 - inventoryYOffset;
        }
        // Setting statsY position
        for (int y = 0; y < statsY.length; y++) {
            statsY[y] = 30 + y * 25 - inventoryYOffset;
        }
        statsX = 140 - inventoryXOffset;

        // Setting actionbar positioon
        for (int i = 0; i < toolsX.length; i++) {
            toolsX[i] = 36 + i * 68 - inventoryXOffset;
        }
        toolsY = 206;
    }

    public void render(Graphics2D g) {
        g.drawImage(image, centerX - inventoryXOffset, centerY - inventoryYOffset, null);

        // Rendering Item in the Item inventory section
        for (int xp = 0; xp < 5; xp++) {
            for (int yp = 0; yp < 4; yp++) {
                ItemStack stack = itemStacks[xp][yp];
                if (stack != null) {
                    stack.render(g, centerX + inventoryX[xp], centerY + inventoryY[yp]);
                }
            }
        }

        // Rendering Gear in the Gear inventory section
        for (int xp = 0; xp < 2; xp++) {
            for (int yp = 0; yp < 3; yp++) {
                if (gear[xp][yp] != null)
                    gear[xp][yp].render(g, centerX + gearX[xp], centerY + gearY[yp]);
            }
        }

        // Rendering the held item
        if (holding != null) {
            holding.render(g, InputManager.getMouseX() - holding.getItem().getImage().getWidth(null) / 2, InputManager.getMouseY() - holding.getItem().getImage().getHeight(null) / 2);
        }

        // Rendering Tools in actionbar
        for (int i = 0; i < 4; i++) {
            Tool tool = (Tool) player.getTools()[i].getItem();
                g.drawImage(tool.getImage(), centerX + toolsX[i], centerY + toolsY, null);
        }

        renderStats(g, centerX, centerY);
    }

    private void renderStats(Graphics2D g, int x, int y) {
        g.drawString("Stamina: " + player.getStat(Stats.STAMINA), x + statsX, y + statsY[0]);
        g.drawString("Strength: " + player.getStat(Stats.STRENGTH), x + statsX, y + statsY[1]);
        g.drawString("Agility:  " + player.getStat(Stats.AGILITY), x + statsX, y + statsY[2]);
        g.drawString("Intellect:  " + player.getStat(Stats.INTELLECT), x + statsX, y + statsY[3]);
        g.drawString("Health:  " + player.getSecondaryStat(Stats.HEALTH), x + statsX, y + statsY[4]);
        g.drawString("Crit:  " + player.getSecondaryStat(Stats.CRIT), x + statsX, y + statsY[5]);
        g.drawString("Attack Power:  " + player.getSecondaryStat(Stats.ATTACKPOWER), x + statsX, y + statsY[6]);
    }

    /**
     * Interacts with the item slot at x, y.
     */
    public void leftClick(int x, int y) {
        // Sets X to the left side of the Inventory
        int slotX = (x - 4 - (centerX - inventoryXOffset));

        // Sets Y to the top of the Item slots
        int slotY = (y - 208 - (centerY - inventoryYOffset));

        if (slotX < 0) return;

        // If click is in the actionbar part of the inventory
        if (slotY >= 266 && slotY < 340) {
            slotX -= 34;
            if (slotX < 0) return;
            slotX /= 68;

            if (slotX >= 0 && slotX < 4) {
                if (holding == null) {
                    holding = player.getActionbar().takeTool(slotX);
                }
                else if (holding.getItem() instanceof Tool) {
                    holding = player.getActionbar().addToolToSlot(slotX, holding);
                }
            }
        }

        // If click is in the Gear part of the Inventory
        else if (slotY < 0) {

            // Sets slotY to the top of the Gear Inventory
            slotY += 204;

            // If slotY is less than the top of the Gear Inventory return
            if (slotY < 0) return;

            slotX = slotX / 68;
            slotY = slotY / 68;

            // Retrieving Gear from the corresponding gear slot
            if (slotX >= 0 && slotX < 2 && slotY >= 0 && slotY < 3) {
                ItemStack gearSlot = gear[slotX][slotY];
                if (holding == null && gearSlot != null) {
                    holding = gearSlot;
                    player.unequipGear((Gear) holding.getItem());
                    gear[slotX][slotY] = null;
                }

                // Placing Gear in the corresponding gear slot
                else if (canPlaceInGearSlot(holding, slotX, slotY) && gearSlot == null) {
                    player.equipGear((Gear) holding.getItem());
                    gear[slotX][slotY] = holding;
                    holding = null;
                }
            }
        }

        // If click is in the Item part of the Inventory
        else {
            slotX = slotX / 68;
            slotY = slotY / 68;

            // Switches holding item with the item at itemStacks[slotX][slotY].
            if (slotX >= 0 && slotX < 5 && slotY >= 0 && slotY < 4) {
                ItemStack itemSlot = itemStacks[slotX][slotY];
                ItemStack tempItem = holding;
                if (holding != null && itemSlot != null && holding.getItem().equals(itemSlot.getItem())) {
                    holding = itemSlot.mergeStacks(holding);
                }
                else  {
                    holding = itemSlot;
                    itemStacks[slotX][slotY] = tempItem;
                }
            }
        }
    }

    public void rightClick(int x, int y) {
        // Sets X to the left side of the Inventory
        int slotX = (x - 4 - (centerX - inventoryXOffset));

        // Sets Y to the top of the Item slots
        int slotY = (y - 208 - (centerY - inventoryYOffset));

        slotX = slotX / 68;
        slotY = slotY / 68;

        if (slotX >= 0 && slotX < 5 && slotY >= 0 && slotY < 4) {
            ItemStack stack = itemStacks[slotX][slotY];
            if (holding == null && stack != null) {
                holding = stack.splitStack();
            } else if (stack == null && holding != null) {
                itemStacks[slotX][slotY] = holding.placeOne();
                if (holding.getSize() <= 0) holding = null;
            }else if (stack != null && holding != null) {
                stack.mergeStacks(holding.placeOne());
                if (holding.getSize() <= 0) holding = null;
            }
        }

    }

    /**
     * Checks if the Item being held matches the gear slot.
     */
    private boolean canPlaceInGearSlot(ItemStack stack, int slotX, int slotY) {
        switch (slotX) {
            case 0:
                switch (slotY) {
                    case 0:
                        if (stack.getItem() instanceof ChestArmor) return true;
                        break;
                    case 1:
                        if (stack.getItem() instanceof LegArmor) return true;
                        break;
                    case 2:
                        if (stack.getItem() instanceof BootArmor) return true;
                        break;
                }
                break;
            case 1:
                switch (slotY) {
                    case 0:
                        if (stack.getItem() instanceof HeadArmor) return true;
                        break;
                    case 1:
                        if (stack.getItem() instanceof Weapon) return true;
                        break;
                    case 2:
                        if (stack.getItem() instanceof FingerArmor) return true;
                        break;
                }
                break;
        }
        return false;
    }

    /**
     * Checks if there is room for the Item to be added
     *
     * @param stack   Item to be added
     * @return true if the Item can be added
     */
    public boolean canAddItem(ItemStack stack) {
        // Tries to add the ItemStack to a matching ItemStack
        for (int x = 0; x < 5; x++) {
            for (int y = 0; y < 4; y++) {
                if (itemStacks[x][y] != null && itemStacks[x][y].canAdd(stack.getItem(), stack.getSize())) {
                    return true;
                }
            }
        }

        // If no matching ItemStacks are available tries to add the ItemStack to an empty slot.
        for (int x = 0; x < 5; x++) {
            for (int y = 0; y < 4; y++) {
                if (itemStacks[x][y] == null) {
                    itemStacks[x][y] = stack;
                    return true;
                }
            }
        }

        return false;
    }

    public void removeItem(Item item, int amount) {

    }

    public ItemStack[][] getItemStacks() {
        return itemStacks;
    }

    public void closeInventory() {
        player.closeInventory();
    }
}
