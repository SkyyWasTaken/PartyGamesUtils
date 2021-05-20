package us.skyywastaken.partygamesutils.feature.pgs.command.partycommands.commands;

import us.skyywastaken.partygamesutils.command.PartyCommand;
import us.skyywastaken.partygamesutils.feature.pgs.settings.SeekSettings;
import us.skyywastaken.partygamesutils.util.HypixelUtils;

public class PGSPartyStartCommand implements PartyCommand {
    private final SeekSettings PGS_MANAGER;

    public PGSPartyStartCommand(SeekSettings passedSeekSettings) {
        this.PGS_MANAGER = passedSeekSettings;
    }

    @Override
    public void onPartyCommand(String[] args) {
        enableSeeking();
        sendSuccessMessage();
    }

    private void sendSuccessMessage() {
        String successMessage = getSuccessMessage();
        HypixelUtils.sendPartyChatMessage(successMessage);
    }

    private String getSuccessMessage() {
        return "Seeking has been enabled!";
    }

    private void enableSeeking() {
        PGS_MANAGER.setSeekingEnabled(true);
    }
}
