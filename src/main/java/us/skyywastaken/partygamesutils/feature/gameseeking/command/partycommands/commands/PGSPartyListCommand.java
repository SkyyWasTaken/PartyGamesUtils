package us.skyywastaken.partygamesutils.feature.gameseeking.command.partycommands.commands;

import us.skyywastaken.partygamesutils.command.PartyCommand;
import us.skyywastaken.partygamesutils.feature.gameseeking.settings.SeekSettings;
import us.skyywastaken.partygamesutils.util.HypixelUtils;

import java.util.List;

public class PGSPartyListCommand implements PartyCommand {
    private final SeekSettings SEEK_MANAGER;

    public PGSPartyListCommand(SeekSettings passedSeekManager) {
        this.SEEK_MANAGER = passedSeekManager;
    }

    @Override
    public void onPartyCommand(String[] args) {
        sendGameList();
    }

    private void sendGameList() {
        String gameListString;
        int gameListSize = SEEK_MANAGER.getSeekListSize();
        gameListString = getGameListPrefix(gameListSize) + getUnformattedGameList();
        if (gameListString.length() > 200) {
            HypixelUtils.sendPartyChatMessage(getGameListTooLongString());
        } else {
            HypixelUtils.sendPartyChatMessage(gameListString);
        }
    }

    private String getUnformattedGameList() {
        String delimiter;
        List<String> seekList = SEEK_MANAGER.getSeekList();
        delimiter = ", ";
        return String.join(delimiter, seekList);
    }

    private String getGameListPrefix(int gameAmount) {
        if (gameAmount == 0) {
            return "There are no games in the seek list!";
        } else if (gameAmount == 1) {
            return "Current sought game: ";
        } else {
            return "Currently sought games: ";
        }
    }

    private String getGameListTooLongString() {
        return "The game list is too long to send through party chat!";
    }
}
