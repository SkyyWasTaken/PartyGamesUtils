package us.skyywastaken.partygamesutils.command.pgs.PartyCommands;

public enum PGSPartyCommandType {
    ADD, REMOVE, CLEAR, LIST, START, STOP;

    public static PGSPartyCommandType fromString(String string) {
        String modifiedString = string.toUpperCase().trim();
        try {
            return valueOf(modifiedString);
        } catch(IllegalArgumentException e) {
            return null;
        }
    }
}
