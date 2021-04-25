package us.skyywastaken.partygamesutils.util;

import net.minecraft.event.ClickEvent;
import net.minecraft.event.HoverEvent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import us.skyywastaken.partygamesutils.feature.PGS.command.partycommands.PGSPartyCommandType;

import javax.annotation.Nullable;

public class ChatUtils {
    public static IChatComponent getCommandChatComponent(String clickableText, String commandText, @Nullable String textTooltip) {
        HoverEvent.Action showTextAction = HoverEvent.Action.SHOW_TEXT;
        HoverEvent styleHoverEvent = new HoverEvent(showTextAction, new ChatComponentText(textTooltip));
        ClickEvent styleClickEvent = new ClickEvent(ClickEvent.Action.RUN_COMMAND, commandText);
        ChatComponentText returnChatComponent
                = new ChatComponentText(clickableText);
        ChatStyle returnChatStyle
                = new ChatStyle().setChatHoverEvent(styleHoverEvent).setChatClickEvent(styleClickEvent);

        returnChatComponent.setChatStyle(returnChatStyle);
        return returnChatComponent;
    }

    public static IChatComponent getHoverChatComponent (String baseText, String hoverText) {
        HoverEvent.Action showTextAction = HoverEvent.Action.SHOW_TEXT;
        HoverEvent styleHoverEvent = new HoverEvent(showTextAction, new ChatComponentText(hoverText));
        ChatStyle hoverStyle = new ChatStyle().setChatHoverEvent(styleHoverEvent);
        ChatComponentText returnChatComponent = new ChatComponentText(baseText);
        returnChatComponent.setChatStyle(hoverStyle);
        return returnChatComponent;
    }
}
