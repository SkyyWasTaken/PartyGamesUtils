package us.skyywastaken.partygamesutils.util;

import net.minecraft.client.Minecraft;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.util.StringUtils;

import java.util.LinkedHashMap;

public class ScoreboardUtils {
    public static String getUnformattedLineFromScoreboard(int lineNumber) {
        Scoreboard scoreboard = getScoreboard();
        if (scoreboard == null) {
            return "";
        }
        ScorePlayerTeam requestedTeam;
        try {
            requestedTeam = getScoreboard().getTeam("team_" + lineNumber);
        } catch (NullPointerException e) {
            return "";
        }
        if (requestedTeam == null) {
            return "";
        }
        String colorPrefix = requestedTeam.getColorPrefix();
        String colorSuffix = requestedTeam.getColorSuffix();
        return StringUtils.stripControlCodes(colorPrefix + colorSuffix);
    }

    public static String getGameNumberRow() {
        return getUnformattedLineFromScoreboard(4);
    }

    private static Scoreboard getScoreboard() {
        if (Minecraft.getMinecraft() == null || Minecraft.getMinecraft().theWorld == null) {
            return null;
        } else {
            return Minecraft.getMinecraft().theWorld.getScoreboard();
        }
    }

    public static String getScoreboardTitle() {
        Scoreboard scoreboard = getScoreboard();
        if(scoreboard == null) {
            return "";
        }
        ScoreObjective scoreObjective = scoreboard.getObjectiveInDisplaySlot(1);
        if(scoreObjective == null) {
            return "";
        }
        return scoreboard.getObjectiveInDisplaySlot(1).getName();
    }

    public static LinkedHashMap<String, Integer> getScoreRows() {
        LinkedHashMap<String, Integer> returnHashMap = new LinkedHashMap<>();
        for(int i = 10; i > 7; i--) {
            String currentLine = getUnformattedLineFromScoreboard(i);
            if(currentLine.length() == 0) {
                continue;
            }
            char possibleStarCharacter = currentLine.charAt(currentLine.length()-1);
            if(!Character.isDigit(possibleStarCharacter)) {
                System.out.println((int)(possibleStarCharacter));
            }
        }
        return returnHashMap;
    }
}
