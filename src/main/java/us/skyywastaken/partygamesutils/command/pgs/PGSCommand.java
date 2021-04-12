package us.skyywastaken.partygamesutils.command.pgs;

import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;
import us.skyywastaken.partygamesutils.command.MasterCommand;
import us.skyywastaken.partygamesutils.command.pgs.subcommands.PGSAddCommand;
import us.skyywastaken.partygamesutils.command.pgs.subcommands.PGSClearCommand;
import us.skyywastaken.partygamesutils.command.pgs.subcommands.PGSHelpCommand;
import us.skyywastaken.partygamesutils.command.pgs.subcommands.PGSListCommand;
import us.skyywastaken.partygamesutils.command.pgs.subcommands.PGSRemoveCommand;
import us.skyywastaken.partygamesutils.command.pgs.subcommands.set.PGSSetCommand;
import us.skyywastaken.partygamesutils.command.pgs.subcommands.PGSStartCommand;
import us.skyywastaken.partygamesutils.command.pgs.subcommands.PGSStopCommand;
import us.skyywastaken.partygamesutils.command.pgs.subcommands.PGSTogglePCCommand;
import us.skyywastaken.partygamesutils.command.pgs.subcommands.set.PGSSettingsManager;
import us.skyywastaken.partygamesutils.misc.SeekManager;

import java.util.Collections;
import java.util.List;

public class PGSCommand extends MasterCommand implements ICommand {
    private final SeekManager SEEK_MANAGER;
    private final PGSSettingsManager SETTINGS_MANAGER;
    private final PGSPartyCommandManager PARTY_COMMAND_MANAGER;

    public PGSCommand(SeekManager passedSeekManager, PGSPartyCommandManager passedPartyCommandManager) {
        this.SEEK_MANAGER = passedSeekManager;
        this.PARTY_COMMAND_MANAGER = passedPartyCommandManager;
        this.SETTINGS_MANAGER = new PGSSettingsManager(SEEK_MANAGER, PARTY_COMMAND_MANAGER);
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
    public void processCommand(ICommandSender sender, String[] args){
        if(args.length >= 1 && subCommandExists(args[0])) {
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
        registerSubCommand("help", new PGSHelpCommand());
        registerSubCommand("set", new PGSSetCommand(SETTINGS_MANAGER));
        registerSubCommand("clear", new PGSClearCommand(SEEK_MANAGER));
        registerSubCommand("add", new PGSAddCommand(SEEK_MANAGER));
        registerSubCommand("remove", new PGSRemoveCommand(SEEK_MANAGER));
        registerSubCommand("list", new PGSListCommand(SEEK_MANAGER));
        registerSubCommand("togglepartycommands", new PGSTogglePCCommand(SEEK_MANAGER));
        registerSubCommand("start", new PGSStartCommand(SEEK_MANAGER));
        registerSubCommand("stop", new PGSStopCommand(SEEK_MANAGER));
    }
}
