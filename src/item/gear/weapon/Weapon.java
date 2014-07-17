package item.gear.weapon;

import item.gear.Gear;

public class Weapon extends Gear{

    public static Sword swordDefault = new Sword("swordDefault", "no Image", 91, 15, 5, 0, 0);

    public Weapon(String name, String image, int id, int stamina, int strength, int intellect, int agility) {
        super(name, image, id, stamina, strength, intellect, agility);
    }
}
