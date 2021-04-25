package us.skyywastaken.partygamesutils.feature.PGS.command.subcommands;

import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import us.skyywastaken.partygamesutils.command.PartyCommand;
import us.skyywastaken.partygamesutils.command.SubCommand;
import us.skyywastaken.partygamesutils.feature.PGS.settings.SeekSettings;
import us.skyywastaken.partygamesutils.util.HypixelUtils;
import us.skyywastaken.partygamesutils.util.StringUtils;

import javax.annotation.Nullable;
import java.util.List;

public class PGSClearCommand implements SubCommand, PartyCommand {
    private final SeekSettings PGS_MANAGER;

    public PGSClearCommand(SeekSettings passedSeekManager) {
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
    public String getHelpInformation() {
        return StringUtils.BODY_FORMATTING + "This command is used to clear the seek list, "
                + "just in case you don't want to individually remove each game.\n"
                + StringUtils.INFORMATION_FORMATTING + "Usage: " + StringUtils.COMMAND_USAGE_FORMATTING
                + "/pgs clear";
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
            return StringUtils.BODY_FORMATTING + "Successfully " + EnumChatFormatting.YELLOW
                    + "cleared " + StringUtils.BODY_FORMATTING + "the seek list!";
        }
    }
}
