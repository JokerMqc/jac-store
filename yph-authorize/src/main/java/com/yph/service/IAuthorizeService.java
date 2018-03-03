package com.yph.service;

import com.yph.entity.po.AutoUserPo;
import com.yph.entity.po.UserListPo;
import com.yph.entity.vo.AutoUserVo;

import java.util.List;

/**
 * @author 毛其传
 * @Title: com.yph.service
 * @Package com.yph.service
 * @Description: 授权逻辑处理接口
 * @date 2018/1/2914:27
 */
public interface IAuthorizeService{
    /**
     * @Description: 获取当前公司下代理等级
     * @return List<AutoUserPo>
     * @throws
     * @author 毛其传
     * @date 2018/1/29 17:31
     */
    List<AutoUserPo> getAgentGrade(AutoUserVo autoUserVo);
    /**
     * @Description: 获取对应的用户信息
     * @return List<UserListPo>
     * @throws
     * @author 毛其传
     * @date 2018/1/29 17:32
     */
    List<UserListPo> getUserList(AutoUserVo autoUserVo);
}
