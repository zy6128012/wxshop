package com.wx.dto;

import java.util.Date;

public class NewsObj {
    private Integer newid;

    private String newtitle;

    private Date newtime;

    private Integer newsdate;

    private String memo;

    private String contant;

    public Integer getNewid() {
        return newid;
    }

    public void setNewid(Integer newid) {
        this.newid = newid;
    }

    public String getNewtitle() {
        return newtitle;
    }

    public void setNewtitle(String newtitle) {
        this.newtitle = newtitle == null ? null : newtitle.trim();
    }

    public Date getNewtime() {
        return newtime;
    }

    public void setNewtime(Date newtime) {
        this.newtime = newtime;
    }

    public Integer getNewsdate() {
        return newsdate;
    }

    public void setNewsdate(Integer newsdate) {
        this.newsdate = newsdate;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    public String getContant() {
        return contant;
    }

    public void setContant(String contant) {
        this.contant = contant == null ? null : contant.trim();
    }
}