package us.skyywastaken.partygamesutils.feature.gameseeking.misc;

import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import us.skyywastaken.partygamesutils.feature.gameseeking.settings.SeekSettings;
import us.skyywastaken.partygamesutils.util.ChatUtils;
import us.skyywastaken.partygamesutils.util.StringUtils;

import java.util.Iterator;
import java.util.List;

public class ListMenuManager {
    private static final int MESSAGE_ID = 32432;
    private final SeekSettings PGS_MANAGER;

    public ListMenuManager(SeekSettings seekSettings) {
        this.PGS_MANAGER = seekSettings;
    }

    public void displayList() {
        ChatUtils.addDeletableChatMessage(getFullChatComponent(), MESSAGE_ID);
    }

    private IChatComponent getFullChatComponent() {
        IChatComponent fullChatComponent = new ChatComponentText("");
        fullChatComponent.appendSibling(createGameListHeader());
        fullChatComponent.appendSibling(createGameListDescription());
        fullChatComponent.appendSibling(createGameList());
        return fullChatComponent;
    }

    private IChatComponent createGameList() {
        EnumChatFormatting commaColor = EnumChatFormatting.YELLOW;
        EnumChatFormatting gameColor = EnumChatFormatting.AQUA;

        List<String> seekList = PGS_MANAGER.getSeekList();
        int gameListSize = seekList.size();
        IChatComponent returnComponent = new ChatComponentText("");
        returnComponent.appendSibling(getGameListPrefix(gameListSize));

        Iterator<String> seekListIterator = seekList.iterator();
        while (seekListIterator.hasNext()) {
            String currentGame = seekListIterator.next();
            IChatComponent currentGameComponent
                    = ChatUtils.getCommandChatComponent(gameColor + currentGame,
                    "/pgs remove " + currentGame + " --displaylist", "Click me to remove " + currentGame + "!");
            returnComponent.appendSibling(currentGameComponent);
            if (seekListIterator.hasNext()) {
                returnComponent.appendSibling(new ChatComponentText(commaColor + ", "));
            }
        }
        return returnComponent;
    }

    private IChatComponent createGameListHeader() {
        String dashPadding = StringUtils.ACCENT_FORMATTING + "----------------";
        String headerText = dashPadding + StringUtils.INFORMATION_FORMATTING + "/pgs list" + dashPadding + "\n";

        String commandDesc = StringUtils.BODY_FORMATTING + "Click a game to remove it from the list!";
        return new ChatComponentText(headerText);
    }

    private IChatComponent createGameListDescription() {
        String seekListDescription;
        int seekListSize = this.PGS_MANAGER.getSeekListSize();
        if (seekListSize == 0) {
            seekListDescription = StringUtils.BODY_FORMATTING + "This description would be useful, but you aren't " +
                    "seeking any games!\n";
        } else if (seekListSize == 1) {
            seekListDescription = StringUtils.BODY_FORMATTING + "Click the game to remove it from the seek list!\n";
        } else {
            seekListDescription = StringUtils.BODY_FORMATTING + "Click a game to remove it from the seek list!\n";
        }
        return new ChatComponentText(seekListDescription);
    }

    private IChatComponent getGameListPrefix(int gameAmount) {
        String prefixString;
        if (gameAmount == 0) {
            prefixString = StringUtils.WARNING_FORMATTING + "There are no games in the seek list!";
        } else if (gameAmount == 1) {
            prefixString = StringUtils.BODY_FORMATTING + "Current sought game: ";
        } else {
            prefixString = StringUtils.BODY_FORMATTING + "Currently sought games: ";
        }
        return new ChatComponentText(prefixString);
    }
}
