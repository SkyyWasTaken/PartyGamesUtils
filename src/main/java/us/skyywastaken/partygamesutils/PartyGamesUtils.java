package us.skyywastaken.partygamesutils;

import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import us.skyywastaken.partygamesutils.command.pgs.PGSCommand;
import us.skyywastaken.partygamesutils.command.pgs.PartyCommandManager;
import us.skyywastaken.partygamesutils.misc.SeekManager;

@Mod(modid = "partygamesutils", name = "Party Games Utils", version = "0.0.1")
public class PartyGamesUtils {
    private final SeekManager SEEK_MANAGER;
    public PartyGamesUtils() {
        SEEK_MANAGER = new SeekManager();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        ClientCommandHandler clientCommandHandler = ClientCommandHandler.instance;
        PGSCommand seekCommand = new PGSCommand(SEEK_MANAGER);
        clientCommandHandler.registerCommand(seekCommand);
        MinecraftForge.EVENT_BUS.register(SEEK_MANAGER);
        MinecraftForge.EVENT_BUS.register(new PartyCommandManager(seekCommand, SEEK_MANAGER));
    }
}
