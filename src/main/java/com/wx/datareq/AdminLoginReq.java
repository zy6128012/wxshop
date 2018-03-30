package com.wx.datareq;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created by zy612 on 2018/3/2.
 */
@ApiModel(description = "管理员信息登录描述")
public class AdminLoginReq implements Serializable{
    @ApiModelProperty(value = "管理员登录名",name="管理员登录名")
    private String logonName;
    @ApiModelProperty(value = "密码",name = "password")
    private String password;

    public String getLogonName() {
        return logonName;
    }

    public void setLogonName(String logonName) {
        this.logonName = logonName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
