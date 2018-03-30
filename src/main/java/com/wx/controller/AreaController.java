package com.wx.controller;

/**
 * Created by zy612 on 2018/2/9.
 */


import com.wx.datareq.LoginReq;
import com.wx.dto.AreaObj;
import com.wx.mapper.AreaObjMapper;

import com.wx.result.ProjectResult;
import com.wx.service.AreaService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;



@RestController
@Api(value = "省市县选择",description = "省市县地址栏中的三级选择",position = 99)
@RequestMapping("/area")
public class AreaController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private AreaObjMapper areaObjMapper;
    @Autowired
    private AreaService areaServie;

    @ApiOperation(value="获取省", notes="获取省")
    @PostMapping(value = "getProvice")
    public ProjectResult getProvice() {
        logger.info("开始获取省区");
        List<AreaObj> areaObjs = areaServie.getProvice();
        ProjectResult res = new ProjectResult();
        res.setCount(areaObjs.size());
        res.setData(areaObjs);
        res.setnStatus(ProjectResult.nStatusSuccess);
        return res;
    }


    @ApiOperation(value="获取市", notes="获取市,proviceID,则只获取该省下的所有市，不传表示全部")
    @PostMapping(value = "getCity")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "proviceID", value = "省的areacode号", paramType="query",required = true,dataType ="Integer")})
    public ProjectResult getCity(@RequestParam(value = "proviceID") Integer proviceID){
        logger.info("开始获取省区");
        List<AreaObj> areaObjs = areaServie.getCity(proviceID);
        ProjectResult res = new ProjectResult();
        res.setCount(areaObjs.size());
        res.setData(areaObjs);
        res.setnStatus(ProjectResult.nStatusSuccess);
        return res;
    }


    @ApiOperation(value="获取县", notes="获取县,cityID,则只获取该市下的所有县，不传表示全部")
    @PostMapping(value = "getCountry")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cityID", value = "市的areacode号", required = true,paramType="query",dataType ="Integer")})
    public ProjectResult getCountry(@RequestParam(value = "cityID") Integer cityID){
        logger.info("开始获取县区");
        List<AreaObj> areaObjs = areaServie.getCity(cityID);
        ProjectResult res = new ProjectResult();
        res.setCount(areaObjs.size());
        res.setData(areaObjs);
        res.setnStatus(ProjectResult.nStatusSuccess);
        return res;
    }

}
