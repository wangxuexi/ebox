package com.fijo.ebox.base.util.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
    private static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
    private static SimpleDateFormat df3 = new SimpleDateFormat("yyyy-MM");
    private static SimpleDateFormat df4 = new SimpleDateFormat("yyyy");
    private static SimpleDateFormat df5 = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    /**
     * 获取当前日期时间，返回24小时制
     *
     * @return
     */
    public static String getNowDateYMDHMS() {
        String date = df.format(new Date());
        return date;
    }

    public static String getNowDateYM() {
        String date = df3.format(new Date());
        return date;
    }

    /**
     * 获取当前日期时间，返回24小时制,例：2019-11-01
     *
     * @return
     */
    public static String getNowDateYMD() {
        String date = df2.format(new Date());
        return date;
    }

    /**
     * @return
     */
    public static String getNowDateFirstYM() {
        String date = df4.format(new Date()) + "-01";
        return date;
    }

    /**
     * @return
     */
    public static String getNowDateYYYY() {
        String date = df4.format(new Date());
        return date;
    }

    /**
     * 获取当前日期时间，返回24小时制
     *
     * @return
     */
    public static Date getNowDate() {
        String date = df.format(new Date());
        Date nodwymdate = new Date();
        try {
            nodwymdate = df.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return nodwymdate;
    }


    /**
     * str 转date
     *
     * @param datestr
     * @return
     */
    public static Date str2Date(String datestr) throws Exception {
        Date date = new Date();
        try {
            date = df2.parse(datestr);
        } catch (ParseException e) {
            throw new Exception("时间格式化错误");
        }
        return date;
    }

    /**
     * str 转date
     *
     * @param datestr
     * @return
     */
    public static Long str2DateTimeLong(String datestr) throws Exception {
        Date date = new Date();
        try {
            date = df5.parse(datestr);
        } catch (ParseException e) {
            throw new Exception("时间格式化错误");
        }
        return date.getTime();
    }

    /**
     * str 转date
     *
     * @param datestr
     * @return
     */
    public static Date str3Date(String datestr) throws Exception {
        Date date = new Date();
        try {
            date = df3.parse(datestr);
        } catch (ParseException e) {
            throw new Exception("时间格式化错误");
        }
        return date;
    }

    /**
     * 判断一个时间是否在另一个时间之后
     * 例如：判断2019-11-11是否在2019-09-01之后
     *
     * @param date
     * @param afterDate
     * @return boolean
     */
    public static boolean checkDateAfter(String date, String afterDate) throws Exception {
        if (DateUtils.str2Date(afterDate).after(DateUtils.str2Date(date))) {
            return true;
        } else {
            return false;
        }
    }

 

    /**
     * 获取两个时间之间有多少天
     * 例如：date1=2019-05-05，date2=2019-04-04
     *
     * @param date1
     * @param date2
     * @return int
     */
    public static long getDays(String date1, String date2) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Date d1 = sdf.parse(date1);
        Date d2 = sdf.parse(date2);

        long daysBetween = (d2.getTime() - d1.getTime() + 1000000) / (60 * 60 * 24 * 1000);
        return daysBetween;
    }

    /**
     * 时间往前或往后推多少天
     *
     * @param day 如果是往前推就传负数，如果是往后推就传正数
     */
    public static String getDate(int day) {
        Date date = new Date();
        Calendar calendar = new java.util.GregorianCalendar();
        calendar.add(Calendar.DATE, day);
        date = calendar.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(date);
        return dateString;
    }

    /**
     * 判断当前日期是星期几
     *
     * @param pDate 需要判断的时间
     * @return dayForWeek 判断结果
     */

    public static int getDayOfWeek(String pDate) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        Calendar c = Calendar.getInstance();

        c.setTime(format.parse(pDate));

        int dayForWeek = 0;

        if (c.get(Calendar.DAY_OF_WEEK) == 1) {
            dayForWeek = 7;
        } else {
            dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
        }

        return dayForWeek;
    }


    /**
     * 获取星期
     * @param strDate
     * @return
     */
    public static String getWeekByDateStr(String strDate) {
        int year = Integer.parseInt(strDate.substring(0, 4));
        int month = Integer.parseInt(strDate.substring(5, 7));
        int day = Integer.parseInt(strDate.substring(8, 10));

        Calendar c = Calendar.getInstance();

        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month - 1);
        c.set(Calendar.DAY_OF_MONTH, day);

        String week = "";
        int weekIndex = c.get(Calendar.DAY_OF_WEEK);

        switch (weekIndex) {
            case 1:
                week = "星期日";
                break;
            case 2:
                week = "星期一";
                break;
            case 3:
                week = "星期二";
                break;
            case 4:
                week = "星期三";
                break;
            case 5:
                week = "星期四";
                break;
            case 6:
                week = "星期五";
                break;
            case 7:
                week = "星期六";
                break;
        }
        return week;
    }
}
