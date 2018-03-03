package com.yph.controller.email;

import com.github.pagehelper.PageInfo;
import com.yph.common.result.CommonResult;
import com.yph.entity.mail.SysMailTemplate;
import com.yph.service.mail.ISysMailTemplateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * 邮件模版
 *
 * @author : Administrator Hzhan
 * @create ：2018/1/18
 **/
@Slf4j
@RestController
@RequestMapping("sys/mail/template")
public class SysMailTemplateController {

    @Autowired
    private ISysMailTemplateService sysMailTemplateService;

    /**
     * 获取默认的模版
     *
     * @return
     */
    @RequestMapping("findSysMailTemplateByDefault")
    public CommonResult findSysMailTemplateByDefault(Long type) {
        SysMailTemplate sysMailTemplate = sysMailTemplateService.findSysMailTemplateByDefault(type);
        return CommonResult.SUCCESS(sysMailTemplate);
    }


    /**
     * 获取邮件模版  分页
     *
     * @param params
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("findSysMailTemplateByPage")
    public CommonResult findSysMailTemplateByPage(@RequestParam HashMap<String, Object> params, Integer pageNum, Integer pageSize) {
        PageInfo pageInfo = sysMailTemplateService.findSysMailTemplateByPage(params, pageNum, pageSize);
        return CommonResult.SUCCESS(pageInfo);
    }

    /**
     * 选择默认的邮件模版
     *
     * @param id
     * @return
     */
    @RequestMapping("updateDefaultTemplateById")
    public CommonResult updateDefaultTemplateById(Long id, Long type) {
        int result = sysMailTemplateService.updateDefaultTemplageById(id, type);
        if (result < 1) {
            return CommonResult.ERROR("设置默认的邮件模版失败");
        }
        return CommonResult.SUCCESS();
    }

    /**
     * 取消默认
     *
     * @param sysMailTemplate
     * @return
     */
    @RequestMapping("cancelDufaultTemplate")
    public CommonResult cancelDufaultTemplate(SysMailTemplate sysMailTemplate) {
        int result = sysMailTemplateService.updateSysMailTemplate(sysMailTemplate);
        if (result < 1) {
            return CommonResult.ERROR("取消默认的邮件模版失败");
        }
        return CommonResult.SUCCESS();
    }


    /**
     * 删除邮件模版
     *
     * @param id
     * @return
     */
    @RequestMapping("delSysMailTempateById")
    public CommonResult delSysMailTempateById(Long id) {
        int result = sysMailTemplateService.delSysMailTempateById(id);
        if (result < 1) {
            return CommonResult.ERROR("删除邮件模版失败!");
        }
        return CommonResult.SUCCESS();
    }

    /**
     * @param request
     * @return
     */
    @RequestMapping("batchDelSysMail")
    public CommonResult batchDelSysMail(HttpServletRequest request) {

        Long[] ids = new Long[10];

        int result = sysMailTemplateService.batchDelSysMail(ids);
        if (result < 1) {
            return CommonResult.ERROR("批量删除邮件模版失败!");
        }
        return CommonResult.SUCCESS();
    }


}
