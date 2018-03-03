package com.yph.service.mail.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yph.entity.dict.SysDictRes;
import com.yph.entity.mail.SysMailTemplate;
import com.yph.entity.mail.vo.SysMailTemplateVo;
import com.yph.mapper.mail.SysMailTemplateMapper;
import com.yph.service.dict.ISysDictResService;
import com.yph.service.mail.ISysMailTemplateService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 邮件模版 服务层
 *
 * @author : Administrator Hzhan
 * @create ：2018/1/18
 **/
@Service
public class SysMailTemplateServiceImpl implements ISysMailTemplateService {

    @Autowired
    private SysMailTemplateMapper sysMailTemplateMapper;

    @Autowired
    private ISysDictResService sysDictResService;


    /**
     * 分页  获取邮件模版
     *
     * @param params
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo findSysMailTemplateByPage(HashMap<String, Object> params, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        PageHelper.orderBy(" create_time desc");
        params.put("pageNum", pageNum);
        params.put("pageSize", pageSize);
        List<SysMailTemplateVo> list = sysMailTemplateMapper.findSysMailTemplateByPage(params);
        PageInfo<SysMailTemplateVo> pageInfo = new PageInfo<SysMailTemplateVo>(list);
        // 组合数据
        for (int i = 0; i < pageInfo.getList().size(); i++) {
            pageInfo.getList().set(i, assemble(pageInfo.getList().get(i)));

        }
        return pageInfo;
    }


    /**
     * 设置默认的邮件模版
     *
     * @param id
     * @return
     */
    @Override
    public int updateDefaultTemplageById(Long id, Long type) {
        // 查找默认的模版出来 ，将其状态更变
        SysMailTemplate sysMailTemplateByDefault = findSysMailTemplateByDefault(type);
        if (sysMailTemplateByDefault != null) {
            sysMailTemplateMapper.cancelDefaultTemplageById(sysMailTemplateByDefault.getId());
        }
        return sysMailTemplateMapper.selectDefaultTemplageById(id);
    }

    /**
     * 删除邮件模版
     *
     * @param id
     * @return
     */
    @Override
    public int delSysMailTempateById(Long id) {
        SysMailTemplate sysMailTemplate = new SysMailTemplate();
        sysMailTemplate.setId(id);
        sysMailTemplate.setStatusFlag(new Byte("1"));
        return sysMailTemplateMapper.updateByPrimaryKey(sysMailTemplate);
    }

    /**
     * 批量删除邮件模版
     *
     * @param ids
     * @return
     */
    @Override
    public int batchDelSysMail(Long[] ids) {
        return 0;
    }

    /**
     * 获取默认的模版
     *
     * @return
     */
    @Override
    public SysMailTemplate findSysMailTemplateByDefault(Long type) {
        return sysMailTemplateMapper.findSysMailTemplateByDefault(type);
    }


    /**
     * 邮件类型
     *
     * @param resCode 字典资源code
     * @return
     */
    @Override
    public SysMailTemplate findSysMailTemplateDefaultByType(String resCode) {
        // 获取资源类型
        SysDictRes sysDictRes = sysDictResService.findSysDictResOneByCode(resCode);

        // 获取默认的模板 按照资源code
        SysMailTemplate sysMailTemplate = sysMailTemplateMapper.findSysMailTemplateDefaultByType(sysDictRes.getId());
        return sysMailTemplate;
    }

    /**
     * 更新模版
     *
     * @param sysMailTemplate
     * @return
     */
    @Override
    public int updateSysMailTemplate(SysMailTemplate sysMailTemplate) {
        sysMailTemplate.setStatusFlag(new Byte("1"));
        return sysMailTemplateMapper.updateByPrimaryKeySelective(sysMailTemplate);
    }

    /**
     * 组合数据
     *
     * @param sysMailTemplate
     * @return
     */
    private SysMailTemplateVo assemble(SysMailTemplate sysMailTemplate) {
        SysMailTemplateVo sysMailTemplateVo = new SysMailTemplateVo();
        BeanUtils.copyProperties(sysMailTemplate, sysMailTemplateVo);
        if (sysMailTemplate.getType() != null) {
            SysDictRes sysDictResOne = sysDictResService.findSysDictResOne(Long.valueOf(sysMailTemplate.getType() + ""));
            if (sysDictResOne != null) {
                sysMailTemplateVo.setTypeName(sysDictResOne.getName());
            }
        }
        return sysMailTemplateVo;
    }

}
