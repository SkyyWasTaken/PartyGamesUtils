package us.skyywastaken.partygamesutils.command;

import net.minecraft.command.ICommandSender;

public interface SubCommand {
    void onCommand(ICommandSender commandSender, String[] args);

}
