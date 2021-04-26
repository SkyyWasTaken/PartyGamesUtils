package us.skyywastaken.partygamesutils.feature.PGS.command.subcommands;

import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;
import us.skyywastaken.partygamesutils.command.SubCommand;
import us.skyywastaken.partygamesutils.feature.PGS.misc.ListMenuManager;
import us.skyywastaken.partygamesutils.util.StringUtils;

import java.util.List;

public class PGSListCommand implements SubCommand {
    private final ListMenuManager LIST_MENU_MANAGER;

    public PGSListCommand(ListMenuManager passedListMenuManager) {
        this.LIST_MENU_MANAGER = passedListMenuManager;
    }

    @Override
    public void onCommand(ICommandSender commandSender, String[] args) {
        sendGameList();
    }

    @Override
    public List<String> getTabCompletions(ICommandSender sender, String[] args, BlockPos blockPos) {
        return null;
    }

    @Override
    public String getHelpInformation() {
        return StringUtils.BODY_FORMATTING + "This command is used to list all of the games in the seek list. "
                + "You can click a game to remove it from the list.\n"
                + StringUtils.INFORMATION_FORMATTING + "Usage: " + StringUtils.COMMAND_USAGE_FORMATTING
                + "/pgs list";
    }

    private void sendGameList() {
        LIST_MENU_MANAGER.displayList();
    }
}
