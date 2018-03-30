package com.wx.service;

import com.github.pagehelper.PageHelper;
import com.wx.comClass.PagesOrder;
import com.wx.datareq.AdminGetReq;
import com.wx.datareq.AdminLoginReq;
import com.wx.datareq.SetPassword;
import com.wx.dto.AdminObj;
import com.wx.mapper.AdminObjMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by zy612 on 2018/2/9.
 */
@Service
public class AdminService {

    @Autowired
    private AdminObjMapper adminObjMapper;

    @Transactional
    public AdminObj adminLogin(AdminLoginReq adminLoginReq) {
        AdminObj adminObj = adminObjMapper.adminLogin(adminLoginReq);
        return adminObj;
    }
    public List<AdminObj> getAdmin(AdminGetReq adminReq) {

        List<AdminObj> adminObjs = adminObjMapper.select(adminReq);
        return adminObjs;
    }
    public Integer addAdmin(AdminObj adminObj) {

        Date day=new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        adminObj.setCreatedate(Integer.parseInt(df.format(day)));
        Integer nRes= adminObjMapper.insert(adminObj);
        return nRes;
    }
    public Integer delAdmin(Integer adminID) {
        Integer nRes= adminObjMapper.deleteByPrimaryKey(adminID);
        return nRes;
    }
    public Integer setPassword(SetPassword setPassword) {
        Integer nRes= adminObjMapper.updatePassword(setPassword);
        return nRes;
    }
    public Integer setAdmin(AdminObj adminObj) {
        Integer nRes= adminObjMapper.updateByPrimaryKey(adminObj);
        return nRes;
    }

}
