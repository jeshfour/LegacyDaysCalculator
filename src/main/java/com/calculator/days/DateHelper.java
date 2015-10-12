package com.calculator.days;

import java.util.ArrayList;
import java.util.List;

import static com.calculator.days.CustomDate.DAYS_FOR_MONTH_MAPPING;

public class DateHelper {

    private static final List leapYears;

    static {
        leapYears = new ArrayList<Integer>(); // build leap years between 1900 and 3000
        for (int year = 1900; year < 3000; year = 4 + year) {
            if ((year % 4 == 0) && (year % 100 != 0) || (year % 400 == 0)) {
                leapYears.add(year);
            }
        }


    }

    public static long getDaysBetween(final CustomDate startDate, final CustomDate endDate) {
        if (startDate.equals(endDate)) {
            return 0l;
        }
        if (startDate.getYear() > endDate.getYear()) {
            return (getDaysFor(startDate) - getDaysFor(endDate) - 1) + getAdditionalLeapYearDays(endDate, startDate);
        }
        return (getDaysFor(endDate) - getDaysFor(startDate) - 1) + getAdditionalLeapYearDays(startDate, endDate);
    }

    /**
     * Calculates the number of leap years between two
     * dates to offset the additional days
     *
     * @param startDate
     * @param endDate
     * @return
     */
    private static int getAdditionalLeapYearDays(final CustomDate startDate,
                                                 final CustomDate endDate) {
        int i = 0;
        final int offsetStart = startDate.isAfterFeb() ? 1 : 0;
        final int offsetEnd = endDate.isAfterFeb() ? 1 : 0;
        final int startYearValue = offsetStart + startDate.getYear();
        final int endYearValue = offsetEnd + endDate.getYear();
        for (int year = startYearValue; year < endYearValue; year++) {
            i = i + (leapYears.contains(year) ? 1 : 0);
        }
        return i;
    }

    /**
     * Gets days starting since 1/1/1900
     *
     * @param date
     * @return
     */
    private static long getDaysFor(final CustomDate date) {
        long days = 0l;
        for (int i = 1900; i < date.getYear(); i++) {
            days = days + 365;
        }
        return days + getDaysForMonthAndDate(date.getMonth(), date.getDay());
    }

    /**
     * calculates days for months and adds the
     * last month days to it before it returns
     *
     * @param month
     * @param days
     * @return
     */
    private static long getDaysForMonthAndDate(final int month,
                                               final int days) {
        long totalDays = 0l;
        for (int i = 1; i < month; i++) {
            totalDays = totalDays + (Integer) DAYS_FOR_MONTH_MAPPING.get(i);
        }
        return totalDays + days;
    }
}
