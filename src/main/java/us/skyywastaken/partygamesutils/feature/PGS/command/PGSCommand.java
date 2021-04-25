package us.skyywastaken.partygamesutils.feature.PGS.command;

import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import us.skyywastaken.partygamesutils.command.MasterCommand;
import us.skyywastaken.partygamesutils.feature.PGS.command.subcommands.PGSAddCommand;
import us.skyywastaken.partygamesutils.feature.PGS.command.subcommands.PGSClearCommand;
import us.skyywastaken.partygamesutils.feature.PGS.command.subcommands.PGSDoNotSeekThresholdCommand;
import us.skyywastaken.partygamesutils.feature.PGS.command.subcommands.PGSHelpCommand;
import us.skyywastaken.partygamesutils.feature.PGS.command.subcommands.PGSListCommand;
import us.skyywastaken.partygamesutils.feature.PGS.command.subcommands.PGSPartyPermissionsCommand;
import us.skyywastaken.partygamesutils.feature.PGS.command.subcommands.PGSRemoveCommand;
import us.skyywastaken.partygamesutils.feature.PGS.command.subcommands.PGSSettingsCommand;
import us.skyywastaken.partygamesutils.feature.PGS.command.subcommands.PGSStartCommand;
import us.skyywastaken.partygamesutils.feature.PGS.command.subcommands.PGSStopCommand;
import us.skyywastaken.partygamesutils.feature.PGS.command.subcommands.PGSToggleSeekCommand;
import us.skyywastaken.partygamesutils.feature.PGS.command.subcommands.PGSToggleBlacklistCommand;
import us.skyywastaken.partygamesutils.feature.PGS.command.subcommands.PGSTogglePCCommand;
import us.skyywastaken.partygamesutils.feature.PGS.PGSManager;
import us.skyywastaken.partygamesutils.feature.PGS.misc.SettingsMenuManager;
import us.skyywastaken.partygamesutils.util.StringUtils;

import java.util.Collections;
import java.util.List;

public class PGSCommand extends MasterCommand implements ICommand {
    private final PGSManager PGS_MANAGER;
    private final SettingsMenuManager SETTINGS_MENU_MANAGER;

    public PGSCommand(PGSManager passedPGSManager) {
        this.PGS_MANAGER = passedPGSManager;
        this.SETTINGS_MENU_MANAGER = new SettingsMenuManager(this.PGS_MANAGER);
        registerSubCommands();
    }

    @Override
    public String getCommandName() {
        return "pgs";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/pgs <args>";
    }

    @Override
    public List<String> getCommandAliases() {
        return Collections.singletonList("pgs");
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        if(super.userIsRequestingSubCommandHelp(args)) {
            sender.addChatMessage(getHelpInfo(args));
        } else if (args.length >= 1 && subCommandExists(args[0])) {
            executeCommand(args[0], sender, args);
        } else {
            executeCommand("help", sender, args);
        }
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return true;
    }

    @Override
    public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
        return getSubCommandTabCompletions(sender, args, pos);
    }

    @Override
    public boolean isUsernameIndex(String[] args, int index) {
        return false;
    }

    @Override
    public int compareTo(ICommand o) {
        return 0;
    }

    private void registerSubCommands() {
        super.registerSubCommand("help", new PGSHelpCommand());
        super.registerSubCommand("PartyPermissions", new PGSPartyPermissionsCommand(PGS_MANAGER));
        super.registerSubCommand("clear", new PGSClearCommand(PGS_MANAGER));
        super.registerSubCommand("add", new PGSAddCommand(PGS_MANAGER));
        super.registerSubCommand("remove", new PGSRemoveCommand(PGS_MANAGER));
        super.registerSubCommand("list", new PGSListCommand(PGS_MANAGER));
        super.registerSubCommand("TogglePartyCommands", new PGSTogglePCCommand(PGS_MANAGER));
        super.registerSubCommand("ToggleSeek", new PGSToggleSeekCommand(PGS_MANAGER));
        super.registerSubCommand("start", new PGSStartCommand(PGS_MANAGER));
        super.registerSubCommand("stop", new PGSStopCommand(PGS_MANAGER));
        super.registerSubCommand("DoNotSeekThreshold", new PGSDoNotSeekThresholdCommand(PGS_MANAGER));
        super.registerSubCommand("ToggleBlacklist", new PGSToggleBlacklistCommand(PGS_MANAGER));
        super.registerSubCommand("settings", new PGSSettingsCommand(SETTINGS_MENU_MANAGER));
    }

    private IChatComponent getHelpInfo(String[] args) {
        String commandHelpText = "/pgs " + args[1] + " help";
        int dashCount = (int) Math.ceil(((40 - commandHelpText.length())/(double)2));
        String dashPadding = StringUtils.ACCENT_FORMATTING
                + String.format("%-" + dashCount + "s", "").replaceAll(" ", "-");
        IChatComponent returnComponent = new ChatComponentText(dashPadding
                + StringUtils.INFORMATION_FORMATTING + commandHelpText + dashPadding + "\n");
        returnComponent.appendSibling(new ChatComponentText(super.getSubCommandHelpMessage(args)));
        return returnComponent;
    }
}
