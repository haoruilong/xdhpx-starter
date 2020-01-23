package com.xdhpx.boot.starter.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
  * @ClassName: CheckDateUtil
  * @Description: 校验工具类
  * @author 郝瑞龙
 */
public class CheckUtil {
  
	/**
	 * 	手机号格式校验
	 * @param phone
	 * @return
	 * @throws Exception
	 */
    public static boolean checkPhone(String phone){
    	if (phone.length() != 11) {
			return false;
	    } 
    	/**手机号正则**/
		String regex="^[1](([3|5|8][\\d])|([4][5,6,7,8,9])|([6][5,6])|([7][3,4,5,6,7,8])|([9][8,9]))[\\d]{8}$";
		Pattern p = Pattern.compile(regex);
	    Matcher m = p.matcher(phone);
        return m.matches();
    }
    
    
}
