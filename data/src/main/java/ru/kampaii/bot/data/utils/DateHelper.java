package ru.kampaii.bot.data.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateHelper {

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public static LocalDate getDate(String input){
        return LocalDate.parse(input,formatter);
    }
}
