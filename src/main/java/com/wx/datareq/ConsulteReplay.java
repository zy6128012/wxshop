package com.wx.datareq;

import com.wx.comClass.PagesOrder;
import io.swagger.annotations.ApiModel;

import java.io.Serializable;

/**
 * Created by zy612 on 2018/3/2.
 */
@ApiModel(description = "回复咨询")
public class ConsulteReplay implements Serializable {
private Integer conID;
private String RepalyContent;

    public Integer getConID() {
        return conID;
    }

    public void setConID(Integer conID) {
        this.conID = conID;
    }

    public String getRepalyContent() {
        return RepalyContent;
    }

    public void setRepalyContent(String repalyContent) {
        RepalyContent = repalyContent;
    }
}
