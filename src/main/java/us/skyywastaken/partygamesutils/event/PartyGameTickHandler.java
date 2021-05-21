package us.skyywastaken.partygamesutils.event;

import net.minecraftforge.common.MinecraftForge;
import us.skyywastaken.partygamesutils.event.events.PartyGamesStartEvent;
import us.skyywastaken.partygamesutils.util.PartyGamesInfo;
import us.skyywastaken.partygamesutils.util.minigame.MiniGameManager;
import us.skyywastaken.partygamesutils.util.minigame.MiniGameType;

public class PartyGameTickHandler {
    public void onPartyGamesInitialization() {
        String partyGameName = PartyGamesInfo.getGameNameRow();
        MiniGameType miniGameType = MiniGameType.fromString(partyGameName);
        MiniGameManager.setMiniGameType(miniGameType);
        MiniGameManager.setMiniGameNumber(1);
        MiniGameManager.resetMiniGameTimer();
        PartyGamesStartEvent partyGamesStartEvent = new PartyGamesStartEvent(miniGameType);
        MinecraftForge.EVENT_BUS.post(partyGamesStartEvent);
    }

    public void onPartyGamesDeInitialization() {
        String partyGameName = PartyGamesInfo.getGameNameRow();
    }
}
