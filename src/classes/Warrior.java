package classes;

import classes.ability.Ability;

public class Warrior extends PlayerClass {

    public Warrior(String name, int health, int crit, int spellPower, int attackPower) {
        super(name, health, crit, spellPower, attackPower);
    }

    protected void levelUp() {
        if (level >= maxLevel) return;

        level++;
        unlockAbility();
        changeStat(Stats.STAMINA, 14 + (int) (level * .63));
        changeStat(Stats.STRENGTH, 23 + (int) (level * .77));
        adjustSecondaryStats();
    }

    protected void levelDown() {
        if (level <= 1) return;

        level--;
        lockAbility();
        changeStat(Stats.STAMINA, - 14 - (int) (level * .63));
        changeStat(Stats.STRENGTH, - 23 - (int) (level * .77));
        adjustSecondaryStats();
    }

    protected void adjustSecondaryStats() {
        sStats[Stats.HEALTH] = baseHealth + stats[Stats.STAMINA] * 10;
        sStats[Stats.CRIT] = baseCrit + (int) (stats[Stats.AGILITY] * .25);
        sStats[Stats.ATTACKPOWER] = baseAttackPower + (int) (stats[Stats.STRENGTH] * 1.75);
    }

    protected Ability unlockAbility() {
        switch (level) {

        }

        return null;
    }

    protected Ability lockAbility() {
        switch (level) {

        }

        return null;
    }
}
