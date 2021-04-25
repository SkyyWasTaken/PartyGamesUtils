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

    public void sendSettingsMenu() {

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

        return returnComponent;
    }

    private IChatComponent getSettingsLineOne() {
        String refreshString = EnumChatFormatting.GOLD + "Click here to refresh. For more info on a setting, click the setting name.";
        String refreshTooltip = "Click me to reload the settings menu!";
        return ChatUtils.getCommandChatComponent(refreshString, "/pgs settings", refreshTooltip);
    }

    private IChatComponent getSettingsLineTwo() {
        // Seeking: (status)! | Blacklist: (status)!
        String seekString = EnumChatFormatting.GREEN + "Seeking: "
                + StringUtils.getEnabledDisabledString(PGS_MANAGER.isSeekingEnabled()).toUpperCase()
                + EnumChatFormatting.GREEN + "!";
        String seekTooltip = "Click me to toggle seeking!";
        IChatComponent returnComponent = ChatUtils.getCommandChatComponent(seekString, "/pgs settings ToggleSeek", seekTooltip);

        returnComponent.appendSibling(new ChatComponentText(" | ").setChatStyle(new ChatStyle()));

        String blacklistString = EnumChatFormatting.GREEN + "Blacklist: "
                + StringUtils.getEnabledDisabledString(PGS_MANAGER.isBlacklistEnabled()).toUpperCase()
                + EnumChatFormatting.GREEN + "!";
        String blacklistTooltip = "Click me to toggle the blacklist!";
        returnComponent.appendSibling(ChatUtils.getCommandChatComponent(blacklistString,
                "/pgs ToggleBlacklist", blacklistTooltip));
        return returnComponent;
    }

    private IChatComponent getSettingsLineThree() {
        IChatComponent returnComponent = new ChatComponentText("Do Not Seek Threshold: ").setChatStyle(new ChatStyle());
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
                IChatComponent newComponent = ChatUtils.getCommandChatComponent(currentText, "/pgs DoNotSeekThreshold " + i, currentTooltip);
                returnComponent.appendSibling(newComponent);
            }
            returnComponent.appendSibling(new ChatComponentText(" ").setChatStyle(new ChatStyle()));
        }
        return returnComponent;
    }
}
