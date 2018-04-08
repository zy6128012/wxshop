package com.wx.service;

import com.wx.datareq.GoodsReq;
import com.wx.datareq.OrderReq;
import com.wx.dto.GoodsObj;
import com.wx.dto.OrderDetailObj;
import com.wx.dto.OrderGoodsObj;
import com.wx.dto.OrderObj;
import com.wx.mapper.OrderGoodsObjMapper;
import com.wx.mapper.OrderObjMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zy612 on 2018/2/9.
 */
@Service
public class OrderDetailService {

    @Autowired
    private OrderObjMapper orderObjMapper;

    @Autowired
    private OrderGoodsObjMapper orderGoodsObjMapper;

    @Autowired
    private GoodsService goodsService;


    @Autowired
    private TransactionTemplate transactionTemplate;

    // @Transactional(readOnly = true, rollbackFor = Exception.class)
    public List<OrderGoodsObj> select(Integer orderID) {
        List<OrderGoodsObj> res = orderGoodsObjMapper.selectByPrimaryKey(orderID);
        for (Integer i = 0; i < res.size(); i++) {
            GoodsReq goodsReq=new GoodsReq();
            goodsReq.setGoodsID(res.get(i).getGoodsid());
            List<GoodsObj> goodsObjs=goodsService.select(goodsReq);
            res.get(i).setGoodsObj(goodsObjs.get(0));
        }
        return  res;
    }
}

