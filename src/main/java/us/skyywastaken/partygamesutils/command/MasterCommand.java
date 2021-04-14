package us.skyywastaken.partygamesutils.command;

import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;
import us.skyywastaken.partygamesutils.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
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
        if (subCommandToExecute == null) return;
        subCommandToExecute.onCommand(commandSender, passedArgs);
    }

    public boolean subCommandExists(String subCommandName) {
        return SUB_COMMAND_HASH_MAP.containsKey(subCommandName);
    }

    public HashMap<String, SubCommand> getSubCommands() {
        return new HashMap<>(SUB_COMMAND_HASH_MAP);
    }

    public List<String> getSubCommandTabCompletions(ICommandSender sender, String[] currentArgs, BlockPos blockPos) {
        List<String> returnList = null;
        if (currentArgs.length == 0) {
            returnList = new ArrayList<>(SUB_COMMAND_HASH_MAP.keySet());
        } else if (currentArgs.length == 1) {
            returnList = StringUtils.getPartialMatches(currentArgs[0], SUB_COMMAND_HASH_MAP.keySet());
        } else if (subCommandExists(currentArgs[0])) {
            returnList = SUB_COMMAND_HASH_MAP.get(currentArgs[0]).getTabCompletions(sender, currentArgs, blockPos);
        }
        if (returnList == null) return null;
        Collections.sort(returnList);
        return StringUtils.getPartialMatches(currentArgs[currentArgs.length - 1], returnList);
    }
}
