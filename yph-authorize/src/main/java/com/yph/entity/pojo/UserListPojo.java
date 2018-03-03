package com.yph.entity.pojo;

import java.util.Date;

/**
 * 用户list的pojo类
 * @author mqc
 * @create 2018-01-29 15:49
 **/
public class UserListPojo {
    private java.util.Date endTime;
    private Integer gradeId;

    // @Id
    private Integer id;
    private Integer infoId;
    private String openId;
    private Integer status;
    private Integer step;
    private Integer superId;
    private Integer type;
    private Integer userId;

    private Date createTime;

    private String errormsg;
    private Integer cpadminId;

    private String nickName;
    private Integer performTotal;
    private Integer storageTotal;

    private Integer referrer;
    private String resource;

    public java.util.Date getEndTime() {
        return endTime;
    }

    public void setEndTime(java.util.Date in) {
        this.endTime = in;
    }

    public Integer getGradeId() {
        return gradeId;
    }

    public void setGradeId(Integer in) {
        this.gradeId = in;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer in) {
        this.id = in;
    }

    public Integer getInfoId() {
        return infoId;
    }

    public void setInfoId(Integer in) {
        this.infoId = in;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String in) {
        this.openId = in;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer in) {
        this.status = in;
    }

    public Integer getStep() {
        return step;
    }

    public void setStep(Integer in) {
        this.step = in;
    }

    public Integer getSuperId() {
        return superId;
    }

    public void setSuperId(Integer in) {
        this.superId = in;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer in) {
        this.type = in;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer in) {
        this.userId = in;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getErrormsg() {
        return errormsg;
    }

    public void setErrormsg(String errormsg) {
        this.errormsg = errormsg;
    }

    public Integer getCpadminId() {
        return cpadminId;
    }

    public void setCpadminId(Integer cpadminId) {
        this.cpadminId = cpadminId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Integer getPerformTotal() {
        return performTotal;
    }

    public void setPerformTotal(Integer performTotal) {
        this.performTotal = performTotal;
    }

    public Integer getStorageTotal() {
        return storageTotal;
    }

    public void setStorageTotal(Integer storageTotal) {
        this.storageTotal = storageTotal;
    }

    public Integer getReferrer() {
        return referrer;
    }

    public void setReferrer(Integer referrer) {
        this.referrer = referrer;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

}
