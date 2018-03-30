package com.wx.result;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/8/18.
 */
public class ProjectResult extends ObjectResult<Object> implements Serializable{
    public static final int nStatusSuccess = 0;
    public static final int nStatusError = 4;
    public static final int nStatusTimeout= 8;

    private Long count;
    public ProjectResult(){super();}
    public ProjectResult(Integer nStatus, String szError) {
        super(nStatus, szError);
    }

    public ProjectResult(Integer nStatus, String szError, Object data) {
        super(nStatus, szError, data);
    }

    public ProjectResult(Integer nStatus, String szError, Object data,Long count){
        super(nStatus, szError, data);
        this.count=count;
    }


    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
    public void setCount(Integer count) {
        this.count = (Long)((long)count);
    }
}
