package us.skyywastaken.partygamesutils.command.pgs.subcommands;

import jline.internal.Nullable;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import us.skyywastaken.partygamesutils.command.PartyCommand;
import us.skyywastaken.partygamesutils.command.SubCommand;
import us.skyywastaken.partygamesutils.command.pgs.PGSManager;
import us.skyywastaken.partygamesutils.util.HypixelUtils;

import java.util.List;

public class PGSRemoveCommand implements SubCommand, PartyCommand {
    private final PGSManager PGS_MANAGER;

    public PGSRemoveCommand(PGSManager passedPGSManager) {
        this.PGS_MANAGER = passedPGSManager;
    }

    @Override
    public void onCommand(ICommandSender commandSender, String[] args) {
        if (args.length < 2) {
            sendTooFewArgsFailureMessage(false, commandSender);
            return;
        }
        String[] gameList = getGameListStringsFromArgs(args);
        removeGames(gameList);
        sendClientSuccessMessages(commandSender, gameList);
    }

    @Override
    public List<String> getTabCompletions(ICommandSender sender, String[] args, BlockPos blockPos) {
        return null;
    }

    @Override
    public void onPartyCommand(String[] args) {
        if (args.length < 2) {
            sendTooFewArgsFailureMessage(true, null);
            return;
        }
        String[] gameList = getGameListStringsFromArgs(args);
        getGameListStringsFromArgs(args);
        sendPartySuccessMessage(gameList.length);
    }


    private void removeGame(String gameToAdd) {
        PGS_MANAGER.removeSoughtGame(gameToAdd);
    }

    private void removeGames(String[] passedGameStrings) {
        for (String currentGameString : passedGameStrings) {
            String finalizedGameString = currentGameString.trim();
            removeGame(finalizedGameString);
        }
    }

    private String[] getGameListStringsFromArgs(String[] rawArgsArray) {
        String rawArgsString = String.join(" ", rawArgsArray);
        String gameListString = rawArgsString.toLowerCase().replace("remove", "");
        return gameListString.split(",");
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
            HypixelUtils.sendPartyChatMessage("Game removed successfully!");
        } else {
            HypixelUtils.sendPartyChatMessage("Game(s) removed successfully!");
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
        return EnumChatFormatting.GREEN + "Removed " + EnumChatFormatting.YELLOW + addedGame.trim()
                + EnumChatFormatting.GREEN + " from the seek list!";
    }

    private String getTooFewArgsFailureMessage(boolean isPartyCommand) {
        if (isPartyCommand) {
            return "You need to specify what game(s) you want to remove!";
        } else {
            return EnumChatFormatting.RED
                    + "You need to specify what game(s) you want to remove:\n"
                    + EnumChatFormatting.WHITE + "ex. /pgs remove Lawn Moower, RPG-16, Lab Escape";
        }
    }
}
