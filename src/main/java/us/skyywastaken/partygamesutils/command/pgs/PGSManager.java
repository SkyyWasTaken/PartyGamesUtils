package us.skyywastaken.partygamesutils.command.pgs;

import us.skyywastaken.partygamesutils.command.pgs.PartyCommands.PGSPartyCommandType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PGSManager {
    private boolean partyCommandsEnabled;
    private boolean seekingEnabled;
    private int doNotSkipThreshold;
    private final ArrayList<String> SEEK_LIST;
    private final HashMap<PGSPartyCommandType, Boolean> partyCommandPermissionsHashMap;

    public PGSManager() {
        this.SEEK_LIST = new ArrayList<>();
        this.partyCommandsEnabled = false;
        doNotSkipThreshold = 4;
        this.partyCommandPermissionsHashMap = new HashMap<>();
        this.seekingEnabled = false;
        initializePartyCommandPermissions();
    }


    public void addSoughtGame(String gameToAdd) {
        SEEK_LIST.add(gameToAdd);
    }

    public void removeSoughtGame(String gameToRemove) {
        SEEK_LIST.remove(gameToRemove);
    }

    public void clearSeekList() {
        SEEK_LIST.clear();
    }

    public boolean isGameSought(String gameName) {
        return SEEK_LIST.contains(gameName);
    }

    public List<String> getSeekList() {
        return SEEK_LIST;
    }

    public int getSeekListSize() {
        return SEEK_LIST.size();
    }

    public void setPartyCommandsEnabled(boolean newValue) {
        this.partyCommandsEnabled = newValue;
    }

    public boolean getPartyCommandsEnabled() {
        return partyCommandsEnabled;
    }

    private void initializePartyCommandPermissions() {
        partyCommandPermissionsHashMap.put(PGSPartyCommandType.CLEAR, true);
        partyCommandPermissionsHashMap.put(PGSPartyCommandType.ADD, true);
        partyCommandPermissionsHashMap.put(PGSPartyCommandType.REMOVE, true);
        partyCommandPermissionsHashMap.put(PGSPartyCommandType.LIST, true);
        partyCommandPermissionsHashMap.put(PGSPartyCommandType.START, false);
        partyCommandPermissionsHashMap.put(PGSPartyCommandType.STOP, false);
    }

    public void updatePartyPermission(PGSPartyCommandType permissionType, boolean newValue) {
        partyCommandPermissionsHashMap.put(permissionType, newValue);
    }

    public boolean getPartyPermissionEnabled(PGSPartyCommandType commandType) {
        return partyCommandPermissionsHashMap.get(commandType);
    }

    public void setSeekingEnabled(boolean passedBoolean) {
        seekingEnabled = passedBoolean;
    }

    public boolean isSeekingEnabled() {
        return seekingEnabled;
    }

    public int getDoNotSkipThreshold() {
        return doNotSkipThreshold;
    }

    public void setDoNotSkipThreshold(int newMax) {
        if(newMax < 0) {
            doNotSkipThreshold = 0;
        } else doNotSkipThreshold = Math.min(newMax, 8);
    }
}
