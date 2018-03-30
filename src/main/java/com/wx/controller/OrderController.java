package com.wx.controller;

/**
 * Created by zy612 on 2018/2/9.
 */

import com.wx.comClass.LoginRequired;
import com.wx.datareq.OrderSet;
import com.wx.dto.AddressObj;
import com.wx.dto.OrderObj;
import com.wx.mapper.AddressObjMapper;
import com.wx.mapper.OrderObjMapper;
import com.wx.result.ProjectResult;
import com.wx.service.AddressService;
import com.wx.service.OrderService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@Api(value = "订单信息",description = "订单信息",position = 3)
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderObjMapper orderObjMapper;
    @Autowired
    private OrderService orderService;

    @ApiOperation(value="下订单", notes="下订单")
    @ApiParam(name = "orderObj", value = "下订单", required = true)
    @LoginRequired
    @PostMapping(value = "addOrder")
    public ProjectResult addOrder(@RequestBody OrderSet orderObj){
        ArrayList addressList=new ArrayList();
        addressList.add(orderObj);
        ProjectResult res=new ProjectResult();
        res.setCount(addressList.size());
        res.setData(addressList);
        res.setnStatus(ProjectResult.nStatusSuccess);
        return res;
    }


    @ApiOperation(value="删除订单", notes="删除订单")
    @PostMapping(value = "delOrder")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderID", value = "订单ID号", paramType="query",required = true,dataType ="Integer")})
    public ProjectResult delAddress(@RequestParam(value = "orderID") Integer orderID){
        ArrayList addressList=new ArrayList();
        OrderObj obj=new OrderObj();
        obj.setOrderid(orderID);
        addressList.add(obj);
        ProjectResult res=new ProjectResult();
        res.setCount(addressList.size());
        res.setData(addressList);
        res.setnStatus(ProjectResult.nStatusSuccess);
        return res;
    }
}
