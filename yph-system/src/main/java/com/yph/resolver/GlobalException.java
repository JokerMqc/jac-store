package com.yph.resolver;

import com.yph.common.cache.IRedisService;
import com.yph.common.exception.LoginOverTimeException;
import com.yph.entity.log.SysExceptionLog;
import com.yph.entity.mail.SysEmail;
import com.yph.entity.mail.SysMailTemplate;
import com.yph.service.log.ISysExceptionLogService;
import com.yph.service.mail.ISysMailService;
import com.yph.service.mail.ISysMailTemplateService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;

/**
 * 全局异常步骤
 *
 * @author : Administrator Hzhan
 * @create ：2017/12/25
 **/
@Slf4j
public class GlobalException implements HandlerExceptionResolver {

    @Autowired
    private IRedisService redisService;

    @Autowired
    private ISysExceptionLogService sysExceptionLogService;

    @Autowired
    private ISysMailService sysMailService;

    @Autowired
    private ISysMailTemplateService sysMailTemplateService;

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) {
        ModelAndView mv = new ModelAndView();

        StackTraceElement[] stackTrace = e.getStackTrace();

        StackTraceElement stackTraceElement = stackTrace[0];

        String className = stackTraceElement.getClassName();

        String methodName = stackTraceElement.getMethodName();

        String exceptionMsg = "";
        if (!StringUtils.isBlank(e.getMessage())) {
            exceptionMsg = e.getMessage();
        } else {
            exceptionMsg = e.toString();
        }

        if (true) {
            saveExceptionLog(className, methodName, exceptionMsg);
        }

        log.debug("[ 异常捕获 ] -------> 类 : {}   方法名 : {}   原因 : {}  ", className, methodName, exceptionMsg);

        MappingJackson2JsonView jsonView = new MappingJackson2JsonView();
        HashMap<String, Object> errorMap = new HashMap<>();
        if (!StringUtils.isBlank(e.getMessage())) {
            errorMap.put("msg", e.getMessage());
        } else {
            errorMap.put("msg", e.toString());
        }


        if (e instanceof LoginOverTimeException) {
            // 处理 ajax 登录超时后的操作。
            errorMap.put("code", "2");
            jsonView.setAttributesMap(errorMap);
            mv.setView(jsonView);
        } else if (e instanceof RuntimeException) {
            errorMap.put("code", "1");
            jsonView.setAttributesMap(errorMap);
            mv.setView(jsonView);
        } else {
            mv.setViewName("error/error500.jsp");
        }

        return mv;
    }


    /**
     * 保存异常日志。
     *
     * @param className
     * @param methodName
     * @param exceptionMsg
     */
    private void saveExceptionLog(final String className, final String methodName, final String exceptionMsg) {
        //  缓存key
        final String key = getCacheKey(className, methodName, exceptionMsg);

        SysExceptionLog selectSysExceptionLog = (SysExceptionLog) redisService.get(key);

        if (selectSysExceptionLog == null) {
            // 线程后去操作。
            new Thread(new Runnable() {
                @Override
                public void run() {
                    SysExceptionLog sysExceptionLog = new SysExceptionLog();
                    sysExceptionLog.setCalzzName(className);
                    sysExceptionLog.setExceptionMsg(exceptionMsg);
                    sysExceptionLog.setMethodName(methodName);
                    sysExceptionLogService.saveSysExceptionLog(sysExceptionLog);
                    long exprieTime = 60 * 60 * 12;
                    redisService.set(key, sysExceptionLog, exprieTime);
                    if (true) {
                        sendExceptionMsgToEmail(sysExceptionLog);
                    }
                }
            }).start();
        }
    }

    /**
     * 邮件通知 管理员
     *
     * @param sysExceptionLog
     */
    private void sendExceptionMsgToEmail(SysExceptionLog sysExceptionLog) {
        // 获取邮件模板
        SysMailTemplate sysMailTemplate = sysMailTemplateService.findSysMailTemplateDefaultByType("EXCEPTION_RES");

        SysEmail sysEmail = new SysEmail();
        sysEmail.setToEmails("2016271115@qq.com");
        sysEmail.setSubject("  [系统异常]  通知邮件 ");
        sysEmail.setFromName(" 系统邮件 ");
        sysEmail.setFromEmail("jon8957@163.com");
        StringBuilder msg = new StringBuilder();
        msg.append("异常类 : ").append(sysExceptionLog.getCalzzName())
                .append(" 异常方法 : ").append(sysExceptionLog.getMethodName())
                .append(" 异常原因 : ").append(sysExceptionLog.getExceptionMsg());
        String content = sysMailTemplate.getContent().replace("{{ exceptionMsg }}", msg);
        sysEmail.setContext(content);
//        try {
//            sysMailService.sendMail(sysEmail);
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//            log.debug(" 通过邮件发送异常信息给管理员 失败---->异常 ");
//        } catch (MessagingException e) {
//            e.printStackTrace();
//            log.debug(" 通过邮件发送异常信息给管理员 失败---->异常 ");
//        }
    }

    /**
     * 获取 缓存key
     *
     * @param className
     * @param methodName
     * @param exceptionMsg
     * @return
     */
    private String getCacheKey(String className, String methodName, String exceptionMsg) {
        StringBuilder key = new StringBuilder();
        key.append(className).append(":");
        key.append(methodName).append(":");
        key.append(exceptionMsg);
        return key.toString();
    }


}
