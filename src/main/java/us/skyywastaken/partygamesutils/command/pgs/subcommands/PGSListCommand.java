package us.skyywastaken.partygamesutils.command.pgs.subcommands;

import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import us.skyywastaken.partygamesutils.command.PartyCommand;
import us.skyywastaken.partygamesutils.command.SubCommand;
import us.skyywastaken.partygamesutils.command.pgs.PGSManager;
import us.skyywastaken.partygamesutils.util.HypixelUtils;

import java.util.List;

public class PGSListCommand implements SubCommand, PartyCommand {
    private final PGSManager SEEK_MANAGER;

    public PGSListCommand(PGSManager passedSeekManager) {
        this.SEEK_MANAGER = passedSeekManager;
    }
    @Override
    public void onCommand(ICommandSender commandSender, String[] args) {
        String messageString;
        List<String> seekList = SEEK_MANAGER.getSeekList();
        int seekListSize = seekList.size();
        if(seekListSize <= 0) {
            messageString = EnumChatFormatting.YELLOW + "There are no games in the seek list!";
        } else {
            String seekListString = String.join(EnumChatFormatting.YELLOW + ", "
                    + EnumChatFormatting.AQUA, seekList);
            if(seekListSize == 1) {
                messageString = EnumChatFormatting.GREEN + "Current game: " + EnumChatFormatting.AQUA + seekListString;
            } else {
                messageString = EnumChatFormatting.GREEN + "Current games: " + EnumChatFormatting.AQUA + seekListString;
            }
        }
        commandSender.addChatMessage(new ChatComponentText(messageString));
    }

    @Override
    public List<String> getTabCompletions(ICommandSender sender, String[] args, BlockPos blockPos) {
        return null;
    }

    @Override
    public void onPartyCommand(String[] args) {
        String messageString;
        List<String> seekList = SEEK_MANAGER.getSeekList();
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
        if(messageString.length() > 246) {
            HypixelUtils.sendPartyChatMessage("The seek list is too large to send!");
        } else {
            HypixelUtils.sendPartyChatMessage(messageString);
        }
    }
}
