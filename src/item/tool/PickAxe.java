package item.tool;

import item.tool.material.Material;

public class PickAxe extends Tool {

    public static Tool woodPickAxe = new PickAxe("WoodPickAxe", "wood_pick.png", 1, Material.wood);

    public PickAxe(String name, String image, int id, Material material) {
        super(name, image, id, material);
        configureStats();
    }

    private void configureStats() {
        if (material == Material.wood) {
            damage += 20;
        }
    }
}
