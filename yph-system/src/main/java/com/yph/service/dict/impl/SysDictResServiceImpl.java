package com.yph.service.dict.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yph.entity.dict.SysDictRes;
import com.yph.entity.dict.SysDictType;
import com.yph.entity.tree.ZtreeVo;
import com.yph.mapper.dict.SysDictResMapper;
import com.yph.service.dict.ISysDictResService;
import com.yph.service.dict.ISysDictTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * 字典资源
 *
 * @author : Administrator Hzhan
 * @create ：2018/1/18
 **/
@Slf4j
@Service
public class SysDictResServiceImpl implements ISysDictResService {

    @Autowired
    private SysDictResMapper sysDictResMapper;

    @Autowired
    private ISysDictTypeService sysDictTypeService;


    /**
     *  分页查询
     * @param params
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo findSysDictResListByPage(HashMap<String, Object> params, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        PageHelper.orderBy(" create_time desc ");
        params.put("pageNum",pageNum);
        params.put("pageSize",pageSize);
        List<SysDictRes> list = sysDictResMapper.findSysDictResListByPage(params);
        PageInfo<SysDictRes> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    /**
     *  通过Id 删除字典资源
     * @param id
     * @return
     */
    @Override
    public int delSysDictResById(Long id) {
        return sysDictResMapper.delSysDictResById(id);
    }

    /**
     *  更新字典资源
     * @param sysDictRes
     * @return
     */
    @Override
    public int updateSysDictRes(SysDictRes sysDictRes) {
        return sysDictResMapper.updateByPrimaryKeySelective(sysDictRes);
    }

    /**
     *  保存字典资源
     * @param sysDictRes
     * @return
     */
    @Override
    public int saveSysDictRes(SysDictRes sysDictRes) {
        return sysDictResMapper.insert(sysDictRes);
    }

    /**
     *  批量删除
     * @param ids
     * @return
     *  TODO : 未实现
     */

    @Override
    public int batchDelSysDictRes(Long[] ids) {
        return 0;
    }

    /**
     *  通过字典类型 获取字典资源
     * @param typeId
     * @return
     */
    @Override
    public List<SysDictRes> findSysDictResListByTypeId(Long typeId) {
        return sysDictResMapper.findSysDictResListByTypeId(typeId);
    }

    /**
     *   获取所有的字典资源
     * @return
     */
    @Override
    public List<ZtreeVo> findAllSysDictResByZtree() {
        // 获取所有的字典类型
        List<ZtreeVo> list=sysDictTypeService.findAllSysDictTypeListByZtree();
        List<ZtreeVo> ztreeVoList=null;
        for (int i = 0; i < list.size(); i++) {
            ztreeVoList= sysDictResMapper.findSysDictResListByZtree(list.get(i).getId());
            list.get(i).setChildren(ztreeVoList);
        }
        return list;
    }

    /**
     *  主键获取
     * @param id
     * @return
     */
    @Override
    public SysDictRes findSysDictResOne(Long id) {
        return sysDictResMapper.selectByPrimaryKey(id);
    }

    /**
     *
     * @param resCode
     * @return
     */
    @Override
    public SysDictRes findSysDictResOneByCode(String resCode) {
        return sysDictResMapper.findSysDictResOneByCode(resCode);
    }


}
