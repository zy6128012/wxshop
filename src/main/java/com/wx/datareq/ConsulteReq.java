package com.wx.datareq;

import com.wx.comClass.PagesOrder;
import io.swagger.annotations.ApiModel;

import java.io.Serializable;

/**
 * Created by zy612 on 2018/3/2.
 */
@ApiModel(description = "获取咨询")
public class ConsulteReq implements Serializable {
    private String Context;
    private Integer conDate;
    private Integer conStatue;
    private  PagesOrder pageNum;

    public Integer getConStatue() {
        return conStatue;
    }

    public void setConStatue(Integer conStatue) {
        this.conStatue = conStatue;
    }

    public String getContext() {
        return Context;
    }

    public void setContext(String context) {
        Context = context;
    }

    public Integer getConDate() {
        return conDate;
    }

    public void setConDate(Integer conDate) {
        this.conDate = conDate;
    }

    public PagesOrder getPageNum() {
        return pageNum;
    }

    public void setPageNum(PagesOrder pageNum) {
        this.pageNum = pageNum;
    }
}
