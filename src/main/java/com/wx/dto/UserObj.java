package com.wx.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

@ApiModel
public class UserObj implements Serializable{
    @ApiModelProperty(value = "用户ID号")
    private Integer userid;
    @ApiModelProperty(value = "用户名")
    private String username;
    @ApiModelProperty(value = "手机")
    private String telphone;
    @ApiModelProperty(value = "小名")
    private String extname;
    @ApiModelProperty(value = "密码")
    private String password;
    @ApiModelProperty(value = "性别")
    private Integer sex;
    @ApiModelProperty(value = "生日",name = "20180101表示2018年")
    private Integer birthday;
    @ApiModelProperty(value = "地址栏")
    private Integer addreskey;
    @ApiModelProperty(value = "微信号")
    private String wxid;
    @ApiModelProperty(value = "阿里账号")
    private String aliid;
    @ApiModelProperty(value = "创建时间")
    private Date createtime;
    @ApiModelProperty(value = "用户类型")
    private Integer userkind;
    @ApiModelProperty(value = "用户属性")
    private Integer userprop;
    @ApiModelProperty(value = "余额")
    private Integer wxbalance;
    @ApiModelProperty(value = "积分")
    private Integer usersocre;
    @ApiModelProperty(value = "备注")
    private String memo;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    public String getExtname() {
        return extname;
    }

    public void setExtname(String extname) {
        this.extname = extname == null ? null : extname.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getBirthday() {
        return birthday;
    }

    public void setBirthday(Integer birthday) {
        this.birthday = birthday;
    }

    public Integer getAddreskey() {
        return addreskey;
    }

    public void setAddreskey(Integer addreskey) {
        this.addreskey = addreskey;
    }

    public String getWxid() {
        return wxid;
    }

    public void setWxid(String wxid) {
        this.wxid = wxid == null ? null : wxid.trim();
    }

    public String getAliid() {
        return aliid;
    }

    public void setAliid(String aliid) {
        this.aliid = aliid == null ? null : aliid.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getUserkind() {
        return userkind;
    }

    public void setUserkind(Integer userkind) {
        this.userkind = userkind;
    }

    public Integer getUserprop() {
        return userprop;
    }

    public void setUserprop(Integer userprop) {
        this.userprop = userprop;
    }

    public Integer getWxbalance() {
        return wxbalance;
    }

    public void setWxbalance(Integer wxbalance) {
        this.wxbalance = wxbalance;
    }

    public Integer getUsersocre() {
        return usersocre;
    }

    public void setUsersocre(Integer usersocre) {
        this.usersocre = usersocre;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }
}