package MyEnums;

public enum ChampionTier {
    TIER_1,
    TIER_2,
    TIER_3,
    TIER_4,
    TIER_5,
    TIER_6,
    TIER_7;

    public static ChampionTier getEnum (String value) {

        switch(value) {
            case "Tier 1":
                return TIER_1;

            case "Tier 2":
                return TIER_2;

            case "Tier 3":
                return TIER_3;

            case "Tier 4":
                return TIER_4;

            case "Tier 5":
                return TIER_5;

            case "Tier 6":
                return TIER_6;

            case "Tier 7":
                return TIER_7;
        }

        return null;
    }

}
