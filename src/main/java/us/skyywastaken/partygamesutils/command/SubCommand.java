package us.skyywastaken.partygamesutils.command;

import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;

import java.util.List;

public interface SubCommand {
    void onCommand(ICommandSender commandSender, String[] args);

    List<String> getTabCompletions(ICommandSender sender, String[] args, BlockPos blockPos);

    String getHelpInformation();
}
