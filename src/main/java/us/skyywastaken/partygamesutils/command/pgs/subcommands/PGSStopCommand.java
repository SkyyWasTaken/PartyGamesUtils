package us.skyywastaken.partygamesutils.command.pgs.subcommands;

import net.minecraft.client.Minecraft;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import us.skyywastaken.partygamesutils.command.PartyCommand;
import us.skyywastaken.partygamesutils.command.SubCommand;
import us.skyywastaken.partygamesutils.command.pgs.PGSManager;
import us.skyywastaken.partygamesutils.misc.SeekManager;
import us.skyywastaken.partygamesutils.util.HypixelUtils;

import java.util.List;

public class PGSStopCommand implements SubCommand, PartyCommand {
    private final PGSManager PGS_MANAGER;

    public PGSStopCommand(PGSManager passedPGSManager) {
        this.PGS_MANAGER = passedPGSManager;
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
    public List<String> getTabCompletions(ICommandSender sender, String[] args, BlockPos blockPos) {
        return null;
    }

    @Override
    public void onPartyCommand(String[] args) {
        disableSeeking();
        HypixelUtils.sendPartyChatMessage("Seeking has been disabled!");
    }

    private void disableSeeking() {
        PGS_MANAGER.setSeekingEnabled(false);
    }
}
