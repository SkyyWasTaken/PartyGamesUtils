package us.skyywastaken.partygamesutils.util;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.util.StringUtils;

public class ScoreboardUtils {
    public static String getLineFromScoreboard(int lineNumber) {
        Scoreboard scoreboard = getScoreboard();
        if(scoreboard == null) return "garbage string";
        ScorePlayerTeam requestedTeam = getScoreboard().getTeam("team_" + lineNumber);
        if(requestedTeam == null) return "garbage string";
        String colorPrefix = requestedTeam.getColorPrefix();
        String colorSuffix = requestedTeam.getColorSuffix();
        return colorPrefix + colorSuffix;
    }

    public static String getNoPrefixGameName() {
        Scoreboard scoreboard = getScoreboard();
        if(scoreboard == null) return "";
        ScorePlayerTeam requestedTeam = getScoreboard().getTeam("team_12");
        if(requestedTeam == null) return "";
        String basePrefixString = requestedTeam.getColorPrefix();
        String prefixString;
        if(basePrefixString.length() > 2) {
            prefixString = basePrefixString.substring(2);
        } else {
            prefixString = basePrefixString;
        }
        String baseSuffixString = requestedTeam.getColorSuffix();
        String suffixString;
        if(baseSuffixString.length() > 2) {
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
        if(Minecraft.getMinecraft() == null || Minecraft.getMinecraft().theWorld == null)  {
            return null;
        }else {
            return Minecraft.getMinecraft().theWorld.getScoreboard();
        }
    }
}
