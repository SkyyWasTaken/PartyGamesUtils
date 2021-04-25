package us.skyywastaken.partygamesutils.feature.PGS.misc;

import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import us.skyywastaken.partygamesutils.feature.PGS.PGSManager;
import us.skyywastaken.partygamesutils.util.ChatUtils;

import java.util.Iterator;
import java.util.List;

public class ListMenuManager {
    private static final int MESSAGE_ID = 32432;
    private final PGSManager PGS_MANAGER;

    public ListMenuManager(PGSManager pgsManager) {
        this.PGS_MANAGER = pgsManager;
    }

    public void displayList() {
        ChatUtils.addDeletableChatMessage(getDeletableChatMessage(), MESSAGE_ID);
    }

    private IChatComponent getDeletableChatMessage() {
        EnumChatFormatting commaColor = EnumChatFormatting.YELLOW;
        EnumChatFormatting gameColor = EnumChatFormatting.AQUA;
        List<String> seekList = PGS_MANAGER.getSeekList();
        int gameListSize = seekList.size();
        IChatComponent gameList = new ChatComponentText(getGameListPrefix(gameListSize));
        Iterator<String> gameListIterator = seekList.iterator();
        while(gameListIterator.hasNext()) {
            String currentGame = gameListIterator.next();
            IChatComponent currentChatComponent
                    = ChatUtils.getCommandChatComponent(gameColor + currentGame,
                    "/pgs remove " + currentGame + ", --displaylist", "Click me to remove " + currentGame + "!");
            gameList.appendSibling(currentChatComponent);
            if(gameListIterator.hasNext()) {
                gameList.appendSibling(new ChatComponentText(commaColor + ", "));
            }
        }
        return gameList;
    }

    private String getGameListPrefix(int gameAmount) {
        if (gameAmount == 0) {
            return EnumChatFormatting.RED + "There are no games in the seek list!";
        } else if (gameAmount == 1) {
            return EnumChatFormatting.GREEN + "Current sought game: ";
        } else {
            return EnumChatFormatting.GREEN + "Currently sought games: ";
        }
    }
}
