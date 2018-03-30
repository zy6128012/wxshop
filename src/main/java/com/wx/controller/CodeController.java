package com.wx.controller;

/**
 * Created by zy612 on 2018/2/9.
 */


import com.wx.result.ProjectResult;
import com.wx.service.AddressService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@Api(value = "各结构状态类型定义",description = "各结构状态类型定义",position = 3)
@RequestMapping("/code")
public class CodeController {

    @ApiOperation(value="获取各个结构的定义", notes="获取各个结构的定义")
    @PostMapping(value = "getObjCode")
    public ProjectResult getObjCode(){
        ArrayList cityList=new ArrayList();

        ProjectResult res=new ProjectResult();
        res.setCount(cityList.size());
        res.setData(cityList);
        res.setnStatus(ProjectResult.nStatusSuccess);
        return res;
    }
    @ApiOperation(value="获取一个结构下面买个状态或者类型的定义", notes="获取各个结构下面买个状态或者类型的定义")
    @PostMapping(value = "getObjKindStatus")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "objID", value = "objID", paramType="query",required = true,dataType ="Integer")})
    public ProjectResult getObjKindStatus(@RequestParam(value = "objID") Integer objID){
        ArrayList cityList=new ArrayList();
        ProjectResult res=new ProjectResult();
        res.setCount(cityList.size());
        res.setData(cityList);
        res.setnStatus(ProjectResult.nStatusSuccess);
        return res;
    }
}
