package us.skyywastaken.partygamesutils.feature.pgs.misc;

import com.google.gson.Gson;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;
import us.skyywastaken.partygamesutils.PartyGamesUtils;
import us.skyywastaken.partygamesutils.feature.pgs.settings.PartyCommandSettings;
import us.skyywastaken.partygamesutils.feature.pgs.settings.SeekSettings;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class PGSSaveManager {
    private static final File PARTY_COMMANDS_SETTING_FILE = getPartyCommandsSettingsFile();
    private static final File SEEK_SETTINGS_FILE = getSeekSettingsFile();
    private final SeekSettings SEEK_SETTINGS;
    private final PartyCommandSettings PARTY_COMMAND_SETTINGS;
    private long lastCheck = 0;

    public PGSSaveManager(SeekSettings passedSeekSettings, PartyCommandSettings passedPartyCommandSettings) {
        this.SEEK_SETTINGS = passedSeekSettings;
        this.PARTY_COMMAND_SETTINGS = passedPartyCommandSettings;
    }

    public static PartyCommandSettings loadPartyCommandSettings() {
        Gson gson = new Gson();
        try {
            return gson.fromJson(new FileReader(PARTY_COMMANDS_SETTING_FILE), PartyCommandSettings.class);
        } catch (IOException ignored) {
            return null;
        }
    }

    public static SeekSettings loadSeekSettings() {
        Gson gson = new Gson();
        try {
            return gson.fromJson(new FileReader(SEEK_SETTINGS_FILE), SeekSettings.class);
        } catch (IOException ignored) {
            return null;
        }
    }

    private static File getSeekSettingsFile() {
        return new File(PartyGamesUtils.configFile.getAbsolutePath() + "\\PGS\\SeekSettings.json");
    }

    private static File getPartyCommandsSettingsFile() {
        System.out.println(PartyGamesUtils.configFile);
        return new File(PartyGamesUtils.configFile.getAbsolutePath() + "\\PGS\\PartyCommandsSettings.json");
    }

    public void saveData() {
        if (!SEEK_SETTINGS_FILE.getParentFile().exists() && !SEEK_SETTINGS_FILE.getParentFile().mkdirs()) {
            PartyGamesUtils.logger.warn("Failed to create the config directory! Data will not be saved.");
            return;
        }
        if (SEEK_SETTINGS.isDirty()) {
            saveSeekSettings();
            SEEK_SETTINGS.setDirty(false);
        }
        if (PARTY_COMMAND_SETTINGS.isDirty()) {
            savePartyCommandSettings();
            PARTY_COMMAND_SETTINGS.setDirty(false);
        }
    }

    private void saveSeekSettings() {
        Gson gson = new Gson();
        String seekSettings = gson.toJson(this.SEEK_SETTINGS);
        try {
            if (!SEEK_SETTINGS_FILE.exists() && !SEEK_SETTINGS_FILE.createNewFile()) {
                PartyGamesUtils.logger.warn("Failed to create the seek settings file. Seek settings will not be saved.");
                return;
            }
            FileWriter fileWriter = new FileWriter(SEEK_SETTINGS_FILE);
            fileWriter.write(seekSettings);
            fileWriter.close();
        } catch (IOException ignored) {
        }
    }

    private void savePartyCommandSettings() {
        Gson gson = new Gson();
        String seekSettings = gson.toJson(this.PARTY_COMMAND_SETTINGS);
        try {
            if (!SEEK_SETTINGS_FILE.exists() && !SEEK_SETTINGS_FILE.createNewFile()) {
                PartyGamesUtils.logger.warn("Failed to create the party settings file. party settings will not be saved.");
                return;
            }
            FileWriter fileWriter = new FileWriter(PARTY_COMMANDS_SETTING_FILE);
            fileWriter.write(seekSettings);
            fileWriter.close();
        } catch (IOException ignored) {
        }
    }

    @SubscribeEvent
    public void onServerLeft(TickEvent.PlayerTickEvent worldTickEvent) {
        long worldTime = worldTickEvent.player.worldObj.getTotalWorldTime();
        if (worldTime != lastCheck && worldTime % 20 == 0) {
            saveData();
            lastCheck = worldTime;
        }
    }

    @SubscribeEvent
    public void onServerJoined(FMLNetworkEvent.ClientDisconnectionFromServerEvent event) {
        saveData();
    }
}
