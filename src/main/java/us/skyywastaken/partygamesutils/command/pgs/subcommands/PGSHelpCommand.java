package us.skyywastaken.partygamesutils.command.pgs.subcommands;

import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import us.skyywastaken.partygamesutils.command.SubCommand;

import java.util.List;

public class PGSHelpCommand implements SubCommand {
    @Override
    public void onCommand(ICommandSender commandSender, String[] args) {
        String helpMessage = EnumChatFormatting.AQUA + "----------------" + EnumChatFormatting.YELLOW
                    + "/pgs help" + EnumChatFormatting.AQUA + "----------------\n"
                + EnumChatFormatting.GREEN + "/pgs add <game1>, <game2>, etc.: " + EnumChatFormatting.LIGHT_PURPLE
                    + "adds games to the seek list\n"
                + EnumChatFormatting.GREEN + "/pgs start: " + EnumChatFormatting.LIGHT_PURPLE + "starts game seeking\n"
                + EnumChatFormatting.GREEN + "/pgs stop: " + EnumChatFormatting.LIGHT_PURPLE + "stops game seeking\n"
                + EnumChatFormatting.GREEN + "/pgs list: " + EnumChatFormatting.LIGHT_PURPLE + "lists games\n"
                + EnumChatFormatting.GREEN + "/pgs togglepartycommands: " + EnumChatFormatting.LIGHT_PURPLE + "lets party members add/remove games in party chat. Prefix: \".\"\n"
                + EnumChatFormatting.GREEN + "/pgs clear: " + EnumChatFormatting.LIGHT_PURPLE + "Clears the seek list\n"
                + EnumChatFormatting.GREEN + "/pgs set: " + EnumChatFormatting.LIGHT_PURPLE + "pgs-related settings management";
        commandSender.addChatMessage(new ChatComponentText(helpMessage));
    }

    @Override
    public List<String> getTabCompletions(ICommandSender sender, String[] args, BlockPos blockPos) {
        return null;
    }
}
