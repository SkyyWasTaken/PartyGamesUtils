package us.skyywastaken.partygamesutils.misc;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import us.skyywastaken.partygamesutils.command.pgs.PGSManager;
import us.skyywastaken.partygamesutils.util.HypixelUtils;
import us.skyywastaken.partygamesutils.util.ScoreboardUtils;

public class SeekManager {
    private final PGSManager PGS_MANAGER;
    private boolean playerWasNotified;

    public SeekManager(PGSManager pgsManager) {
        this.PGS_MANAGER = pgsManager;
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
            if (gameRow.equals("Game:") && gameCanBeSkipped()) {
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
        if (PGS_MANAGER.getPartyCommandsEnabled()) {
            HypixelUtils.sendPartyChatMessage("I've found a game you're looking for!");
        } else {
            clientPlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + "The correct game has been found"));
        }
        BlockPos playerPos = clientPlayer.getPosition();
        Minecraft.getMinecraft().theWorld.playSound(playerPos.getX(), playerPos.getY(), playerPos.getZ(),
                "note.pling", Float.MAX_VALUE, 2, false);
    }

    private boolean gameCanBeSkipped() {
        String currentGameNumberRow = ScoreboardUtils.getGameNumberRow();
        int index = currentGameNumberRow.indexOf("/") - 1;
        if (currentGameNumberRow.length() <= index) return false;
        int gameNumber = Integer.parseInt(String.valueOf(currentGameNumberRow.charAt(index)));
        return gameNumber < PGS_MANAGER.getDoNotSeekThreshold();
    }
}
