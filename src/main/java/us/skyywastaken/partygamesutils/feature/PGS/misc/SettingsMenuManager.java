package us.skyywastaken.partygamesutils.feature.PGS.misc;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiNewChat;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import us.skyywastaken.partygamesutils.feature.PGS.PGSManager;
import us.skyywastaken.partygamesutils.util.ChatUtils;
import us.skyywastaken.partygamesutils.util.StringUtils;

public class SettingsMenuManager {
    private static final int MESSAGE_ID = 3621;
    private final PGSManager PGS_MANAGER;

    public SettingsMenuManager(PGSManager pgsManager) {
        this.PGS_MANAGER = pgsManager;
    }

    public void displaySettingsMenu() {
        GuiNewChat chatGUI = Minecraft.getMinecraft().ingameGUI.getChatGUI();
        IChatComponent settingsMessage = getSettingsMessage();
        chatGUI.printChatMessageWithOptionalDeletion(settingsMessage, MESSAGE_ID);
    }

    private IChatComponent getSettingsMessage() {
        IChatComponent returnComponent = getSettingMessageHeader();
        returnComponent.appendSibling(getSettingsMenu());
        return returnComponent;
    }

    private IChatComponent getSettingMessageHeader() {
        return new ChatComponentText(EnumChatFormatting.AQUA + "---------" + EnumChatFormatting.YELLOW
                + "PGS Settings" + EnumChatFormatting.AQUA + "---------\n");
    }

    private IChatComponent getSettingsMenu() {
        IChatComponent newLineComponent = new ChatComponentText("\n");
        IChatComponent returnComponent = new ChatComponentText("");
        returnComponent.appendSibling(getSettingsLineOne()).appendSibling(newLineComponent);
        returnComponent.appendSibling(getSettingsLineTwo()).appendSibling(newLineComponent);
        returnComponent.appendSibling(getSettingsLineThree()).appendSibling(newLineComponent);
        returnComponent.appendSibling(getSettingsLineFour()).appendSibling(newLineComponent);

        return returnComponent;
    }

    private IChatComponent getSettingsLineOne() {
        // Click here to refresh. For more info, commit click.
        String refreshString = EnumChatFormatting.GOLD + "Click here to refresh. For more info on a setting, click the setting name.";
        String refreshTooltip = "Click me to reload the settings menu!";
        return ChatUtils.getCommandChatComponent(refreshString, "/pgs settings", refreshTooltip);
    }

    private IChatComponent getSettingsLineTwo() {
        // Seeking: (status)! | Blacklist: (status)!
        IChatComponent returnComponent = new ChatComponentText("");
        String seekHelpText = EnumChatFormatting.GREEN + "Seeking: ";
        String seekHelpCommand = "/pgs help ToggleSeek";
        String dNSTTooltip = "Click me to view information about toggling seeking!";
        returnComponent.appendSibling(ChatUtils.getCommandChatComponent(seekHelpText, seekHelpCommand, dNSTTooltip));

        String seekStatusString = StringUtils.getEnabledDisabledString(PGS_MANAGER.isSeekingEnabled()).toUpperCase()
                + EnumChatFormatting.GREEN + "!";
        String seekCommand = "/pgs ToggleSeek --displaysettings";
        String seekTooltip = "Click me to toggle seeking!";
        returnComponent.appendSibling(ChatUtils.getCommandChatComponent(seekStatusString, seekCommand, seekTooltip));
        returnComponent.appendSibling(new ChatComponentText(" | ").setChatStyle(new ChatStyle()));


        String blacklistText = EnumChatFormatting.GREEN + "Blacklist: ";
        String blacklistHelpCommand = "/pgs help ToggleBlacklist";
        String blacklistHelpTooltip = "Click me to view information about toggling the blacklist!";
        returnComponent.appendSibling(ChatUtils.getCommandChatComponent(blacklistText,
                blacklistHelpCommand, blacklistHelpTooltip));


        String blacklistString = StringUtils.getEnabledDisabledString(PGS_MANAGER.isBlacklistEnabled()).toUpperCase()
                + EnumChatFormatting.GREEN + "!";
        String blacklistTooltip = "Click me to toggle the blacklist!";
        returnComponent.appendSibling(ChatUtils.getCommandChatComponent(blacklistString,
                "/pgs ToggleBlacklist --displaysettings", blacklistTooltip));
        return returnComponent;
    }

    private IChatComponent getSettingsLineThree() {
        // Do Not Seek Threshold: 0 1 2 3 4 5 6 7 8
        IChatComponent returnComponent = new ChatComponentText("");
        String dNSTText = EnumChatFormatting.GREEN + "Do Not Seek Threshold: ";
        String dNSTCommand = "/pgs help DoNotSeekThreshold";
        String dNSTTooltip = "Click me to view information about the seek threshold!";
        returnComponent.appendSibling(ChatUtils.getCommandChatComponent(dNSTText, dNSTCommand, dNSTTooltip));

        int doNotSkipThreshold = PGS_MANAGER.getDoNotSeekThreshold();
        String baseTooltip = "Click me to set the do-not-seek threshold to ";
        for(int i = 0; i <= 8; i++) {
            String numberFormatting;
            if(i == doNotSkipThreshold) {
                numberFormatting = EnumChatFormatting.AQUA + "" + EnumChatFormatting.UNDERLINE;
                String currentText = numberFormatting + i + "";
                String currentTooltip = "This is the current threshold";
                returnComponent.appendSibling(ChatUtils.getHoverChatComponent(currentText, currentTooltip));
            } else {
                numberFormatting = EnumChatFormatting.YELLOW + "";
                String currentText = numberFormatting + i + "";
                String currentTooltip = baseTooltip + i;
                IChatComponent newComponent = ChatUtils.getCommandChatComponent(currentText,
                        "/pgs DoNotSeekThreshold " + i + " --displaysettings", currentTooltip);
                returnComponent.appendSibling(newComponent);
            }
            returnComponent.appendSibling(new ChatComponentText(" ").setChatStyle(new ChatStyle()));
        }
        return returnComponent;
    }

    private IChatComponent getSettingsLineFour() {
        // Seek list: x games, click to view!
        IChatComponent returnComponent = new ChatComponentText("");
        String listHelpText = EnumChatFormatting.GREEN + "Seek list: ";
        String listHelpCommand = "/pgs help list";
        String listHelpTooltip = "Click me to view information about listing sought games!";
        returnComponent.appendSibling(ChatUtils.getCommandChatComponent(listHelpText, listHelpCommand, listHelpTooltip));

        int gameStatus = PGS_MANAGER.getSeekListSize();
        String gameString = StringUtils.ACCENT_FORMATTING + gameStatus + StringUtils.INFORMATION_FORMATTING + " games";
        if(gameStatus == 0) {
            String noListGameString = gameString + ". Use '" + StringUtils.ACCENT_FORMATTING + "/pgs add"
                    + StringUtils.INFORMATION_FORMATTING + "' to add games to the seek list!";
            returnComponent.appendSibling(new ChatComponentText(noListGameString));
        } else {
            String gameStatusString = StringUtils.ACCENT_FORMATTING + gameStatus + StringUtils.INFORMATION_FORMATTING
                    + " games, click to view!";
            String gameStatusCommand = "/pgs list";
            String gameStatusTooltip = "Click me to display the game list!";
            returnComponent.appendSibling(ChatUtils.getCommandChatComponent(gameStatusString,
                    gameStatusCommand, gameStatusTooltip));
        }
        return returnComponent;
    }

    private IChatComponent getSettingsLineFive() {
        IChatComponent returnComponent = new ChatComponentText("");
        String pCHelpText = EnumChatFormatting.GREEN + "Seek list: ";
        String pCHelpCommand = "/pgs help list";
        String pCHelpTooltip = "Click me to view information about listing sought games!";
        returnComponent.appendSibling(ChatUtils.getCommandChatComponent(pCHelpText, pCHelpCommand, pCHelpTooltip));

        boolean partyCommandStatus = PGS_MANAGER.getPartyCommandsEnabled();
        String
    }
}
