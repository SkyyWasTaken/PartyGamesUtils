package us.skyywastaken.partygamesutils.util;

public class PartyGamesInfo {
    public static int getCurrentGameNumber() {
        String currentGameNumberRow = ScoreboardUtils.getGameNumberRow();
        int index = currentGameNumberRow.indexOf("/") - 1;
        if (currentGameNumberRow.length() <= index || index < 0) {
            return 0; // This fixes a race condition that can crash the game.
        }
        return Integer.parseInt(String.valueOf(currentGameNumberRow.charAt(index)));
    }



    public static boolean playerIsInPartyGame() {
        return ScoreboardUtils.getScoreboardTitle().equals("PartyGames");
    }

    public static String getGameNameRow() {
        return ScoreboardUtils.getUnformattedLineFromScoreboard(12);
    }
}
