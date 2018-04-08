package com.wx.mapper;

import com.wx.dto.OrderDetailObj;
import com.wx.dto.OrderGoodsObj;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderGoodsObjMapper {
    int deleteByPrimaryKey(Integer ordergoodsid);

    int insert(OrderGoodsObj record);

    int insertSelective(OrderGoodsObj record);

    List<OrderGoodsObj> selectByPrimaryKey(Integer ordergoodsid);

    int updateByPrimaryKeySelective(OrderGoodsObj record);

    int updateByPrimaryKey(OrderGoodsObj record);
}