package us.skyywastaken.partygamesutils.command.pgs.SubCommands;

import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import us.skyywastaken.partygamesutils.command.SubCommand;

import java.util.List;

public class PGSHelpCommand implements SubCommand {
    @Override
    public void onCommand(ICommandSender commandSender, String[] args) {
        String helpMessage = "----------------/pgs help----------------\n"
                + "/pgs add <game1>, <game2>, etc.: adds games to the seek list\n"
                + "/pgs start: starts game seeking\n"
                + "/pgs stop: stops game seeking\n"
                + "/pgs list: lists games\n"
                + "/pgs togglepartycommands: lets party members add/remove games in party chat. Prefix: \".\"\n"
                + "/pgs clear: Clears the seek list";
        commandSender.addChatMessage(new ChatComponentText(helpMessage));
    }

    @Override
    public List<String> getTabCompletions(ICommandSender sender, String[] args, BlockPos blockPos) {
        return null;
    }
}
