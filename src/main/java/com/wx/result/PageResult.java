package com.wx.result;

import java.io.Serializable;

/**
 * @date 2017/12/29.
 */
public class PageResult extends ObjectResult<Object> implements Serializable {
    private Integer total;

    public PageResult(int nStatus, String szError) {
        super(nStatus, szError);
    }

    public PageResult(int nStatus, String szError, Object data) {
        super(nStatus, szError, data);
    }

    public PageResult(int nStatus, String szError, Object data, Integer total){
        super(nStatus, szError, data);
        this.total=total;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
