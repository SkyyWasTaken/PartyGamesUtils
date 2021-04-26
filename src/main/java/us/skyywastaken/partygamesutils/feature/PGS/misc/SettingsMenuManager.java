package us.skyywastaken.partygamesutils.feature.PGS.misc;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiNewChat;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import us.skyywastaken.partygamesutils.feature.PGS.command.partycommands.PGSPartyCommandType;
import us.skyywastaken.partygamesutils.feature.PGS.settings.PartyCommandSettings;
import us.skyywastaken.partygamesutils.feature.PGS.settings.SeekSettings;
import us.skyywastaken.partygamesutils.util.ChatUtils;
import us.skyywastaken.partygamesutils.util.StringUtils;

import java.util.Iterator;
import java.util.LinkedHashMap;

public class SettingsMenuManager {
    private static final int MESSAGE_ID = 3621;
    private final SeekSettings PGS_MANAGER;
    private final PartyCommandSettings PARTY_COMMAND_SETTINGS;

    public SettingsMenuManager(SeekSettings seekSettings, PartyCommandSettings passedPartyCommandSettings) {
        this.PGS_MANAGER = seekSettings;
        this.PARTY_COMMAND_SETTINGS = passedPartyCommandSettings;
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
        return new ChatComponentText(EnumChatFormatting.AQUA + "---------------" + EnumChatFormatting.YELLOW
                + "PGS Settings" + EnumChatFormatting.AQUA + "---------------\n");
    }

    private IChatComponent getSettingsMenu() {
        IChatComponent newLineComponent = new ChatComponentText("\n");
        IChatComponent returnComponent = new ChatComponentText("");
        returnComponent.appendSibling(getSettingsLineOne()).appendSibling(newLineComponent);
        returnComponent.appendSibling(getSettingsLineTwo()).appendSibling(newLineComponent);
        returnComponent.appendSibling(getSettingsLineThree()).appendSibling(newLineComponent);
        returnComponent.appendSibling(getSettingsLineFour()).appendSibling(newLineComponent);
        returnComponent.appendSibling(getSettingsLineFive()).appendSibling(newLineComponent);
        returnComponent.appendSibling(getSettingsLineSix());

        return returnComponent;
    }

    private IChatComponent getSettingsLineOne() {
        // Click here to refresh. For more info, commit click.
        String refreshString = EnumChatFormatting.GOLD + "Click a setting's value to change it. For more info on a setting, click the setting name.";
        String refreshTooltip = "Click me to reload the settings menu!";
        return ChatUtils.getCommandChatComponent(refreshString, "/pgs settings", refreshTooltip);
    }

    private IChatComponent getSettingsLineTwo() {
        // Seeking: (status)! | Blacklist: (status)!
        IChatComponent returnComponent = new ChatComponentText("");
        String seekHelpText = StringUtils.BODY_FORMATTING + "Seeking:";
        String seekHelpCommand = "/pgs help ToggleSeek";
        String dNSTTooltip = "Click me to view information about toggling seeking!";
        returnComponent.appendSibling(ChatUtils.getCommandChatComponent(seekHelpText, seekHelpCommand, dNSTTooltip));
        returnComponent.appendSibling(new ChatComponentText(" "));

        String seekStatusString = StringUtils.getEnabledDisabledString(PGS_MANAGER.isSeekingEnabled()).toUpperCase()
                + StringUtils.BODY_FORMATTING + "!";
        String seekCommand = "/pgs ToggleSeek --displaysettings";
        String seekTooltip = "Click me to toggle seeking!";
        returnComponent.appendSibling(ChatUtils.getCommandChatComponent(seekStatusString, seekCommand, seekTooltip));
        returnComponent.appendSibling(new ChatComponentText( StringUtils.BODY_FORMATTING + " | ").setChatStyle(new ChatStyle()));


        String blacklistText = StringUtils.BODY_FORMATTING + "Blacklist:";
        String blacklistHelpCommand = "/pgs help ToggleBlacklist";
        String blacklistHelpTooltip = "Click me to view information about toggling the blacklist!";
        returnComponent.appendSibling(ChatUtils.getCommandChatComponent(blacklistText,
                blacklistHelpCommand, blacklistHelpTooltip));
        returnComponent.appendSibling(new ChatComponentText(" "));

        String blacklistString = StringUtils.getEnabledDisabledString(PGS_MANAGER.isBlacklistEnabled()).toUpperCase()
                + StringUtils.BODY_FORMATTING + "!";
        String blacklistTooltip = "Click me to toggle the blacklist!";
        returnComponent.appendSibling(ChatUtils.getCommandChatComponent(blacklistString,
                "/pgs ToggleBlacklist --displaysettings", blacklistTooltip));
        return returnComponent;
    }

    private IChatComponent getSettingsLineThree() {
        // Do Not Seek Threshold: 0 1 2 3 4 5 6 7 8
        IChatComponent returnComponent = new ChatComponentText("");
        String dNSTText = StringUtils.BODY_FORMATTING + "Do Not Seek Threshold:";
        String dNSTCommand = "/pgs help DoNotSeekThreshold";
        String dNSTTooltip = "Click me to view information about the seek threshold!";
        returnComponent.appendSibling(ChatUtils.getCommandChatComponent(dNSTText, dNSTCommand, dNSTTooltip));
        returnComponent.appendSibling(new ChatComponentText(" "));

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
        String listHelpText = StringUtils.BODY_FORMATTING + "Seek list:";
        String listHelpCommand = "/pgs help list";
        String listHelpTooltip = "Click me to view information about listing sought games!";
        returnComponent.appendSibling(ChatUtils.getCommandChatComponent(listHelpText, listHelpCommand, listHelpTooltip));
        returnComponent.appendSibling(new ChatComponentText(" "));

        int gameStatus = PGS_MANAGER.getSeekListSize();
        String gameString = StringUtils.ACCENT_FORMATTING + gameStatus + StringUtils.INFORMATION_FORMATTING + " games";
        if(gameStatus == 0) {
            String noListGameString = gameString + ". Use '" + StringUtils.ACCENT_FORMATTING + "/pgs add"
                    + StringUtils.INFORMATION_FORMATTING + "' to add games to the seek list!";
            returnComponent.appendSibling(new ChatComponentText(noListGameString));
        } else {
            String gameStatusString = StringUtils.ACCENT_FORMATTING + gameStatus + StringUtils.INFORMATION_FORMATTING
                    + (gameStatus == 1 ? " game" : " games") + ", click to view!";
            String gameStatusCommand = "/pgs list";
            String gameStatusTooltip = "Click me to display the game list!";
            returnComponent.appendSibling(ChatUtils.getCommandChatComponent(gameStatusString,
                    gameStatusCommand, gameStatusTooltip));
        }
        return returnComponent;
    }

    private IChatComponent getSettingsLineFive() {
        //Party commands: (STATUS)! | Permissions:
        IChatComponent returnComponent = new ChatComponentText("");
        String pCHelpText = StringUtils.BODY_FORMATTING + "Party commands: ";
        String pCHelpCommand = "/pgs help TogglePartyCommands";
        String pCHelpTooltip = "Click me to view information about toggling party commands!";
        returnComponent.appendSibling(ChatUtils.getCommandChatComponent(pCHelpText, pCHelpCommand, pCHelpTooltip));

        boolean partyCommandStatus = PARTY_COMMAND_SETTINGS.getPartyCommandsEnabled();
        String partyCommandStatusText = StringUtils.BODY_FORMATTING
                + StringUtils.getEnabledDisabledString(partyCommandStatus).toUpperCase()
                + StringUtils.BODY_FORMATTING + "!";
        String partyCommandStatusCommand = "/pgs TogglePartyCommands --displaysettings";
        String partyCommandTooltip = "Click me to toggle party commands!";
        returnComponent.appendSibling(ChatUtils
                .getCommandChatComponent(partyCommandStatusText, partyCommandStatusCommand, partyCommandTooltip));

        String dividerString = StringUtils.BODY_FORMATTING + " | ";
        returnComponent.appendSibling(new ChatComponentText(dividerString));

        String partyPermissionHelpText = StringUtils.BODY_FORMATTING + "Permissions:";
        String partyPermissionHelpCommand = "/pgs help PartyPermissions";
        String partyPermissionTooltip = "Click me to view party permission help!";
        returnComponent.appendSibling(ChatUtils
                .getCommandChatComponent(partyPermissionHelpText, partyPermissionHelpCommand, partyPermissionTooltip));
        return returnComponent;
    }

    private IChatComponent getSettingsLineSix() {
        IChatComponent returnComponent = new ChatComponentText("");
        LinkedHashMap<String, String> permissionStringPermissionNameHashMap = new LinkedHashMap<>();
        permissionStringPermissionNameHashMap.put("ADD", "Add");
        permissionStringPermissionNameHashMap.put("REM", "Remove");
        permissionStringPermissionNameHashMap.put("STRT", "Start");
        permissionStringPermissionNameHashMap.put("STOP", "Stop");
        permissionStringPermissionNameHashMap.put("CLR", "Clear");
        permissionStringPermissionNameHashMap.put("LST", "List");
        permissionStringPermissionNameHashMap.put("TGLBLKLST", "ToggleBlacklist");
        Iterator<String> textIterator = permissionStringPermissionNameHashMap.keySet().iterator();

        while(textIterator.hasNext()) {
            String currentString = textIterator.next();
            String permissionString = permissionStringPermissionNameHashMap.get(currentString);
            boolean currentPermissionValue = this.PARTY_COMMAND_SETTINGS.getPartyPermissionEnabled(
                    PGSPartyCommandType.fromString(permissionString));
            String permissionText = StringUtils.BODY_FORMATTING + "["
                    + StringUtils.getEnabledDisabledFormatting(currentPermissionValue) + currentString
                    + StringUtils.BODY_FORMATTING + "]";
            String permissionCommand = "/pgs PartyPermissions " + permissionString + " --displaysettings";
            String permissionTooltip = "Click me to toggle the " + permissionString + " permission!";
            returnComponent.appendSibling(ChatUtils.getCommandChatComponent(permissionText, permissionCommand, permissionTooltip));
            if(textIterator.hasNext()) {
                returnComponent.appendSibling(new ChatComponentText(" "));
            }
        }
        return returnComponent;
    }

}
