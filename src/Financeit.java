import manualtracker.ManualTracker;
import utils.InputParser;

public class Financeit {
    public static void main(String[] args){
        InputParser inputParser = new InputParser();
        // String inputString = "mom /m mom -d dad /s son /d daughter";
        String inputString = "mom";
        System.out.println(inputParser.parseInput(inputString));
        String[][] input = {
                {"Name", "Age", "Place", "letters"},
                {"John", "15", "Woodlands", "g"},
                {"Mary", "18", "Johore", "l"}
        };
        String[][] input1 = {
                {"Name"},
                {"John"},
                {"Mary"}
        };

        utils.UiManager.printList(input1);
        ManualTracker.main();
    }
}

// This prints a table in case we need it
