package com.wx.result;

/**
 * Created by Administrator on 2017/8/18.
 */
public abstract class ObjectResult<T>  extends BaseResult {
    private T data;
    public ObjectResult(){super();};
    public ObjectResult(Integer nStatus, String szError) {
        super(nStatus, szError);
    }

    public ObjectResult(Integer nStatus,String szError,T data){
        super(nStatus, szError);
        this.data=data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
