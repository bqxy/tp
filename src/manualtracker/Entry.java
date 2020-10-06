package manualtracker;

import utils.Constants;
import utils.InputParser;
import utils.User;

import java.time.LocalTime;
import java.util.Arrays;

public class Entry {
    private String description;
    private String category;
    private Constants.EntryType entryType;
    private LocalTime time;
    private boolean isAuto;

    public Entry(){
    }

    public Entry(String description, String category, Constants.EntryType entryType, LocalTime time, boolean isAuto){
        this.description = description;
        this.category = category;
        this.entryType = entryType;
        this.time = time;
        this.isAuto = isAuto;
    }

    public boolean isValidCategory(String category, Constants.EntryType type){
        return type == Constants.EntryType.INC ? isInStringArray(Constants.DEFAULT_INC_CAT, category) || User.customCat.contains(category):
                isInStringArray(Constants.DEFAULT_EXP_CAT, category) || User.customCat.contains(category);

    }

    public boolean isInStringArray(String[] arr, String category){
        return Arrays.stream(arr).anyMatch(category::equals);
    }

    public void setDescription(String description){
        this.description = description;
    }

    public void setCategory(String category, Constants.EntryType type){
        if(isValidCategory(category, type)) {
            this.category = category;
        }
    }

    public void setEntryType(Constants.EntryType entryType){
        this.entryType = entryType;
    }

    public void setTime(String rawTime){
        LocalTime time = LocalTime.parse(InputParser.parseDateTime(rawTime, "time"));
        this.time = time;
    }

    public void setIsAuto(boolean isAuto){
        this.isAuto = isAuto;
    }
}
