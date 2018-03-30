package com.wx.mapper;

import com.wx.datareq.AreaReq;
import com.wx.dto.AreaObj;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface AreaObjMapper {
    int deleteByPrimaryKey(Integer areaid);

    int insert(AreaObj record);

    int insertSelective(AreaObj record);

    AreaObj selectByPrimaryKey(Integer areaid);

    List<AreaObj> selecArea(AreaReq areaReq);

    int updateByPrimaryKeySelective(AreaObj record);

    int updateByPrimaryKey(AreaObj record);
}