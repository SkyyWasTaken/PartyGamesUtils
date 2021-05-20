package us.skyywastaken.partygamesutils.command.PGU.subcommands;

import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import us.skyywastaken.partygamesutils.command.SubCommand;
import us.skyywastaken.partygamesutils.feature.startracker.StarTracker;
import us.skyywastaken.partygamesutils.util.StringUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class PGUDisplayStarsCommand implements SubCommand {
    private final StarTracker STAR_TRACKER;

    public PGUDisplayStarsCommand(StarTracker passedStarTracker) {
        this.STAR_TRACKER = passedStarTracker;
    }

    @Override
    public void onCommand(ICommandSender commandSender, String[] args) {
        sendPlayerStars(commandSender);
    }

    @Override
    public List<String> getTabCompletions(ICommandSender sender, String[] args, BlockPos blockPos) {
        return new ArrayList<>();
    }

    @Override
    public String getHelpInformation() {
        return "h";
    }

    private void sendPlayerStars(ICommandSender commandSender) {
        HashMap<String, Integer> playerScoreHashMap = this.STAR_TRACKER.getPlayerScoreHashMap();
        LinkedHashMap<String, Integer> sortedScoreHashMap = sortScoreMap(playerScoreHashMap);
        IChatComponent message = new ChatComponentText(getPlayerScoreHeader());
        Iterator<Map.Entry<String, Integer>> scoreIterator = sortedScoreHashMap.entrySet().iterator();
        while(scoreIterator.hasNext()) {
            Entry<String, Integer> currentEntry = scoreIterator.next();
            ChatComponentText newComponent = new ChatComponentText(StringUtils.INFORMATION_FORMATTING
                    + currentEntry.getKey() + ": "+ StringUtils.ACCENT_FORMATTING + currentEntry.getValue() + EnumChatFormatting.YELLOW + "\u272D");
            message.appendSibling(newComponent);
            if(scoreIterator.hasNext()) {
                message.appendSibling(new ChatComponentText("\n"));
            }
        }
        commandSender.addChatMessage(message);
    }

    private LinkedHashMap<String, Integer> sortScoreMap(HashMap<String, Integer> passedScoreMap) {
        LinkedList<Entry<String, Integer>> scoreList = new LinkedList<>(passedScoreMap.entrySet());
        scoreList.sort(Entry.comparingByValue((Comparator.reverseOrder())));
        LinkedHashMap<String, Integer> returnScoreMap = new LinkedHashMap<>();
        for(Entry<String, Integer> currentEntry : scoreList) {
            returnScoreMap.put(currentEntry.getKey(), currentEntry.getValue());
        }
        return returnScoreMap;
    }

    private String getPlayerScoreHeader() {
        String dashPadding = StringUtils.ACCENT_FORMATTING + "";
        return dashPadding + StringUtils.INFORMATION_FORMATTING + "All Stars" + dashPadding + "\n";
    }
}
