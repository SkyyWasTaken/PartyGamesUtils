package us.skyywastaken.partygamesutils.command.pgs.SubCommands;

import net.minecraft.client.Minecraft;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import us.skyywastaken.partygamesutils.command.PartyCommand;
import us.skyywastaken.partygamesutils.command.SubCommand;
import us.skyywastaken.partygamesutils.misc.SeekManager;
import us.skyywastaken.partygamesutils.util.HypixelUtils;

import java.util.ArrayList;

public class PGSListCommand implements SubCommand, PartyCommand {
    private final SeekManager SEEK_MANAGER;

    public PGSListCommand(SeekManager passedSeekManager) {
        this.SEEK_MANAGER = passedSeekManager;
    }
    @Override
    public void onCommand(ICommandSender commandSender, String[] args) {
        String messageString;
        ArrayList<String> seekList = SEEK_MANAGER.getSeekList();
        int seekListSize = seekList.size();
        if(seekListSize <= 0) {
            messageString = EnumChatFormatting.YELLOW + "There are no games in the seek list!";
        } else {
            String seekListString = String.join(EnumChatFormatting.YELLOW + ", "
                    + EnumChatFormatting.AQUA, seekList);
            if(seekListSize == 1) {
                messageString = EnumChatFormatting.GREEN + "Current game: " + seekListString;
            } else {
                messageString = EnumChatFormatting.GREEN + "Current games: " + seekListString;
            }
        }
        commandSender.addChatMessage(new ChatComponentText(messageString));
    }

    @Override
    public void onPartyCommand(String[] args) {
        String messageString;
        ArrayList<String> seekList = SEEK_MANAGER.getSeekList();
        int seekListSize = seekList.size();
        if(seekListSize <= 0) {
            messageString = "There are no games in the seek list!";
        } else {
            String seekListString = String.join(", ", seekList);
            if(seekListSize == 1) {
                messageString = "Current game: " + seekListString;
            } else {
                messageString = "Current games: " + seekListString;
            }
        }
        HypixelUtils.sendPartyChatMessage(messageString);
    }
}
