package com.wx.comClass;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by zy612 on 2018/3/2.
 */
@ApiModel(value="分页请求")
public class PagesOrder {
    @ApiModelProperty("分页请求")
    private int pages;//请求第几页
    private int pageNum;//一页行数
    private String orderKey;//排序字段
    private String orderModel;//排序模式

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public String getOrderKey() {
        return orderKey;
    }

    public void setOrderKey(String orderKey) {
        this.orderKey = orderKey;
    }

    public String getOrderModel() {
        return orderModel;
    }

    public void setOrderModel(String orderModel) {
        this.orderModel = orderModel;
    }
}
