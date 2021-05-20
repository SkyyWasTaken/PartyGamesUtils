package us.skyywastaken.partygamesutils.feature.pgs.settings;

import us.skyywastaken.partygamesutils.feature.pgs.command.partycommands.PGSPartyCommandType;

import java.util.HashMap;

public class PartyCommandSettings {
    private final HashMap<PGSPartyCommandType, Boolean> partyCommandPermissionsHashMap;
    private transient boolean isDirty = false;
    private boolean partyCommandsEnabled;

    public PartyCommandSettings() {
        this.partyCommandsEnabled = false;
        this.partyCommandPermissionsHashMap = new HashMap<>();
        initializePartyCommandPermissions();
    }

    private void initializePartyCommandPermissions() {
        partyCommandPermissionsHashMap.put(PGSPartyCommandType.CLEAR, true);
        partyCommandPermissionsHashMap.put(PGSPartyCommandType.ADD, true);
        partyCommandPermissionsHashMap.put(PGSPartyCommandType.REMOVE, true);
        partyCommandPermissionsHashMap.put(PGSPartyCommandType.LIST, true);
        partyCommandPermissionsHashMap.put(PGSPartyCommandType.START, false);
        partyCommandPermissionsHashMap.put(PGSPartyCommandType.STOP, false);
        partyCommandPermissionsHashMap.put(PGSPartyCommandType.TOGGLEBLACKLIST, true);
    }

    public boolean getPartyCommandsEnabled() {
        return partyCommandsEnabled;
    }

    public void setPartyCommandsEnabled(boolean newValue) {
        this.partyCommandsEnabled = newValue;
        isDirty = true;
    }

    public void updatePartyPermission(PGSPartyCommandType permissionType, boolean newValue) {
        partyCommandPermissionsHashMap.put(permissionType, newValue);
        isDirty = true;
    }

    public boolean getPartyPermissionEnabled(PGSPartyCommandType commandType) {
        return partyCommandPermissionsHashMap.get(commandType);
    }

    public boolean isDirty() {
        return isDirty;
    }

    public void setDirty(boolean newValue) {
        isDirty = newValue;
    }
}
