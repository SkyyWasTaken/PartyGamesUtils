package us.skyywastaken.partygamesutils.command;

import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;
import us.skyywastaken.partygamesutils.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public abstract class MasterCommand {
    private final HashMap<String, SubCommand> SUB_COMMAND_HASH_MAP;
    public MasterCommand() {
        SUB_COMMAND_HASH_MAP = new HashMap<>();
    }

    public void registerSubCommand(String subCommandName, SubCommand subCommand) {
        SUB_COMMAND_HASH_MAP.put(subCommandName, subCommand);
    }

    public void executeCommand(String subCommand, ICommandSender commandSender, String[] passedArgs) {
        SubCommand subCommandToExecute = SUB_COMMAND_HASH_MAP.get(subCommand);
        if(subCommandToExecute == null) return;
        subCommandToExecute.onCommand(commandSender, passedArgs);
    }

    public boolean subCommandExists(String subCommandName) {
        return SUB_COMMAND_HASH_MAP.containsKey(subCommandName);
    }

    public List<String> getSubCommandTabCompletions(ICommandSender sender, String[] currentArgs, BlockPos blockPos) {
        if(currentArgs.length == 0) {
            return new ArrayList<>(SUB_COMMAND_HASH_MAP.keySet());
        } else if(currentArgs.length == 1) {
            return StringUtils.getPartialMatches(currentArgs[0], SUB_COMMAND_HASH_MAP.keySet());
        } else if (subCommandExists(currentArgs[0])) {
            return SUB_COMMAND_HASH_MAP.get(currentArgs[0]).getTabCompletions(sender, currentArgs, blockPos);
        }
        return null;
    }
}
