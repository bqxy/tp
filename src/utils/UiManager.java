package utils;

import java.util.ArrayList;
import java.util.Arrays;

public class UiManager {
    public static String returnLineWithSymbol(int width, String symbol){
        return new String(new char[width]).replace("\0", symbol);
    }
    public static void printLineWithSymbol(int width, String symbol){
        System.out.println(returnLineWithSymbol(width, symbol));
    }

    public static void drawPartition(){
        printLineWithSymbol(Constants.MAX_PARTITION_LINE_LEN, "_");
    }

    public static void printList(String[][] input){
        printRowHeader(input[0]);
        for (int i = 1; i < input.length; i++) {
            System.out.println(getPrintListRow(input[i]).replaceFirst("|", ""));
        }
    }

    public static String getPrintListRow(String[] input){
        ArrayList<String> buffer = new ArrayList<>();
        Arrays.stream(input)
                .forEach(s -> buffer.add(getPrintFormat(s)));
        return String.join("", buffer);
    }

    public static String getPrintFormat(String s){
        return String.format("| %-15s", s);
    }

    public static void printRowHeader(String[] row){
        String header = getPrintListRow(row);
        System.out.println(header);
        System.out.println(new String(new char[header.length()]).replace("", "-"));
    }

    public static void printInputPrompt(){
        drawPartition();
        System.out.println(">>> ");
    }
}
