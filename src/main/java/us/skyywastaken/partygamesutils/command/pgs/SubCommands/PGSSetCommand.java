package us.skyywastaken.partygamesutils.command.pgs.SubCommands;

import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import us.skyywastaken.partygamesutils.command.SubCommand;

import java.util.Arrays;
import java.util.List;

public class PGSSetCommand implements SubCommand {
    @Override
    public void onCommand(ICommandSender commandSender, String[] args) {
        String tooFewArgsFeedback = EnumChatFormatting.RED + "Not enough arguments!\n"
                + EnumChatFormatting.WHITE + "To see correct usage, type /pgs set";
        if(args.length < 3) {
            commandSender.addChatMessage(new ChatComponentText(tooFewArgsFeedback));
            return;
        }
        String[] trimmedArgs = Arrays.copyOfRange(args, 1, args.length);
    }

    @Override
    public List<String> getTabCompletions(ICommandSender sender, String[] args, BlockPos blockPos) {
        return null;
    }
}
