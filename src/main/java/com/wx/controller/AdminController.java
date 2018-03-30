package com.wx.controller;


import com.github.pagehelper.PageInfo;
import com.wx.comClass.LoginRequired;
import com.wx.comClass.PagesOrder;
import com.wx.comClass.ResClass;
import com.wx.datareq.AdminGetReq;
import com.wx.datareq.AdminLoginReq;
import com.wx.datareq.SetPassword;
import com.wx.dto.AdminObj;
import com.wx.mapper.AdminObjMapper;
import com.wx.result.ProjectResult;
import com.wx.service.AdminService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.github.pagehelper.PageHelper;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminObjMapper adminObjMapper;
    @Autowired
    private AdminService adminService;


    @PostMapping(value = "adminLogin")
    @ResponseBody
    @LoginRequired
    public ProjectResult adminLogin(@RequestBody AdminLoginReq adminLoginReq) {
        ProjectResult res = new ProjectResult();
        res.setnStatus(ProjectResult.nStatusError);

        if (adminLoginReq.getLogonName() == null || adminLoginReq.getLogonName() == "") {
            res.setSzError("用户名不能为空");
            return res;
        }
        if (adminLoginReq.getPassword() == null || adminLoginReq.getPassword() == "") {
            res.setSzError("密码不能为空");
            return res;
        }
        adminLoginReq.setPassword(adminLoginReq.getPassword().toUpperCase());
        AdminObj adminObj = adminService.adminLogin(adminLoginReq);
        if (adminObj == null) {
            res.setSzError("用户名或者密码错误");
            return res;
        } else {
            res.setnStatus(ProjectResult.nStatusSuccess);
            res.setCount(1);
            List<AdminObj> adminObjs = new ArrayList();
            adminObjs.add(adminObj);
            res.setData(adminObjs);
        }
        return res;
    }

    @ResponseBody
    @PostMapping(value = "getAdmin")
    public ProjectResult getAdmin(@RequestBody AdminGetReq adminReq) {
        ProjectResult res = new ProjectResult();

        PagesOrder pagesOrder = adminReq.getPageNum();
        if (pagesOrder == null) {
            pagesOrder = new PagesOrder();
        }

        if (pagesOrder != null) {
            PageHelper.startPage(pagesOrder.getPages(), pagesOrder.getPageNum());
        }

        List<AdminObj> adminObjs = adminService.getAdmin(adminReq);
        if (pagesOrder != null) {
            PageInfo<AdminObj> pageInfo = new PageInfo<AdminObj>(adminObjs);

            res.setCount(pageInfo.getTotal());
        } else {
            res.setCount(adminObjs.size());
        }
        res.setnStatus(ProjectResult.nStatusSuccess);


        res.setData(adminObjs);
        return res;
    }

    @ApiOperation(value = "新增管理员", notes = "新增管理员描述")
    @ApiParam(name = "adminObj", value = "管理员类", required = true)
    @PostMapping(value = "addAdmin")
    public ProjectResult addAdmin(@RequestBody AdminObj adminObj) {
        ProjectResult res = new ProjectResult();
        String szLogonName = adminObj.getLogonname();
        if (szLogonName == null || szLogonName.length() < 1) {
            res.setnStatus(ProjectResult.nStatusError);
            res.setSzError("用户名不能为空");
            return res;
        }
        res.setnStatus(ProjectResult.nStatusError);
        Integer nRes = adminService.addAdmin(adminObj);
        if (nRes < 1) {
            res.setSzError("新增用户失败");
        } else {
            res.setnStatus(ProjectResult.nStatusSuccess);
            res.setCount(1);
            List<AdminObj> adminObjs = new ArrayList();
            //  adminObjs.add(adminObjRes);
            res.setData(adminObjs);
        }
        return res;
    }
    @ApiOperation(value = "修改管理员", notes = "修改管理员")
    @ApiParam(name = "adminObj", value = "管理员类", required = true)
    @PostMapping(value = "setAdmin")
    public ProjectResult setAdmin(@RequestBody AdminObj adminObj) {
        ProjectResult res = new ProjectResult();
        String szLogonName = adminObj.getLogonname();
        if (szLogonName == null || szLogonName.length() < 1) {
            res.setnStatus(ProjectResult.nStatusError);
            res.setSzError("用户名不能为空");
            return res;
        }
        Integer nadminID= adminObj.getAdminid();
        if (nadminID == null || nadminID==0) {
            res.setnStatus(ProjectResult.nStatusError);
            res.setSzError("管理员账号不能为空");
            return res;
        }
        res.setnStatus(ProjectResult.nStatusError);
        Integer nRes = adminService.setAdmin(adminObj);
        if (nRes < 1) {
            res.setSzError("修改用户失败");
        } else {
            res.setnStatus(ProjectResult.nStatusSuccess);

        }
        return res;
    }

    @ApiOperation(value = "删除管理员", notes = "删除管理员")
    @PostMapping(value = "delAdmin")
    public ProjectResult delAdmin(@RequestParam("adminID") Integer adminID) {
        ProjectResult res = new ProjectResult();
        res.setnStatus(ResClass.nStatusError);
        Integer nRes = adminService.delAdmin(adminID);
        if (nRes < 1) {
            res.setSzError("删除失败");
        } else {
            res.setnStatus(ProjectResult.nStatusSuccess);
        }
        return res;
    }

    @ApiOperation(value = "修改密码", notes = "修改密码")
    @PostMapping(value = "setPassword")
    public ProjectResult setPassword(@RequestBody SetPassword setPassword) {
        ProjectResult res = new ProjectResult();
        if (setPassword.getAdminID()== null || setPassword.getAdminID() == 0) {
            res.setnStatus(ProjectResult.nStatusError);
            res.setSzError("账号不能为空");
            return res;
        }
        if (setPassword.getPassword() == null || setPassword.getPassword() == "") {
            res.setnStatus(ProjectResult.nStatusError);
            res.setSzError("密码不能为空");
            return res;
        }
        if (!setPassword.getPassword().equals(setPassword.getPasswordConfirm())) {
            res.setnStatus(ProjectResult.nStatusError);
            res.setSzError("两次密码必须一致");
            return res;
        }

            Integer nRes = adminService.setPassword(setPassword);
        if (nRes < 1) {
            res.setSzError("密码修改失败");
        } else {
            res.setnStatus(ProjectResult.nStatusSuccess);
        }
        return res;
    }
}
