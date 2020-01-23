package com.xdhpx.boot.starter.common.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.Serializable;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "swaggerModel", description = "Swagger配置Model")
@ConfigurationProperties(prefix = "xdhpx.starter.swagger",ignoreInvalidFields = true)
/**Bean类在容器不存在才会去创建**/
@ConditionalOnMissingBean(SwaggerModel.class)
public class SwaggerModel implements Serializable {

    @ApiModelProperty(value="标题")
    private String title;

    @ApiModelProperty(value="描述信息")
    private String descInfo;

    @ApiModelProperty(value="作者")
    private String author;

    @ApiModelProperty(value="版本")
    private String version;

}
