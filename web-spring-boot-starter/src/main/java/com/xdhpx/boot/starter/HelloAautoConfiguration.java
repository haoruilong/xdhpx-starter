package com.xdhpx.boot.starter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
//@EnableConfigurationProperties({Person.class,SwaggerModel.class})
@EnableConfigurationProperties(Person.class)
public class HelloAautoConfiguration {

    @Autowired
    private Person person;

//    @Autowired
//    private SwaggerModel swaggerModel;


    @Bean
    @ConditionalOnMissingBean(HelloService.class)
    public HelloService helloService(){
        HelloService hs=new HelloService();
        hs.setPerson(person);

//        System.out.println(JSONObject.toJSONString(swaggerModel));

        return hs;
    }


}

/**
 * @EnableConfigurationProperties(Person.class)
 * 该注解是用来开启对 @ConfigurationProperties 注解配置Bean的支持。
 * 也就是@EnableConfigurationProperties注解告诉Spring Boot 能支持@ConfigurationProperties
 * 本例子中把与配置文件绑定的Person类导入到容器；
 *
 *
 * @ConditionalOnMissingBean(HelloService.class)容器里不存在HelloService的bean时候才创建该Bean
 */