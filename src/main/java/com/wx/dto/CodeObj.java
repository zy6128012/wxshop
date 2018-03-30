package com.wx.dto;

public class CodeObj {
    private Integer codeid;

    public Integer getCodevalue() {
        return codevalue;
    }

    public void setCodevalue(Integer codevalue) {
        this.codevalue = codevalue;
    }

    private Integer codevalue;
    private String codename;

    private Integer codetype;

    private Integer parentid;

    private String extinfo;

    private String memo;

    public Integer getCodeid() {
        return codeid;
    }

    public void setCodeid(Integer codeid) {
        this.codeid = codeid;
    }

    public String getCodename() {
        return codename;
    }

    public void setCodename(String codename) {
        this.codename = codename == null ? null : codename.trim();
    }

    public Integer getCodetype() {
        return codetype;
    }

    public void setCodetype(Integer codetype) {
        this.codetype = codetype;
    }

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    public String getExtinfo() {
        return extinfo;
    }

    public void setExtinfo(String extinfo) {
        this.extinfo = extinfo == null ? null : extinfo.trim();
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }
}