package us.skyywastaken.partygamesutils.feature.pgs;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import us.skyywastaken.partygamesutils.feature.pgs.settings.PartyCommandSettings;
import us.skyywastaken.partygamesutils.feature.pgs.settings.SeekSettings;
import us.skyywastaken.partygamesutils.util.HypixelUtils;
import us.skyywastaken.partygamesutils.util.ScoreboardUtils;
import us.skyywastaken.partygamesutils.util.StringUtils;

public class SeekManager {
    private final SeekSettings PGS_MANAGER;
    private final PartyCommandSettings PARTY_COMMAND_SETTINGS;
    private boolean playerWasNotified;

    public SeekManager(SeekSettings seekSettings, PartyCommandSettings passedPartyCommandSettings) {
        this.PGS_MANAGER = seekSettings;
        this.PARTY_COMMAND_SETTINGS = passedPartyCommandSettings;
        this.playerWasNotified = false;
    }

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent tickEvent) {
        if (Minecraft.getMinecraft().theWorld == null || Minecraft.getMinecraft().thePlayer == null) {
            return;
        }
        if (Minecraft.getMinecraft().theWorld.getTotalWorldTime() % 20 == 0 && PGS_MANAGER.isSeekingEnabled()) {
            String gameRow = ScoreboardUtils.getLineFromScoreboard(13);
            String gameNameRow = ScoreboardUtils.getNoPrefixGameName();
            if (HypixelUtils.playerIsInPartyGames() && gameCanBeSkipped()) {
                boolean isGameOnList = PGS_MANAGER.isGameSought(gameNameRow);
                if ((!isGameOnList && !PGS_MANAGER.isBlacklistEnabled()) || (isGameOnList && PGS_MANAGER.isBlacklistEnabled())) {
                    Minecraft.getMinecraft().thePlayer.sendChatMessage("/play arcade_party_games_1");
                } else {
                    if (!playerWasNotified) {
                        notifyPlayer();
                        playerWasNotified = true;
                    }
                }
            } else {
                if (playerWasNotified) playerWasNotified = false;
            }
        }
    }

    private void notifyPlayer() {
        EntityPlayerSP clientPlayer = Minecraft.getMinecraft().thePlayer;
        if (PARTY_COMMAND_SETTINGS.getPartyCommandsEnabled()) {
            HypixelUtils.sendPartyChatMessage("I've found a game you're looking for!");
        } else {
            clientPlayer.addChatMessage(new ChatComponentText(StringUtils.BODY_FORMATTING + "A good game has been found!"));
        }
        BlockPos playerPos = clientPlayer.getPosition();
        Minecraft.getMinecraft().theWorld.playSound(playerPos.getX(), playerPos.getY(), playerPos.getZ(),
                "note.pling", Float.MAX_VALUE, 2, false);
    }

    private boolean gameCanBeSkipped() {
        int doNotSeekThreshold = PGS_MANAGER.getDoNotSeekThreshold();
        if (doNotSeekThreshold == 0) {
            return true;
        }
        String currentGameNumberRow = ScoreboardUtils.getGameNumberRow();
        int index = currentGameNumberRow.indexOf("/") - 1;
        if (currentGameNumberRow.length() <= index || index < 0)
            return false; // This fixes a race condition that can crash the game.
        int gameNumber = Integer.parseInt(String.valueOf(currentGameNumberRow.charAt(index)));
        return gameNumber < doNotSeekThreshold;
    }
}
