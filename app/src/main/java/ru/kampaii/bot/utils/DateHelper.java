package ru.kampaii.bot.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateHelper {

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");

    public static LocalDateTime getDateTime(String input){
        return LocalDateTime.parse(input,formatter);
    }
}
