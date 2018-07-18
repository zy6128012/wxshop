package com.wx.datareq;

import io.swagger.annotations.ApiModel;

import java.io.Serializable;

/**
 * Created by zy612 on 2018/3/2.
 */
@ApiModel(description = "获取新闻接口")
public class NewsReq implements Serializable{
    private Integer newsID;

    public Integer getNewsID() {
        return newsID;
    }

    public void setNewsID(Integer newsID) {
        this.newsID = newsID;
    }
}
