package com.wx.dto;

public class AreaObj {
    private Integer areaid;

    private Integer areacode;

    private String areaname;

    private Integer parentcode;

    private Integer areastatus;

    private String memo;

    public Integer getAreaid() {
        return areaid;
    }

    public void setAreaid(Integer areaid) {
        this.areaid = areaid;
    }

    public Integer getAreacode() {
        return areacode;
    }

    public void setAreacode(Integer areacode) {
        this.areacode = areacode;
    }

    public String getAreaname() {
        return areaname;
    }

    public void setAreaname(String areaname) {
        this.areaname = areaname == null ? null : areaname.trim();
    }

    public Integer getParentcode() {
        return parentcode;
    }

    public void setParentcode(Integer parentcode) {
        this.parentcode = parentcode;
    }

    public Integer getAreastatus() {
        return areastatus;
    }

    public void setAreastatus(Integer areastatus) {
        this.areastatus = areastatus;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }
}