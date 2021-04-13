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
        String helpMessage;
        if(args.length <= 1 || !args[1].equals("2")){
            helpMessage = getHelpHeader(1)
                    + EnumChatFormatting.RED + "WARNING: USING PGS MAY RESULT IN A PUNISHMENT\n"
                    + EnumChatFormatting.GOLD + "It is possible that this feature is considered a disallowed modification "
                    + "under Hypixel's rules. Use at your own risk!\n"
                    + EnumChatFormatting.GREEN + "/pgs add <game1>, <game2>, etc.: " + EnumChatFormatting.LIGHT_PURPLE
                    + "Adds games to the seek list\n"
                    + EnumChatFormatting.GREEN + "/pgs start: " + EnumChatFormatting.LIGHT_PURPLE + "Starts game seeking\n"
                    + EnumChatFormatting.GREEN + "/pgs stop: " + EnumChatFormatting.LIGHT_PURPLE + "Stops game seeking\n"
                    + EnumChatFormatting.GREEN + "/pgs list: " + EnumChatFormatting.LIGHT_PURPLE + "Lists games\n"
                    + EnumChatFormatting.GREEN + "/pgs togglepartycommands: " + EnumChatFormatting.LIGHT_PURPLE
                    + "Lets party members add/remove games in party chat. Prefix: \".\"";
        } else {
            helpMessage = getHelpHeader(2)
                    + EnumChatFormatting.GREEN + "/pgs clear: " + EnumChatFormatting.LIGHT_PURPLE + "Clears the seek list\n"
                    + EnumChatFormatting.GREEN + "/pgs set: " + EnumChatFormatting.LIGHT_PURPLE
                    + "PGS-related settings management\n"
                    + EnumChatFormatting.GREEN + "/pgs PartyPermissions: " + EnumChatFormatting.LIGHT_PURPLE
                    + "Lets you toggle party permissions on or off";
        }
        commandSender.addChatMessage(new ChatComponentText(helpMessage));
    }

    @Override
    public List<String> getTabCompletions(ICommandSender sender, String[] args, BlockPos blockPos) {
        return null;
    }

    private String getHelpHeader(int currentPage) {
        return EnumChatFormatting.AQUA + "-------------" + EnumChatFormatting.YELLOW
                + "/pgs help (" + currentPage + "/2)" + EnumChatFormatting.AQUA + "-------------\n";
    }
}
