package com.wx.service;

import com.wx.dto.AddressObj;
import com.wx.dto.AreaObj;
import com.wx.mapper.AddressObjMapper;
import com.wx.mapper.UserObjMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by zy612 on 2018/2/9.
 */
@Service
public class AddressService {

    @Autowired
    private AddressObjMapper addressObjMapper;

    @Transactional
    public Integer add(AddressObj areaObj) {
        return addressObjMapper.insert(areaObj);
    }
    @Transactional
    public Integer update(AddressObj areaObj) {
        return addressObjMapper.updateByPrimaryKeySelective(areaObj);
    }
    public  Integer delete(Integer nAddress) {
        return addressObjMapper.deleteByPrimaryKey(nAddress);
    }
    @Transactional
    public AddressObj selectByID(Integer addresid) {
        return addressObjMapper.selectByPrimaryKey(addresid);
    }
    @Transactional
    public List<AddressObj> selectByUserID(Integer userid) { return addressObjMapper.selectByUserID(userid); }
}
