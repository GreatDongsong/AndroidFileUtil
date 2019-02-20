package com.dawson.androidfileutil;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * create by Dawson.Gao
 * on 2018/12/28
 */
public class TimeUtil {
    private static final String DATE_FORMAT_24 = "HH:mm";

    public static int[] toGMT(int hour, int minute) {
        TimeZone srcTimeZone = TimeZone.getDefault();
        TimeZone destTimeZone = TimeZone.getTimeZone("GMT");

        return timeTransformBetweenTimeZone(hour, minute, srcTimeZone, destTimeZone);
    }

    public static int[] fromGMT(int hour, int minute) {
        TimeZone srcTimeZone = TimeZone.getTimeZone("GMT");
        TimeZone destTimeZone = TimeZone.getDefault();

        return timeTransformBetweenTimeZone(hour, minute, srcTimeZone, destTimeZone);
    }

    private static int[] timeTransformBetweenTimeZone(int hour, int minute, TimeZone srcTimeZone, TimeZone destTimeZone) {
        DateFormat formatter = new SimpleDateFormat(DATE_FORMAT_24, Locale.getDefault());
        Calendar instance = Calendar.getInstance();
        instance.set(Calendar.HOUR_OF_DAY, hour);
        instance.set(Calendar.MINUTE, minute);
        Date time = instance.getTime();

        Long targetTime = time.getTime() - srcTimeZone.getRawOffset() + destTimeZone.getRawOffset();
        Date date = new Date(targetTime);
        String result = formatter.format(date);

        String[] split = result.split(":");
        int[] hourMinute = new int[split.length];
        for (int i = 0; i < split.length; i++) {
            hourMinute[i] = Integer.parseInt(split[i]);
        }
        return hourMinute;
    }
}
