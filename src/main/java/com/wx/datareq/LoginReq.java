package com.wx.datareq;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

/**
 * Created by zy612 on 2018/3/2.
 */
@ApiModel(description = "用户信息登录描述")
public class LoginReq  implements Serializable{
    @ApiModelProperty(value = "用户名",name="用户名userid")
    private String userName;
    @ApiModelProperty(value = "密码",name = "password")
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
