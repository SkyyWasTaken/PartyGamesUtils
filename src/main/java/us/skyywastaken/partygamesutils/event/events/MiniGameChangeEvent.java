package us.skyywastaken.partygamesutils.event.events;

import net.minecraftforge.fml.common.eventhandler.Event;
import us.skyywastaken.partygamesutils.util.minigame.MiniGame;
import us.skyywastaken.partygamesutils.util.minigame.MiniGameType;

public class MiniGameChangeEvent extends Event {
    public final MiniGame OLD_MINI_GAME;
    public final MiniGameType NEW_MINI_GAME_TYPE;

    public MiniGameChangeEvent(MiniGame passedOldMiniGame, MiniGameType passedNewMiniGameType) {
        this.OLD_MINI_GAME = passedOldMiniGame;
        this.NEW_MINI_GAME_TYPE = passedNewMiniGameType;
    }
}
