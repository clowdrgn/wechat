package com.blueware.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeTools {

        public static SimpleDateFormat formatDay = new SimpleDateFormat("yyyyMMdd");
        public static SimpleDateFormat formatDay_chinese = new SimpleDateFormat("yyyy年MM月dd日");
        public static SimpleDateFormat formatDay_month= new SimpleDateFormat("MM-dd");
        public static SimpleDateFormat formatDay2 = new SimpleDateFormat("yyyy-MM-dd");
        public static SimpleDateFormat formatTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        public static int today;

        public static String getStrTime(String cc_time) {
                String re_StrTime = null;
                long lcc_time = Long.valueOf(cc_time);
                re_StrTime = formatTime.format(new Date(lcc_time * 1000L));
                return re_StrTime;
        }

//        public static void main(String[] args){
//                System.out.println(getLongTime(0));
//        }
        public static long getLongTime(int day) {
                Date date = new Date();
                date.setHours(0);
                date.setMinutes(0);
                date.setSeconds(0);
                return date.getTime()/1000 - day * 60 * 60 * 24;
        }
        
        public static String format() {
                Date date = new Date();
                return formatTime.format(date);
        }
        
        public static String format(long second) {
                Date date = new Date();
                date.setTime(date.getTime()-second);
                return formatTime.format(date);
        }
        
        public static String formatDay() {
                Date date = new Date();
                return formatDay.format(date);
        }
        public static String formatDay2() {
                Date date = new Date();
                return formatDay2.format(date);
        }

        public static String format(Date date) {
                return formatTime.format(date);
        }

        /**
         * 两日相隔天数
         * 
         * @param date
         * @return
         * @throws ParseException
         */
        public static int fromToday(String date) throws ParseException {
                Date dateTime = formatTime.parse(date);
                Date today = new Date();
                long da = dateTime.getTime();
                long to = today.getTime();
                return (int) ((to - da) / 1000 / 60 / 60 / 24);
        }

        public static int fromHour(String date) throws ParseException {
                Date dateTime = formatTime.parse(date);
                Date today = new Date();
                long da = dateTime.getTime();
                long to = today.getTime();
                return (int) ((to - da) / 1000 / 60 / 60);
        }

        public static void formatToday() {
                today = Integer.parseInt(formatDay.format(new Date()));
        }

        /**
         * 获取�?个月的第几天
         * 
         * @param day
         * @return
         */
        public static int formatDay(int day) {
                if (day < 0 || day > 28)
                        return 0;
                Date date = new Date();
                int d = date.getDate();
                if (day <= 0)
                        return d;
                d -= day;
                if (d > 0)
                        return d;
                int m = date.getMonth();
                m -= 1;
                if (m == 0)
                        m = 12;
                return d + MonthDay(date.getYear(), m);
        }
        
        public static int MonthDay(int year, int month) {
                int[] monthDay = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
                if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0)
                        monthDay[1]++;
                return monthDay[month];
        }

        public static int apartDay(String startTime, String endTime) {
                try {
                        return (int) ((formatTime.parse(startTime).getTime() - formatTime.parse(endTime).getTime()) / (24 * 60 * 60 * 1000));
                } catch (Exception e) {

                }
                return -1;
        }

        public static int apartHour(String startTime, String endTime) {
                try {
                        return (int) ((formatTime.parse(startTime).getTime() - formatTime.parse(endTime).getTime()) / (60 * 60 * 1000));
                } catch (Exception e) {

                }
                return -1;
        }

        public static int apartMin(String startTime, String endTime) {
                try {
                        return (int) ((formatTime.parse(startTime).getTime() - formatTime.parse(endTime).getTime()) / (60 * 1000));
                } catch (Exception e) {

                }
                return -1;
        }

        /**
         * 获取day天前的日期零�?
         * 
         * @param day
         * @return
         */
        public static String getDateTime(int day) {
                Date date = new Date();
                date.setHours(0);
                date.setMinutes(0);
                date.setSeconds(0);
                long time = (date.getTime() / 1000) - day * 60 * 60 * 24;
                date.setTime(time * 1000);
                String dateTime = formatTime.format(date);
                return dateTime;
        }
        public static String getDateChinese(int day) {
                Date date = new Date();
                date.setHours(0);
                date.setMinutes(0);
                date.setSeconds(0);
                long time = (date.getTime() / 1000) - day * 60 * 60 * 24;
                date.setTime(time * 1000);
                String dateTime = formatDay_chinese.format(date);
                return dateTime;
        }
        public static String getDateMonth(int day) {
                Date date = new Date();
                date.setHours(0);
                date.setMinutes(0);
                date.setSeconds(0);
                long time = (date.getTime() / 1000) - day * 60 * 60 * 24;
                date.setTime(time * 1000);
                String dateTime = formatDay_month.format(date);
                return dateTime;
        }
        
        public static String next(String start, int day) throws ParseException{
                Date date = new Date();
                long time = formatTime.parse(start).getTime() + day*60 * 60 * 24 * 1000;
                date.setTime(time);
                return formatTime.format(date);
        }
        public static String next0(String start, int day) throws ParseException{
                Date date = new Date();
                long time = formatTime.parse(start).getTime() + day*60 * 60 * 24 * 1000;
                date.setTime(time);
                date.setHours(0);
                date.setMinutes(0);
                date.setSeconds(0);
                return formatTime.format(date);
        }

        /**
         * 获取n小时前的时间
         * 
         * @param day
         * @return
         */
        public static String getHourTime(int n) {
                Date date = new Date();
                long time = (date.getTime() / 1000) - n * 60 * 60;
                date.setTime(time * 1000);
                String dateTime = formatTime.format(date);
                return dateTime;
        }
        /**
         * 获取n分钟前的时间
         * 
         * @param day
         * @return
         */
        public static String getMinTime(int n) {
            Date date = new Date();
            long time = date.getTime() - n * 60 * 1000;
            date.setTime(time);
            String dateTime = formatTime.format(date);
            return dateTime;
        }
        public static String getDateTime(String dateTime) throws ParseException {
                Date date = formatTime.parse(dateTime);
                date.setHours(0);
                date.setMinutes(0);
                date.setSeconds(0);
                return formatTime.format(date);
        }
        
        /**
         * 获取距dateTime day天前的零�?
         * 
         * @param day
         * @param dateTime
         * @return
         * @throws ParseException
         */
        public static String getDateTime(int day, String dateTime) throws ParseException {
                Date date = formatTime.parse(dateTime);
                date.setHours(0);
                date.setMinutes(0);
                date.setSeconds(0);
                long time = (date.getTime() / 1000) - day * 60 * 60 * 24;
                date.setTime(time * 1000);
                dateTime = formatTime.format(date);
                return dateTime;
        }


        public static String week(int n, int week) {
                try {
                        int w = dayForWeek(new Date());
                        if (w == 7) {
                                if (week == 7) {
                                        return weekDay(n, week, new Date());
                                } else {
                                        return weekDay(n + 1, week, new Date());
                                }
                        } else {
                                if (week == 7) {
                                        return weekDay(n - 1, week, new Date());
                                } else {
                                        return weekDay(n, week, new Date());
                                }
                        }
                } catch (Exception e) {
                }
                return null;
        }

        public static String week(String time, int n, int week) {
                try {
                        Date date = formatTime.parse(time);
                        int w = dayForWeek(date);
                        if (w == 7) {
                                if (week == 7) {
                                        return weekDay(n, week, date);
                                } else {
                                        return weekDay(n + 1, week, date);
                                }
                        } else {
                                if (week == 7) {
                                        return weekDay(n - 1, week, date);
                                } else {
                                        return weekDay(n, week, date);
                                }
                        }
                } catch (Exception e) {
                }
                return null;
        }

        public static int dayForWeek(Date date) throws Exception {
                Calendar c = Calendar.getInstance();
                c.setTime(date);
                int dayForWeek = 0;
                if (c.get(Calendar.DAY_OF_WEEK) == 1) {
                        dayForWeek = 7;
                } else {
                        dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
                }
                return dayForWeek;
        }

        public static String weekDay(int n, int week, Date date) throws ParseException {
                if (week <= 0 || week > 7) {
                        return null;
                }
                Calendar cal = Calendar.getInstance();
                cal.setTime(date);
                cal.add(Calendar.DATE, -n * 7);
                switch (week) {
                case 1:
                        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
                        break;
                case 2:
                        cal.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
                        break;
                case 3:
                        cal.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
                        break;
                case 4:
                        cal.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
                        break;
                case 5:
                        cal.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
                        break;
                case 6:
                        cal.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
                        break;
                case 7:
                        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
                        break;
                default:
                        break;
                }
                String day = formatTime.format(cal.getTime());
                Date time = formatTime.parse(day);
                time.setHours(0);
                time.setMinutes(0);
                time.setSeconds(0);
                String dateTime = formatTime.format(time);
                return dateTime;
        }
        /**
         * 比较日期大小
         * d1在d2之后 1
         * d1在d2之前 -1
         * @param day
         * @return
         */
        public static int compare_date(String DATE1, String DATE2) {
            try {
                Date dt1 = formatTime.parse(DATE1);
                Date dt2 = formatTime.parse(DATE2);
                if (dt1.getTime() > dt2.getTime()) {
                    return 1;
                } else if (dt1.getTime() < dt2.getTime()) {
                    return -1;
                } else {
                    return 0;
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            return 0;
        }
        
}
