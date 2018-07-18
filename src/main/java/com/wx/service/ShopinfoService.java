package com.wx.service;

import com.wx.datareq.NewsReq;
import com.wx.dto.NewsObj;
import com.wx.dto.ShopinfoObj;
import com.wx.mapper.NewsObjMapper;
import com.wx.mapper.ShopinfoObjMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zy612 on 2018/2/9.
 */
@Service
public class ShopinfoService {

    @Autowired
    private ShopinfoObjMapper shopinfoObjMapper;


    public ShopinfoObj selectByPrimaryKey(Integer id) {
        ShopinfoObj shopinfoObj = shopinfoObjMapper.selectByPrimaryKey(id);
        return shopinfoObj;
    }

    public List<ShopinfoObj> selectByType(Integer id) {
        List<ShopinfoObj> shopinfoObj = (List<ShopinfoObj>) shopinfoObjMapper.selectByType(id);
        return shopinfoObj;
    }

    public List<ShopinfoObj> select() {
        List<ShopinfoObj> shopinfoObj = shopinfoObjMapper.select();
        return shopinfoObj;
    }

    public Integer add(ShopinfoObj shopinfoObj) {
        Integer newsid = shopinfoObjMapper.insert(shopinfoObj);
        return newsid;
    }

    public Integer del(Integer id) {
        Integer newsid = shopinfoObjMapper.deleteByPrimaryKey(id);
        return newsid;
    }
}
