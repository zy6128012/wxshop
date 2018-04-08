package com.wx.mapper;

import com.wx.datareq.OrderReq;
import com.wx.dto.OrderObj;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderObjMapper {
    int deleteByPrimaryKey(Integer orderid);

    int insert(OrderObj record);

    int insertSelective(OrderObj record);

    OrderObj selectByPrimaryKey(Integer orderid);

    int updateByPrimaryKeySelective(OrderObj record);

    int updateByPrimaryKey(OrderObj record);

    List<OrderObj> select(OrderReq orderReq);
}