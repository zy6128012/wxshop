package com.wx.service;

import com.wx.datareq.ScoreReq;
import com.wx.dto.ScoreObj;
import com.wx.mapper.ScoreObjMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zy612 on 2018/2/9.
 */
@Service
public class ScoreService {

    @Autowired
    private ScoreObjMapper scoreObjMapper;


    public List<ScoreObj> getScore(ScoreReq scoreReq) {
        List<ScoreObj> scoreObjs = scoreObjMapper.select(scoreReq);
        return scoreObjs;
    }
public  Integer insert(ScoreObj scoreObj)
{
    return  scoreObjMapper.insert(scoreObj);
}
}
