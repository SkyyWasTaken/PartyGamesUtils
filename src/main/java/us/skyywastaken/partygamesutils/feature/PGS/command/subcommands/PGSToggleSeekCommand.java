package us.skyywastaken.partygamesutils.feature.PGS.command.subcommands;

import jline.internal.Nullable;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import us.skyywastaken.partygamesutils.command.SubCommand;
import us.skyywastaken.partygamesutils.feature.PGS.PGSManager;
import us.skyywastaken.partygamesutils.util.HypixelUtils;
import us.skyywastaken.partygamesutils.util.StringUtils;

import java.util.List;

public class PGSToggleSeekCommand implements SubCommand{
    private final PGSManager PGS_MANAGER;

    public PGSToggleSeekCommand(PGSManager passedPGSManager) {
        this.PGS_MANAGER = passedPGSManager;
    }

    @Override
    public void onCommand(ICommandSender commandSender, String[] args) {
        toggleSeeking();
        sendSuccessMessage(commandSender);
    }

    @Override
    public List<String> getTabCompletions(ICommandSender sender, String[] args, BlockPos blockPos) {
        return null;
    }


    private void sendSuccessMessage(ICommandSender commandSender) {
        String successMessage = getSuccessMessage();
        commandSender.addChatMessage(new ChatComponentText(successMessage));
    }

    private String getSuccessMessage() {
        boolean newSeekingStatus = PGS_MANAGER.isSeekingEnabled();
        return EnumChatFormatting.GREEN + "Seeking has been "
                + StringUtils.getEnabledDisabledString(newSeekingStatus).toUpperCase()
                + EnumChatFormatting.GREEN + "!";
    }

    private void toggleSeeking() {
        PGS_MANAGER.setSeekingEnabled(!PGS_MANAGER.isSeekingEnabled());
    }
}
