package item.resource;

import item.Item;

public class ResourceItem extends Item {

    public static ResourceItem wood = new ResourceItem("Wood", "wood.png", 50);

    public ResourceItem(String name, String image, int id) {
        super(name, image, id);
    }
}
