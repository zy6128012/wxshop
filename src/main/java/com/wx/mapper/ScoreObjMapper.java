package com.wx.mapper;

import com.wx.datareq.ScoreReq;
import com.wx.dto.ScoreObj;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ScoreObjMapper {
    int deleteByPrimaryKey(Integer scoreid);

    int insert(ScoreObj record);

    List<ScoreObj> select(ScoreReq scoreReq);

    int insertSelective(ScoreObj record);

    ScoreObj selectByPrimaryKey(Integer scoreid);

    int updateByPrimaryKeySelective(ScoreObj record);

    int updateByPrimaryKey(ScoreObj record);
}