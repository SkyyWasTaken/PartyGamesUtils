package us.skyywastaken.partygamesutils.feature.PGS.command.subcommands;

import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;
import us.skyywastaken.partygamesutils.command.PartyCommand;
import us.skyywastaken.partygamesutils.command.SubCommand;
import us.skyywastaken.partygamesutils.feature.PGS.PGSManager;
import us.skyywastaken.partygamesutils.feature.PGS.misc.ListMenuManager;
import us.skyywastaken.partygamesutils.util.HypixelUtils;
import us.skyywastaken.partygamesutils.util.StringUtils;

import java.util.List;

public class PGSListCommand implements SubCommand, PartyCommand {
    private final PGSManager PGS_MANAGER;
    private final ListMenuManager LIST_MENU_MANAGER;

    public PGSListCommand(PGSManager passedSeekManager) {
        this.PGS_MANAGER = passedSeekManager;
        this.LIST_MENU_MANAGER = new ListMenuManager(this.PGS_MANAGER);
    }

    @Override
    public void onCommand(ICommandSender commandSender, String[] args) {
        sendGameList(false);
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

    @Override
    public void onPartyCommand(String[] args) {
        sendGameList(true);
    }

    private void sendGameList(boolean isPartyCommand) {
        String gameListString;
        int gameListSize = PGS_MANAGER.getSeekListSize();
        if(isPartyCommand) {
            gameListString = getGameListPrefix(gameListSize) + getUnformattedGameList();
            if (gameListString.length() > 200) {
                HypixelUtils.sendPartyChatMessage(getGameListTooLongString());
            } else {
                HypixelUtils.sendPartyChatMessage(gameListString);
            }
        } else {
            LIST_MENU_MANAGER.displayList();
        }
    }

    private String getUnformattedGameList() {
        String delimiter;
        List<String> seekList = PGS_MANAGER.getSeekList();
        delimiter = ", ";
        return String.join(delimiter, seekList);
    }

    private String getGameListPrefix(int gameAmount) {
        if (gameAmount == 0) {
            return "There are no games in the seek list!";
        } else if (gameAmount == 1) {
            return "Current sought game: ";
        } else {
            return "Currently sought games: ";
        }
    }

    private String getGameListTooLongString() {
        return "The game list is too long to send through party chat!";
    }
}
