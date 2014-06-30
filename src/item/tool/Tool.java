package item.tool;

import item.Item;
import item.tool.material.Material;

public class Tool extends Item {

    public static Tool woodPickAxe = new PickAxe("WoodPickAxe", "wood_pick.png", 1, Material.wood);

    protected int damage = 15;
    protected Material material;

    public Tool(String name, String image, int id, Material material) {
        super(name, image, id);
        this.material = material;
    }

    public Material getMaterial() {
        return material;
    }

    public int getDamage() {
        return damage;
    }
}
