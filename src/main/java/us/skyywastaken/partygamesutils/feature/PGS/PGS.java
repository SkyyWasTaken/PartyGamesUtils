package us.skyywastaken.partygamesutils.feature.PGS;

import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import us.skyywastaken.partygamesutils.feature.PGS.command.PGSCommand;
import us.skyywastaken.partygamesutils.feature.PGS.command.partycommands.PGSPartyCommandManager;
import us.skyywastaken.partygamesutils.feature.PGS.misc.PGSSaveManager;
import us.skyywastaken.partygamesutils.feature.PGS.settings.PartyCommandSettings;
import us.skyywastaken.partygamesutils.feature.PGS.settings.SeekSettings;

public class PGS {
    private final SeekManager SEEK_MANAGER;
    private SeekSettings SEEK_SETTINGS;
    private PartyCommandSettings PARTY_COMMAND_SETTINGS;
    private final PGSCommand SEEK_COMMAND;
    private final PGSPartyCommandManager PGS_PARTY_COMMAND_MANAGER;
    private final PGSSaveManager PGS_SAVE_MANAGER;

    public PGS() {
        loadPGSData();
        this.PGS_PARTY_COMMAND_MANAGER = new PGSPartyCommandManager(this.SEEK_SETTINGS, PARTY_COMMAND_SETTINGS);
        this.SEEK_MANAGER = new SeekManager(this.SEEK_SETTINGS, this.PARTY_COMMAND_SETTINGS);
        this.SEEK_COMMAND = new PGSCommand(this.SEEK_SETTINGS, this.PARTY_COMMAND_SETTINGS);
        this.PGS_SAVE_MANAGER = new PGSSaveManager(this.SEEK_SETTINGS, this.PARTY_COMMAND_SETTINGS);
    }

    public void init() {
        ClientCommandHandler clientCommandHandler = ClientCommandHandler.instance;
        clientCommandHandler.registerCommand(SEEK_COMMAND);
        MinecraftForge.EVENT_BUS.register(SEEK_MANAGER);
        MinecraftForge.EVENT_BUS.register(PGS_PARTY_COMMAND_MANAGER);
        MinecraftForge.EVENT_BUS.register(this.PGS_SAVE_MANAGER);
    }

    @Override
    public void finalize() {
        System.out.println("this is being called.");
        this.PGS_SAVE_MANAGER.saveData();
    }

    private void loadPGSData() {
        SeekSettings loadedSeekSettings = PGSSaveManager.loadSeekSettings();
        if(loadedSeekSettings == null) {
            this.SEEK_SETTINGS = new SeekSettings();
        } else {
            this.SEEK_SETTINGS = loadedSeekSettings;
        }
        PartyCommandSettings loadedPartyCommandSettings = PGSSaveManager.loadPartyCommandSettings();
        if(loadedPartyCommandSettings == null) {
            this.PARTY_COMMAND_SETTINGS = new PartyCommandSettings();
        } else {
            this.PARTY_COMMAND_SETTINGS = loadedPartyCommandSettings;
        }
    }
}
