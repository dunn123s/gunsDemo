package com.xsl.crm.util;

import com.xsl.crm.enums.TargetRangeEnum;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateUtils {

    public static SimpleDateFormat daydf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static SimpleDateFormat sdf   = new SimpleDateFormat("yyyy-MM-dd");
    public static SimpleDateFormat ym    = new SimpleDateFormat("yyyy-MM");
    private static Integer defDay = 7;

    /**
     * @param date 字符型时间格式(2010-10-10)
     */
    public static Date stringFormatDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param date 字符型时间格式
     */
    public static Date strFormatDate(String date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取当前时间的字符串（yyyy-MM-dd）
     */
    public static String getDateString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(new Date());
    }

    /**
     * 获取当前时间的字符串（yyyyMMdd）
     */
    public static String getDateStr() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(new Date());
    }

    /**
     * 获取时间字符串
     */
    public static String getDateStr(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    /**
     * 获取当前时间的字符串（yyyyMMddHHmmss）
     * 
     * @return
     */
    public static String getTimestampStr() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.format(new Date());
    }
    

    /**
     * 获取当前时间的字符串（yyyy年MM月dd日 HH:mm:ss）
     * 
     * @return
     */
    public static String getTimestampStr2() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        return sdf.format(new Date());
    }

    /**
     * 格式时间（Date），返回年月日（yyyy-MM-dd）
     */
    public static String getDateString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }

    /**
     * 格式时间（Date），返回时分秒（yyyy-MM-dd）
     */
    public static String getDateStringTime(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return sdf.format(date);
    }

    /**
     * 获取当前时间的字符串（yyyy-MM-dd HH:mm:ss）
     */
    public static String getDateTimeString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date());
    }

    /**
     * 获取上一日日期（yyyy-MM-dd）
     * 
     * @return
     */
    public static String getRoundDateTimeString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        Date date = calendar.getTime();
        return sdf.format(date);
    }

    /**
     * 获取上一日日期（yyyy-MM-dd）
     * 
     * @return
     */
    public static String getRoundDateString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        Date date = calendar.getTime();
        return sdf.format(date);
    }

    /**
     * 将不规范的时间格式转换为规范的时间格式
     * 
     * @param time 时间
     * @param format 时间格式
     * @param format1 转换格式
     * @return
     */
    public static String timeConversion(String time, String format, String format1) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        SimpleDateFormat sdf1 = new SimpleDateFormat(format1);
        try {
            Date date = sdf.parse(time);
            return sdf1.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 给指定时间加上一个数值
     * 
     * @param time1 要加上一数值的时间，为null即为当前时间，格式为yyyy-MM-dd HH:mm:ss
     * @param addpart 要加的部分：年月日时分秒分别为：YMDHFS
     * @param num 要加的数值
     * @return 新时间，格式为yyyy-MM-dd HH:mm:ss
     */
    public static String addTime(String time1, String addpart, int num) {
        try {
            String now = daydf.format(new Date());
            time1 = (time1 == null) ? now : time1;
            if (time1.length() < 19) {
                time1 += " 00:00:00";
            }
            GregorianCalendar cal = new GregorianCalendar();
            cal.setTime(daydf.parse(time1));
            if (addpart.equalsIgnoreCase("Y")) {
                cal.add(Calendar.YEAR, num);
            } else if (addpart.equalsIgnoreCase("M")) {
                cal.add(Calendar.MONTH, num);
            } else if (addpart.equalsIgnoreCase("D")) {
                cal.add(Calendar.DATE, num);
            } else if (addpart.equalsIgnoreCase("H")) {
                cal.add(Calendar.HOUR, num);
            } else if (addpart.equalsIgnoreCase("F")) {
                cal.add(Calendar.MINUTE, num);
            } else if (addpart.equalsIgnoreCase("S")) {
                cal.add(Calendar.SECOND, num);
            }
            return daydf.format(cal.getTime());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 给指定日期加上一个数值
     * 
     * @param date1 要加上一数值的日期，为null即为当前日期，格式为yyyy-MM-dd
     * @param addpart 要加的部分：年月日分别为：YMD
     * @param num 要加的数值
     * @return 新日期，格式为yyyy-MM-dd
     */
    public static String addDate(String date1, String addpart, int num) {
        return addTime(date1, addpart, num).substring(0, 10);
    }

    /**
     * 计算 两个日期相差多少天 过滤时分秒
     * 
     * @param fDate
     * @param oDate
     * @return
     */
    public static int getIntervalDays(Date fDate, Date oDate) {
        if (null == fDate || null == oDate) {
            return -1;
        }
        fDate = stringFormatDate((sdf.format(fDate)));
        oDate = stringFormatDate((sdf.format(oDate)));
        long intervalMilli = oDate.getTime() - fDate.getTime();
        return (int) (intervalMilli / (24 * 60 * 60 * 1000));

    }

    public static Date holdDateDay(Date dateTime, int subDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateTime);
        calendar.add(Calendar.DATE, subDate);
        return calendar.getTime();
    }

    public static String holdDateDay(Date dateTime, int subDate, String format) {
        return getDateStr(holdDateDay(dateTime, subDate), format);
    }

    /**
     * 获取两个时间相差值是否正确，并返回
     * 
     * @param starTime
     * @param endTime
     * @param day
     * @return
     */
    public static Map<String, Date> getIntervalDate(Date starTime, Date endTime, int day) {
        Calendar calendar = Calendar.getInstance();
        Map<String, Date> dateMap = new HashMap<String, Date>();
        int sday = 0, eday = 0;
        if (null != starTime) {
            calendar.setTime(starTime);
            sday = calendar.get(Calendar.DAY_OF_YEAR);
        }else{
            calendar.setTime(new Date());
            calendar.add(Calendar.DATE, -1);
            starTime = calendar.getTime();
        }
        if (null != endTime) {
            calendar.setTime(endTime);
            eday = calendar.get(Calendar.DAY_OF_YEAR);
        }else{
            calendar.setTime(new Date());
        }
        if (day != 0 && ((eday - sday) < 0 || (eday - sday) >= day)) {
            calendar.setTime(starTime);
            calendar.add(Calendar.DATE, day == 0 ? 1 : day);
        }
        dateMap.put("starTime", starTime);
        dateMap.put("endTime", calendar.getTime());
        return dateMap;
    }

    public static Map<String, String> getIntervalDate(String starTime, String endTime,String fmt) {
        return getIntervalDate(starTime, endTime,defDay, fmt);
    }
    public static Map<String, String> getIntervalDate(String starTime, String endTime, int day, String fmt) {
        SimpleDateFormat sdf = new SimpleDateFormat(fmt);
        Map<String, String> dateStrMap = new HashMap<String, String>();
        try {
            Map<String, Date> dateMap = getIntervalDate(sdf.parse(starTime), sdf.parse(endTime), day);
            dateStrMap.put("starTime", sdf.format(dateMap.get("starTime")));
            dateStrMap.put("endTime", sdf.format(dateMap.get("endTime")));
        } catch (Exception e) {
            dateStrMap.put("starTime", StringUtil.isEmpty(starTime) ? getIntervalDate(fmt, 1) : starTime);
            dateStrMap.put("endTime",  StringUtil.isEmpty(endTime) ? getIntervalDate(fmt, 2) : endTime);
        }
        return dateStrMap;
    }
    
    private static String getIntervalDate(String fmt,int type){
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND,0);
        cal.set(Calendar.MINUTE,0);
        cal.add(Calendar.DAY_OF_MONTH, 1);
        cal.add(Calendar.SECOND, -1);    
        if(type == 1){
            cal.add(Calendar.DAY_OF_MONTH, -2);
            cal.add(Calendar.SECOND, +1); 
        }
        SimpleDateFormat fmtDate = new SimpleDateFormat(fmt);
        return fmtDate.format(cal.getTime());
    }

    public static void main(String[] args) {
        try {
//            System.out.println(getIntervalDate("yyyy-MM-dd HH:mm:ss", 1) );
//            System.out.println(getIntervalDate("yyyy-MM-dd HH:mm:ss", 2) );
//            Map<String, String> map =  getIntervalDate("2017-06-01 12:00:00", "2017-06-22 12:00:00",2, "yyyy-MM-dd HH:mm:ss");
           // Map<String, Date> map2 =  getIntervalDate(null, daydf.parse("2017-03-8 12:00:00"), 10);
            // map2 = DateUtils.getIntervalDate(null, null,30);
//            System.out.println(JSON.toJSONString(map));
            System.out.println(getRangeFirstDate(new Date(), "yyyyMMdd", TargetRangeEnum.MONTH));
            
          //  System.out.println(daydf.format(map2.get("starTime")));
           // System.out.println(daydf.format(map2.get("endTime")));
            //
          // System.out.println(DateUtils.getIntervalDays(new Date(), daydf.parse("2017-03-8 12:00:00")));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // String d = "2016-03-31"; Date date = StringFormatDate(d); System.out.println(date.getTime());

       // System.out.println(addDate("2016-08-08", "D", -7));
    }
    
	/**
	 * 将传入的日期转为yyyy-MM-dd的格式
	 * 
	 * @return
	 */
	public static String getDateStr10(Date date) {
		return new SimpleDateFormat("yyyy-MM-dd").format(date);
	}

	public static String getRangeFirstDate(Date date, String fmt, TargetRangeEnum targetRange){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        String dateStr = null;
        if(targetRange == TargetRangeEnum.WEEK){
            calendar.setFirstDayOfWeek(Calendar.MONDAY);
            calendar.set(Calendar.DAY_OF_WEEK,Calendar.MONDAY);
        }else if(targetRange == TargetRangeEnum.MONTH){
            calendar.set(Calendar.DAY_OF_MONTH,1);
            calendar.add(Calendar.MONTH,0);
        }else if(targetRange == TargetRangeEnum.YEAR){
            calendar.set(Calendar.DAY_OF_YEAR,1);
            calendar.add(Calendar.YEAR,0);
        }

        SimpleDateFormat fmtDate = new SimpleDateFormat(fmt);
        return fmtDate.format(calendar.getTime());
    }

    public static int getMonthDays(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }
}
