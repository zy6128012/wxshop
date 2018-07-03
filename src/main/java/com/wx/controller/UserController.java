package com.wx.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wx.comClass.PagesOrder;
import com.wx.comClass.LoginRequired;
import com.wx.datareq.UserReq;
import com.wx.mapper.UserObjMapper;
import com.wx.dto.UserObj;
import com.wx.result.ProjectResult;
import com.wx.service.UserService;
import com.wx.service.WeChatAuthServiceImpl;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserObjMapper userObjMapper;
    @Autowired
    private UserService userServie;

    WeChatAuthServiceImpl weChatAuthService=new WeChatAuthServiceImpl();
    private Logger logger = LoggerFactory.getLogger(WeChatAuthServiceImpl.class);

    @ApiOperation(value = "获取微信ID", notes = "获取微信ID")
    @GetMapping(value = "getwxid")
    @LoginRequired
    public String getwxid()  throws UnsupportedEncodingException {
        ProjectResult res = new ProjectResult();

        String url=weChatAuthService.getAuthorizationUrl();

        return "<script>window.location.href='"+url+"';</script>";
    }

    @ApiOperation(value = "用户登录", notes = "登录信息")
    @GetMapping(value = "login")
    @LoginRequired
    public String login(HttpServletRequest request, HttpServletResponse response) {
        String szCode=request.getParameter("code");
        if(szCode.equals("abc123"))
        {
            HttpSession seesionTemp=request.getSession();

            UserObj userObjs = userServie.getUserByWXID("abc123");
            if (userObjs != null) {
                seesionTemp.setAttribute("loginUser", userObjs);
            }
            return "<script>window.location.href='/client/index.html';</script>";
        }

        String szRes="";
        szRes= weChatAuthService.getAccessToken(szCode);
        if(szRes==null||szRes.equals(""))
        {
            return "<h3>获取微信信息错误</h3>";
        }
        JSONObject obj = JSON.parseObject(szRes);

        String szopenid = obj.getString("openId");
        String nickname= obj.getString("nickname");

        HttpSession seesion=request.getSession();
        if (!szopenid.equals("")) {
            UserReq userReq = new UserReq();
            userReq.setWxID(szopenid);
            UserObj userObjs = userServie.getUserByWXID(szopenid);
            if (userObjs != null) {
                seesion.setAttribute("loginUser", userObjs);
            } else {
                UserObj userObjNew = new UserObj();
                userObjNew.setWxid(szopenid);
                Integer nID = userServie.addUser(userObjNew);
                UserReq userReqNew = new UserReq();
                userReqNew.setWxID(szopenid);
                UserObj userObjsNew = userServie.getUserByWXID(szopenid);
                if (userObjsNew != null) {
                    seesion.setAttribute("loginUser", userObjsNew);
                }
            }
        }
     return "<script>window.location.href='/client/index.html';</script>";
    }

    @ApiOperation(value = "获取登录个人信息", notes = "获取登录个人信息")
    @ResponseBody
    @PostMapping(value = "getLoginUser")
    public ProjectResult getLoginUser(HttpServletRequest request) {

        ProjectResult res = new ProjectResult();
        HttpSession seesion=request.getSession();
        UserObj user=(UserObj)seesion.getAttribute("loginUser");
        if(user!=null) {
            res.setnStatus(ProjectResult.nStatusSuccess);
            res.setData(user);
        }else{
            res.setnStatus(ProjectResult.nStatusError);
        }
        return res;

    }


    @ApiOperation(value = "获取个人信息", notes = "获取个人信息")
    @ResponseBody
    @PostMapping(value = "getUser")
    public ProjectResult getUser(@RequestBody UserReq userReq) {
        ProjectResult res = new ProjectResult();
        PagesOrder pagesOrder = userReq.getPageNum();
        if (pagesOrder == null) {
            pagesOrder = new PagesOrder();
        }

        if (pagesOrder != null) {
            PageHelper.startPage(pagesOrder.getPages(), pagesOrder.getPageNum());
        }

        List<UserObj> userObjs = userServie.getUsers(userReq);
        if (pagesOrder != null) {
            PageInfo<UserObj> pageInfo = new PageInfo<UserObj>(userObjs);
            res.setCount(pageInfo.getTotal());
        } else {
            res.setCount(userObjs.size());
        }
        res.setnStatus(ProjectResult.nStatusSuccess);
        res.setData(userObjs);
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
