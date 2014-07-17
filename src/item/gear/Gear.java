package item.gear;

import classes.Stats;
import item.Item;

public class Gear extends Item{

    private int[] stats = new int[Stats.TOTAL];

    public Gear(String name, String image, int id, int stamina, int strength, int intellect, int agility) {
        super(name, image, id);
        stats[Stats.STAMINA] = stamina;
        stats[Stats.STRENGTH] = strength;
        stats[Stats.INTELLECT] = intellect;
        stats[Stats.AGILITY] = agility;
    }

    public int[] getStats() {
        return stats;
    }
}
