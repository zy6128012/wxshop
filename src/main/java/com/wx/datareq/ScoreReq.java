package com.wx.datareq;

import com.wx.comClass.PagesOrder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created by zy612 on 2018/3/2.
 */
@ApiModel(description = "获取积分信息")
public class ScoreReq implements Serializable {
    private Integer userID;
    private String telphone;
    private Integer scoreDate;
    private Integer scoreType;
    private  PagesOrder pageNum;
    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }



    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Integer getScoreDate() {
        return scoreDate;
    }

    public void setScoreDate(Integer scoreDate) {
        this.scoreDate = scoreDate;
    }

    public Integer getScoreType() {
        return scoreType;
    }

    public void setScoreType(Integer scoreType) {
        this.scoreType = scoreType;
    }

    public PagesOrder getPageNum() {
        return pageNum;
    }

    public void setPageNum(PagesOrder pageNum) {
        this.pageNum = pageNum;
    }
}
