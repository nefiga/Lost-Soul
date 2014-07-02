package menu;

import entity.LivingEntity;
import inventory.Inventory;
import item.Item;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ItemInventoryMenu extends Menu{

    private List<Item> items  = new ArrayList<Item>();
    private int[] itemCount = new int[100];
    LivingEntity entity;
    Font font;

    public ItemInventoryMenu(Inventory inventory, LivingEntity entity, int width, int height) {
        super(width, height);
        items = inventory.getItems();
        itemCount = inventory.getItemCount();
        this.entity = entity;
        font = new Font(null, Font.PLAIN, 15);
    }

    public void update() {

    }

    public void render(Graphics2D g, int startX, int startY) {
        super.render(g, startX, startY);

        g.setFont(font);
        renderStrings(g, startX, startY);
    }

    public void renderStrings(Graphics2D g, int startX, int startY) {
        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            String text = item.getName() + " " + itemCount[item.getID()];
            g.drawString(text, startX + 8, startY + 30);
        }
    }

}
