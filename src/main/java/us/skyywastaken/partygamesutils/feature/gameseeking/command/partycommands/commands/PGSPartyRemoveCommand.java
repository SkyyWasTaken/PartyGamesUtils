package us.skyywastaken.partygamesutils.feature.gameseeking.command.partycommands.commands;

import us.skyywastaken.partygamesutils.command.PartyCommand;
import us.skyywastaken.partygamesutils.feature.gameseeking.settings.SeekSettings;
import us.skyywastaken.partygamesutils.util.HypixelUtils;

public class PGSPartyRemoveCommand implements PartyCommand {
    private final SeekSettings SEEK_SETTINGS;

    public PGSPartyRemoveCommand(SeekSettings passedSeekSettings) {
        this.SEEK_SETTINGS = passedSeekSettings;
    }

    @Override
    public void onPartyCommand(String[] args) {
        if (args.length == 0) {
            sendTooFewArgsFailureMessage();
            return;
        }
        String[] gameList = getGameListStringsFromArgs(args);
        removeGames(gameList);
        sendPartySuccessMessage(gameList.length);
    }


    private void removeGame(String gameToAdd) {
        SEEK_SETTINGS.removeSoughtGame(gameToAdd);
    }

    private void removeGames(String[] passedGameStrings) {
        for (String currentGameString : passedGameStrings) {
            String finalizedGameString = currentGameString.trim();
            removeGame(finalizedGameString);
        }
    }

    private String[] getGameListStringsFromArgs(String[] rawArgsArray) {
        String rawArgsString = String.join(" ", rawArgsArray);
        return rawArgsString.split(",");
    }

    private void sendTooFewArgsFailureMessage() {
        HypixelUtils.sendPartyChatMessage(getTooFewArgsFailureMessage());
    }

    private void sendPartySuccessMessage(int gamesAdded) {
        if (gamesAdded == 1) {
            HypixelUtils.sendPartyChatMessage("Game removed successfully!");
        } else {
            HypixelUtils.sendPartyChatMessage("Game(s) removed successfully!");
        }
    }

    private String getTooFewArgsFailureMessage() {
        return "You need to specify what game(s) you want to remove!";
    }
}
