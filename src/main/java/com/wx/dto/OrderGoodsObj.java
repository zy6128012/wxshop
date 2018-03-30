package com.wx.dto;

public class OrderGoodsObj {
    private Integer ordergoodsid;

    private Integer goodsid;

    private Integer goodsnum;

    private Integer goodsprice;

    private Integer totalsum;

    private String memo;

    public Integer getOrdergoodsid() {
        return ordergoodsid;
    }

    public void setOrdergoodsid(Integer ordergoodsid) {
        this.ordergoodsid = ordergoodsid;
    }

    public Integer getGoodsid() {
        return goodsid;
    }

    public void setGoodsid(Integer goodsid) {
        this.goodsid = goodsid;
    }

    public Integer getGoodsnum() {
        return goodsnum;
    }

    public void setGoodsnum(Integer goodsnum) {
        this.goodsnum = goodsnum;
    }

    public Integer getGoodsprice() {
        return goodsprice;
    }

    public void setGoodsprice(Integer goodsprice) {
        this.goodsprice = goodsprice;
    }

    public Integer getTotalsum() {
        return totalsum;
    }

    public void setTotalsum(Integer totalsum) {
        this.totalsum = totalsum;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }
}