package utils;

import java.util.ArrayList;
import java.util.Arrays;

public class Printer {
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

}
