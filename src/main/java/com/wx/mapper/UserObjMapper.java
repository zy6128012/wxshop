package com.wx.mapper;

import com.github.pagehelper.Page;
import com.wx.dto.UserObj;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserObjMapper {
    int deleteByPrimaryKey(Integer userid);

    int insert(UserObj record);

    int insertSelective(UserObj record);

    List<UserObj> select(UserObj userObj);

    Page<UserObj> selectPage(Integer pageCurrent, Integer pageSize, UserObj userObj);

    UserObj selectByPrimaryKey(Integer userid);

    int updateByPrimaryKeySelective(UserObj record);

    int updateByPrimaryKey(UserObj record);
}