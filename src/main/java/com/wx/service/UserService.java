package com.wx.service;

import com.wx.dto.UserObj;
import com.wx.mapper.UserObjMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Created by zy612 on 2018/2/9.
 */
@Service
public class UserService {
    @Autowired
    private UserObjMapper userObjMapper;

    @Transactional
    public UserObj getUserByWXID(String wxid) {
        UserObj userReq = new UserObj();
        userReq.setWxid(wxid);
        List<UserObj> userObjsRes = userObjMapper.select(userReq);
        if (userObjsRes != null && userObjsRes.size() > 0) {
            return userObjsRes.get(0);
        }
        return null;
    }

    public List<UserObj> getUsers(UserObj userObj) {
        UserObj userReq = new UserObj();
        List<UserObj> userObjsRes = userObjMapper.select(userObj);
        if (userObjsRes != null && userObjsRes.size() > 0) {
            return userObjsRes;
        }
        return null;
    }

    public Integer addUser(UserObj userObj) {
        Date date = new Date();
        Timestamp timeStamp = new Timestamp(date.getTime());
        userObj.setCreatetime(timeStamp);
        Integer nRes = userObjMapper.insert(userObj);
        return nRes;
    }

    public Integer updateByPrimaryKeySelective(UserObj userObj) {
        return userObjMapper.updateByPrimaryKeySelective(userObj);
    }
}
