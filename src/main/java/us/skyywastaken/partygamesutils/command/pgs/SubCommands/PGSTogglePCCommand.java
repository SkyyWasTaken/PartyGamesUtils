package us.skyywastaken.partygamesutils.command.pgs.SubCommands;

import net.minecraft.client.Minecraft;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import us.skyywastaken.partygamesutils.command.SubCommand;
import us.skyywastaken.partygamesutils.misc.SeekManager;

public class PGSTogglePCCommand implements SubCommand {
    private final SeekManager SEEK_MANAGER;

    public PGSTogglePCCommand(SeekManager passedSeekManager) {
        this.SEEK_MANAGER = passedSeekManager;
    }
    @Override
    public void onCommand(ICommandSender commandSender, String[] args) {
        SEEK_MANAGER.setPartyCommandsEnabled(!SEEK_MANAGER.getPartyCommandsEnabled());
        if(!(Minecraft.getMinecraft().ingameGUI == null)){
            String message = "Party commands are now " + (SEEK_MANAGER.getPartyCommandsEnabled() ? "enabled" : "disabled");
            Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(new ChatComponentText(message));
        }
    }
}
