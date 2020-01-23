package com.xdhpx.boot.starter.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
  * @ClassName: DateUtil
  * @Description: 日期处理工具类
  * @author 郝瑞龙
 */
public class DateUtil {
  
	/**
	 *  日期形式字符串转date
	  * @param dateString 字符串日期
	  * @param dateType  日期格式 如: yyyy-MM-dd
	  * @return
	  * @throws Exception
	 */
    public static Date strToDate(String dateString,String dateType) throws Exception{
    	 SimpleDateFormat sdf = new SimpleDateFormat(dateType);  
		 Date date = sdf.parse(dateString);  
         return date;
    }
    /**
     * 
      * @param date 日期
      * @param dateType 日期格式 如: yyyy-MM-dd
      * @return
      * @throws Exception
     */
    public static String dateToStr(Date date,String dateType){
   	 SimpleDateFormat sdf = new SimpleDateFormat(dateType);  
       return sdf.format(date);
   }
	/**
	 *  日期月份计算
	  * @param date
	  * @return
	 */
    public static Date monthCount(Date date,int amount){
		/**日历对象**/
		Calendar calendar = getCalendar(date);
		/**月份加减数**/
		calendar.add(Calendar.MONTH, amount);
        return calendar.getTime();
    } 
	/**
	 *  日期天数计算
	  * @param date
	  * @return
	 */
    public static Date daysCount(Date date,int amount){
		/**日历对象**/
		Calendar calendar = getCalendar(date);
		/**天数加减数**/
		calendar.add(Calendar.DAY_OF_MONTH, amount);
        return calendar.getTime();
    }
    /**
	 *  判断是否区间：startDate <= date < endDate
	  * @param date
	  * @return
	 */
    public static boolean isIn(Date date, Date start,Date end){
    	if (date.getTime()>=start.getTime() && date.getTime() < end.getTime()) {
			return true;
		}
        return false;
    } 
    /**
      *  比较两个时间的大小
      * @param beginDate
      * @param endDate
      * @return 大于是1，等于的话是0，小于的话是-1
      * @throws ParseException
     */
    public static int compareDate(Date beginDate,Date endDate){
        /**compareTo的返回值:大于是1(beginDate大于endDate，后面类同)，等于的话是0，小于的话是-1**/
       return beginDate.compareTo(endDate);
    }
    /**
      * 获取年份
      * @param date
      * @return
     */
    public static int getYear(Date date){
		/**设置当前日期**/
    	Calendar calendar = getCalendar(date);
		/**获取年份**/
       return calendar.get(Calendar.YEAR);
    }
    /**
     * 获取月份
     * @param date
     * @return
    */
    public static int getMonth(Date date){
		/**设置当前日期**/
    	Calendar calendar = getCalendar(date);
		/**获取月份**/
		return calendar.get(Calendar.MONTH);
    }
    /**
     * 获取日期
     * @param date
     * @return
    */
    public static int getDay(Date date){
		/**设置当前日期**/
    	Calendar calendar = getCalendar(date);
		/**获取年份**/
    	return calendar.get(Calendar.DATE);
    }
    
    /**
     * 	根据时间计算年龄
     * @param birthday 固定格式yyyy-MM-dd
     * @return
     */
    public static int getAgeByBirth(String birthday) {
        int age = 0;
        try {
        	/**出生年月空直接返回0**/
        	if ("".equals(birthday) || birthday.length() != 10) {
				 return age;
			}
        	/**获取当前系统时间**/
            Calendar now = getCalendar(new Date());
            /**获取出生年月**/
            Calendar birth = getCalendar(strToDate(birthday,"yyyy-MM-dd"));
            /**如果传入的时间，在当前时间的后面，返回0岁**/
            if (birth.after(now)) {
                age = 0;
            } else {
            	/**计算年**/
                age = now.get(Calendar.YEAR) - birth.get(Calendar.YEAR);
                /**计算月份,如果当前月份大于出生月份,直接年龄加1返回**/
                if (now.get(Calendar.MONTH) > birth.get(Calendar.MONTH)) {
                	age += 1;
                	return age;
                }
                /**月份相等,计算天,当前日期大于出生日期，年龄加1**/
                if (now.get(Calendar.DAY_OF_MONTH) > birth.get(Calendar.DAY_OF_MONTH)) {
                    age += 1;
                }
            }
            return age;
        } catch (Exception e) {
        	/**兼容性更强,异常后返回数据**/
           return 0;
        }
    }


	/**
	 * 	获取Calendar对象
	 * @param dataTime
	 * @return
	 */
	public static Calendar getCalendar(Date dataTime){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dataTime);
		return calendar;
	}
    
   /**
    * 	时长计算 key为类型,value为时长
    * @param startTime
    * @param endTime
    * @return
    */
    public static Map<String, Long> getTimeLength(Date startTime,Date endTime){
		/**声明返回**/
		Map<String, Long> map = new HashMap<String, Long>();
		/**判断是否为空**/
		if (startTime == null || endTime == null) {
			return map;
		}
		/**获取时间差**/
		long length = endTime.getTime() - startTime.getTime();
		
		/**判断时差**/
		if (length<=0) {
			return map;
		}
		
		/**计算小时**/
		long hour = (length / (60 * 60 * 1000));
		map.put("hour", hour);
		
		/**计算分钟**/
		long min = ((length / (60 * 1000)) - hour * 60);
		map.put("min", min);
		
		/**计算秒**/
		long second = (length / 1000 - hour * 60 * 60 - min * 60);
		map.put("second", second);
		
		/**返回结果**/
		return map;
	}

    
	public static void main(String[] args) {
//			System.out.println(String.valueOf(DateUtil.getYear(new Date())).substring(2));
//			System.out.println(DateUtil.getMonth(new Date()));
//			System.out.println(new DecimalFormat("00").format(DateUtil.getMonth(new Date())));
//			System.out.println(DateUtil.getDay(new Date()));
//			/**获取昨天日期**/
//			System.out.println(DateUtil.daysCount(new Date(), -1));
		/**根据出生年月获取年龄**/
		System.out.println(DateUtil.getAgeByBirth("2008-10-9"));
		System.out.println(DateUtil.getAgeByBirth("2008-10-10"));
		System.out.println(DateUtil.getAgeByBirth("2008-10-11"));
		
	}
	
    
}
