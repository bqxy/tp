package utils;

import java.util.HashMap;
import java.util.regex.Matcher;

public final class InputParser {
    protected Matcher matcher;
    public InputParser() {
    }

    /**
     * Example input: deadline do homework /by tomorrow /note skip page 70
     * commandString: "deadline"
     * CommandPacket created:
     * {
     *  commandType: ADD_DEADLINE
     *  commandContent: "do homework"
     *  params: HashMap<String, String>
     *  {
     *   "by": "tomorrow"
     *   "note": "skip page 70"
     *  }
     * }
     */
    public CommandPacket parseInput(String input) {
        String commandContent = "";
        String commandString = "";
        String restOfCommand;
        CommandPacket packet;
        HashMap<String, String> params = new HashMap<>();
        String[] buffer = null;

        boolean paramsExist = false;
        boolean commandContentExist = false;

        //Split into [command, rest of input]


        //Check for existence of command title
        //commandContentExist = buffer.length > 1 && !(buffer[1].startsWith("/") || buffer[1].startsWith("-"));
        for(String i : Constants.DEFAULT_PARAMS_PREFIX) {
            commandContentExist = input.contains(i);
            if (commandContentExist) {
                buffer = input.split(i, 2);
                buffer[1] = i + buffer[1];
                commandString = buffer[0];
                break;
            }
        }
        if(commandContentExist) {
            String paramSubstring = buffer[1];
            params = new ParamsParser(paramSubstring).parseParams();
        } else {
            commandString = input;
        }
        return new CommandPacket(commandString, params);
    }

    /**
     * Parse strings to produce date or time depending on mode specified in arguments.
     * @param input
     * @param mode
     * @return Formatted date or time for parsing with the DateTime class.
     */
    public static String parseDateTime(String input, final String mode) {
        String partition = mode.equals("time") ? ":":
                mode.equals("date") ? "-":
                        "?";
        String[] output = new String[3];
        if (input.matches("[0-9]{4}") && mode.equals("time")) {
            // If input time format is XXXX, replace with XX:XX:00.
            input = input.substring(0,2) + ","+ input.substring(2) + ",00";
        } else if (input.matches("[0-9]{6}") && mode.equals("date")) {
            // If input date format is XXXXXX, replace with XX,XX,XX
            input = input.substring(0,2) + ","+ input.substring(2, 4) + "," + input.substring(4);
        }
        // Replaces on no-digit numbers with the same string "c" such that they can be manipulated easily regardless of string.
        input = input.replaceAll("[\\D]+", "c");
        String[] tokens = input.split("c");
        for(int i = 0; i < output.length; i++) {
            if (i >  tokens.length-1) {
                // If subsequent pair of digits is undefined by simplified input, set to 0.
                output[i] = "00";
            } else if (tokens[i].length() < 2) {
                // If token is 1 or 0 digits long, fill the remaining space with 0 such that a 2 digit number is formed.
                output[i] = "0" + tokens[i];
            } else if (i == 0 && tokens[i].matches("[0-9]{2}") && mode.equals("date")) {
                // If year given is YY
                output[i] = "20" + tokens[i].substring(0,2);
            } else {
                output[i] = tokens[i];
            }
        }
        return String.join(partition, output);
    }
}