package com.kangaroo.backup.Utils;

import java.util.Set;

public class MatchUriUtils {
    public static boolean isMatchedUri(String uri, Set<String> matchSet) {
        for(String greenUri : matchSet) {
            if(uri.matches(greenUri)) {
                return true;
            }
        }
        return false;
    }
}
