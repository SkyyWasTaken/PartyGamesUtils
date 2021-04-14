package us.skyywastaken.partygamesutils.misc;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.BlockPos;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import us.skyywastaken.partygamesutils.command.pgs.PGSManager;
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
                if (!PGS_MANAGER.isGameSought(gameNameRow)) {
                    Minecraft.getMinecraft().thePlayer.sendChatMessage("/play arcade_party_games_1");
                } else {
                    if (!playerWasNotified) {
                        notifyPlayer(gameNameRow);
                        playerWasNotified = true;
                    }
                }
            } else {
                if (playerWasNotified) playerWasNotified = false;
            }
        }
    }

    private void notifyPlayer(String gameName) {
        EntityPlayerSP clientPlayer = Minecraft.getMinecraft().thePlayer;
        if (PGS_MANAGER.getPartyCommandsEnabled()) {
            clientPlayer.sendChatMessage("/pchat The correct game has been found! This is " + gameName);
        }
        BlockPos playerPos = clientPlayer.getPosition();
        Minecraft.getMinecraft().theWorld.playSound(playerPos.getX(), playerPos.getY(), playerPos.getZ(),
                "note.harp", Float.MAX_VALUE, 2, false);
    }

    private boolean gameCanBeSkipped() {
        String currentGameNumberRow = ScoreboardUtils.getGameNumberRow();
        int gameNumber = Integer.parseInt(String.valueOf(currentGameNumberRow.charAt(currentGameNumberRow.indexOf("/") - 1)));
        return gameNumber < PGS_MANAGER.getDoNotSeekThreshold();
    }
}
