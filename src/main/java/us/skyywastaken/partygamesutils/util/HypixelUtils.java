package us.skyywastaken.partygamesutils.util;

import net.minecraft.client.Minecraft;

public class HypixelUtils {

    public static void sendPartyChatMessage(String messageString) {
        Minecraft.getMinecraft().thePlayer.sendChatMessage("/pchat " + messageString);
    }
}
