package com.yph.service.mail;

import com.github.pagehelper.PageInfo;
import com.yph.entity.mail.SysEmail;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

public interface ISysMailService {

    MimeMessage createMimeMessage(SysEmail email) throws MessagingException, UnsupportedEncodingException;

    void sendMail(SysEmail email) throws UnsupportedEncodingException, MessagingException;


    PageInfo findMailListByPage(HashMap<String, Object> params,Integer pageNum,Integer pageSize);

    int delSysMailById(Long id);

    int reSendMail(Long id);

    int sendEmailToOne(String emailAddr);


    int saveSysEmail(SysEmail sysEmail);

    int sendForgetPassWordMail(String email,String href);
}
