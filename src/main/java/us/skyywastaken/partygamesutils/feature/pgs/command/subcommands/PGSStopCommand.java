package us.skyywastaken.partygamesutils.feature.pgs.command.subcommands;

import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import us.skyywastaken.partygamesutils.command.SubCommand;
import us.skyywastaken.partygamesutils.feature.pgs.settings.SeekSettings;
import us.skyywastaken.partygamesutils.util.StringUtils;

import java.util.List;

public class PGSStopCommand implements SubCommand {
    private final SeekSettings PGS_MANAGER;

    public PGSStopCommand(SeekSettings passedSeekSettings) {
        this.PGS_MANAGER = passedSeekSettings;
    }

    @Override
    public void onCommand(ICommandSender commandSender, String[] args) {
        disableSeeking();
        sendSuccessMessage(commandSender);
    }

    @Override
    public List<String> getTabCompletions(ICommandSender sender, String[] args, BlockPos blockPos) {
        return null;
    }

    @Override
    public String getHelpInformation() {
        return StringUtils.BODY_FORMATTING + "This command stops seeking. You can alternatively used /pgs ToggleSeek or "
                + "/pgs settings, but some prefer having dedicated start and stop commands.\n"
                + StringUtils.INFORMATION_FORMATTING + "Usage: " + StringUtils.COMMAND_USAGE_FORMATTING
                + "/pgs stop";
    }

    private void sendSuccessMessage(ICommandSender commandSender) {
        String successMessage = getSuccessMessage();
        commandSender.addChatMessage(new ChatComponentText(successMessage));
    }

    private String getSuccessMessage() {
        return StringUtils.BODY_FORMATTING + "Seeking has been " + EnumChatFormatting.DARK_RED + "DISABLED!";
    }

    private void disableSeeking() {
        PGS_MANAGER.setSeekingEnabled(false);
    }
}
