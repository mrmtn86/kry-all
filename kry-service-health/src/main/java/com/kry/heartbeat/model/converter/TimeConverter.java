package com.kry.heartbeat.model.converter;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class TimeConverter {
    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd hh:mm:ss";

    public static String toString(Instant time) {
        if (time == null) {
            return null;
        }
        ZonedDateTime datetime = ZonedDateTime.ofInstant(time, ZoneOffset.UTC);
        return DateTimeFormatter.ofPattern(YYYY_MM_DD_HH_MM_SS).format(datetime);
    }
}
