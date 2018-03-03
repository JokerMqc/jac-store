package com.yph.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;

/**
 * 请求日志 --->
 *
 * @author : Administrator Hzhan
 * @create ：2018/1/16
 **/
@Slf4j
//@Aspect
//@Component
public class RequestAspect {

    /**
     * 环绕 controller  打印参数
     *
     * @param jp
     * @return
     */
    @Around("execution(* com.yph.controller.*.*.*(..))")
    public Object showLog(ProceedingJoinPoint jp) throws Throwable {
        String clazzName = jp.getTarget().getClass().getSimpleName();
        String methodName = jp.getSignature().getName();
        Object[] args = jp.getArgs();

        StringBuilder argStr = new StringBuilder();
        for (Object obj : args) {
            argStr.append(obj.toString()).append(" , ");
        }
        log.debug("[ 请求的控制器 : {}  ------> 请求的方法: {}   ]", clazzName,methodName);
        log.debug("[ 请求的参数  ] -----------> {}", argStr.toString());
        return jp.proceed(args);
    }
}
