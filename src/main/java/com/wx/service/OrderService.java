package com.wx.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wx.comClass.PagesOrder;
import com.wx.datareq.OrderReq;
import com.wx.datareq.OrderSet;
import com.wx.dto.GoodsObj;
import com.wx.dto.OrderGoodsObj;
import com.wx.dto.OrderObj;
import com.wx.mapper.AddressObjMapper;
import com.wx.mapper.OrderGoodsObjMapper;
import com.wx.mapper.OrderObjMapper;
import com.wx.result.ProjectResult;
import net.bytebuddy.description.field.FieldDescription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zy612 on 2018/2/9.
 */
@Service
public class OrderService {

    @Autowired
    private OrderObjMapper orderObjMapper;

    @Autowired
    private OrderGoodsObjMapper orderGoodsObjMapper;

    @Autowired
    private TransactionTemplate transactionTemplate;

    // @Transactional(readOnly = true, rollbackFor = Exception.class)
    public Integer addOrder(OrderObj orderObj, ArrayList<OrderGoodsObj> orderGoodsObjs) {
        return transactionTemplate.execute(new TransactionCallback<Integer>() {
            @Override
            public Integer doInTransaction(TransactionStatus transactionStatus) {
                try {
                    Integer nOderRes = orderObjMapper.insertSelective(orderObj);
                    Integer norderID = orderObj.getOrderid();
                    if (nOderRes > 0) {

                        for (Integer i = 0; i < orderGoodsObjs.size(); i++) {
                            OrderGoodsObj temp = new OrderGoodsObj();
                            temp = orderGoodsObjs.get(i);
                            temp.setOrdergoodsid(norderID);

                            Integer nOrderGoodsRes = orderGoodsObjMapper.insertSelective(temp);
                            if (nOrderGoodsRes < 1) {
                                return 0;

                            }
                        }
                    } else {
                        return 0;
                    }
                    return norderID;
                } catch (Exception e) {
                    transactionStatus.setRollbackOnly();
                    //  transactionStatus.s
                    // e.printStackTrace();
                    return 0;
                }
            }
        });

    }

    // @Transactional(readOnly = true, rollbackFor = Exception.class)
    public boolean setStatus(Integer nStatus, Integer nOrderID) {
        OrderObj orderObj = new OrderObj();
        orderObj.setOrderstatus(nStatus);
        orderObj.setOrderid(nOrderID);
        Integer nRes = orderObjMapper.updateByPrimaryKeySelective(orderObj);
        if (nRes > 0) {
            return true;
        }
        return false;
    }

    // @Transactional(readOnly = true, rollbackFor = Exception.class)
    public Integer delOrder(Integer nOrderID) {
        return transactionTemplate.execute(new TransactionCallback<Integer>() {
            @Override
            public Integer doInTransaction(TransactionStatus transactionStatus) {
                try {
                    orderObjMapper.deleteByPrimaryKey(nOrderID);
                    orderGoodsObjMapper.deleteByPrimaryKey(nOrderID);
                } catch (Exception e) {
                    transactionStatus.setRollbackOnly();
                    //  transactionStatus.s
                    // e.printStackTrace();
                    return 0;
                }
                return 0;
            }
        });
    }

    public List<OrderObj> select(OrderReq orderReq) {
        return orderObjMapper.select(orderReq);
    }
}

