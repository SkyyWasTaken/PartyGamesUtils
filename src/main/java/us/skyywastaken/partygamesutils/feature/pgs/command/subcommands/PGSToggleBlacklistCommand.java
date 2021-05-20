package us.skyywastaken.partygamesutils.feature.pgs.command.subcommands;

import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import us.skyywastaken.partygamesutils.command.SubCommand;
import us.skyywastaken.partygamesutils.feature.pgs.misc.SettingsMenuManager;
import us.skyywastaken.partygamesutils.feature.pgs.settings.SeekSettings;
import us.skyywastaken.partygamesutils.util.StringUtils;

import java.util.List;

public class PGSToggleBlacklistCommand implements SubCommand {
    private final SeekSettings PGS_MANAGER;
    private final SettingsMenuManager SETTINGS_MENU_MANAGER;

    public PGSToggleBlacklistCommand(SeekSettings passedSeekSettings, SettingsMenuManager passedSettingsMenuManager) {
        this.PGS_MANAGER = passedSeekSettings;
        this.SETTINGS_MENU_MANAGER = passedSettingsMenuManager;
    }

    @Override
    public void onCommand(ICommandSender commandSender, String[] args) {
        toggleBlacklist();
        if (args.length >= 1 && args[0].equals("--displaysettings")) {
            SETTINGS_MENU_MANAGER.displaySettingsMenu();
        } else {
            sendSuccessMessage(commandSender);
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

    private void sendSuccessMessage(ICommandSender commandSender) {
        String successMessage = getSuccessMessage();
        commandSender.addChatMessage(new ChatComponentText(successMessage));
    }

    private String getSuccessMessage() {
        boolean isBlacklistEnabled = PGS_MANAGER.isBlacklistEnabled();
        return StringUtils.BODY_FORMATTING + "The blacklist has been "
                + StringUtils.getEnabledDisabledString(isBlacklistEnabled);
    }

    private void toggleBlacklist() {
        PGS_MANAGER.setBlacklistEnabled(!PGS_MANAGER.isBlacklistEnabled());
    }
}
