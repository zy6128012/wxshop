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
import java.util.ArrayList;
import java.util.List;


@RestController
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

    @ApiOperation(value = "获取个人的通讯地址", notes = "获取个人的通讯地址")
    @ApiParam(name = "getAddress", value = "个人的通讯地址", required = true)
    @LoginRequired
    @GetMapping(value = "getAddress")
    public ProjectResult getAddress() {
        ProjectResult res = new ProjectResult();

        List<AddressObj> addList= addressService.selectByUserID(10);
        if (addList.size() > 0) {
            res.setData(addList);
            res.setCount(addList.size());
            res.setnStatus(ProjectResult.nStatusSuccess);
        } else {
            res.setnStatus(ProjectResult.nStatusSuccess);
            res.setSzError("获取联系地址失败");
        }
        return res;
    }
    @ApiOperation(value = "获取个人的通讯地址byid", notes = "获取个人的通讯地址byid")
    @ApiParam(name = "getAddressbyid", value = "获取个人的通讯地址byid", required = true)
    @LoginRequired
    @GetMapping(value = "getAddressbyid")
    public ProjectResult getAddressbyid(Integer id) {
        ProjectResult res = new ProjectResult();

        AddressObj address= addressService.selectByID(id);
        if (address!=null) {
            List<AddressObj> Temp=new ArrayList<AddressObj>();
            Temp.add(address);
            res.setData(Temp);
            res.setCount(1);
            res.setnStatus(ProjectResult.nStatusSuccess);
        } else {
            res.setnStatus(ProjectResult.nStatusSuccess);
            res.setSzError("获取联系地址失败");
        }
        return res;
    }
    @ApiOperation(value = "更新个人的通讯地址", notes = "更新个人的通讯地址")
    @ApiParam(name = "addressObj", value = "个人的通讯地址", required = true)
    @PostMapping(value = "updateAddress")
    public ProjectResult updateAddress(@RequestBody AddressObj addressObj) {
        ProjectResult res = new ProjectResult();
        if (addressObj.getAddresid() == null) {
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
    @GetMapping(value = "delAddress")
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
