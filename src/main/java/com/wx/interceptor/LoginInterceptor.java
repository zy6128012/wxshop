package com.wx.interceptor;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.wx.comClass.LoginRequired;
import com.wx.comClass.ResClass;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Created by zy612 on 2018/3/2.
 */
@Configuration
public class LoginInterceptor implements HandlerInterceptor {
    public LoginInterceptor loginInterceptor() {
        return new LoginInterceptor();
    }
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) throws Exception {
        if((httpServletRequest.getRequestURI().toString().startsWith("/swagger-resources"))) {
            return true;
        }
        if((httpServletRequest.getRequestURI().toString().startsWith("/v2"))) {
            return true;
        }
        if((httpServletRequest.getRequestURI().toString().startsWith("/webjars"))) {
            return true;
        }
/*
        if (handler instanceof HandlerMethod) {
            // 判断当前method上是否有标签;
            HandlerMethod hm = (HandlerMethod) handler;
            if (hm.getMethodAnnotation(LoginRequired.class) == null) {
                // r如果有,判断当前是否用户登录,如果没有登录,跳转到登录页面
                PrintWriter pw = null;
                try {
                    httpServletResponse.setCharacterEncoding("UTF-8");
                    httpServletResponse.setContentType("application/json; charset=utf-8");
                    ResClass res = new ResClass();
                    res.setSzError("调用之前必须先登陆");
                    String jsonString = JSONObject.toJSONString(res);
                    pw = httpServletResponse.getWriter();
                    pw.append(jsonString);
                } catch (Exception e) {
                    // logger.error("request refused:", e.getMessage());
                } finally {
                    pw.close();
                }
                return false;
            }

        }
        */

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
