package us.skyywastaken.partygamesutils.feature.gameseeking.command.subcommands;

import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import us.skyywastaken.partygamesutils.command.SubCommand;
import us.skyywastaken.partygamesutils.feature.gameseeking.command.partycommands.PGSPartyCommandType;
import us.skyywastaken.partygamesutils.feature.gameseeking.misc.SettingsMenuManager;
import us.skyywastaken.partygamesutils.feature.gameseeking.settings.PartyCommandSettings;
import us.skyywastaken.partygamesutils.util.ChatUtils;
import us.skyywastaken.partygamesutils.util.StringUtils;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class PGSPartyPermissionsCommand implements SubCommand {
    private final static int MESSAGE_ID = 3462;
    private final PartyCommandSettings PARTY_COMMAND_SETTINGS;
    private final SettingsMenuManager SETTINGS_MENU_MANAGER;

    public PGSPartyPermissionsCommand(PartyCommandSettings passedPartyCommandSettings, SettingsMenuManager passedSettingsMenuManager) {
        this.PARTY_COMMAND_SETTINGS = passedPartyCommandSettings;
        this.SETTINGS_MENU_MANAGER = passedSettingsMenuManager;
    }

    @Override
    public void onCommand(ICommandSender commandSender, String[] args) {
        if (args.length == 0) {
            sendPartyPermissionsMessage();
        } else {
            PGSPartyCommandType partyCommandType = PGSPartyCommandType.fromString(args[0]);
            if (partyCommandType == null) {
                sendInvalidPermissionMessage(commandSender, args[0]);
            } else {
                attemptToTogglePermission(partyCommandType);
                if (args.length > 1 && args[1].equals("--displaysettings")) {
                    SETTINGS_MENU_MANAGER.displaySettingsMenu();
                } else {
                    sendPartyPermissionsMessage();
                }
            }
        }
    }

    @Override
    public List<String> getTabCompletions(ICommandSender sender, String[] args, BlockPos blockPos) {
        if (args.length == 2) {
            return Arrays.asList("add", "remove", "clear", "list", "start", "stop", "toggleblacklist");
        } else {
            return null;
        }
    }

    @Override
    public String getHelpInformation() {
        return StringUtils.BODY_FORMATTING + "This command is used to manage individual party command permissions." +
                "Click a permission name to toggle it on or off.\n"
                + StringUtils.INFORMATION_FORMATTING + "Usage: " + StringUtils.COMMAND_USAGE_FORMATTING
                + "/pgs PartyPermissions";
    }

    private void sendPartyPermissionsMessage() {
        ChatUtils.addDeletableChatMessage(getPartyPermissionsMessage(), MESSAGE_ID);
    }

    private void attemptToTogglePermission(PGSPartyCommandType partyCommandType) {
        boolean currentValue = PARTY_COMMAND_SETTINGS.getPartyPermissionEnabled(partyCommandType);
        System.out.println(PARTY_COMMAND_SETTINGS.getPartyPermissionEnabled(partyCommandType));
        PARTY_COMMAND_SETTINGS.updatePartyPermission(partyCommandType, !currentValue);
    }

    private void sendInvalidPermissionMessage(ICommandSender commandSender, String invalidPermissionName) {
        String invalidPermissionMessage = getInvalidPermissionMessage(invalidPermissionName);
        commandSender.addChatMessage(new ChatComponentText(invalidPermissionMessage));
    }

    private String getInvalidPermissionMessage(String invalidPermissionName) {
        return EnumChatFormatting.GOLD + invalidPermissionName + StringUtils.WARNING_FORMATTING + " isn't a valid permission!";
    }

    private IChatComponent getPartyPermissionsMessage() {
        String baseMessage = EnumChatFormatting.AQUA + "----------" + EnumChatFormatting.YELLOW + "/pgs PartyPermissions"
                + EnumChatFormatting.AQUA + "----------\n"
                + EnumChatFormatting.GOLD + "Click a permission to toggle it on or off.\n";
        ChatComponentText mainChatComponent = new ChatComponentText(baseMessage);
        IChatComponent clickableChatComponents = getClickableChatComponents();
        mainChatComponent.appendSibling(clickableChatComponents);
        return mainChatComponent;
    }

    private IChatComponent getClickableChatComponents() {
        ChatComponentText newLineText = new ChatComponentText("\n");
        List<String> chatComponentsToAdd = getComponentStringList();
        Iterator<String> chatComponentStringIterator = chatComponentsToAdd.iterator();
        ChatComponentText baseChatComponent = new ChatComponentText("");
        while (chatComponentStringIterator.hasNext()) {
            String currentString = chatComponentStringIterator.next();
            baseChatComponent.appendSibling(getPartyCommandChatComponent(currentString));
            if (chatComponentStringIterator.hasNext()) {
                baseChatComponent.appendSibling(newLineText);
            }
        }
        return baseChatComponent;
    }

    private List<String> getComponentStringList() {
        return Arrays.asList("Add", "Remove", "Clear", "List", "Start", "Stop", "ToggleBlacklist");
    }

    private IChatComponent getPartyCommandChatComponent(String permissionName) {
        String hoverEventText = EnumChatFormatting.AQUA + "Click me to toggle the \"" + EnumChatFormatting.YELLOW
                + permissionName + EnumChatFormatting.AQUA + "\" permission";
        String permissionStatus = StringUtils.getEnabledDisabledString(PARTY_COMMAND_SETTINGS
                .getPartyPermissionEnabled(PGSPartyCommandType.fromString(permissionName)));
        String clickableText = EnumChatFormatting.LIGHT_PURPLE + permissionName + ": " + permissionStatus;
        String commandText = "/pgs PartyPermissions " + permissionName;
        return ChatUtils.getCommandChatComponent(clickableText, commandText, hoverEventText);
    }
}
