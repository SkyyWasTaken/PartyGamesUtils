package us.skyywastaken.partygamesutils.feature.startracker;

import java.util.HashMap;

public class StarTracker {
    private final HashMap<String, Integer> PLAYER_SCORE_HASHMAP = new HashMap<>();
    public StarTracker() {
    }

    public void addScoreToPlayer(String playerName, int scoreToAdd) {
        if(PLAYER_SCORE_HASHMAP.containsKey(playerName)) {
            PLAYER_SCORE_HASHMAP.put(playerName, PLAYER_SCORE_HASHMAP.get(playerName) + scoreToAdd);
        } else {
            PLAYER_SCORE_HASHMAP.put(playerName, scoreToAdd);
        }
    }

    public void clearScores() {
        this.PLAYER_SCORE_HASHMAP.clear();
    }

    public HashMap<String, Integer> getPlayerScoreHashMap() {
        return PLAYER_SCORE_HASHMAP;
    }
}
