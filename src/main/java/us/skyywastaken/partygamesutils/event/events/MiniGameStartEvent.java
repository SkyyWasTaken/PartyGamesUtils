package us.skyywastaken.partygamesutils.event.events;

import net.minecraftforge.fml.common.eventhandler.Event;
import us.skyywastaken.partygamesutils.util.minigame.MiniGameType;

public class MiniGameStartEvent extends Event {
    public final MiniGameType MINI_GAME_TYPE;
    public final int MINI_GAME_NUMBER;
    public MiniGameStartEvent(MiniGameType currentMiniGameType, int gameNumber) {
        super();
        this.MINI_GAME_TYPE = currentMiniGameType;
        this.MINI_GAME_NUMBER = gameNumber;
    }
}
