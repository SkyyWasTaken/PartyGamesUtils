package us.skyywastaken.partygamesutils.feature.gameseeking.command.partycommands.commands;

import us.skyywastaken.partygamesutils.command.PartyCommand;
import us.skyywastaken.partygamesutils.feature.gameseeking.settings.SeekSettings;
import us.skyywastaken.partygamesutils.util.HypixelUtils;

public class PGSPartyClearCommand implements PartyCommand {
    private final SeekSettings SEEK_SETTINGS;

    public PGSPartyClearCommand(SeekSettings passedSeekManager) {
        this.SEEK_SETTINGS = passedSeekManager;
    }

    @Override
    public void onPartyCommand(String[] args) {
        SEEK_SETTINGS.clearSeekList();
        sendSuccessMessage();
    }

    private void sendSuccessMessage() {
        String successMessage = getSuccessMessage();
        HypixelUtils.sendPartyChatMessage(successMessage);
    }

    private String getSuccessMessage() {
        return "Successfully cleared the seek list!";
    }
}
