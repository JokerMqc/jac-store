package com.yph.entity.vo;

/**
 * @author 毛其传
 * 用于传输授权模块传输数据的vo
 * @create 2018-01-29 17:04
 **/
public class AutoUserVo {
    //对应公司的id
    private Long userId;
    //-1：CEO
    //  0： 公司管理员
    // 1： 意向客户
    //  2： 正式会员
    //  3： 代理
    private Integer type;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

}
