package item.tool;

import item.Item;
import item.tool.material.Material;

public class Tool extends Item {

    protected int damage = 15;
    protected Material material;

    public Tool(String name, String image, int id, Material material) {
        super(name, image, id);
        this.material = material;
    }

    public boolean canStack() {return false;}

    public Material getMaterial() {
        return material;
    }

    public int getDamage() {
        return damage;
    }
}
