package us.skyywastaken.partygamesutils;

import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import us.skyywastaken.partygamesutils.command.PGU.PGUCommand;
import us.skyywastaken.partygamesutils.feature.gameseeking.PGS;
import us.skyywastaken.partygamesutils.feature.startracker.StarTracker;
import us.skyywastaken.partygamesutils.util.minigame.MiniGameInfoUpdater;
import us.skyywastaken.partygamesutils.util.minigame.MiniGameManager;

import java.io.File;

@Mod(modid = "partygamesutils", name = "PGU", version = "ALPHA-1.1.0")
public class PartyGamesUtils {
    public static final String MOD_ID = "partygamesutils";
    public static final Logger logger = LogManager.getLogger(MOD_ID);
    public static File configFile;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        configFile = new File(event.getModConfigurationDirectory().getAbsolutePath() + "\\PartyGamesUtils");
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        PGS PARTY_GAMES_SEEK = new PGS();
        PARTY_GAMES_SEEK.init();

        StarTracker starTracker = new StarTracker();
        starTracker.init();

        MiniGameInfoUpdater miniGameInfoUpdater = new MiniGameInfoUpdater();
        MinecraftForge.EVENT_BUS.register(miniGameInfoUpdater);

        ClientCommandHandler.instance.registerCommand(new PGUCommand(starTracker));
    }
}
