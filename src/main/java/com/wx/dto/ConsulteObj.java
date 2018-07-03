package com.wx.dto;

import net.sf.jsqlparser.expression.DateTimeLiteralExpression;

import java.util.Date;

public class ConsulteObj {
    private Integer conID;
    private String conTent;
    private Integer conType;
    private Date conTime;
    private Integer conDate;
    private String refContent;
    private  Integer conStatue;

    public Date getConTime() {
        return conTime;
    }

    public void setConTime(Date conTime) {
        this.conTime = conTime;
    }

    public Integer getConDate() {
        return conDate;
    }

    public void setConDate(Integer conDate) {
        this.conDate = conDate;
    }

    public Integer getConID() {
        return conID;
    }

    public void setConID(Integer conID) {
        this.conID = conID;
    }

    public String getConTent() {
        return conTent;
    }

    public void setConTent(String conTent) {
        this.conTent = conTent;
    }

    public Integer getConType() {
        return conType;
    }

    public void setConType(Integer conType) {
        this.conType = conType;
    }

    public String getRefContent() {
        return refContent;
    }

    public void setRefContent(String refContent) {
        this.refContent = refContent;
    }

    public Integer getConStatue() {
        return conStatue;
    }

    public void setConStatue(Integer conStatue) {
        this.conStatue = conStatue;
    }
}