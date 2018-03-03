package com.yph.controller.autouser;

import com.yph.common.result.CommonResult;
import com.yph.common.utils.ShiroUtils;
import com.yph.entity.po.AutoUserPo;
import com.yph.entity.po.UserListPo;
import com.yph.entity.vo.AutoUserVo;
import com.yph.service.IAuthorizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * @author 毛其传
 * @create 2018-01-29 14:15
 **/
@RequestMapping("authorize/autouser")
@RestController
public class AuthorizeController {
    @Autowired
    private IAuthorizeService authorizeService;

    /**
    * @Description:获取公司代理级别
    * @return Object
    * @throws
    * @author 毛其传
    * @date 2018/1/29 17:39
    */
    @RequestMapping("getGradeData")
    public CommonResult getGradeData(){
        Long userId = ShiroUtils.getUserId();

        AutoUserVo autoUserVo = new AutoUserVo();
        autoUserVo.setUserId(userId);

        //获取对应的代理等级信息
        List<AutoUserPo> gradeList = authorizeService.getAgentGrade(autoUserVo);

        autoUserVo.setType(0);


        //返回对应数据
        HashMap hashMap = new HashMap();
        hashMap.put("gradeList",gradeList);

        return CommonResult.SUCCESS(hashMap);
    }

    /**
     * 获取公司管理员信息
     * @return
     */
    @RequestMapping("getUserListData")
    public CommonResult getUserListData(){
        Long userId = ShiroUtils.getUserId();

        AutoUserVo autoUserVo = new AutoUserVo();
        autoUserVo.setUserId(userId);
        autoUserVo.setType(0);

        //获取对应公司下的管理员信息
        List<UserListPo> listUset = authorizeService.getUserList(autoUserVo);

        //返回对应数据
        HashMap hashMap = new HashMap();
        hashMap.put("listUser",listUset);

        return CommonResult.SUCCESS(hashMap);
    }





}
