package com.xdhpx.boot.starter.aop;


import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.xdhpx.boot.starter.aop.annotation.RequestLogAnnotation;
import com.xdhpx.boot.starter.common.model.LogModel;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@Aspect
@Component
public class RequestLogAspect {

    @Autowired
    private HttpServletRequest request;

    /**切入点表达式决定了用注解方式的方法切还是针对某个路径下的所有类和方法进行切，方法必须是返回void类型**/
    @Pointcut("@annotation(com.xdhpx.boot.starter.aop.annotation.RequestLogAnnotation)")
    public void pointCut(){};


    @Around(value = "pointCut()")
    public Object around(ProceedingJoinPoint pjp) {

        /**创建StopWatch计时器,并赋值一个ID**/
        StopWatch stopwatch = new StopWatch(UUID.randomUUID().toString());

        /**request解析计时开始**/
        stopwatch.start("request");

        /**从JoinPoint获取Method**/
        Method method = ((MethodSignature) pjp.getSignature()).getMethod();

        /**拼装日志model**/
        LogModel logModel = LogModel.builder()
                .moduleName(method.getAnnotation(RequestLogAnnotation.class).moduleName())
                .descInfo(method.getAnnotation(RequestLogAnnotation.class).descInfo())
                .requestUrl(request.getRequestURL().toString())
                .requestType(request.getMethod())
                .build();

        /**请求参数**/
        Object[] args = pjp.getArgs();
        if ("POST".equals(logModel.getRequestType())) {
            logModel.setRequestParam(JSONObject.toJSONString(args[0], SerializerFeature.WriteMapNullValue));
        } else if ("GET".equals(logModel.getRequestType())) {
            logModel.setRequestParam(request.getQueryString());
        }

        /**request解析计时结束**/
        stopwatch.stop();
        log.info("\n计时任务ID：{}\n模块：{}\n描述：{}\n路径: {}\n请求方式: {}\n参数：{}",
                stopwatch.getId(),
                logModel.getModuleName(), logModel.getDescInfo(),
                logModel.getRequestUrl(),logModel.getRequestType(),logModel.getRequestParam());

        try {

            /**result结果获取计时开始**/
            stopwatch.start("result");

            /**获取返回结果**/
            logModel.setRequestResult(pjp.proceed());

            /**result结果获取计时结束**/
            stopwatch.stop();
            /**结果不为空，且级别是INFO输出**/
            if (!Objects.isNull(logModel.getRequestResult()) && log.isInfoEnabled()) {
                log.info("\nSUCCESS==>\n计时任务ID：{}\n结果：{}\n耗时：{}\n耗时分析：{}",
                        stopwatch.getId(),JSONObject.toJSONString(logModel.getRequestResult()), stopwatch.getTotalTimeMillis(),stopwatch.prettyPrint());
            }
        } catch (Throwable throwable) {
            /**如果计时还在，停止计时**/
            if (stopwatch.isRunning()){
                stopwatch.stop();
            }
            log.info("\nERROR==>\n计时任务ID：{}\n异常信息：{}\n耗时：{}\n耗时分析：{}",
                    stopwatch.getId(), throwable.getMessage(), stopwatch.getTotalTimeMillis(),stopwatch.prettyPrint());
        }

        return logModel.getRequestResult();
    }


}
