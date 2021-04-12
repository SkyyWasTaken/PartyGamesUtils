package us.skyywastaken.partygamesutils.command.pgs.subcommands.set;

import us.skyywastaken.partygamesutils.command.pgs.PGSPartyCommandManager;
import us.skyywastaken.partygamesutils.misc.SeekManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class PGSSettingsManager {
    private HashMap<String, SettingType> pgsSettingTypeHashMap;
    private final SeekManager SEEK_MANAGER;
    private final PGSPartyCommandManager PARTY_COMMAND_MANAGER;

    public PGSSettingsManager(SeekManager seekManager, PGSPartyCommandManager partyCommandManager) {
        pgsSettingTypeHashMap = new HashMap<>();
        this.SEEK_MANAGER = seekManager;
        this.PARTY_COMMAND_MANAGER = partyCommandManager;
        registerSettings();
    }

    private void registerSettings() {
        pgsSettingTypeHashMap.put("PartyPermissions.add", SettingType.BOOLEAN);
        pgsSettingTypeHashMap.put("PartyPermissions.clear", SettingType.BOOLEAN);
        pgsSettingTypeHashMap.put("PartyPermissions.list", SettingType.BOOLEAN);
        pgsSettingTypeHashMap.put("PartyPermissions.remove", SettingType.BOOLEAN);
        pgsSettingTypeHashMap.put("PartyPermissions.start", SettingType.BOOLEAN);
        pgsSettingTypeHashMap.put("PartyPermissions.stop", SettingType.BOOLEAN);
        pgsSettingTypeHashMap.put("DoNotSkipThreshold", SettingType.INT);
    }

    private enum SettingType {
        BOOLEAN (Arrays.asList("true", "false")), INT (Collections.singletonList("{0-8}"));

        private final List<String> COMPLETION_SETTINGS;

        private List<String> getCompletionSuggestions() {
            return COMPLETION_SETTINGS;
        }

        SettingType(List<String> completionSuggestions) {
            this.COMPLETION_SETTINGS = completionSuggestions;
        }
    }

    public List<String> getTabCompletions(String[] args) {
        String[] trimmedArgs = Arrays.copyOfRange(args, 1, args.length);
        if(trimmedArgs.length == 1) {
            return getAvailableCompletionsAtIndex(0);
        }
        String currentPath = String.join(".", Arrays.copyOfRange(trimmedArgs, 0, trimmedArgs.length-1));
        if(pgsSettingTypeHashMap.containsKey(currentPath)) {
            return pgsSettingTypeHashMap.get(currentPath).getCompletionSuggestions();
        } else {
            return getAvailableCompletionsAtIndex(trimmedArgs.length-1);
        }
    }

    private List<String> getAvailableCompletionsAtIndex(int index) {
        ArrayList<String> returnList = new ArrayList<>();
        for(String currentSettingPathString : pgsSettingTypeHashMap.keySet()) {
            String[] settingPathStrings = currentSettingPathString.split("\\.");
            if(settingPathStrings.length <= index) continue;
            String stringToAdd = settingPathStrings[index];
            if(!returnList.contains(stringToAdd)) {
                returnList.add(stringToAdd);
            }
        }
        return returnList;
    }
}
