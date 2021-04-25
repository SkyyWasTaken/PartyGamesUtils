package us.skyywastaken.partygamesutils.feature.PGS.command.subcommands;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiNewChat;
import net.minecraft.command.ICommandSender;
import net.minecraft.event.ClickEvent;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import us.skyywastaken.partygamesutils.command.SubCommand;
import us.skyywastaken.partygamesutils.feature.PGS.PGSManager;
import us.skyywastaken.partygamesutils.feature.PGS.misc.SettingsMenuManager;
import us.skyywastaken.partygamesutils.util.ChatUtils;
import us.skyywastaken.partygamesutils.util.StringUtils;

import java.util.List;

public class PGSSettingsCommand implements SubCommand {
    private final SettingsMenuManager SETTINGS_MENU_MANAGER;
    public PGSSettingsCommand(SettingsMenuManager settingsMenuManager) {
        this.SETTINGS_MENU_MANAGER = settingsMenuManager;
    }

    @Override
    public void onCommand(ICommandSender commandSender, String[] args) {
        SETTINGS_MENU_MANAGER.sendSettingsMenu();
    }

    @Override
    public List<String> getTabCompletions(ICommandSender sender, String[] args, BlockPos blockPos) {
        return null;
    }

}
