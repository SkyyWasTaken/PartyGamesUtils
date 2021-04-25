package us.skyywastaken.partygamesutils.feature.PGS.command.subcommands;

import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;
import us.skyywastaken.partygamesutils.command.SubCommand;
import us.skyywastaken.partygamesutils.feature.PGS.misc.SettingsMenuManager;
import us.skyywastaken.partygamesutils.util.StringUtils;

import java.util.List;

public class PGSSettingsCommand implements SubCommand {
    private final SettingsMenuManager SETTINGS_MENU_MANAGER;
    public PGSSettingsCommand(SettingsMenuManager settingsMenuManager) {
        this.SETTINGS_MENU_MANAGER = settingsMenuManager;
    }

    @Override
    public void onCommand(ICommandSender commandSender, String[] args) {
        SETTINGS_MENU_MANAGER.displaySettingsMenu();
    }

    @Override
    public List<String> getTabCompletions(ICommandSender sender, String[] args, BlockPos blockPos) {
        return null;
    }

    @Override
    public String getHelpInformation() {
        return StringUtils.BODY_FORMATTING + "This command is used to manage PGS-related settings. "
                + "To change a setting, just click on it!\n"
                + StringUtils.INFORMATION_FORMATTING + "Usage: " + StringUtils.COMMAND_USAGE_FORMATTING
                + "/pgs settings";
    }

}
