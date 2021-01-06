package com.homework.testassignment.main.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Utils {

    public static LocalDateTime computeRequestDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
        LocalDate localDate = LocalDate.parse(date, formatter);
        return localDate.atStartOfDay();
    }

    public static LocalDateTime computeDBDate(String date) {
        LocalDateTime dateTime = LocalDateTime.parse(date);
        return dateTime;
    }

    public static boolean isRenewable(String renewable) {
        return renewable.equalsIgnoreCase(Constants.YES);
    }

    public static boolean isNullOrEmpty(String text) {
        return text == null || text.isEmpty();
    }
}
