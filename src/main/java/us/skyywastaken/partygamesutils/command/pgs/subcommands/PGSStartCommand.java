package us.skyywastaken.partygamesutils.command.pgs.subcommands;

import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import us.skyywastaken.partygamesutils.command.PartyCommand;
import us.skyywastaken.partygamesutils.command.SubCommand;
import us.skyywastaken.partygamesutils.command.pgs.PGSManager;
import us.skyywastaken.partygamesutils.util.HypixelUtils;

import java.util.List;

public class PGSStartCommand implements SubCommand, PartyCommand {
    private final PGSManager PGS_MANAGER;

    public PGSStartCommand(PGSManager passedPGSManager) {
        this.PGS_MANAGER = passedPGSManager;
    }


    @Override
    public void onCommand(ICommandSender commandSender, String[] args) {
        enableSeeking();
        String messageString = EnumChatFormatting.GREEN + "Seeking has been " + EnumChatFormatting.AQUA + "ENABLED!";
        commandSender.addChatMessage(new ChatComponentText(messageString));
    }

    @Override
    public List<String> getTabCompletions(ICommandSender sender, String[] args, BlockPos blockPos) {
        return null;
    }

    @Override
    public void onPartyCommand(String[] args) {
        enableSeeking();
        HypixelUtils.sendPartyChatMessage("Seeking has been enabled!");
    }

    private void enableSeeking() {
        PGS_MANAGER.setSeekingEnabled(true);
    }
}
