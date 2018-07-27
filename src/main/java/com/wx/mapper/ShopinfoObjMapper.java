package com.wx.mapper;

import com.wx.dto.ShopinfoObj;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ShopinfoObjMapper {
    int deleteByPrimaryKey(Integer sid);

    int insert(ShopinfoObj record);

    int insertSelective(ShopinfoObj record);

    ShopinfoObj selectByPrimaryKey(Integer sid);

    List<ShopinfoObj> selectByType(Integer sid);

    List<ShopinfoObj> select();

    int updateByPrimaryKeySelective(ShopinfoObj record);

    int updateByPrimaryKeyWithBLOBs(ShopinfoObj record);

    int updateByPrimaryKey(ShopinfoObj record);
}