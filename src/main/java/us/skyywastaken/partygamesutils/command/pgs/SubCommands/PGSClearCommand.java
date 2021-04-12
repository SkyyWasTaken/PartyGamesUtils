package us.skyywastaken.partygamesutils.command.pgs.SubCommands;

import net.minecraft.client.Minecraft;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import us.skyywastaken.partygamesutils.command.PartyCommand;
import us.skyywastaken.partygamesutils.command.SubCommand;
import us.skyywastaken.partygamesutils.misc.SeekManager;
import us.skyywastaken.partygamesutils.util.HypixelUtils;

import java.util.List;

public class PGSClearCommand implements SubCommand, PartyCommand {
    private final SeekManager SEEK_MANAGER;

    public PGSClearCommand(SeekManager passedSeekManager) {
        this.SEEK_MANAGER = passedSeekManager;
    }
    @Override
    public void onCommand(ICommandSender commandSender, String[] args) {
        SEEK_MANAGER.clearSeekList();
        String successMessage = EnumChatFormatting.GREEN + "Successfully " + EnumChatFormatting.YELLOW
                + "cleared " + EnumChatFormatting.GREEN + "the seeking list!";
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
        SEEK_MANAGER.clearSeekList();
        HypixelUtils.sendPartyChatMessage("Successfully cleared the seeking list!");
    }
}
