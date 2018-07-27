package com.wx.controller;

import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import com.lly835.bestpay.utils.JsonUtil;
import com.wx.datareq.OrderReq;
import com.wx.datareq.UserReq;
import com.wx.dto.OrderObj;
import com.wx.dto.ScoreObj;
import com.wx.dto.UserObj;
import com.wx.mapper.ScoreObjMapper;
import com.wx.result.ProjectResult;
import com.wx.service.OrderService;
import com.wx.service.ScoreService;
import com.wx.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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

    @Autowired
    private ScoreObjMapper scoreObjMapper;
    @Autowired
    private ScoreService scoreService;
    @Autowired
    private UserService userService;

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

        Double price = Double.parseDouble(map.get("price").toString());
        price=price/100.0;
        price=price+0.001;
        String orderID = map.get("orderid").toString();
        String ordername = map.get("ordername").toString();
        //支付请求参数
        request.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        request.setOrderId(orderID);
        request.setOrderAmount(price);
        request.setOrderName("下订单");
        request.setOpenid(userObjs.getWxid());

        try {
            PayResponse payResponse = bestPayService.pay(request);
            log.info("【发起支付】response={}", JsonUtil.toJson(payResponse));
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
        OrderReq orderReq = new OrderReq();
        orderReq.setOrderID(Integer.parseInt(order));
        List<OrderObj> list = orderService.select(orderReq);
        if (orderService.setStatus(nSend, Integer.parseInt(order)) == true) {
            projectResult.setnStatus(ProjectResult.nStatusSuccess);
        }
        Integer inScore = 0;
        if (list.size() > 0) {
            ScoreObj scoreObj = new ScoreObj();
            if (list.size() > 0) {
                Date dayNow = new Date();
                SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
                scoreObj.setScoredate(Integer.parseInt(df.format(dayNow)));
                scoreObj.setScoretime(dayNow);
                scoreObj.setScoretype(1);
                if (list.get(0).getTotalsum() < 1) {
                    inScore = list.get(0).getTotalsum() * 100;
                } else {
                    inScore = list.get(0).getTotalsum();
                }
                scoreObj.setScorevalue(inScore);
                scoreObj.setUserid(list.get(0).getUserid());
                scoreService.insert(scoreObj);
            }
            UserReq userReq = new UserReq();
            userReq.setUserID(list.get(0).getUserid());
            List<UserObj> userList = userService.getUsers(userReq);
            if (userList != null && userList.size() > 0) {
                UserObj userObjTemp = new UserObj();
                userObjTemp.setUserid(userList.get(0).getUserid());
                Integer nTempbase = userList.get(0).getUsersocre();
                userObjTemp.setUsersocre(nTempbase + inScore);
                userService.updateByPrimaryKeySelective(userObjTemp);
            }
        }
        return projectResult;
    }
}
