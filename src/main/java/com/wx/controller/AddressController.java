package com.wx.controller;

/**
 * Created by zy612 on 2018/2/9.
 */

import com.alibaba.fastjson.JSONObject;
import com.wx.comClass.LoginRequired;
import com.wx.dto.AddressObj;
import com.wx.mapper.AddressObjMapper;
import com.wx.result.ProjectResult;
import com.wx.service.AddressService;
import com.wx.service.WeChatAuthServiceImpl;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@Api(value = "用户地址模块",description = "个人信息中的邮寄地址维护",position = 3)
@RequestMapping("/address")
public class AddressController {
    @Autowired
    private AddressObjMapper addressObjMapper;
    @Autowired
    private AddressService addressService;

    WeChatAuthServiceImpl weChatAuthService=new WeChatAuthServiceImpl();

    @ApiOperation(value = "新增个人的通讯地址", notes = "新增个人的通讯地址")
    @ApiParam(name = "addressObj", value = "个人的通讯地址", required = true)
    @LoginRequired
    @PostMapping(value = "addAddress")
    public ProjectResult addAddress(@RequestBody AddressObj addressObj) {
        ProjectResult res = new ProjectResult();
        if (addressObj.getProvicename() == null || addressObj.getProvicename() == "") {
            res.setSzError("省信息不能为空");
            res.setnStatus(ProjectResult.nStatusError);
            return res;
        }
        if (addressObj.getCityname() == null || addressObj.getCityname() == "") {
            res.setSzError("市信息不能为空");
            res.setnStatus(ProjectResult.nStatusError);
            return res;
        }
        if (addressObj.getCountryname() == null || addressObj.getCountryname() == "") {
            res.setSzError("县信息不能为空");
            res.setnStatus(ProjectResult.nStatusError);
            return res;
        }
        if (addressObj.getDetailaddress() == null || addressObj.getDetailaddress() == "") {
            res.setSzError("详细信息不能为空");
            res.setnStatus(ProjectResult.nStatusError);
            return res;
        }
        if (addressObj.getTelphone() == null || addressObj.getTelphone() == "") {
            res.setSzError("联系电话不能为空");
            res.setnStatus(ProjectResult.nStatusError);
            return res;
        }
        if (addressObj.getUserid() == null || addressObj.getUserid() == 0) {
            res.setSzError("用户信息不能为空");
            res.setnStatus(ProjectResult.nStatusError);
            return res;
        }
        Integer nRes = addressService.add(addressObj);
        if (nRes > 0) {
            res.setnStatus(ProjectResult.nStatusSuccess);
        } else {
            res.setnStatus(ProjectResult.nStatusSuccess);
            res.setSzError("新建联系地址失败");
        }
        return res;
    }



    @ApiOperation(value = "微信获取登录界面", notes = "微信获取登录界面")
    @LoginRequired
    @RequestMapping(value = "/wxLoginPage",method = RequestMethod.GET)
    public ProjectResult wxLoginPage() throws Exception {
        ProjectResult res = new ProjectResult();

        String uri = weChatAuthService.getAuthorizationUrl();
        return res;//loginPage(uri);
    }
    @ApiOperation(value = "微信登录", notes = "微信登录")
    @LoginRequired
    @RequestMapping(value = "/wechat")
    public void callback(String code,HttpServletRequest request,HttpServletResponse response) throws Exception {
        String result = weChatAuthService.getAccessToken(code);
        JSONObject jsonObject = JSONObject.parseObject(result);

        String access_token = jsonObject.getString("access_token");
        String openId = jsonObject.getString("openId");
//        String refresh_token = jsonObject.getString("refresh_token");

        // 保存 access_token 到 cookie，两小时过期
        Cookie accessTokencookie = new Cookie("accessToken", access_token);
        accessTokencookie.setMaxAge(60 *2);
        response.addCookie(accessTokencookie);

        Cookie openIdCookie = new Cookie("openId", openId);
        openIdCookie.setMaxAge(60 *2);
        response.addCookie(openIdCookie);

        //根据openId判断用户是否已经登陆过
       // KmsUser user = userService.getUserByCondition(openId);
/*
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/student/html/index.min.html#/bind?type="+Constants.LOGIN_TYPE_WECHAT);
        } else {
            //如果用户已存在，则直接登录
            response.sendRedirect(request.getContextPath() + "/student/html/index.min.html#/app/home?open_id=" + openId);
        }
    */
    }

    @ApiOperation(value = "更新个人的通讯地址", notes = "更新个人的通讯地址")
    @ApiParam(name = "addressObj", value = "个人的通讯地址", required = true)
    @LoginRequired
    @PostMapping(value = "updateAddress")
    public ProjectResult updateAddress(@RequestBody AddressObj addressObj) {
        ProjectResult res = new ProjectResult();
        if (addressObj.getAddresid() == null || addressObj.getAddresid() != 0) {
            res.setSzError("修改必须参入地址ID号");
            res.setnStatus(ProjectResult.nStatusError);
            return res;
        }
        Integer nRes = addressService.update(addressObj);
        if (nRes > 0) {
            res.setnStatus(ProjectResult.nStatusSuccess);
        } else {
            res.setnStatus(ProjectResult.nStatusSuccess);
            res.setSzError("修改联系地址失败");
        }
        return res;
    }

    @ApiOperation(value = "删除个人的通讯地址", notes = "删除个人的通讯地址")
    @PostMapping(value = "delAddress")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "addressID", value = "通讯地址ID号", paramType = "query", required = true, dataType = "Integer")})
    public ProjectResult delAddress(@RequestParam(value = "addressID") Integer addressID) {
        ProjectResult res = new ProjectResult();
        if (addressID == 0) {
            res.setnStatus(ProjectResult.nStatusError);
            res.setSzError("联系地址ID号不能为0");
        }
        Integer nRes = addressService.delete(addressID);
        if (nRes > 0) {
            res.setnStatus(ProjectResult.nStatusSuccess);
        } else {
            res.setnStatus(ProjectResult.nStatusSuccess);
            res.setSzError("修改联系地址失败");
        }
        return res;
    }
}
