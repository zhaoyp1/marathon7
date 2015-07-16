package com.digiwes.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 *
 * ��������ʱ�����������
 *
 * @version 1.0
 */
public class DateUtils {
    // ����ʱ���ʽ
    public static final SimpleDateFormat date_sdf = new SimpleDateFormat(
            "yyyy-MM-dd");
    // ����ʱ���ʽ
    public static final SimpleDateFormat yyyyMMdd = new SimpleDateFormat(
            "yyyyMMdd");
    // ����ʱ���ʽ
    public static final SimpleDateFormat date_sdf_wz = new SimpleDateFormat(
            "yyyy��MM��dd��");
    public static final SimpleDateFormat time_sdf = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm");
    public static final SimpleDateFormat yyyymmddhhmmss = new SimpleDateFormat(
            "yyyyMMddHHmmss");
    public static final SimpleDateFormat short_time_sdf = new SimpleDateFormat(
            "HH:mm");
    public static final  SimpleDateFormat datetimeFormat = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss");

    // �Ժ����ʾ��ʱ��
    private static final long DAY_IN_MILLIS = 24 * 3600 * 1000;
    private static final long HOUR_IN_MILLIS = 3600 * 1000;
    private static final long MINUTE_IN_MILLIS = 60 * 1000;
    private static final long SECOND_IN_MILLIS = 1000;
    /**
     * ��ǰ�������������й�ʱ���ʾ
     *
     * @return �Ե���ʱ����ʾ��ϵͳ��ǰ����
     */
    public static Calendar getCalendar() {
        return Calendar.getInstance();
    }

    /**
     * ʱ��ת�ַ���
     * @return
     */
    public static String date2SStr()
    {
        Date date=getDate();
        if (null == date) {
            return null;
        }
        return yyyyMMdd.format(date);
    }


    /**
     * ��ǰ����
     *
     * @return ϵͳ��ǰʱ��
     */
    public static Date getDate() {
        return new Date();
    }


    /**
     * �ַ���ת��������
     * @param str
     * @param sdf
     * @return
     */
    public static Date str2Date(String str, SimpleDateFormat sdf) {
        if (null == str || "".equals(str)) {
            return null;
        }
        Date date = null;
        try {
            date = sdf.parse(str);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * ����ת��Ϊ�ַ���
     *

     *            ���ڸ�ʽ
     * @return �ַ���
     */
    public static String date2Str(SimpleDateFormat date_sdf) {
        Date date=getDate();
        if (null == date) {
            return null;
        }
        return date_sdf.format(date);
    }
    /**
     * ��ʽ��ʱ��
     * @param data
     * @param format
     * @return
     */
    public static String dataformat(String data,String format)
    {
        SimpleDateFormat sformat = new SimpleDateFormat(format);
        Date date=null;
        try {
            date=sformat.parse(data);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return sformat.format(date);
    }
    /**
     * ����ת��Ϊ�ַ���
     *
     * @param date
     *            ����
     * @return �ַ���
     */
    public static String date2Str(Date date, SimpleDateFormat date_sdf) {
        if (null == date) {
            return null;
        }
        return date_sdf.format(date);
    }
    /**
     * ��������ʱ��֮��Ĳ�ֵ�����ݱ�־�Ĳ�ͬ����ͬ
     *
     * @param flag
     *            �����־����ʾ������/��/��/ʱ/��/��ȼ���
     * @param calSrc
     *            ����
     * @param calDes
     *            ������
     * @return ��������֮��Ĳ�ֵ
     */
    public static int dateDiff(char flag, Calendar calSrc, Calendar calDes) {

        long millisDiff = getMillis(calSrc) - getMillis(calDes);

        if (flag == 'y') {
            return (calSrc.get(calSrc.YEAR) - calDes.get(calDes.YEAR));
        }

        if (flag == 'd') {
            return (int) (millisDiff / DAY_IN_MILLIS);
        }

        if (flag == 'h') {
            return (int) (millisDiff / HOUR_IN_MILLIS);
        }

        if (flag == 'm') {
            return (int) (millisDiff / MINUTE_IN_MILLIS);
        }

        if (flag == 's') {
            return (int) (millisDiff / SECOND_IN_MILLIS);
        }

        return 0;
    }
    /**
     * ָ�������ĺ�����
     *
     * @param cal
     *            ָ������
     * @return ָ�������ĺ�����
     */
    public static long getMillis(Calendar cal) {
        return cal.getTime().getTime();
    }

    /**
     * ����ָ���ĸ�ʽ���ַ���ת����Date �����룺2003-11-19 11:20:20���������ת��ʱ��
     *
     * @param src
     *            ��Ҫת����ԭʼ�ַ���
     * @param pattern
     *            ת����ƥ���ʽ
     * @return ���ת���ɹ��򷵻�ת���������
     * @throws ParseException
     */
    public static Calendar parseCalendar(String src, String pattern)
            throws ParseException {

        Date date = parseDate(src, pattern);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }

    /**
     * ����ָ���ĸ�ʽ���ַ���ת����Date �����룺2003-11-19 11:20:20���������ת��ʱ��
     *
     *            ��Ҫת����ԭʼ�ַ���
     *            ת����ƥ���ʽ
     * @return ���ת���ɹ��򷵻�ת���������
     * @throws ParseException
     */
    public static Calendar parseCalendar(Date date)
            throws ParseException {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }

    /**
     * ����ָ���ĸ�ʽ���ַ���ת����Date �����룺2003-11-19 11:20:20���������ת��ʱ��
     *
     * @param src
     *            ��Ҫת����ԭʼ�ַ���
     * @param pattern
     *            ת����ƥ���ʽ
     * @return ���ת���ɹ��򷵻�ת���������
     * @throws ParseException
     */
    public static Date parseDate(String src, String pattern)
            throws ParseException {
        return getSDFormat(pattern).parse(src);

    }

    // ָ��ģʽ��ʱ���ʽ
    private static SimpleDateFormat getSDFormat(String pattern) {
        return new SimpleDateFormat(pattern);
    }
}
