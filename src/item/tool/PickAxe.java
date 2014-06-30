package item.tool;

import item.tool.material.Material;

public class PickAxe extends Tool {

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
