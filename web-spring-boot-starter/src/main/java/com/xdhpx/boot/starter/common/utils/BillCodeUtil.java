package com.xdhpx.boot.starter.common.utils;


import java.text.DecimalFormat;
import java.util.Date;
import java.util.Random;



/**
 * 
  * @ClassName: BillCodeUtil
  * @Description: 编码生成工具类
  * @author 郝瑞龙
 */
public class BillCodeUtil {

	/**
	  * 获取交易流水号	
	  * 14位时间+4随机数
	 * @return 
	  * @return
	 */
	public static  String makeCodeNum() {
		StringBuffer code = new StringBuffer();
		code.append(DateUtil.dateToStr(new Date(), "yyyyMMddHHmmss"));
		code.append(getRandom(4));
       return code.toString();
	}
	
	/**
	 * 	获取交易流水
	 * 	17位时间
	 * @return
	 */
	public static  String makeDealCode() {
		StringBuffer code = new StringBuffer();
		code.append(DateUtil.dateToStr(new Date(), "yyyyMMddHHmmssSSS"));
       return code.toString();
	}
	
	/**
	 *   获取学生编号
	 *   2固定前缀+2位年(取年份后两位)+2位月+2位日+3位随机数+2位性别
	 * @return
	 */
	public static  String makeStudentCode(Byte sex) {
		StringBuffer code = new StringBuffer();
		code.append("SD");
		code.append(String.valueOf(DateUtil.getYear(new Date())).substring(2));
		code.append(new DecimalFormat("00").format(DateUtil.getMonth(new Date())));
		code.append(new DecimalFormat("00").format(DateUtil.getDay(new Date())));
		code.append(getRandomString(3));
		code.append(new DecimalFormat("00").format(sex.intValue()));
       return code.toString();
	}
	
	/**
	 * 	获取课程编号
	 * 	2固定前缀+2位年(取年份后两位)+2位月+2位日+5位随机数
	 * @param sex
	 * @return
	 */
	public static  String makeCourseCode() {
		StringBuffer code = new StringBuffer();
		code.append("CS");
		code.append(String.valueOf(DateUtil.getYear(new Date())).substring(2));
		code.append(new DecimalFormat("00").format(DateUtil.getMonth(new Date())));
		code.append(new DecimalFormat("00").format(DateUtil.getDay(new Date())));
		code.append(getRandomString(5));
       return code.toString();
	}
	/**
	  * 获取指定位数随机数
	  * @return
	 */
	public static  String getRandom(int length) {
		 	String base = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";     
		    Random random = new Random();     
		    StringBuffer sb = new StringBuffer();     
		    for (int i = 0; i < length; i++) {     
		        int number = random.nextInt(base.length());     
		        sb.append(base.charAt(number));     
		    }     
		    return sb.toString(); 
	}
	public static  String getRandomString(int length) {
	 	String base = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";     
	    Random random = new Random();     
	    StringBuffer sb = new StringBuffer();     
	    for (int i = 0; i < length; i++) {     
	        int number = random.nextInt(base.length());     
	        sb.append(base.charAt(number));     
	    }     
	    return sb.toString(); 
}



}
