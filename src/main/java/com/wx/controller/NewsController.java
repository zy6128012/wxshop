package com.wx.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wx.comClass.LoginRequired;
import com.wx.comClass.PagesOrder;
import com.wx.datareq.NewsReq;
import com.wx.datareq.UserReq;
import com.wx.dto.NewsObj;
import com.wx.dto.UserObj;
import com.wx.mapper.NewsObjMapper;
import com.wx.mapper.UserObjMapper;
import com.wx.result.ProjectResult;
import com.wx.service.NewsService;
import com.wx.service.UserService;
import com.wx.service.WeChatAuthServiceImpl;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/news")
public class NewsController {
    @Autowired
    private NewsObjMapper newsObjMapper;
    @Autowired
    private NewsService newsService;


    @ApiOperation(value = "获取新闻", notes = "获取新闻")
    @ResponseBody
    @PostMapping(value = "getNews")
    public ProjectResult getNews(HttpServletRequest request) {
        ProjectResult res = new ProjectResult();
        NewsReq newsReq=new NewsReq();
        res.setnStatus(ProjectResult.nStatusSuccess);
        List<NewsObj> newsObjs=newsService.select(newsReq);
        if(newsObjs.size()>0)
        {
            res.setData(newsObjs);
        }
        return res;
    }

    @ApiOperation(value = "获取新闻ID", notes = "获取新闻ID")
    @ResponseBody
    @GetMapping(value = "getNewsByID")
    public ProjectResult getNewsByID(HttpServletRequest request,Integer id) {
        ProjectResult res = new ProjectResult();
        NewsReq newsReq=new NewsReq();
        res.setnStatus(ProjectResult.nStatusSuccess);
        NewsObj newsObj=newsService.selectByPrimaryKey(id);
        if(newsObj!=null)
        {
            res.setData(newsObj);
        }
        return res;
    }
    @ApiOperation(value = "删除新闻", notes = "删除新闻")
    @ResponseBody
    @GetMapping(value = "delNews")
    public ProjectResult delNews(Integer id) {
        ProjectResult res = new ProjectResult();
        NewsReq newsReq=new NewsReq();
        res.setnStatus(ProjectResult.nStatusSuccess);
        Integer nID=newsService.del(id);

        return res;
    }


}
