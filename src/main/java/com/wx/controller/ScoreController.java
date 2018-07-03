package com.wx.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wx.comClass.LoginRequired;
import com.wx.comClass.PagesOrder;
import com.wx.comClass.ResClass;
import com.wx.datareq.AdminGetReq;
import com.wx.datareq.AdminLoginReq;
import com.wx.datareq.ScoreReq;
import com.wx.datareq.SetPassword;
import com.wx.dto.AdminObj;
import com.wx.dto.ScoreObj;
import com.wx.dto.UserObj;
import com.wx.mapper.AdminObjMapper;
import com.wx.mapper.ScoreObjMapper;
import com.wx.result.ProjectResult;
import com.wx.service.AdminService;
import com.wx.service.ScoreService;
import com.wx.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/score")
public class ScoreController {
    @Autowired
    private ScoreObjMapper scoreObjMapper;
    @Autowired
    private ScoreService scoreService;
    @Autowired
    private UserService userService;


    @ApiOperation(value = "获取积分", notes = "获取积分")
    @ResponseBody
    @PostMapping(value = "getScore")
    public ProjectResult getAdmin(@RequestBody ScoreReq scoreReq) {
        ProjectResult res = new ProjectResult();

        PagesOrder pagesOrder = scoreReq.getPageNum();
        if (pagesOrder == null) {
            pagesOrder = new PagesOrder();
        }

        if (pagesOrder != null) {
            PageHelper.startPage(pagesOrder.getPages(), pagesOrder.getPageNum());
        }
        if (scoreReq != null && scoreReq.getTelphone() != null && scoreReq.getTelphone() != "") {
            UserObj userObjTemp = new UserObj();
            userObjTemp.setTelphone(scoreReq.getTelphone());
            List<UserObj> objList = userService.getUsers(userObjTemp);
            if (objList != null && objList.size() > 0) {
                scoreReq.setUserID(objList.get(0).getUserid());
            }
        }

        List<ScoreObj> scoreObjList = scoreService.getScore(scoreReq);
        if (pagesOrder != null) {
            PageInfo<ScoreObj> pageInfo = new PageInfo<ScoreObj>(scoreObjList);

            res.setCount(pageInfo.getTotal());
        } else {
            res.setCount(scoreObjList.size());
        }
        for (Integer i = 0; i < scoreObjList.size(); i++) {
            UserObj userObj = new UserObj();
            userObj.setUserid(scoreObjList.get(i).getUserid());
            scoreObjList.get(i).setUserObj(userService.getUsers(userObj).get(0));
        }
        res.setnStatus(ProjectResult.nStatusSuccess);
        res.setData(scoreObjList);
        return res;
    }


}
