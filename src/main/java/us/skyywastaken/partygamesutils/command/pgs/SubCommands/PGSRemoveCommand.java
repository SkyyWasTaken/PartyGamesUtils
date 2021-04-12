package us.skyywastaken.partygamesutils.command.pgs.SubCommands;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import us.skyywastaken.partygamesutils.command.PartyCommand;
import us.skyywastaken.partygamesutils.command.SubCommand;
import us.skyywastaken.partygamesutils.misc.SeekManager;

import java.util.Arrays;
import java.util.List;

public class PGSRemoveCommand implements SubCommand, PartyCommand {
    private final SeekManager SEEK_MANAGER;
    public PGSRemoveCommand(SeekManager passedSeekManager) {
        this.SEEK_MANAGER = passedSeekManager;
    }

    @Override
    public void onCommand(ICommandSender commandSender, String[] args) {
        String clientRemoveMessage = EnumChatFormatting.RED + "Removed " + EnumChatFormatting.YELLOW + "%gameName%"
                + EnumChatFormatting.RED + " from the seek list!";
        String[] gameStrings = getGameListStringFromArgs(args);
        for(String currentString : gameStrings) {
            String trimmedString = currentString.trim();
            removeGame(trimmedString);
            String addedGameMessage = clientRemoveMessage.replace("%gameName%", trimmedString);
            if(!(Minecraft.getMinecraft().ingameGUI == null)) {
                Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(
                        new ChatComponentText(addedGameMessage));
            }
        }
    }

    @Override
    public List<String> getTabCompletions(ICommandSender sender, String[] args, BlockPos blockPos) {
        return null;
    }

    @Override
    public void onPartyCommand(String[] args) {
        EntityPlayerSP playerSP = Minecraft.getMinecraft().thePlayer;
        if(args.length < 2) {
            playerSP.sendChatMessage("/pchat You need to specify what game(s) you want to remove!");
        }
        removeGames(getGameListStringFromArgs(args));
        playerSP.sendChatMessage("/pchat Game(s) removed successfully!");
    }

    private void removeGame(String gameToAdd) {
        SEEK_MANAGER.removeSeekedGame(gameToAdd);
    }

    private void removeGames(String[] passedGameStrings) {
        for(String currentGameString : passedGameStrings) {
            String finalizedGameString = currentGameString.trim();
            removeGame(finalizedGameString);
        }
    }

    private String[] getGameListStringFromArgs(String[] rawArgsArray) {
        String rawArgsString = String.join(" ", rawArgsArray);
        String gameListString = rawArgsString.replace("remove", "");
        return gameListString.split(",");
    }
}
