package inventory;

import entity.Player;
import input.InputManager;
import item.Item;
import item.gear.Gear;
import item.gear.armor.Armor;
import item.gear.armor.ChestArmor;
import item.gear.weapon.Weapon;
import util.ImageManager;

import java.awt.*;

public class Inventory {

    protected int[] itemCount = new int[100];
    private Item[][] items = new Item[5][4];
    private Item[][] gear = new Item[2][3];

    private final int inventoryXOffset, inventoryYOffset;
    private final int centerX, centerY;

    private int statsX;
    private int[] statsY = new int[8];
    private int[] gearX = new int[2];
    private int[] gearY = new int[3];
    private int[] inventoryX = new int[5];
    private int[] inventoryY = new int[4];

    Player player;
    Image image;
    Item holding = null;

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
            statsY[y] = 30 + y * 30 - inventoryYOffset;
        }
        statsX = 140 - inventoryXOffset;
    }

    public void render(Graphics2D g) {
        g.drawImage(image, centerX - inventoryXOffset, centerY - inventoryYOffset, null);

        // Rendering Item in the Item inventory section
        for (int xp = 0; xp < 5; xp++) {
            for (int yp = 0; yp < 4; yp++) {
                if (items[xp][yp] != null) {
                    g.drawImage(items[xp][yp].getImage(), centerX + inventoryX[xp], centerY + inventoryY[yp], null);
                }
            }
        }

        // Rendering Gear in the Gear inventory section
        for (int xp = 0; xp < 2; xp++) {
            for (int yp = 0; yp < 3; yp++) {
                if (gear[xp][yp] != null) g.drawImage(gear[xp][yp].getImage(), centerX + gearX[xp], centerY + gearY[yp], null);
            }
        }

        renderStats(g, centerX, centerY);

        // Rendering the held item
        if (holding != null) {
            Image image = holding.getImage();
            g.drawImage(holding.getImage(), InputManager.getMouseX() - image.getWidth(null) / 2, InputManager.getMouseY() - image.getHeight(null) / 2, null);
        }
    }

    private void renderStats(Graphics2D g, int x, int y) {
        g.drawString("Stamina:  138", x + statsX, y + statsY[0]);
        g.drawString("Strength: 327", x + statsX, y + statsY[1]);
        g.drawString("Agility:  21", x + statsX, y + statsY[2]);
        g.drawString("Intellect:  3", x + statsX, y + statsY[3]);
    }

    /**
     * Interacts with the item slot at x, y.
     */
    public void clickItemSlot(int x, int y) {
        // Sets X to the left side of the Inventory
        int slotX = (x - 4 - (centerX - inventoryXOffset));

        // Sets Y to the top of the Item slots
        int slotY = (y - 208 - (centerY - inventoryYOffset));

        // If click is in the Gear part of the Inventory
        if (slotY < 0) {

            // Sets Y to the top of the Inventory
            slotY += 204;

            // If slotY is less than the top of the inventory return
            if (slotY < 0) return;
            slotX = slotX / 68;
            slotY = slotY / 68;

            // Retrieving Gear from the corresponding gear slot
            if (slotX >= 0 && slotX < 2 && slotY >= 0 && slotY < 3) {
                Item gearSlot = gear[slotX][slotY];
                if (holding == null && gearSlot != null) {
                    System.out.println("Trying to pick up gear");
                    holding = gearSlot;
                    gear[slotX][slotY] = null;
                }

                // Placing Gear in the corresponding gear slot
                else if (holding instanceof Gear && gearSlot == null) {
                    System.out.println("Trying to place gear");
                    gear[slotX][slotY] = holding;
                    holding = null;
                }
            }
        }

        // If click is in the Item part of the Inventory
        else {
            slotX = slotX / 68;
            slotY = slotY / 68;

            // Retrieves an Item from the corresponding items slot.
            if (slotX >= 0 && slotX < 5 && slotY >= 0 && slotY < 4) {
                Item itemSlot = items[slotX][slotY];
                if (holding == null && itemSlot != null) {
                    holding = itemSlot;
                    items[slotX][slotY] = null;
                }

                // Places an Item in the corresponding items slot
                else if (holding != null && itemSlot == null) {
                    items[slotX][slotY] = holding;
                    holding = null;
                }
            }
        }
    }

    /**
     * Checks if there is room for the Item to be added
     *
     * @param item   Item to be added
     * @param amount The amount of items
     * @return true if the Item can be added
     */
    public boolean canAddItem(Item item, int amount) {
        for (int x = 0; x < 5; x++) {
            for (int y = 0; y < 4; y++) {
                if (items[x][y] == item && item.canStack()) {
                    itemCount[item.getID()] += amount;
                    return true;
                }
                if (items[x][y] == null) {
                    items[x][y] = item;
                    itemCount[item.getID()] += amount;
                    return true;
                }
            }
        }
        return false;
    }

    public void addGearItem(Item item) {

    }

    public void removeItem(Item item, int amount) {
        int itemId = item.getID();
        for (int x = 0; x < 5; x++) {
            for (int y = 0; y < 4; y++) {
                if (items[x][y] == item) {
                    itemCount[itemId] -= amount;
                    if (itemCount[itemId] <= 0) items[x][y] = null;
                    return;
                }
            }
        }
    }

    public Item[][] getItems() {
        return items;
    }

    public int[] getItemCount() {
        return itemCount;
    }

    public void closeInventory() {
        player.closeInventory();
    }
}
