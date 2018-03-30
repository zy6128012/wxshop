package com.wx.datareq;

import com.wx.comClass.PagesOrder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by zy612 on 2018/3/2.
 */
@ApiModel(value="获取商品请求")
public class GoodsReq {
    @ApiModelProperty(value="商品ID号" ,required=true)
    private Integer goodsID;
    private String goodsName;
    private String suppcompany;
    private Integer buyType;
    private Integer minbuyprice;
    private Integer maxbuyprice;
    private Integer beginBuyDate;
    private Integer endBuyDate;
    private Integer paperSize;
    private Integer paperColor;
    private Integer useType;
    private Integer goodsProp;
    private Integer goodsStatue;
    private  PagesOrder  pageNum;

    public PagesOrder getPageNum() {
        return pageNum;
    }

    public void setPageNum(PagesOrder pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getGoodsID() {
        return goodsID;
    }

    public void setGoodsID(Integer goodsID) {
        this.goodsID = goodsID;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Integer getBuyType() {
        return buyType;
    }

    public void setBuyType(Integer buyType) {
        this.buyType = buyType;
    }

    public Integer getMinbuyprice() {
        return minbuyprice;
    }

    public void setMinbuyprice(Integer minbuyprice) {
        this.minbuyprice = minbuyprice;
    }

    public Integer getMaxbuyprice() {
        return maxbuyprice;
    }

    public void setMaxbuyprice(Integer maxbuyprice) {
        this.maxbuyprice = maxbuyprice;
    }

    public Integer getBeginBuyDate() {
        return beginBuyDate;
    }

    public void setBeginBuyDate(Integer beginBuyDate) {
        this.beginBuyDate = beginBuyDate;
    }

    public Integer getEndBuyDate() {
        return endBuyDate;
    }

    public void setEndBuyDate(Integer endBuyDate) {
        this.endBuyDate = endBuyDate;
    }

    public Integer getPaperSize() {
        return paperSize;
    }

    public void setPaperSize(Integer paperSize) {
        this.paperSize = paperSize;
    }

    public Integer getPaperColor() {
        return paperColor;
    }

    public void setPaperColor(Integer paperColor) {
        this.paperColor = paperColor;
    }

    public Integer getUseType() {
        return useType;
    }

    public void setUseType(Integer useType) {
        this.useType = useType;
    }

    public Integer getGoodsProp() {
        return goodsProp;
    }

    public void setGoodsProp(Integer goodsProp) {
        this.goodsProp = goodsProp;
    }

    public Integer getGoodsStatue() {
        return goodsStatue;
    }

    public void setGoodsStatue(Integer goodsStatue) {
        this.goodsStatue = goodsStatue;
    }


    public String getSuppcompany() {
        return suppcompany;
    }

    public void setSuppcompany(String suppcompany) {
        this.suppcompany = suppcompany;
    }
}
