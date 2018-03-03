package com.yph.controller.dict;

import com.github.pagehelper.PageInfo;
import com.yph.common.result.CommonResult;
import com.yph.entity.dict.SysDictType;
import com.yph.service.dict.ISysDictTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * 字典类型控制器
 *
 * @author : Administrator Hzhan
 * @create ：2018/1/18
 **/
@Slf4j
@RestController
@RequestMapping("sys/dict/type")
public class SysDictTypeController {

    @Autowired
    private ISysDictTypeService sysDictTypeService;


    /**
     * 保存字典类型
     *
     * @param sysDictType
     * @return
     */
    @RequestMapping("saveSysDictType")
    public CommonResult saveSysDictType(SysDictType sysDictType) {
        int result = sysDictTypeService.saveSysDictType(sysDictType);
        if (result < 1) {
            return CommonResult.ERROR("保存字典类型失败 !");
        }
        return CommonResult.SUCCESS();
    }

    /**
     * 删除字典类型
     *
     * @param id
     * @return
     */
    @RequestMapping("delSysDictTypeById")
    public CommonResult delSysDictTypeById(Long id) {
        int result = sysDictTypeService.delSysDictTypeById(id);
        if (result < 1) {
            return CommonResult.ERROR("删除字典类型失败 !");
        }
        return CommonResult.SUCCESS();
    }

    /**
     * 更新字典类型
     *
     * @param sysDictType
     * @return
     */
    @RequestMapping("updateSysDictType")
    public CommonResult updateSysDictType(SysDictType sysDictType) {
        int result = sysDictTypeService.updateSysDictType(sysDictType);
        if (result < 1) {
            return CommonResult.ERROR("更新字典类型失败 !");
        }
        return CommonResult.SUCCESS();
    }


    /**
     * 查询字典类型 分页
     *
     * @param params
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("findSysDictTypeListByPage")
    public CommonResult findSysDictTypeListByPage(@RequestParam HashMap<String, Object> params, Integer pageNum, Integer pageSize) {
        PageInfo pageInfo = sysDictTypeService.findSysDictTypeListByPage(params, pageNum, pageSize);
        return CommonResult.SUCCESS(pageInfo);
    }


}
