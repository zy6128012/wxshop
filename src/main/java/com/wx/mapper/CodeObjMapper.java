package com.wx.mapper;

import com.wx.dto.CodeObj;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CodeObjMapper {
    int deleteByPrimaryKey(Integer codeid);

    int insert(CodeObj record);

    int insertSelective(CodeObj record);

    CodeObj selectByPrimaryKey(Integer codeid);

    int updateByPrimaryKeySelective(CodeObj record);

    int updateByPrimaryKey(CodeObj record);
}