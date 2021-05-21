package us.skyywastaken.partygamesutils.feature.gameseeking.settings;

import java.util.ArrayList;
import java.util.List;

public class SeekSettings {
    private final ArrayList<String> SEEK_LIST;
    private boolean seekingEnabled;
    private boolean blacklistEnabled;
    private transient boolean isDirty;
    private int doNotSeekThreshold;

    public SeekSettings() {
        this.SEEK_LIST = new ArrayList<>();
        this.isDirty = false;
        doNotSeekThreshold = 4;
        this.seekingEnabled = false;
    }


    public void addSoughtGame(String gameToAdd) {
        SEEK_LIST.add(gameToAdd);
        isDirty = true;
    }

    public void removeSoughtGame(String gameToRemove) {
        SEEK_LIST.remove(gameToRemove);
        isDirty = true;
    }

    public void clearSeekList() {
        SEEK_LIST.clear();
        isDirty = true;
    }

    public boolean isGameSought(String gameName) {
        for (String currentString : SEEK_LIST) {
            if (gameName.equalsIgnoreCase(currentString)) {
                return true;
            }
        }
        return false;
    }

    public List<String> getSeekList() {
        return SEEK_LIST;
    }

    public int getSeekListSize() {
        return SEEK_LIST.size();
    }

    public boolean isSeekingEnabled() {
        return seekingEnabled;
    }

    public void setSeekingEnabled(boolean passedBoolean) {
        seekingEnabled = passedBoolean;
        isDirty = true;
    }

    public int getDoNotSeekThreshold() {
        return doNotSeekThreshold;
    }

    public void setDoNotSeekThreshold(int newMax) {
        if (newMax < 0) {
            doNotSeekThreshold = 0;
        } else doNotSeekThreshold = Math.min(newMax, 8);
        isDirty = true;
    }

    public boolean isBlacklistEnabled() {
        return blacklistEnabled;
    }

    public void setBlacklistEnabled(boolean newValue) {
        blacklistEnabled = newValue;
        isDirty = true;
    }

    public boolean isDirty() {
        return isDirty;
    }

    public void setDirty(boolean newValue) {
        isDirty = newValue;
    }
}
