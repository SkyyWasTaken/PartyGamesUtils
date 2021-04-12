package us.skyywastaken.partygamesutils.command.pgs;

import com.sun.istack.internal.NotNull;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;
import us.skyywastaken.partygamesutils.command.MasterCommand;
import us.skyywastaken.partygamesutils.command.pgs.SubCommands.PGSAddCommand;
import us.skyywastaken.partygamesutils.command.pgs.SubCommands.PGSClearCommand;
import us.skyywastaken.partygamesutils.command.pgs.SubCommands.PGSHelpCommand;
import us.skyywastaken.partygamesutils.command.pgs.SubCommands.PGSListCommand;
import us.skyywastaken.partygamesutils.command.pgs.SubCommands.PGSRemoveCommand;
import us.skyywastaken.partygamesutils.command.pgs.SubCommands.PGSSetCommand;
import us.skyywastaken.partygamesutils.command.pgs.SubCommands.PGSStartCommand;
import us.skyywastaken.partygamesutils.command.pgs.SubCommands.PGSStopCommand;
import us.skyywastaken.partygamesutils.command.pgs.SubCommands.PGSTogglePCCommand;
import us.skyywastaken.partygamesutils.misc.SeekManager;

import java.util.Collections;
import java.util.List;

public class PGSCommand extends MasterCommand implements ICommand {
    private final SeekManager seekManager;

    public PGSCommand(SeekManager passedSeekManager) {
        seekManager = passedSeekManager;
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
        registerSubCommand("set", new PGSSetCommand());
        registerSubCommand("clear", new PGSClearCommand(seekManager));
        registerSubCommand("add", new PGSAddCommand(seekManager));
        registerSubCommand("remove", new PGSRemoveCommand(seekManager));
        registerSubCommand("list", new PGSListCommand(seekManager));
        registerSubCommand("togglepartycommands", new PGSTogglePCCommand(seekManager));
        registerSubCommand("start", new PGSStartCommand(seekManager));
        registerSubCommand("stop", new PGSStopCommand(seekManager));
    }
}
