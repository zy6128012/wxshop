package com.wx.datareq;

import com.wx.comClass.PagesOrder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created by zy612 on 2018/3/2.
 */
@ApiModel(description = "获取管理员信息")
public class AdminGetReq implements Serializable {
    private Integer adminID;

    @ApiModelProperty(value = "管理员登录名", name = "管理员登录名")
    private String logonName;

    private  String trueName;
    @ApiModelProperty(value = "密码", name = "password")
    private  PagesOrder pageNum;

    public String getLogonName() {
        return logonName;
    }

    public void setLogonName(String logonName) {
        this.logonName = logonName;
    }

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

    public PagesOrder getPageNum() {
        return pageNum;
    }

    public void setPageNum(PagesOrder pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getAdminID() {
        return adminID;
    }

    public void setAdminID(Integer adminID) {
        this.adminID = adminID;
    }
}
