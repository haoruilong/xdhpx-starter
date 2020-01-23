package com.xdhpx.boot.starter.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.Charset;
import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {


    /**
     * 	消息转化器
     */
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        /**声明fastjson消息转化器**/
        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();

        /**声明默认配置规则**/
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat,
                SerializerFeature.WriteNullStringAsEmpty,
                SerializerFeature.WriteNullNumberAsZero,
                SerializerFeature.WriteNullListAsEmpty,
                SerializerFeature.WriteMapNullValue);

        /**配置规则添加到转换器中**/
        fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);

        /**把自定义的替换SpringBoot默认的**/
        converters.add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
        converters.add(fastJsonHttpMessageConverter);
    }

    /**
     * 	资源匹配设置,值为false表示favorPathExtension表示支持后缀匹配
     */
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.favorPathExtension(false);
    }

    /**
     * 	跨域设置
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        /**设置允许跨域的路径**/
        registry.addMapping("/**")
                /**设置允许跨域请求的域名**/
                .allowedOrigins("*")
                /**是否允许证书 不再默认开启**/
                .allowCredentials(true)
                /**设置允许的方法,可指定方法allowedMethods("GET", "POST", "PUT", "DELETE")**/
                .allowedMethods("*")
                /**设置允许的请求头列表，“*”表示允许所有的headers**/
                .allowedHeaders("*")
                /**设置在响应header中设置客户端可见的header**/
                .exposedHeaders("access-control-allow-headers",
                        "access-control-allow-methods",
                        "access-control-allow-origin",
                        "access-control-max-age",
                        "X-Frame-Options")
                /**跨域允许时间**/
                .maxAge(3600);
    }

}
