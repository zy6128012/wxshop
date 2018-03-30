package com.wx.service;

import com.wx.datareq.AreaReq;
import com.wx.dto.AreaObj;
import com.wx.mapper.AreaObjMapper;
import com.wx.mapper.UserObjMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.Null;
import java.util.List;
import java.util.Map;

/**
 * Created by zy612 on 2018/2/9.
 */
@Service
public class AreaService {

    @Autowired
    private AreaObjMapper areaObjMapper;

    public  List<AreaObj> getProvice() {
        AreaReq areaReq=new AreaReq();
        areaReq.setParentCode(0);
        List<AreaObj> areaObjs = areaObjMapper.selecArea(areaReq);
     return areaObjs;
    }

    public  List<AreaObj> getCity(Integer nParentCode) {
        AreaReq areaReq=new AreaReq();
        areaReq.setParentCode(nParentCode);
        List<AreaObj> areaObjs = areaObjMapper.selecArea(areaReq);
        return areaObjs;
    }
}
