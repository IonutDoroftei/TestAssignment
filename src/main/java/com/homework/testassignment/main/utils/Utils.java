package com.homework.testassignment.main.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class Utils {
    public static final String YES = "YES";
    public static final String DATE_FORMAT = "M/d/yyyy";

    public static Long computeRequestDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        LocalDate localDate = LocalDate.parse(date, formatter);
        return localDate.atStartOfDay(ZoneOffset.UTC).toInstant().toEpochMilli() / 1000;
    }

    public static Long formatDate(LocalDateTime date) {
        return date.atZone(ZoneOffset.UTC).toEpochSecond();
    }

    public static boolean isRenewable(String renewable) {
        return renewable.equalsIgnoreCase(YES);
    }
}
