package us.skyywastaken.partygamesutils.feature.PGS.command.subcommands;

import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import us.skyywastaken.partygamesutils.command.PartyCommand;
import us.skyywastaken.partygamesutils.command.SubCommand;
import us.skyywastaken.partygamesutils.feature.PGS.PGSManager;
import us.skyywastaken.partygamesutils.feature.PGS.misc.SettingsMenuManager;
import us.skyywastaken.partygamesutils.util.HypixelUtils;
import us.skyywastaken.partygamesutils.util.StringUtils;

import javax.annotation.Nullable;
import java.util.List;

public class PGSToggleBlacklistCommand implements SubCommand, PartyCommand {
    private final PGSManager PGS_MANAGER;
    private final SettingsMenuManager SETTINGS_MENU_MANAGER;

    public PGSToggleBlacklistCommand(PGSManager passedPGSManager) {
        this.PGS_MANAGER = passedPGSManager;
        this.SETTINGS_MENU_MANAGER = new SettingsMenuManager(this.PGS_MANAGER);
    }

    @Override
    public void onCommand(ICommandSender commandSender, String[] args) {
        toggleBlacklist();
        if(args.length >= 1 && args[0].equals("--displaysettings")) {
            SETTINGS_MENU_MANAGER.displaySettingsMenu();
        } else {
            sendSuccessMessage(false, commandSender);
        }
    }

    @Override
    public List<String> getTabCompletions(ICommandSender sender, String[] args, BlockPos blockPos) {
        return null;
    }

    @Override
    public String getHelpInformation() {
        return StringUtils.BODY_FORMATTING + "This command toggles the blacklist. When the blacklist is enabled, it will allow any game but the games on your seek list.\n"
                + StringUtils.INFORMATION_FORMATTING + "Usage: " + StringUtils.COMMAND_USAGE_FORMATTING
                + "/pgs add <game1>, <game2>, <game3>";
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
