package com.wx.controller;


import com.wx.datareq.NewsReq;
import com.wx.dto.NewsObj;
import com.wx.dto.ShopinfoObj;
import com.wx.mapper.NewsObjMapper;
import com.wx.mapper.ShopinfoObjMapper;
import com.wx.result.ProjectResult;
import com.wx.service.NewsService;
import com.wx.service.ShopinfoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/shopinfo")
public class ShopinfoController {
    @Autowired
    private ShopinfoObjMapper shopinfoObjMapper;
    @Autowired
    private ShopinfoService shopinfoService;

    @ApiOperation(value = "获取关于我们", notes = "获取关于我们")
    @ResponseBody
    @GetMapping(value = "getAboutus")
    public ProjectResult getAboutus() {
        ProjectResult res = new ProjectResult();
        res.setnStatus(ProjectResult.nStatusSuccess);
        List<ShopinfoObj> shopObj = shopinfoService.selectByType(1);
        if(shopObj!=null&&shopObj.size()>0){
            res.setData(shopObj.get(0));
        }
        return res;

    }

}
