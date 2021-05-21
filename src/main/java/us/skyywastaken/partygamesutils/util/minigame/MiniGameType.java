package us.skyywastaken.partygamesutils.util.minigame;

public enum MiniGameType {
    ANIMAL_SLAUGHTER("Animal Slaughter", true),
    ANVIL_SPLEEF("Anvil Spleef", false),
    AVALANCHE("Avalanche", false),
    BOMBARDMENT("Bombardment", false),
    CANNON_PAINTING("Cannon Painting", true),
    CHICKEN_RINGS("Chicken Rings", true),
    DIVE("Dive", true),
    FIRE_LEAPERS("Fire Leapers", true),
    FROZEN_FLOOR("Frozen Floor", false),
    HIGH_GROUND("High Ground", true),
    HOE_HOE_HOE("Hoe Hoe Hoe", true),
    JIGSAW_RUSH("Jigsaw Rush", true),
    JUNGLE_JUMP("Jungle Jump", true),
    LAB_ESCAPE("Lab Escape", true),
    LAWN_MOOWER("Lawn Moower", true),
    MINECART_RACING("Minecart Racing", true),
    PIG_FISHING("Pig Fishing", true),
    PIG_JOUSTING("Pig Jousting", false),
    RPG_16("RPG-16", true),
    SHOOTING_RANGE("Shooting Range", true),
    SPIDER_MAZE("Spider Maze", false),
    SUPER_SHEEP("Super Sheep", false),
    THE_FLOOR_IS_LAVA("The Floor is Lava", true),
    TRAMPOLINIO("Trampolinio", true),
    VOLCANO("Volcano", false),
    WORKSHOP("Workshop", true),
    NONE("None", false);

    private String scoreboardText;
    public boolean tracksScore;

    MiniGameType(String scoreboardText, boolean passedTracksScore) {
        this.scoreboardText = scoreboardText;
        this.tracksScore = passedTracksScore;
    }

    public static MiniGameType fromString(String passedString) {
        try {
            return valueOf(passedString.toUpperCase().replaceAll(" ", "_").replaceAll("-", "_"));
        } catch (IllegalArgumentException e) {
            return MiniGameType.NONE;
        }
    }
}
