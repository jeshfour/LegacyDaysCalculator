package com.calculator.days;

import org.junit.Test;

import static com.calculator.days.DateHelper.getDaysBetween;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class DateHelperTest {

    @Test
    public void shouldReturnDaysBetweenTwoDates() throws Exception {
        assertThat(getDaysBetween(new MyDate("01/01/1983"), new MyDate("02/01/1983")), is(0l));
        assertThat(getDaysBetween(new MyDate("02/06/1983"), new MyDate("22/06/1983")), is(19l));
        assertThat(getDaysBetween(new MyDate("30/10/2015"), new MyDate("01/11/2015")), is(1l));
        assertThat(getDaysBetween(new MyDate("27/02/2015"), new MyDate("01/03/2015")), is(1l));
        assertThat(getDaysBetween(new MyDate("04/07/1984"), new MyDate("25/12/1984")), is(173l));
        assertThat(getDaysBetween(new MyDate("03/08/1983"), new MyDate("03/01/1989")), is(1979l));
        assertThat(getDaysBetween(new MyDate("01/01/1901"), new MyDate("31/12/1999")), is(36157l));
        assertThat(getDaysBetween(new MyDate("01/01/1901"), new MyDate("31/12/2999")), is(401400l));
        assertThat(getDaysBetween(new MyDate("01/01/1901"), new MyDate("31/12/2999")), is(401400l));
        assertThat(getDaysBetween(new MyDate("31/12/2999"), new MyDate("01/01/1901")), is(401400l));
    }
}