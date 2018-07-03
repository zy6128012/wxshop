package com.wx.service;

import com.wx.datareq.ConsulteReplay;
import com.wx.datareq.ConsulteReq;
import com.wx.datareq.ScoreReq;
import com.wx.dto.ConsulteObj;
import com.wx.dto.ScoreObj;
import com.wx.mapper.ConsulteObjMapper;
import com.wx.mapper.ScoreObjMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zy612 on 2018/2/9.
 */
@Service
public class ConsulteService {

    @Autowired
    private ConsulteObjMapper consulteObjMapper;


    public List<ConsulteObj> getConsulte(ConsulteReq consulteReq) {
        List<ConsulteObj> consulteObjs = consulteObjMapper.select(consulteReq);
        return consulteObjs;
    }
    public Integer replay(ConsulteReplay consulteReplay) {
        return   consulteObjMapper.replay(consulteReplay);

    }
}
