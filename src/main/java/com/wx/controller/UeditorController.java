package com.wx.controller;


import com.alibaba.fastjson.JSONException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wx.comClass.*;
import com.wx.datareq.AdminGetReq;
import com.wx.datareq.AdminLoginReq;
import com.wx.datareq.SetPassword;
import com.wx.dto.AdminObj;
import com.wx.dto.NewsObj;
import com.wx.dto.ShopinfoObj;
import com.wx.mapper.AdminObjMapper;
import com.wx.mapper.NewsObjMapper;
import com.wx.result.ProjectResult;
import com.wx.service.AdminService;
import com.wx.service.GoodsService;
import com.wx.service.NewsService;
import com.wx.service.ShopinfoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationHome;
import org.springframework.expression.spel.ast.Operator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Logger;

@Controller
@RequestMapping("/ueditor")
public class UeditorController {
    @Autowired
    private GoodsService goodsServie;
    @Autowired
    private NewsService newsService;

    @Autowired
    private ShopinfoService shopinfoService;
    @RequestMapping(value = "/uploaddetail")
    @ResponseBody
    public ProjectResult uploadDetail(HttpServletRequest request) {
        ProjectResult res = new ProjectResult();
        String szID = request.getParameter("id");
        String szType = request.getParameter("type");
        String szValue = request.getParameter("editor");
        if (szType.toLowerCase().equals("photo")) {
            goodsServie.updatePhoto(Integer.parseInt(szID), szValue);
        } else if (szType.toLowerCase().equals("detail")) {
            goodsServie.updateDetail(Integer.parseInt(szID), szValue);
        } else if (szType.toLowerCase().equals("news")) {
            NewsObj newsObj = new NewsObj();
            String szTitle = request.getParameter("title");
            Date dayNow = new Date();
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
            newsObj.setNewsdate(Integer.parseInt(df.format(dayNow)));
            newsObj.setNewtime(dayNow);
            newsObj.setContant(szValue);
            newsObj.setNewtitle(szTitle);
            newsService.add(newsObj);
        }else if (szType.toLowerCase().equals("shopinfo")) {
            ShopinfoObj shopinfoObj = new ShopinfoObj();
            String szTitle = request.getParameter("title");
            Date dayNow = new Date();
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
            shopinfoObj.setSdate(Integer.parseInt(df.format(dayNow)));
            shopinfoObj.setStime(dayNow);
            shopinfoObj.setContant(szValue);
            shopinfoObj.setStitle(szTitle);
            shopinfoObj.setType(1);//关于我们
            shopinfoService.add(shopinfoObj);
        }
        return res;

    }

    @RequestMapping("/")
    private String showPage() {
        return "index";
    }

    private final org.slf4j.Logger logger = LoggerFactory.getLogger(ControllerExceptionHandleAdvice.class);

    @RequestMapping(value = "/config")
    @ResponseBody
    public String ueditor(HttpServletRequest request) {

        return PublicMsg.UEDITOR_CONFIG;
    }

    @RequestMapping(value = "/uploadimage")
    @ResponseBody
    public Ueditor imgUpload(HttpServletRequest request, MultipartFile upfile) {
        Ueditor ueditor = new Ueditor();
        String contentType = upfile.getContentType();
        String fileName = UUID.randomUUID().toString() + upfile.getSize() + getExtName(upfile.getOriginalFilename());// upfile.getOriginalFilename();
        /*System.out.println("fileName-->" + fileName);
        System.out.println("getContentType-->" + contentType);*/
        ApplicationHome home = new ApplicationHome(getClass());
        String szTemp = home.getDir().getAbsolutePath();
        String szsppath = szTemp + "\\imgupload";

        File file = new File(szsppath);
        if (!file.exists()) {
            file.mkdir();
        }

        String filePath = szsppath;//request.getSession().getServletContext().getRealPath("imgupload/");
        try {
            uploadFile(upfile.getBytes(), filePath, fileName);
            ueditor.setUrl("/imgupload/" + fileName);
            ueditor.setTitle(fileName);
            ueditor.setState("SUCCESS");
        } catch (Exception e) {

        }
        return ueditor;
    }

    private String getExtName(String s) {
        int i = s.indexOf('.');
        int leg = s.length();
        return (i > 0 ? (i + 1) == leg ? " " : s.substring(i, s.length()) : " ");
    }

    public void uploadFile(byte[] file, String filePath, String fileName) throws Exception {
        File targetFile = new File(filePath);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(filePath + "\\" + fileName);
        out.write(file);
        out.flush();
        out.close();
    }

}
