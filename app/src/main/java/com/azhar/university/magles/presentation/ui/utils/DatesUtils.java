package com.azhar.university.magles.presentation.ui.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by interactive on 7/30/18.
 */

public class DatesUtils {
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final SimpleDateFormat datetimeFormatter;
    private static final SimpleDateFormat dateFormatter;

    static {
        datetimeFormatter = new SimpleDateFormat(DATE_TIME_FORMAT);
        dateFormatter = new SimpleDateFormat(DATE_FORMAT);
    }

    public static String formatDateOnly(Date date) {
        return dateFormatter.format(date);
    }
}
