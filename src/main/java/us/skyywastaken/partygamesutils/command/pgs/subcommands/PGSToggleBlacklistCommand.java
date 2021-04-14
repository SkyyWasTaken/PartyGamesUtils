package us.skyywastaken.partygamesutils.command.pgs.subcommands;

import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import us.skyywastaken.partygamesutils.command.PartyCommand;
import us.skyywastaken.partygamesutils.command.SubCommand;
import us.skyywastaken.partygamesutils.command.pgs.PGSManager;
import us.skyywastaken.partygamesutils.util.HypixelUtils;
import us.skyywastaken.partygamesutils.util.StringUtils;

import javax.annotation.Nullable;
import java.util.List;

public class PGSToggleBlacklistCommand implements SubCommand, PartyCommand {
    private final PGSManager PGS_MANAGER;

    public PGSToggleBlacklistCommand(PGSManager passedPGSManager) {
        this.PGS_MANAGER = passedPGSManager;
    }

    @Override
    public void onCommand(ICommandSender commandSender, String[] args) {
        toggleBlacklist();
        sendSuccessMessage(false, commandSender);
    }

    @Override
    public List<String> getTabCompletions(ICommandSender sender, String[] args, BlockPos blockPos) {
        return null;
    }

    @Override
    public void onPartyCommand(String[] args) {
        toggleBlacklist();
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
        boolean isBlacklistEnabled = PGS_MANAGER.isBlacklistEnabled();
        if (isPartyCommand) {
            return "The blacklist has been " + StringUtils.getColorlessEnabledDisabledString(isBlacklistEnabled);
        } else {
            return EnumChatFormatting.GREEN + "The blacklist has been "
                    + StringUtils.getEnabledDisabledString(isBlacklistEnabled);
        }
    }

    private void toggleBlacklist() {
        PGS_MANAGER.setBlacklistEnabled(!PGS_MANAGER.isBlacklistEnabled());
    }
}
