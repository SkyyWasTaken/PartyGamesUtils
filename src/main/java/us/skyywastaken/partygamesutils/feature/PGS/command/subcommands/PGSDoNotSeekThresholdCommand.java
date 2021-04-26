package us.skyywastaken.partygamesutils.feature.PGS.command.subcommands;

import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import us.skyywastaken.partygamesutils.command.SubCommand;
import us.skyywastaken.partygamesutils.feature.PGS.settings.SeekSettings;
import us.skyywastaken.partygamesutils.feature.PGS.misc.SettingsMenuManager;
import us.skyywastaken.partygamesutils.util.StringUtils;

import java.util.List;

public class PGSDoNotSeekThresholdCommand implements SubCommand {
    private final SeekSettings SEEK_SETTINGS;
    private final SettingsMenuManager SETTINGS_MENU_MANAGER;

    public PGSDoNotSeekThresholdCommand(SeekSettings passedSeekSettings, SettingsMenuManager passedSettingsMenuManager) {
        this.SEEK_SETTINGS = passedSeekSettings;
        this.SETTINGS_MENU_MANAGER = passedSettingsMenuManager;
    }


    @Override
    public void onCommand(ICommandSender commandSender, String[] args) {
        if (args.length == 0) {
            sendHelpMessage(commandSender);
        } else {
            System.out.println(String.join(" ", args));
            int newValue = attemptToParseInt(commandSender, args[0]);
            SEEK_SETTINGS.setDoNotSeekThreshold(newValue);
            if(args.length >= 2 && args[1].equals("--displaysettings")) {
                SETTINGS_MENU_MANAGER.displaySettingsMenu();
            } else {
                String successMessage = getSuccessMessage(newValue);
                commandSender.addChatMessage(new ChatComponentText(successMessage));
            }
        }
    }

    @Override
    public List<String> getTabCompletions(ICommandSender sender, String[] args, BlockPos blockPos) {
        return null;
    }

    @Override
    public String getHelpInformation() {
        return StringUtils.BODY_FORMATTING + "This command is used to set the seek threshold. "
                + "The seek threshold can also be set in /pgs settings.\n"
                + StringUtils.INFORMATION_FORMATTING + "Usage: " + StringUtils.COMMAND_USAGE_FORMATTING
                + "/pgs DoNotSeekThreshold <newValue>";
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
        return StringUtils.WARNING_FORMATTING + "Please enter a valid number!";
    }

    private String getSuccessMessage(int newValue) {
        if (newValue < 0) {
            return EnumChatFormatting.GOLD + "You can't use a value less than 0! Defaulting to 0...";
        } else if (newValue > 8) {
            return EnumChatFormatting.GOLD + "You can't use a value over 8! Defaulting to 8...";
        } else if (newValue == 0) {
            return StringUtils.BODY_FORMATTING + "The no-seek threshold has been " + EnumChatFormatting.YELLOW + "disabled!";
        } else {
            return StringUtils.BODY_FORMATTING + "The no-seek threshold has been set to " + EnumChatFormatting.YELLOW
                    + "Game " + newValue + StringUtils.BODY_FORMATTING + "!";
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
                + StringUtils.BODY_FORMATTING + "Current no-seek threshold: " + EnumChatFormatting.YELLOW + "Game "
                + SEEK_SETTINGS.getDoNotSeekThreshold();
    }
}
