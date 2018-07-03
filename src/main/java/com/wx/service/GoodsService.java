package com.wx.service;

import com.wx.datareq.GoodsReq;
import com.wx.dto.GoodsObj;
import com.wx.mapper.GoodsObjMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by zy612 on 2018/2/9.
 */
@Service
public class GoodsService {

    @Autowired
    private GoodsObjMapper goodsObjMapper;

    @Transactional
    public Integer add(GoodsObj goodsObj) {
        Integer nGoodsid = goodsObjMapper.insert(goodsObj);
        return nGoodsid;
    }

    @Transactional
    public Integer update(GoodsObj goodsObj) {
        Integer nGoodsid = goodsObjMapper.updateByPrimaryKeySelective(goodsObj);
        return nGoodsid;
    }
    public Integer updatePhoto(Integer goodID,String goodPhoto) {
        Integer nGoodsid = goodsObjMapper.updatePhoto(goodPhoto,goodID);
        return nGoodsid;
    }
    public Integer updateDetail(Integer goodID,String goodDetail) {
        Integer nGoodsid = goodsObjMapper.updateDetail(goodDetail,goodID);
        return nGoodsid;
    }
    public Integer deleteByPrimaryKey(Integer goodsid) {
        return goodsObjMapper.deleteByPrimaryKey(goodsid);
    }

    public List<GoodsObj> select(GoodsReq goodsReq) {
        return goodsObjMapper.select(goodsReq);
    }
}
