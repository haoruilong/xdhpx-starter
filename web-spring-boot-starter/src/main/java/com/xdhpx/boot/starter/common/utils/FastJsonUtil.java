package com.xdhpx.boot.starter.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class FastJsonUtil {

    /**序列化配置类**/
    private static SerializeConfig config  = new SerializeConfig();
    static {
        /**日期格式设置**/
        config .put(Date.class, new SimpleDateFormatSerializer("yyyy-MM-dd HH:mm:ss"));
    }

    /** 自定义序列化特性 */
    private static final SerializerFeature[] features = {
            /**输出空置字段**/
            SerializerFeature.WriteMapNullValue,
            /**list字段如果为null，输出为[]，而不是null**/
            SerializerFeature.WriteNullListAsEmpty,
            /**数值字段如果为null，输出为0，而不是null**/
            SerializerFeature.WriteNullNumberAsZero,
            /**Boolean字段如果为null，输出为false，而不是null**/
            SerializerFeature.WriteNullBooleanAsFalse,
            /**字符类型字段如果为null，输出为""，而不是null**/
            SerializerFeature.WriteNullStringAsEmpty
    };

    public static String toJSONString(Object object) {
        return JSON.toJSONString(object, config, features);
    }
    public static String toJSONNoFeatures(Object object) {
        return JSON.toJSONString(object, config);
    }

    public static Object toBean(String text) {
        return JSON.parse(text);
    }

    public static <T> T toBean(String text, Class<T> clazz) {
        return JSON.parseObject(text, clazz);
    }

    /**转换为数组**/
    public static <T> Object[] toArray(String text) {
        return toArray(text, null);
    }
    public static <T> Object[] toArray(String text, Class<T> clazz) {
        return JSON.parseArray(text, clazz).toArray();
    }

    /**转换为List**/
    public static <T> List<T> toList(String text, Class<T> clazz) {
        return JSON.parseArray(text, clazz);
    }

    /**将string转化为序列化的json字符串**/
    public static Object textToJson(String text) {
        Object objectJson  = JSON.parse(text);
        return objectJson;
    }

    /**将map转化为string**/
    @SuppressWarnings("rawtypes")
	public static String collectToString(Map m) {
        String s = JSONObject.toJSONString(m);
        return s;
    }
    /**json字符串转化为map**/
    @SuppressWarnings("rawtypes")
	public static Map stringToCollect(String s) {
        Map m = JSONObject.parseObject(s);
        return m;
    }

}
