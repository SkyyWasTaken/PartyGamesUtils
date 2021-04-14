package us.skyywastaken.partygamesutils.command.pgs.subcommands;

import jline.internal.Nullable;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import us.skyywastaken.partygamesutils.command.PartyCommand;
import us.skyywastaken.partygamesutils.command.SubCommand;
import us.skyywastaken.partygamesutils.command.pgs.PGSManager;
import us.skyywastaken.partygamesutils.util.HypixelUtils;

import java.util.List;

public class PGSListCommand implements SubCommand, PartyCommand {
    private final PGSManager PGS_MANAGER;

    public PGSListCommand(PGSManager passedSeekManager) {
        this.PGS_MANAGER = passedSeekManager;
    }
    @Override
    public void onCommand(ICommandSender commandSender, String[] args) {
        sendGameList(false, commandSender);
    }

    @Override
    public List<String> getTabCompletions(ICommandSender sender, String[] args, BlockPos blockPos) {
        return null;
    }

    @Override
    public void onPartyCommand(String[] args) {
        sendGameList(true, null);
    }

    private void sendGameList(boolean isPartyCommand, @Nullable ICommandSender commandSender) {
        String gameListString;
        int gameListSize = PGS_MANAGER.getSeekListSize();
        if(gameListSize == 0) {
            gameListString = getGameListPrefix(isPartyCommand, gameListSize);
        } else {
            gameListString = getGameListPrefix(isPartyCommand, gameListSize) + getGameList(isPartyCommand);
        }
        if(isPartyCommand) {
            if(gameListString.length() > 200) {
                HypixelUtils.sendPartyChatMessage(getGameListTooLongString());
            } else {
                HypixelUtils.sendPartyChatMessage(gameListString);
            }
        } else {
            if(commandSender != null) {
                commandSender.addChatMessage(new ChatComponentText(gameListString));
            }
        }
    }

    private String getGameList(boolean isPartyCommand) {
        EnumChatFormatting commaColor = EnumChatFormatting.YELLOW;
        EnumChatFormatting gameColor = EnumChatFormatting.AQUA;
        String delimiter;
        List<String> seekList = PGS_MANAGER.getSeekList();
        if(isPartyCommand) {
            delimiter = ", ";
            return String.join(delimiter, seekList);
        } else {
            delimiter = commaColor + ", " + gameColor;
            return gameColor + String.join(delimiter, seekList);
        }
    }

    private String getGameListPrefix(boolean isPartyCommand, int gameAmount) {
        if(gameAmount == 0) {
            if(isPartyCommand) {
                return "There are no games in the seek list!";
            } else {
                return EnumChatFormatting.RED + "There are no games in the seek list!";
            }
        } else if(gameAmount == 1) {
            if(isPartyCommand) {
                return "Current sought game: ";
            } else {
                return EnumChatFormatting.GREEN + "Current sought game: ";
            }
        } else {
            if(isPartyCommand) {
                return "Currently sought games: ";
            } else {
                return EnumChatFormatting.GREEN + "Currently sought games:";
            }
        }
    }

    private String getGameListTooLongString() {
        return "The game list is too long to send through party chat!";
    }
}
