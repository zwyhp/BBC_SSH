package com.ssh.bbc.controller;

import com.ssh.bbc.util.BussinessException;
import com.ssh.bbc.util.LoginException;
import com.ssh.bbc.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


@ControllerAdvice
public class GlobalExceptionHandler {
    private Logger log = LoggerFactory.getLogger(this.getClass());
    /**
     * 数据校验
     * @param exception
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = {BindException.class, MethodArgumentNotValidException.class})
    public Object validationExceptionHandler(Exception exception) {
        BindingResult bindResult = null;
        if (exception instanceof BindException) {
            bindResult = ((BindException) exception).getBindingResult();
        } else if (exception instanceof MethodArgumentNotValidException) {
            bindResult = ((MethodArgumentNotValidException) exception).getBindingResult();
        }
        String msg;
        if (bindResult != null && bindResult.hasErrors()) {
            msg = bindResult.getAllErrors().get(0).getDefaultMessage();
            if (msg.contains("NumberFormatException")) {
                msg = "参数类型错误！";
            }
        }else {
            msg = "系统繁忙，请稍后重试...";
        }
       return ResponseUtil.parameterError(msg);
    }


    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseBody
    public Object notFount(MissingServletRequestParameterException e) {
        log.error("输入参数错误:", e);
        return ResponseUtil.badArgument("输入参数错误");
    }

    @ExceptionHandler(LoginException.class)
    @ResponseBody
    public Object notFount(LoginException e) {
        log.error("登录错误:", e);
        return ResponseUtil.badArgument(e.getMessage());
    }

    /**
     * 拦截业务异常
     */
    @ExceptionHandler(BussinessException.class)   //此处为自定义业务异常类
    @ResponseBody
    public Object notFount(BussinessException e) {
        log.error("业务异常:", e);
        return ResponseUtil.unsupport(e.getMessage());
    }


    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Object handleException(Exception e){
        System.out.println(e.getMessage());
        log.error(e.getMessage());
        return ResponseUtil.fail(-1,e.getMessage());
    }


}
