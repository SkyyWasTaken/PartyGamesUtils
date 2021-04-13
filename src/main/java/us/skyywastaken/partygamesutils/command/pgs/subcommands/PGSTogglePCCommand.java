package us.skyywastaken.partygamesutils.command.pgs.subcommands;

import net.minecraft.client.Minecraft;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import us.skyywastaken.partygamesutils.command.SubCommand;
import us.skyywastaken.partygamesutils.command.pgs.PGSManager;

import java.util.List;

public class PGSTogglePCCommand implements SubCommand {
    private final PGSManager PGS_MANAGER;

    public PGSTogglePCCommand(PGSManager passedPartyCommandManager) {
        this.PGS_MANAGER = passedPartyCommandManager;
    }
    @Override
    public void onCommand(ICommandSender commandSender, String[] args) {
        PGS_MANAGER.setPartyCommandsEnabled(!PGS_MANAGER.arePartyCommandsEnabled());
        if(!(Minecraft.getMinecraft().ingameGUI == null)){
            String message = "Party commands are now " + (PGS_MANAGER.arePartyCommandsEnabled() ? "enabled" : "disabled");
            Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(new ChatComponentText(message));
        }
    }

    @Override
    public List<String> getTabCompletions(ICommandSender sender, String[] args, BlockPos blockPos) {
        return null;
    }
}
