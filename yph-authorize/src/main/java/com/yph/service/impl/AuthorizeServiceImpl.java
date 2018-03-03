package com.yph.service.impl;

import com.yph.entity.po.AutoUserPo;
import com.yph.entity.po.UserListPo;
import com.yph.entity.vo.AutoUserVo;
import com.yph.mapper.autouser.AuthorizeMapper;
import com.yph.service.IAuthorizeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 毛其传
 * @create 2018-01-29 14:31
 **/
@Slf4j
@Service
@Transactional
public class AuthorizeServiceImpl implements IAuthorizeService {

    //授权mapper
    @Autowired
    private AuthorizeMapper authorizeMapper;

    /**
     * @Description: 获取当前公司下代理等级
     * @return List<AutoUserPo>
     * @throws
     * @author 毛其传
     * @date 2018/1/29 17:31
     */
    @Override
    public List<AutoUserPo> getAgentGrade(AutoUserVo autoUserVo) {
        return authorizeMapper.getAgentGrade(autoUserVo);
    }

    /**
     * @Description: 获取对应的用户信息
     * @return List<UserListPo>
     * @throws
     * @author 毛其传
     * @date 2018/1/29 17:32
     */
    @Override
    public List<UserListPo> getUserList(AutoUserVo autoUserVo) {
        return authorizeMapper.getUserList(autoUserVo);
    }
}
