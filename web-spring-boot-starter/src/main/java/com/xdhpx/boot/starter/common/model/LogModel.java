package com.xdhpx.boot.starter.common.model;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "LogModel", description = "日志model")
/**Bean类在容器不存在才会去创建**/
@ConditionalOnMissingBean(LogModel.class)
public class LogModel implements Serializable {

    @ApiModelProperty(value="模块名称")
    private String moduleName;

    @ApiModelProperty(value="描述信息")
    private String descInfo;

    @ApiModelProperty(value="请求地址")
    private String requestUrl;

    @ApiModelProperty(value="请求方式")
    private String requestType;

    @ApiModelProperty(value="请求参数")
    private String requestParam;

    @ApiModelProperty(value="请求IP地址")
    private String requestIp;

    @ApiModelProperty(value="请求耗时")
    private String requestTimeLength;

    @ApiModelProperty(value="请求结果")
    private Object requestResult;

}
