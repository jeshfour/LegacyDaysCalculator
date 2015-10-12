package com.calculator.days;

import java.text.ParseException;

import static com.calculator.days.CustomDate.parse;
import static com.calculator.days.DateHelper.getDaysBetween;

public class LegacyDaysCalculator {
    private String startDateStr;
    private String endDateStr;

    public LegacyDaysCalculator(final String startDateStr, final String endDateStr) {
        this.startDateStr = startDateStr;
        this.endDateStr = endDateStr;
    }

    public long getDays() throws ParseException {
        if (startDateStr == null || endDateStr == null) {
            throw new IllegalArgumentException("Input is not valid: " + startDateStr + ", " + endDateStr);
        }
        final CustomDate startDate = parse(startDateStr);
        final CustomDate endDate = parse(endDateStr);
        return getDaysBetween(startDate, endDate);
    }
}
