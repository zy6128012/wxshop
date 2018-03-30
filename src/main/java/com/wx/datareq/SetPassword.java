package com.wx.datareq;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created by zy612 on 2018/3/2.
 */
@ApiModel(description = "修改密码")
public class SetPassword implements Serializable{
    @ApiModelProperty(value = "用户账号",name="用户名userid")
    private Integer adminID;
    @ApiModelProperty(value = "密码",name = "password")
    private String password;
    private String passwordConfirm;

    public Integer getAdminID() {
        return adminID;
    }

    public void setAdminID(Integer adminID) {
        this.adminID = adminID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }
}
