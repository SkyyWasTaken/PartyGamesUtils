package us.skyywastaken.partygamesutils.event.events;

import net.minecraftforge.fml.common.eventhandler.Event;
import us.skyywastaken.partygamesutils.util.minigame.MiniGame;

import java.util.List;

public class PartyGamesEndEvent extends Event {
    public final List<MiniGame> FINAL_MINI_GAME_LIST;
    public PartyGamesEndEvent(List<MiniGame> passedMiniGameList) {
        this.FINAL_MINI_GAME_LIST = passedMiniGameList;
    }
}
