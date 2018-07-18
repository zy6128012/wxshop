package com.wx.service;

import com.wx.datareq.NewsReq;
import com.wx.datareq.ScoreReq;
import com.wx.dto.NewsObj;
import com.wx.dto.ScoreObj;
import com.wx.mapper.NewsObjMapper;
import com.wx.mapper.ScoreObjMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zy612 on 2018/2/9.
 */
@Service
public class NewsService {

    @Autowired
    private NewsObjMapper newsObjMapper;


    public NewsObj selectByPrimaryKey(Integer id) {
        NewsObj newsObj = newsObjMapper.selectByPrimaryKey(id);
        return newsObj;
    }

    public List<NewsObj> select(NewsReq req) {
      List<NewsObj>  newsObjs = newsObjMapper.select(req);
        return newsObjs;
    }

    public Integer add(NewsObj newsObj) {
        Integer newsid = newsObjMapper.insert(newsObj);
        return newsid;
    }
    public Integer del(Integer id) {
        Integer newsid = newsObjMapper.deleteByPrimaryKey(id);
        return newsid;
    }
}
