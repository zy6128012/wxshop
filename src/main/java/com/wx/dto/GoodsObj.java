package com.wx.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="商品结构")
public class GoodsObj {

    @ApiModelProperty(value="商品ID号" ,required=true)
    private Integer goodsid;

    private String goodsname;

    private Integer buytype;

    private String suppcompany;
    private String model;
    private Integer buyprice;

    private Integer papersize;

    private Integer papercolor;

    private Integer usetype;

    private Integer goodsprop;

    private Integer goodsstatue;

    private Integer goodsstock;

    private Integer buydate;

    private String goodsimgurl;

    private String memo;

    private String goodintro;

    public Integer getGoodsid() {
        return goodsid;
    }

    public void setGoodsid(Integer goodsid) {
        this.goodsid = goodsid;
    }

    public String getGoodsname() {
        return goodsname;
    }

    public void setGoodsname(String goodsname) {
        this.goodsname = goodsname == null ? null : goodsname.trim();
    }

    public Integer getBuytype() {
        return buytype;
    }

    public void setBuytype(Integer buytype) {
        this.buytype = buytype;
    }

    public String getSuppcompany() {
        return suppcompany;
    }

    public void setSuppcompany(String suppcompany) {
        this.suppcompany = suppcompany;
    }

    public Integer getBuyprice() {
        return buyprice;
    }

    public void setBuyprice(Integer buyprice) {
        this.buyprice = buyprice;
    }

    public Integer getPapersize() {
        return papersize;
    }

    public void setPapersize(Integer papersize) {
        this.papersize = papersize;
    }

    public Integer getPapercolor() {
        return papercolor;
    }

    public void setPapercolor(Integer papercolor) {
        this.papercolor = papercolor;
    }

    public Integer getUsetype() {
        return usetype;
    }

    public void setUsetype(Integer usetype) {
        this.usetype = usetype;
    }

    public Integer getGoodsprop() {
        return goodsprop;
    }

    public void setGoodsprop(Integer goodsprop) {
        this.goodsprop = goodsprop;
    }

    public Integer getGoodsstatue() {
        return goodsstatue;
    }

    public void setGoodsstatue(Integer goodsstatue) {
        this.goodsstatue = goodsstatue;
    }

    public Integer getGoodsstock() {
        return goodsstock;
    }

    public void setGoodsstock(Integer goodsstock) {
        this.goodsstock = goodsstock;
    }

    public Integer getBuydate() {
        return buydate;
    }

    public void setBuydate(Integer buydate) {
        this.buydate = buydate;
    }

    public String getGoodsimgurl() {
        return goodsimgurl;
    }

    public void setGoodsimgurl(String goodsimgurl) {
        this.goodsimgurl = goodsimgurl == null ? null : goodsimgurl.trim();
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    public String getGoodintro() {
        return goodintro;
    }

    public void setGoodintro(String goodintro) {
        this.goodintro = goodintro == null ? null : goodintro.trim();
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}