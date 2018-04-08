package com.wx.datareq;

import com.wx.dto.OrderGoodsObj;

/**
 * Created by zy612 on 2018/3/2.
 */
public class OrderSet {
    private Integer addressID;
    private OrderGoodsObj[] orderGoodsObjs;
    private Integer totalSum;

    public Integer getAddressID() {
        return addressID;
    }

    public void setAddressID(Integer addressID) {
        this.addressID = addressID;
    }

    public OrderGoodsObj[] getOrderGoodsObjs() {
        return orderGoodsObjs;
    }

    public void setOrderGoodsObjs(OrderGoodsObj[] orderGoodsObjs) {
        this.orderGoodsObjs = orderGoodsObjs;
    }

    public Integer getTotalSum() {
        return totalSum;
    }

    public void setTotalSum(Integer totalSum) {
        this.totalSum = totalSum;
    }


}
