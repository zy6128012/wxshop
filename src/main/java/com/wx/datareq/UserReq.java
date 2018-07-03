package com.wx.datareq;

import com.wx.comClass.PagesOrder;
import io.swagger.annotations.ApiModel;

import java.io.Serializable;

/**
 * Created by zy612 on 2018/3/2.
 */
@ApiModel(description = "获取账户信息")
public class UserReq implements Serializable {
    private Integer userID;
    private String telphone;
    private String userName;
    private String wxID;
    public PagesOrder getPageNum() {
        return pageNum;
    }

    public void setPageNum(PagesOrder pageNum) {
        this.pageNum = pageNum;
    }

    private  PagesOrder pageNum;

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getWxID() {
        return wxID;
    }

    public void setWxID(String wxID) {
        this.wxID = wxID;
    }
}
