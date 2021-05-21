package us.skyywastaken.partygamesutils.event.events;

import net.minecraftforge.fml.common.eventhandler.Event;
import us.skyywastaken.partygamesutils.util.minigame.MiniGameType;

public class PartyGamesStartEvent extends Event {
    public final MiniGameType firstMiniGameType;
    public PartyGamesStartEvent(MiniGameType passedMiniGameType) {
        this.firstMiniGameType = passedMiniGameType;
    }
}
