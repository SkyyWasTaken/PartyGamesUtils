package us.skyywastaken.partygamesutils.feature.PGS.command.subcommands;

import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import us.skyywastaken.partygamesutils.command.SubCommand;
import us.skyywastaken.partygamesutils.feature.PGS.settings.SeekSettings;
import us.skyywastaken.partygamesutils.util.StringUtils;

import java.util.List;

public class PGSClearCommand implements SubCommand {
    private final SeekSettings SEEK_SETTINGS;

    public PGSClearCommand(SeekSettings passedSeekManager) {
        this.SEEK_SETTINGS = passedSeekManager;
    }

    @Override
    public void onCommand(ICommandSender commandSender, String[] args) {
        SEEK_SETTINGS.clearSeekList();
        sendSuccessMessage(commandSender);
    }

    @Override
    public List<String> getTabCompletions(ICommandSender sender, String[] args, BlockPos blockPos) {
        return null;
    }

    @Override
    public String getHelpInformation() {
        return StringUtils.ACCENT_FORMATTING + "/pgs clear " + StringUtils.BODY_FORMATTING + "is used to clear the "
                + "seek list, just in case you don't want to individually remove each game.\n"
                + StringUtils.INFORMATION_FORMATTING + "Usage: " + StringUtils.COMMAND_USAGE_FORMATTING
                + "/pgs clear";
    }

    private void sendSuccessMessage(ICommandSender commandSender) {
        String successMessage = getSuccessMessage();
        if (commandSender != null) {
            commandSender.addChatMessage(new ChatComponentText(successMessage));
        }
    }

    private String getSuccessMessage() {
        return StringUtils.BODY_FORMATTING + "Successfully " + EnumChatFormatting.YELLOW + "cleared "
                + StringUtils.BODY_FORMATTING + "the seek list!";
    }
}
