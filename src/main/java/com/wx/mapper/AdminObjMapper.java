package com.wx.mapper;

import com.wx.datareq.AdminGetReq;
import com.wx.datareq.AdminLoginReq;
import com.wx.datareq.SetPassword;
import com.wx.dto.AdminObj;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminObjMapper {
    int deleteByPrimaryKey(Integer adminid);

    List<AdminObj> select(AdminGetReq adminObj);

    int insert(AdminObj record);

    int insertSelective(AdminObj record);

    AdminObj selectByPrimaryKey(Integer adminid);

    AdminObj adminLogin(AdminLoginReq adminLoginReq);

    int updateByPrimaryKeySelective(AdminObj record);

    int updateByPrimaryKey(AdminObj record);

    int updatePassword(SetPassword setPassword);
}