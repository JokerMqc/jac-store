package com.yph.controller.dict;

import com.github.pagehelper.PageInfo;
import com.yph.common.result.CommonResult;
import com.yph.entity.dict.SysDictRes;
import com.yph.entity.tree.ZtreeVo;
import com.yph.service.dict.ISysDictResService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 字典资源控制器
 *
 * @author : Administrator Hzhan
 * @create ：2018/1/18
 **/
@Slf4j
@RestController
@RequestMapping("sys/dict/res")
public class SysDictResController {

    @Autowired
    private ISysDictResService sysDictResService;

    /**
     * 获取字典资源  分页获取
     *
     * @param params
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("findSysDictResListByPage")
    public CommonResult findSysDictResListByPage(@RequestParam HashMap<String, Object> params, Integer pageNum, Integer pageSize) {
        PageInfo pageInfo = sysDictResService.findSysDictResListByPage(params, pageNum, pageSize);
        return CommonResult.SUCCESS(pageInfo);
    }

    /**
     * 删除菜单资源
     *
     * @param id
     * @return
     */
    @RequestMapping("delSysDictResById")
    public CommonResult delSysDictResById(Long id) {
        int result = sysDictResService.delSysDictResById(id);
        if (result < 1) {
            return CommonResult.ERROR("删除失败!");
        }
        return CommonResult.SUCCESS();
    }


    /**
     * 更新字典资源
     *
     * @param sysDictRes
     * @return
     */
    @RequestMapping("updateSysDictRes")
    public CommonResult updateSysDictRes(SysDictRes sysDictRes) {
        int result = sysDictResService.updateSysDictRes(sysDictRes);
        if (result < 1) {
            return CommonResult.ERROR("更新失败!");
        }
        return CommonResult.SUCCESS();
    }

    /**
     * 新增字典资源
     *
     * @param sysDictRes
     * @return
     */
    @RequestMapping("saveSysDictRes")
    public CommonResult saveSysDictRes(SysDictRes sysDictRes) {
        int result = sysDictResService.saveSysDictRes(sysDictRes);
        if (result < 1) {
            return CommonResult.ERROR("保存失败!");
        }
        return CommonResult.SUCCESS();
    }


    /**
     * 批量删除字典资源
     *
     * @param request
     * @return
     */
    @RequestMapping("batchDelSysDictRes")
    public CommonResult batchDelSysDictRes(HttpServletRequest request) {
        Long[] ids = new Long[10];
        int result = sysDictResService.batchDelSysDictRes(ids);
        if (result < 1) {
            return CommonResult.ERROR("删除失败!");
        }
        return CommonResult.SUCCESS();
    }


    /**
     * 根据字典类型获取字典资源
     *
     * @param typeId
     * @return
     */
    @RequestMapping("findSysDictResListByTypeId")
    public CommonResult findSysDictResListByTypeId(Long typeId) {
        Assert.notNull("字典类型 Id 不能为空! ");
        List<SysDictRes> list = sysDictResService.findSysDictResListByTypeId(typeId);
        return CommonResult.SUCCESS(list);
    }


    /**
     *  获取所有的字典资源 ztree
     * @return
     */
    @RequestMapping("findAllSysDictResByZtree")
    public CommonResult findAllSysDictResByZtree(){
        List<ZtreeVo> list=sysDictResService.findAllSysDictResByZtree();
        return CommonResult.SUCCESS(list);
    }



}































