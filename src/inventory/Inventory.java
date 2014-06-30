package inventory;

import item.Item;

import java.util.ArrayList;
import java.util.List;

public class Inventory {

    protected int[] itemCount = new int[100];
    protected List<Item> items = new ArrayList<Item>();

    public Inventory() {

    }

    public void addItem(Item item, int amount) {
        if (!items.contains(item)){
            items.add(item);
        }
        itemCount[item.getID()] += amount;
    }

    public void removeItem(Item item, int amount) {
        int itemId = item.getID();
        itemCount[itemId] -= amount;
        if (itemCount[itemId] <= 0) {
            itemCount[itemId] = 0;
            items.remove(item);
        }
    }
}
