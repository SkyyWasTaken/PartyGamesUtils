package us.skyywastaken.partygamesutils.util.minigame;

import java.util.LinkedHashMap;
import java.util.Map;

public class MiniGame {
    private MiniGameType MINI_GAME_TYPE;
    private final LinkedHashMap<String, Integer> PLAYER_SCORES = new LinkedHashMap<>();
    private int gameNumber = 0;
    private long elapsedTime = 0;

    public MiniGame(MiniGameType passedMiniGameType) {
        this.MINI_GAME_TYPE = passedMiniGameType;
    }

    public MiniGame(MiniGame originalMiniGame) {
        this.MINI_GAME_TYPE = originalMiniGame.getMiniGameType();
        for(Map.Entry<String, Integer> currentScore : originalMiniGame.PLAYER_SCORES.entrySet()) {
            this.PLAYER_SCORES.put(currentScore.getKey(), currentScore.getValue());
        }
        this.gameNumber = originalMiniGame.gameNumber;
        this.elapsedTime = originalMiniGame.elapsedTime;
    }

    public MiniGameType getType() {
        return this.MINI_GAME_TYPE;
    }

    public void updatePlayerScore(String playerName, int score) {
        if(score > 0) {
            this.PLAYER_SCORES.put(playerName, score);
        }
    }

    public void setMiniGameType(MiniGameType passedMiniGameType) {
        this.MINI_GAME_TYPE = passedMiniGameType;
    }
    
    public MiniGameType getMiniGameType() {
        return this.MINI_GAME_TYPE;
    }

    public void updateLeaderboardScores(LinkedHashMap<String, Integer> passedScores) {
        PLAYER_SCORES.clear();
        for(Map.Entry<String, Integer> currentScore : passedScores.entrySet()) {
            updatePlayerScore(currentScore.getKey(), currentScore.getValue());
        }
    }

    public void setGameNumber(int newGameNumber) {
        this.gameNumber = newGameNumber;
    }
    
    public int getGameNumber() {
        return this.gameNumber;
    }

    public void addToElapsedTime(long timeToAdd) {
        elapsedTime += timeToAdd;
    }

    public void setElapsedTime(long newTime) {
        elapsedTime = newTime;
    }
}
