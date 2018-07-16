package com.wx.mapper;

import com.wx.dto.AddressObj;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AddressObjMapper {
    int deleteByPrimaryKey(Integer addresid);

    int insert(AddressObj record);

    int insertSelective(AddressObj record);

    AddressObj selectByPrimaryKey(Integer addresid);

    List<AddressObj> selectByUserID(Integer userid);

    int updateByPrimaryKeySelective(AddressObj record);

    int updateByPrimaryKey(AddressObj record);
}