package com.xdhpx.boot.starter.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication(scanBasePackages = {"com.xdhpx"})
@MapperScan("com.xdhpx.boot.starter.web.mapper")/**配置扫描mapper位置**/
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
public class SpringbootApplication {

   public static void main(String[] args) {
       SpringApplication.run(SpringbootApplication.class, args);
   }

}