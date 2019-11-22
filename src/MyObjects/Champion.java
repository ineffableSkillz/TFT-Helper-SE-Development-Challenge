package MyObjects;

import MyEnums.ChampionTier;

import java.util.ArrayList;

/**
 * This class represents the champions holding their relevant information
 */
public class Champion {

    private String name;
    private ChampionTier tier;
    private String ability;
    private ArrayList<String> type;


    private ArrayList<Integer> health;
    private ArrayList<Integer> dps;
    private ArrayList<Integer> dmg;

    private String cost;
    private int mana;
    private int crit;
    private int attack;
    private int magicResist;
    private int armour;
    private int attackRange;

    private double attackSpeed;



    public Champion(String name, ChampionTier tier, String ability, ArrayList<String> type) {

        this.name = name;
        this.tier = tier;
        this.ability = ability;
        this.type = type;

    }

    public void addStats(String cost, ArrayList<Integer> health, int mana, ArrayList<Integer> dps, ArrayList<Integer> dmg, int crit, double attackSpeed,
                         int attackRange, int magicResist, int armour) {

        this.cost = cost;
        this.health = health;
        this.mana = mana;
        this.dps = dps;
        this.dmg = dmg;
        this.crit = crit;
        this.attackSpeed = attackSpeed;
        this.attackRange = attackRange;
        this.magicResist = magicResist;
        this.armour = armour;

    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "> " + name
                    + "\n\t" + tier
                    + "\n\t" + type
                    + "\n\t" + ability
                    + "\n";
    }
}
