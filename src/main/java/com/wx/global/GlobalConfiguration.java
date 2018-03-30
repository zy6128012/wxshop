package com.wx.global;


import com.wx.interceptor.LoginInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by zy612 on 2018/3/2.
 */
@ControllerAdvice
public class GlobalConfiguration extends WebMvcConfigurerAdapter {

    private final Logger   logger = LoggerFactory.getLogger(GlobalConfiguration.class);
    @Autowired
    public LoginInterceptor loginInterceptor;

    /**拦截器配置*/
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor.loginInterceptor());
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("/user/login");
        super.addViewControllers(registry);
    }

    /**
     * 处理全局未处理的异常
     *
     * @param exception
     * @return
     */
    /*@ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ProjectResult exception(Exception exception) {
    	logger.error(exception.getMessage());
    	UserInfoDo ufo=new UserInfoDo();
    	ufo.setLoginName("loginName");
    	ufo.setLoginPassword("password");
        ProjectResult pr=new ProjectResult("xxx", "unknown error", ufo);
        return pr;
    }*/

    /**
     * 将键值对添加到全局，所有@RequestMapping的方法可以获得此键值对
     *
     * @param model
     */
    @ModelAttribute
    public void addAttributes(Model model) {
      //  model.addAttribute("shuaideren", "-shuaideren");
    }
}
