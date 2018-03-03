package com.yph.controller.email;

import com.github.pagehelper.PageInfo;
import com.yph.common.result.CommonResult;
import com.yph.service.mail.ISysMailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * 邮件控制器
 *
 * @author : Administrator Hzhan
 * @create ：2018/1/18
 **/
@Slf4j
@RestController
@RequestMapping("sys/email/")
public class SysEmailController {

    @Autowired
    private ISysMailService sysMailService;

    /**
     *  删除邮件
     * @param id
     * @return
     */
    @RequestMapping("delSysMailById")
    public CommonResult delSysMailById(Long id){
       int result =  sysMailService.delSysMailById(id);
       if(result<1){
           return CommonResult.ERROR("删除失败!");
       }
       return CommonResult.SUCCESS();
    }

    /**
     *  重新发送邮件
     * @param id
     * @return
     */
    @RequestMapping("reSendMail")
    public CommonResult reSendMail(Long id){
       int result =  sysMailService.reSendMail(id);
       if(result<1){
           return CommonResult.ERROR("发送失败");
       }
       return CommonResult.SUCCESS();
    }


    /**
     *  查询发送的邮件列表
     * @param params
     * @return
     */
    @RequestMapping("findMailListByPage")
    public CommonResult findMailListByPage(@RequestParam HashMap<String,Object> params,Integer pageNum,Integer pageSize){
      PageInfo pageInfo = sysMailService.findMailListByPage(params,pageNum,pageSize);
      return CommonResult.SUCCESS(pageInfo);
    }

    /**
     *  发送邮件到指定地址
     * @return
     */
    @RequestMapping("sendEmailToOne")
    public CommonResult sendEmailToOne(String emailAddr,String valiCode){
        int result =  sysMailService.sendEmailToOne(emailAddr);
        if(result<1){
            return CommonResult.ERROR("发送失败 !请重试或者联系管理员。 ");
        }
        return CommonResult.SUCCESS();
    }

    /**
     *
     * @param email
     * @param validCode
     * @return
     */
//    @RequestMapping("sendForgetPassWordMail")
//    public CommonResult sendForgetPassWordMail(String email ,String validCode){
//
//
//
//        return CommonResult.SUCCESS();
//    }




}
