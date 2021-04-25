package us.skyywastaken.partygamesutils.feature.PGS.command.subcommands;

import jline.internal.Nullable;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import us.skyywastaken.partygamesutils.command.PartyCommand;
import us.skyywastaken.partygamesutils.command.SubCommand;
import us.skyywastaken.partygamesutils.feature.PGS.settings.SeekSettings;
import us.skyywastaken.partygamesutils.util.HypixelUtils;
import us.skyywastaken.partygamesutils.util.StringUtils;

import java.util.List;

public class PGSAddCommand implements SubCommand, PartyCommand {
    private final SeekSettings PGS_MANAGER;

    public PGSAddCommand(SeekSettings passedSeekSettings) {
        this.PGS_MANAGER = passedSeekSettings;
    }

    @Override
    public void onCommand(ICommandSender commandSender, String[] args) {
        if (args.length == 0) {
            sendTooFewArgsFailureMessage(false, commandSender);
            return;
        }
        String[] gameList = getGameListStringsFromArgs(args);
        addGames(gameList);
        sendClientSuccessMessages(commandSender, gameList);
    }

    @Override
    public List<String> getTabCompletions(ICommandSender sender, String[] args, BlockPos blockPos) {
        return null;
    }

    @Override
    public String getHelpInformation() {
        return StringUtils.BODY_FORMATTING + "This command is used to clear the seek list. "
                + "You can add multiple games by separating names with commas.\n"
                + StringUtils.INFORMATION_FORMATTING + "Usage: " + StringUtils.COMMAND_USAGE_FORMATTING
                + "/pgs add <game1>, <game2>, <game3>";
    }

    @Override
    public void onPartyCommand(String[] args) {
        if (args.length == 0) {
            sendTooFewArgsFailureMessage(true, null);
            return;
        }
        String[] gameListString = getGameListStringsFromArgs(args);
        addGames(gameListString);
        sendPartySuccessMessage(gameListString.length);
    }


    private void addGame(String gameToAdd) {
        if (!PGS_MANAGER.isGameSought(gameToAdd)) {
            PGS_MANAGER.addSoughtGame(gameToAdd);
        }
    }

    private void addGames(String[] passedGameStrings) {
        for (String currentGameString : passedGameStrings) {
            String finalizedGameString = currentGameString.trim();
            addGame(finalizedGameString);
        }
    }

    private String[] getGameListStringsFromArgs(String[] rawArgsArray) {
        String rawArgsString = String.join(" ", rawArgsArray);
        return rawArgsString.split(",");
    }

    private void sendTooFewArgsFailureMessage(boolean isPartyCommand, @Nullable ICommandSender commandSender) {
        if (isPartyCommand) {
            HypixelUtils.sendPartyChatMessage(getTooFewArgsFailureMessage(true));
        } else {
            if (commandSender != null) {
                String failureMessage = getTooFewArgsFailureMessage(false);
                commandSender.addChatMessage(new ChatComponentText(failureMessage));
            }
        }
    }

    private void sendPartySuccessMessage(int gamesAdded) {
        if (gamesAdded == 1) {
            HypixelUtils.sendPartyChatMessage("Game added successfully!");
        } else {
            HypixelUtils.sendPartyChatMessage("Game(s) added successfully!");
        }
    }

    private void sendClientSuccessMessages(ICommandSender commandSender, String[] addedGames) {
        for (String currentGame : addedGames) {
            sendClientSuccessMessage(commandSender, currentGame);
        }
    }

    private void sendClientSuccessMessage(ICommandSender commandSender, String addedGame) {
        String clientAddMessage = getClientSuccessMessage(addedGame);
        commandSender.addChatMessage(new ChatComponentText(clientAddMessage));
    }

    private String getClientSuccessMessage(String addedGame) {
        return StringUtils.BODY_FORMATTING + "Added " + EnumChatFormatting.YELLOW + addedGame.trim()
                + StringUtils.BODY_FORMATTING + " to the seek list!";
    }

    private String getTooFewArgsFailureMessage(boolean isPartyCommand) {
        if (isPartyCommand) {
            return "You need to specify what game(s) you want to add!";
        } else {
            return StringUtils.WARNING_FORMATTING
                    + "You need to specify what game(s) you want to add:\n"
                    + EnumChatFormatting.WHITE + "ex. /pgs add Pig Fishing, Animal Slaughter, Shooting Range";
        }
    }
}
