package us.skyywastaken.partygamesutils.feature.PGS.command.subcommands;

import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import us.skyywastaken.partygamesutils.command.SubCommand;
import us.skyywastaken.partygamesutils.feature.PGS.PGSManager;
import us.skyywastaken.partygamesutils.util.StringUtils;

import java.util.List;

public class PGSTogglePCCommand implements SubCommand {
    private final PGSManager PGS_MANAGER;

    public PGSTogglePCCommand(PGSManager passedPartyCommandManager) {
        this.PGS_MANAGER = passedPartyCommandManager;
    }

    @Override
    public void onCommand(ICommandSender commandSender, String[] args) {
        togglePartyCommands();
        sendSuccessMessage(commandSender);
    }

    private void togglePartyCommands() {
        PGS_MANAGER.setPartyCommandsEnabled(!PGS_MANAGER.getPartyCommandsEnabled());
    }

    private void sendSuccessMessage(ICommandSender commandSender) {
        String successMessage = getSuccessMessage();
        commandSender.addChatMessage(new ChatComponentText(successMessage));
    }

    private String getSuccessMessage() {
        boolean partyCommandStatus = PGS_MANAGER.getPartyCommandsEnabled();
        return EnumChatFormatting.GREEN + "Party commands are now "
                + StringUtils.getEnabledDisabledString(partyCommandStatus);
    }

    @Override
    public List<String> getTabCompletions(ICommandSender sender, String[] args, BlockPos blockPos) {
        return null;
    }

    @Override
    public String getHelpInformation() {
        return StringUtils.BODY_FORMATTING + "This command is used to enable/disable party commands. When party "
                + "commands are enabled, party members can use some PGS commands. You can view PGS command permissions "
                + "with /pgs PartyPermissions or /pgs settings.\n"
                + StringUtils.INFORMATION_FORMATTING + "Usage: " + StringUtils.COMMAND_USAGE_FORMATTING
                + "/pgs TogglePartyCommands";
    }
}
