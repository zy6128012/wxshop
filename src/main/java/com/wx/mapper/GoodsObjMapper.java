package com.wx.mapper;

import com.wx.datareq.GoodsReq;
import com.wx.dto.AdminObj;
import com.wx.dto.GoodsObj;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GoodsObjMapper {
    int deleteByPrimaryKey(Integer goodsid);

    int insert(GoodsObj record);

    int insertSelective(GoodsObj record);

    List<GoodsObj>  select(GoodsReq goodsReq);

    int updateByPrimaryKeySelective(GoodsObj record);

    int updateDetail(@Param("goodIntro")String goodIntro,@Param("goodid")Integer goodid);

    int updatePhoto(@Param("goodImaurl")String goodImaurl,@Param("goodid")Integer goodid);

    int updateByPrimaryKeyWithBLOBs(GoodsObj record);

    int updateByPrimaryKey(GoodsObj record);
}