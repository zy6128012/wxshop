package com.wx.controller;

import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import com.lly835.bestpay.utils.JsonUtil;
import com.wx.dto.UserObj;
import com.wx.result.ProjectResult;
import com.wx.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.Random;

/**
 * 支付相关
 * @version 1.0 2017/3/2
 * @auther <a href="mailto:lly835@163.com">廖师兄</a>
 * @since 1.0
 */
@RestController
@Slf4j
@RequestMapping("/pay")
public class PayController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private BestPayServiceImpl bestPayService;

    /**
     * 发起支付
     */
    @ResponseBody
    @GetMapping(value = "/pay")
    public ProjectResult pay(HttpServletRequest requestr, @RequestParam Map<String, Object> map) {
        ProjectResult projectResult = new ProjectResult();
        projectResult.setnStatus(ProjectResult.nStatusSuccess);
        PayRequest request = new PayRequest();
        Random random = new Random();

        HttpSession seesion = requestr.getSession();
        UserObj userObjs = (UserObj) seesion.getAttribute("loginUser");

        Double price= Double.parseDouble(map.get("price").toString());
        String orderID=map.get("orderid").toString();
        String ordername=map.get("ordername").toString();
        //支付请求参数
        request.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        request.setOrderId(orderID);
        request.setOrderAmount(price);
        request.setOrderName(ordername);
        request.setOpenid(userObjs.getWxid());

        try {
            PayResponse payResponse = bestPayService.pay(request);
            projectResult.setData(payResponse);
            return projectResult;

        } catch (Exception e) {
            log.info(e.toString());

        }
        return projectResult;
    }

    /**
     * 异步回调
     */
    @PostMapping(value = "/notify")
    public ProjectResult notify(@RequestBody String notifyData) throws Exception {
        PayResponse response = bestPayService.asyncNotify(notifyData);
        ProjectResult projectResult = new ProjectResult();
        String order = response.getOrderId();
        Integer nSend = 2;
        if (orderService.setStatus(nSend,Integer.parseInt(order)) == true) {
            projectResult.setnStatus(ProjectResult.nStatusSuccess);
        }
        log.info("【异步回调】response={}", JsonUtil.toJson(response));
        return projectResult;
    }


}
