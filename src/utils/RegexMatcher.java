package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexMatcher {
    public static Matcher regexMatcher(String inputString, String regex){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(inputString);
        matcher.find();
        return matcher;
    }
}
