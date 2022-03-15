package com.xkyz.xinke.common;

import com.xkyz.xinke.service.UserOrderService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;


/**
* @author Klein
* @description 切面日志配置
*/
@Aspect
@Component
public class LogAsPect {
    
    private final static Logger log = org.slf4j.LoggerFactory.getLogger(LogAsPect.class);

    @Autowired
    private UserOrderService userOrderService;
    
    //表示匹配带有自定义注解的方法
    @Pointcut("@annotation(com.web.springbootaoplog.config.Log)")
    public void pointcut() {}

    @Pointcut("execution(public * com.xkyz.xinke.service..*.*(..))")
    public void pointcutController() {}


    @Before("pointcutController()")
    public void around2(JoinPoint point) {
        //获取目标方法
        String methodNam = point.getSignature().getDeclaringTypeName() + "." + point.getSignature().getName();

        //获取方法参数
        String params = Arrays.toString(point.getArgs());

        log.info("get in {} params :{}",methodNam,params);
    }

//    @Around("pointcut()")
//    public Object around(ProceedingJoinPoint point) {
//        Object result =null;
//        long beginTime = System.currentTimeMillis();
//
//        try {
//            log.info("我在目标方法之前执行！");
//            result = point.proceed();
//            long endTime = System.currentTimeMillis();
//            insertLog(point,endTime-beginTime);
//        } catch (Throwable e) {
//            // TODO Auto-generated catch block
//        }
//        return result;
//    }
    
}