package cn.ipaynow.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;


public class DateUtil {
    /**
     * 日期长字符串格式符
     */
    public static final String DATE_FORMAT_FULL = "yyyy-MM-dd HH:mm:ss";

    /**
     * 日期短字符串格式符
     */
    public static final String DATE_FORMAT_SHORT = "yyyy-MM-dd";

    /**
     * 日期紧凑串格式符
     */
    public static final String DATE_FORMAT_COMPACTSHORT = "yyyyMMdd";

    /**
     * 紧凑格式的全字符串
     */
    public static final String DATE_FORMAT_COMPACTFULL = "yyyyMMddHHmmss";


    public static final String DATE_FORMAT_HHMMSS = "HH:mm:ss";


    /**
     * 得到当前日期
     *
     * @return 当前日期 yyyy-MM-dd HH:mm:ss格式
     */
    public static String getCurDateTime() {
        Calendar now = Calendar.getInstance(TimeZone.getDefault());
        DateFormat sdf = new SimpleDateFormat(DATE_FORMAT_FULL);
        sdf.setTimeZone(TimeZone.getDefault());
        return (sdf.format(now.getTime()));
    }

    /**
     * 得到当前日期
     *
     * @return 当前日期
     * @Format 日期格式, yyyy-MM-dd HH:mm:ss, 等
     */
    public static String getCurDateTimeFormat(String Format) {
        Calendar now = Calendar.getInstance(TimeZone.getDefault());
        DateFormat sdf = new SimpleDateFormat(Format);
        sdf.setTimeZone(TimeZone.getDefault());
        return (sdf.format(now.getTime()));
    }

    /**
     * 得到一个Date对象
     *
     * @return Date对象
     * @throws ParseException
     * @tdate 时间的字符串表示
     * @format 日期格式, yyyy-MM-dd HH:mm:ss, 等
     */
    public static Date getDate(String date, String format) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        return formatter.parse(date);
    }

    /**
     * 得到Date转换后的String
     *
     * @param date   时间的Date格式
     * @param format 日期格式, yyyy-MM-dd HH:mm:ss, 等
     * @return date 时间的String格式
     */
    public static String getDateString(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }


    /**
     * System.currentTimeMillis()时间格式化l
     *
     * @return
     */
    public static String getTimeFromCurrent(long l) {

        if (l == 0) return "";
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(l));
    }

    /**
     * 得到当前星期几
     *
     * @return 当前的星期几, 如 "星期一"
     */
    public static String getWeekDay() {
        Calendar now = Calendar.getInstance(TimeZone.getDefault());
        int r = now.get(Calendar.DAY_OF_WEEK);
        if (r == Calendar.SUNDAY) {
            return "星期日";
        }
        if (r == Calendar.MONDAY) {
            return "星期一";
        }
        if (r == Calendar.TUESDAY) {
            return "星期二";
        }
        if (r == Calendar.WEDNESDAY) {
            return "星期三";
        }
        if (r == Calendar.THURSDAY) {
            return "星期四";
        }
        if (r == Calendar.FRIDAY) {
            return "星期五";
        }
        if (r == Calendar.SATURDAY) {
            return "星期六";
        }
        return null;
    }

    /**
     * 得到星期几
     * yyyy_mm_dd 日期格式, yyyy-MM-dd
     *
     * @return 参数日期是星期几
     * @throws ParseException
     */
    public static String getWeekDay(String yyyy_mm_dd) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = dateFormat.parse(yyyy_mm_dd);
        ;

        Calendar cd = Calendar.getInstance();
        cd.setTime(date);
        int r = cd.get(Calendar.DAY_OF_WEEK);
        if (r == Calendar.SUNDAY) {
            return "星期日";
        }
        if (r == Calendar.MONDAY) {
            return "星期一";
        }
        if (r == Calendar.TUESDAY) {
            return "星期二";
        }
        if (r == Calendar.WEDNESDAY) {
            return "星期三";
        }
        if (r == Calendar.THURSDAY) {
            return "星期四";
        }
        if (r == Calendar.FRIDAY) {
            return "星期五";
        }
        if (r == Calendar.SATURDAY) {
            return "星期六";
        }
        return null;
    }

    /**
     * 是否是今天
     *
     * @return 是今天则返回true
     * @throws ParseException
     * @yyyy_mm_dd 所判断的当前日期  yyyy-MM-dd
     */
    public static boolean isCurrentDay(String yyyy_mm_dd) throws ParseException {
        boolean bRet = false;
        Calendar now = Calendar.getInstance(TimeZone.getDefault());
        DateFormat sdf = new SimpleDateFormat(DATE_FORMAT_SHORT);
        sdf.setTimeZone(TimeZone.getDefault());
        Date date1 = sdf.parse(yyyy_mm_dd);
        String strDate1 = sdf.format(date1);
        String strDate2 = sdf.format(now.getTime());
        bRet = strDate1.equalsIgnoreCase(strDate2);
        return bRet;
    }


    /**
     * 得到N分钟后的时间
     *
     * @return N分钟后的日期
     * @throws ParseException
     * @theDate 某日期 格式 yyyy-MM-dd HH:mm:ss 等
     * @nMinutesNum N分钟
     * @format 日期格式, yyyy-MM-dd HH:mm:ss, 等
     */
    public static String afterNMinutesDate(String theDate, int nMinutesNum, String format) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date dd1 = sdf.parse(theDate);
        Calendar cal = Calendar.getInstance();
        cal.setTime(dd1);
        cal.add(Calendar.MINUTE, nMinutesNum);
        sdf.setTimeZone(TimeZone.getDefault());
        return (sdf.format(cal.getTime()));
    }

    /**
     * 得到N天后的日期
     *
     * @return N天后的日期
     * @throws ParseException
     * @theDate 某日期 格式 yyyy-MM-dd HH:mm:ss 等
     * @nDayNum N天
     * @format 日期格式, yyyy-MM-dd HH:mm:ss, 等
     */
    public static String afterNDaysDate(String theDate, int nDayNum, String format) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date dd1 = sdf.parse(theDate);
        Calendar cal = Calendar.getInstance();
        cal.setTime(dd1);
        cal.add(Calendar.DAY_OF_YEAR, nDayNum);
        sdf.setTimeZone(TimeZone.getDefault());
        return (sdf.format(cal.getTime()));
    }

    /**
     * 得到N天前的日期
     *
     * @return N天前的日期
     * @throws ParseException
     * @theDate 某日期 格式 yyyy-MM-dd HH:mm:ss, 等
     * @nDayNum N天
     * @format 日期格式, yyyy-MM-dd HH:mm:ss, 等
     */
    public static String beforeNDaysDate(String theDate, int nDayNum, String format) throws ParseException {
        return afterNDaysDate(theDate, 0 - nDayNum, format);
    }

    /**
     * 获取两个日期中间的天数
     *
     * @param yyyy_mm_dd_1 第一个日期(yyyy-MM-dd)
     * @param yyyy_mm_dd_2 第二个日期(yyyy-MM-dd)
     * @return daycount 前一个日期减去后一个日期的天数
     * @throws ParseException
     */
    public static int getDayCount(String yyyy_mm_dd_1, String yyyy_mm_dd_2) throws ParseException {
        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = myFormatter.parse(yyyy_mm_dd_1);
        Date mydate = myFormatter.parse(yyyy_mm_dd_2);
        return (int) ((date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000));
    }

    /**
     * 获取本周一的日期
     *
     * @return 本周一的日期(字符串yyyy-MM-dd格式)
     */
    public static String getMondayOFWeek() {
        int mondayPlus = getMondayPlus();
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, mondayPlus);
        Date monday = currentDate.getTime();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String preMonday = sdf.format(monday);
        return preMonday;
    }

    /**
     * 获取当月第一天
     *
     * @return 当月的第一天(字符串yyyy-MM-dd格式)
     */
    public static String getFirstDayOfMonth() {
        String str = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Calendar lastDate = Calendar.getInstance();
        lastDate.set(Calendar.DATE, 1);//设为当前月的1号
        str = sdf.format(lastDate.getTime());
        return str;
    }

    /**
     * 获取当月最后一天
     *
     * @return 当月的第一天(字符串yyyy-MM-dd格式)
     */
    public static String getLastDayOfMonth() {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
        String last = sdf.format(ca.getTime());
        return last;
    }


    /**
     * 获得本季度第一天
     *
     * @return 获得本季度第一天(字符串yyyy-MM-dd格式)
     */
    public static String getFirstDayOfSeason() {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        String str = df.format(new Date());
        //当前月份
        int month = Integer.parseInt(str.substring(4, 6));


        int array[][] = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {10, 11, 12}};
        int season = 1;
        if (month >= 1 && month <= 3) {
            season = 1;
        }
        if (month >= 4 && month <= 6) {
            season = 2;
        }
        if (month >= 7 && month <= 9) {
            season = 3;
        }
        if (month >= 10 && month <= 12) {
            season = 4;
        }
        int start_month = array[season - 1][0];
        int end_month = array[season - 1][2];

        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");//可以方便地修改日期格式
        String years = dateFormat.format(date);
        int years_value = Integer.parseInt(years);

        int start_days = 1;//years+"-"+String.valueOf(start_month)+"-1";//getLastDayOfMonth(years_value,start_month);
        @SuppressWarnings("unused")
        int end_days = getLastDayOfMonth(years_value, end_month);
//      本季度第一天
        String seasonstartDate = years_value + "-" + (start_month < 10 ? "0" + start_month : start_month) + "-" + (start_days < 10 ? "0" + start_days : start_days);

//      本季度最后一天
//      String seasonendDate = years_value+"-"+end_month+"-"+end_days;
        return seasonstartDate;
    }

    /**
     * 获取本年第一天的日期
     *
     * @return 本本年第一天的日期(字符串yyyy-MM-dd格式)
     */
    public static String getFirstDayOfYear() {
        int yearPlus = getYearPlus();
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, yearPlus);
        Date yearDay = currentDate.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String preYearDay = sdf.format(yearDay);
        return preYearDay;
    }

    /**
     * 得到时间间隔(秒)
     *
     * @return
     * @throws ParseException
     * @date1,时间参数1
     * @date2,时间参数2
     * @format 时间格式, 如yyyy-MM-dd HH:mm:ss
     */
    public static long getInters(String date1, String date2, String format) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        Date d1 = formatter.parse(date1);
        Date d2 = formatter.parse(date2);
        long result = (d1.getTime() - d2.getTime()) / 1000;
        if (result < 0) {
            return -result;
        } else {
            return result;
        }
    }

    public static boolean isStoockTime() {
        if (getWeekDay().equals("星期六") || getWeekDay().equals("星期日")) {
            return false;
        }
        String hhmmss = getCurDateTimeFormat(DateUtil.DATE_FORMAT_HHMMSS);
        if (hhmmss.compareTo("09:15:00") > 0 && hhmmss.compareTo("11:30:00") < 0) {
            return true;
        }
        if (hhmmss.compareTo("13:00:00") > 0 && hhmmss.compareTo("15:00:00") < 0) {
            return true;
        }
        return false;
    }


    private static int getMondayPlus() {
        Calendar cd = Calendar.getInstance();
        // 获得今天是一周的第几天，星期日是第一天，星期二是第二天......
        int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK) - 1;//因为按中国礼拜一作为第一天所以这里减1
        if (dayOfWeek == 1) {
            return 0;
        } else {
            return 1 - dayOfWeek;
        }
    }

    private static int getYearPlus() {
        Calendar cd = Calendar.getInstance();
        int yearOfNumber = cd.get(Calendar.DAY_OF_YEAR);//获得当天是一年中的第几天
        cd.set(Calendar.DAY_OF_YEAR, 1);//把日期设为当年第一天
        cd.roll(Calendar.DAY_OF_YEAR, -1);//把日期回滚一天。
        int MaxYear = cd.get(Calendar.DAY_OF_YEAR);
        if (yearOfNumber == 1) {
            return -MaxYear;
        } else {
            return 1 - yearOfNumber;
        }
    }

    private static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    private static int getLastDayOfMonth(int year, int month) {
        if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8
                || month == 10 || month == 12) {
            return 31;
        }
        if (month == 4 || month == 6 || month == 9 || month == 11) {
            return 30;
        }
        if (month == 2) {
            if (isLeapYear(year)) {
                return 29;
            } else {
                return 28;
            }
        }
        return 0;
    }


    /**
     * 得到时间间隔（分)
     *
     * @return
     * @throws ParseException
     * @date1,时间参数1
     * @date2,时间参数2
     * @format 时间格式, 如yyyy-MM-dd HH:mm:ss
     */
    public static long getMinutes(Date d1, Date d2) throws ParseException {
        long result = (d1.getTime() - d2.getTime()) / 1000 / 60;
        if (result < 0) {
            return -result;
        } else {
            return result;
        }
    }

    /**
     * 得到N天后的日期
     *
     * @return N天后的日期
     * @throws ParseException
     * @theDate 某日期 格式 yyyy-MM-dd HH:mm:ss 等
     * @nDayNum N天
     */
    public static Date afterNDaysDate(Date theDate, int nDayNum) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(theDate);
        cal.add(Calendar.DAY_OF_YEAR, nDayNum);
        return cal.getTime();
    }

    /**
     * 得到N天前的日期
     *
     * @return N天后的日期
     * @throws ParseException
     * @theDate 某日期 格式 yyyy-MM-dd HH:mm:ss 等
     * @nDayNum N天
     */
    public static Date beforeNDaysDate(Date theDate, int nDayNum) {
        return afterNDaysDate(theDate, -nDayNum);
    }


    /**
     * 得到N秒后的日期
     *
     * @param theDate
     * @param nSecNum
     * @return
     * @throws ParseException
     */
    public static Date afterNSecDate(Date theDate, int nSecNum) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(theDate);
        cal.add(Calendar.SECOND, nSecNum);
        return cal.getTime();
    }

    public static Date beforeNSecDate(Date theDate, int nSecNum) {
        return afterNSecDate(theDate, -nSecNum);
    }


    public static Date afterNMinutesDate(Date theDate, int nMinutesNum) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(theDate);
        cal.add(Calendar.MINUTE, nMinutesNum);
        return cal.getTime();
    }

    /**
     * 获取几分钟之前的时间
     *
     * @return
     * @throws ParseException
     */
    public static Date beforeNMinutesDate(Date theDate, int nMinutesNum) {
        return afterNMinutesDate(theDate, -nMinutesNum);
    }


    /**
     * 得到N月后的日期
     *
     * @return N天后的日期
     * @throws ParseException
     * @theDate 某日期 格式 yyyy-MM-dd HH:mm:ss 等
     * @nDayNum N天
     */
    public static Date afterNMonthsDate(Date theDate, int nMonthNum) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(theDate);
        cal.add(Calendar.MONTH, nMonthNum);
        return cal.getTime();
    }

    public static Date beforeNMonthsDate(Date theDate, int nMonthNum) {
        return afterNMonthsDate(theDate, -nMonthNum);
    }


}