package item.tool;

import item.tool.material.Material;

public class Hand extends Tool{

    public static Hand hand = new Hand("hand", "empty.png", 0, null);

    public Hand(String name, String image, int id, Material material) {
        super(name, image, id, material);
    }
}
