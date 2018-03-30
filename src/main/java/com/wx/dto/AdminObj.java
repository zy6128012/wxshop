package com.wx.dto;

public class AdminObj {
    private Integer adminid;

    private String logonname;

    private String truename;

    private String password;

    private Integer manrole;

    private Integer createdate;

    private Integer kind;

    private String memo;

    public Integer getAdminid() {
        return adminid;
    }

    public void setAdminid(Integer adminid) {
        this.adminid = adminid;
    }

    public String getLogonname() {
        return logonname;
    }

    public void setLogonname(String logonname) {
        this.logonname = logonname == null ? null : logonname.trim();
    }

    public String getTruename() {
        return truename;
    }

    public void setTruename(String truename) {
        this.truename = truename == null ? null : truename.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getManrole() {
        return manrole;
    }

    public void setManrole(Integer manrole) {
        this.manrole = manrole;
    }

    public Integer getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Integer createdate) {
        this.createdate = createdate;
    }

    public Integer getKind() {
        return kind;
    }

    public void setKind(Integer kind) {
        this.kind = kind;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }
}