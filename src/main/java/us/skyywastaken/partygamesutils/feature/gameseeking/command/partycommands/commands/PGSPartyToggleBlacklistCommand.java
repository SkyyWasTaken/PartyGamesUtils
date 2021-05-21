package us.skyywastaken.partygamesutils.feature.gameseeking.command.partycommands.commands;

import us.skyywastaken.partygamesutils.command.PartyCommand;
import us.skyywastaken.partygamesutils.feature.gameseeking.settings.SeekSettings;
import us.skyywastaken.partygamesutils.util.HypixelUtils;
import us.skyywastaken.partygamesutils.util.StringUtils;

public class PGSPartyToggleBlacklistCommand implements PartyCommand {
    private final SeekSettings PGS_MANAGER;

    public PGSPartyToggleBlacklistCommand(SeekSettings passedSeekSettings) {
        this.PGS_MANAGER = passedSeekSettings;
    }

    @Override
    public void onPartyCommand(String[] args) {
        toggleBlacklist();
        sendSuccessMessage();
    }

    private void sendSuccessMessage() {
        String successMessage = getSuccessMessage();
        HypixelUtils.sendPartyChatMessage(successMessage);
    }

    private String getSuccessMessage() {
        boolean isBlacklistEnabled = PGS_MANAGER.isBlacklistEnabled();
        return "The blacklist has been " + StringUtils.getColorlessEnabledDisabledString(isBlacklistEnabled);
    }

    private void toggleBlacklist() {
        PGS_MANAGER.setBlacklistEnabled(!PGS_MANAGER.isBlacklistEnabled());
    }
}
