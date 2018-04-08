package com.wx.controller;


import com.alibaba.fastjson.JSONObject;
import com.wx.datareq.LoginReq;
import com.wx.comClass.LoginRequired;
import com.wx.comClass.ResClass;
import com.wx.dto.AddressObj;
import com.wx.mapper.UserObjMapper;
import com.wx.dto.UserObj;
import com.wx.result.ProjectResult;
import com.wx.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserObjMapper userObjMapper;
    @Autowired
    private UserService userServie;


    /*
    @ApiOperation(value = "用户登录(暂时用不到)", notes = "登录信息")
    @PostMapping(value = "login")
    @LoginRequired
    public ProjectResult login(@RequestBody LoginReq loginReq) {
        ProjectResult res = new ProjectResult();
        res.setnStatus(1);
        res.setCount(1);

        res.setData(loginReq);
        return res;
    }
    */

    @ResponseBody
    @LoginRequired
    @PostMapping(value = "getUser")
    public ProjectResult getUser(UserObj user) {
        List<UserObj> userObjs = userServie.getUsers(user);
        ProjectResult res = new ProjectResult();
        if(userObjs!=null) {
            res.setCount(userObjs.size());
            res.setData(userObjs);
        }
        res.setnStatus(ResClass.nStatusSuccess);
        return res;
    }

    @ApiOperation(value = "更新个人信息接口", notes = "更新个人信息接口")
    @ApiParam(name = "user", value = "用户类", required = true)
    @PostMapping(value = "updateUser")
    public ProjectResult updateUser(@RequestBody UserObj user) {
        ProjectResult projectResult = new ProjectResult();
        if(user.getUserid()==null||user.getUserid()==0)
        {
            projectResult.setnStatus(ProjectResult.nStatusError);
            projectResult.setSzError("用户账号不能为空");
            return projectResult;
        }
        Integer nUpdate = userServie.updateByPrimaryKeySelective(user);
        if (nUpdate < 1) {
            projectResult.setnStatus(ProjectResult.nStatusError);
        } else {
            projectResult.setnStatus(ProjectResult.nStatusSuccess);
        }
        return projectResult;
    }

    @ApiOperation(value = "通过ticket获取openid", notes = "通过ticket获取openid")
    @LoginRequired
    @PostMapping(value = "getOpenID")
    public ProjectResult getOpenID(@RequestParam("ticket") String ticket) {
        ProjectResult projectResult = new ProjectResult();
        List<UserObj> userObjs = new ArrayList<UserObj>();
        String szOpenID = getOpenIDByTicket(ticket);
        UserObj userObj = userServie.getUserByWXID(szOpenID);
        if (userObj != null) {
            projectResult.setCount(1);
            projectResult.setnStatus(ProjectResult.nStatusSuccess);
            userObjs.add(userObj);
            projectResult.setData(userObjs);
            return projectResult;
        } else {
            userObj=new UserObj();
            userObj.setWxid(szOpenID);
            Integer nRes = userServie.addUser(userObj);
            if (nRes > 0) {
                userObj.setUserid(userObj.getUserid());
                projectResult.setCount(1);
                projectResult.setnStatus(ProjectResult.nStatusSuccess);
                userObjs.add(userObj);
                projectResult.setData(userObjs);
                return projectResult;
            } else {
                projectResult.setnStatus(ProjectResult.nStatusError);
                projectResult.setSzError("获取信息失败");
                return projectResult;
            }
        }
    }

    public String getOpenIDByTicket(String szTicket) {
        String szResOpendID = szTicket + "123";
        return szResOpendID;
    }
}
