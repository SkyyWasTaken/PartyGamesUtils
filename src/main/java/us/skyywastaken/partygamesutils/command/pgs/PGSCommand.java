package us.skyywastaken.partygamesutils.command.pgs;

import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;
import us.skyywastaken.partygamesutils.command.MasterCommand;
import us.skyywastaken.partygamesutils.command.pgs.subcommands.PGSAddCommand;
import us.skyywastaken.partygamesutils.command.pgs.subcommands.PGSClearCommand;
import us.skyywastaken.partygamesutils.command.pgs.subcommands.PGSDoNotSeekThresholdCommand;
import us.skyywastaken.partygamesutils.command.pgs.subcommands.PGSHelpCommand;
import us.skyywastaken.partygamesutils.command.pgs.subcommands.PGSListCommand;
import us.skyywastaken.partygamesutils.command.pgs.subcommands.PGSPartyPermissionsCommand;
import us.skyywastaken.partygamesutils.command.pgs.subcommands.PGSRemoveCommand;
import us.skyywastaken.partygamesutils.command.pgs.subcommands.PGSStartCommand;
import us.skyywastaken.partygamesutils.command.pgs.subcommands.PGSStopCommand;
import us.skyywastaken.partygamesutils.command.pgs.subcommands.PGSTogglePCCommand;

import java.util.Collections;
import java.util.List;

public class PGSCommand extends MasterCommand implements ICommand {
    private final PGSManager PGS_MANAGER;

    public PGSCommand(PGSManager passedPGSManager) {
        this.PGS_MANAGER = passedPGSManager;
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
        if (args.length >= 1 && subCommandExists(args[0])) {
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
        super.registerSubCommand("togglepartycommands", new PGSTogglePCCommand(PGS_MANAGER));
        super.registerSubCommand("start", new PGSStartCommand(PGS_MANAGER));
        super.registerSubCommand("stop", new PGSStopCommand(PGS_MANAGER));
        super.registerSubCommand("DoNotSeekThreshold", new PGSDoNotSeekThresholdCommand(PGS_MANAGER));
    }
}
