package com.feedback.dto.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

public class DateTimeUtil {
    
    // Formatters
    public static final DateTimeFormatter ISO_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
    public static final DateTimeFormatter DISPLAY_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");
    
    // Private constructor to prevent instantiation
    private DateTimeUtil() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }
    
    /**
     * Get current date and time as LocalDateTime
     */
    public static LocalDateTime now() {
        return LocalDateTime.now();
    }
    
    /**
     * Format LocalDateTime to ISO string
     */
    public static String toIsoString(LocalDateTime dateTime) {
        if (dateTime == null) {
            return null;
        }
        return dateTime.format(ISO_FORMATTER);
    }
    
    /**
     * Format LocalDateTime to display string (dd-MM-yyyy HH:mm:ss)
     */
    public static String toDisplayString(LocalDateTime dateTime) {
        if (dateTime == null) {
            return null;
        }
        return dateTime.format(DISPLAY_FORMATTER);
    }
    
    /**
     * Format LocalDateTime to date only (dd-MM-yyyy)
     */
    public static String toDateString(LocalDateTime dateTime) {
        if (dateTime == null) {
            return null;
        }
        return dateTime.format(DATE_FORMATTER);
    }
    
    /**
     * Format LocalDateTime to time only (HH:mm:ss)
     */
    public static String toTimeString(LocalDateTime dateTime) {
        if (dateTime == null) {
            return null;
        }
        return dateTime.format(TIME_FORMATTER);
    }
    
    /**
     * Parse ISO string to LocalDateTime
     */
    public static LocalDateTime parseIsoString(String dateTimeStr) {
        if (dateTimeStr == null || dateTimeStr.isEmpty()) {
            return null;
        }
        try {
            return LocalDateTime.parse(dateTimeStr, ISO_FORMATTER);
        } catch (DateTimeParseException e) {
            return null;
        }
    }
    
    /**
     * Parse display string to LocalDateTime
     */
    public static LocalDateTime parseDisplayString(String dateTimeStr) {
        if (dateTimeStr == null || dateTimeStr.isEmpty()) {
            return null;
        }
        try {
            return LocalDateTime.parse(dateTimeStr, DISPLAY_FORMATTER);
        } catch (DateTimeParseException e) {
            return null;
        }
    }
    
    /**
     * Convert Date to LocalDateTime
     */
    public static LocalDateTime toLocalDateTime(Date date) {
        if (date == null) {
            return null;
        }
        return date.toInstant()
            .atZone(java.time.ZoneId.systemDefault())
            .toLocalDateTime();
    }
    
    /**
     * Convert LocalDateTime to Date
     */
    public static Date toDate(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            return null;
        }
        return Date.from(localDateTime.atZone(java.time.ZoneId.systemDefault()).toInstant());
    }
    
    /**
     * Get start of day (00:00:00)
     */
    public static LocalDateTime startOfDay(LocalDateTime dateTime) {
        if (dateTime == null) {
            return null;
        }
        return dateTime.withHour(0).withMinute(0).withSecond(0).withNano(0);
    }
    
    /**
     * Get end of day (23:59:59)
     */
    public static LocalDateTime endOfDay(LocalDateTime dateTime) {
        if (dateTime == null) {
            return null;
        }
        return dateTime.withHour(23).withMinute(59).withSecond(59).withNano(999999999);
    }
    
    /**
     * Check if date is today
     */
    public static boolean isToday(LocalDateTime dateTime) {
        if (dateTime == null) {
            return false;
        }
        LocalDateTime now = now();
        return dateTime.toLocalDate().equals(now.toLocalDate());
    }
    
    /**
     * Get days between two dates
     */
    public static long daysBetween(LocalDateTime start, LocalDateTime end) {
        if (start == null || end == null) {
            return 0;
        }
        return java.time.temporal.ChronoUnit.DAYS.between(start, end);
    }
    
    /**
     * Add days to date
     */
    public static LocalDateTime addDays(LocalDateTime dateTime, long days) {
        if (dateTime == null) {
            return null;
        }
        return dateTime.plusDays(days);
    }
    
    /**
     * Subtract days from date
     */
    public static LocalDateTime subtractDays(LocalDateTime dateTime, long days) {
        if (dateTime == null) {
            return null;
        }
        return dateTime.minusDays(days);
    }
    
    /**
     * Add hours to date
     */
    public static LocalDateTime addHours(LocalDateTime dateTime, long hours) {
        if (dateTime == null) {
            return null;
        }
        return dateTime.plusHours(hours);
    }
    
    /**
     * Get current timestamp as string (ISO format)
     */
    public static String getCurrentTimestamp() {
        return now().format(ISO_FORMATTER);
    }
    
    /**
     * Get current date as string (dd-MM-yyyy)
     */
    public static String getCurrentDate() {
        return now().format(DATE_FORMATTER);
    }
    
    /**
     * Get current time as string (HH:mm:ss)
     */
    public static String getCurrentTime() {
        return now().format(TIME_FORMATTER);
    }
    
    /**
     * Check if a date is valid (not null and not in the future for creation)
     */
    public static boolean isValidCreationDate(LocalDateTime dateTime) {
        return dateTime != null && !dateTime.isAfter(now());
    }
    
    /**
     * Format date with custom pattern
     */
    public static String formatWithPattern(LocalDateTime dateTime, String pattern) {
        if (dateTime == null || pattern == null || pattern.isEmpty()) {
            return null;
        }
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
            return dateTime.format(formatter);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}