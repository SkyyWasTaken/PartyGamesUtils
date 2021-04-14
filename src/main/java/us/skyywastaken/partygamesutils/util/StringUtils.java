package us.skyywastaken.partygamesutils.util;

import net.minecraft.util.EnumChatFormatting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StringUtils {
    public static List<String> getPartialMatches(String partialString, Iterable<String> originalStrings) {
        ArrayList<String> matches = new ArrayList<>();
        for (String possibleMatch : originalStrings) {
            if (possibleMatch.toLowerCase().startsWith(partialString.toLowerCase())) {
                matches.add(possibleMatch);
            }
        }
        Collections.sort(matches);
        return matches;
    }

    public static String getEnabledDisabledString(boolean isEnabled) {
        if (isEnabled) {
            return EnumChatFormatting.AQUA + "Enabled";
        } else {
            return EnumChatFormatting.DARK_RED + "Disabled";
        }
    }

    public static String getColorlessEnabledDisabledString(boolean isEnabled) {
        if (isEnabled) {
            return "Enabled";
        } else {
            return "Disabled";
        }
    }
}
