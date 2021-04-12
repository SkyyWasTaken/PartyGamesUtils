package us.skyywastaken.partygamesutils.command.pgs.SubCommands;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import us.skyywastaken.partygamesutils.command.SubCommand;
import us.skyywastaken.partygamesutils.command.PartyCommand;
import us.skyywastaken.partygamesutils.misc.SeekManager;

import java.util.Arrays;

public class PGSAddCommand implements SubCommand, PartyCommand {
    private final SeekManager SEEK_MANAGER;

    public PGSAddCommand(SeekManager passedSeekManager) {
        this.SEEK_MANAGER = passedSeekManager;
    }

    @Override
    public void onCommand(ICommandSender commandSender, String[] args) {
        String clientAddMessage = EnumChatFormatting.GREEN + "Added " + EnumChatFormatting.YELLOW + "%gameName%"
                + EnumChatFormatting.GREEN + " to the seek list!";
        if(args.length < 2) {
            String tooFewArgsFailureMessage = EnumChatFormatting.RED
                    + "You need to specify what game(s) you want to add:\n"
                    + EnumChatFormatting.WHITE + "ex. /pgs add Pig Fishing, Animal Slaughter, Shooting Range";
            commandSender.addChatMessage(new ChatComponentText(tooFewArgsFailureMessage));
            return;
        }
        String[] gameList = getGameListStringFromArgs(args);
        for(String currentString : gameList) {
            String trimmedString = currentString.trim();
            addGame(trimmedString);
            String addedGameMessage = clientAddMessage.replace("%gameName%", trimmedString);
            if(!(Minecraft.getMinecraft().ingameGUI == null)) {
                Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(
                        new ChatComponentText(addedGameMessage));
            }
        }
    }

    @Override
    public void onPartyCommand(String[] args) {
        EntityPlayerSP playerSP = Minecraft.getMinecraft().thePlayer;
        if(args.length < 2) {
            playerSP.sendChatMessage("/pchat You need to specify what game(s) you want to add!");
        }
        addGames(getGameListStringFromArgs(args));
        playerSP.sendChatMessage("/pchat Game(s) added successfully!");
    }

    private void addGame(String gameToAdd) {
        SEEK_MANAGER.addSeekedGame(gameToAdd);
    }

    private void addGames(String[] passedGameStrings) {
        for(String currentGameString : passedGameStrings) {
            String finalizedGameString = currentGameString.trim();
            addGame(finalizedGameString);
        }
    }

    private String[] getGameListStringFromArgs(String[] rawArgsArray) {
        String rawArgsString = String.join(" ", rawArgsArray);
        String gameListString = rawArgsString.replace("add", "");
        return gameListString.split(",");
    }
}
