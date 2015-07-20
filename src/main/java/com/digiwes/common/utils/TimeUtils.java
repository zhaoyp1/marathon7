package com.digiwes.common.utils;

import org.apache.commons.lang.StringUtils;

import java.text.ParseException;
import java.util.Date;

/**
 * Created by Nisx on 2015/7/17.
 */
public class TimeUtils {

    public static final String Y4MMDD = "yyyyMMdd";
    public static final String Y2MMDD = "yyMMdd";
    public static final String Y4_MM_DD = "yyyy-MM-dd";
    public static final String Y2_MM_DD = "yy-MM-dd";
    public static final String Y4MMDD_TIME = "yyyyMMdd HH:mm:ss";
    public static final String Y2MMDD_TIME = "yyMMdd HH:mm:ss";
    public static final String Y4_MM_DD_TIME = "yyyy-MM-dd HH:mm:ss";
    public static final String Y2_MM_DD_TIME = "yy-MM-dd HH:mm:ss";

    private static final String[] fullPattern = new String[] {
            Y4MMDD,
            Y2MMDD,
            Y4_MM_DD,
            Y2_MM_DD,
            Y4MMDD_TIME,
            Y2MMDD_TIME,
            Y4_MM_DD_TIME,
            Y2_MM_DD_TIME,
    };
    public static Date parseDate(String strDate) throws Exception {
        return parseDate(strDate,null);
    }
    public static Date parseDate(String strDate, String pattern) throws Exception {
        if (StringUtils.isEmpty(strDate)) {
            throw new IllegalArgumentException("parameter [strDate] must not be null or empty");
        }
        String[] patterns;
        if (pattern == null) {
            patterns = new String[] {
                    pattern
            };
        } else {
            patterns = fullPattern;
        }
        return org.apache.commons.lang.time.DateUtils.parseDate(strDate, patterns);
    }
    public static Date truncDate( Date date){
        date.setHours(0);
        date.setMinutes(0);
        date.setSeconds(0);
        return date;
    }
}
