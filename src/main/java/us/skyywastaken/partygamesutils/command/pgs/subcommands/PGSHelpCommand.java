package us.skyywastaken.partygamesutils.command.pgs.subcommands;

import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import us.skyywastaken.partygamesutils.command.SubCommand;

import java.util.HashMap;
import java.util.List;

public class PGSHelpCommand implements SubCommand {
    private final HashMap<String, String> HELP_PAGES;

    public PGSHelpCommand() {
        this.HELP_PAGES = new HashMap<>();
        initializeHelpPages();
    }

    @Override
    public void onCommand(ICommandSender commandSender, String[] args) {
        if(args.length <= 1) {
            sendHelpMessage(commandSender, "1");
        } else {
            sendHelpMessage(commandSender, args[1]);
        }
    }

    @Override
    public List<String> getTabCompletions(ICommandSender sender, String[] args, BlockPos blockPos) {
        return null;
    }

    private String getHelpHeader(int currentPage, int maxPages) {
        return EnumChatFormatting.AQUA + "-------------" + EnumChatFormatting.YELLOW
                + "/pgs help (" + currentPage + "/" + maxPages + ")" + EnumChatFormatting.AQUA + "-------------\n";
    }

    private void sendHelpMessage(ICommandSender commandSender, String pageNumberString) {
        String helpMessage = getHelpPage(pageNumberString);
        commandSender.addChatMessage(new ChatComponentText(helpMessage));
    }

    private String getHelpPage(String requestedPage) {
        if(!HELP_PAGES.containsKey(requestedPage)) {
            return HELP_PAGES.get("1");
        } else {
            return HELP_PAGES.get(requestedPage);
        }
    }

    private void initializeHelpPages() {
        EnumChatFormatting commandColor = EnumChatFormatting.GREEN;
        EnumChatFormatting descriptionColor = EnumChatFormatting.GRAY;
        String helpPageOne = getHelpHeader(1, 2)
                + EnumChatFormatting.RED + "WARNING: USING PGS MAY RESULT IN A PUNISHMENT\n"
                + EnumChatFormatting.GOLD + "It is possible that this feature is considered a disallowed modification "
                + "under Hypixel's rules. Use at your own risk!\n"
                + commandColor + "/pgs add <game1>, <game2>, etc.: " + descriptionColor
                + "Adds games to the seek list\n"
                + commandColor + "/pgs start: " + descriptionColor + "Starts game seeking\n"
                + commandColor + "/pgs stop: " + descriptionColor + "Stops game seeking\n"
                + commandColor + "/pgs list: " + descriptionColor + "Lists games\n"
                + commandColor + "/pgs togglepartycommands: " + descriptionColor
                + "Lets party members add/remove games in party chat. Prefix: \".\"";
        this.HELP_PAGES.put("1", helpPageOne);

        String helpPageTwo = getHelpHeader(2, 2)
                + commandColor + "/pgs clear: " + descriptionColor + "Clears the seek list\n"
                + commandColor + "/pgs set: " + descriptionColor + "PGS-related settings management\n"
                + commandColor + "/pgs PartyPermissions: " + descriptionColor
                + "Lets you toggle party permissions on or off";
        this.HELP_PAGES.put("2", helpPageTwo);
    }
}
