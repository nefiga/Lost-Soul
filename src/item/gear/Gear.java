package item.gear;

import item.Item;

public class Gear extends Item{

    private int stanima, strength, agility;

    public Gear(String name, String image, int id, int stamina, int strength, int agility) {
        super(name, image, id);
        this.stanima = stamina;
        this.strength = strength;
        this.agility = agility;
    }
}
