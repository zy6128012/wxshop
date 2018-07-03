package com.wx.mapper;

import com.wx.datareq.ConsulteReplay;
import com.wx.datareq.ConsulteReq;
import com.wx.datareq.ScoreReq;
import com.wx.dto.ConsulteObj;
import com.wx.dto.ScoreObj;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ConsulteObjMapper {
    List<ConsulteObj> select(ConsulteReq consulteReq);

    Integer replay(ConsulteReplay consulteReplay);
}