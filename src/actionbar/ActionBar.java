package actionbar;

import item.ItemStack;
import item.tool.Hand;
import item.tool.Tool;
import util.ImageManager;

import java.awt.*;

public class ActionBar {

    private ItemStack[] tools = new ItemStack[4];

    Image image, highlight;
    int x, y, slotY;

    private int[] slotX;
    int currentSlot = 0;

    public ActionBar(int x, int  y) {
        this.x =  x;
        this.y = y;
        slotY = y + 4;
        slotX = new int[] {x + 4, x + 72, x + 140, x + 208};
        for (int i = 0; i < tools.length; i++) {
            tools[i] = new ItemStack(Hand.hand, 1);
        }
        image = ImageManager.getImage("actionbar.png");
        highlight = ImageManager.getImage("actionbar_highlight.png");
    }

    public void render(Graphics2D g) {
        g.drawImage(image, x, y, null);

        //Draw selected Tool
        g.drawImage(highlight, slotX[currentSlot], y + 4,  null);

        //Drawing Tools
        for (int i = 0; i < 4; i++) {
            g.drawImage(tools[i].getItem().getImage(), slotX[i], slotY, null);
        }
    }

    public ItemStack[] getTools() {
        return tools;
    }

    public ItemStack takeTool(int slot) {
        ItemStack returnItem = tools[slot];
        tools[slot] = new ItemStack(Hand.hand, 1);
        return returnItem;
    }

    /**
     * Returns the current selected Tool
     */
    public Tool getCurrentTool() {
        return (Tool) tools[currentSlot].getItem();
    }

    /**
     * Sets the current selected slot
     */
    public void setCurrentSlot(int slot) {
        currentSlot = slot;
    }

    public ItemStack addToolToSlot(int slot, ItemStack tool) {
        if (tools[slot].getItem() == Hand.hand) {
            tools[slot] = tool;
            return null;
        }
        ItemStack returnItem = tools[slot];
        tools[slot] = tool;
        return returnItem;
    }
}
