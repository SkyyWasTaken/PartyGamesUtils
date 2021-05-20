package us.skyywastaken.partygamesutils.util;

import net.minecraft.client.Minecraft;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.scoreboard.Scoreboard;

import java.util.Collection;
import java.util.List;

public class ScoreboardUtils {
    public static String getLineFromScoreboard(int lineNumber) {
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
        if (requestedTeam == null) return "garbage string";
        String colorPrefix = requestedTeam.getColorPrefix();
        String colorSuffix = requestedTeam.getColorSuffix();
        return colorPrefix + colorSuffix;
    }

    public static String getNoPrefixGameName() {
        Scoreboard scoreboard = getScoreboard();
        if (scoreboard == null) return "";
        ScorePlayerTeam requestedTeam;
        try {
            requestedTeam = getScoreboard().getTeam("team_12");
        } catch (NullPointerException e) {
            return "";
        }
        if (requestedTeam == null) return "";
        String basePrefixString = requestedTeam.getColorPrefix();
        String prefixString;
        if (basePrefixString.length() > 2) {
            prefixString = basePrefixString.substring(2);
        } else {
            prefixString = basePrefixString;
        }
        String baseSuffixString = requestedTeam.getColorSuffix();
        String suffixString;
        if (baseSuffixString.length() > 2) {
            suffixString = baseSuffixString.substring(2);
        } else {
            suffixString = baseSuffixString;
        }
        return prefixString + suffixString;
    }

    public static String getGameNumberRow() {
        return getLineFromScoreboard(4);
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
}
