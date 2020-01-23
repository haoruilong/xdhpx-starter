package com.xdhpx.boot.starter.config;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
/**其作用顾名思义，就是将一个配置类在另一个配置类之后加载。
 * 在加载配置的类之后再加载当前类**/
@AutoConfigureAfter(RedisAutoConfiguration.class)
/**控制redis是否开启**/
@ConditionalOnProperty(prefix = "xdhpx.starter.redis",value = {"isOpen"},havingValue = "true")
public class RedisConfig {



    /**
     * 配置自定义redisTemplate
     * @return
     */
    @Bean
    RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {


        /**
         * ObjectMapper 是一个使用 Swift 语言编写的数据模型转换框架
         * 我们可以方便的将模型对象转换为JSON
         **/
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);


        /**使用Jackson2JsonRedisSerialize 替换默认序列化(默认采用的是JDK序列化)**/
        Jackson2JsonRedisSerializer<Object> serializer = new Jackson2JsonRedisSerializer<>(Object.class);
        serializer.setObjectMapper(mapper);


        /**声明RedisTemplate**/
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        template.setValueSerializer(serializer);
        /**
         * 对比StringRedisSerializer，Jackson2JsonRedisSerializer和JdkSerializationRedisSerializer三种方式
         * 用StringRedisSerializer进行序列化的redis的key值，在Java和Redis中保存的内容是一样的
         **/
        /**使用StringRedisSerializer来序列化和反序列化redis的key值**/
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());

        template.setHashValueSerializer(serializer);
        template.afterPropertiesSet();
        return template;
    }


    /**
     * RedisConnectionFactory 从哪里来？
     * 是在@AutoConfigureAfter(RedisAutoConfiguration.class)这个注解的
     * RedisAutoConfiguration类里面的@Import注解里面的
     * LettuceConnectionConfiguration配置里面的转配的
     **/




}