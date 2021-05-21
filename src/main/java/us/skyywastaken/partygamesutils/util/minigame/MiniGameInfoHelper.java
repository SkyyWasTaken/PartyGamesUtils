package us.skyywastaken.partygamesutils.util.minigame;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import us.skyywastaken.partygamesutils.util.ScoreboardUtils;

public class MiniGameInfoHelper {
    public static int getCurrentGameNumber() {
        String currentGameNumberRow = ScoreboardUtils.getGameNumberRow();
        int index = currentGameNumberRow.indexOf("/") - 1;
        if (currentGameNumberRow.length() <= index || index < 0) {
            return 0; // This fixes a race condition that can crash the game.
        }
        return Integer.parseInt(String.valueOf(currentGameNumberRow.charAt(index)));
    }

    public static boolean playerIsInPartyGames() {
        return ScoreboardUtils.getScoreboardTitle().equals("PartyGames");
    }

    public static boolean playerIsActivelyPlayingPartyGame() {
        if(Minecraft.getMinecraft().thePlayer != null) {
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(ScoreboardUtils.getUnformattedLineFromScoreboard(10).contains("\u272E") + ""));
        }
        return ScoreboardUtils.getUnformattedLineFromScoreboard(10).contains("\u272E");
    }
}
