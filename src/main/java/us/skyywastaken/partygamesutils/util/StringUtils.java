package us.skyywastaken.partygamesutils.util;

import java.util.ArrayList;
import java.util.List;

public class StringUtils {
    public static List<String> getPartialMatches(String partialString, Iterable<String> originalStrings) {
        ArrayList<String> matches = new ArrayList<>();
        for(String possibleMatch : originalStrings) {
            if(possibleMatch.toLowerCase().startsWith(partialString.toLowerCase())) {
                matches.add(possibleMatch);
            }
        }
        return matches;
    }
}
