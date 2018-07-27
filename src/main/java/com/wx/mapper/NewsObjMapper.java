package com.wx.mapper;

import com.wx.datareq.NewsReq;
import com.wx.dto.NewsObj;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NewsObjMapper {
    int deleteByPrimaryKey(Integer newid);

    int insert(NewsObj record);

    int insertSelective(NewsObj record);

    NewsObj selectByPrimaryKey(Integer newid);

    List<NewsObj> select(NewsReq req);

    int updateByPrimaryKeySelective(NewsObj record);

    int updateByPrimaryKeyWithBLOBs(NewsObj record);

    int updateByPrimaryKey(NewsObj record);
}