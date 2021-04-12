package us.skyywastaken.partygamesutils.command.pgs.subcommands.set;

import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import us.skyywastaken.partygamesutils.command.SubCommand;

import java.util.Arrays;
import java.util.List;

public class PGSSetCommand implements SubCommand {
    private final PGSSettingsManager SETTINGS_MANAGER;

    public PGSSetCommand(PGSSettingsManager passedSettingsManager) {
        this.SETTINGS_MANAGER = passedSettingsManager;
    }

    @Override
    public void onCommand(ICommandSender commandSender, String[] args) {
        String tooFewArgsFeedback = EnumChatFormatting.RED + "Not enough arguments!\n"
                + EnumChatFormatting.WHITE + "To see correct usage, type /pgs set help";
        if(args.length < 2) {
            commandSender.addChatMessage(new ChatComponentText(tooFewArgsFeedback));
            return;
        }
    }

    @Override
    public List<String> getTabCompletions(ICommandSender sender, String[] args, BlockPos blockPos) {
        return SETTINGS_MANAGER.getTabCompletions(args);
    }
}
