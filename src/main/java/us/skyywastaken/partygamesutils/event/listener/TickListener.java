package us.skyywastaken.partygamesutils.event.listener;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import us.skyywastaken.partygamesutils.event.PartyGameTickHandler;
import us.skyywastaken.partygamesutils.util.PartyGamesInfo;

public class TickListener {
    private boolean cachedPlayerIsInPartyGamesResult = false;
    private final PartyGameTickHandler PARTY_GAME_TICK_HANDLER;
    public TickListener(PartyGameTickHandler passedTickHandler) {
        this.PARTY_GAME_TICK_HANDLER = passedTickHandler;
    }

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent clientTickEvent) {
        if(clientTickEvent.phase != TickEvent.Phase.END) {
            return;
        }
        boolean playerIsInPartyGames = PartyGamesInfo.playerIsInPartyGame();
        if(playerIsInPartyGames != cachedPlayerIsInPartyGamesResult) {
            if(playerIsInPartyGames) {
                this.PARTY_GAME_TICK_HANDLER.onPartyGamesInitialization();
            } else {

            }
        }
    }
}
