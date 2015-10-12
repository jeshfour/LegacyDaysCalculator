package com.calculator.days;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Integer.parseInt;

public class CustomDate {
    private int day;
    private int month;
    private int year;

    public static final Map DAYS_FOR_MONTH_MAPPING;

    static {
        DAYS_FOR_MONTH_MAPPING = new HashMap<Integer, Integer>() { // add days for each month
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

    public CustomDate(final String dateString) {
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

    public boolean isValid() {
        if (year < 1900 || year > 2999) {
            return false;
        }
        if (month < 1 || month > 12) {
            return false;
        }
        final int dayForMonth = (Integer) DAYS_FOR_MONTH_MAPPING.get(month);
        if (day < 1 || day > 31 || day > dayForMonth) {
            return false;
        }
        return true;
    }

    public static CustomDate parse(final String date) throws ParseException {
        try {
            final CustomDate customDate = new CustomDate(date);
            if (!customDate.isValid()) {
                throw new Exception();
            }
            return customDate;
        } catch (final Exception e) {
            throw new ParseException("Date is invalid, enter a valid date between 1/1/1900, 31/12/2999", 0);
        }
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof CustomDate)) return false;

        final CustomDate that = (CustomDate) o;

        if (day != that.day) return false;
        if (month != that.month) return false;
        if (year != that.year) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = day;
        result = 31 * result + month;
        result = 31 * result + year;
        return result;
    }
}
