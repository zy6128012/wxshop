package com.wx.dto;

import java.util.Date;
import java.util.List;

public class OrderDetailObj {
    private Integer orderid;

    private UserObj userObj;

    private AddressObj addressObj;

    private Date ordertime;

    private Integer totalsum;

    private Integer paytype;

    private Integer orderstatus;

    private String memo;

    private List<OrderGoodsObj> orderGoodsObjList;


    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    public UserObj getUserObj() {
        return userObj;
    }

    public void setUserObj(UserObj userObj) {
        this.userObj = userObj;
    }

    public Date getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(Date ordertime) {
        this.ordertime = ordertime;
    }

    public Integer getTotalsum() {
        return totalsum;
    }

    public void setTotalsum(Integer totalsum) {
        this.totalsum = totalsum;
    }

    public Integer getPaytype() {
        return paytype;
    }

    public void setPaytype(Integer paytype) {
        this.paytype = paytype;
    }

    public Integer getOrderstatus() {
        return orderstatus;
    }

    public void setOrderstatus(Integer orderstatus) {
        this.orderstatus = orderstatus;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public List<OrderGoodsObj> getOrderGoodsObjList() {
        return orderGoodsObjList;
    }

    public void setOrderGoodsObjList(List<OrderGoodsObj> orderGoodsObjList) {
        this.orderGoodsObjList = orderGoodsObjList;
    }

    public AddressObj getAddressObj() {
        return addressObj;
    }

    public void setAddressObj(AddressObj addressObj) {
        this.addressObj = addressObj;
    }
}