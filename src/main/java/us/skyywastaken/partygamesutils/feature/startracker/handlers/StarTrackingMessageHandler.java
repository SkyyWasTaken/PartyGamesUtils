package us.skyywastaken.partygamesutils.feature.startracker.handlers;

import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import us.skyywastaken.partygamesutils.feature.startracker.StarTracker;
import us.skyywastaken.partygamesutils.util.PartyGamesInfo;
import us.skyywastaken.partygamesutils.util.ScoreboardUtils;

public class StarTrackingMessageHandler {
    private final StarTracker STAR_TRACKER;
    public StarTrackingMessageHandler(StarTracker starTracker) {
        this.STAR_TRACKER = starTracker;
    }
    @SubscribeEvent
    public void onMessageReceived(ClientChatReceivedEvent chatEvent) {
        if(!PartyGamesInfo.playerIsInPartyGame()) {
            this.STAR_TRACKER.clearScores();
            return;
        }
        ScoreboardUtils.getScoreboardTitle();
        String message = chatEvent.message.getUnformattedText();
        String[] messageSplit = message.split(" ");
        if(messageSplit.length == 0) {
            return;
        }
        String playerName = messageSplit[messageSplit.length-1];

        if(message.startsWith("  1st    (")) {
            this.STAR_TRACKER.addScoreToPlayer(playerName, 3);
        } else if(message.startsWith("  2nd    (")) {
            this.STAR_TRACKER.addScoreToPlayer(playerName, 2);
        } else if(message.startsWith("  3rd    (")) {
            this.STAR_TRACKER.addScoreToPlayer(playerName, 1);
        }
    }
}
