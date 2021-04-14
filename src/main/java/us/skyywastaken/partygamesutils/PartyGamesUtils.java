package us.skyywastaken.partygamesutils;

import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import us.skyywastaken.partygamesutils.command.pgs.PGSCommand;
import us.skyywastaken.partygamesutils.command.pgs.PGSManager;
import us.skyywastaken.partygamesutils.command.pgs.PartyCommands.PGSPartyCommandManager;
import us.skyywastaken.partygamesutils.misc.SeekManager;

@Mod(modid = "partygamesutils", name = "Party Games Utils", version = "0.0.1")
public class PartyGamesUtils {
    private final SeekManager SEEK_MANAGER;
    private final PGSCommand SEEK_COMMAND;
    private final PGSPartyCommandManager PGS_PARTY_COMMAND_MANAGER;

    public PartyGamesUtils() {
        PGSManager PGS_MANAGER = new PGSManager();
        this.PGS_PARTY_COMMAND_MANAGER = new PGSPartyCommandManager(PGS_MANAGER);
        SEEK_MANAGER = new SeekManager(PGS_MANAGER);
        this.SEEK_COMMAND = new PGSCommand(PGS_MANAGER);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        ClientCommandHandler clientCommandHandler = ClientCommandHandler.instance;
        clientCommandHandler.registerCommand(SEEK_COMMAND);
        MinecraftForge.EVENT_BUS.register(SEEK_MANAGER);
        MinecraftForge.EVENT_BUS.register(PGS_PARTY_COMMAND_MANAGER);
    }
}
