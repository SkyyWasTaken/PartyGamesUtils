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
    }


    public void addSeekedGame(String gameToAdd) {
        SEEK_LIST.add(gameToAdd);
    }

    public void removeSeekedGame(String gameToRemove) {
        SEEK_LIST.remove(gameToRemove);
    }

    public void clearSeekList() {
        SEEK_LIST.clear();
    }

    public boolean isGameSeeked(String gameName) {
        return SEEK_LIST.contains(gameName);
    }

    public List<String> getSeekList() {
        return SEEK_LIST;
    }

    public void setPartyCommandsEnabled(boolean newValue) {
        this.partyCommandsEnabled = newValue;
    }

    public boolean arePartyCommandsEnabled() {
        return partyCommandsEnabled;
    }

    private void initializeCommandPermissions() {
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
