package us.skyywastaken.partygamesutils.feature.gameseeking.command;

import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import us.skyywastaken.partygamesutils.command.MasterCommand;
import us.skyywastaken.partygamesutils.feature.gameseeking.command.subcommands.PGSAddCommand;
import us.skyywastaken.partygamesutils.feature.gameseeking.command.subcommands.PGSClearCommand;
import us.skyywastaken.partygamesutils.feature.gameseeking.command.subcommands.PGSDoNotSeekThresholdCommand;
import us.skyywastaken.partygamesutils.feature.gameseeking.command.subcommands.PGSHelpCommand;
import us.skyywastaken.partygamesutils.feature.gameseeking.command.subcommands.PGSListCommand;
import us.skyywastaken.partygamesutils.feature.gameseeking.command.subcommands.PGSPartyPermissionsCommand;
import us.skyywastaken.partygamesutils.feature.gameseeking.command.subcommands.PGSRemoveCommand;
import us.skyywastaken.partygamesutils.feature.gameseeking.command.subcommands.PGSSettingsCommand;
import us.skyywastaken.partygamesutils.feature.gameseeking.command.subcommands.PGSStartCommand;
import us.skyywastaken.partygamesutils.feature.gameseeking.command.subcommands.PGSStopCommand;
import us.skyywastaken.partygamesutils.feature.gameseeking.command.subcommands.PGSToggleBlacklistCommand;
import us.skyywastaken.partygamesutils.feature.gameseeking.command.subcommands.PGSTogglePCCommand;
import us.skyywastaken.partygamesutils.feature.gameseeking.command.subcommands.PGSToggleSeekCommand;
import us.skyywastaken.partygamesutils.feature.gameseeking.misc.ListMenuManager;
import us.skyywastaken.partygamesutils.feature.gameseeking.misc.SettingsMenuManager;
import us.skyywastaken.partygamesutils.feature.gameseeking.settings.PartyCommandSettings;
import us.skyywastaken.partygamesutils.feature.gameseeking.settings.SeekSettings;
import us.skyywastaken.partygamesutils.util.StringUtils;

import java.util.Collections;
import java.util.List;

public class PGSCommand extends MasterCommand implements ICommand {
    private final SeekSettings SEEK_SETTINGS;
    private final PartyCommandSettings PARTY_COMMAND_SETTINGS;
    private final SettingsMenuManager SETTINGS_MENU_MANAGER;
    private final ListMenuManager LIST_MENU_MANAGER;

    public PGSCommand(SeekSettings passedSeekSettings, PartyCommandSettings passedPartyCommandSettings) {
        this.SEEK_SETTINGS = passedSeekSettings;
        this.PARTY_COMMAND_SETTINGS = passedPartyCommandSettings;
        this.SETTINGS_MENU_MANAGER = new SettingsMenuManager(this.SEEK_SETTINGS, this.PARTY_COMMAND_SETTINGS);
        this.LIST_MENU_MANAGER = new ListMenuManager(this.SEEK_SETTINGS);
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
        if (super.userIsRequestingSubCommandHelp(args)) {
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
        super.registerSubCommand("PartyPermissions", new PGSPartyPermissionsCommand(PARTY_COMMAND_SETTINGS, SETTINGS_MENU_MANAGER));
        super.registerSubCommand("clear", new PGSClearCommand(SEEK_SETTINGS));
        super.registerSubCommand("add", new PGSAddCommand(SEEK_SETTINGS));
        super.registerSubCommand("remove", new PGSRemoveCommand(SEEK_SETTINGS, LIST_MENU_MANAGER));
        super.registerSubCommand("list", new PGSListCommand(LIST_MENU_MANAGER));
        super.registerSubCommand("TogglePartyCommands", new PGSTogglePCCommand(PARTY_COMMAND_SETTINGS
                , SETTINGS_MENU_MANAGER));
        super.registerSubCommand("ToggleSeek", new PGSToggleSeekCommand(SEEK_SETTINGS, SETTINGS_MENU_MANAGER));
        super.registerSubCommand("start", new PGSStartCommand(SEEK_SETTINGS));
        super.registerSubCommand("stop", new PGSStopCommand(SEEK_SETTINGS));
        super.registerSubCommand("DoNotSeekThreshold", new PGSDoNotSeekThresholdCommand(SEEK_SETTINGS, SETTINGS_MENU_MANAGER));
        super.registerSubCommand("ToggleBlacklist", new PGSToggleBlacklistCommand(SEEK_SETTINGS, SETTINGS_MENU_MANAGER));
        super.registerSubCommand("settings", new PGSSettingsCommand(SETTINGS_MENU_MANAGER));
    }

    private IChatComponent getHelpInfo(String[] args) {
        String commandHelpText = "/pgs " + args[1] + " help";
        int dashCount = (int) Math.ceil(((40 - commandHelpText.length()) / (double) 2));
        String dashPadding = StringUtils.ACCENT_FORMATTING
                + String.format("%-" + dashCount + "s", "").replaceAll(" ", "-");
        IChatComponent returnComponent = new ChatComponentText(dashPadding
                + StringUtils.INFORMATION_FORMATTING + commandHelpText + dashPadding + "\n");
        returnComponent.appendSibling(new ChatComponentText(super.getSubCommandHelpMessage(args)));
        return returnComponent;
    }
}
