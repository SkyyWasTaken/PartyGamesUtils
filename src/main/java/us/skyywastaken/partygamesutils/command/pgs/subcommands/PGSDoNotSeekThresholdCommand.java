package us.skyywastaken.partygamesutils.command.pgs.subcommands;

import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import us.skyywastaken.partygamesutils.command.SubCommand;
import us.skyywastaken.partygamesutils.command.pgs.PGSManager;

import java.util.List;

public class PGSDoNotSeekThresholdCommand implements SubCommand {
    private final PGSManager PGS_MANAGER;

    public PGSDoNotSeekThresholdCommand(PGSManager passedPGSManager) {
        this.PGS_MANAGER = passedPGSManager;
    }


    @Override
    public void onCommand(ICommandSender commandSender, String[] args) {
        if (args.length == 1) {
            sendHelpMessage(commandSender);
        } else {
            int newValue = attemptToParseInt(commandSender, args[1]);
            PGS_MANAGER.setDoNotSeekThreshold(newValue);
            String successMessage = getSuccessMessage(newValue);
            commandSender.addChatMessage(new ChatComponentText(successMessage));
        }
    }

    @Override
    public List<String> getTabCompletions(ICommandSender sender, String[] args, BlockPos blockPos) {
        return null;
    }

    private int attemptToParseInt(ICommandSender commandSender, String stringToParse) {
        try {
            return Integer.parseInt(stringToParse);
        } catch (NumberFormatException e) {
            sendNumberFormatExceptionMessage(commandSender);
            return 0;
        }
    }

    private void sendNumberFormatExceptionMessage(ICommandSender commandSender) {
        String errorMessage = getNumberFormatExceptionMessage();
        commandSender.addChatMessage(new ChatComponentText(errorMessage));
    }

    private String getNumberFormatExceptionMessage() {
        return EnumChatFormatting.RED + "Please enter a valid number!";
    }

    private String getSuccessMessage(int newValue) {
        if (newValue < 0) {
            return EnumChatFormatting.GOLD + "You can't use a value less than 0! Defaulting to 0...";
        } else if (newValue > 8) {
            return EnumChatFormatting.GOLD + "You can't use a value over 8! Defaulting to 8...";
        } else if (newValue == 0) {
            return EnumChatFormatting.GREEN + "The no-seek threshold has been " + EnumChatFormatting.YELLOW + "disabled!";
        } else {
            return EnumChatFormatting.GREEN + "The no-seek threshold has been set to " + EnumChatFormatting.YELLOW
                    + "Game " + newValue + EnumChatFormatting.GREEN + "!";
        }
    }

    private void sendHelpMessage(ICommandSender commandSender) {
        String helpMessage = getHelpMessage();
        commandSender.addChatMessage(new ChatComponentText(helpMessage));
    }

    private String getHelpMessage() {
        return EnumChatFormatting.AQUA + "----------" + EnumChatFormatting.YELLOW
                + "/pgs NoSkipThreshold" + EnumChatFormatting.AQUA + "----------\n"
                + EnumChatFormatting.GOLD + "To modify the no-seek threshold, type /pgs NoSkipThreshold newNumber\n"
                + EnumChatFormatting.GRAY + "Examples: 0 = always fine to pull me out, 4 = don't pull me out on game 4 and over, " +
                "5 = don't pull me out on game 5 and over, etc.\n"
                + EnumChatFormatting.GREEN + "Current no-seek threshold: " + EnumChatFormatting.YELLOW + "Game "
                + PGS_MANAGER.getDoNotSeekThreshold();
    }
}
