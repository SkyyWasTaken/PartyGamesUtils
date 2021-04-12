package us.skyywastaken.partygamesutils.misc;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.BlockPos;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import us.skyywastaken.partygamesutils.util.ScoreboardUtils;

import java.util.ArrayList;

public class SeekManager {
    private ArrayList<String> seekList;
    private boolean seekingEnabled;
    private boolean playerWasNotified;
    private boolean partyCommandsEnabled;
    private int doNotSkipThreshold;

    public SeekManager() {
        this.seekList = new ArrayList<>();
        this.seekingEnabled = false;
        this.playerWasNotified = false;
        this.partyCommandsEnabled = false;
        this.doNotSkipThreshold = 4;
    }

    public void addSeekedGame(String gameToAdd) {
        seekList.add(gameToAdd);
    }

    public void removeSeekedGame(String gameToRemove) {
        seekList.remove(gameToRemove);
    }

    public void setSeekingEnabled(boolean passedBoolean) {
        seekingEnabled = passedBoolean;
    }

    public void clearSeekList() {
        seekList.clear();
    }

    public ArrayList<String> getSeekList() {
        return seekList;
    }

    public boolean getPartyCommandsEnabled() {
        return partyCommandsEnabled;
    }

    public void setPartyCommandsEnabled(boolean passedBoolean) {
        partyCommandsEnabled = passedBoolean;
    }

    public void setDoNotSkipThreshold(int newMax) {
        if(newMax < 0) {
            doNotSkipThreshold = 0;
        } else doNotSkipThreshold = Math.min(newMax, 8);
    }

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent tickEvent) {
        if(Minecraft.getMinecraft().theWorld == null || Minecraft.getMinecraft().thePlayer == null) {
            return;
        }
        if(Minecraft.getMinecraft().theWorld.getTotalWorldTime() % 20 == 0 && seekingEnabled) {
            String gameRow = ScoreboardUtils.getLineFromScoreboard(13);
            String gameNameRow = ScoreboardUtils.getNoPrefixGameName();
            if(gameRow.equals("Game:") && gameCanBeSkipped()) {
                if(!seekList.contains(gameNameRow)) {
                    Minecraft.getMinecraft().thePlayer.sendChatMessage("/play arcade_party_games_1");
                } else {
                    if(!playerWasNotified) {
                        notifyPlayer(gameNameRow);
                        playerWasNotified = true;
                    }
                }
            } else {
                if(playerWasNotified) playerWasNotified = false;
            }
        }
    }

    private void notifyPlayer(String gameName) {
        EntityPlayerSP clientPlayer = Minecraft.getMinecraft().thePlayer;
        clientPlayer.sendChatMessage("/pchat The correct game has been found! This is " + gameName);
        BlockPos playerPos = clientPlayer.getPosition();
        Minecraft.getMinecraft().theWorld.playSound(playerPos.getX(), playerPos.getY(), playerPos.getZ(),
                "note.harp", Float.MAX_VALUE, 2, false);
    }

    private boolean gameCanBeSkipped() {
        String currentGameNumberRow = ScoreboardUtils.getGameNumberRow();
        int gameNumber = Integer.parseInt(String.valueOf(currentGameNumberRow.charAt(currentGameNumberRow.indexOf("/")-1)));
        return gameNumber < doNotSkipThreshold;
    }
}
