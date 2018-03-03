package com.yph.service.mail.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yph.common.cache.IRedisService;
import com.yph.entity.dict.SysDictRes;
import com.yph.entity.mail.SysEmail;
import com.yph.entity.mail.SysMailTemplate;
import com.yph.mapper.mail.SysEmailMapper;
import com.yph.service.dict.ISysDictResService;
import com.yph.service.mail.ISysMailService;
import com.yph.service.mail.ISysMailTemplateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * 邮件
 *
 * @author : Administrator Hzhan
 * @create ：2018/1/17
 **/
@Slf4j
@Service
public class SysMailServiceImpl implements ISysMailService {

    @Autowired
    private JavaMailSenderImpl javaMailSenderImpl;

    @Autowired
    private SysEmailMapper sysEmailMapper;

    @Autowired
    private IRedisService redisService;

    @Autowired
    private ISysMailTemplateService sysMailTemplateService;

    /**
     *  分页查询邮件
     * @param params
     * @return
     */
    @Override
    public PageInfo findMailListByPage(HashMap<String, Object> params,Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        PageHelper.orderBy(" create_time desc");

        params.put("pageNum",pageNum);
        params.put("pageSize",pageSize);
        List<SysEmail> list =  sysEmailMapper.findMailListByPage(params);
        PageInfo<SysEmail> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    /**
     *  删除邮件
     * @param id
     * @return
     */
    @Override
    public int delSysMailById(Long id) {
        return 0;
    }

    /**
     *  重新发邮件
     * @param id
     * @return
     */
    @Override
    public int reSendMail(Long id) {
        return 0;
    }

    /**
     *  发送单封邮件
     * @param emailAddr
     * @return
     */
    @Override
    public int sendEmailToOne(String emailAddr) {

        SysEmail sysEmail = new SysEmail();
        sysEmail.setContext("这是一份测试的邮件发送。。。。。。。。。");
        sysEmail.setCreateTime(new Date());
        sysEmail.setFlag(new Byte("0"));
        sysEmail.setFromEmail("15577001565@163.com");
        sysEmail.setFromName("云品汇公司");
        sysEmail.setSubject("这是邮件测试");
        sysEmail.setToEmails(emailAddr);

        // 保存 发送记录
        saveSysEmail(sysEmail);

        try {
            sendMail(sysEmail);
        } catch (Exception e) {
            log.debug("[ 邮件发送失败 !]");
            return 0;
        }
        return 1;
    }

    /**
     *  保存邮件 记录
     * @param sysEmail
     * @return
     */
    @Override
    public int saveSysEmail(SysEmail sysEmail) {
        sysEmail.setCreateTime(new Date());
        sysEmail.setFlag(new Byte("1"));
        return sysEmailMapper.insert(sysEmail);
    }

    /**
     *
     * @param email
     * @return
     */
    @Override
    public int sendForgetPassWordMail(String email,String href) {

        SysMailTemplate forgetPasswordTemplate = sysMailTemplateService.findSysMailTemplateDefaultByType("MODIFY_RES");
        SysEmail sysEmail = new SysEmail();

        UUID uuid = UUID.randomUUID();
        href = href+"?uid="+uuid;
        log.debug("[  修改密码地址链接 : {}]",href);

        // 5分钟的时间
        redisService.set("forgetPassword",uuid, (long) 60*5);

        String content = forgetPasswordTemplate.getContent().replace("{{href}}", href);
        sysEmail.setContext(content);
        sysEmail.setFromEmail("jon8957@163.com");
        sysEmail.setSubject("找回密码");
        sysEmail.setToEmails(email);
        sysEmail.setFromName("云品汇   ");
        try {
            sendMail(sysEmail);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            log.debug("[ 忘记密码时 发送邮件异常!  ]------------>  不支持编码 ");
            return 0;
        } catch (MessagingException e) {
            e.printStackTrace();
            log.debug("[ 忘记密码时 发送邮件异常!  ]------------>  发送失败 ");
            return 0;
        }
        return 1;
    }

    /**
     *
     * @return
     */
    public int updateSysEmail(SysEmail sysEmail){
      return   sysEmailMapper.updateByPrimaryKey(sysEmail);
    }



    /**
     *  创建邮件信息
     * @param email
     * @return
     * @throws MessagingException
     * @throws UnsupportedEncodingException
     */
    @Override
    public MimeMessage createMimeMessage(SysEmail email) throws MessagingException, UnsupportedEncodingException{
        MimeMessage mimeMessage = javaMailSenderImpl.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        messageHelper.setFrom(email.getFromEmail(), email.getFromName());
        messageHelper.setSubject(email.getSubject());
        messageHelper.setTo(email.getToEmails());
        messageHelper.setText(email.getContext(), true);
        return mimeMessage;
    }


    /**
     *  发送邮件
     * @param email
     * @throws UnsupportedEncodingException
     * @throws MessagingException
     */
    @Override
    public void sendMail(SysEmail email) throws UnsupportedEncodingException, MessagingException {
        int id = saveSysEmail(email);
        MimeMessage msg = createMimeMessage(email);
        javaMailSenderImpl.send(msg);
        log.debug("[ 发送邮件成功 ! ]");
        email.setId(Long.valueOf(id+""));
        updateSysEmail(email);
    }


}
