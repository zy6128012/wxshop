package com.wx.comClass;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by zy612 on 2018/3/2.
 */
@ApiModel(value="返回结果说明")
public class ResClass {
    @ApiModelProperty("返回状态")
    public int nStatus;//1表示成功，4表示失败
    public static final int nStatusSuccess = 1;
    public static final int nStatusError = 4;
    public static final int nStatusTimeout= 8;

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

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Object data() {
        return data;
    }

    public void setRows(Object data) {
        this.data = data;
    }
    @ApiModelProperty("结果错误说明")
    private String szError;
    @ApiModelProperty("返回总行数")
    private Integer total;
    @ApiModelProperty("返回数据")
    private Object data;

    public ResClass() {
        this.nStatus = ResClass.nStatusError;
        //this.szError =null;
        this.total = 0;
        this.data = new Object();
    }
}
