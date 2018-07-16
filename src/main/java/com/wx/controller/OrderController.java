package com.wx.controller;

/**
 * Created by zy612 on 2018/2/9.
 */

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wx.comClass.LoginRequired;
import com.wx.comClass.PagesOrder;
import com.wx.datareq.OrderReq;
import com.wx.datareq.OrderSet;
import com.wx.dto.*;
import com.wx.mapper.AddressObjMapper;
import com.wx.mapper.OrderGoodsObjMapper;
import com.wx.mapper.OrderObjMapper;
import com.wx.mapper.UserObjMapper;
import com.wx.result.ProjectResult;
import com.wx.service.AddressService;
import com.wx.service.OrderDetailService;
import com.wx.service.OrderService;
import com.wx.service.UserService;
import io.swagger.annotations.*;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@Api(value = "订单信息",description = "订单信息",position = 3)
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderObjMapper orderObjMapper;
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private OrderDetailService orderDetailService;

    @ApiOperation(value="下订单", notes="下订单")
    @ApiParam(name = "orderObj", value = "下订单", required = true)
    @LoginRequired
    @PostMapping(value = "addOrder")
    public ProjectResult addOrder(@RequestBody OrderSet orderSetObj, HttpServletRequest request) throws Exception {
        ProjectResult res = new ProjectResult();

        OrderObj orderObj = new OrderObj();
        ArrayList<OrderGoodsObj> orderGoodsObjs = new ArrayList<OrderGoodsObj>();
        orderObj.setAddressid(orderSetObj.getAddressID());
        HttpSession seesionTemp = request.getSession();
        UserObj userObj = (UserObj) seesionTemp.getAttribute("loginUser");
        if (userObj == null) {
            res.setnStatus(ProjectResult.nStatusError);
            res.setSzError("个人信息丢失，请重新登录");
            return res;
        }
        orderObj.setUserid(userObj.getUserid());
        Date dayNow = new Date();

        orderObj.setOrdertime(dayNow);
        orderObj.setPaytype(1);//微信支付
        orderObj.setOrderstatus(1);//下单成功

        Integer nTotalPrice = 0;
        OrderGoodsObj[] orderGoodsObs = orderSetObj.getOrderGoodsObjs();
        if (orderGoodsObs != null && orderGoodsObs.length > 0) {
            for (Integer i = 0; i < orderGoodsObs.length; i++) {
                nTotalPrice = nTotalPrice + orderGoodsObs[i].getGoodsprice() * orderGoodsObs[i].getGoodsnum();
                boolean bRes = orderGoodsObjs.add(orderGoodsObs[i]);
            }
        }
        if (!nTotalPrice.equals(orderSetObj.getTotalSum())) {
            res.setnStatus(ProjectResult.nStatusError);
            res.setSzError("总金额累加不正确");
            return res;
        }
        orderObj.setTotalsum(nTotalPrice);
        orderObj.setMemo(userObj.getExtname()+orderGoodsObjs.get(0).getGoodsid());
        Integer orderID = orderService.addOrder(orderObj, orderGoodsObjs);
        if (orderID == 0 || orderID < 0) {
            res.setnStatus(ProjectResult.nStatusError);
        } else {
            List<OrderGoodsObj> orderGoodsObjList1 = orderDetailService.select(orderID);
            if (orderGoodsObjList1.size() == 1) {
                OrderGoodsObj temp = new OrderGoodsObj();
                temp = orderGoodsObjList1.get(0);
                res.setData(temp);
            }
        }
        return res;
    }



    @ApiOperation(value="删除订单", notes="删除订单")
    @PostMapping(value = "delOrder")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderID", value = "订单ID号", paramType="query",required = true,dataType ="Integer")})
    public ProjectResult delOrder(@RequestParam(value = "orderID") Integer orderID) throws Exception{
        ProjectResult res=new ProjectResult();
     Integer nRes=orderService.delOrder(orderID);
        return res;
    }
    @ApiOperation(value="获取订单", notes="获取订单")
    @PostMapping(value = "getOrder")
    @ApiParam(name = "orderObj", value = "下订单", required = true)
    public ProjectResult getOrder(@RequestBody OrderReq orderReq) throws Exception{
        ProjectResult res = new ProjectResult();
        PagesOrder pagesOrder = orderReq.getPageNum();
        if (pagesOrder == null) {
            pagesOrder = new PagesOrder();
        }
        if (pagesOrder != null) {
            PageHelper.startPage(pagesOrder.getPages(), pagesOrder.getPageNum());
        }
        List<OrderObj> orderObjs= orderObjMapper.select(orderReq);
        ArrayList<OrderDetailObj> orderDetailObjs=new ArrayList<OrderDetailObj>();

        if (pagesOrder != null) {
            PageHelper.startPage(pagesOrder.getPages(), pagesOrder.getPageNum());
        }
        if (pagesOrder != null) {
            PageInfo<OrderObj> pageInfo = new PageInfo<OrderObj>(orderObjs);
            res.setCount(pageInfo.getTotal());
        } else {
            res.setCount(orderObjs.size());
        }
        for(Integer i=0;i<orderObjs.size();i++) {
            OrderObj temp = new OrderObj();
            temp = orderObjs.get(i);
            //普通信息
            OrderDetailObj orderDetailObj = new OrderDetailObj();
            orderDetailObj.setTotalsum(temp.getTotalsum());
            orderDetailObj.setOrderid(temp.getOrderid());
            orderDetailObj.setOrdertime(temp.getOrdertime());
            orderDetailObj.setPaytype(temp.getPaytype());
            orderDetailObj.setPaytype(temp.getPaytype());
            orderDetailObj.setOrderstatus(temp.getOrderstatus());
            orderDetailObj.setMemo(temp.getMemo());
            //用户信息
            Integer nUserID = temp.getUserid();
            UserObj userTemp = new UserObj();
            userTemp.setUserid(nUserID);
            List<UserObj> userObjTemp = userService.getUsers(userTemp);
            orderDetailObj.setUserObj(userObjTemp.get(0));
            //地址信息
            orderDetailObj.setAddressObj(addressService.selectByID(temp.getAddressid()));
            //订单商品详细信息
            orderDetailObj.setOrderGoodsObjList(orderDetailService.select(temp.getOrderid()));
            orderDetailObjs.add(orderDetailObj);
        }
        res.setData(orderDetailObjs);
        res.setnStatus(ProjectResult.nStatusSuccess);
        return res;
    }
    @ApiOperation(value="设置发货状态", notes="设置发货状态")
    @PostMapping(value = "setSend")
    public ProjectResult setSend(@RequestParam("orderID") Integer nOrderID) throws Exception {
        Integer nSend = 4;
        ProjectResult res = new ProjectResult();
        if(orderService.setStatus(nSend, nOrderID)==true)
        {
            res.setnStatus(ProjectResult.nStatusSuccess);
        }
        return res;
    }
    @ApiOperation(value="设置未发货状态", notes="设置未发货状态")
    @PostMapping(value = "setNoSend")
    public ProjectResult setNoSend(@RequestParam("orderID") Integer nOrderID) throws Exception{
        Integer nSend = 2;
        ProjectResult res = new ProjectResult();
        if(orderService.setStatus(nSend, nOrderID)==true)
        {
            res.setnStatus(ProjectResult.nStatusSuccess);
        }
        return res;
    }

}