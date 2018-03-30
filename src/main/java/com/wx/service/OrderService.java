package com.wx.service;

import com.wx.mapper.AddressObjMapper;
import com.wx.mapper.OrderObjMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by zy612 on 2018/2/9.
 */
@Service
public class OrderService {

    @Autowired
    private OrderObjMapper orderObjMapper;

    @Transactional
    public  void insertTwo() {
        /*
        CityObj city1 = new CityObj();
        city1.setName("厦门");
        city1.setState("福建");
        cityObjMapper.insert(city1);

       // System.out.print(1/0);

        CityObj city2 = new CityObj();
        city2.setName("深圳");
        city2.setState("广东");
        cityObjMapper.insert(city2);
        */

    }
}
