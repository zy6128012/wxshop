package com.wx.service;

import com.wx.datareq.AdminGetReq;
import com.wx.datareq.AdminLoginReq;
import com.wx.datareq.ScoreReq;
import com.wx.datareq.SetPassword;
import com.wx.dto.AdminObj;
import com.wx.dto.ScoreObj;
import com.wx.mapper.AdminObjMapper;
import com.wx.mapper.ScoreObjMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
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

}
