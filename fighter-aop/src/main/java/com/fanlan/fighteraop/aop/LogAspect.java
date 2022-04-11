package com.fanlan.fighteraop.aop;


import com.alibaba.fastjson.JSONObject;
import com.fanlan.fighteraop.vo.ResultVO;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class LogAspect {

    private final Logger logger=LoggerFactory.getLogger(LogAspect.class);
    /**
     * 切入点
     */
    @Pointcut("execution(* com.fanlan.fighteraop.controller..*.*(..))")
    public void controllerMethod() {
    }


    @Before("controllerMethod()")
    public void LogRequestInfo(JoinPoint joinPoint) throws Exception {

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        HttpServletRequest request = attributes.getRequest();

        StringBuilder requestLog = new StringBuilder();

        Signature signature = joinPoint.getSignature();

        /**
         * 编译参数
         */
        requestLog.append("requestMessage：")
                .append("ip={").append(request.getRemoteAddr()).append("},")
                .append("requestMethod = {").append(request.getMethod()).append("},")
                .append("interfaceName = {").append(signature.getDeclaringTypeName()+".").append(signature.getName()).append("},")
                .append("url= {").append(request.getRequestURL()).append("},");
        /**
         * [name="zhangsan"]
         *
         * name 请求参数
         * zhangsan 请求数据
         */
        // 处理请求参数
        String[] paramNames = ((MethodSignature) signature).getParameterNames();
        // 处理请求数据
        Object[] paramValues = joinPoint.getArgs();

        int paramLength = null == paramNames ? 0 : paramNames.length;
        if (paramLength == 0) {
            requestLog.append("requestArge = {} ");
        } else {
            requestLog.append("requestArge = [");
            for (int i = 0; i < paramLength - 1; i++) {
                requestLog.append(paramNames[i]).append("=").append(JSONObject.toJSONString(paramValues[i])).append(",");
            }
            requestLog.append(paramNames[paramLength - 1]).append("=").append(JSONObject.toJSONString(paramValues[paramLength - 1])).append("]");
        }

        logger.info(requestLog.toString());
    }
    /**
     * 方法执行后
     *
     * @param resultVO
     * @throws Exception
     */
    @AfterReturning(returning = "resultVO", pointcut = "controllerMethod()")
    public void logResultVOInfo(ResultVO resultVO) throws Exception {
        logger.info("requestResult：" + "resultCode:{"+resultVO.getCode() + "},resultMessage:{" + resultVO.getMsg()+"},resultJson{"+resultVO.getData()+"}");
    }
}
