package seedu.financeit.recurringtracker;

import seedu.financeit.common.CommandPacket;
import seedu.financeit.common.Constants;
import seedu.financeit.common.Item;
import seedu.financeit.common.exceptions.ConflictingItemReference;
import seedu.financeit.common.exceptions.InsufficientParamsException;
import seedu.financeit.common.exceptions.ItemNotFoundException;
import seedu.financeit.common.exceptions.ParseFailParamException;
import seedu.financeit.ui.UiManager;

import java.time.Month;
import java.util.ArrayList;

import static seedu.financeit.utils.ParamChecker.*;

public class RecurringEntry extends Item {
    private int day;
    private String description = "";
    private Constants.EntryType entryType;
    private double amount;
    private Month start = Month.of(1);
    private Month end  = Month.of(12);
    private boolean auto = false;
    private String notes = "";


    public RecurringEntry() {
        requiredParams = new ArrayList<>() {
            {
                add("/day");
                add("/desc");
                add("/amt");
            }
        };
    }

    public RecurringEntry(CommandPacket packet) throws AssertionError, InsufficientParamsException {
        this();
        try {
            handleParams(packet);
        } catch (ItemNotFoundException | ConflictingItemReference exception) {
            // Fall-through
        }
    }

    @Override
    public void handleSingleParam(CommandPacket packet, String paramType) throws ParseFailParamException {
        boolean validParam = true;
        switch (paramType) {
        case PARAM_DAY:
            day = paramChecker.checkAndReturnInt(paramType);
            break;
        case PARAM_AMOUNT:
            amount = paramChecker.checkAndReturnInt(paramType);
            break;
        case PARAM_INC:
            entryType = Constants.EntryType.INC;
            break;
        case PARAM_EXP:
            entryType = Constants.EntryType.EXP;
            break;
        case PARAM_DESCRIPTION:
            description = packet.getParam(paramType);
            break;
        case PARAM_AUTO:
            auto = true;
            break;
        case PARAM_NOTES:
            notes = packet.getParam(paramType);
            break;
        default:
            validParam = false;
            UiManager.printWithStatusIcon(Constants.PrintType.ERROR_MESSAGE,
                    paramChecker.getUnrecognizedParamMessage(paramType));
        }
        if (validParam) {
            parseSuccessParams.add(paramType);
        }
    }

    @Override
    public String getName() {
        return String.format("Entry: [ %s ] on day [ %s ] ",
                description, day);
    }

    public int getDay() {
        return day;
    }

    @Override
    public boolean isValidItem() {
        return true;
    }

    @Override
    public String toString() {
        String expenditureAmount = this.entryType == Constants.EntryType.EXP ? "-$" + this.amount : "";
        String incomeAmount = this.entryType == Constants.EntryType.INC ? "+$" + this.amount : "";
        String duration;
        if (this.start.getValue() == 1 && this.end.getValue() == 12) {
            duration = "Every month";
        } else {
            duration = start + " to " + end;
        }
        String payment = this.auto ? "Auto deduction" : "Manual payment";
        return String.format("%s;%s;%s;%s;%s;%s;%s", this.day, this.description, expenditureAmount, incomeAmount,
                duration, payment, this.notes);
    }
}
