package us.skyywastaken.partygamesutils.command.pgs.SubCommands;

import net.minecraft.client.Minecraft;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import us.skyywastaken.partygamesutils.command.PartyCommand;
import us.skyywastaken.partygamesutils.command.SubCommand;
import us.skyywastaken.partygamesutils.misc.SeekManager;
import us.skyywastaken.partygamesutils.util.HypixelUtils;

public class PGSStartCommand implements SubCommand, PartyCommand {
    private final SeekManager SEEK_MANAGER;

    public PGSStartCommand(SeekManager passedSeekManager) {
        this.SEEK_MANAGER = passedSeekManager;
    }


    @Override
    public void onCommand(ICommandSender commandSender, String[] args) {
        enableSeeking();
        String messageString = EnumChatFormatting.GREEN + "Seeking has been " + EnumChatFormatting.AQUA + "ENABLED!";
        commandSender.addChatMessage(new ChatComponentText(messageString));
    }

    @Override
    public void onPartyCommand(String[] args) {
        enableSeeking();
        HypixelUtils.sendPartyChatMessage("Seeking has been enabled!");
    }

    private void enableSeeking() {
        SEEK_MANAGER.setSeekingEnabled(true);
    }
}
