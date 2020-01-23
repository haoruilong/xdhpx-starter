package com.xdhpx.boot.starter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName Person
 * @Description 演示bean
 * @Author xdhpx
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ConfigurationProperties(prefix = "xdhpx.starter.person",ignoreInvalidFields = true)
public class Person {
    private int age;
    private String name;
    private String gender;
}

/**
 * @ConfigurationProperties可以将外部配置文件
 * （比如applicaition.properties或者yml文件）加载进来
 * 将指定前缀的配置文件的数据，填充对象的对应字段的数据，然后供Bean使用
 * 指定前缀就是prefix后面的值
 * ignoreInvalidFields 默认是false
 * 设置成ture是为了避免万一属性配置错误的时候，不希望SpringBoot应用启动失败
 */