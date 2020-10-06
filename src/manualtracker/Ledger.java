package manualtracker;

import manualtracker.Entry;

import java.time.LocalDate;
import java.util.ArrayList;

public class Ledger {
    ArrayList<Entry> entryList = new ArrayList<>();
    LocalDate date;
    public Ledger(String rawDate){
        LocalDate date = LocalDate.parse(utils.InputParser.parseDateTime(rawDate, "date"));
        this.date = date;
    }

    public void addEntry(Entry entry){
        this.entryList.add(entry);
    }

    public String getDate(){
        return this.date.getDayOfWeek().toString();
    }
}
