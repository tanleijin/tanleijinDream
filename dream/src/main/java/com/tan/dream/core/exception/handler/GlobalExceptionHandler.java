package com.tan.dream.core.exception.handler;


import com.tan.dream.core.exception.DreamException;
import com.tan.dream.common.log.Service.LogService;
import com.tan.dream.common.log.domain.LogDO;
import com.tan.dream.core.shiro.utils.ShiroUtils;
import com.tan.dream.core.utils.HttpServletUtils;
import com.tan.dream.core.vo.ResultVO;
import com.tan.dream.sys.domain.UserDO;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * 全局的的异常拦截器（拦截所有的控制器）（带有@RequestMapping注解的方法上都会拦截）
 *
 * @Author tanleijin
 * @date Create in 22:23 2018/5/6
 */
@ControllerAdvice
@Order(-1)
public class GlobalExceptionHandler {

    private Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    LogService logService;
    /**
     * 拦截业务异常
     */
    @ExceptionHandler(DreamException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResultVO notFount(DreamException e) {
        log.error("业务异常:", e);
        return new ResultVO(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(AuthorizationException.class)
    public Object handleAuthorizationException(AuthorizationException e, HttpServletRequest request) {
        log.error(e.getMessage(), e);
        if (HttpServletUtils.jsAjax(request)) {
            return new ResultVO(403, "未授权");
        }
        return new ModelAndView("error/403");
    }

    /**
     * 登陆失败
     * @param e
     * @param request
     * @return
     */
    @ExceptionHandler(AuthenticationException.class)
    @ResponseBody
    public Object handleAuthenticationException(AuthenticationException e, HttpServletRequest request) {
        log.error(e.getMessage(), e);
        return new ResultVO(2,"账号或密码不正确");
    }

    @ExceptionHandler({Exception.class})
    public Object handleException(Exception e, HttpServletRequest request) {
        LogDO logDO = new LogDO();
        logDO.setGmtCreate(new Date());
        logDO.setOperation("error");
        logDO.setMethod(request.getRequestURL().toString());
        logDO.setParams(e.toString());
        UserDO current = ShiroUtils.getUser();
        if(null!=current){
            logDO.setUserId(current.getUserId());
            logDO.setUsername(current.getUsername());
        }
        logService.save(logDO);
        log.error(e.getMessage(), e);
        if (HttpServletUtils.jsAjax(request)) {
            return new ResultVO(500, "服务器错误，请联系管理员");
        }
        return new ModelAndView("error/500");
    }

}
