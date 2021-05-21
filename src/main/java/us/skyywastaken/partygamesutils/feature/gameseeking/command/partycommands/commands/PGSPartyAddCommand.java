package us.skyywastaken.partygamesutils.feature.gameseeking.command.partycommands.commands;

import us.skyywastaken.partygamesutils.command.PartyCommand;
import us.skyywastaken.partygamesutils.feature.gameseeking.settings.SeekSettings;
import us.skyywastaken.partygamesutils.util.HypixelUtils;

public class PGSPartyAddCommand implements PartyCommand {
    private final SeekSettings SEEK_SETTINGS;

    public PGSPartyAddCommand(SeekSettings passedSeekSettings) {
        this.SEEK_SETTINGS = passedSeekSettings;
    }


    @Override
    public void onPartyCommand(String[] args) {
        if (args.length == 0) {
            sendTooFewArgsFailureMessage();
            return;
        }
        String[] gameListString = getGameListStringsFromArgs(args);
        addGames(gameListString);
        sendPartySuccessMessage(gameListString.length);
    }

    private void sendTooFewArgsFailureMessage() {
        HypixelUtils.sendPartyChatMessage(getTooFewArgsFailureMessage());

    }

    private String[] getGameListStringsFromArgs(String[] rawArgsArray) {
        String rawArgsString = String.join(" ", rawArgsArray);
        return rawArgsString.split(",");
    }

    private void addGames(String[] passedGameStrings) {
        for (String currentGameString : passedGameStrings) {
            String finalizedGameString = currentGameString.trim();
            addGame(finalizedGameString);
        }
    }

    private void addGame(String gameToAdd) {
        if (!SEEK_SETTINGS.isGameSought(gameToAdd)) {
            SEEK_SETTINGS.addSoughtGame(gameToAdd);
        }
    }

    private void sendPartySuccessMessage(int gamesAdded) {
        if (gamesAdded == 1) {
            HypixelUtils.sendPartyChatMessage("Game added successfully!");
        } else {
            HypixelUtils.sendPartyChatMessage("Game(s) added successfully!");
        }
    }

    private String getTooFewArgsFailureMessage() {
        return "You need to specify what game(s) you want to add!";
    }
}
