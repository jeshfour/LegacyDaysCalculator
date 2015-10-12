package com.calculator.days;

import org.junit.Test;

import java.text.ParseException;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Tests LegacyDaysCalculator and CustomDate classes here
 */
public class LegacyDaysCalculatorTest {

    @Test
    public void shouldCalculateDaysBetweenStartAndEndDates() throws Exception {

        //CASE 1
        String startDate = "01/01/1983";
        String endDate = "02/01/1983";
        LegacyDaysCalculator legacyDaysCalculator = new LegacyDaysCalculator(startDate, endDate);
        assertThat(legacyDaysCalculator.getDays(), is(0l));

        //CASE 2
        startDate = "02/06/1983";
        endDate = "22/06/1983";
        legacyDaysCalculator = new LegacyDaysCalculator(startDate, endDate);
        assertThat(legacyDaysCalculator.getDays(), is(19l));

        //CASE 3
        startDate = "30/10/2015";
        endDate = "01/11/2015";
        legacyDaysCalculator = new LegacyDaysCalculator(startDate, endDate);
        assertThat(legacyDaysCalculator.getDays(), is(1l));

        //CASE 4
        startDate = "27/02/2015";
        endDate = "01/03/2015";
        legacyDaysCalculator = new LegacyDaysCalculator(startDate, endDate);
        assertThat(legacyDaysCalculator.getDays(), is(1l));

        //CASE 5
        startDate = "09/03/2015";
        endDate = "09/03/2015";
        legacyDaysCalculator = new LegacyDaysCalculator(startDate, endDate);
        assertThat(legacyDaysCalculator.getDays(), is(0l));

        //CASE 6
        startDate = "04/07/1984";
        endDate = "25/12/1984";
        legacyDaysCalculator = new LegacyDaysCalculator(startDate, endDate);
        assertThat(legacyDaysCalculator.getDays(), is(173l));

        //CASE 7
        startDate = "03/01/1989";
        endDate = "03/08/1983";
        legacyDaysCalculator = new LegacyDaysCalculator(startDate, endDate);
        assertThat(legacyDaysCalculator.getDays(), is(1979l));

        //CASE 8
        startDate = "01/01/1901";
        endDate = "31/12/1999";
        legacyDaysCalculator = new LegacyDaysCalculator(startDate, endDate);
        assertThat(legacyDaysCalculator.getDays(), is(36157L));

        //CASE 9
        startDate = "01/01/1901";
        endDate = "31/12/2999";
        legacyDaysCalculator = new LegacyDaysCalculator(startDate, endDate);
        assertThat(legacyDaysCalculator.getDays(), is(401400L));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionIfStartDateIsNull() throws Exception {
        final String endDate = "02/01/1983";
        for (final String startDate : asList(null, "", " ")) {
            final LegacyDaysCalculator legacyDaysCalculator = new LegacyDaysCalculator(startDate, endDate);
            legacyDaysCalculator.getDays();
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionIfEndDateIsNull() throws Exception {
        final String startDate = "02/01/1983";
        for (final String endDate : asList(null, "", " ")) {
            final LegacyDaysCalculator legacyDaysCalculator = new LegacyDaysCalculator(startDate, endDate);
            legacyDaysCalculator.getDays();
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionIfBothDatesAreNull() throws Exception {
        for (final String startDate : asList(null, "", " ")) {
            for (final String endDate : asList(null, "", " ")) {
                final LegacyDaysCalculator legacyDaysCalculator = new LegacyDaysCalculator(startDate, endDate);
                legacyDaysCalculator.getDays();
            }
        }
    }

    @Test(expected = ParseException.class)
    public void shouldThrowParseExceptionIfStartDateIsInvalid() throws Exception {
        final String endDate = "02/03/1983";
        for (final String startDate : asList("02//1983", "234234234", "sdfer34324234/34234/dsad")) {
            final LegacyDaysCalculator legacyDaysCalculator = new LegacyDaysCalculator(startDate, endDate);
            legacyDaysCalculator.getDays();
        }
    }

    @Test(expected = ParseException.class)
    public void shouldThrowParseExceptionIfEndDateIsInvalid() throws Exception {
        final String startDate = "02/03/1983";
        for (final String endDate : asList("02//1983", "234234234", "sdfer34324234/34234/dsad")) {
            final LegacyDaysCalculator legacyDaysCalculator = new LegacyDaysCalculator(startDate, endDate);
            legacyDaysCalculator.getDays();
        }
    }

    @Test(expected = ParseException.class)
    public void shouldThrowParseExceptionIfBothDatesAreInvalid() throws Exception {
        for (final String startDate : asList("02//1983", "234234234", "sdfer34324234/34234/dsad")) {
            for (final String endDate : asList("02//1983", "234234234", "sdfer34324234/34234/dsad")) {
                final LegacyDaysCalculator legacyDaysCalculator = new LegacyDaysCalculator(startDate, endDate);
                legacyDaysCalculator.getDays();
            }
        }
    }

    @Test(expected = ParseException.class)
    public void shouldThrowParseExceptionIfStartDateIsOutOfRange() throws Exception {
        final String startDate = "02/03/1800";
        final String endDate = "02/03/1910";
        final LegacyDaysCalculator legacyDaysCalculator = new LegacyDaysCalculator(startDate, endDate);
        legacyDaysCalculator.getDays();
    }

    @Test(expected = ParseException.class)
    public void shouldThrowParseExceptionIfEndDateIsOutOfRange() throws Exception {
        final String startDate = "02/03/1983";
        final String endDate = "02/03/3010";
        final LegacyDaysCalculator legacyDaysCalculator = new LegacyDaysCalculator(startDate, endDate);
        legacyDaysCalculator.getDays();
    }

    @Test(expected = ParseException.class)
    public void shouldThrowParseExceptionIfBothDatesAreOutOfRange() throws Exception {
        for (final String startDate : asList("02/03/1800", "31/12/1899", "31/12/1900", "1/1/3000")) {
            for (final String endDate : asList("02/03/1800", "31/12/1899", "31/12/1900", "1/1/3000")) {
                final LegacyDaysCalculator legacyDaysCalculator = new LegacyDaysCalculator(startDate, endDate);
                legacyDaysCalculator.getDays();
            }
        }
    }

    @Test(expected = ParseException.class)
    public void shouldThrowParseExceptionIfStartDateDoesNotExistOnTheCalendar() throws Exception {
        final String startDate = "31/02/2010";
        final String endDate = "02/03/1910";
        final LegacyDaysCalculator legacyDaysCalculator = new LegacyDaysCalculator(startDate, endDate);
        legacyDaysCalculator.getDays();
    }

    @Test(expected = ParseException.class)
    public void shouldThrowParseExceptionIfEndDateDoesNotExistOnTheCalendar() throws Exception {
        final String startDate = "02/03/1983";
        final String endDate = "31/02/2010";
        final LegacyDaysCalculator legacyDaysCalculator = new LegacyDaysCalculator(startDate, endDate);
        legacyDaysCalculator.getDays();
    }

    @Test(expected = ParseException.class)
    public void shouldThrowParseExceptionIfBothDatesDoNotExistOnTheCalendar() throws Exception {
        final String startDate = "31/02/2018";
        final String endDate = "31/02/2010";
        final LegacyDaysCalculator legacyDaysCalculator = new LegacyDaysCalculator(startDate, endDate);
        legacyDaysCalculator.getDays();
    }
}