package com.wx.dto;

public class AddressObj {
    private Integer addresid;

    private String conname;

    private String provicename;

    private String cityname;

    private String countryname;

    private String detailaddress;

    private Integer zipcode;

    private String telphone;

    private Integer userid;

    private Integer addresstype;

    private String memo;

    public Integer getAddresid() {
        return addresid;
    }

    public void setAddresid(Integer addresid) {
        this.addresid = addresid;
    }

    public String getConname() {
        return conname;
    }

    public void setConname(String conname) {
        this.conname = conname == null ? null : conname.trim();
    }

    public String getProvicename() {
        return provicename;
    }

    public void setProvicename(String provicename) {
        this.provicename = provicename == null ? null : provicename.trim();
    }

    public String getCityname() {
        return cityname;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname == null ? null : cityname.trim();
    }

    public String getCountryname() {
        return countryname;
    }

    public void setCountryname(String countryname) {
        this.countryname = countryname == null ? null : countryname.trim();
    }

    public String getDetailaddress() {
        return detailaddress;
    }

    public void setDetailaddress(String detailaddress) {
        this.detailaddress = detailaddress == null ? null : detailaddress.trim();
    }

    public Integer getZipcode() {
        return zipcode;
    }

    public void setZipcode(Integer zipcode) {
        this.zipcode = zipcode;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getAddresstype() {
        return addresstype;
    }

    public void setAddresstype(Integer addresstype) {
        this.addresstype = addresstype;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }
}