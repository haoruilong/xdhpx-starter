package com.xdhpx.boot.starter.common.utils;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

/***
 * @ClassName: BeanCopyUtil(org.springframework.beans.BeanUtils)
 * @Description: 对象属性值拷贝
 * @author 郝瑞龙
*/
public class BeanCopyUtil {
	
	/**
	 * 	获取对象中属性为null的属性名
	 * @param from 要拷贝的对象
	 * @return
	 */
   public static String[] getNullPropertyNames(Object from) {
       final BeanWrapper src = new BeanWrapperImpl(from);
       java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

       Set<String> emptyNames = new HashSet<String>();
       for (java.beans.PropertyDescriptor pd : pds) {
           Object srcValue = src.getPropertyValue(pd.getName());
           if (srcValue == null){
               emptyNames.add(pd.getName());
           }
       }
       String[] result = new String[emptyNames.size()];
       return emptyNames.toArray(result);
   }

   /**
    * 	值拷贝
    * @param from
    * @param to
    */
   public static void copyProperties(Object from, Object to) {
       BeanUtils.copyProperties(from, to);
   }
   
   /**
    * 	值拷贝，排除null
    * @param from
    * @param to
    */
   public static void copyPropertiesNotNull(Object from, Object to) {
       BeanUtils.copyProperties(from, to, getNullPropertyNames(from));
   }

}
