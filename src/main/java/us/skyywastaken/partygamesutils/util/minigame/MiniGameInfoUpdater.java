package us.skyywastaken.partygamesutils.util.minigame;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import us.skyywastaken.partygamesutils.util.PartyGamesInfo;

public class MiniGameInfoUpdater {
    private boolean miniGameTypeNeedsToBeUpdated = true;
    public MiniGameInfoUpdater() {
    }

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent clientTickEvent) {
        MiniGameInfoHelper.playerIsActivelyPlayingPartyGame();
        if(clientTickEvent.phase == TickEvent.Phase.END) {
            if(miniGameTypeNeedsToBeUpdated && PartyGamesInfo.playerIsInPartyGame()) {
                this.miniGameTypeNeedsToBeUpdated = false;
            } else if(!PartyGamesInfo.playerIsInPartyGame() && !miniGameTypeNeedsToBeUpdated) {
                this.miniGameTypeNeedsToBeUpdated = true;
            } else if(PartyGamesInfo.playerIsInPartyGame()) {
                MiniGameManager.updateMiniGameStatus();
            }
        }
    }
}
