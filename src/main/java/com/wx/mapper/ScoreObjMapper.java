package com.wx.mapper;

import com.wx.datareq.AdminGetReq;
import com.wx.datareq.AdminLoginReq;
import com.wx.datareq.ScoreReq;
import com.wx.datareq.SetPassword;
import com.wx.dto.AdminObj;
import com.wx.dto.ScoreObj;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ScoreObjMapper {
    List<ScoreObj> select(ScoreReq scoreReq);
}