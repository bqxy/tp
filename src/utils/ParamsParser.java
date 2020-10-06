package utils;
import java.util.HashMap;
import java.util.regex.Matcher;

public class ParamsParser {
    protected String paramSubstring;
    protected Matcher matcher;
    public ParamsParser(String paramSubstring) {
        this.paramSubstring = paramSubstring;
    }

    /**
     * Example input: deadline do homework /by tomorrow /note skip page 70
     * --First iteration--
     * paramSubstring: "by tomorrow /note skip page 70"
     * paramType: "by"
     * paramArgument: "tomorrow"
     * --Next iteration--
     * paramSubstring: "note skip page 70"
     * paramType: "note"
     * paramArgument: "skip page 70"
     * etc.
     */
    public HashMap<String, String> parseParams() {
        HashMap<String, String> params = new HashMap<>();
        String[] buffer;
        boolean shouldContinueParsing = true;
        paramSubstring += " /end";
        do {
            //Separate into [paramType, rest of string]
            buffer = paramSubstring.trim().split(" ", 2);
            String paramType = buffer[0];
            boolean paramArgumentExist = buffer.length > 1;

            if (buffer[0].trim().equals("/end")){
                break;
            }
            paramSubstring = buffer[1];
            matcher = RegexMatcher.regexMatcher(paramSubstring, Constants.paramRegex);
            //Separate into [paramArgument, rest of string]

            String separator = String.valueOf(paramSubstring.charAt(matcher.start()));
            buffer = paramSubstring.split(separator, 2);
            buffer[1] = separator + buffer[1];
            String paramArgument = buffer[0];
            boolean nextParamExist = buffer.length > 1;
            paramSubstring = buffer[1];
            params.put(paramType, paramArgument);
        } while(shouldContinueParsing);
        return params;
    }
}