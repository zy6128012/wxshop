package com.wx.comClass;

import com.wx.global.GlobalConfiguration;
import com.wx.result.ProjectResult;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.logging.Logger;

/**
 * Created by zy612 on 2018/4/2.
 */
//@RestControllerAdvice
public class ControllerExceptionHandleAdvice {

    private final org.slf4j.Logger logger = LoggerFactory.getLogger(ControllerExceptionHandleAdvice.class);
    @ExceptionHandler
    public ProjectResult handler(HttpServletRequest req, HttpServletResponse res, Exception e) {
        logger.info("Restful Http请求发生异常...");
        ProjectResult projectResult=new ProjectResult();
        if (res.getStatus() == HttpStatus.BAD_REQUEST.value()) {
            projectResult.setnStatus(ProjectResult.nStatusSuccess);
            projectResult.setSzError("修改返回状态值为200");//.setStatus(HttpStatus.OK.value());
        }

        if (e instanceof NullPointerException) {
            logger.error("代码00：" + e.getMessage(), e);
          //  return ResultEntity.fail("发生空指针异常");
            projectResult.setnStatus(ProjectResult.nStatusError);
            projectResult.setSzError("发生空指针异常；+" + e.getMessage());//.setStatus(HttpStatus.OK.value());

        } else if (e instanceof IllegalArgumentException) {
            projectResult.setnStatus(ProjectResult.nStatusError);
            projectResult.setSzError("请求参数类型不匹配：" + e.getMessage());

        } else if (e instanceof SQLException) {
            projectResult.setnStatus(ProjectResult.nStatusError);
            projectResult.setSzError("数据库访问异常：" + e.getMessage());
        } else {
            projectResult.setnStatus(ProjectResult.nStatusError);
            projectResult.setSzError("服务器代码发生异常：" + e.getMessage());
        }
        return projectResult;
    }
}
