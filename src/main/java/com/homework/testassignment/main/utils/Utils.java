package com.homework.testassignment.main.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Utils {
    public static final String DATE_FORMAT = "M/d/yyyy";

    public static LocalDateTime computeRequestDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        LocalDate localDate = LocalDate.parse(date, formatter);
        return localDate.atStartOfDay();
    }

    public static String formatDate(LocalDateTime date) {
        return DateTimeFormatter.ofPattern(DATE_FORMAT).format(date);
    }

    public static boolean isRenewable(String renewable) {
        return renewable.equalsIgnoreCase(Constants.YES);
    }

    public static boolean isNullOrEmpty(String text) {
        return text == null || text.isEmpty();
    }
}
