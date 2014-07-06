package item.gear.armor;

import item.gear.Gear;

public class Armor extends Gear{

    public static ChestArmor chestDefault = new ChestArmor("chestDefault", "no image", 90, 0, 0, 0);

    public Armor(String name, String image, int id, int stamina, int strength, int agility) {
        super(name, image, id, stamina, strength, agility);
    }
}
