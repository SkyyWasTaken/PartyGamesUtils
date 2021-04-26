package us.skyywastaken.partygamesutils;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLModDisabledEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import us.skyywastaken.partygamesutils.feature.PGS.PGS;

import java.io.File;

@Mod(modid = "partygamesutils", name = "PGS", version = "ALPHA-1.1.0")
public class PartyGamesUtils {
    public static File configFile;
    public static final String MOD_ID = "partygamesutils";
    private PGS PARTY_GAMES_SEEK;
    public static final Logger logger = LogManager.getLogger(MOD_ID);

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        configFile = new File(event.getModConfigurationDirectory().getAbsolutePath() + "\\PartyGamesUtils");
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        this.PARTY_GAMES_SEEK = new PGS();
        PARTY_GAMES_SEEK.init();
    }
}
