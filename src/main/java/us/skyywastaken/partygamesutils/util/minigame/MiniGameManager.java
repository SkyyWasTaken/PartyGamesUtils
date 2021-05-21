package us.skyywastaken.partygamesutils.util.minigame;

import us.skyywastaken.partygamesutils.util.PartyGamesInfo;
import us.skyywastaken.partygamesutils.util.ScoreboardUtils;

public class MiniGameManager {
    private static MiniGame currentMiniGame = new MiniGame(MiniGameType.NONE);

    public static void updateMiniGameStatus() {
        if(currentMiniGame.getType().tracksScore) {
            currentMiniGame.updateLeaderboardScores(ScoreboardUtils.getScoreRows());
            currentMiniGame.addToElapsedTime(1);
        }
    }

    public static boolean partyGameIsActive() {
        return MiniGameInfoHelper.playerIsInPartyGames();
    }

    public static MiniGame getCurrentMiniGame() {
        return new MiniGame(currentMiniGame);
    }

    public static void setMiniGameType(MiniGameType newMiniGameType) {
        currentMiniGame.setMiniGameType(newMiniGameType);
    }

    public static void setMiniGameNumber(int newMiniGameNumber) {
        currentMiniGame.setGameNumber(newMiniGameNumber);
    }

    public static void resetMiniGameTimer() {
        currentMiniGame.setElapsedTime(0);
    }
}
