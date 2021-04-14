package us.skyywastaken.partygamesutils.command.pgs.subcommands;

import net.minecraft.command.ICommandSender;
import net.minecraft.event.ClickEvent;
import net.minecraft.event.HoverEvent;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import scala.actors.threadpool.Arrays;
import us.skyywastaken.partygamesutils.command.SubCommand;
import us.skyywastaken.partygamesutils.command.pgs.PGSManager;
import us.skyywastaken.partygamesutils.command.pgs.PartyCommands.PGSPartyCommandType;
import us.skyywastaken.partygamesutils.util.StringUtils;

import java.util.Iterator;
import java.util.List;

public class PGSPartyPermissionsCommand implements SubCommand {
    private final PGSManager PGS_MANAGER;

    public PGSPartyPermissionsCommand(PGSManager passedPGSManager) {
        this.PGS_MANAGER = passedPGSManager;
    }

    @Override
    public void onCommand(ICommandSender commandSender, String[] args) {
        if(args.length < 2) {
            commandSender.addChatMessage(getPartyPermissionsMessage());
        } else if (args.length == 2) {
            PGSPartyCommandType partyCommandType = PGSPartyCommandType.fromString(args[1]);
            if(partyCommandType == null) {
                sendInvalidPermissionMessage(commandSender, args[0]);
            } else {
                attemptToTogglePermission(commandSender, partyCommandType);
            }
        }
    }

    @Override
    public List<String> getTabCompletions(ICommandSender sender, String[] args, BlockPos blockPos) {
        if(args.length == 2) {
            return Arrays.asList(new String[]{"add", "remove", "clear", "list", "start", "stop"});
        } else {
            return null;
        }
    }

    private void attemptToTogglePermission(ICommandSender commandSender, PGSPartyCommandType partyCommandType) {
        boolean currentValue = PGS_MANAGER.getPartyPermissionEnabled(partyCommandType);
        PGS_MANAGER.updatePartyPermission(partyCommandType, !currentValue);
        sendSuccessMessage(commandSender, partyCommandType, currentValue);
    }

    private void sendSuccessMessage(ICommandSender commandSender, PGSPartyCommandType partyCommandType
            , boolean oldValue) {
        String successMessage = getSuccessString(partyCommandType, oldValue);
        commandSender.addChatMessage(new ChatComponentText(successMessage));
    }

    private String getSuccessString(PGSPartyCommandType partyCommandType, boolean oldValue) {
        return EnumChatFormatting.YELLOW + partyCommandType.toString()
                + EnumChatFormatting.AQUA + " has been " + StringUtils.getEnabledDisabledString(!oldValue);
    }

    private void sendInvalidPermissionMessage(ICommandSender commandSender, String invalidPermissionName) {
        String invalidPermissionMessage = getInvalidPermissionMessage(invalidPermissionName);
        commandSender.addChatMessage(new ChatComponentText(invalidPermissionMessage));
    }

    private String getInvalidPermissionMessage(String invalidPermissionName) {
        return EnumChatFormatting.GOLD + invalidPermissionName + EnumChatFormatting.RED + " isn't a valid permission!";
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
        while(chatComponentStringIterator.hasNext()) {
            String currentString = chatComponentStringIterator.next();
            baseChatComponent.appendSibling(getPartyCommandChatComponent(currentString));
            if(chatComponentStringIterator.hasNext()) {
                baseChatComponent.appendSibling(newLineText);
            }
        }
        return baseChatComponent;
    }

    private List<String> getComponentStringList() {
        return Arrays.asList(new String[]{"Add", "Remove", "Clear", "List", "Start", "Stop"});
    }

    private ChatComponentText getPartyCommandChatComponent(String permissionName) {
        HoverEvent.Action showTextAction = HoverEvent.Action.SHOW_TEXT;
        String hoverEventText = EnumChatFormatting.AQUA + "Click me to toggle the \"" + EnumChatFormatting.YELLOW
                + "%PermissionName%" + EnumChatFormatting.AQUA + "\" permission";
        String clickableText = EnumChatFormatting.LIGHT_PURPLE + "%PermissionName%: %PermissionStatus%";
        HoverEvent styleHoverEvent = new HoverEvent(showTextAction, new ChatComponentText(hoverEventText
                .replace("%PermissionName%", permissionName)));
        ClickEvent styleClickEvent = new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/pgs PartyPermissions "
                + permissionName);
        String permissionStatus = StringUtils.getEnabledDisabledString(PGS_MANAGER
                .getPartyPermissionEnabled(PGSPartyCommandType.fromString(permissionName)));
        ChatComponentText returnChatComponent
                = new ChatComponentText(clickableText.replace("%PermissionName%", permissionName)
                .replace("%PermissionStatus%", permissionStatus));
        ChatStyle returnChatStyle
                = new ChatStyle().setChatHoverEvent(styleHoverEvent).setChatClickEvent(styleClickEvent);

        returnChatComponent.setChatStyle(returnChatStyle);
        return returnChatComponent;
    }
}
