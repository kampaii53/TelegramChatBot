package ru.kampaii.bot.utils;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class DateHelperTest {

    @Test
    public void testConvertation(){
        String input = "02.02.2020 01:00:00";

        assertNotNull(DateHelper.getDateTime(input));
    }
}
