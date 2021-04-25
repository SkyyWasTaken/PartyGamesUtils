package us.skyywastaken.partygamesutils.util;

import net.minecraft.util.EnumChatFormatting;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StringUtils {
    public static final String BODY_FORMATTING = EnumChatFormatting.GREEN + "";
    public static final String ACCENT_FORMATTING = EnumChatFormatting.AQUA + "";
    public static final String INFORMATION_FORMATTING = EnumChatFormatting.YELLOW + "";
    public static final String COMMAND_USAGE_FORMATTING = EnumChatFormatting.GRAY + "";
    public static final String WARNING_FORMATTING = EnumChatFormatting.RED + "";

    public static List<String> getPartialMatches(String partialString, Collection<String> originalStrings) {
        return originalStrings.stream()
                .filter(possibleMatch -> possibleMatch.toLowerCase().startsWith(partialString.toLowerCase()))
                .sorted()
                .collect(Collectors.toList());
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
