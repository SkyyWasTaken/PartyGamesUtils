package us.skyywastaken.partygamesutils.command.pgs.SubCommands;

import net.minecraft.client.Minecraft;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import us.skyywastaken.partygamesutils.command.PartyCommand;
import us.skyywastaken.partygamesutils.command.SubCommand;
import us.skyywastaken.partygamesutils.misc.SeekManager;
import us.skyywastaken.partygamesutils.util.HypixelUtils;

public class PGSStopCommand implements SubCommand, PartyCommand {
    private final SeekManager SEEK_MANAGER;

    public PGSStopCommand(SeekManager passedSeekManager) {
        this.SEEK_MANAGER = passedSeekManager;
    }
    @Override
    public void onCommand(ICommandSender commandSender, String[] args) {
        disableSeeking();
        String successMessage = EnumChatFormatting.GREEN + "Seeking has been " + EnumChatFormatting.AQUA + "DISABLED!";
        if(Minecraft.getMinecraft().ingameGUI != null) {
            Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(new ChatComponentText(successMessage));
        }
    }

    @Override
    public void onPartyCommand(String[] args) {
        disableSeeking();
        HypixelUtils.sendPartyChatMessage("Seeking has been disabled!");
    }

    private void disableSeeking() {
        SEEK_MANAGER.setSeekingEnabled(false);
    }
}
