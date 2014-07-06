package item.gear.weapon;

public class Sword extends Weapon{

    public static Weapon jaggedSword = new Sword("Jagged Sword", "sword.png", 50, 24, 56, 0);

    public Sword(String name, String image, int id, int stamina, int strength, int agility) {
        super(name, image, id, stamina, strength, agility);
    }
}
