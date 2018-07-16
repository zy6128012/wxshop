package com.wx.controller;

/**
 * Created by zy612 on 2018/2/9.
 */

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wx.comClass.PagesOrder;
import com.wx.datareq.GoodsReq;
import com.wx.comClass.LoginRequired;
import com.wx.comClass.ResClass;
import com.wx.dto.AdminObj;
import com.wx.dto.GoodsObj;
import com.wx.mapper.GoodsObjMapper;
import com.wx.result.ProjectResult;
import com.wx.service.GoodsService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@Api(value = "商品模块",description = "商城信息的维护",position = 2)
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    private GoodsObjMapper goodsObjMapper;
    @Autowired
    private GoodsService goodsServie;

    @ApiOperation(value = "获取商品信息接口", notes = "获取商品信息接口描述")
    @ApiParam(name = "goods", value = "商品类", required = true)
    @ResponseBody
    @PostMapping(value = "getGoods")
    public ProjectResult getGoods(@RequestBody GoodsReq goodsReq) {
        ProjectResult res = new ProjectResult();
        PagesOrder pagesOrder = goodsReq.getPageNum();
        if (goodsReq.getPaperSize() ==null||goodsReq.getPaperSize()== 0) {
            goodsReq.setPaperSize(null);
        }
        if (goodsReq.getBuyType()==null||goodsReq.getBuyType() == 0) {
            goodsReq.setBuyType(null);
        }
        if (pagesOrder == null) {
            pagesOrder = new PagesOrder();
        }
        if (pagesOrder != null) {
            PageHelper.startPage(pagesOrder.getPages(), pagesOrder.getPageNum());
        }
        List<GoodsObj> goodsObjs = goodsServie.select(goodsReq);
        if (pagesOrder != null) {
            PageHelper.startPage(pagesOrder.getPages(), pagesOrder.getPageNum());
        }
        if (pagesOrder != null) {
            PageInfo<GoodsObj> pageInfo = new PageInfo<GoodsObj>(goodsObjs);
            res.setCount(pageInfo.getTotal());
        } else {
            res.setCount(goodsObjs.size());
        }
        res.setData(goodsObjs);
        res.setnStatus(ProjectResult.nStatusSuccess);
        return res;
    }

    @ApiOperation(value = "新增商品", notes = "新增商品")
    @ResponseBody
    @PostMapping(value = "addGoods")
    public ProjectResult addGoods(@RequestBody GoodsObj goodsObj) {
        ProjectResult res = new ProjectResult();
        Integer nRes=goodsServie.add(goodsObj);
        if(nRes>0)
        {
            res.setnStatus(ProjectResult.nStatusSuccess);
        }else {
            res.setnStatus(ProjectResult.nStatusError);
            res.setSzError("新增商品失败");
        }
        return res;
    }

    @ApiOperation(value = "更新商品", notes = "更新商品")
    @LoginRequired
    @PostMapping(value = "updateGoods")
    public ProjectResult updateGoods(@RequestBody GoodsObj goodsObj) {
        ProjectResult res = new ProjectResult();
        if (goodsServie.update(goodsObj) > 0) {
            res.setnStatus(ProjectResult.nStatusSuccess);
        } else {
            res.setnStatus(ProjectResult.nStatusError);
            res.setSzError("更新失败");
        }
        return res;
    }
    @ApiOperation(value = "更新商品介绍", notes = "更新商品介绍")
    @LoginRequired
    @PostMapping(value = "updateGoodsDetail")
    public ProjectResult updateGoodsDetail(@RequestParam("goodsID") Integer goodsID,@RequestParam("editor") String editor) {
        ProjectResult res = new ProjectResult();
        /*
        if (goodsServie.update(goodsObj) > 0) {
            res.setnStatus(ProjectResult.nStatusSuccess);
        } else {
            res.setnStatus(ProjectResult.nStatusError);
            res.setSzError("更新失败");
        }*/
        return res;
    }
    @ApiOperation(value = "更新商品", notes = "更新商品")
    @LoginRequired
    @PostMapping(value = "delGoods")
    public ProjectResult delGoods(@RequestParam("goodsID") Integer goodsID) {
        ProjectResult res = new ProjectResult();
        if(goodsServie.deleteByPrimaryKey(goodsID)>0)
        {
            res.setnStatus(ProjectResult.nStatusSuccess);
        }
        else
        {
            res.setnStatus(ProjectResult.nStatusError);
        }
        return res;
    }

    @ApiOperation(value = "更新商品库存", notes = "更新商品库存", response = ResClass.class)
    @LoginRequired
    @ApiImplicitParams({
            @ApiImplicitParam(name = "goodsID", value = "商品ID号", required = true, paramType="query",dataType ="Integer"),
            @ApiImplicitParam(name = "stockNum", value = "该商品剩余数量", required = true,paramType="query", dataType ="Integer")})
    @PostMapping(value = "updateGoodsStock")
    public ResClass updateGoodsStock( @RequestParam(value = "goodsID") Integer goodsID, @RequestParam(value = "stockNum") Integer stockNum) {
     //  System.out.println(goodsID+"   "+stockNum);
         Integer nRes = goodsID + stockNum;
        ArrayList goodsList = new ArrayList();
        ResClass res = new ResClass();
        res.setTotal(goodsList.size());
        res.setSzError(nRes.toString());
        res.setRows(goodsList);
        res.setnStatus(ResClass.nStatusSuccess);
        return res;
    }
}
