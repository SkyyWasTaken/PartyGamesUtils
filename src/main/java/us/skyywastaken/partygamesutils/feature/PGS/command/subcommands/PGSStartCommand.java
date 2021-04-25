package us.skyywastaken.partygamesutils.feature.PGS.command.subcommands;

import jline.internal.Nullable;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import us.skyywastaken.partygamesutils.command.PartyCommand;
import us.skyywastaken.partygamesutils.command.SubCommand;
import us.skyywastaken.partygamesutils.feature.PGS.PGSManager;
import us.skyywastaken.partygamesutils.util.HypixelUtils;
import us.skyywastaken.partygamesutils.util.StringUtils;

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
    public String getHelpInformation() {
        return StringUtils.BODY_FORMATTING + "This command starts seeking. You can alternatively used /pgs ToggleSeek or /pgs settings, but some prefer having dedicated start and stop commands.\n"
                + StringUtils.INFORMATION_FORMATTING + "Usage: " + StringUtils.COMMAND_USAGE_FORMATTING
                + "/pgs start";
    }

    @Override
    public void onPartyCommand(String[] args) {
        System.out.println("got the command");
        enableSeeking();
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
            return "Seeking has been enabled!";
        } else {
            return EnumChatFormatting.GREEN + "Seeking has been " + EnumChatFormatting.AQUA + "ENABLED!";
        }
    }

    private void enableSeeking() {
        PGS_MANAGER.setSeekingEnabled(true);
    }
}
