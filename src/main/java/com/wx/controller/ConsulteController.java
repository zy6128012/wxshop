package com.wx.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wx.comClass.PagesOrder;
import com.wx.datareq.ConsulteReplay;
import com.wx.datareq.ConsulteReq;
import com.wx.datareq.ScoreReq;
import com.wx.dto.ConsulteObj;
import com.wx.dto.ScoreObj;
import com.wx.dto.UserObj;
import com.wx.mapper.ScoreObjMapper;
import com.wx.result.ProjectResult;
import com.wx.service.ConsulteService;
import com.wx.service.ScoreService;
import com.wx.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consulte")
public class ConsulteController {

    @Autowired
    private ConsulteService consulteService;


    @ApiOperation(value = "获取咨询", notes = "获取咨询")
    @ResponseBody
    @PostMapping(value = "getCon")
    public ProjectResult getCon(@RequestBody ConsulteReq consulteReq) {
        ProjectResult res = new ProjectResult();

        PagesOrder pagesOrder = consulteReq.getPageNum();
        if (pagesOrder == null) {
            pagesOrder = new PagesOrder();
        }

        if (pagesOrder != null) {
            PageHelper.startPage(pagesOrder.getPages(), pagesOrder.getPageNum());
        }

        List<ConsulteObj> consulteObjs= consulteService.getConsulte(consulteReq);
        if (pagesOrder != null) {
            PageInfo<ConsulteObj> pageInfo = new PageInfo<ConsulteObj>(consulteObjs);
            res.setCount(pageInfo.getTotal());
        } else {
            res.setCount(consulteObjs.size());
        }
        res.setnStatus(ProjectResult.nStatusSuccess);
        res.setData(consulteObjs);
        return res;
    }
    @ApiOperation(value = "回复咨询", notes = "回复咨询")
    @ResponseBody
    @PostMapping(value = "replay")
    public ProjectResult replay(@RequestBody ConsulteReplay consulteReplay) {
        ProjectResult res = new ProjectResult();
        if (consulteReplay.getConID() == null || consulteReplay.getConID() == 0) {
            res.setnStatus(ProjectResult.nStatusError);
            res.setSzError("编号不能为空");
            return res;
        }

        if (consulteReplay.getRepalyContent() == null || consulteReplay.getRepalyContent() == "") {
            res.setnStatus(ProjectResult.nStatusError);
            res.setSzError("回复内容不能为空");
            return res;
        }
        Integer nRes = consulteService.replay(consulteReplay);
        if (nRes > 0) {
            res.setnStatus(ProjectResult.nStatusSuccess);

            return res;
        } else {
            res.setnStatus(ProjectResult.nStatusError);
            res.setSzError("回复失败");

            return res;
        }
    }


}
