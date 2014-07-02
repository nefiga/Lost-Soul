package actionbar;

import item.tool.Hand;
import item.tool.Tool;
import util.ImageManager;

import java.awt.*;

public class ActionBar {

    private Tool[] tools;

    Image image, highlight;
    int x, y, slotY;

    private int[] slotX;
    int currentSlot = 0;

    public ActionBar(int x, int  y) {
        this.x =  x;
        this.y = y;
        slotY = y + 4;
        slotX = new int[] {x + 4, x + 72, x + 140, x + 208};
        tools = new Tool[] {Hand.hand, Hand.hand, Hand.hand, Hand.hand};
        image = ImageManager.getImage("actionbar.png");
        highlight = ImageManager.getImage("actionbar_highlight.png");
    }

    public void render(Graphics2D g) {
        g.drawImage(image, x, y, null);

        //Draw selected Tool
        g.drawImage(highlight, slotX[currentSlot], y + 4,  null);

        //Drawing Tools
        for (int i = 0; i < 4; i++) {
            g.drawImage(tools[i].getImage(), slotX[i], slotY, null);
        }
    }

    /**
     * Adds {@code tool} to the first open position in the {@code tools} Array
     * and returns null because no Tools were replaced.
     * If all slots are full it will replace the last Tool in the {@code tools} Array
     * and will return the tool that was replaced.
     */
    public Tool addTool(Tool tool) {
        if (tools[0] == Hand.hand) tools[0] = tool;
        else if (tools[1] == Hand.hand) tools[1] = tool;
        else if (tools[2] == Hand.hand) tools[2] = tool;
        else if (tools[3] == Hand.hand) tools[3] = tool;
        else {
            Tool returnTool = tools[3];
            tools[3] = tool;
            return returnTool;
        }
        return null;
    }

    /**
     * Returns the current selected Tool
     */
    public Tool selectedTool() {
        return tools[currentSlot];
    }

    /**
     * Gets the Tool at {@code slot} and replace it with {@code Hand.hand}
     */
    public Tool getTool(int slot) {
        Tool tool = tools[slot];
        tools[slot] = Hand.hand;
        return tool;
    }

    public void setCurrentSlot(int slot) {
        currentSlot = slot;
    }
}
