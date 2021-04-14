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

public class PGSStartCommand implements SubCommand, PartyCommand {
    private final PGSManager PGS_MANAGER;

    public PGSStartCommand(PGSManager passedPGSManager) {
        this.PGS_MANAGER = passedPGSManager;
    }

    @Override
    public void onCommand(ICommandSender commandSender, String[] args) {
        enableSeeking();
        sendSuccessMessage(false, commandSender);
    }

    @Override
    public List<String> getTabCompletions(ICommandSender sender, String[] args, BlockPos blockPos) {
        return null;
    }

    @Override
    public void onPartyCommand(String[] args) {
        enableSeeking();
        sendSuccessMessage(true, null);
    }

    private void sendSuccessMessage(boolean isPartyCommand, @Nullable ICommandSender commandSender) {
        String successMessage = getSuccessMessage(isPartyCommand);
        if(isPartyCommand) {
            HypixelUtils.sendPartyChatMessage(successMessage);
        } else {
            if(commandSender != null) {
                commandSender.addChatMessage(new ChatComponentText(successMessage));
            }
        }
    }

    private String getSuccessMessage(boolean isPartyCommand) {
        if(isPartyCommand) {
            return "Seeking has been enabled!";
        } else {
            return EnumChatFormatting.GREEN + "Seeking has been " + EnumChatFormatting.AQUA + "ENABLED!";
        }
    }

    private void enableSeeking() {
        PGS_MANAGER.setSeekingEnabled(true);
    }
}
