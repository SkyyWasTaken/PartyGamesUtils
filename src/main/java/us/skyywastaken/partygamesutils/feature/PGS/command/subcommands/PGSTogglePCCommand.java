package us.skyywastaken.partygamesutils.feature.PGS.command.subcommands;

import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import us.skyywastaken.partygamesutils.command.SubCommand;
import us.skyywastaken.partygamesutils.feature.PGS.settings.PartyCommandSettings;
import us.skyywastaken.partygamesutils.feature.PGS.settings.SeekSettings;
import us.skyywastaken.partygamesutils.feature.PGS.misc.SettingsMenuManager;
import us.skyywastaken.partygamesutils.util.StringUtils;

import java.util.List;

public class PGSTogglePCCommand implements SubCommand {
    private final PartyCommandSettings PARTY_COMMAND_SETTINGS;
    private final SettingsMenuManager SETTINGS_MENU_MANAGER;

    public PGSTogglePCCommand(PartyCommandSettings passedPartyCommandManager, SettingsMenuManager passedSettingsMenuManager) {
        this.PARTY_COMMAND_SETTINGS = passedPartyCommandManager;
        this.SETTINGS_MENU_MANAGER = passedSettingsMenuManager;
    }

    @Override
    public void onCommand(ICommandSender commandSender, String[] args) {
        togglePartyCommands();
        if(args.length >= 1 && args[0].equals("--displaysettings")) {
            this.SETTINGS_MENU_MANAGER.displaySettingsMenu();
        } else {
            sendSuccessMessage(commandSender);
        }
    }

    private void togglePartyCommands() {
        PARTY_COMMAND_SETTINGS.setPartyCommandsEnabled(!PARTY_COMMAND_SETTINGS.getPartyCommandsEnabled());
    }

    private void sendSuccessMessage(ICommandSender commandSender) {
        String successMessage = getSuccessMessage();
        commandSender.addChatMessage(new ChatComponentText(successMessage));
    }

    private String getSuccessMessage() {
        boolean partyCommandStatus = PARTY_COMMAND_SETTINGS.getPartyCommandsEnabled();
        return StringUtils.BODY_FORMATTING + "Party commands are now "
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
