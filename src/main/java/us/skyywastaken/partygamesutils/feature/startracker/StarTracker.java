package us.skyywastaken.partygamesutils.feature.startracker;

import net.minecraftforge.common.MinecraftForge;
import us.skyywastaken.partygamesutils.feature.startracker.handlers.StarTrackingMessageHandler;

import java.util.HashMap;

public class StarTracker {
    private final HashMap<String, Integer> PLAYER_SCORE_HASHMAP = new HashMap<>();
    public StarTracker() {
    }

    public void init() {
        StarTrackingMessageHandler messageHandler = new StarTrackingMessageHandler(this);
        MinecraftForge.EVENT_BUS.register(messageHandler);
    }

    public void addScoreToPlayer(String playerName, int scoreToAdd) {
        if(PLAYER_SCORE_HASHMAP.containsKey(playerName)) {
            PLAYER_SCORE_HASHMAP.put(playerName, PLAYER_SCORE_HASHMAP.get(playerName) + scoreToAdd);
        } else {
            PLAYER_SCORE_HASHMAP.put(playerName, scoreToAdd);
        }
    }

    public void clearScores() {
        if(PLAYER_SCORE_HASHMAP.isEmpty()) {
            return;
        } else {
            this.PLAYER_SCORE_HASHMAP.clear();
        }
    }

    public HashMap<String, Integer> getPlayerScoreHashMap() {
        return PLAYER_SCORE_HASHMAP;
    }
}
