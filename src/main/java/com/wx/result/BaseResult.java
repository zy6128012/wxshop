package com.wx.result;

/**
 * Created by Administrator on 2017/8/18.
 */
public abstract class BaseResult {
    public BaseResult(){}
    private int nStatus;

    public int getnStatus() {
        return nStatus;
    }

    public void setnStatus(int nStatus) {
        this.nStatus = nStatus;
    }

    public String getSzError() {
        return szError;
    }

    public void setSzError(String szError) {
        this.szError = szError;
    }

    private String szError;

    public BaseResult(int nStatus,String szError){
        this.nStatus=nStatus;
        this.szError=szError;
    }
}
