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
import us.skyywastaken.partygamesutils.command.pgs.PartyCommands.PGSPartyCommandManager;
import us.skyywastaken.partygamesutils.command.pgs.PartyCommands.PGSPartyCommandType;

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
                String failureMessage = EnumChatFormatting.GOLD + args[0] + EnumChatFormatting.RED
                        + " isn't a valid permission!";
                commandSender.addChatMessage(new ChatComponentText(failureMessage));
            } else {
                boolean currentValue = PGS_MANAGER.getPartyPermissionEnabled(partyCommandType);
                PGS_MANAGER.updatePartyPermission(partyCommandType, !currentValue);
                String successMessage = EnumChatFormatting.YELLOW + partyCommandType.toString()
                        + EnumChatFormatting.AQUA + " has been " + getEnabledDisabledString(!currentValue);
                commandSender.addChatMessage(new ChatComponentText(successMessage));
            }
        }
    }

    @Override
    public List<String> getTabCompletions(ICommandSender sender, String[] args, BlockPos blockPos) {
        return Arrays.asList(new String[]{"add", "remove", "clear", "list", "start", "stop"});
    }
    private IChatComponent getPartyPermissionsMessage() {
        String baseMessage = EnumChatFormatting.AQUA + "----------" + EnumChatFormatting.YELLOW + "/pgs PartyPermissions"
                + EnumChatFormatting.AQUA + "----------\n"
                + EnumChatFormatting.GOLD + "Click a permission to toggle it on or off.\n";
        ChatComponentText mainChatComponent = new ChatComponentText(baseMessage);
        ChatComponentText newLineText = new ChatComponentText("\n");
        mainChatComponent.appendSibling(getPartyCommandChatComponent("Add")).appendSibling(newLineText)
                .appendSibling(getPartyCommandChatComponent("Remove")).appendSibling(newLineText)
                .appendSibling(getPartyCommandChatComponent("Clear")).appendSibling(newLineText)
                .appendSibling(getPartyCommandChatComponent("List")).appendSibling(newLineText)
                .appendSibling(getPartyCommandChatComponent("Start")).appendSibling(newLineText)
                .appendSibling(getPartyCommandChatComponent("Stop"));
        return mainChatComponent;
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
        String permissionStatus = getEnabledDisabledString(PGS_MANAGER
                .getPartyPermissionEnabled(PGSPartyCommandType.fromString(permissionName)));
        ChatComponentText returnChatComponent
                = new ChatComponentText(clickableText.replace("%PermissionName%", permissionName)
                .replace("%PermissionStatus%", permissionStatus));
        ChatStyle returnChatStyle
                = new ChatStyle().setChatHoverEvent(styleHoverEvent).setChatClickEvent(styleClickEvent);

        returnChatComponent.setChatStyle(returnChatStyle);
        return returnChatComponent;
    }

    private String getEnabledDisabledString(boolean passedBoolean) {
        if(passedBoolean) {
            return EnumChatFormatting.GREEN + "Enabled!";
        } else {
            return EnumChatFormatting.DARK_RED + "Disabled!";
        }
    }
}
