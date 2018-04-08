package com.wx.datareq;

import com.wx.comClass.PagesOrder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by zy612 on 2018/3/2.
 */
@ApiModel(value="获取订单请求")
public class OrderReq {
    private Integer orderID;
    private String userID;
    private String orderStatus;
    private Integer buyType;
    private Integer beginDate;
    private Integer endDate;
    private  PagesOrder  pageNum;

    public Integer getOrderID() {
        return orderID;
    }

    public void setOrderID(Integer orderID) {
        this.orderID = orderID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getStatus() {
        return orderStatus;
    }

    public void setStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getBuyType() {
        return buyType;
    }

    public void setBuyType(Integer buyType) {
        this.buyType = buyType;
    }

    public Integer getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Integer beginDate) {
        this.beginDate = beginDate;
    }

    public Integer getEndDate() {
        return endDate;
    }

    public void setEndDate(Integer endDate) {
        this.endDate = endDate;
    }

    public PagesOrder getPageNum() {
        return pageNum;
    }

    public void setPageNum(PagesOrder pageNum) {
        this.pageNum = pageNum;
    }
}
