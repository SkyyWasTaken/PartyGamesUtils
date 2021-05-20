package us.skyywastaken.partygamesutils.feature.pgs.command.subcommands;

import jline.internal.Nullable;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import us.skyywastaken.partygamesutils.command.SubCommand;
import us.skyywastaken.partygamesutils.feature.pgs.settings.SeekSettings;
import us.skyywastaken.partygamesutils.util.StringUtils;

import java.util.List;

public class PGSAddCommand implements SubCommand {
    private final SeekSettings SEEK_SETTINGS;

    public PGSAddCommand(SeekSettings passedSeekSettings) {
        this.SEEK_SETTINGS = passedSeekSettings;
    }

    @Override
    public void onCommand(ICommandSender commandSender, String[] args) {
        if (args.length == 0) {
            sendTooFewArgsFailureMessage(commandSender);
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
        return StringUtils.ACCENT_FORMATTING + "/pgs add " + StringUtils.BODY_FORMATTING + "is used to add games to the seek list. "
                + "You can add multiple games by separating names with commas.\n"
                + StringUtils.INFORMATION_FORMATTING + "Usage: " + StringUtils.COMMAND_USAGE_FORMATTING
                + "/pgs add <game1>, <game2>, <game3>";
    }


    private void addGame(String gameToAdd) {
        if (!SEEK_SETTINGS.isGameSought(gameToAdd)) {
            SEEK_SETTINGS.addSoughtGame(gameToAdd);
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

    private void sendTooFewArgsFailureMessage(@Nullable ICommandSender commandSender) {
        String failureMessage = getTooFewArgsFailureMessage();
        commandSender.addChatMessage(new ChatComponentText(failureMessage));
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

    private String getTooFewArgsFailureMessage() {
        return StringUtils.WARNING_FORMATTING + "You need to specify what game(s) you want to add:\n"
                + EnumChatFormatting.WHITE + "ex. /pgs add Pig Fishing, Animal Slaughter, Shooting Range";
    }
}
