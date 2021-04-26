package us.skyywastaken.partygamesutils.feature.PGS.command.partycommands.commands;

import us.skyywastaken.partygamesutils.command.PartyCommand;
import us.skyywastaken.partygamesutils.feature.PGS.settings.SeekSettings;
import us.skyywastaken.partygamesutils.util.HypixelUtils;

public class PGSPartyStopCommand implements PartyCommand {
    private final SeekSettings PGS_MANAGER;

    public PGSPartyStopCommand(SeekSettings passedSeekSettings) {
        this.PGS_MANAGER = passedSeekSettings;
    }

    @Override
    public void onPartyCommand(String[] args) {
        disableSeeking();
        sendSuccessMessage();
    }

    private void sendSuccessMessage() {
        String successMessage = getSuccessMessage();
            HypixelUtils.sendPartyChatMessage(successMessage);
    }

    private String getSuccessMessage() {
        return "Seeking has been disabled!";
    }

    private void disableSeeking() {
        PGS_MANAGER.setSeekingEnabled(false);
    }
}
