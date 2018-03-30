package com.wx.controller;

/**
 * Created by zy612 on 2018/2/9.
 */

import com.wx.datareq.LoginReq;
import com.wx.comClass.LoginRequired;
import com.wx.dto.AddressObj;
import com.wx.mapper.AddressObjMapper;
import com.wx.result.ProjectResult;
import com.wx.service.AddressService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@Api(value = "用户地址模块",description = "个人信息中的邮寄地址维护",position = 3)
@RequestMapping("/address")
public class AddressController {
    @Autowired
    private AddressObjMapper addressObjMapper;
    @Autowired
    private AddressService addressService;

    @ApiOperation(value="新增个人的通讯地址", notes="新增个人的通讯地址")
    @ApiParam(name = "addressObj", value = "个人的通讯地址", required = true)
    @LoginRequired
    @PostMapping(value = "addAddress")
    public ProjectResult addAddress(@RequestBody AddressObj addressObj){
        ArrayList addressList=new ArrayList();
        addressList.add(addressObj);
        ProjectResult res=new ProjectResult();
        res.setCount(addressList.size());
        res.setData(addressList);
        res.setnStatus(ProjectResult.nStatusSuccess);
        return res;
    }

    @ApiOperation(value="更新个人的通讯地址", notes="更新个人的通讯地址")
    @ApiParam(name = "addressObj", value = "个人的通讯地址", required = true)
    @LoginRequired
    @PostMapping(value = "updateAddress")
    public ProjectResult updateAddress(@RequestBody AddressObj addressObj){
        ArrayList addressList=new ArrayList();
        addressList.add(addressObj);
        ProjectResult res=new ProjectResult();
        res.setCount(addressList.size());
        res.setData(addressList);
        res.setnStatus(ProjectResult.nStatusSuccess);
        return res;
    }

    @ApiOperation(value="删除个人的通讯地址", notes="删除个人的通讯地址")
    @PostMapping(value = "delAddress")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "addressID", value = "通讯地址ID号", paramType="query",required = true,dataType ="Integer")})
    public ProjectResult delAddress(@RequestParam(value = "addressID") Integer addressID){
        ArrayList addressList=new ArrayList();
        AddressObj obj=new AddressObj();
        obj.setAddresid(addressID);
        addressList.add(obj);
        ProjectResult res=new ProjectResult();
        res.setCount(addressList.size());
        res.setData(addressList);
        res.setnStatus(ProjectResult.nStatusSuccess);
        return res;
    }
}
