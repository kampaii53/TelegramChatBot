package ru.kampaii.bot.data.utils;


import org.junit.Assert;
import org.junit.Test;

public class DateHelperTest {

    @Test
    public void testConvertation(){
        String input = "02.02.2020";

        Assert.assertNotNull(DateHelper.getDate(input));
    }

    @Test
    public void testParseUrl(){
        String url = "https://docs.google.com/spreadsheets/d/1lwQDTx_DQPogr_bgBuPbD8Yqtk551Kkn-LKXPAUx7fc/edit#gid=614266638";

        url = url.replace("https://docs.google.com/spreadsheets/d/","");

        url = url.substring(0,url.indexOf("/edit"));

        System.out.println(url);
    }
}
