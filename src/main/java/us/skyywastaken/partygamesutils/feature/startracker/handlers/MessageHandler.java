package us.skyywastaken.partygamesutils.feature.startracker.handlers;

import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import us.skyywastaken.partygamesutils.feature.startracker.StarTracker;
import us.skyywastaken.partygamesutils.util.HypixelUtils;
import us.skyywastaken.partygamesutils.util.ScoreboardUtils;

public class MessageHandler {
    private final StarTracker STAR_TRACKER;
    public MessageHandler(StarTracker starTracker) {
        this.STAR_TRACKER = starTracker;
    }
    @SubscribeEvent
    public void onMessageReceived(ClientChatReceivedEvent chatEvent) {
        if(!HypixelUtils.playerIsInPartyGames()) {
            this.STAR_TRACKER.clearScores();
            return;
        }
        System.out.println(chatEvent.message.getFormattedText());
        ScoreboardUtils.getScoreboardTitle();
        String message = chatEvent.message.getUnformattedText();
        if(message.startsWith("  1st    (")) {
            String[] args = message.split(" ");
            String playerName = args[args.length-1];
            this.STAR_TRACKER.addScoreToPlayer(playerName, 3);
        } else if(message.startsWith("  2nd    (")) {
            String[] args = message.split(" ");
            String playerName = args[args.length-1];
            this.STAR_TRACKER.addScoreToPlayer(playerName, 2);
        } else if(message.startsWith("  3rd    (")) {
            String[] args = message.split(" ");
            String playerName = args[args.length-1];
            this.STAR_TRACKER.addScoreToPlayer(playerName, 1);
        }
    }
}
