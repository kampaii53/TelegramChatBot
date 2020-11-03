package ru.kampaii.bot.data.utils;


import org.junit.Assert;
import org.junit.Test;

public class DateHelperTest {

    @Test
    public void testConvertation(){
        String input = "02.02.2020";

        Assert.assertNotNull(DateHelper.getDate(input));
    }
}
