package us.skyywastaken.partygamesutils.feature.tiecalculator.command;

import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import us.skyywastaken.partygamesutils.command.MasterCommand;
import us.skyywastaken.partygamesutils.command.PGU.subcommands.PGUDisplayStarsCommand;
import us.skyywastaken.partygamesutils.feature.startracker.StarTracker;
import us.skyywastaken.partygamesutils.util.StringUtils;

import java.util.Collections;
import java.util.List;

public class PGTCommand extends MasterCommand implements ICommand {
    public PGTCommand(StarTracker passedStarTracker) {
        registerSubCommands();
    }

    @Override
    public String getCommandName() {
        return "pgu";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/pgu <args>";
    }

    @Override
    public List<String> getCommandAliases() {
        return Collections.singletonList("pgu");
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        if (super.userIsRequestingSubCommandHelp(args)) {
            sender.addChatMessage(getHelpInfo(args));
        } else if (args.length >= 1 && subCommandExists(args[0])) {
            executeCommand(args[0], sender, args);
        } else {
            executeCommand("help", sender, args);
        }
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return true;
    }

    @Override
    public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
        return getSubCommandTabCompletions(sender, args, pos);
    }

    @Override
    public boolean isUsernameIndex(String[] args, int index) {
        return false;
    }

    @Override
    public int compareTo(ICommand o) {
        return 0;
    }

    private void registerSubCommands() {

    }

    private IChatComponent getHelpInfo(String[] args) {
        String commandHelpText = "/pgu " + args[1] + " help";
        int dashCount = (int) Math.ceil(((40 - commandHelpText.length()) / (double) 2));
        String dashPadding = StringUtils.ACCENT_FORMATTING
                + String.format("%-" + dashCount + "s", "").replaceAll(" ", "-");
        IChatComponent returnComponent = new ChatComponentText(dashPadding
                + StringUtils.INFORMATION_FORMATTING + commandHelpText + dashPadding + "\n");
        returnComponent.appendSibling(new ChatComponentText(super.getSubCommandHelpMessage(args)));
        return returnComponent;
    }
}
