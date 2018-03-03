package com.yph.service.mail;

import com.github.pagehelper.PageInfo;
import com.yph.entity.mail.SysMailTemplate;

import java.util.HashMap;

public interface ISysMailTemplateService {

    PageInfo findSysMailTemplateByPage(HashMap<String, Object> params, Integer pageNum, Integer pageSize);

    int updateDefaultTemplageById(Long id , Long Type);

    int delSysMailTempateById(Long id);

    int batchDelSysMail(Long[] ids);

    SysMailTemplate findSysMailTemplateByDefault(Long type);


    SysMailTemplate findSysMailTemplateDefaultByType(String resCode);

    int updateSysMailTemplate(SysMailTemplate sysMailTemplate);

}
