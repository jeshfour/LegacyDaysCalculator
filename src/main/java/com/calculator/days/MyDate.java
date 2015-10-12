package com.calculator.days;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Integer.parseInt;

public class MyDate implements Comparable {
    private final int day;
    private final int month;
    private final int year;

    public static final Map DAYS_MONTHS_MAPPING;
    public static final List LEAP_YEARS;

    static {
        LEAP_YEARS = new ArrayList<Integer>(); // build leap years between 1900 and 3000
        for (int year = 1900; year < 3000; year = 4 + year) {
            if ((year % 4 == 0) && (year % 100 != 0) || (year % 400 == 0)) {
                LEAP_YEARS.add(year);
            }
        }
        DAYS_MONTHS_MAPPING = new HashMap<Integer, Integer>() { // add days for each month
            {
                put(1, 31);
                put(2, 28);
                put(3, 31);
                put(4, 30);
                put(5, 31);
                put(6, 30);
                put(7, 31);
                put(8, 31);
                put(9, 30);
                put(10, 31);
                put(11, 30);
                put(12, 31);
            }
        };
    }

    public MyDate(final String dateString) {
        final String[] dateArray = dateString.split("/");
        this.day = parseInt(dateArray[0]);
        this.month = parseInt(dateArray[1]);
        this.year = parseInt(dateArray[2]);
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public boolean isAfterFeb() {
        return (day > 28 && month == 2) || month > 2;
    }

    boolean isValid() {
        if (year < 1901 || year > 2999) {
            return false;
        }
        if (month < 1 || month > 12) {
            return false;
        }
        final int dayForMonth = (Integer) DAYS_MONTHS_MAPPING.get(month);
        return !(day < 1 || day > 31 || day > dayForMonth);
    }

    public static MyDate parse(final String date) throws ParseException {
        try {
            final MyDate myDate = new MyDate(date);
            if (!myDate.isValid()) {
                throw new Exception();
            }
            return myDate;
        } catch (final Exception e) {
            throw new ParseException("Date is invalid, enter a valid date between 1/1/1901, 31/12/2999", 0);
        }
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof MyDate)) return false;
        final MyDate that = (MyDate) o;
        return day == that.day && month == that.month && year == that.year;
    }

    @Override
    public int hashCode() {
        int result = day;
        result = 31 * result + month;
        result = 31 * result + year;
        return result;
    }

    @Override
    public int compareTo(final Object o) {
        if (this == o) return 0;
        if (!(o instanceof MyDate)) return 1;
        final MyDate that = (MyDate) o;
        if (that.year > this.year) return -1;
        else if (this.year > that.year) return 1;
        else if (this.month < that.month) return -1;
        else if (that.month < this.month) return 1;
        else if (this.day < that.day) return -1;
        return 1;
    }
}
