package com.xdhpx.boot.starter.config;

import com.xdhpx.boot.starter.common.model.SwaggerModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.List;


@Configuration
@EnableSwagger2
/**加载配置文件model类**/
@EnableConfigurationProperties(SwaggerModel.class)
/**控制swagger是否开启**/
@ConditionalOnProperty(prefix = "xdhpx.starter.swagger",value = {"isOpen"},havingValue = "true")
public class SwaggerConfig {

    @Autowired
    private SwaggerModel swaggerModel;

    /**
     * @Description swagger配置
     * @Param
     * @Return springfox.documentation.spring.web.plugins.Docket
     * @Exception
     * @Author 郝大龙
     * @Date 2019-12-30 16:55
     **/
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                /**扫描所有有api注解的**/
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build()
                /**添加固定参数**/
                .globalOperationParameters(checkParams());
    }

    /**
     * @Description 文档描述
     * @Param
     * @Return springfox.documentation.service.ApiInfo
     * @Exception
     * @Author 郝大龙
     * @Date 2019-12-30 16:55
     **/
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(swaggerModel.getTitle())
                .description(swaggerModel.getDescInfo())
                /**联系：作者，网址，邮箱**/
                .contact(new Contact(swaggerModel.getAuthor(), null, null))
                .version(swaggerModel.getVersion())
                .build();
    }


    /**
     * @Description 添加固定参数
     * @Param
     * @Return java.util.List<springfox.documentation.service.Parameter>
     * @Exception
     * @Author 郝大龙
     * @Date 2020-01-11 14:22
     **/
    private List<Parameter> checkParams(){

        ParameterBuilder sing = new ParameterBuilder();
        sing.name("sign").description("加密秘钥").modelRef(new ModelRef("string")).parameterType("header").required(true).build();

        ParameterBuilder channel = new ParameterBuilder();
        channel.name("channel").description("渠道标记").modelRef(new ModelRef("string")).parameterType("header").required(true).build();

        return Arrays.asList(sing.build(),channel.build());
    }

}
