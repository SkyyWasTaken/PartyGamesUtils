package us.skyywastaken.partygamesutils.feature.pgs.command.subcommands;

import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import us.skyywastaken.partygamesutils.command.SubCommand;
import us.skyywastaken.partygamesutils.feature.pgs.misc.ListMenuManager;
import us.skyywastaken.partygamesutils.feature.pgs.settings.SeekSettings;
import us.skyywastaken.partygamesutils.util.StringUtils;

import java.util.List;

public class PGSRemoveCommand implements SubCommand {
    private final SeekSettings SEEK_SETTINGS;
    private final ListMenuManager LIST_MENU_MANAGER;

    public PGSRemoveCommand(SeekSettings passedSeekSettings, ListMenuManager passedListMenuManager) {
        this.SEEK_SETTINGS = passedSeekSettings;
        this.LIST_MENU_MANAGER = passedListMenuManager;
    }

    @Override
    public void onCommand(ICommandSender commandSender, String[] args) {
        if (args.length == 0) {
            sendTooFewArgsFailureMessage(commandSender);
            return;
        }
        String[] gameList = getGameListStringsFromArgs(args);
        removeGames(gameList);
        if (args.length > 1 && args[1].equals("--displaylist")) {
            LIST_MENU_MANAGER.displayList();
        } else {
            sendClientSuccessMessages(commandSender, gameList);
        }
    }

    @Override
    public List<String> getTabCompletions(ICommandSender sender, String[] args, BlockPos blockPos) {
        return null;
    }

    @Override
    public String getHelpInformation() {
        return StringUtils.ACCENT_FORMATTING + "/pgs remove " + StringUtils.BODY_FORMATTING + "is used to remove games "
                + "from the seek list. You can remove multiple games at a time by separating game names with commas.\n"
                + StringUtils.INFORMATION_FORMATTING + "Usage: " + StringUtils.COMMAND_USAGE_FORMATTING
                + "/pgs remove <game1>, <game2>, <game3>";
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
        String rawArgsString = String.join(" ", rawArgsArray).replace("--displaylist", "").trim();
        return rawArgsString.split(",");
    }

    private void sendTooFewArgsFailureMessage(ICommandSender commandSender) {
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
        return StringUtils.BODY_FORMATTING + "Removed " + EnumChatFormatting.YELLOW + addedGame.trim()
                + StringUtils.BODY_FORMATTING + " from the seek list!";
    }

    private String getTooFewArgsFailureMessage() {
        return StringUtils.WARNING_FORMATTING
                + "You need to specify what game(s) you want to remove:\n"
                + EnumChatFormatting.WHITE + "ex. /pgs remove Lawn Moower, RPG-16, Lab Escape";
    }
}
