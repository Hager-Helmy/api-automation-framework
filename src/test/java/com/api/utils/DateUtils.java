package com.api.utils;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import static org.testng.Assert.assertTrue;


public class DateUtils {

    public static void validateCreatedAtDate(String createdAt){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_DATE_TIME;

        ZonedDateTime dateTime = ZonedDateTime.parse(createdAt, dateTimeFormatter);
        ZonedDateTime now = ZonedDateTime.now();

        assertTrue(
                dateTime.isAfter(now.minusMinutes(1)) && dateTime.isBefore(now.plusMinutes(1))
        );
        System.out.println("createdAt is valid " + dateTime);
    }
}
