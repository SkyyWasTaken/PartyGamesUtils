package us.skyywastaken.partygamesutils.feature.PGS.command.subcommands;

import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import us.skyywastaken.partygamesutils.command.SubCommand;
import us.skyywastaken.partygamesutils.feature.PGS.settings.SeekSettings;
import us.skyywastaken.partygamesutils.feature.PGS.misc.SettingsMenuManager;
import us.skyywastaken.partygamesutils.util.StringUtils;

import java.util.List;

public class PGSToggleSeekCommand implements SubCommand{
    private final SeekSettings PGS_MANAGER;
    private final SettingsMenuManager SETTINGS_MENU_MANAGER;

    public PGSToggleSeekCommand(SeekSettings passedSeekSettings) {
        this.PGS_MANAGER = passedSeekSettings;
        this.SETTINGS_MENU_MANAGER = new SettingsMenuManager(this.PGS_MANAGER);
    }

    @Override
    public void onCommand(ICommandSender commandSender, String[] args) {
        toggleSeeking();
        if(args.length >= 1 && args[0].equals("--displaysettings")) {
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
        return StringUtils.BODY_FORMATTING + "Using this command will toggle seeking. "
                + "When seeking is enabled, PGS will check your current party game and try to pull you out if it "
                + "doesn't match your requirements. When seeking is disabled, PGS won't check any games and you "
                + "cannot get pulled out.\n"
                + StringUtils.INFORMATION_FORMATTING + "Usage: " + StringUtils.COMMAND_USAGE_FORMATTING
                + "/pgs ToggleSeek";
    }


    private void sendSuccessMessage(ICommandSender commandSender) {
        String successMessage = getSuccessMessage();
        commandSender.addChatMessage(new ChatComponentText(successMessage));
    }

    private String getSuccessMessage() {
        boolean newSeekingStatus = PGS_MANAGER.isSeekingEnabled();
        return StringUtils.BODY_FORMATTING + "Seeking has been "
                + StringUtils.getEnabledDisabledString(newSeekingStatus).toUpperCase()
                + StringUtils.BODY_FORMATTING + "!";
    }

    private void toggleSeeking() {
        PGS_MANAGER.setSeekingEnabled(!PGS_MANAGER.isSeekingEnabled());
    }
}
