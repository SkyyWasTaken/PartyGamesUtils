package us.skyywastaken.partygamesutils.command.pgs.subcommands;

import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import us.skyywastaken.partygamesutils.command.PartyCommand;
import us.skyywastaken.partygamesutils.command.SubCommand;
import us.skyywastaken.partygamesutils.command.pgs.PGSManager;
import us.skyywastaken.partygamesutils.util.HypixelUtils;

import javax.annotation.Nullable;
import java.util.List;

public class PGSClearCommand implements SubCommand, PartyCommand {
    private final PGSManager PGS_MANAGER;

    public PGSClearCommand(PGSManager passedSeekManager) {
        this.PGS_MANAGER = passedSeekManager;
    }

    @Override
    public void onCommand(ICommandSender commandSender, String[] args) {
        PGS_MANAGER.clearSeekList();
        sendSuccessMessage(false, commandSender);
    }

    @Override
    public List<String> getTabCompletions(ICommandSender sender, String[] args, BlockPos blockPos) {
        return null;
    }

    @Override
    public void onPartyCommand(String[] args) {
        PGS_MANAGER.clearSeekList();
        sendSuccessMessage(true, null);
    }

    private void sendSuccessMessage(boolean isPartyCommand, @Nullable ICommandSender commandSender) {
        String successMessage = getSuccessMessage(isPartyCommand);
        if (isPartyCommand) {
            HypixelUtils.sendPartyChatMessage(successMessage);
        } else {
            if (commandSender != null) {
                commandSender.addChatMessage(new ChatComponentText(successMessage));
            }
        }
    }

    private String getSuccessMessage(boolean isPartyCommand) {
        if (isPartyCommand) {
            return "Successfully cleared the seek list!";
        } else {
            return EnumChatFormatting.GREEN + "Successfully " + EnumChatFormatting.YELLOW
                    + "cleared " + EnumChatFormatting.GREEN + "the seek list!";
        }
    }
}
