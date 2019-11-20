package HelperClasses;

import java.util.ArrayList;

/**
 * This class represents the champions holding their relevant information
 */
public class Champion {

    private String name;
    private ChampionTier tier;
    private String ability;
    private ArrayList<String> type;

    public Champion(String name, ChampionTier tier, String ability, ArrayList<String> type) {

        this.name = name;
        this.tier = tier;
        this.ability = ability;
        this.type = type;

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
