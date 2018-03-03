package com.yph.common.exception;

/**
 * 登录超时异常
 *
 * @author : Administrator Hzhan
 * @create ：2018/1/22
 **/
public class LoginOverTimeException extends RuntimeException {

    public LoginOverTimeException(String message) {
        super(message);
    }

}
