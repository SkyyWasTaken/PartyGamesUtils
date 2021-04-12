package us.skyywastaken.partygamesutils.command;

import net.minecraft.command.ICommandSender;
import us.skyywastaken.partygamesutils.command.SubCommand;

import java.util.HashMap;

public abstract class MasterCommand {
    private HashMap<String, SubCommand> subCommandHashMap;
    public MasterCommand() {
        subCommandHashMap = new HashMap<>();
    }

    public void registerSubCommand(String subCommandName, SubCommand subCommand) {
        subCommandHashMap.put(subCommandName, subCommand);
    }

    public void executeCommand(String subCommand, ICommandSender commandSender, String[] passedArgs) {
        SubCommand subCommandToExecute = subCommandHashMap.get(subCommand);
        if(subCommandToExecute == null) return;
        subCommandToExecute.onCommand(commandSender, passedArgs);
    }

    public boolean subCommandExists(String subCommandName) {
        return subCommandHashMap.containsKey(subCommandName);
    }
}
