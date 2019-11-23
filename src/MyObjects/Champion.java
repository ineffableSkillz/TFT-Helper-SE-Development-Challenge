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

    public Champion(String name, ChampionTier tier, String ability, ArrayList<String> type,String cost,
                        ArrayList<Integer> health, int mana, ArrayList<Integer> dps, ArrayList<Integer> dmg, int crit,
                            double attackSpeed, int attackRange, int magicResist, int armour) {

        this.name = name;
        this.tier = tier;
        this.ability = ability;
        this.type = type;
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

    public String getXMLFormat() {

        return getIndentLevel(1) + "<champion>\n" +
                getIndentLevel(2) + "<name>" + name + "</name>\n" +
                getIndentLevel(2) + "<tier>" + tier + "</tier>\n" +
                getIndentLevel(2) + "<types>\n" + getXMLTypes(3) + getIndentLevel(2) + "</types>\n" +
                getIndentLevel(2) + "<ability>" + ability + "</ability>\n" +
                getIndentLevel(2) + "<cost>" + ((cost == null)? "0" : cost) + "</cost>\n" +
                getIndentLevel(2) + "<healths>\n" + getXMLHealths(3) + getIndentLevel(2) + "</healths>\n" +
                getIndentLevel(2) + "<mana>" + mana + "</mana>\n" +
                getIndentLevel(2) + "<DPS>\n" + getXMLDps(3) + getIndentLevel(2) + "</DPS>\n" +
                getIndentLevel(2) + "<damages>\n" + getXMLDmg(3) + getIndentLevel(2) + "</damages>\n" +
                getIndentLevel(2) + "<atkspeed>" + attackSpeed + "</atkspeed>\n" +
                getIndentLevel(2) + "<atkrange>" + attackRange + "</atkrange>\n" +
                getIndentLevel(2) + "<crit>" + crit + "</crit>\n" +
                getIndentLevel(2) + "<magicresist>" + magicResist + "</magicresist>\n" +
                getIndentLevel(2) + "<armour>" + armour + "</armour>\n" +
                getIndentLevel(1) + "</champion>\n";

    }
    private String getXMLDps(int indent) {
        StringBuilder builder = new StringBuilder();

        if(dps == null) //Unable to obtain champion data
            for(int x = 0; x < 3; x++)
                builder.append(getIndentLevel(indent) + "<dps>" + 0 + "</dps>\n");
        else
            for(Integer x : dps)
                builder.append(getIndentLevel(indent) + "<dps>" + x + "</dps>\n");

        return builder.toString();
    }
    private String getXMLDmg(int indent) {
        StringBuilder builder = new StringBuilder();

        if(dmg == null) //Unable to obtain champion data
            for(int x = 0; x < 3; x++)
                builder.append(getIndentLevel(indent) + "<damage>" + 0 + "</damage>\n");
        else
            for(Integer x : dmg)
                builder.append(getIndentLevel(indent) + "<damage>" + x + "</damage>\n");

        return builder.toString();
    }
    private String getXMLHealths(int indent) {

        StringBuilder builder = new StringBuilder();

        if(health == null) //Unable to obtain champion data
            for(int x = 0; x < 3; x++)
                builder.append(getIndentLevel(indent) + "<health>" + 0 + "</health>\n");
        else
            for(Integer x : health)
                builder.append(getIndentLevel(indent) + "<health>" + x + "</health>\n");

        return builder.toString();
    }
    private String getXMLTypes(int indent) {
        StringBuilder builder = new StringBuilder();

        for(String x : type)
            builder.append(getIndentLevel(indent) + "<type>" + x + "</type>\n");

        return builder.toString();
    }
    private String getIndentLevel(int indents) {

        String tabs = "";
        for(int x = 0; x < indents; x++)
            tabs += "\t";

        return tabs;
    }

    @Override
    public String toString() {
        return "> " + name
                    + "\n\tTier: " + tier
                    + "\n\tType: " + type
                    + "\n\tAbility: " + ability
                    + "\n\tCost: " + cost
                    + "\n\tHealth: " + health
                    + "\n\tMana: " + mana
                    + "\n\tDamage Per Second: " + dps
                    + "\n\tDamage: " + dmg
                    + "\n\tAttack Speed: " + attackSpeed
                    + "\n\tAttack Range: " + attackRange + " Tiles"
                    + "\n\tMagic Resist " + magicResist
                    + "\n\tArmour: " + armour + "\n";

    }
}
