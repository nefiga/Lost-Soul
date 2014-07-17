package classes;

import classes.ability.Ability;

import java.util.ArrayList;
import java.util.List;

public abstract class PlayerClass {

    // Classes
    public static PlayerClass warrior = new Warrior("Warrior", 220, 20, 0, 100);

    protected int[] stats = new int[Stats.TOTAL];
    protected int[] sStats = new int[Stats.SECONDARYTOTAL];

    private final String NAME;

    protected List<Ability> abilities = new ArrayList<Ability>();

    protected int level = 1;
    protected final int maxLevel = 60;
    protected int experience = 0;
    protected int experienceCap = 1000;
    protected final float experienceCapChangeRate = 0.35f;

    // Secondary Stats
    protected int baseHealth, baseCrit, baseSpellPower, baseAttackPower;

    public PlayerClass(String name, int health, int crit, int spellPower, int attackPower) {
        this.NAME = name;
        baseHealth = health;
        baseCrit = crit;
        baseSpellPower = spellPower;
        baseAttackPower = attackPower;
        adjustSecondaryStats();
    }

    //-----------GETTERS--------------

    public List<Ability> getAbilities() {
        return abilities;
    }

    public String getName() {
        return NAME;
    }

    public int getStat(int stat) {
        return stats[stat];
    }

    public int getSecondaryStat(int stat) {
        return sStats[stat];
    }

    //------------SETTERS-------------

    public void addAbility(Ability ability) {
        if (!abilities.contains(ability)) abilities.add(ability);
    }

    public void removeAbility(Ability ability) {
        if (abilities.contains(ability)) abilities.remove(ability);
    }

    public void changeExperience(int amount) {
        experience += amount;
        if (experience >= experienceCap) {
            experience = experience - experienceCap;
            levelUp();
            experienceCap *= experienceCapChangeRate;
        } else if (experience <= 0) {
            experienceCap /= experienceCapChangeRate;
            experience = experienceCap - experience;
            levelDown();
        }
    }

    public void changeStat(int stat, int amount) {
        stats[stat] += amount;
        adjustSecondaryStats();
    }

    protected abstract void adjustSecondaryStats();

    protected abstract void levelUp();

    protected abstract void levelDown();

    protected abstract Ability unlockAbility();

    protected abstract Ability lockAbility();
}
